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
    "title": "Название книги",
    "authorList": [
        {
            "name": "Автор книги"
        }
    ]
}

4. Получить автора по имени GET

http://localhost:8080/api/bookapp/author/getAuthor?name=имя

5. Создать нового автора POST
http://localhost:8080/api/bookapp/author/saveAuthor
BODY:
{
    "name": "Автор",
    "books": [
        {
            "title": "Книга автора"
        }
    ]
}

6. Получить книги по автору GET
 http:/localhost:8080//api/bookapp/author/book/getBooksByAuthor?name=имя



Ошибки:
 1. АПИ getBooksByAuthor работает некорректно.

 2. В ветке ext/mapperDTO пыталя сделать mapper и dto, для избавления от циклических зависимостей,
 но там ошибка преобразования листа в сущности, mapstruct его не видит, и решил через JsonIdentityInfo.


