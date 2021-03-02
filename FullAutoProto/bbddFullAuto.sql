PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE trabajador
(
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido1 VARCHAR(30) NOT NULL,
    apellido2 VARCHAR(30),
    funcion VARCHAR(30) NOT NULL,
    numExpedicion_informe INT(30) REFERENCES informe(numExpedicion) ON DELETE CASCADE
);
INSERT INTO trabajador VALUES('11111111A','Juan','Bautista','Conejero','cambios',1111);
CREATE TABLE vehiculo
(
    bastidor    VARCHAR(40) PRIMARY KEY,
    matricula   VARCHAR(15) NOT NULL,
    marca       VARCHAR(20),
    modelo      VARCHAR(40),
    motor       VARCHAR(40),
    potenciaCv  VARCHAR(4),
    carburante  VARCHAR(40),
    aceite      VARCHAR(20),
    consumo     VARCHAR(40),
    dni_trabajador VARCHAR(9) REFERENCES trabajador(dni) ON DELETE CASCADE,
    Id_obd      INT(10) REFERENCES obd(id)  ON DELETE CASCADE
);
INSERT INTO vehiculo VALUES('111111111111111','4470KJZ','Kia','Cerato','1600cc','130','diesel','-10-40','5.8l/100km','11111111A',11111111);
CREATE TABLE repuestos
(
    referencia  VARCHAR(30) PRIMARY KEY,
    nombre      VARCHAR(40),
    marca        VARCHAR(30),
    uso         VARCHAR(50),
    tienda      VARCHAR(30),
    numBastidor_vehiculo VARCHAR(40) REFERENCES vehiculo(bastidor) ON DELETE CASCADE
);
INSERT INTO repuestos VALUES('11111','pastilla de freno','brembo','sistema de frenos','Serca','111111111111111');
CREATE TABLE informe
(
    numExpedicion   INT(30) PRIMARY KEY,
    tareas          TEXT(500),
    numFactura_factura INT(30) REFERENCES factura(numFactura) ON DELETE CASCADE
);
INSERT INTO informe VALUES(1111,'Sustituir pastillas de freno antiguas por la nuevas',111);
CREATE TABLE factura
(
    numFactura  INT(30) PRIMARY KEY,
    precio      INT(5)  NOT NULL,
    lineaFactura    TEXT(300)
);
INSERT INTO factura VALUES(111,35.450000000000002841,'Pastillas freno brembo delanteras y traseras');
CREATE TABLE obd
(
    id              INT(10) PRIMARY KEY,
    rpm_inst        INT(5), 
    rpm_avg         INT(5),
    consumo_inst    FLOAT(6,4),
    consumo_avg     FLOAT(6,4),  
    temp_aceite     FLOAT(5,2),
    temp_agua       FLOAT(5,2),
    cod_salida      VARCHAR(10)
);
INSERT INTO obd VALUES(11111111,2500,1700,8.4000000000000003552,6.0,95.40000000000000568,89.450000000000002842,'12222222');
COMMIT;
