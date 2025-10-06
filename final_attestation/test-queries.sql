--1. Изменить даты заказов
-- т.к. при создании таблицы все даты проставляются автоматически и отличаются на миллисекунды, 
-- то для удобства фильтрации проставляем условно случайные числа. Заменой функции random, которая при использовании в UPDATE, 
--проставит одинаковые значения во всем столбце, выбрана сумма ID продукта и клиента
UPDATE orders 
SET date_order = TO_DATE(Concat('2025-09-',(product_id+customer_id)::varchar(255)), 'YYYY-MM-DD')


--2. Вывести все покупки продуктов питания
--два отдельных объединения таблиц, поле quantity встречается в product и в order, поэтому определяем, из какой таблицы данные
SELECT  date_order, description, quantity, price,first_name,last_name,category
FROM
    (SELECT date_order, description, orders.quantity, price,customer_id,category
    FROM orders LEFT JOIN product
    ON orders.product_id = product.id) as t1
LEFT JOIN customer
ON t1.customer_id = customer.id
WHERE t1.category LIKE 'Продукты питания%'

--3. Вывести все покупки, отсортированные по дате и цене
SELECT  date_order, description, quantity, price,first_name,last_name
FROM
    (SELECT date_order, description, orders.quantity, price,customer_id,category
    FROM orders LEFT JOIN product
    ON orders.product_id = product.id) as t1
LEFT JOIN customer
ON t1.customer_id = customer.id
ORDER by date_order,price

--4. Вывести какие продукты покупали клиенты и сколько
SELECT first_name,last_name,description,sum
FROM(
        (
            (
                SELECT customer_id, product_id, SUM (quantity)
                FROM orders
                GROUP BY customer_id,product_id
            ) as t1
            LEFT JOIN product
            ON t1.product_id = product.id
        ) as t2
        LEFT JOIN customer
        ON t2.customer_id = customer.id
    )
ORDER BY first_name

--5. Заполнить столбец "Любимые продукты" в таблице customer, исходя из количества купленных за весь период продуктов

UPDATE customer
SET favor_product = t5.product_id
FROM 
            (select ci,first_name,last_name,description,product_id,max from
                (
                    (
                    --сгруппированная таблица клиент - продукт - количество
                    select * from
                        (SELECT customer_id as ci, product_id, SUM (quantity) as sum_t1
                        FROM orders
                        GROUP BY customer_id,product_id) as t1
                    join
                    --таблица с максимальным количеством купленного продукта
                        (select customer_id,max(sum_t2) from
                            (SELECT customer_id, product_id, SUM (quantity) as sum_t2
                            FROM orders
                            GROUP BY customer_id,product_id) as t2
                        group by customer_id) as fav
                    on t1.ci = fav.customer_id and t1.sum_t1 = fav.max
                    ) as t3
                join --подтягиваем данные покупателя
                    customer
                on t3.ci = customer.id
                ) as t4
            join 
                product-- и данные о продукте
            on product_id=product.id
            order by first_name
            ) t5
where customer.id = t5.ci

--6. Удаление клиента без заказов

DELETE
FROM customer
WHERE favor_product IS NULL

--7.1 Добавить новые продукты реестр продуктов и их покупки в реестр заказов
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Масло', 200.00, 1000, 'Продукты питания','Буренка');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Творог', 110.00, 60, 'Продукты питания','Буренка');
INSERT INTO product(description, price, quantity, category, brend) VALUES ('Корм для собак', 150.00, 15, 'Корм для животных','Догплюс');
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (11, 7, 1, 1); 
INSERT INTO orders(product_id, customer_id, quantity, status_id) VALUES (12, 1, 8, 3); 

--7.2. Удаление продукта, который никто не купил
DELETE
FROM product
USING
    (SELECT product.id pi,sum(orders.quantity)
    FROM product
    LEFT JOIN orders
    ON product.id = orders.product_id
    GROUP BY product.id) t1
WHERE product.id = t1.pi AND t1.sum IS NULL

--8. Обновление количества на складе при покупке
-- в п.7 после удаления остались два новых продукта, по которым были две продажи - масло и творог
UPDATE product
SET quantity = (product.quantity - t1.quantity)
FROM 
    (SELECT *
    FROM orders
    WHERE date_order > '2025-10-01')
    as t1
where product.id = t1.product_id

--9. Список всех заказов за последние 25 дней с именем покупателя и описанием

SELECT first_name,last_name,description,date_order
FROM
    (SELECT *
    FROM
        (SELECT * 
        FROM
            (SELECT * 
            FROM orders
            WHERE date_order>(CURRENT_DATE-25)) t1
        LEFT JOIN
            customer
        ON t1.customer_id=customer.id
        ) t2
    LEFT JOIN
        product
    ON t2.product_id = product.id)

--10. Топ-3 самых популярных товара.
SELECT description,SUM (quantity)
FROM 
    (SELECT orders.quantity,product_id,description
    FROM orders
    LEFT JOIN product
    ON orders.product_id = product.id)

GROUP BY description
ORDER BY SUM DESC
LIMIT 3 

