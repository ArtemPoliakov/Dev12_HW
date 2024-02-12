CREATE TABLE IF NOT EXISTS client (

id BIGSERIAL PRIMARY KEY,
name VARCHAR(200) NOT NULL CONSTRAINT check_client_name_length CHECK (LENGTH(client.name) BETWEEN 3 and 200)

);

CREATE TABLE IF NOT EXISTS planet (

id VARCHAR(255) PRIMARY KEY CONSTRAINT planet_id_format CHECK (id ~ '^[A-Z0-9]+$'),
name VARCHAR(500) NOT NULL CONSTRAINT check_planet_name_length CHECK (LENGTH(planet.name) BETWEEN 1 AND 500)

);

CREATE TABLE IF NOT EXISTS ticket(

id BIGSERIAL PRIMARY KEY,
created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
client_id BIGINT NOT NULL,
from_planet_id VARCHAR(255),
to_planet_id VARCHAR(255),
FOREIGN KEY(client_id) REFERENCES client(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(from_planet_id) REFERENCES planet(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(to_planet_id) REFERENCES planet(id) ON DELETE CASCADE ON UPDATE CASCADE

)

