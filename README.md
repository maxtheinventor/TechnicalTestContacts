¡HOLA!

v1.0
--------------

- En esta versión de la APP podremos realizar todo lo que habeis pedido en la prueba técnica y un poco más.

  Lo extra añadido:

  1) Pagina inicial donde podremos hacer una busqueda random que nos devolvera 10 usuarios aleatorios o buscar una cantidad predefinida de usuarios aleatorios de la API.
  2) Ver los contactos guardados en la APP (esta opción esta bajo desarrollo todavia aunque si es cierto que ya los persisto en la DDBB).
  3) En el menú desplegable de los tres puntos en la pantalla de visualización de todos los contactos descargados podremos guardar todos los usuarios decargados en la DDBB.
 
  He empleado: inyección de de dependencias con Hilt, usado Retrofit para las llamadas a la API, Room para la parte de DDBB, el patrón de diseño MVVM, clean architecture, splash screen para los checks iniciales antes de que el usuario use la APP, coil para la descarga de imagenes de perfil de manera asincrona
  y constraint layout para la pantalla de detalles del contacto para permitirme hacer una interfaz más personalizada aun. 

  No he usado la navegación de Compose ya que trae problemas y prefiero tener más flexibilidad y hacer una navegación personalizada sin depender de librerias. También hay algunas partes de las interfaces que podria haber reutilizado pero he decidido no hacerlo (me refiero a la del mostrado de datos que
  fuese igual para los de la DDBB como los descargados de la API, pero he optado por no hacer eso ya que quiero tener en un futuro interfaces diferentes, trabajar con viewModels individuales para diferentes funciones y asi mantener las cosas separadas y escalables).
 
  IMPORTANTE:

  - La interfaz está adaptada para la pantalla de un Realme 9i, el cual tiene un tamaño bastante standar, pero para sacar esto en producción escribiria codigo para adaptarlo a todo tipo de pantallas (no me dio tiempo).
  - He incluido un PDF en inglés adjuntado en el correo con mi diseño inicial de la APP para que veais un poco parte de mi proceso de creación de APPs (Figma tambien se usarlo también, pero aquí me habeis dado ya los deberes hechos).
 

    ¡Muchas gracias!
