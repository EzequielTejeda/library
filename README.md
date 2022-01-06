# Explicación de uso
 
Para poder inicializar el proyecto se requiere, en principio, un IDE, en este caso la API fue diseñada en IntelliJ IDEA, pero se puede utilizar cualquiera para poder ejecutarlo. Lo que si es sumamente necesario es tener descargada y configurada la herramienta de Gradle, ya que el proyecto fue desarrollado con ella, en caso contrario el proyecto no podra ser compilado.

Cuando ya tengamos nuestro IDE y Gradle descargado y configurado podremos ejecutar el proyecto. En el caso de IntelliJ IDEA presionando la combinación Mayus+F10 el proyecto sera compilado y cuando en la consola de nuestro IDE se indique que el proyecto fue compilado y ejecutado podremos pasar a probar su parte funcional.

Como este es un proyecto exclusivamente de desarrollo backend, necesitaremos una aplicación que nos permita realizar pruebas API a través los métodos de petición HTTP. Para esto podemos usar la aplicación de Postman o la extensión de VSCode, Thunder Client (ambas son útiles para el uso que les daremos en este caso).

Al ser una API CRUD podremos crear libros, ver todos los libros guardados, actualizar atributos de los libros y borrarlos de ser necesario.

En el primer caso vamos a utilizar en nuestra aplicación de prueba el método GET en la ruta http://localhost:8080/api/books. De este modo vamos a obtener todos los libros que se encuentran guardados en nuestra base de datos.

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

El último método disponible para utilizar es el DELETE, básicamente y como dice su nombre traducido al castellano 'borrar'. Para utilizarlo se usa la misma ruta que usamos anteriormente, http://localhost:8080/api/books, pero en este caso solo necesitamos enviar el número de 'id' del libro que deseemos borrar. Para hacer esto vamos a seleccionar la opción 'Params' y en el campo 'KEY' escibimos 'id'(sin comillas), para indicar el atributo que vamos a enviar, y en el campo 'VALUE' escribiremos el número de 'id' del libro que deesemos borrar. Para esto nos podemos ayudar del primer método descrito para obtener todos los libros registrados y ver el 'id' del libro que deseemos borrar. En este caso y siguiendo con los ejemplos mostrados anteriormente, en el caso que elija borrar el libro con '"id" : 4"', el libro con '"titulo": "Ensayo sobre la ceguera"' dejará de aparecer en la base de datos.  
