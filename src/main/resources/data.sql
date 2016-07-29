INSERT INTO Product (id, catalog, description, name) VALUES(1, 'Store', 'Pants realy beatyful', 'Blue Pants');

INSERT INTO Product (id, catalog, description, name) VALUES(2, 'Store', 'Red Pants Realy beatyful', 'Red Pants');

INSERT INTO Sku (id,description, name, product_id, price, stock) VALUES(1, 'Blue Pants Size Medium', 'Pants M', 1, 12.00, 10);

INSERT INTO Sku (id,description, name, product_id, price, stock) VALUES(2, 'Blue Pants Size Large', 'Pants L', 1, 12.0, 10);

INSERT INTO Sku (id,description, name, product_id, price, stock) VALUES(3, 'Blue Pants Size Extra Large', 'Pants XL', 1, 200.0, 10);

INSERT INTO Sku (id,description, name, product_id, price, stock) VALUES(4, 'Red Pants Size Extra Large', 'Pants XL', 2, 200.00, 10);

INSERT INTO Sku (id,description, name, product_id, price, stock) VALUES(5, 'Red Pants Size Large', 'Pants L', 2, 10.00, 10);


INSERT INTO Ordercom (id, status, paymentStatus, totalAmount, creationDate, lastModifiedDate) VALUES(1, 'SUBMITTED', 'CREATED', 24.0, null, null);

INSERT INTO CommerceItem (id, sku_id, quantity, order_id, unitValue) VALUES(1, 1, 12, 1, 12);

INSERT INTO CommerceItem (id, sku_id, quantity, order_id, unitValue) VALUES(2, 1, 12, 1, 12);

INSERT INTO CommerceItem (id, sku_id, quantity, order_id, unitValue) VALUES(3, 2, 2, 1, 12);


INSERT INTO Ordercom (id,status, paymentStatus, totalAmount, creationDate, lastModifiedDate) VALUES(2, 'SUBMITTED', 'CREATED', 34, null, null);

INSERT INTO CommerceItem (id, sku_id, quantity, order_id, unitValue) VALUES(4, 3, 1, 2, 12);


INSERT INTO Ordercom (id,status, paymentStatus, totalAmount, creationDate, lastModifiedDate) VALUES(3, 'SUBMITTED', 'APPROVED', 34, null, null);

INSERT INTO CommerceItem (id, sku_id, quantity, order_id, unitValue) VALUES(5, 3, 2, 3, 144);
