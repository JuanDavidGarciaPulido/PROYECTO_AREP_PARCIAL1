#AREP_PARCIAL1
#### Juan David García Pulido

##Ejecutar el programa
1. Clonar el repositorio.
 ```
    git clone https://github.com/JuanDavidGarciaPulido/PROYECTO_AREP_PARCIAL1.git

    cd PROYECTO_AREP_PARCIAL1
```

2. Compilar el proyecto.
 ```
    mvn clean install
 ```

3. Ejecutar las clases HttpServerCalculadora y HttpServer. (Hacerlo en dos terminales diferentes puesto que ambas deben correr al tiempo para que funcione el programa)
```
    java -cp target/classes co.edu.escuelaing.arep.app.HttpServerCalculadora

    java -cp target/classes co.edu.escuelaing.arep.app.HttpServer
```


![image](https://github.com/user-attachments/assets/be6cdd7a-2259-41af-854c-44f2edbea14c)



Opcionalmente también puede correr ambas clases desde su IDE preferido.


![image](https://github.com/user-attachments/assets/1e2e92f0-0ecb-4514-866c-d002dc0c86cd)

![image](https://github.com/user-attachments/assets/30312450-1cf5-4872-827a-3c98893bc78f)

4. Use su navegador e ingrese a http://localhost:35000/cliente
Allí podrá ingresar dos números (decimales) separados por una coma (Por defecto se dejó el 10.0 y el -5.0) y se le retornará el número mayor. (utilizando el método max de la librería Math de java)

![image](https://github.com/user-attachments/assets/784e40b9-31cf-4622-9afc-1099a971478d)



