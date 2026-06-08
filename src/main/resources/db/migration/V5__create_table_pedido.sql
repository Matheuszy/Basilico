CREATE TABLE IF NOT EXISTS pedidos (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    status_pedido VARCHAR(30) NOT NULL,
    status_pagamento VARCHAR(30) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes (id)
);

CREATE TABLE IF NOT EXISTS pedido_refeicoes (
   pedido_id BIGINT NOT NULL,
   refeicao_id BIGINT NOT NULL,

    PRIMARY KEY (pedido_id, refeicao_id),
    CONSTRAINT fk_pedido_refeicoes_pedido
        FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE,

    CONSTRAINT fk_pedido_refeicoes_refeicao
        FOREIGN KEY (refeicao_id) REFERENCES refeicoes(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS pedido_bebidas (
   pedido_id BIGINT NOT NULL,
   bebida_id BIGINT NOT NULL,

    PRIMARY KEY (pedido_id, bebida_id),
    CONSTRAINT fk_pedido_bebidas_pedido
        FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE,

    CONSTRAINT fk_pedido_bebidas_bebida
        FOREIGN KEY (bebida_id) REFERENCES bebidas(id) ON DELETE CASCADE
);