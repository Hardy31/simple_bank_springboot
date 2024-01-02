# Используйте официальную среду выполнения OpenJDK 18 в качестве родительского образа

FROM adoptopenjdk/openjdk11:latest

# Установите рабочий каталог /app
WORKDIR /app

# Скопируйте содержимое текущего каталога в контейнер /app
COPY . /app

# Открыть порт 8080 для приложения
EXPOSE 8080

# Установите переменные среды для подключения MySQL
#ENV MYSQL_HOST=db \
#    MYSQL_PORT=3306 \
#    MYSQL_DATABASE=bank \
#    MYSQL_USER=bestuser \
#    MYSQL_PASSWORD=bestuser

# Запускаем контейнер MySQL
# RUN docker run -d --name db -e MYSQL_ROOT_PASSWORD=$MYSQL_PASSWORD mysql

# Установите переменные среды для подключения PostgreySQL

# Запускаем контейнер MySQL


# Запускаем приложение
CMD ["java", "-jar", "simple-bank-springboot.jar"]