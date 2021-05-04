#!/bin/bash
set -eu

declare -A aliases=(
	[3.6]='3'
	[4.4]='4 latest'
	[4.5]='unstable'
)

self="$(basename "$BASH_SOURCE")"
cd "$(dirname "$(readlink -f "$BASH_SOURCE")")"

source '.architectures-lib'

versions=( */ )
versions=( "${versions[@]%/}" )

# get the most recent commit which modified any of "$@"
fileCommit() {
	git log -1 --format='format:%H' HEAD -- "$@"
}

# get the most recent commit which modified "$1/Dockerfile" or any file COPY'd from "$1/Dockerfile"
dirCommit() {
	local dir="$1"; shift
	(
		cd "$dir"
		fileCommit \
			Dockerfile \
			$(git show HEAD:./Dockerfile | awk '
				toupper($1) == "COPY" {
					for (i = 2; i < NF; i++) {
						print $i
					}
				}
			')
	)
}

cat <<-EOH
# this file is generated via https://github.com/docker-library/mongo/blob/$(fileCommit "$self")/$self

Maintainers: Tianon Gravi <admwiggin@gmail.com> (@tianon),
             Joseph Ferguson <yosifkit@gmail.com> (@yosifkit)
GitRepo: https://github.com/docker-library/mongo.git
EOH

# prints "$2$1$3$1...$N"
join() {
	local sep="$1"; shift
	local out; printf -v out "${sep//%/%%}%s" "$@"
	echo "${out#$sep}"
}

for version in "${versions[@]}"; do
	rcVersion="${version%-rc}"

	commit="$(dirCommit "$version")"

	fullVersion="$(git show "$commit":"$version/Dockerfile" | awk '$1 == "ENV" && $2 == "MONGO_VERSION" { gsub(/~/, "-", $3); print $3; exit }')"

	versionAliases=()
	if [ "$version" = "$rcVersion" ]; then
		while [ "$fullVersion" != "$version" -a "${fullVersion%[.-]*}" != "$fullVersion" ]; do
			versionAliases+=( $fullVersion )
			fullVersion="${fullVersion%[.-]*}"
		done
	else
		versionAliases+=( $fullVersion )
	fi
	versionAliases+=(
		$version
		${aliases[$version]:-}
	)

	from="$(git show "$commit":"$version/Dockerfile" | awk '$1 == "FROM" { print $2; exit }')"
	distro="${from%%:*}" # "debian", "ubuntu"
	suite="${from#$distro:}" # "jessie-slim", "xenial"
	suite="${suite%-slim}" # "jessie", "xenial"

	component='multiverse'
	if [ "$distro" = 'debian' ]; then
		component='main'
	fi

	variant="$suite"
	variantAliases=( "${versionAliases[@]/%/-$variant}" )
	variantAliases=( "${variantAliases[@]//latest-/}" )

	major="$(git show "$commit":"$version/Dockerfile" | awk '$1 == "ENV" && $2 == "MONGO_MAJOR" { print $3 }')"

	echo
	cat <<-EOE
		Tags: $(join ', ' "${variantAliases[@]}")
		SharedTags: $(join ', ' "${versionAliases[@]}")
		# see http://repo.mongodb.org/apt/$distro/dists/$suite/mongodb-org/$major/$component/
		Architectures: $(join ', ' $(versionArches "$version"))
		GitCommit: $commit
		Directory: $version
	EOE

	for v in \
		windows/windowsservercore-{1809,ltsc2016} \
		windows/nanoserver-{1809,sac2016} \
	; do
		dir="$version/$v"
		variant="$(basename "$v")"

		[ -f "$dir/Dockerfile" ] || continue

		commit="$(dirCommit "$dir")"

		fullVersion="$(git show "$commit":"$dir/Dockerfile" | awk '$1 == "ENV" && $2 == "MONGO_VERSION" { gsub(/~/, "-", $3); print $3; exit }')"

		versionAliases=()
		if [ "$version" = "$rcVersion" ]; then
			while [ "$fullVersion" != "$version" -a "${fullVersion%[.-]*}" != "$fullVersion" ]; do
				versionAliases+=( $fullVersion )
				fullVersion="${fullVersion%[.-]*}"
			done
		else
			versionAliases+=( $fullVersion )
		fi
		versionAliases+=(
			$version
			${aliases[$version]:-}
		)

		variantAliases=( "${versionAliases[@]/%/-$variant}" )
		variantAliases=( "${variantAliases[@]//latest-/}" )

		sharedTags=()
		for windowsShared in windowsservercore nanoserver; do
			if [[ "$variant" == "$windowsShared"* ]]; then
				sharedTags=( "${versionAliases[@]/%/-$windowsShared}" )
				sharedTags=( "${sharedTags[@]//latest-/}" )
				break
			fi
		done
		if [[ "$variant" == 'windowsservercore'* ]]; then
			sharedTags+=( "${versionAliases[@]}" )
		fi

		echo
		echo "Tags: $(join ', ' "${variantAliases[@]}")"
		if [ "${#sharedTags[@]}" -gt 0 ]; then
			echo "SharedTags: $(join ', ' "${sharedTags[@]}")"
		fi
		cat <<-EOE
			Architectures: windows-amd64
			GitCommit: $commit
			Directory: $dir
		EOE
		[ "$variant" = "$v" ] || echo "Constraints: $variant"
	done
done
