# DormServer ![Travis (.org)](https://img.shields.io/travis/OsmiumKZ/DormServer.svg) ![GitHub repo size](https://img.shields.io/github/repo-size/OsmiumKZ/DormServer.svg) ![GitHub](https://img.shields.io/github/license/OsmiumKZ/DormServer.svg) ![Website](https://img.shields.io/website/https/dorm-keu.herokuapp.com/api/db.svg) ![GitHub last commit](https://img.shields.io/github/last-commit/OsmiumKZ/DormServer.svg)

## Описание проекта
Данный проект позволяет автоматизировать подачу заявлений. Проект делает акцент на общежития учебного заведения. Можно следить за количеством принятых заявлений, какой гендер у комнаты, сколько свободных осталось мест и не только. Вот [таблица API](https://docs.google.com/spreadsheets/d/1f5oslv4L3VMU16m3xWj5cY1P-9549iu62Jq15v2STOg/edit#gid=0).<br />
Проект использует лицензию MIT. Мы будем только рады, если вы его будете модернизировали под свои нужды.<br />

#### Как это все работает?
Имеется сайт, на котором показаны (визуально) [общежития](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/api/dorm/util/gson/Dorm.java), к ним [этажи](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/api/dorm/util/gson/Floor.java), а на этажах [комнаты](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/api/dorm/util/gson/Room.java). Комнаты показывают информацию о количестве и гендере проживающих. Пользователь может [выбрать](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/api/dorm/DormAPI.java#L64) для себя комнату, [заполнить](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/api/dorm/DormAPI.java#L281) анкету и ее отправить. Пользователь должен скачать [сгенерированный](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/api/dorm/DormAPI.java#L95) файл (песле отправки анкеты), распечатать и принести в учебное заведение.<br />
Учебное заведение имеет доступ к базам данных [отправленных заявлений](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/api/dorm/DormAPI.java#L190) и [созданных](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/api/dorm/DormAPI.java#L161) когда-то отчетов. Отчеты создаются после принятия заявления (на физическом уровне). А еще, нужно будет [получить](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/api/dorm/DormAPI.java#L47) доступ, чтобы что-то изменять или читать (на административном уровне). 

#### Зачем?
Чтобы в режиме реального времени, наблюдать за свободными местами общежития. ~~Тут должна была быть ссылка, на репозиторий сайта~~.

## Отдельное спасибо за:
+ [Spark](http://sparkjava.com) - на нем всё и работает;
+ [MySQL JDBC](https://www.mysql.com/products/connector) - не НАДА писать свой драйвер к БД;
+ [Gson](https://github.com/google/gson) - на изи паршу JSON;
+ [Heroku](https://www.heroku.com) - через него сайт и тестили;
+ [Docx4J](https://www.docx4java.org/trac/docx4j) - помогает с документами;
+ [Joda Time](https://www.joda.org/joda-time) - не думаю про изменение времени;
+ [jTDS JDBC](http://jtds.sourceforge.net) - Microsoft, учитесь;
+ [JavaMail](https://www.oracle.com/technetwork/java/javamail/index.html) - удобно работать с email.<br />
А также спасибо за [Java SE Development Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), всей теории [сетевой модели OSI](https://ru.wikipedia.org/wiki/%D0%A1%D0%B5%D1%82%D0%B5%D0%B2%D0%B0%D1%8F_%D0%BC%D0%BE%D0%B4%D0%B5%D0%BB%D1%8C_OSI) и моему ПК.

## Ссылки REST-приложения
Написано всё в кратце, но всегда можно посетить [таблицу](https://docs.google.com/spreadsheets/d/1f5oslv4L3VMU16m3xWj5cY1P-9549iu62Jq15v2STOg/edit#gid=0) данного REST-приложения.<br />
| Метод | Ссылка | Описание |
|:---:|:---:|:---:|
| GET | /api/db | Получить данные: "Общаги", "Этажи", "Названия", "Гендеры" и "Статусы отчетов" |
| GET | /api/search/request | Получить заявление по его ID |
| GET | /api/auth | Авторизироваться и получить токен |
| GET | /api/statistic | Получить общую статистику |
| GET | /api/doc/request | Сгенерировать и получить документ "Заявление" |
| GET | /api/doc/direction | Сгенерировать и получить документ "Направление" |
| GET | /api/room | Получить комнаты (этажа) |
| GET | /api/report | Получить все отчеты из БД |
| GET | /api/request | Получить все заявления из БД |
| GET | /api/search/name | Поиск названий. Подсказка. |
| DELETE | /api/request | Удалить заявление |
| PUT | /api/active/request | Сделать, как прочитанное заявление|
| PUT | /api/status | Обновляет статус в конкретном отчете |
| POST | /api/report | Создать отчет |
| POST | /api/request | Создать заявление |

## Важные файлы и директории
Это те файлы и директории, которей держат основу ОСНОВ этого проекта.
+ [Procfile](https://github.com/OsmiumKZ/DormServer/blob/master/Procfile) - файл для запуска приложения в heroku.com.
+ [docs](https://github.com/OsmiumKZ/DormServer/tree/master/docs) - директория, чтобы работать с документами в будущем.
+ [sql-install](https://github.com/OsmiumKZ/DormServer/tree/master/sql-install) - скрипты, для настройки БД.
+ [config.properties](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/resources/config.properties) - конфигурационный файл электронной почты и БД. Можно так же добовать новые конфигурации, новых или старых фреймворков.
+ [DataBase.java](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/utils/DataBase.java) - подключения к базам данных. Некий паттерн фасад.
+ [DataConfig.java](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/utils/DataConfig.java) - файл, в котором хранятся "глобал переменные". Добавляет мобильность к проекту. Например: можно легко переименновать колонку таблицы базы данных.
+ [heroku](https://github.com/OsmiumKZ/DormServer/tree/master/src/main/java/kz/dorm/heroku) - директория для Heroku.
+ [local](https://github.com/OsmiumKZ/DormServer/tree/master/src/main/java/kz/dorm/local) - директория для локального пользования.
+ [DormAPI.java](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/api/dorm/DormAPI.java) - место, где находятся все CRUD API.
+ [providers](https://github.com/OsmiumKZ/DormServer/tree/master/src/main/java/kz/dorm/api/dorm/util/statement/providers) - тут все SQL запросы.
+ [docx](https://github.com/OsmiumKZ/DormServer/tree/master/src/main/java/kz/dorm/docx) - директория в которой реализован фреймворк [docx4j](https://www.docx4java.org/trac/docx4j)
+ [ControlWrite.java](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/utils/ControlWrite.java) - файл, который проверяет всё то, что нужно проверять, чтобы записать в БД. Например проверка: email, названий и телефонного номера.

## Установка
Правильная "установка" и сборка проекта. 

#### Настройка БД MySQL или MSSQL
Первоначально необходимо настроить сервер реляционной БД, а потом идти и поднимать СЕРФАК.<br />
Зайдите в репозиторий и найдите директорию [sql-install](https://github.com/OsmiumKZ/DormServer/tree/master/sql-install). Там лежат скрипты, используйте их.<br />
Все пароли, логины можно написать вот [ТУТ](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/resources/config.properties).<br />
`db.type = mysql или mssql`. [Раз ссылка](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/java/kz/dorm/utils/EnumDBType.java) и [два ссылка](https://github.com/OsmiumKZ/DormServer/blob/master/src/main/resources/config.properties#L2).<br />
Всё? Работает? Точно? Го устанавливать дальше? 

#### [Сборка проекта в архив JAR](https://www.mkyong.com/gradle/gradle-create-a-jar-file-with-dependencies/)
За сборку проекта в основном отвечает скрипт по [этой ссылке](https://github.com/OsmiumKZ/DormServer/blob/master/build.gradle#L32). Первым делом нам необходимо отчистить пороект. Найдите вкладку **Build** и нажмиет на кнопку **Clean Project**, либо используйте **gradle** через терминал, командой `gradle clean`.<br />
Далее необходимо запустить скрипт `gradle fatJar` (Можно и через вашу IDE).<br />
Сгенерируется **JAR** архив, путь `$project/build/libs/`.<br />

#### [Запуск JAR](https://ru.wikihow.com/%D0%B7%D0%B0%D0%BF%D1%83%D1%81%D1%82%D0%B8%D1%82%D1%8C-.JAR-%D1%84%D0%B0%D0%B9%D0%BB)
Создайте новую директорию для вашего (только, что) сгенерированного приложения. Поместить в эту директорию: JAR и [docs директорию](https://github.com/OsmiumKZ/DormServer/tree/master/docs).<br />
В итоге у нас получилась вот такая иерархия:
```
┌─new-folder
├┬─docs
│├┬─patterns
││├───direction.docx
││└───request.docx
││
│└──null
│
└──dorm-keu-1.0.jar
```
Если все сходится, запускайте **JAR** через команду `java -jar dorm-keu-1.0.jar`.<br />
Зайдите в браузер и перейдите по ссылке http://localhost:4567/api/db.

#### [Запуск на стороне HEROKU.COM](https://devcenter.heroku.com/categories/java-support)
Создайте на Heroku **dashboard**, подключите **dashboard** к локальному **git'у** и замените названия [тут](https://github.com/OsmiumKZ/DormServer/blob/master/build.gradle#L4), [тут](https://github.com/OsmiumKZ/DormServer/blob/master/build.gradle#L7) и [тут](https://github.com/OsmiumKZ/DormServer/blob/master/Procfile#L1). Вот пример:
```
~build.gradle~
group 'my-name-heroku'
applicationName = "my-name-heroku"

~Procfile~
web: ./build/install/my-name-heroku/bin/my-name-heroku
```
Теперь можно заливать на Heroku:
```
git add .
git commit -m "MyREST"
git push heroku master
```
Теперь он должен работать по ссылке https://my-name-heroku.herokuapp.com.

## License
```
The MIT License (MIT)

Copyright (c) 2019 Osmium

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```