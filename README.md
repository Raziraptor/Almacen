# Nombre del Proyecto

## Descripción

Este proyecto fue desarrollado para apoyar a una empresa con el control de sus materiales y el uso de herramientas por parte de sus trabajadores.

## Problema Identificado

El problema era la perdida de materiales por parte de sus trabajadores ya que estos perdian los materiales o no registraban la salida de los mismos.

## Solución

La solucion dada en este momento es una aplicacion de consola Java para que mediante una interfaz simple podamos registrar estos datos como el nombre y material solicitado con la cantidad del mismo, a esta se le asigna un ID y este ID sirve tambien para identificar el regreso.

## Arquitectura

La arquitectura del proyecto puede organizarse de la siguiente manera:

Capa de Presentación (Interfaz de Usuario): Esta capa maneja la interacción con el usuario, como la entrada de datos y la presentación de resultados. Puede incluir interfaces de línea de comandos o interfaces gráficas de usuario (GUI).

Capa de Lógica de Negocios: Aquí se encuentra la lógica principal del sistema. Maneja las operaciones de registro de salidas y regresos, así como cualquier lógica específica del negocio.

Capa de Persistencia de Datos: Se encarga de la lectura y escritura de datos en el archivo CSV. Puede incluir una clase que se encargue de interactuar con el archivo y manejar las operaciones de lectura y escritura.

Gestor de Entrada/Salida (I/O): Coordina la entrada y salida de datos entre la capa de presentación y las capas de lógica de negocios y persistencia de datos.

## Tabla de Contenidos

- [Requerimientos](#requerimientos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso](#uso)
- [Contribución](#contribución)
- [Roadmap](#roadmap)

## Requerimientos

Requerimientos
Java: Asegúrate de tener Java instalado en tu máquina. Puedes verificarlo ejecutando java -version en la línea de comandos.

## Instalación

### Cómo instalar el ambiente de desarrollo

Clona el repositorio: git clone https://url-del-repositorio.git
Navega al directorio del proyecto: cd nombre-del-proyecto
Compila el código fuente: javac ControlAlmacen.java

### Cómo Ejecutar Pruebas Manualmente

Desde el directorio del proyecto, ejecuta el programa: java ControlAlmacen
Paso a paso para configurar el ambiente de desarrollo.

### Cómo Generar un Archivo JAR

Compila el código fuente: javac ControlAlmacen.java
Crea un archivo JAR: jar cfe ControlAlmacen.jar ControlAlmacen ControlAlmacen.class

## Configuración

### Configuración del Producto
No se requiere configuración específica del producto.

### Configuración de los Requerimientos
No se requiere configuración de requisitos específicos.

## Uso

### Referencia para Usuario Final

Ejecuta el programa según las instrucciones de instalación.
Sigue las instrucciones en la interfaz para registrar salidas y regresos.

### Referencia para Usuario Administrador

No hay funciones específicas para administradores en este proyecto.

## Contribución

Clona el repositorio: git clone https://url-del-repositorio.git
Crea un nuevo branch: git checkout -b nombre-del-branch
Realiza tus contribuciones.
Envía un Pull Request

## Roadmap

No hay requisitos planificados para futuras implementaciones actualmente.
