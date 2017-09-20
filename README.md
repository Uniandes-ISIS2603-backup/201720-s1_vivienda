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


[Volver arriba](#tabla-de-contenidos)
