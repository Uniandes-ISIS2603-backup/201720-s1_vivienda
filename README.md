# s1_vivienda
Repositorio del proyecto de vivienda universitaria del grupo 4 de la sección 1

UNIT NAME = viviendaPU

# Tabla de contenidos
- [Recurso Cuenta](#recurso-cuenta)
 - [GET /cuentas](#GET-/cuentas)
 - [GET /cuentas/{id}](#GET-/cuentas/{id})
 - [POST /cuentas](#POST-/cuentas)
 - [PUT /cuentas/{id}](#PUT-/cuentas/{id})
 - [DELETE /cuentas/{id}](#DELETE-/cuentas/{id})
 - [GET cuentas/{cuentasid}/estudiante](#GET-cuentas/{cuentasid}/estudiante)
 - [GET cuentas/{cuentasid}/pagos](#GET-cuentas/{cuentasid}/pagos)
 - [GET cuentas/{cuentasid}/servicios](#GET-cuentas/{cuentasid}/ordenPagos)
 - [GET cuentas/{cuentasid}/tarjetas](#GET-cuentas/{cuentasid}/tarjetas)
 
- [Recurso Pago](#recurso-pago)
 - [GET /pagos](#GET-/pagos)
 - [GET /pagos/{numeropago}](#GET-/pagos/{numeropago})
 - [GET pagos/{pagosnumeropago}/cuenta](#GET-pagos/{pagosnumeropago}/cuenta)
 - [GET pagos/{pagosnumeropago}/ordenPagos](#GET-pagos/{pagosnumeropago}/ordenPagos)
 - [POST /pagos](#POST-/pagos)
 - [PUT /pagos/{numeropago}](#PUT-/pagos/{numeropago})
 - [DELETE /pagos/{numeropago}](#DELETE-/pagos/{numeropago})

- [Recurso Tarjeta](#recurso-tarjeta)
 - [GET /tarjetas](#GET-/tarjetas)
 - [GET /tarjetas/{numerotarjeta}}](#GET-/tarjetas/{numerotarjeta})
 - [GET tarjetas/{numerotarjeta}/cuenta](#GET-tarjetas/{numerotarjeta}/cuenta)
 - [POST /tarjetas](#POST-/tarjetas)
 - [DELETE /tarjetas/{numerotarjeta}](#DELETE-/tarjetas/{numerotarjeta})
 - [PUT /tarjetas/{numerotarjeta}](#PUT-/tarjetas/{numerotarjeta})

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

#### GET cuentas/{cuentasid}/pagos

Retorna una colección de objetos Pago asociados a un objeto Cuenta en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Cuenta a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Pago en [representación Detail](#recurso-pago)
500|Error consultando pagos|Mensaje de error
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

### Recurso Pago
El objeto Pago tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    numeroPago: '' /*Tipo Integer*/,
    totalPagado: '' /*Tipo Double*/,
}
```

#### Representación Detail
```javascript
{
    numeroPago: '' /*Tipo Integer*/,
    totalPagado: '' /*Tipo Double*/,
    cuenta: { /*Cuenta en su representación JSON Minimum*/ },
    ordenPagos: [
         { /*OrdenPago 1 en su representación JSON Minimum*/ },
         ...
         { /*OrdenPago n en su representación JSON Minimum*/ }
    ]
}
```
#### GET /pagos

Retorna una colección de objetos Pago en representación Detail.
Cada Pago en la colección tiene embebidos los siguientes objetos: Cuenta, servicios.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-pago)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error

#### GET /pagos/{numeropago}

Retorna un objeto Pago con la id proporcionada (si existe) en representación Detail.
El Pago tiene embebidos los siguientes objetos: Cuenta, servicios.
#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
numeropago|Path|Número del objeto Pago a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Pago en [representaciones Detail](#recurso-cuenta)
404|No existe un objeto Pago con el ID solicitado|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|Error interno|Mensaje de error


#### GET pagos/{pagosnumeropago}/cuenta

Retorna a la Cuenta asociada a un objeto Pago en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
pagosnumeropago|Path|Número del objeto Pago a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Cuenta en [representación Detail](#recurso-estudiante)
500|Error consultando estudiante|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### GET pagos/{pagosnumeropago}/ordenPagos

Retorna una colección de objetos ordenPago asociados a un objeto Pago en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
pagosnumeropago|Path|Número del objeto Pago a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos OrdenPago en [representación Detail](#recurso-OrdenPago)
500|Error consultando ordenes de pago|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error

#### POST /pagos

Es el encargado de crear objetos Pago.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Pago que será creado|Sí|[Representación Detail](#recurso-pago)


#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Pago ha sido creado|[Representación Detail](#recurso-pago)
412|precondition failed, no se cumple la regla de negocio establecida|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo crear el objeto Cuenta|Mensaje de error

#### PUT /pagos/{numeropago}

Es el encargado de actualizar objetos Pago.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
numeropago|Path|Número del objeto Cuenta a actualizar|Sí|Integer
body|body|Objeto Pago nuevo|Sí|[Representación Detail](#recurso-pago)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Pago actualizado|[Representación Detail](#recurso-pago)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Pago|Mensaje de error

#### DELETE /pagos/{numeropago}

Elimina un objeto Pago.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
numeropago|Path|ID del objeto Pago a eliminar|Sí|Integer

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
201|El objeto Tarjeta actualizado|[Representación Detail](#tarjeta-book)
412|business exception, no se cumple con las reglas de negocio|Mensaje de error
405|method not allowed, no existe permiso para el recurso|Mensaje de error
500|No se pudo actualizar el objeto Tarjeta|Mensaje de error


[Volver arriba](#tabla-de-contenidos)
