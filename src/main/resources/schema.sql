DROP table if exists CAR_VALUE;
DROP table if exists AIRPLANE_VALUE;
DROP table if exists CAR;
DROP table if exists AIRPLANE;


create table CAR
(
    id            IDENTITY primary key,
    brand         VARCHAR2(150),
    model         VARCHAR2(200),
    power         DOUBLE,
    year_of_issue YEAR
);

create table AIRPLANE
(
    id            IDENTITY primary key,
    brand         VARCHAR2(150),
    model         VARCHAR2(200),
    manufacturer  VARCHAR2(500),
    year_of_issue YEAR,
    fuelCapacity  INT,
    seats         INT
);

create table CAR_VALUE
(
    id             IDENTITY primary key,
    date_time      TIMESTAMP NOT NULL,
    assessed_value DEC(20)   NOT NULL,
    id_car         INT       NOT NULL,
    FOREIGN KEY (id_car) REFERENCES CAR (id)
);

create table AIRPLANE_VALUE
(
    id             IDENTITY primary key,
    date_time      TIMESTAMP,
    assessed_value DEC(20),
    id_airplane    INT,
    FOREIGN KEY (id_airplane) REFERENCES AIRPLANE (id)
);

