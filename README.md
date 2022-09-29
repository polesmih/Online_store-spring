# Online store (jsvs Spring)

Интернет-магазин разработан на языке Java с использованием библиотек Spring, основано на микросервисной архитектуре.
В приложении реализованы функции авторизации при помощи JWT Token-ов, корзина хранит заказ используя No-SQL Redis.
Заказы формируются с использованием Apache Kafka. 
Фронт реализован на основе JS Angular. 
Реализована довзможность получения оплаты заказов при помощи QIWI.

В проекте использованы:
Apache Kafka
Redis
Spring Boot
Spring MVC
Spring Data JPA
Spring Security
WildFLy
HTML & CSS & JS Angular
Docker
QIWI-кошелек

Запуск приложения:
Через запуск Dockerа запустить Redis и Kafka.
Для онлайн-оплаты указать свой secret-key в микросервисе order-service/src/main/resources/secrets.properties
Запустить все микросервисы.
В браузере пройти по ссылке http://localhost:3000/front
