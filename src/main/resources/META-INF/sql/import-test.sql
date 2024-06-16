INSERT INTO customer(id, name, email)
VALUES ('afff3cb4-c290-42c5-84f4-56ab644dcd1a', 'First User', 'first_user@email.com');

INSERT INTO customer(id, name, email)
VALUES ('64b0834e-f350-4976-8a98-48ec117d72b1', 'Second User', 'second_user@email.com');


INSERT INTO customer_order(id, address, customerid, items, status, price, paymentmethod)
VALUES ('3d9d6c17-39a0-4487-9b7c-c24ea6626271', 'Street 28', 'afff3cb4-c290-42c5-84f4-56ab644dcd1a', array [
    'that',
    'this',
    'those'
  ], 'pending', 2.50, 'cash');