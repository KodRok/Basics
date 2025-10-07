INSERT INTO products (name, price)
SELECT
    'Product_' || generate_series(1, 100),
    (random() * 1000 + 1)::NUMERIC(10, 2);

INSERT INTO customers (name, email)
SELECT
    'Customer_' || generate_series(1, 1000),
    'user' || generate_series(1, 1000) || '@example.com';

EXPLAIN (ANALYZE, BUFFERS)
SELECT * FROM orders WHERE created_at BETWEEN '2025-01-01' AND '2025-02-01';

CREATE INDEX idx_orders_created_at ON orders (created_at);
CREATE INDEX idx_orders_customer_id ON orders (customer_id);

VACUUM FULL ANALYZE orders;

SELECT o.order_id, c.name, p.name, o.amount
FROM orders o
         JOIN customers c ON o.customer_id = c.customer_id
         JOIN products p ON o.product_id = p.product_id
WHERE o.created_at BETWEEN '2025-01-01' AND '2025-01-31';

EXPLAIN (ANALYZE, BUFFERS)
SELECT o.order_id, c.name, p.name, o.amount
FROM orders o
         JOIN customers c ON o.customer_id = c.customer_id
         JOIN products p ON o.product_id = p.product_id
WHERE o.created_at BETWEEN '2025-01-01 00:00:00' AND '2025-01-01 23:59:59';