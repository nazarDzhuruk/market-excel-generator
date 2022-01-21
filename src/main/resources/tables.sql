CREATE TABLE IF NOT EXISTS product
(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    quantity INTEGER,
    product_distributor VARCHAR
);

CREATE TABLE IF NOT EXISTS orders
(
    id SERIAL PRIMARY KEY,
    datetime DATE,
    destination VARCHAR,
    payment_status BOOLEAN
);
CREATE TABLE IF NOT EXISTS orders_ordered_products
(
    order_id INTEGER,
    ordered_products_id INTEGER,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (ordered_products_id) REFERENCES product (id) ON DELETE CASCADE
);