**#Encrypte_chat**

##Contexto

El presente proyecto trata acerca de un chat cifrado, el cual realiza una conexión por medio de la red entre dos instancias. Además, después de realizar la conexión entre dichas instancias, se negocian claves por medio del algoritmo Diffie-Hellman, las cuales estarán en 256 bits y serán utilizadas para encriptar la información que se compartan las instancias por medio del algoritmo AES.

##¿Cómo se hizo el programa?

Para empezar, se tuvieron en cuenta diversos métodos que servirían para poder establecer una conexión entre dos instancias por medio de la red. Se pensó en usar un middleware llamado Ice que nos permite realizar una conexión tipo cliente-servidor y también se pensó en usar sockets, ambas formas se implementarían en el lenguaje de programación **Java**.

Después de decidir que el proyecto se ejecutaría usando de sockets, se empezó a programar el programa. Primero se definieron las interfaces a utilizar. En este caso planteamos tres, las cuales son:

![image](https://github.com/Itsumohitoride/encrypte_chat/assets/72984873/9fa5a25c-a9d3-49f8-a98f-19b04503ef6e)

En cada interfaz definimos los métodos necesarios que serían implemenados para llevar a cabo la conexión entre instacias, la comunicación por medio del chat y el método de encriptado.

Luego de definir las interfaces, pasaríamos a implementar la clase Chat, en la cual se definieron todos los métodos necesarios para enviar, recibir, escribir y mostrar los mensajes entre las dos intancias, además de inicializar el flujo de mensajes.

Después de implementar la clase chat, se pasaría a codificar la clase Encrypt, la cual se encarga de intercambiar claves entre las instancias por medio del algoritmo **Diffie-Hellman**, para luego utilizar dichas claves para encriptar y desencriptar por medio de **AES de 256 bits**, los mensajes que serán enviados entre las dos instancias.

Implementación de la clase Encrypt

Por último, se pasaría a implementar las dos clases que reprentan cada instancia presente en el chat cifrado:

- Clase servidor
- Clase cliente

La clase **servidor** se encarga de inicializar la conexión por medio de un serverSocket con un puerto definido por el usuario. Luego de inicializar el socket, se crea una conexión con la otra instancia inicializando el flujo de mensajes o el chat para que finalmente, se puedan negociar las claves, cifrar el chat y empezar a enviar y recibir mensajes.

Por otro lado, la **clase** cliente se encarga de inicializar un socket y conectarlo hacia un servidor, el cual se determina por medio de una dirección IP y un puerto específico. Luego de inicializar el socket, se establece la conexión con el servidor y se abre el flujo o el chat para que las dos instacias intercambien claves y se pueda encriptar el chat por medio del algorimo AES.

##Dificultades presentes

##Conclusiones

