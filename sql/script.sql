CREATE DATABASE concessionaria;
--non utilizzo l'enum perche' crea problemi con JPA, le varie soluzioni provate creavano conflitti.
--CREATE TYPE StatoAuto AS ENUM ('disponibile', 'venduta');

CREATE TABLE Automobile (
    id SERIAL PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modello VARCHAR(50) NOT NULL,
    motorizzazione VARCHAR(50) NOT NULL,
    anno INT NOT NULL,
    prezzo NUMERIC(10,2) NOT NULL CHECK (prezzo >= 0),
    stato VARCHAR(11) NOT NULL DEFAULT 'disponibile',
        CHECK (stato IN ('disponibile', 'venduta'))
);

CREATE TABLE roles (
    name VARCHAR(30) PRIMARY KEY
);

CREATE TABLE utenze (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    role_name VARCHAR(30),
    FOREIGN KEY (role_name) REFERENCES roles(name) ON DELETE SET NULL
);