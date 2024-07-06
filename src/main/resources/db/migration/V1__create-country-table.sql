CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE country (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    iso_code CHAR(2) UNIQUE
);