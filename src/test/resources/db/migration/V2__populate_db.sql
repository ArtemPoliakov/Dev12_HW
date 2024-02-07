INSERT INTO client (name) VALUES
  ('Michael Williams'),
  ('Emily Rodriguez'),
  ('Christopher Turner'),
  ('Olivia Baker'),
  ('Jacob Lopez'),
  ('Sophia White'),
  ('Ethan Taylor'),
  ('Ava Harris'),
  ('Matthew Martinez'),
  ('Emma Clark');

INSERT INTO planet (id, name) VALUES
    ('MARS', 'Mars'),
    ('MERCURY', 'Mercury'),
    ('VENUS', 'Venus'),
    ('EARTH', 'Earth'),
    ('NEPTUNE', 'Neptune');

INSERT INTO TICKET (created_at, client_id, from_planet_id, to_planet_id) VALUES
  (CURRENT_TIMESTAMP, 1, 'EARTH', 'MARS'),
  (CURRENT_TIMESTAMP, 2, 'MARS', 'EARTH'),
  (CURRENT_TIMESTAMP, 3, 'VENUS', 'EARTH'),
  (CURRENT_TIMESTAMP, 4, 'MARS', 'NEPTUNE'),
  (CURRENT_TIMESTAMP, 5, 'MERCURY', 'VENUS'),
  (CURRENT_TIMESTAMP, 6, 'MARS', 'NEPTUNE'),
  (CURRENT_TIMESTAMP, 7, 'EARTH', 'VENUS'),
  (CURRENT_TIMESTAMP, 8, 'NEPTUNE', 'MARS'),
  (CURRENT_TIMESTAMP, 9, 'VENUS', 'MERCURY'),
  (CURRENT_TIMESTAMP, 10, 'MERCURY', 'VENUS');
