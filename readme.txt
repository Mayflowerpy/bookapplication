Последовательность сборки проекта
1. mvn clean package
2. Затем скрипт докера ./build_and_run.ps1

Апишки
1. Swagger
http://localhost:8088/api/bookapp/swagger-ui.html

2. Получение всех книг GET
http://localhost:8080/api/bookapp/book/getAllBooks

3. Создать новую книгу POST
http://localhost:8080/api/bookapp/book/saveBook
BODY:
{
    "title": "Книга",
    "authorList": [
        {
            "name": "Автор"
        }
    ]
}

4. Получить автора по имени GET

http://localhost:8080/api/bookapp/author/getAuthor?name=Автор

5. Создать нового автора POST
http://localhost:8080/api/bookapp/author/saveAuthor
BODY:
{
    "name": "Автор",
    "books": [
        {
            "title": "Книга"
        }
    ]
}

6. Получить книги по автору GET
http://localhost:8080/api/bookapp/book/getBooksByAuthor?name=Автор


Прочее:
 1. В ветке ext/mapperDTO пытался сделать mapper и dto, для избавления от циклических зависимостей,
 но там ошибка преобразования листа в сущности, mapstruct его не видит, и решил проблему через JsonIdentityInfo.

2.метод getAuthor возвращает ошибку, если имя не уникально
  имя в сущности автор не уникальное, и если добавить несколько сущностей с едентичными именами, при запросе возвращается ошибка
Servlet.service() for servlet [dispatcherServlet] in context with path [/api/bookapp] threw exception [Request processing failed:
    org.springframework.dao.IncorrectResultSizeDataAccessException: Query did not return a unique result: 5 results were returned] with root cause
app-1       | org.hibernate.NonUniqueResultException: Query did not return a unique result: 5 results were returned