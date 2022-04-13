# APLICACION ANDROID ENJOY PADEL
## Se trada de una aplicación que permite gestionar los partidos de un torneo de padel
### - La aplicación deberá consumir la API diseñada y desarrollada en la asignatura de Acceso a Datos. Al menos 2 operaciones de cada uno de estos métodos: POST, GET, PUT y DELETE. Al menos una de ellas permitirá listar información
#### En todas las clases podemos listar y añadir información mediante GET y POST, tambíen podremos eliminar jugadores y centros deportivos mediante DELETE y modificar jugadores mediante PUT.
#### Se añaden unas preferencias para personalizar la visualización de la lista de jugadores
#### Se incorporan herramientas de elección o toma de fotos para la imagen del usuario
### - La comunicación con la API se llevará a cabo utilizando Retrofit y RxJava
#### La aplicación se comunica mediantre Retrofit y RxJava de forma reactiva
### - Se hará uso de mapas para mostrar información (si es necesario, añadir información geográfica a alguno de los recursos de la API diseñada)
#### En el momento de añadir un centro deportivo, podremos seleccionar en el mapa su ubicación y visualizarla pulsando en el elemento de la lista de centros deportivos que queramos consultar.
### - La aplicación se diseñará utilizando el patrón MVP (Model View Presenter)
#### Se utiliza el patron MVP para el desarrollo de la aplicación
### - El usuario podrá almacenar como “favorito” aquella información de la API que le resulte de interés
#### Se añade la funcionalidad añadir a favoritos en la seccion de JUGADORES, en el menú contextual podremos añadirlos y en el action bar podremos visualizar la lista de jugadores favoritos
### - Utilizar la Directions API para guiar al usuario a alguna ubicación relevante en la aplicación
#### En el apartado de visualizar la información de un centro, mediante Directions API, nos generará una ruta en coche entre nuestra ubicación y la dirección del centro deportivo
### - Utiliza la herramienta Git (y GitHub) durante todo el desarrollo de la aplicación. 
#### Se utiliza GitHub durante el desarrollo de la aplicación. Link de GitHub: https://github.com/raulcoroe/enjoypadel-2EV
### - Añadir un menú de preferencias con al menos 3 opciones que modifiquen el comportamiento de la aplicación. Este menú estará siempre disponible en el ActionBar
#### Se añade un menú con 3 preferencias que se encontrará en el ActionBar de la sección de jugadores.
### - Hacer uso de mapas en las pantalla donde se registre información. Para indicar información geográfica, por ejemplo
#### En la sección de centros deportivos se realizan operaciones con mapas donde se registra, visualiza y genera información como las rutas.
### - Diseñar al menos 3 layouts para otras posiciones de la pantalla (portrait/landscape)
#### Se diseñan 5 layouts para diferentes posiciones de pantalla portrait y landscape