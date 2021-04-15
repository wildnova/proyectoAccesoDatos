INSERT INTO trabajador
VALUES ('11111111A','Juan','Bautista','Conejero','cambios');
INSERT INTO trabajador
VALUES ('22222222B','Pedro','Tremo','García','Mecánico');
INSERT INTO trabajador
VALUES ('33333333C','Ivan','Pérez','Fernández','Gestión');

INSERT INTO vehiculo
VALUES('111111111111111', '4470KJZ','Kia','Cerato','1600cc','130','diesel','-10-40','5.8l/100km','2020-11-30','60 minutos','11111111A');
INSERT INTO vehiculo
VALUES('222222222222222', '2235BFS','Ferrari','California','3000cc','350','gasolina','-10-40','10.50l/100km','2019-6-02','300 minutos','22222222B');
INSERT INTO vehiculo
VALUES('333333333333333', '3465FDH','Audi','A3','2200cc','180','diesel','-10-40','4.9l/100km','2021-02-21','30 minutos','33333333C');

INSERT INTO obd
VALUES ('11111111','2500','63.2','89.45','122','Frenos','111111111111111');
INSERT INTO obd
VALUES ('22222222','4200','70.5','120.51','67','Sistema de refrigeración','222222222222222'); 
INSERT INTO obd
VALUES ('33333333','3100','61.1','92.1','25','Inyección','333333333333333'); 

INSERT INTO informe
VALUES ('1111','Sustituir pastillas de freno antiguas por las nuevas','11111111A');
INSERT INTO informe
VALUES ('2222','Comprobar líquido de frenos','11111111A');
INSERT INTO informe
VALUES ('3333','Comprobar piloto encendido con la máquina de diagnosis ','33333333C');

INSERT INTO factura
VALUES ('111', '35.45','Pastillas freno brembo delanteras y traseras','1111');
INSERT INTO factura
VALUES ('222', '19.60','Líquido de frenos','2222');
INSERT INTO factura
VALUES ('333', '125.40','Inyector diésel ','3333');

INSERT INTO repuestos
VALUES ('25','11111','pastilla de freno','brembo','sistema de frenos','Serca','2021-01-06','111111111111111','111');
INSERT INTO repuestos
VALUES ('37','22222','Disco de freno','brembo','sistema de frenos','Autodoc','2021-03-07','222222222222222','222');
INSERT INTO repuestos
VALUES ('44','33333','Inyector','Audi','Sistema de inyección','Wolkswagen','2020-12-13','333333333333333','333');