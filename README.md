# **Encrypte_chat**

## Contexto

El presente proyecto trata acerca de un chat cifrado, el cual realiza una conexión por medio de la red entre dos instancias. Además, después de realizar la conexión entre dichas instancias, se negocian claves por medio del algoritmo Diffie-Hellman, las cuales estarán en 256 bits y serán utilizadas para encriptar la información que se compartan las instancias por medio del algoritmo AES.

## ¿Cómo se hizo el programa?

Para empezar, se tuvieron en cuenta diversos métodos que servirían para poder establecer una conexión entre dos instancias por medio de la red. Se pensó en usar un middleware llamado Ice que nos permite realizar una conexión tipo cliente-servidor y también se pensó en usar sockets, ambas formas se implementarían en el lenguaje de programación **Java**.

Después de decidir que el proyecto se ejecutaría usando sockets de Java, se empezó a implementar el programa. Primero se definieron las interfaces a utilizar. En este caso planteamos tres, las cuales son:

![image](https://github.com/Itsumohitoride/encrypte_chat/assets/72984873/1ab4d275-959c-4a9d-8844-9c53e0411828)

En cada interfaz definimos los métodos necesarios que serían implemenados para llevar a cabo la conexión entre instacias, la comunicación por medio del chat y el método de encriptado.

Luego de definir las interfaces, pasaríamos a implementar la clase **DiffieHellman**, en la cual se definieron todos los métodos necesarios para realizar dicho algoritmo, en donde se intercambian y generan llaves públicas. Además, se generaron llaves privadas para cada instancia que luego servirían para generar una clave común y así, poder encriptar y desencriptar mensajes.

Después de implementar la clase DiffieHellman, se pasaría a codificar la clase Encrypt, la cual se encarga de utilizar las claves generadas por medio de Diffie-Hellman, para luego utilizar dichas claves para encriptar y desencriptar por medio de **AES de 256 bits**, los mensajes que serán enviados entre las dos instancias. Cabe resaltar que la clase Encrypt encripta los mensajes de salida y desencripta los mensajes que llegan a un remitente. 

Por último, se pasaría a implementar las dos clases que reprentan cada instancia presente en el chat cifrado:

- Clase servidor
- Clase cliente

La clase **servidor** se encarga de inicializar la conexión por medio de un serverSocket con un puerto definido por el usuario. Luego de inicializar el socket, se crea una conexión con la otra instancia inicializando el flujo de mensajes o el chat para que finalmente, se puedan negociar las claves, cifrar el chat y empezar a enviar y recibir mensajes.

Por otro lado, la **clase** cliente se encarga de inicializar un socket y conectarlo hacia un servidor, el cual se determina por medio de una dirección IP y un puerto específico. Luego de inicializar el socket, se establece la conexión con el servidor y se abre el flujo o el chat para que las dos instacias intercambien claves y se pueda encriptar el chat por medio del algorimo AES.

## Dificultades presentes:

Durante el desarrollo del proyecto, se presentaron muchos inconvientes, no solo al momento de programar, sino también de recordar conceptos ya vistos en la clase. 

Dentro de los inconvenientes presentados por fuera de la programación, nos encontramos con problemas para recordar el algoritmo Diffie Hellman, que aunque a priori no es el algortimo más elaborado, al presentar cálculos matemáticos dificultó el proceso de comprensión y nos llevó a perder más tiempo del esperado.

Por otro lado, los problemas presentes al momento de programar fueron muchos, tanto así que el tiempo no nos fue suficiente para implementar una interfaz gráfica. Los problemas más comunes que se nos presentaron fue al momento de intercambiar claves entre instancias, ya que en un principio no se enviaban de forma correcta a las instancias correspondientes. También, presentamos incontables errores al momento de realizar el algoritmo Diffie Hellman, ya que en un principio, pensamos en realizarlo con fórmulas matemáticas, lo cual no resultó conveniente de solo pensarlo.

Aún habiendo mencionado estos problemas al momento de programar, el problema más grande que con el que nos encontramos fue al momento de realizar el encriptado y desencriptada, ya que en un principio, los algoritmos usados no encriptaban de forma correcta debido a una mala configuración en su definición. Después de lograr que los mensajes se encriptaran de forma correcta, llegó la odisea de desencriptarlos. Este fue el proceso que más tiempo nos tomó debido a que se nos presentaron problemas de muchas formas, al momento de definir el algoritmo, de definir sus parámetros, de convertir y obtener los bytes pertienentes tanto de la clave secreta como del mensaje encriptado, pero al final, se logró llevar a cabo la operación de desencriptado.

## Conclusiones

El proyecto de realizar un chat cifrado fue un proyecto muy interesante e innovador para nuestras carreras, ya que nunca habíamos tenido un reto similar, de encriptar datos y pasarlos por medio de la red a una instancia. Además, el hecho de crear un chat fue muy motivdor, ya que es basicamente un prototipo de lo que tenemos día a día en nuestros dispositivos móviles. Para terminar, estuvimos satisfechos con todo el trabajo y el proceso realizado para llevar a cabo el proyecto, que al final, terminó siendo innovador y retador.

## Trabajo realizado por medio de:
- Java
- IDE IntelliJ Idea
- JDK 17

## Trabajo realizado por:
- *Gianni Benavides*
- *Luis Miguel Ossa*
- *Santiago Trochez*

### Referencias:

- [Algoritmo Diffie Hellman](https://protecciondatos-lopd.com/empresas/algoritmo-diffie-hellman/)
- [Sockets en Java](https://parzibyte.me/blog/2018/02/09/sockets-java-chat-cliente-servidor/)


