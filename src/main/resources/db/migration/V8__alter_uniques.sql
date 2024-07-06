ALTER TABLE mortality_table
    DROP CONSTRAINT IF EXISTS unique_year_country_code;

ALTER TABLE population
    DROP CONSTRAINT IF EXISTS unique_year_country_code;


ALTER TABLE mortality_table
    ADD CONSTRAINT unique_mt_year_country_code UNIQUE (year, country_code);


ALTER TABLE population
    ADD CONSTRAINT unique_p_year_country_code UNIQUE (year, country_code);