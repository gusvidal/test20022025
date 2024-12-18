Para probar el desarrollo, es necesario seguir los siguientes pasos:
1.- Crear un usuario en el sistema, para esto, se debe ingresar al siguiente endpoint:
[POST] http://localhost:8082/api/auth/v1/register
Con el siguiente json
{
	"username": "gvidal",
	"password": "GVidal980#123456",
	"email": "gvidal@gmail.com",
	"phones": [
		{
			"number": "2211574",
			"citycode": "65",
			"countrycode": "56"
		},
		{
			"number": "42874817",
			"citycode": "9",
			"countrycode": "56"
		}
	]
}

Esto creará un usuario del tipo ADMIN

2.- Loguear al usuario recién creado, por ejemplo el usuario "gvidal" creado anteriormente
El endpoint es:  [POST]  http://localhost:8082/api/auth/v1/login
el json a utilizar es:
{
	"username": "gvidal",
	"password": "GVidal980#123456"
}
Esto devuelve un json de la siguiente manera:
{
	"id": 1,
	"created": "2024-12-17T22:29:49.807477148",
	"modified": null,
	"lastLogin": "2024-12-17T22:29:53.322418567",
	"accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndmlkYWwiLCJpYXQiOjE3MzQ0ODUzOTMsImV4cCI6MTczNDQ4NTY5M30.mveoBuoiK76e0oh-ezjKgz5DcjjydaTG6atQ8zN6BGleuoMsIO9GH2w3y-_IOk4h5y9kFLEfMjjart51YYWaaA",
	"a

3.- Finalmente, para probar la autorizacion de JWT, ingresamos al siguiente endpoint
[GET]: http://localhost:8082/api/auth/v1/lista
y en la pestaña de authorización pegamos el token devuelto como "accessToken" del punto anteriormente
Para usuario del tipo ADMIN se despliega una lista como todos los usuarios presentes en el sistema, 
de la siguiente forma:
[
	{
		"idUsuario": 1,
		"username": "gvidal",
		"password": "$2a$10$ArAFntDiX.O3EcC1CGMB5.M.lm9z.rHX.fJUslYsKPS6BtF1JDJaW",
		"email": "gvidal@gmail.com",
		"created": "2024/11/11 16:28:41",
		"modified": null,
		"lastLogin": "2024/11/11 16:28:50",
		"token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJndmlkYWwiLCJpYXQiOjE3MzEzNTMzMzAsImV4cCI6MTczMTM1MzYzMH0.Aitt5EO6TZIo-ud3PL_N6mUxIVzUxjKKQ_FDkSgR7BZNK82HuPkTFCI1sbhp3NnF_CSnsAS_IAPLt4f9tTsSEw",
		"isactive": true,
		"roles": [
			{
				"idRole": 1,
				"name": "ADMIN"
			}
		],
		"phones": [
			{
				"idPhone": 1,
				"number": 2211574,
				"citycode": 65,
				"countrycode": 56
			},
			{
				"idPhone": 2,
				"number": 42874817,
				"citycode": 9,
				"countrycode": 56
			}
		]
	}
]

Se adjunta una colección en formato json para probar directamente en
insomnia o postman.

El acceso a la base de datos h2 es mediante la url:
http://localhost:8082/h2-console
usuario: sa
sin password

Para acceder a la documentación de swagger, es preciso ingresar a la siguiente url
http://localhost:8082/doc/swagger-ui/index.html
