ALTER TABLE mortality_table
    DROP CONSTRAINT mortality_table_country_code_fkey,
    ADD CONSTRAINT mortality_table_country_code_fkey FOREIGN KEY (country_code) REFERENCES country(iso_code);

ALTER TABLE population
    DROP CONSTRAINT population_country_id_fkey,
    ADD CONSTRAINT population_country_code_fkey FOREIGN KEY (country_code) REFERENCES country(iso_code);


