CREATE TABLE IF NOT EXISTS pedido (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    status_pedido VARCHAR(30) NOT NULL,
    status_pagamento VARCHAR(30) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);