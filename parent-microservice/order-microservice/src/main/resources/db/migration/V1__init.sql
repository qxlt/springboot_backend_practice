CREATE TABLE t_orders (
    order_id BIGSERIAL NOT NULL,
    order_number VARCHAR(255) DEFAULT NULL,
    sku_number VARCHAR(255),
    price DECIMAL(19,2),
    quantity INT,
    PRIMARY KEY (order_id)
);