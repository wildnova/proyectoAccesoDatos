DROP TABLE IF EXISTS trabajador_repuestos;
DROP TABLE IF EXISTS repuestos;
DROP TABLE IF EXISTS factura;
DROP TABLE IF EXISTS informe;
DROP TABLE IF EXISTS obd;
DROP TABLE IF EXISTS vehiculo;
DROP TABLE IF EXISTS trabajador;

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
    tiempo_dedicado VARCHAR(30),
    
    dni_trabajador  VARCHAR(9) DEFAULT '00000000X',
    FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni) ON DELETE SET DEFAULT
   
);

CREATE TABLE informe
(
    num_expedicion       INT(30) PRIMARY KEY,
    tareas              TEXT(500),

    dni_trabajador VARCHAR(9) DEFAULT '00000000X',
    FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni) ON DELETE SET DEFAULT
);

CREATE TABLE factura
(
    num_factura     INT(30) PRIMARY KEY,
    precio          FLOAT(8,2)  NOT NULL,
    lineaFactura    TEXT(300),

    num_expedicion_informe INT(30) DEFAULT '0000',
    FOREIGN KEY (num_expedicion_informe) REFERENCES informe(num_expedicion) ON DELETE SET DEFAULT
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

    bastidor_vehiculo VARCHAR(40) DEFAULT '000000000000000',
    num_factura       INT(30) DEFAULT '000',

    FOREIGN KEY (bastidor_vehiculo) REFERENCES vehiculo(bastidor) ON DELETE SET DEFAULT,
    FOREIGN KEY (num_factura) REFERENCES factura(num_factura) ON DELETE SET DEFAULT
);



CREATE TABLE obd
(
    id              INT(10) PRIMARY KEY,
    rpm_inst        INT(5), 
    temp_aceite     FLOAT(5,2),
    temp_agua       FLOAT(5,2),
    cod_salida      VARCHAR(10),
    sensores        VARCHAR(25),

    bastidor_vehiculo VARCHAR(40) DEFAULT '000000000000000',
    FOREIGN KEY (bastidor_vehiculo) REFERENCES vehiculo(bastidor) ON DELETE SET DEFAULT
);

CREATE TABLE trabajador_repuestos
(

    dni_trabajador  VARCHAR(9) DEFAULT '00000000X',
    num_serie       VARCHAR(30) DEFAULT '00',

    FOREIGN KEY (dni_trabajador) REFERENCES trabajador(dni) ON DELETE SET DEFAULT,
    FOREIGN KEY (num_serie) REFERENCES repuestos(num_serie) ON DELETE SET DEFAULT
);

