# ExamenJava

Microservicio Para la Administracion de Usuarios. El funcionamiento es el siguiente:

/sign-up: endpoint en el cual se crea un usuario, el cual solo acepta un json de entrada. EJ:

{
"name": String,
"email": String,
"password": String,
"phones": 
{
"number": long,
"citycode": int,
"contrycode": String
}
}

puntos a tener en cuenta:
-El correo debe seguir una expresión regular (aaaaaaa@undominio.algo)
-La clave debe seguir una expresión regular("a2asfGfdfdf4").Debe tener solo una Mayúscula y solamente dos números, en combinación de letras minúsculas, largo máximo de 12 y mínimo 8.
-El nombre y los teléfonos son campos opcionales.


/login: endpoint el cual es para consultar el usuario, Se utiliza el token generado en el endpoint anterior (considere que el token Cambia al ejecutar el endpoint y se devuelve un token Actualizado), Tambien solo acepta un json de entrada. EJ:

{
   "token":"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjYwNTgxNjQzLCJzdWIiOiJsbW95c2FzYWRhc2Rkc2FkMWdAZ21haWwuY29tIiwiaXNzIjoibWFpbiIsImV4cCI6MTY2MDU5NjA0M30.F-70XcLyNSH7xW3K8I2SONacWSdu3ViDd8RgHNUCPyY"
}

luego si se encuentra un usuario compatible con ese token devuelve todos los datos del usuario. EJ:

{
"id": "e5c6cf84-8860-4c00-91cd-22d3be28904e",
"created": "Nov 16, 2021 12:51:43 PM",
"lastLogin": "Nov 16, 2021 12:51:43 PM",
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpb0B0ZXN0...",
"isActive": true,
"name": "Julio Gonzalez",
"email": "julio@testssw.cl",
"password": "a2asfGfdfdf4",
"phones":
{
"number": 87650009,
"citycode": 7,
"contrycode": "25"
}
}

Ejemplos de Prueba en Postman:

![image](https://user-images.githubusercontent.com/42626179/184780003-44f11b20-077c-4939-b3a2-11220b7b7ec2.png)

![image](https://user-images.githubusercontent.com/42626179/184780134-465d6106-597f-4293-ac23-0007f88c52b7.png)







