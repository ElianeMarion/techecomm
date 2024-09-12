# TECHECOMM


## Arquitetura resumida
![](ecommerce_arq_resum.png)
##
##
![](techecomm_final.png)



##
##
### Iniciar projetos e bancos com docker
````
docker-compose -f compose-apps.yaml up --build
````

### Finalizar projetos e bancos com docker
````
docker-compose -f compose-apps.yaml down -v
````

##
##
## Exemplos de requisições

##
### Microsserviço usermanager

#### register
````
curl --request POST \
  --url http://localhost:8012/auth/register \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390 \
  --data '{
	"login": "joao.silva",
	"name": "Joao da Silva",
	"email": "joao.silva@host.com",
	"password": "abcde"
}'
````

#### login
````
curl --request POST \
  --url http://localhost:8012/auth/login \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390 \
  --data '{
	"login": "joao.silva",
	"password": "abcde"
}'
````

#### login-adm
````
curl --request POST \
  --url http://localhost:8012/auth/login \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390 \
  --data '{
	"login": "joao.silva",
	"password": "abcde"
}'
````

#### get-roles
````
curl --request GET \
  --url http://localhost:8012/auth/roles \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW8uc2lsdmEiLCJyb2xlcyI6IlJPTEVfQ1VTVE9NRVIiLCJleHAiOjE3MjU3OTM3NjN9.5rIFAQ_Z4kGs5uLu8mdoFPGBwJ1MOfOJgYX6vMB6GsE' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390
````

#### get-user
````
curl --request GET \
  --url http://localhost:8012/user/1 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW8uc2lsdmEiLCJyb2xlcyI6IlJPTEVfQ1VTVE9NRVIiLCJleHAiOjE3MjU3OTc5NTh9.IkmXV3bLeRD3Wauyag1_4o-fELXSuBx9lmCClOjrLLk' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390
````

#### delete-user
````
curl --request DELETE \
  --url http://localhost:8012/user/1 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW8uc2lsdmEiLCJyb2xlcyI6IlJPTEVfQ1VTVE9NRVIiLCJleHAiOjE3MjU3OTc5NTh9.IkmXV3bLeRD3Wauyag1_4o-fELXSuBx9lmCClOjrLLk' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390
````

#### update-user
````
curl --request PUT \
  --url http://localhost:8012/user/1 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW8uc2lsdmEiLCJyb2xlcyI6IlJPTEVfQ1VTVE9NRVIiLCJleHAiOjE3MjU4MDY2ODJ9.fGor4NtOr2BBECPPLy9xkToriYo05860Q3DkmBj6big' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390 \
  --data '{
	"name": "Joao da Silva II",
	"email": "joao.silvaii@host.com"
}'      
````

#### save-adm
````
curl --request POST \
  --url http://localhost:8012/adm \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImFkbWluIiwicm9sZXMiOiJST0xFX0FETUlOIiwiZXhwIjoxNzI1ODAzMjQ5fQ.QuvfH-uYawUQhWZRgTu9495QjK0O03E6n_HxnsQgt18' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390 \
  --data '{
	"login": "joao.silva",
	"name": "Joao da Silva",
	"email": "joao.silva@host.com",
	"password": "abcde"
}'
````

#### get-adm
````
curl --request GET \
  --url http://localhost:8012/adm/2 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW8uc2lsdmEiLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJleHAiOjE3MjU4MDE5MDJ9.NBdieUG2_E3Ib_gSuiWt9FKBxjFEFWSStyZN3QcS3ZM' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390
````

#### update-adm
````
curl --request PUT \
  --url http://localhost:8012/adm/2 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW8uc2lsdmEiLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJleHAiOjE3MjU4MDE5MDJ9.NBdieUG2_E3Ib_gSuiWt9FKBxjFEFWSStyZN3QcS3ZM' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390 \
  --data '{
	"name": "Joao da Silva II",
	"email": "joao.silvaii@host.com"
}'
````

#### delete-adm
````
curl --request DELETE \
  --url http://localhost:8012/user/2 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6ImpvYW8uc2lsdmEiLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJleHAiOjE3MjU4MDE5MDJ9.NBdieUG2_E3Ib_gSuiWt9FKBxjFEFWSStyZN3QcS3ZM' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/9.3.3' \
  --cookie JSESSIONID=10409C99B6D5569304C93B3D8475C390   
````

##
### Microsserviço products

#### adicionar produto
````
curl --location 'http://localhost:8082/auth/products' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInVzZXJJZCI6OTk5OTk5OSwic3ViIjoiYWRtaW4iLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJleHAiOjE3Mjg1NDI2NDd9.dhDDotPgrNcaNH5fsUxvktVJDtZw1afobvv1zagvA0g' \
--data '{
    "name": "Doril",
    "description": "Doril cartela com 4 comprimidos",
    "price": 19.99,
    "quantityStock": 100,
    "updateDate": "2024-09-09T11:20:21",
    "status": "ATIVO"
}'
````

#### atualizar produto
````
curl --location --request PUT 'http://localhost:8082/auth/products' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInVzZXJJZCI6OTk5OTk5OSwic3ViIjoiYWRtaW4iLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJleHAiOjE3Mjg1NDI2NDd9.dhDDotPgrNcaNH5fsUxvktVJDtZw1afobvv1zagvA0g' \
--data '{
    "productId": 1,
    "name": "Paracetamol",
    "description": "Caixa com 20 comprimidos",
    "price": 8.99,
    "quantityStock": 90,
    "updateDate": "2024-09-09T12:20:21",
    "status": "ATIVO"
}'
````

#### atualizar preço de produto
````
curl --location --request PATCH 'http://localhost:8082/auth/products/update/price' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInVzZXJJZCI6OTk5OTk5OSwic3ViIjoiYWRtaW4iLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJleHAiOjE3Mjg1NDI2NDd9.dhDDotPgrNcaNH5fsUxvktVJDtZw1afobvv1zagvA0g' \
--data '{
    "productId": 1,
    "price": 12.99
}'
````

#### Consultar produto por id
````
curl --location 'http://localhost:8082/products/1'
````

#### Consultar todos os produtos
````
curl --location 'http://localhost:8082/products'
````

#### Consultar produto por id
````
curl --location 'http://localhost:8082/products/1'
````

#### Reserva de quantidade de produto
````
curl --location 'http://localhost:8082/products/reserve' \
--header 'Content-Type: application/json' \
--data '{
    "productId": 1,
    "quantityRequired": 2
}'
````

##
### Microsserviço shopping-cart

#### Inserir produto no carrinho do usuário
````
curl --location 'http://localhost:8083/shopping-cart/9999999' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInVzZXJJZCI6OTk5OTk5OSwic3ViIjoiYWRtaW4iLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJleHAiOjE3Mjg1NDI2NDd9.dhDDotPgrNcaNH5fsUxvktVJDtZw1afobvv1zagvA0g' \
--data '{
    "productIdCart": 1,
    "sizeProductCart":1,
    "colorProductCar": "blue",
    "unitPriceProductCart": 10.0
}'
````

#### Buscar carrinho do usuário
````
curl --location 'http://localhost:8083/shopping-cart/9999999' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInVzZXJJZCI6OTk5OTk5OSwic3ViIjoiYWRtaW4iLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJleHAiOjE3Mjg1NDI2NDd9.dhDDotPgrNcaNH5fsUxvktVJDtZw1afobvv1zagvA0g'
````

#### Solicitar pagamento do carrinho
````
curl --location --request POST 'http://localhost:8083/shopping-cart/checkout/9999999' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInVzZXJJZCI6OTk5OTk5OSwic3ViIjoiYWRtaW4iLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJleHAiOjE3Mjg1NDI2NDd9.dhDDotPgrNcaNH5fsUxvktVJDtZw1afobvv1zagvA0g'
````



##
### Microsserviço paymentsAPI

#### Efetuar pagamento
````
curl --location 'http://localhost:8084/pagamentos' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInVzZXJJZCI6OTk5OTk5OSwic3ViIjoiYWRtaW4iLCJyb2xlcyI6IlJPTEVfQURNSU4iLCJleHAiOjE3Mjg1NDI2NDd9.dhDDotPgrNcaNH5fsUxvktVJDtZw1afobvv1zagvA0g' \
--data '{
    "orderId": 1,
    "quantity": 10,
    "payerName": "nome",
    "price": 150.99,
    "userId": 9999999
}'
````