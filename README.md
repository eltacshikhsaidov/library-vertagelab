# library-vertagelab
Testing project for interview

# Endpoints for user entity

- GET /users/all return all users
- POST /users/all adding new user
- DELETE /users/user/id delete user
- PUT /users/user/id updating user
- GET /users/user/id get specific user 

# Endpoints for book entity

- GET /books/all return all books
- POST /books/all adding new book
- DELETE /books/book/id delete book
- PUT /books/book/id updating book
- GET /books/book/id get specific book 

#

<img align="center" alt = "allUsers" width="auto" src="https://github.com/eltacshikhsaidov/library-vertagelab/blob/main/getUsers.png?raw=true">

<img align="center" alt = "allUsers" width="auto" src="https://github.com/eltacshikhsaidov/library-vertagelab/blob/main/getBooks.png?raw=true">

I creaetd isFree in Book entity for checking if book is taken or not. If it is taken we assign it false, otherwise true.
Becaue for one user could have multiple books, I applied @OneToMany for user and @ManyToOne for book.

Thanks
