# BackEnd Ceiba Parking App

## INADN-1393

Consiste en un sistema que simula el comportamiento del vigilante de un parqueadero. Las reglas de negocio son las siguientes:

 - El parqueadero recibe carros y motos
 - El parqueadero solo puede tener 20 carros y 10 motos simultaneamente
 - Las placas que inician por la letra "A" solo pueden ingresar al parqueadero los días Domingo y Lunes, de lo contrario debe mostrar un mensaje de que no esta autorizado a ingresar

La tabla de precios es la siguiente:

 - Valor hora carro = 1000
 - Valor hora moto = 500
 - Valor día carro = 8000
 - valor día moto = 4000
 
Las motos que tengan un cilindraje mayor a 500 CC paga 2000 de mas al valor total. Cuando sale un carro del parqueadero se cobra por horas si permaneció menos de 9 horas en el parqueadero, de lo contrario se cobra por días. El valor del día comienza entre las 9 horas de parqueo y finaliza pasadas 24 horas de parqueo. El parqueadero funciona 24 horas, los 7 días de la semana.

Ejemplos:

 - Si el carro permaneció un día y 3 horas se debe cobrar 11.000
 - Si la moto permaneció un 10 horas y es de 650CC se cobra 6.000

## INADN-1394
 
 - La aplicación debe ser construida para la Web
 - La interfaz gráfica Web se debe adaptar a los celulares
 - Los servicios REST deben aceptar mínimo 100 peticiones concurrentes por minuto con un tiempo de respuesta cada una no mayor a 1,5 segundos
 - Todo el software se debe construir con pruebas automatizadas, para validar cada una de las capas. Por ejemplo, pruebas unitarias, integración, carga y funcionales
 - Todas las pruebas y el proceso debe quedar automatizado en Jenkins
 - El servidor de producción sobre el que se instalará la aplicación tiene Linux (Ubuntu 12) con 2 Gb de memoria RAM y 4 cores de procesamiento
 
## INADN-1395

En este paso debería quedar lista una prueba de concepto con la arquitectura inicial y además deberían quedar corriendo los test unitarios en integración continua, además de eso debería quedar la revisión estática de código en Sonar.

## INADN-1396

Se recomienda usar alguno de los siguientes conjuntos de tecnologías para el backend, en las cuales se expone la lógica de negocio y se exponen los servicios REST

 - Sring boot - Gradle
 - Play Framework - SBT
 - Vertx - Gradle
 - Jersey con algún servidor de aplicaciones como Weblogic - Jboss - Glassfish y Gradle
 - La base de datos queda a su críterio, se recomienda alguna de las siguientes H2, Mysql, Mongo, Oracle

Para el frontend se recomienda utilizar alguna de las siguientes tecnologías Angular 4, React Js.
