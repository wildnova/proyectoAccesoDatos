ALTER TABLE informe DROP CONSTRAINT IF EXISTS fk_informe_num_factura;

ALTER TABLE informe DROP CONSTRAINT IF EXISTS fk_informe_dni_trabajador;

ALTER TABLE vehiculo DROP CONSTRAINT IF EXISTS fk_vehiculo_dni_trabajador;

ALTER TABLE repuestos DROP CONSTRAINT IF EXISTS fk_repuestos_bastidor_vehiculo;

ALTER TABLE repuestos DROP CONSTRAINT IF EXISTS fk_repuestos_num_factura;

ALTER TABLE factura DROP CONSTRAINT IF EXISTS fk_factura_num_expedicion_informe;

ALTER TABLE obd DROP CONSTRAINT IF EXISTS fk_obd_bastidor_vehiculo;

ALTER TABLE trabajador_repuestos DROP CONSTRAINT IF EXISTS fk_trabajador_repuestos_dni_trabajador;

ALTER TABLE trabajador_repuestos DROP CONSTRAINT IF EXISTS fk_trabajador_repuestos_num_serie;

ALTER TABLE trabajador_vehiculo DROP CONSTRAINT IF EXISTS fk_trabajador_vehiculo_dni_trabajador;

ALTER TABLE  trabajador_vehiculo DROP CONSTRAINT IF EXISTS fk_trabajador_vehiculo_bastidor_vehiculo;

DROP TABLE IF EXISTS trabajador_repuestos;
DROP TABLE IF EXISTS trabajador_vehiculo;
DROP TABLE IF EXISTS trabajador;
DROP TABLE IF EXISTS vehiculo;
DROP TABLE IF EXISTS repuestos;
DROP TABLE IF EXISTS informe;
DROP TABLE IF EXISTS factura;
DROP TABLE IF EXISTS obd;


CREATE TABLE trabajador
(
    dni         VARCHAR(9)  PRIMARY KEY,
    nombre      VARCHAR(50) NOT NULL,
    apellido1   VARCHAR(30) NOT NULL,
    apellido2   VARCHAR(30),
    funcion     VARCHAR(30) NOT NULL

);

CREATE TABLE vehiculo
(
    bastidor        VARCHAR(40) PRIMARY KEY,
    matricula       VARCHAR(15),
    marca           VARCHAR(20),
    modelo          VARCHAR(40),
    motor           VARCHAR(40),
    potenciaCv      VARCHAR(4),
    carburante      VARCHAR(40),
    aceite          VARCHAR(20),
    consumo         VARCHAR(40),
    fecha_entrada   DATE,

    dni_trabajador  VARCHAR(9),
    FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni)
   
);

CREATE TABLE informe
(
    num_expedicion       INT(30) PRIMARY KEY,
    tareas              TEXT(500),

    dni_trabajador VARCHAR(9),
    FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni)
);

CREATE TABLE factura
(
    num_factura     INT(30) PRIMARY KEY,
    precio          FLOAT(8,2)  NOT NULL,
    lineaFactura    TEXT(300),

    num_expedicion_informe INT(30),

    FOREIGN KEY (num_expedicion_informe) REFERENCES informe(num_expedicion)
);

CREATE TABLE repuestos
(
    num_serie       VARCHAR(30) PRIMARY KEY,
    referencia      VARCHAR(30),
    nombre          VARCHAR(40),
    marca           VARCHAR(30),
    uso             VARCHAR(50),
    tienda          VARCHAR(30),
    fecha_compra    DATE,

    bastidor_vehiculo VARCHAR(40),
    num_factura       INT(30),

    FOREIGN KEY (bastidor_vehiculo) REFERENCES vehiculo(bastidor),
    FOREIGN KEY (num_factura) REFERENCES factura(num_factura)
);



CREATE TABLE obd
(
    id              INT(10) PRIMARY KEY,
    rpm_inst        INT(5), 
    rpm_avg         INT(5),
    consumo_inst    FLOAT(6,4),
    consumo_avg     FLOAT(6,4),  
    temp_aceite     FLOAT(5,2),
    temp_agua       FLOAT(5,2),
    cod_salida      VARCHAR(10),

    bastidor_vehiculo VARCHAR(40),
    FOREIGN KEY (bastidor_vehiculo) REFERENCES vehiculo(bastidor)
);

CREATE TABLE trabajador_repuestos
(

    dni_trabajador  VARCHAR(9),
    num_serie       VARCHAR(30),

    FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni),
    FOREIGN KEY (num_serie) REFERENCES repuestos(num_serie)
);

CREATE TABLE trabajador_vehiculo
(

    dni_trabajador  VARCHAR(9),  
    bastidor_vehiculo VARCHAR(40),

    tiempo_dedicado VARCHAR(30),

    FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni),
    FOREIGN KEY (bastidor_vehiculo) REFERENCES vehiculo(bastidor)
);

/*
ALTER TABLE informe ADD CONSTRAINT fk_informe_dni_trabajador
FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni);

ALTER TABLE vehiculo ADD CONSTRAINT fk_vehiculo_dni_trabajador
FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni);

ALTER TABLE repuestos ADD CONSTRAINT fk_repuestos_bastidor_vehiculo
FOREIGN KEY (bastidor_vehiculo) REFERENCES vehiculo(bastidor);

ALTER TABLE repuestos ADD CONSTRAINT fk_repuestos_num_factura
FOREIGN KEY (num_factura) REFERENCES factura(num_factura);

ALTER TABLE factura ADD CONSTRAINT fk_factura_num_expedicion_informe
FOREIGN KEY (num_expedicion_informe) REFERENCES informe(num_expedicion);

ALTER TABLE obd ADD CONSTRAINT fk_obd_bastidor_vehiculo
FOREIGN KEY (bastidor_vehiculo) REFERENCES vehiculo(bastidor);



ALTER TABLE trabajador_repuestos ADD CONSTRAINT fk_trabajador_repuestos_dni_trabajador
FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni);

ALTER TABLE trabajador_repuestos ADD CONSTRAINT fk_trabajador_repuestos_num_serie
FOREIGN KEY (num_serie) REFERENCES repuestos(num_serie);

ALTER TABLE trabajador_vehiculo ADD CONSTRAINT fk_trabajador_vehiculo_dni_trabajador
FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni);

ALTER TABLE trabajador_vehiculo ADD CONSTRAINT fk_trabajador_vehiculo_bastidor_vehiculo
FOREIGN KEY (bastidor_vehiculo) REFERENCES vehiculo(bastidor);*/