# Documentación del Proyecto Spring Boot

## Estructura del Proyecto

La estructura del proyecto se organiza de la siguiente manera:

- **src**: Carpeta principal donde reside el código fuente.
  - **main**: Contiene el código fuente principal del proyecto.
    - **java**: Contiene el código fuente en Java.
      - **com.springboot.di.app.springboot_di**: Paquete base del proyecto.
        - **controllers**: Contiene los controladores que manejan las peticiones HTTP.
        - **models**: Contiene las clases de modelo que representan los datos.
        - **repositories**: Contiene las interfaces y clases que manejan la persistencia de datos.
        - **services**: Contiene las interfaces y clases que implementan la lógica de negocio.
        - **AppConfig.java**: Clase de configuración de Spring.
        - **SpringbootDiApplication.java**: Clase principal que arranca la aplicación Spring Boot.
    - **resources**: Contiene recursos estáticos y archivos de configuración.
    - **test**: Contiene el código de prueba.
    - **target**: Contiene los archivos compilados y paquetes generados por Maven.

## Explicación de Clases y Anotaciones

### Paquete `controllers`

#### Clase `SomeController`

Esta clase maneja las peticiones HTTP dirigidas a la API. Utiliza las siguientes anotaciones:

- **@RestController**: Indica que la clase es un controlador donde cada método retorna un objeto de dominio en lugar de una vista. La respuesta se serializa directamente en JSON o XML y se escribe en el cuerpo de la respuesta HTTP.
- **@RequestMapping("/api")**: Mapea las peticiones HTTP a nivel de clase. Todas las peticiones que comiencen con `/api` serán manejadas por esta clase.
- **@Autowired**: Inyección automática de dependencias por parte de Spring.

Métodos:
- `list()`: Maneja las peticiones GET a `/api`, retornando una lista de productos.
- `show(Long id)`: Maneja las peticiones GET a `/api/{id}`, retornando un producto específico por su ID.

### Paquete `models`

#### Clase `Product`

Representa un producto con atributos `id`, `name` y `price`. Implementa `Cloneable` para permitir la clonación de objetos. Incluye constructores, getters y setters, y un método `clone()` para clonar el objeto.

### Paquete `repositories`

#### Interfaz `ProductRepository`

Define las operaciones básicas para acceder a los productos, como `findAll()` y `findById(Long id)`.

#### Clase `ProductRepositoryFoo`

Implementa `ProductRepository` retornando un solo producto de ejemplo. Utiliza la anotación:
- **@Repository("productFoo")**: Marca la clase como un bean de repositorio y la identifica con el nombre `productFoo`.

#### Clase `ProductRepositoryImpl`

Implementa `ProductRepository` utilizando una lista de productos en memoria. Utiliza las anotaciones:
- **@Primary**: Indica que esta es la implementación principal que debe ser inyectada cuando haya múltiples implementaciones de una interfaz.
- **@RequestScope**: Define el ciclo de vida del bean para cada solicitud HTTP.
- **@Repository("productList")**: Marca la clase como un bean de repositorio y la identifica con el nombre `productList`.

#### Clase `ProductRepositoryJson`

Implementa `ProductRepository` leyendo los productos desde un archivo JSON. Utiliza `ObjectMapper` de la biblioteca Jackson para la deserialización del JSON.

### Paquete `services`

#### Interfaz `ProductService`

Define las operaciones de servicio para los productos, como `findAll()` y `findById(Long id)`.

#### Clase `ProductServiceImpl`

Implementa `ProductService` y contiene la lógica de negocio para manejar los productos. Utiliza las anotaciones:
- **@Service**: Marca la clase como un bean de servicio.
- **@Autowired**: Inyección automática de dependencias.
- **@Qualifier("productJson")**: Especifica el bean `ProductRepository` a inyectar, identificándolo con el nombre `productJson`.

Métodos:
- `findAll()`: Retorna todos los productos aplicando un impuesto configurado.
- `findById(Long id)`: Retorna un producto específico por su ID.

### Clase `AppConfig`

Clase de configuración de Spring que define los beans necesarios. Utiliza las anotaciones:
- **@Configuration**: Indica que la clase tiene métodos `@Bean` que deben ser gestionados por el contenedor de Spring.
- **@PropertySource("classpath:config.properties")**: Indica la ubicación de un archivo de propiedades para configurar la aplicación.

Métodos:
- `productRepositoryJson()`: Define un bean de `ProductRepository` que lee productos desde un archivo JSON, identificado como `productJson`.

## Clase Principal `SpringbootDiApplication`

Esta es la clase principal que arranca la aplicación Spring Boot. Contiene el método `main` que utiliza `SpringApplication.run` para iniciar la aplicación.

## Conclusión

Este proyecto de Spring Boot muestra una implementación básica de inyección de dependencias (DI) utilizando varias anotaciones de Spring. Las clases están organizadas en paquetes que representan diferentes capas de la aplicación (controladores, modelos, repositorios, servicios), y las anotaciones se utilizan para definir beans, configurar el ciclo de vida de los componentes y manejar la inyección de dependencias. Esto proporciona una estructura clara y escalable, facilitando el mantenimiento y la expansión del proyecto.
