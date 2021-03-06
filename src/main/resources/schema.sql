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
    fuel_capacity  INT,
    seats         INT
);

create table CAR_VALUE
(
    id             IDENTITY primary key,
    date_time      DATETIME NOT NULL,
    assessed_value DEC(20)  NOT NULL,
    car_id         LONG      NOT NULL,
    FOREIGN KEY (car_id) REFERENCES CAR (id) ON DELETE CASCADE
);

create table AIRPLANE_VALUE
(
    id             IDENTITY primary key,
    date_time      DATETIME NOT NULL,
    assessed_value DEC(20)  NOT NULL,
    airplane_id    LONG      NOT NULL,
    FOREIGN KEY (airplane_id) REFERENCES AIRPLANE (id) ON DELETE CASCADE
);

