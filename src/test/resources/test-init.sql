CREATE DATABASE IF NOT EXISTS stockcar_test;
USE stockcar_test;

DROP TABLE IF EXISTS voiture;
CREATE TABLE voiture (
    id integer primary key auto_increment,    
    marque varchar(30) not null,
    modele varchar(30) not null,
    finition varchar(30) not null,
    carburant char,
    km integer,
    annee integer,
    prix integer
);

INSERT INTO voiture (marque, modele, finition, carburant, km, annee, prix) VALUES
('Test', 'TestModel', 'TestFinition', 'E', 10000, 2020, 15000);
