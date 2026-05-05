CREATE TABLE orders (
    orde_id BIGSERIAL PRIMARY KEY,
    rest_id BIGINT NOT NULL,
    cust_id BIGINT NOT NULL,
    empl_id BIGINT NULL,
    orde_pin INT NULL,
    orde_status VARCHAR(50) NOT NULL
);

CREATE TABLE order_dish (
    ordi_id BIGSERIAL PRIMARY KEY,
    dish_id BIGINT NOT NULL,
    ordi_quantity INT NOT NULL,
    order_id BIGINT NOT NULL,
    CONSTRAINT fk_orderdish_order FOREIGN KEY (order_id)
    REFERENCES orders(orde_id) ON DELETE CASCADE
);