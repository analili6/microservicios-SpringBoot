Implementación de una arquitectura de microservicios para un monitor de frecuencia cardíaca, temperatura y oxigenación de un bebé.

Esta arquitectura consta de varios microservicios:

microservice-ConfigServer: Se encarga de centralizar toda la configuración del servidor, incluida la configuración de la base de datos y los puertos en los que se ejecuta cada microservicio.
microservice-Gateway: Es la puerta de entrada de nuestro servidor. Se ejecuta en el puerto 8080 y se encarga de recibir y enviar las peticiones a los demás servicios. También se encarga de la autenticación de la aplicación y utiliza SendMail para la verificación del correo electrónico.
microservice-Eureka: Servidor de registro para establecer la comunicación entre los microservicios.
microservice-Infante: CRUD para manipular la información del infante.
microservice-Dispositivo: CRUD para manipular la información de los dispositivos existentes.
microservice-Contacto: CRUD para manipular la información de los contactos de emergencia.
microservice-RegistroFr: CRUD para manipular la información del sensor de oxigenación.
microservice-RegistroFc: CRUD para manipular la información del sensor de frecuencia cardíaca.
microservice-Temperatura: CRUD para manipular la información del sensor de temperatura

Este proyecto Es Multiplaforma  se consume desde React, React Native y Tkinte lo que permite una mayor escalabilidad del sistema.
