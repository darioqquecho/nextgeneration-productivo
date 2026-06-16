# MIGRATION_ALERTAS_ASSESSMENT.md

## Resultado

Migración funcional productiva inicial del módulo `royal-alertas-api` hacia la nueva arquitectura:

```text
royal-alertas-domain
royal-alertas-application
royal-alertas-infrastructure
royal-alertas-api
royal-alertas-bootstrap
royal-alertas-war
```

## Inventario detectado del ZIP legacy

| Tipo | Cantidad |
|---|---:|
| Clases Java | 309 |
| Controllers REST | 41 |
| Endpoints REST detectados | 335 |
| Entidades JPA migradas a metadata | 38 |
| SQL HBM XML | 40 |
| Named queries SQL HBM | 129 |
| DAOs legacy | 38 |
| Services legacy | 38 |
| Validadores legacy | 38 |

## Estrategia aplicada

1. Se migraron las entidades JPA legacy a `AlertasMetadataCatalog`.
2. Se implementó CRUD SQL Server real sobre las 38 tablas detectadas.
3. Se copiaron los 40 archivos `*.sql.hbm.xml` y se cargan con `SqlHbmCatalog`.
4. Se implementó `SqlServerNamedQueryRepository` para ejecutar named queries legacy.
5. Se implementó compatibilidad REST legacy `/spring/alertas/**`.
6. Se implementó API nueva `/api/alertas/**`.
7. Se implementó JAR, WAR/Tomcat, Dockerfile y Kubernetes.
8. Se preservó el código legacy completo en `docs/migration/alertas/legacy-source`.

## Límites honestos

El módulo legacy depende de clases propietarias antiguas (`royal-framework-core`, `GenericoRest`, `GenericoDaoImpl`, etc.). Para entregar un producto compilable y funcional bajo la nueva arquitectura, no se copiaron esas clases como dependencias directas. Se migró el comportamiento operativo a puertos/adapters nuevos usando SQL Server y metadatos generados.

