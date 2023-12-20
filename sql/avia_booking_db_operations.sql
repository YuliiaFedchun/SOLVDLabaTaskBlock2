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

