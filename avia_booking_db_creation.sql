CREATE DATABASE if not exists avia_booking;
USE avia_booking;

CREATE table if not exists airport_types (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	name VARCHAR(15) NOT NULL
);

INSERT INTO airport_types VALUES 
('international'),
('regional'),
('hub');

CREATE TABLE if not exists airports (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	name VARCHAR(20) NOT NULL,
	city VARCHAR(20) NOT NULL,
	country VARCHAR(20) NOT NULL,
	airport_type_id BIGINT NOT NULL,
	FOREIGN KEY (airport_type_id) REFERENCES airport_types(id) ON DELETE RESTRICT ON UPDATE CASCADE 
);

INSERT INTO airports (name, city, country, airport_type_id) VALUES 
('Charles de Gaulle', 'Paris', 'France', 1),
('Heathrow', 'London', 'United Kingdom', 1),
('Frankfurt', 'Frankfurt', 'Germany', 1);

CREATE table if not exists passports (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	number VARCHAR(10) NOT NULL UNIQUE);
	
INSERT INTO passports (number) VALUES 
('AA234567'),
('WE123765'),
('RT235490');

CREATE TABLE if not exists passengers (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	first_name VARCHAR(15) NOT NULL,
	last_name VARCHAR(15) NOT NULL,
	age INT NOT NULL,
	phone_number VARCHAR(15) NOT NULL,
	email VARCHAR(25) NOT NULL,
	passport_id BIGINT NOT NULL,
	CONSTRAINT fk_passenger_passport FOREIGN KEY (passport_id) REFERENCES passports(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO passengers (first_name, last_name, age, phone_number, email, passport_id) VALUES 
('Tom', 'White', 20, '0445678900', 'tom_white@gmail.com', 1),
('Alice', 'Brown', 22, '0440098765', 'alice_brown@gmail.com', 2),
('Bill', 'Black', 40, '0443333333', 'bill_black@gmail.com', 3);

CREATE table if not exists arrivals (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
    airport_id BIGINT NOT NULL UNIQUE,
	CONSTRAINT fk_airport_arrival FOREIGN KEY (airport_id) REFERENCES airports(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO arrivals (airport_id) VALUES 
(1),(2),(3);

CREATE table if not exists departures (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
    airport_id BIGINT NOT NULL UNIQUE,
	CONSTRAINT fk_airport_departure FOREIGN KEY (airport_id) REFERENCES airports(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO departures (airport_id) VALUES 
(1),(2),(3);

CREATE table if not exists plane_types (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	name VARCHAR(10) NOT NULL UNIQUE,
	seats_number INT NOT NULL
);

INSERT INTO plane_types (name, seats_number) VALUES 
('Boing-737', 350),
('Airbus-320', 180);

CREATE table if not exists airlines (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	name VARCHAR(40) NOT NULL UNIQUE,
	code VARCHAR(3) NOT NULL UNIQUE
);

INSERT INTO airlines (name, code) VALUES 
('KLM Royal Dutch Airlines', 'KLM'),
('Ryanair', 'RYR');

CREATE TABLE if not exists flights (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	number VARCHAR(4) NOT NULL,
	departure_time TIMESTAMP NOT NULL,
	arrival_time TIMESTAMP NOT NULL,
	airline_id BIGINT NOT NULL,
	departure_id BIGINT NOT NULL,
	arrival_id BIGINT NOT NULL,
	plane_type_id BIGINT NOT NULL,
	FOREIGN KEY (airline_id) REFERENCES airlines(id) ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (departure_id) REFERENCES departures(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (arrival_id) REFERENCES arrivals(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (plane_type_id) REFERENCES plane_types(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO flights (number, departure_time, arrival_time, airline_id, departure_id, arrival_id, plane_type_id) VALUES 
('0035', '2023-12-19 12:35:00', '2023-12-19 13:50:00', 1, 1, 2, 1),
('0141', '2023-12-24 16:10:00', '2023-12-24 17:45:00', 2, 2, 3, 2);


CREATE TABLE if not exists lagguage_tariffs (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	hand_lagguage_price DOUBLE NOT NULL,
	register_lagguage_price DOUBLE NOT NULL,
	airline_id BIGINT NOT NULL,
	CONSTRAINT fk_airline_lagguage_tariff FOREIGN KEY (airline_id) REFERENCES airlines(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO lagguage_tariffs (hand_lagguage_price, register_lagguage_price, airline_id) VALUES 
(15.0, 25.0, 1),
(10.5, 18.5, 2);

CREATE TABLE if not exists flight_types (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	type VARCHAR(25) NOT NULL UNIQUE
);

INSERT INTO flight_types (type) VALUES
('regular'),
('lowcost'),
('charter');

CREATE TABLE if not exists airlines_has_flight_types (
	airline_id BIGINT NOT NULL,
	flight_type_id BIGINT NOT NULL,
	FOREIGN KEY (airline_id) REFERENCES airlines(id) ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (flight_type_id) REFERENCES flight_types(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO airlines_has_flight_types (airline_id, flight_type_id) VALUES
(1, 1),
(1, 3),
(2, 2);

CREATE TABLE if not exists service_classes (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	class VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO service_classes (class) VALUES
('business'),
('economy');

CREATE TABLE if not exists tariffs (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	name VARCHAR(30) NOT NULL,
	hand_lagguage INT NOT NULL,
	register_lagguage INT NOT NULL,
	place_choice TINYINT NOT NULL,
	fast_track TINYINT NOT NULL,
	priority_boarding TINYINT NOT NULL,
	airline_id BIGINT NOT NULL,
	service_class_id BIGINT NOT NULL,
	base_price DOUBLE NOT NULL,
	FOREIGN KEY (airline_id) REFERENCES airlines(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (service_class_id) REFERENCES service_classes(id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO tariffs (name, hand_lagguage, register_lagguage, place_choice, fast_track, priority_boarding, airline_id, service_class_id, base_price) VALUES 
('Economy Saver', 1, 0, 0, 0, 0, 1, 2, 134.5),
('Economy Standard', 1, 1, 1, 0, 0, 1, 2, 154.5),
('Business Full Flex', 2, 2, 1, 1, 1, 1, 1, 200.0),
('Economy Flex', 1, 0, 1, 0, 0, 2, 2, 112.5),
('Business Standard', 1, 1, 1, 1, 1, 2, 1, 173.25);


CREATE TABLE if not exists tickets (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
	passenger_id BIGINT NOT NULL,
	flight_id BIGINT NOT NULL,
	add_hand_lagguage INT NOT NULL,
	add_register_lagguage INT NOT NULL,
	tariff_id BIGINT NOT NULL,
	price DOUBLE NOT NULL,
	CONSTRAINT fk_ticket_tariff FOREIGN KEY (tariff_id) REFERENCES tariffs(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_ticket_passenger FOREIGN KEY (passenger_id) REFERENCES passengers(id) ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT fk_ticket_flight FOREIGN KEY (flight_id) REFERENCES flights(id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO tickets (passenger_id, flight_id, add_hand_lagguage, add_register_lagguage, tariff_id, price) VALUES 
(1, 1, 0, 0, 1, 100.0),
(2, 1, 1, 1, 3, 100.0),
(3, 2, 1, 0, 4, 100.0);