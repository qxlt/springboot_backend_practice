CREATE TABLE t_inventory (
    id BIGSERIAL NOT NULL,
    skuNumber VARCHAR(255),
    quantity INT,
    PRIMARY KEY (id)
);