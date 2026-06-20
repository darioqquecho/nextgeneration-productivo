# REST Entre Modulos

Las llamadas de un modulo a otra API deben pasar por un puerto de application y un adapter REST de infrastructure.

## Regla

El caso de uso no llama controllers externos directamente.

```text
UseCase -> Port -> RestAdapter -> RestApiClient -> API externa
```

## Cliente Comun

Usar:

```text
RestApiClient
```

Este cliente maneja:

```text
JSON
headers funcionales
traceId
requestId
tenantId
clientId
timeouts
errores estandar
```

Ejemplo real:

```text
ConsultaPermisoPort
RestConsultaPermisoAdapter
/api/autenticacion/obtenerpermiso
```

