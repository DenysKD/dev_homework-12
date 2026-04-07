CREATE TABLE Client (
id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name varchar NOT NULL CHECK (length(name) >= 3 AND length(name) <= 200)
);

CREATE TABLE Planet (
id varchar NOT NULL PRIMARY KEY CHECK (id ~ '^[A-Z]+$'),
name varchar NOT NULL CHECK (length(name) >= 1 AND length(name) <= 500)
);

CREATE TABLE Ticket (
id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
created_at timestamp WITH time ZONE DEFAULT now(),
client_id bigint NOT NULL,
from_planet_id varchar NOT NULL,
to_planet_id varchar NOT NULL
);

ALTER TABLE Ticket ADD CONSTRAINT client_if_fk FOREIGN KEY (client_id) REFERENCES Client(id) ON DELETE CASCADE;
ALTER TABLE Ticket ADD CONSTRAINT from_planet_id_fk FOREIGN KEY(from_planet_id) REFERENCES Planet(id) ON DELETE CASCADE;
ALTER TABLE Ticket ADD CONSTRAINT to_planet_id_fk FOREIGN KEY (to_planet_id) REFERENCES Planet(id) ON DELETE CASCADE;