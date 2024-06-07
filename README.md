# CRUD Application for Books with Redis Caching

This is a CRUD application for managing books using Spring MVC, Spring Data JPA, and Spring Boot Redis. The application supports caching using Redis and allows you to create, read, update, and delete books. Each book belongs to a category, and categories are created only with books. If a category already exists, it is reused.

## Features

- Find a book by its title and author
- Find a list of books by the category name
- Create a book with a category
- Update a book's information
- Delete a book by ID
- Cache book searches using Redis
- Cache invalidation on book creation, update, and deletion

## Technologies Used

- Spring Boot
- Spring MVC
- Spring Data JPA
- Redis
- H2 Database (or any other database of your choice)

## Prerequisites

- JDK 11 or higher
- Maven 3.6.0 or higher
- Redis Server
- (Optional) Docker

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/your-username/book-crud-redis.git
cd book-crud-redis
