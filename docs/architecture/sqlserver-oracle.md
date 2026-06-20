# SQL Server Y Oracle

Los motores se mantienen separados.

## Regla

No reutilizar el mismo archivo SQL entre SQL Server y Oracle.

```text
royal-<modulo>-infrastructure/src/main/resources/sql/<modulo>/<proceso>/*
royal-<modulo>-infrastructure/src/main/resources/oracle/<modulo>/<proceso>/*
```

Ejemplo:

```text
sql/hr/maestros/mantenimientoparametros/parametros.v1.sql
oracle/hr/maestros/mantenimientoparametros/parametros.v1.sql
```

## Adapters

```text
sqlserver -> SqlServer<CasoUso>Adapter
oracle    -> Oracle<CasoUso>Adapter
```

Los adapters SQL deben heredar de:

```text
RoyalBaseSqlAdapter
```

