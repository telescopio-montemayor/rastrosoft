	Para hacer una prueba del sistema es necesario seguir los siguientes pasos:

	- Instalar INDI. 

		- Si dispone de un sistema operativo Linux, introduzca los siguientes comandos para su instalación.
		
			sudo apt-add-repository ppa:mutlaqja/ppa
			sudo apt-get update
			sudo apt-get install indi-full

		- Sinó, visite la página oficial de INDI en el siguiente enlace: http://indilib.org/download.html

	- Instalar INDI Web manager disponible en el siguiente enlace: http://indilib.org/support/tutorials/162-indi-web-manager.html

	- Iniciar un servidor INDI con los dispositivos: "Telescope Simulator", "CCD Simulator", "Focuser Simulator".
	
	- Importar la base de datos rastrosoft.sql con usuario y contraseña: "root" , "".
	
	- En la tabla "config_data" de la base de datos, cargar la siguiente configuración:

		- "path" -> Ruta dónde se desea que se guarden las capturas. (/home/captures)
		- "indi_server_location" -> dirección dónde se encuentra corriendo el servidor INDI. (localhost)
		- "indi_server_port" -> puerto del servidor INDI. (7624)
		- "host_www" -> dirección del servidor rastrosoft. (http://localhost:8080)

	- En la tabla "send_mail" configurar el correo electrónico de rastrosoft para enviar la confirmación de creación de cuenta.
	
		- "mail" -> correo electrónico de rastrosoft de gmail. (rastrosoft@gmail.com)
		- "password" -> contraseña del correo electrónico de rastrosoft.

	- Ejecutar rastrosoft.war en un servidor Java.

	- Para acceder al panel de administrador es necesario ingresar en: "http://localhost:8080/rastrosoft/moderation".
	
	- El usuario y contraseña por defecto del moderador es: "moderador", "1".
