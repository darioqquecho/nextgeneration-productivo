# Versionamiento Funcional

El versionamiento evita forks por cliente sin crear una clase versionadora por cada caso de uso.

## Regla

Cada caso de uso puede tener implementaciones concretas:

```text
MiCasoUsoV1UseCase
MiCasoUsoV2UseCase
```

El wiring del modulo registra esas versiones con el proxy global:

```java
return RoyalFunctionalVersionProxyFactory.builder(MiCasoUsoV1UseCase.class, resolver)
        .register(FunctionalVersion.V1, v1)
        .register(FunctionalVersion.V2, v2)
        .build();
```

El controller inyecta el tipo base `MiCasoUsoV1UseCase`. El proxy resuelve la version real usando `FunctionalContext`.

## Decision Por Cliente

La version se resuelve por:

```text
clientId
module
useCase
requestedVersion
```

Esto permite que dos clientes usen versiones distintas sin duplicar codigo.
