-- Atualizar referências estrangeiras para usar iso_code em vez de id
ALTER TABLE mortality_table
    ADD COLUMN country_code CHAR(2),
    DROP CONSTRAINT mortality_table_country_id_fkey,
    ADD CONSTRAINT mortality_table_country_code_fkey FOREIGN KEY (country_code) REFERENCES country(iso_code);

-- Atualizar referências estrangeiras para usar iso_code em vez de id
ALTER TABLE population
    ADD COLUMN country_code CHAR(2),
    DROP CONSTRAINT population_country_id_fkey,
    ADD CONSTRAINT population_country_id_fkey FOREIGN KEY (country_code) REFERENCES country(iso_code);

-- Remover a coluna id
ALTER TABLE country DROP COLUMN id;

-- Adicionar a constraint PRIMARY KEY na coluna iso_code
ALTER TABLE country ADD PRIMARY KEY (iso_code);