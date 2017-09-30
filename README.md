# s1_vivienda
Repositorio del proyecto de vivienda universitaria del grupo 4 de la sección 1

UNIT NAME = viviendaPU

# s1_vivienda
Repositorio del proyecto de vivienda universitaria del grupo 4 de la sección 1

UNIT NAME = viviendaPU
# API Rest Recurso Prestador
## Descripción
Para el recurso Servicio, al igual que los demás que existen en el sistema, se modifica y se envía información mediante archivos JSON. Si se quiere modificar algo de la entidad, se lee el archivo JSON y es mapeado para que sea visto como atributos por el sistema. De la misma forma, si se desea enviar información al cliente, se toman los atributos y son convertidos a un archivo JSON, que es lo que finalmente ve el cliente. Son servicios son generados en la URL /administrador/api/.

### Recurso Prestador
El objeto Servicio tiene una representación JSON:

#### Representación Minimum
```javascript
{
nombre: '' /*Tipo String*/,
    documento : '' /*Tipo Double*/,
 disponible : '' /*Tipo Boolean*/,
}
```

#### GET /Prestadores
Retorna una colección de objetos Prestadores en representación Detail.
Cada Cuenta en la colección tiene embebidos los siguientes objetos:  servicios.

#### Parámetros

#### N/A

#### Respuesta


Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-prestador)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error


#### GET /prestadores/{id}


Retorna un objeto de la clase prestadores

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto prestador a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Servicio en [representaciones Detail](#recurso-prestador)
404|No existe un objeto Prestador con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error


#### POST /Prestador

Es el encargado de crear objetos Prestador.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Servicio que será creado|Sí|[Representación Detailed](#recurso-prestador)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto prestador ha sido creado|[Representación Detailed](#recurso-prestador)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto prestador|Mensaje de error

#### PUT /prestadores /{id}

Es el encargado de actualizar objetos servicio.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto prestador a actualizar|Sí|Integer
body|body|Objeto prestador nuevo|Sí|[Representación Detailed](#recurso-prestador)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto prestador actualizado|[Representación Detailed](#recurso-prestadpr)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Administrador|Mensaje de error


#### DELETE /administrador/{id}

Elimina un objeto prestador.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto servicio  a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
# API Rest Recurso Servicio
## Descripción
Para el recurso Servicio, al igual que los demás que existen en el sistema, se modifica y se envía información mediante archivos JSON. Si se quiere modificar algo de la entidad, se lee el archivo JSON y es mapeado para que sea visto como atributos por el sistema. De la misma forma, si se desea enviar información al cliente, se toman los atributos y son convertidos a un archivo JSON, que es lo que finalmente ve el cliente. Son servicios son generados en la URL /administrador/api/.

### Recurso Servicio
El objeto Servicio tiene una representación JSON:

#### Representación Minimum
```javascript
{
nombre: '' /*Tipo String*/,
    precio: '' /*Tipo Double*/,
}
```

#### GET /Servicio
Retorna una colección de objetos Servicio en representación Detail.
Cada Cuenta en la colección tiene embebidos los siguientes objetos: Tarjeta, estudiante, servicios, pagos.

#### Parámetros

#### N/A

#### Respuesta


Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-Servicio)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error


#### GET /servicio/{id}


Retorna un objeto de la clase servicio

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto servicio a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Servicio en [representaciones Detail](#recurso-servicio)
404|No existe un objeto Servicio con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error


#### POST /Servicio

Es el encargado de crear objetos Servicio.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Servicio que será creado|Sí|[Representación Detailed](#recurso-administrador)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto servicio ha sido creado|[Representación Detailed](#recurso-servicio)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto servicio|Mensaje de error

#### PUT /servicio/{id}

Es el encargado de actualizar objetos servicio.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto servicio a actualizar|Sí|Integer
body|body|Objeto servicio nuevo|Sí|[Representación Detailed](#recurso-administrador)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto servicio actualizado|[Representación Detailed](#recurso-administrador)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Administrador|Mensaje de error


#### DELETE /administrador/{id}

Elimina un objeto servicio.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto servicio  a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

# Tabla de contenidos

-  [API](#api-de-la-aplicación-Utower)
  - [Recurso Estudiante](#recurso-Estudiante)
    - [GET /estudiantes](#GET-/estudiantes)
    - [GET /estudiantes/{documento}](#GET-/estudiantes/{documento})
    - [POST /estudiantes](#POST-/estudiantes)
    - [PUT /estudiantes/{documento}](#PUT-/estudiantes/{documento})
    - [DELETE /estudiantes/{documento}](#DELETE-/estudiantes/{documento})
# API Rest
## API de la aplicación UTower
### Recurso Estudiante
El objeto Estudiante tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    documento:  /*Tipo Long*/, nombre: /*Tipo String*/, userName: /*Tipo String*/
}
```

#### Representación Detail
```javascript
{
    documento:  /*Tipo Long*/, 
    nombre: /*Tipo String*/,
    userName: /*Tipo String*/
    sugerencias: [
            {/*Sugerencia 1 en su Representación JSON Minimun/*},
            .....
            {/*Sugerencia n en su Representación JSON Minimun/*}
    ],
    cuenta: {/*Cuenta en su Representación JSON Minimun/*},
    apartamento: {/*Apartamento en su Representación JSON Minimun/*}
}
```
#### GET /estudiantes

Retorna una colección de objetos Estudiante en representación Detail.
#### Parámetros

#### N/A
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Estudiantes
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### GET /estudiantes/{documento}

Retorna una colección de objetos Estudiante en representación Detail.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
documento|Path|Documento del objeto Estudiante a consultar|Sí|Long
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Estudiante en representación Detail 
404|No existe un objeto Estudiante con el documento solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### POST /estudiantes

Es el encargado de crear objetos Estudiantes.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Estudiante que será creado|Sí|[Representación](#recurso-Estudiante)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Estudiante ha sido creado|[Representación](#recurso-Estudiante)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Estudiante|Mensaje de error
#### PUT /estudiantes/{documento}

Es el encargado de actualizar objetos Estudiante.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
documento|Path|Documento del objeto Estudiante a actualizar|Sí|Long
body|body|Objeto Torre nuevo|Sí|[Representación](#recurso-Estudiante)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Estudiante actualizado|[Representación](#recurso-Estudiante)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Estudiante|Mensaje de error
#### DELETE /estudiantes/{documento}

Elimina un objeto Estudiante.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
documento|Path|Documento del objeto Estudiante a eliminar|Sí|Long
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
# Tabla de contenidos

-  [API](#api-de-la-aplicación-Utower)
 - [Recurso Sugerencia](#recurso-Sugerencia)
    - [GET /sugerencias](#GET-/sugerencias)
    - [GET /sugerencias/{id}](#GET-/sugerencias/{id})
    - [POST /sugerencias](#POST-/sugerencias)
    - [PUT /sugerencias/{id}](#PUT-/sugerencias/{id})
    - [DELETE /sugerencias/{id}](#DELETE-/sugerencias/{id})
  
# API Rest
## API de la aplicación UTower
### Recurso Sugerencia 
El objeto Sugerencia tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id:  /*Tipo Long*/, mensaje: /*Tipo String*/
}
```

#### Representación Detail
```javascript
{
    id:  /*Tipo Long*/, 
    mensaje: /*Tipo String*/,
    estudiante: {/*Estudiante en su Representación JSON Minimun/*},
    administrador: {/*Administrador en su Representación JSON Minimun/*}
}
```
#### GET /sugerencias

Retorna una colección de objetos Sugerencia en representación Detail.
#### Parámetros

#### N/A
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Sugerencia
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### GET /sugerencias/{id}

Retorna una colección de objetos Sugerencia en representación Detail.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Sugerencia a consultar|Sí|Long
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Sugerencia
404|No existe un objeto sugerencia con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### POST /sugerencias

Es el encargado de crear objetos Sugerencia.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Piso que será creado|Sí|[Representación](#recurso-Sugerencia)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto sugerencia ha sido creado|[Representación](#recurso-piso)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto sugerencia|Mensaje de error
#### PUT /sugerencias/{id}

Es el encargado de actualizar objetos Sugerencia.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Sugerencia a actualizar|Sí|Long
body|body|Objeto Piso nuevo|Sí|[Representación](#recurso-Sugerencia)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto sugerencia actualizado|[Representación](#recurso-piso)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto sugerencia|Mensaje de error
#### DELETE /sugerencias/{id}

Elimina un objeto Sugerencia.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Piso a eliminar|Sí|Long
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

# Tabla de contenidos
-  [API](#api-de-la-aplicación-Utower)
- [Recurso Cuenta](#recurso-cuenta)
   - [GET /cuentas](#GET-/cuentas)
   - [GET /cuentas/{id}](#GET-/cuentas/{id})
   - [POST /cuentas](#POST-/cuentas)
   - [PUT /cuentas/{id}](#PUT-/cuentas/{id})
   - [DELETE /cuentas/{id}](#DELETE-/cuentas/{id})
   - [GET cuentas/{cuentasid}/estudiante](#GET-cuentas/{cuentasid}/estudiante)
   - [GET cuentas/{cuentasid}/servicios](#GET-cuentas/{cuentasid}/ordenPagos)
   - [GET cuentas/{cuentasid}/tarjetas](#GET-cuentas/{cuentasid}/tarjetas)

 - [Recurso OrdenPago](#recurso-OrdenPago)
    - [GET /ordenPagos](#GET-/ordenPagos)
    - [GET /ordenPagos/{idPago}](#GET-/ordenPagos/{idPago})
    - [POST /ordenPagos](#POST-/ordenPagos)
    - [PUT /ordenPagos/{idPago}](#PUT-/ordenPagos/{idPago})
    - [DELETE /ordenPagos/{idPago}](#DELETE-/ordenPagos/{idPago})
    

- [Recurso Tarjeta](#recurso-tarjeta)
   - [GET /tarjetas](#GET-/tarjetas)
   - [GET /tarjetas/{numerotarjeta}}](#GET-/tarjetas/{numerotarjeta})
   - [GET tarjetas/{numerotarjeta}/cuenta](#GET-tarjetas/{numerotarjeta}/cuenta)
   - [POST /tarjetas](#POST-/tarjetas)
   - [DELETE /tarjetas/{numerotarjeta}](#DELETE-/tarjetas/{numerotarjeta})
   - [PUT /tarjetas/{numerotarjeta}](#PUT-/tarjetas/{numerotarjeta})
  
# API Rest
## API de la aplicación UTower
### Recurso Cuenta
El objeto Cuenta tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    renta: '' /*Tipo Integer*/,
}
```

#### Representación Detail
```javascript
{
    id: '' /*Tipo Long*/,
    renta: '' /*Tipo Integer*/,
    tarjetas: [
         { /*Tarjeta 1 en su representación JSON Minimum*/ },
         ...
         { /*Tarjeta n en su representación JSON Minimum*/ }
    ],
    estudiante: { /*Estudiante en su representación JSON Minimum*/ },
    servicios: [
         { /*Servicio 1 en su representación JSON Minimum*/ },
         ...
         { /*Servicio n en su representación JSON Minimum*/ }
    ],
    pagos: [
         { /*Pago 1 en su representación JSON Minimum*/ },
         ...
         { /*Pago n en su representación JSON Minimum*/ }
    ]
}
```



#### GET /cuentas

Retorna una colección de objetos Cuenta en representación Detail.
Cada Cuenta en la colección tiene embebidos los siguientes objetos: Tarjeta, estudiante, servicios, pagos.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-cuenta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### GET /cuentas/{id}

Retorna un objeto Cuenta con la id proporcionada (si existe) en representación Detail.
La Cuenta tiene embebidos los siguientes objetos: Tarjeta, estudiante, servicios, pagos.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Cuenta a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Cuenta en [representaciones Detail](#recurso-cuenta)
404|No existe un objeto Cuenta con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### POST /cuentas

Es el encargado de crear objetos Cuenta.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Cuenta que será creado|Sí|[Representación Detail](#recurso-cuenta)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Cuenta ha sido creado|[Representación Detail](#recurso-cuenta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Cuenta|Mensaje de error

#### PUT /cuentas/{id}

Es el encargado de actualizar objetos Cuenta.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Cuenta a actualizar|Sí|Integer
body|body|Objeto Cuenta nuevo|Sí|[Representación Detail](#recurso-cuenta)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Cuenta actualizado|[Representación Detail](#recurso-cuenta)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Cuenta|Mensaje de error

#### DELETE /cuentas/{id}

Elimina un objeto Cuenta.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Cuenta a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### GET cuentas/{cuentasid}/estudiante

Retorna al estudiante asociado a un objeto Cuenta en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Cuenta a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Estudiante en [representación Detail](#recurso-estudiante)
500|Error consultando estudiante|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

### GET cuentas/{cuentasid}/tarjetas

Retorna una colección de objetos Tarjeta asociados a un objeto Cuenta en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Cuenta a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Tarjeta en [representación Detail](#recurso-tarjeta)
500|Error consultando servicios|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### GET cuentas/{cuentasid}/ordenPagos

Retorna una colección de objetos OrdenPago asociados a un objeto Cuenta en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Cuenta a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos OrdenPago en [representación Detail](#recurso-OrdenPago)
500|Error consultando ordenes de pago|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

### Recurso OrdenPago
El objeto OrdenPago tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    idPago:  /*Tipo Long*/, precio: /*Tipo Double*/
}
```

#### Representación Detail
```javascript
{
    idPago:  /*Tipo Long*/, 
    percio: /*Tipo Double*/,
    servicio: {/*Servicio en su Representación JSON Minimun/*},
}
```
#### GET /ordenPagos

Retorna una colección de objetos OrdenPago en representación Detail.
#### Parámetros

#### N/A
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos OrdenPago
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### GET /ordenPagos/{idPago}

Retorna una colección de objetos OrdenPago en representación Detail.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idPago|Path|ID del objeto OrdenPago a consultar|Sí|Long
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto OrdenPago en representación Detail 
404|No existe un objeto OrdernPago con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### POST /ordenPagos

Es el encargado de crear objetos OrdenPago.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto OrdenPago que será creado|Sí|[Representación](#recurso-OrdenPago)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto OrdenPago ha sido creado|[Representación](#recurso-OrdenPago)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto OrdenPago|Mensaje de error
#### PUT /ordenPagos/{idPago}

Es el encargado de actualizar objetos OrdenPago.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idPago|Path|ID del objeto OrdenPago a actualizar|Sí|Long
body|body|Objeto OrdenPago nuevo|Sí|[Representación](#recurso-OrdenPago)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto OrdenPago actualizado|[Representación](#recurso-OrdenPago)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto OrdenPago|Mensaje de error
#### DELETE /ordenPagos/{idPago}

Elimina un objeto OrdenPago.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
idPago|Path|ID del objeto OrdenPago a eliminar|Sí|Long
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

### Recurso Tarjeta
El objeto Tarjeta tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    nombre: '' /*Tipo String*/,
    numerotarjeta: '' /*Tipo Long*/
}
```

#### Representación Detail
```javascript
{
    nombre: '' /*Tipo String*/,
    numerotarjeta: '' /*Tipo Long*/
    cuenta: { /*Cuenta en su representación JSON Minimum*/ }
}
```
#### GET /tarjetas

Retorna una colección de objetos Tarjeta en representación Detail.
Cada Tarjeta en la colección tiene embebidos los siguientes objetos: Cuenta.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-tarjeta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### GET /tarjetas/{numerotarjeta}

Retorna un objeto Tarjeta con la id proporcionada (si existe) en representación Detail.
La Tarjeta tiene embebidos los siguientes objetos: Cuenta.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
numerotarjeta|Path|Número del objeto Tarjerta a consultar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Tarjeta en [representaciones Detail](#recurso-tarjeta)
404|No existe un objeto Tarjeta con el número solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error


#### GET tarjetas/{numerotarjeta}/cuenta

Retorna a la Cuenta asociada a un objeto Tarjeta en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
numerotarjeta|Path|Número del objeto Tarjeta a consultar|Sí|Long

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Cuenta en [representación Detail](#recurso-estudiante)
500|Error consultando estudiante|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### POST /tarjetas

Es el encargado de crear objetos Tarjeta.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Tarjeta que será creado|Sí|[Representación Detail](#recurso-tarjeta)


#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Tarjeta ha sido creado|[Representación Detail](#recurso-tarjeta)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Tarjeta|Mensaje de error

#### DELETE /tarjetas/{numerotarjeta}

Elimina un objeto tarjeta.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
numerotarjeta|Path|Número del objeto Tarjeta a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### PUT /tarjetas/{numerotarjeta}

Es el encargado de actualizar objetos Tarjeta.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
numerotarjeta|Path|Número del objeto Tarjeta a actualizar|Sí|Integer
body|body|Objeto Tarjeta nuevo|Sí|[Representación Detail](#recurso-tarjeta)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Tarjeta actualizado|[Representación Detail](#recurso-tarjeta)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Tarjeta|Mensaje de error
[Volver arriba](#tabla-de-contenidos)

# Tabla de contenidos

-  [API](#api-de-la-aplicación-books)
  - [Recurso Torre](#recurso-torre)
    - [GET /torres](#GET-/torres)
    - [GET /torres/{id}](#GET-/torres/{id})
    - [POST /torres](#POST-/torres)
    - [PUT /torres/{id}](#PUT-/torres/{id})
    - [DELETE /torres/{id}](#DELETE-/torres/{id})
### Recurso Torre
El objeto Piso tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    pisos: {
    id: '' /*Tipo Long*/,
        apartamentos: {
        id: '' /*Tipo Long*/,
     }
    }
}
```
#### GET /Torres

Retorna una colección de objetos Torre.
#### Parámetros

#### N/A
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Torre
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### GET /torres/{id}

Retorna una colección de objetos Torre.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Torre a consultar|Sí|Integer
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Torre 
404|No existe un objeto Torre con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### POST /torres

Es el encargado de crear objetos Torre.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Torre que será creado|Sí|[Representación](#recurso-torre)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Torre ha sido creado|[Representación](#recurso-torre)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Torre|Mensaje de error
#### PUT /torres/{id}

Es el encargado de actualizar objetos Torre.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Torre a actualizar|Sí|Integer
body|body|Objeto Torre nuevo|Sí|[Representación](#recurso-torre)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Torre actualizado|[Representación](#recurso-torre)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Torre|Mensaje de error
#### DELETE /torres/{id}

Elimina un objeto Torre.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Torre a eliminar|Sí|Integer
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

# Tabla de contenidos
 - [Recurso Piso](#recurso-piso)
    - [GET /pisos](#GET-/pisos)
    - [GET /pisos/{id}](#GET-/pisos/{id})
    - [POST /pisos](#POST-/pisos)
    - [PUT /pisos/{id}](#PUT-/pisos/{id})
    - [DELETE /torres/{id}](#DELETE-/pisos/{id})

### Recurso Piso 
El objeto Piso tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    Torre: {
    id: '' /*Tipo Long*/,
        apartamentos: {
        id: '' /*Tipo Long*/,
     }
    }
}
```
#### GET /Pisos

Retorna una colección de objetos Piso.
#### Parámetros

#### N/A
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Piso
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### GET /pisos/{id}

Retorna una colección de objetos Piso.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Piso a consultar|Sí|Integer
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Piso
404|No existe un objeto Piso con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### POST /pisos

Es el encargado de crear objetos Piso.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Piso que será creado|Sí|[Representación](#recurso-piso)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Piso ha sido creado|[Representación](#recurso-piso)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Piso|Mensaje de error
#### PUT /pisos/{id}

Es el encargado de actualizar objetos Piso.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Piso a actualizar|Sí|Integer
body|body|Objeto Piso nuevo|Sí|[Representación](#recurso-piso)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Piso actualizado|[Representación](#recurso-piso)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Piso|Mensaje de error
#### DELETE /torres/{id}

Elimina un objeto Piso.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Piso a eliminar|Sí|Integer
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

# Tabla de contenidos
 - [Recurso Apartamento](#recurso-apartamento)
    - [GET /apartamentos](#GET-/apartamentos)
    - [GET /apartamentos/{id}](#GET-/apartamentos/{id})
    - [POST /apartamentos](#POST-/apartamentos)
    - [PUT /apartamentos/{id}](#PUT-/apartamentos/{id})
    - [DELETE /apartamentos/{id}](#DELETE-/apartamentos/{id})
### Recurso Apartamento
El objeto Apartamento tiene 2 representaciones JSON:	
#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    disponible: '' /*Tipo Boleean*/,
    numDeApartamento: '' /*Tipo Integer*/
}
```
#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    Torre: {
        piso: {
        id: '' /*Tipo Long*/,
        }
    }
}
```
#### GET /Apartamentos

Retorna una colección de objetos Apartamento.
#### Parámetros

#### N/A
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Apartamento
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### GET /apartamentos/{id}

Retorna una colección de objetos Apartamento.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Apartamento a consultar|Sí|Integer
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Apartamento
404|No existe un objeto Apartamento con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error
#### POST /apartamentos

Es el encargado de crear objetos Apartamento.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Apartamento que será creado|Sí|[Representación](#recurso-apartamento)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Apartamento ha sido creado|[Representación](#recurso-apartamento)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Apartamento|Mensaje de error
#### PUT /apartamentos/{id}

Es el encargado de actualizar objetos Apartamento.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Apartamento a actualizar|Sí|Integer
body|body|Objeto apartamento nuevo|Sí|[Representación](#recurso-apartamento)
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Apartamento actualizado|[Representación](#recurso-apartamento)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Apartamento|Mensaje de error
#### DELETE /apartamentos/{id}

Elimina un objeto Apartamento.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Apartamento a eliminar|Sí|Integer
#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
