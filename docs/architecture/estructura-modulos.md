# Estructura De Modulos Y Procesos

La organizacion debe seguir el lenguaje funcional del ERP.

## Modulos Maven

```text
royal-<modulo>-domain
royal-<modulo>-application
royal-<modulo>-infrastructure
royal-<modulo>-api
royal-<modulo>-bootstrap
royal-<modulo>-war
```

## Paquetes Por Proceso

Ejemplo RRHH:

```text
hr/application/maestros/parametro
hr/application/maestros/tiposeguro
hr/application/capacitacion/registrar
hr/application/requerimiento/aprobar
```

La API y la infraestructura deben reflejar el mismo proceso:

```text
hr/api/v1/maestros/parametro
hr/api/v1/capacitacion/registrar
hr/api/v1/requerimiento/aprobar
hr/infrastructure/maestros/parametro/sqlserver
hr/infrastructure/maestros/parametro/oracle
hr/infrastructure/capacitacion/registrar
hr/infrastructure/requerimiento/aprobar
```

## Catalogo Funcional

Cada modulo debe tener un catalogo de procesos y casos de uso. En RRHH esta en:

```text
hr/application/process/RrhhProcessCatalog
```

Ese catalogo es la fuente unica para nombres funcionales usados por controllers,
observabilidad, auditoria, permisos y analisis de codigo.

## Regla

No crear paquetes tecnicos planos con muchos casos de uso mezclados. Primero va el proceso funcional, luego el caso de uso.

Tambien esta prohibido crear controllers directamente en `hr/api`. Todo controller
debe vivir debajo de `hr/api/v<version>/<proceso>/<caso-uso>`.
