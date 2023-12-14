-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

-- Insert records into the Ebooks table
    INSERT INTO ebooks (title, authors, publisher, image_name, description, genre, selling_price, purchase_cost, is_available)
VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', 'great-gatsby.jpg', 'A classic novel about the American Dream.', 'FICTION', 12.99, 8.00, 1);

INSERT INTO ebooks (title, authors, publisher, image_name, description, genre, selling_price, purchase_cost, is_available)
VALUES ('To Kill a Mockingbird', 'Harper Lee', 'J. B. Lippincott & Co.', 'to-kill-a-mockingbird.jpg', 'A story of racial injustice and moral growth.', 'FICTION', 11.95, 7.50, 1);

INSERT INTO ebooks (title, authors, publisher, image_name, description, genre, selling_price, purchase_cost, is_available)
VALUES ('1984', 'George Orwell', 'Secker & Warburg', '1984.jpg', 'A dystopian novel about totalitarianism.', 'SCIENCE_FICTION', 9.99, 6.00, 1);

INSERT INTO ebooks (title, authors, publisher, image_name, description, genre, selling_price, purchase_cost, is_available)
VALUES ('Pride and Prejudice', 'Jane Austen', 'T. Egerton, Whitehall', 'pride-prejudice.jpg', 'A classic romance novel.', 'ROMANCE', 10.50, 6.75, 1);

INSERT INTO ebooks (title, authors, publisher, image_name, description, genre, selling_price, purchase_cost, is_available)
VALUES ('The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', 'catcher-rye.jpg', 'A coming-of-age novel.', 'FICTION', 13.75, 9.00, 1);

INSERT INTO ebooks (title, authors, publisher, image_name, description, genre, selling_price, purchase_cost, is_available)
VALUES ('The Hobbit', 'J.R.R. Tolkien', 'George Allen & Unwin', 'hobbit.jpg', 'A fantasy adventure novel.', 'FANTASY', 14.99, 9.50, 1);

INSERT INTO ebooks (title, authors, publisher, image_name, description, genre, selling_price, purchase_cost, is_available)
VALUES ('The Da Vinci Code', 'Dan Brown', 'Doubleday', 'da-vinci-code.jpg', 'A mystery thriller.', 'MYSTERY', 12.25, 8.25, 1);

INSERT INTO ebooks (title, authors, publisher, image_name, description, genre, selling_price, purchase_cost, is_available)
VALUES ('The Lord of the Rings', 'J.R.R. Tolkien', 'George Allen & Unwin', 'lord-of-the-rings.jpg', 'An epic fantasy trilogy.', 'FANTASY', 29.99, 20.00, 1);

INSERT INTO ebooks (title, authors, publisher, image_name, description, genre, selling_price, purchase_cost, is_available)
VALUES ('Harry Potter and the Sorcerer''s Stone', 'J.K. Rowling', 'Bloomsbury', 'harry-potter.jpg', 'The first book in the Harry Potter series.', 'FANTASY', 9.95, 6.25, 1);

INSERT INTO ebooks (title, authors, publisher, image_name, description, genre, selling_price, purchase_cost, is_available)
VALUES ('The Alchemist', 'Paulo Coelho', 'HarperCollins', 'alchemist.jpg', 'A philosophical novel about a shepherd''s journey.', 'FICTION', 11.50, 7.75, 1);

-- Insert records into the Users table
INSERT INTO users (email, password, name, role )
VALUES
    ('user@user', '$2a$12$2hPFiW10UtWZlYPuQ.vAm.94ZsuA8wUDTbVyODuMtt1nIXzyXCqbq',  'Regular user', 'USER');
INSERT INTO users (email, password, name, role )
VALUES
    ('admin@admin', '$2a$12$.MF.VaMgCKTEASGtfHfrmO3w3hFcOvZ/3VlJuTHrYgJh17vuh1iWe',  'Administrator', 'ADMIN');

-- -- Insert an order with a specific user, total cost, and order time
-- -- Then Insert join table
-- INSERT INTO orders (user_id, total_cost, order_time)
-- VALUES (1, 100.00, '2023-10-17 15:45:00');
--
-- INSERT INTO orders_ebooks (order_id, ebooks_id)
-- VALUES (1, 1), (1, 2);
--
-- INSERT INTO orders (user_id, total_cost, order_time)
-- VALUES (2, 200.00, NOW());
--
-- INSERT INTO orders_ebooks (order_id, ebooks_id)
-- VALUES (2, 3);
--
-- INSERT INTO orders (user_id, total_cost, order_time)
-- VALUES (3, 20.00, NOW());
--
-- INSERT INTO orders_ebooks (order_id, ebooks_id)
-- VALUES (3, 5);
--
-- INSERT INTO orders (user_id, total_cost, order_time)
-- VALUES (4, 500.00, NOW());
--
-- INSERT INTO orders_ebooks (order_id, ebooks_id)
-- VALUES (4, 3), (4, 4), (4, 5), (4, 6);
--
