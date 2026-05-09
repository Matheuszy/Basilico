CREATE TABLE IF NOT EXISTS usuario (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username VARCHAR(30) UNIQUE,
    email VARCHAR(50) UNIQUE,
    password VARCHAR(150),
    role VARCHAR(20) NOT NULL

);