# Explicación de uso
 
Para poder compilar y ejecutar este proyecto hace falta tener ciertas consideraciones. Necesitamos tener JDK 11 o superior, la versión 6.8.3 de Gradle o superior y nuestro IDE de preferencia. Si tenemos todo lo antes mencionado, podemos entrar a la aplicación de nuestro IDE, abrir el proyecto y ejecutarlo sin problemas. En cuanto se haya inicializado por completo ya podremos porbar sus funcionalidades.

Como este es un proyecto exclusivamente de desarrollo backend, necesitaremos una aplicación que nos permita realizar pruebas API a través los métodos de petición HTTP. Para esto podemos usar la aplicación de Postman o la extensión de VSCode, Thunder Client (ambas son útiles para el uso que les daremos en este caso).

Al ser una API CRUD podremos crear libros, ver todos los libros guardados, actualizar atributos de los libros y borrarlos de ser necesario. Hay que tener en cuenta que el proyecto usa la base de datos H2, que es una base en memoria y no deja los datos persistidos cuando se detiene la ejecución del proyecto, pero para este caso de prueba nos es útil.

En el primer caso vamos a utilizar en nuestra aplicación de prueba el método GET en la ruta http://localhost:8080/api/books. En el caso de simplemente enviar la petición, sin la intención de buscar un libro en particular por su título o autor, obtendremos una página con 2 libros ordenados ascendentemente por nombre de autor. Para poder pasar de página y ver la siguiente, lo que tendremos que hacer es ir a la opción parámetros, en el campo 'KEY' escribir 'page'(sin comillas) y en el campo 'VALUE' escribir el número de página que deseemos ver, en este caso la primera página está numerada como 0, es la página que vemos por defecto, en caso de querer ver la siguiente escribiremos 1 y así sucesivamente.
También tenemos la opción de cambiar la cantidad de libros que vemos por página, la cantidad por defecto de libros que podemos ver se encuentra en 2, en caso de querer aumentar o disminuir este número, en el campo 'KEY' escibiremos 'size' y en el campo 'VALUE' el número de libros que deseemos ver por página.
Otras utilidades que tiene el método GET es el de filtrar por título y autor. En el caso de querer buscar los libros por un título en particular, en el campo 'KEY' escribiremos 'titulo'(sin comillas) y en el campo 'VALUE' escribiremos el nombre del libro que deseemos buscar.
En el caso de querer buscar libros por autor, en el campo 'KEY' escribiremos 'autor'(sin comillas) y en el campo 'VALUE' escribiremos el nombre del autor que deseemos buscar.
Una salvedad importante es que todos estos parámetros funcionan en conjunto, por lo tanto podemos elegir el número de página que deseemos ver, junto al autor que estemos buscando o buscar un libro por título y autor simultaneamente, todas las combinaciones son posibles.

Nuestro segundo caso es para poder guardar un nuevo libro en nuestra base de datos. En esta ocación vamos a utilizar el método POST con la misma ruta utilizada anteriormente, http://localhost:8080/api/books, pero es un poco más complejo porque tendremos que enviar información para poder guardar el nuevo libro. En el caso de que estemos usando Postman, tendremos que aclarar en los encabezados, la opción 'Headers', que tipo de contenido enviaremos, en este caso vamos a querer enviar nuestra información en formato JSON, por lo tanto en el campo 'KEY' escribiremos 'Content-Type'(sin comillas) y en el campo 'VALUE' escribiremos 'application/json'(sin comillas).
Ahora que tenemos configurado el formato en el que enviaremos la información, vamos a completar con la información necesaria del libro.
En este caso vamos a necesitar 4 piezas de información: título, autor, fecha de lanzamiento y precio; pero lo tendremos que escribir de una forma particular. Como estamos enviando información para guardar un objeto, tendremos que enviar la información en forma de objeto, esto se hace de la siguiente manera:

{
   "titulo": "Ensayo sobre la ceguera",
    "autor": "José Saramago",
    "fechaLanzamiento": "05-07-1995",
    "precio": 2000.0
}

Para enviar esta información iremos a la opción 'Body', que se encuentra junto a 'Headers', y cargaremos la información del mismo modo que en ejemplo presentado, pero con los valores que deseemos guardar. Si hicimos la petición de manera correcta, el libro se guardará y al utilizar el método GET se encontrará en nuestra lista de libros la nueva incorporación.

El siguiente caso de prueba es el método PUT, que utilizaremos para modificar los atributos de un objeto ya guardado en nuestra base de datos. A través de a misma ruta utilizada anteriormente, http://localhost:8080/api/books. El proceso es similar al que realizamos con el método POST, tenemos que configurar el tipo de contenido que enviaremos en los encabezados de la misma manera y con el mismo tipo, y enviaremos la información de la misma forma, pero esta vez le vamos a agregar un atributo a nuestro objeto:

{
    "id": 4,
    "titulo": "Ensayo sobre la ceguera",
    "autor": "José Saramago",
    "fechaLanzamiento": "05-07-1995",
    "precio": 2500.0
}

Para el caso de este método PUT vamos a tener que agregarle el 'id'(numero de identificación que posee el objeto en la base de datos), para que pueda buscarlo y modificarlo; se pueden corroborar los distintos 'id' que poseen los libros a través del método GET presentado al principio, de esta forma podremos seleccionar que libro queremos modificar.
Cabe aclarar, como se ve en el ejemplo, que hay que enviar todos los atributos nuevamente, pero solo cambiar los que deseemos. En este ejemplo práctico solo cambió el precio de '2000' a '2500' y el resto de los atributos mantuvieron su valor.

Otro método que nos permite hacer cambio en los libros es el PATCH, pero este solo es capaz de hacer cambios en un libro un atributo a la vez, por lo tanto si quiero cambiar más de un atributo de una vez la mejor opción es el método PUT.
Para utilizar el método PATCH configuramos la opción 'Headers' como hicimos en el caso anterior y de la misma manera enviaremos la información en un body, pero solo cambiará un atributo a la vez, si enviamos más de un atributo solo se modificara el primero que enviemos.
Para utilizar este método escribiremos la petición de esta manera:

{
    "id": 4,
    "precio": 2000.0
}

Es muy importante demarcar que al igual que con el método PUT, también es necesario enviar el 'id' del libro al cual deseamos hacerle algún cambio, ya que de caso contrario no podremos aplicarlo y enviandole solo el atributo a modificar.

El último método disponible para utilizar es el DELETE, básicamente y como dice su nombre traducido al castellano 'borrar'. Para utilizarlo se usa la misma ruta que usamos anteriormente, http://localhost:8080/api/books, pero en este caso solo necesitamos enviar el número de 'id' del libro que deseemos borrar. Para hacer esto vamos a seleccionar la opción 'Params' y en el campo 'KEY' escibimos 'id'(sin comillas), para indicar el atributo que vamos a enviar, y en el campo 'VALUE' escribiremos el número de 'id' del libro que deesemos borrar. Para esto nos podemos ayudar del primer método descrito para obtener todos los libros registrados y ver el 'id' del libro que deseemos borrar. En este caso y siguiendo con los ejemplos mostrados anteriormente, en el caso que elija borrar el libro con '"id" : 4"', el libro con '"titulo": "Ensayo sobre la ceguera"' dejará de aparecer en la base de datos.  
