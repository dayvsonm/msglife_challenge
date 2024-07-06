CREATE TABLE population (
     id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
     year INT NOT NULL,
     value INT NOT NULL,
     population_female INT NOT NULL,
     population_male INT NOT NULL,
     country_id UUID  REFERENCES country(id),
     UNIQUE (year, country_id)
);