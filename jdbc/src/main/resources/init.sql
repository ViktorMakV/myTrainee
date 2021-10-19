INSERT INTO authors (name) VALUES ( 'Dean Koontz' );
INSERT INTO authors (name) VALUES ( 'Stephen King' );

INSERT INTO books (name, author_id) VALUES ( 'Odd Thomas', 1 );
INSERT INTO books (name, author_id) VALUES ( 'The Dark Tower: The Gunslinger', 2 );
INSERT INTO books (name, author_id) VALUES ( 'The Dark Tower II: The Drawing of the Three', 2 );

INSERT INTO storage (book_id, quantity, price) VALUES ( 1, 7, 22.0 );
INSERT INTO storage (book_id, quantity, price) VALUES ( 2, 3, 20.0 );
INSERT INTO storage (book_id, quantity, price) VALUES ( 3, 4, 25.0 );