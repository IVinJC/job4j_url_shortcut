# Проект URLShortCut

Сервис работает через REST API.
Функционал.

* Регистрация сайта.
Сервисом могут пользоваться разные сайты. Каждому сайту выдается пару пароль и логин.
Чтобы зарегистрировать сайт в систему нужно отправить запрос POST http://localhost:8080/registration
с телом JSON объекта
{name : "регистрируемый хост"}

* Авторизация
POST http://localhost:8080/login.
В блоке HEAD вы получите tocken

* Регистрация URL
POST http://localhost:8080/convert
C телом JSON объекта.
{url: "ссылка URL"}
В теле ответа получите УНИКАЛЬНЫЙ_КОД

* Переадресация. Выполняется без авторизации.
Запрос:
GET http://localhost:8080/redirect/УНИКАЛЬНЫЙ_КОД
В блоке HEAD ответа получите HTTP CODE - 302 REDIRECT "зарегистрированный URL"

* Статистика.
В сервисе считается количество вызовов каждого адреса.
По сайту можно получить статистку всех адресов и количество вызовов этого адреса.
Запрос:
GET http://localhost:8080/statistic

## Развертывание и запуск приложения в Kubernetes(K8s)
* Перед началом выполнения, убедитесь, что кластер запущен - kubectl get nodes
* Если нет, то заново запустите его. Опять проверьте, что кластер запущен - minikube start
* Cохранить файл resources/k8s/postgresdb-secret.yml по пути ./k8s/postgresdb-secret.yml
* Создаем секрет kubectl apply -f postgresdb-secret.yml
* Проверяем, что secret создался kubectl get secret
* Создаем ConfigMap. Cохранить файл resources/k8s/postgresdb-configmap.yml по пути ./k8s/postgresdb-configmap.yml
* Вносим ConfigMap в кластер kubectl apply -f postgresdb-configmap.yml
* Проверяем kubectl get configmaps
* Cохранить файл resources/k8s/postgresdb-deployment.yml по пути ./k8s/postgresdb-deployment.yml
* Запускаем kubectl apply -f postgresdb-deployment.yml
* Cохранить файл resources/k8s/spring-deployment.yml по пути ./k8s/spring-deployment.yml
* Проверяем kubectl logs -l app=spring-boot
* Набираем комманду minikube service spring-boot-service

### Использование REST API curl:
Register site

`curl --location --request POST 'http://localhost:8080/register' \
--header 'Content-Type: application/json' \
--data-raw '{
"site": "wikipedia.org"
}'`

Getting token

`curl --location --request POST 'http://localhost:8080/login' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"login": "your_login",
"password": "your_password"
}'`

Convert link

`curl --location --request POST 'http://localhost:8080/convert' \
--header 'Authorization: Bearer your_token\
--header 'Content-Type: application/json' \
--data-raw '{
"url": "https://ru.wikipedia.org/wiki/Java"
}'`

Getting statistic

`curl --location --request GET 'http://localhost:8080/statistic' \
--header 'Authorization: Bearer your_token\`
