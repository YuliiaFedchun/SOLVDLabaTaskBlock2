UPDATE passengers
SET email = 'white@gmail.com'
WHERE last_name = 'White';

UPDATE passengers
SET name = 'Jane', last_name = 'Green'
WHERE id = 2;

UPDATE plane_types
SET seats_number = 400
WHERE name = 'Boeing-737';

UPDATE airlines
SET name = 'KLM Royal Dutch'
WHERE code = 'KLM';

UPDATE flights
SET arrival_time = '2023-12-19 14:00:00', plane_type_id = 2
WHERE id = 1;

UPDATE lagguage_tariffs
SET hand_lagguage_price = 18.0, register_lagguage_price = 27.0
WHERE airline_id = 1;

UPDATE tariffs
SET name = 'Economy Standard'
WHERE id = 4;

UPDATE tariffs
SET base_price = 115.0
WHERE name = 'Economy Standard' AND airline_id = 2;

UPDATE tariffs
SET place_choice = 1
WHERE service_class_id = 1 OR airline_id = 1;

UPDATE tickets
SET add_hang_lagguage = 1, price = 125.0
WHERE id = 1;


DELETE FROM passports;

DELETE FROM passengers WHERE id = 3;

DELETE FROM airports WHERE country = 'Germany';

DELETE FROM airports WHERE airport_type_id = 2;

DELETE FROM airlines WHERE code = 'RYR';

DELETE FROM tariffs WHERE airline_id = 2 AND service_class_id = 1;

DELETE FROM tickets WHERE passenger_id = 3;

DROP TABLE tickets;

DELETE FROM flights WHERE arrival_id = 3;

DELETE FROM lagguage_tariffs WHERE hand_lagguage_price > 12.0;

ALTER TABLE airport_types
MODIFY COLUMN name VARCHAR(20) NOT NULL;

ALTER TABLE passengers
ADD booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE passengers
ADD sex VARCHAR(1);

ALTER TABLE passengers
DROP COLUMN SEX VARCHAR(1);

ALTER TABLE plane_types
RENAME COLUMN name TO type;

SELECT name, city
FROM airports
INNER JOIN departures WHERE airports.id = departures.airport_id;

SELECT flights.number, flights.departure_time, flights.arrival_time, flights.airline_id, airlines.code
FROM flights
LEFT JOIN airlines ON flights.airline_id = airlines.id;

SELECT passengers.first_name, passengers.last_name, passports.number
FROM passengers
LEFT JOIN passports ON passengers.passport_id = passports.id;

SELECT passengers.first_name, passengers.last_name, tickets.flight_id, tickets.price
FROM passengers
RIGHT JOIN tickets ON passengers.id = tickets.passenger_id;

SELECT tariffs.name, tariffs.base_price, service_classes.class
FROM tariffs
FULL OUTER JOIN service_classes
ON tariffs.service_class_id = service_classes.id
ORDER BY service_classes.class;

SELECT COUNT(id), flight_id
FROM tickets
GROUP BY flight_id
ORDER BY COUNT(id)DESC;

SELECT SUM(price), flight_id
FROM tickets
GROUP BY flight_id
ORDER BY SUM(price);

SELECT airlines.code, AVG(base_price)
FROM tariffs
LEFT JOIN airlines ON tariffs.airline_id = airlines.id
GROUP BY airlines.code;

SELECT service_classes.class, MIN(base_price)
FROM tariffs
LEFT JOIN service_classes ON tariffs.service_class_id = service_classes.id
GROUP BY service_classes.class;

SELECT airport_types.name, COUNT(airports.id)
FROM airports
LEFT JOIN airport_types ON airports.airport_type_id = airport_types.id
GROUP BY airport_types.name
ORDER BY COUNT(airports.id)DESC;

SELECT airlines.code, COUNT(airlines_has_flight_types.airline_id)
FROM airlines
LEFT JOIN airlines_has_flight_types ON airlines.id = airlines_has_flight_types.airline_id
GROUP BY airlines.code
ORDER BY COUNT(airlines_has_flight_types.airline_id);

SELECT airlines.code, COUNT(passengers.id)
FROM airlines
LEFT JOIN flights ON airlines.id = flights.airline_id
LEFT JOIN tickets ON flights.id = tickets.flight_id
LEFT JOIN passengers ON tickets.passenger_id = passengers.id
GROUP BY airlines.code
ORDER BY COUNT(passengers.id);

SELECT airlines.code, COUNT(tickets.id)
FROM airlines
LEFT JOIN flights ON airlines.id = flights.airline_id
LEFT JOIN tickets ON flights.id = tickets.flight_id
GROUP BY airlines.code
HAVING COUNT(tickets.id) > 10;

SELECT airports.name, COUNT(flights.departure_id)
FROM airports
LEFT JOIN departures ON airports.id = departures.airport_id
LEFT JOIN flights ON departures.id = flights.departure_id
GROUP BY airports.name
HAVING COUNT(flights.departure_id) = 1;

SELECT plane_types.name, COUNT(flights.plane_type_id)
FROM flights
LEFT JOIN plane_types ON flights.plane_type_id = plane_types.id
GROUP BY plane_types.name
HAVING COUNT(flights.plane_type_id) = 1;

SELECT passengers.last_name, passports.number, tickets.price, tariffs.name, 
		service_classes.class, airlines.code, flights.number, flights.departure_time,
		flights.arrival_time, plane_types.name, airports.name, airport_types.name
FROM passengers 
LEFT JOIN passports ON passengers.passport_id = passports.id
LEFT JOIN tickets ON passengers.id = tickets.passenger_id
INNER JOIN tariffs ON tickets.tariff_id = tariffs.id
LEFT JOIN service_classes ON tariffs.service_class_id = service_classes.id
LEFT JOIN airlines ON tariffs.airline_id = airlines.id
LEFT JOIN flights ON tickets.flight_id = flights.id
LEFT JOIN plane_types ON flights.plane_type_id = plane_types.id
LEFT JOIN departures ON flights.departure_id = departures.id
LEFT JOIN airports ON departures.airport_id = airports.id
LEFT JOIN airport_types ON airports.airport_type_id = airport_types.id;

