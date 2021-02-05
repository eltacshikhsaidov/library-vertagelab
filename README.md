# library-vertagelab
Testing project for interview

# Endpoints for user entity

- GET <code style="background-color:green">/users/all</code> return all users
- POST <code>/users/all</code> adding new user
- DELETE <code>/users/user/id</code> delete user
- PUT <code>/users/user/id</code> updating user
- GET <code>/users/user/id</code> get specific user 
- PUT <code>/users/user/take/{user_id}?book_id={book_id}</code> taking book by specific user

# Endpoints for book entity

- GET <code>/books/all</code> return all books
- POST <code>/books/all</code> adding new book
- DELETE <code>/books/book/id</code> delete book
- PUT <code>/books/book/id</code> updating book
- GET <code>/books/book/id</code> get specific book 
- PUT <code>/books/book/return/{book_id}</code> returning book free for use

#

<img align="center" alt = "allUsers" width="auto" src="https://github.com/eltacshikhsaidov/library-vertagelab/blob/main/getUsers.png?raw=true">

<img align="center" alt = "allUsers" width="auto" src="https://github.com/eltacshikhsaidov/library-vertagelab/blob/main/getBooks.png?raw=true">

As you can see if we don't add user for specific book then it automatically becomes <code>"user":null, "isFree":true</code>

<img align="center" alt = "allUsers" width="auto" src="https://github.com/eltacshikhsaidov/library-vertagelab/blob/main/returnBook.png?raw=true">

<img align="center" alt = "allUsers" width="auto" src="https://github.com/eltacshikhsaidov/library-vertagelab/blob/main/takeBook.png?raw=true">

I creaetd isFree in Book entity for checking if book is taken or not. If it is taken we assign it false, otherwise true.
Becaue for one user could have multiple books, I applied @OneToMany for user and @ManyToOne for book.

Thanks
