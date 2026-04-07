INSERT INTO Client(name) VALUES ('Stepan'), ('Julia'), ('Anna'), ('Anatoliy'),
('Joeline'), ('Megatron'), ('Siri'), ('Joseph'), ('Tirion'), ('John');

INSERT INTO Planet(id, name) VALUES
('MARS', 'Mars'), ('VEN', 'Venus'), ('EA', 'Earth'),
('MERC', 'Mercury'), ('PLUTO', 'Pluto');

INSERT INTO Ticket(client_id, from_planet_id, to_planet_id) VALUES
(1, 'MARS', 'VEN'), (2, 'VEN', 'EA'), (3, 'MERC', 'VEN'),
(4, 'EA', 'PLUTO'), (5, 'MARS', 'MERC'),
(6, 'EA', 'MERC'), (7, 'PLUTO', 'EA'),
(8, 'MERC', 'EA'), (9, 'EA', 'MARS'), (10, 'MARS', 'PLUTO');