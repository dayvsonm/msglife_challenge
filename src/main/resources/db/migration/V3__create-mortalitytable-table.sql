CREATE TABLE mortality_table (
     id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
     year INT NOT NULL,
     rate_female DECIMAL(5,2),
     rate_male DECIMAL(5,2),
     country_id UUID references country(id),
     UNIQUE(year,country_id)
);