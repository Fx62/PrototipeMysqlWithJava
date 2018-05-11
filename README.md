# PrototipeMysqlWithJava
/*
*  Luis Fernando Ortega Rodríguez
*       - 7690-17-360
*  Nelson Steven Cifuentes Chilin 
*       - 7690-17-2711
*  Brandon Stewart Diaz
*       - 7690-14-307
*/

Este proyecto consiste en poder manejar archivos de texto como si fuera una base de datos.

El usuario puede definir los nombres de las columnas, el tipo de dato que se utiliza en cada columna (por medio de los datos primitivos de java), y tambien ingresar registros.




# Procedimientos para ejecutar el programa
1.- El primer paso despues de clonar el repositorio es compilar el proyecto

![alt text](http://learninghtml.890m.com/Images/Compilar.png)


2.- Despues de compilado, se debe ejecutar con java

![alt text](http://learninghtml.890m.com/Images/Ejecutar.png)




# Manejo del progrma
NOTA:
A partir de aqui utilizare db en ves de archivo de texto.

Lo primero que nos mostrara el programa es un menu con las siguiente opciones.

![alt text](http://learninghtml.890m.com/Images/MenuPrincipal.png)

La opcion 1 nos permite crear n cantidad de db por medio del nombre que ingresa el usuario

![alt text](http://learninghtml.890m.com/Images/CrearDB.png)

La opcion 2 nos permite mostrar las dbs existentes

![alt text](http://learninghtml.890m.com/Images/MostrarDb.png)

La opcion 3 primero nos mostrara el listado de dbs existentes para que luego el usuario ingrese el nombre de la db que desea utilizar

![alt text](http://learninghtml.890m.com/Images/UsarDB.png)

La opcion 1 del submenu nos permite estructurar la db por medio de columnas donde el usuario ingresa el nombre que desea darle a cada columna y el tipo de datos que desea almacenar en cada columna

![alt text](http://learninghtml.890m.com/Images/Estructurar.png)

La opcion 2 del submenu muestra los nombres de las columnas y el tipo de datos que almacena cada columna

![alt text](http://learninghtml.890m.com/Images/Describir.png)

La opcion 3 del submenu permite el almacenar informacion en la db

![alt text](http://learninghtml.890m.com/Images/Insertar.png)

La opcion 4 del submenu mostrara todos registros almacenados

![alt text](http://learninghtml.890m.com/Images/Listar.png)

NOTA:
La opcion 4 tambien mostrara un numero autogenerado en el campo *CORRELATIVO* a cada registro

La opcion 5 del submenu permite la busqueda de registros por medio del numero CORRELATIVO

![alt text](http://learninghtml.890m.com/Images/Buscar.png)

La opcion 6 del submenu hace posible la modificacion de los diferentes campos de cada registro por medio de la busqueda del numero de CORRELATIVO

![alt text](http://learninghtml.890m.com/Images/Modificar.png)

La opcion 7 del submenu permite eliminar registros que ya no seran utilizados en la db

![alt text](http://learninghtml.890m.com/Images/Eliminar.png)

La opcion 8 del submenu regresa al menu principal

En la opcion 4 del menu principal se pueden borrar dbs

![alt text](http://learninghtml.890m.com/Images/EliminarDB.png)

La opcion 5 del menu principar es para salir del programa
