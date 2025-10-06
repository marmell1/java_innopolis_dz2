CREATE SCHEMA IF NOT EXISTS final_attestation_schema;

-- Таблица продуктов
CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    price NUMERIC(10, 2) NOT NULL CHECK(price >= 0),
    quantity INT NOT NULL CHECK(quantity >= 0),
    category VARCHAR(50) NOT NULL,
    brend VARCHAR(50)

);
COMMENT ON COLUMN product.price IS 'Цена продукта';
COMMENT ON COLUMN product.quantity IS 'Количество на складе';
COMMENT ON COLUMN product.brend IS 'Торговая марка';

-- Таблица покупателей
CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(15) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    favor_product INT REFERENCES product(id)
   
);
COMMENT ON COLUMN customer.first_name IS 'Имя клиента';
COMMENT ON COLUMN customer.last_name IS 'Фамилия клиента';
COMMENT ON COLUMN customer.phone IS 'Телефон клиента';
COMMENT ON COLUMN customer.email IS 'Почта клиента';
COMMENT ON COLUMN customer.favor_product IS 'Любимый продукт клиента';

-- Справочная таблица статусов заказов
CREATE TABLE IF NOT EXISTS order_status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);
COMMENT ON COLUMN order_status.name IS 'Название статуса заказа';

-- Основная таблица заказов
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES product(id),
    customer_id INT REFERENCES customer(id),
    date_order TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    quantity INT NOT NULL CHECK(quantity > 0),
    status_id INT REFERENCES order_status(id)
);
COMMENT ON COLUMN orders.date_order IS 'Дата размещения заказа';
COMMENT ON COLUMN orders.status_id IS 'Идентификатор текущего статуса заказа';

-- Индексация внешних ключей и полей даты заказа
CREATE INDEX idx_product_fk ON orders(product_id);
CREATE INDEX idx_customer_fk ON orders(customer_id);
CREATE INDEX idx_date_order ON orders(date_order);

-- Тестовые данные
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Хлеб', 50.00, 100, 'Продукты питания','Хлебзавод');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Сыр', 300.00, 80, 'Продукты питания','Буренка');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Молоко', 100.00, 150, 'Продукты питания (напитки)','Буренка');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Сок', 200.00, 50, 'Продукты питания (напитки)','Ромашка');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Шампунь', 400.00, 70, 'Косметика','Альфа');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Мороженье', 150.00, 200, 'Продукты питания (десерты)','Буренка');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Торт', 1500.00, 30, 'Продукты питания (десерты)','Сладкая жизнь');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Крем', 500.00, 70, 'Косметика','Космо');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Мясо', 800.00, 40, 'Продукты питания','Мясокомбинат');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Кефир', 90.00, 60, 'Продукты питания (напитки)','Буренка');


INSERT INTO customer(first_name, last_name, phone, email) VALUES ('Иван', 'Иванов', '+79991234567', 'ivan@example.com');
INSERT INTO customer(first_name, last_name, phone, email) VALUES ('Анна', 'Петрова', '+79997654321', 'anna@example.com');
INSERT INTO customer(first_name, last_name, phone, email) VALUES ('Василий', 'Федоров', '+79991235548', 'vas@example.com');
INSERT INTO customer(first_name, last_name, phone, email) VALUES ('Илья', 'Михайлов', '+79997698731', 'ilya@example.com');
INSERT INTO customer(first_name, last_name, phone, email) VALUES ('Андрей', 'Сорокин', '+79997619753', 'andr@example.com');
INSERT INTO customer(first_name, last_name, phone, email) VALUES ('Инна', 'Никулина', '+79997654863', 'inna@example.com');
INSERT INTO customer(first_name, last_name, phone, email) VALUES ('Виктория', 'Савельева', '+79991684329', 'vika@example.com');
INSERT INTO customer(first_name, last_name, phone, email) VALUES ('Лидия', 'Кузнецова', '+79997654759', 'lidiya@example.com');
INSERT INTO customer(first_name, last_name, phone, email) VALUES ('Афанасий', 'Цветков', '+79997614721', 'afan@example.com');
INSERT INTO customer(first_name, last_name, phone, email) VALUES ('Семен', 'Сидоров', '+79997616871', 'cemen@example.com');


INSERT INTO order_status(name) VALUES ('Новый'), ('Оплачен'), ('Отгружен'), ('Завершен');

INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (1, 1, 12, 1); -- Заказ №1
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (2, 2, 5, 2); -- Заказ №2
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (5, 8, 3, 3); -- Заказ №3
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (7, 3, 7, 4); -- Заказ №4
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (8, 9, 9, 4); -- Заказ №5
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (6, 10, 4, 3); -- Заказ №6
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (5, 7, 3, 4); -- Заказ №7
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (1, 9, 3, 1); -- Заказ №8
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (10, 1, 2, 3); -- Заказ №9
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (9, 2, 4, 2); -- Заказ №10
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (7, 2, 3, 2); -- Заказ №11
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (4, 4, 4, 1); -- Заказ №12
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (6, 5, 6, 3); -- Заказ №13
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (3, 3, 10, 3); -- Заказ №14
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (2, 7, 9, 1); -- Заказ №15
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (1, 9, 2, 1); -- Заказ №16
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (6, 4, 3, 4); -- Заказ №17
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (4, 4, 5, 2); -- Заказ №18