# Versionamiento Funcional

El versionamiento existe para evitar forks por cliente.

## Regla

Cada caso de uso puede tener versiones:

```text
MiCasoUsoV1UseCase
MiCasoUsoV2UseCase
MiCasoUsoVersionedUseCase
```

El router debe heredar de:

```text
RoyalBaseVersionedUseCase
```

y usar:

```text
ModuleVersionRouter
```

## Ejemplo

```java
super(new ModuleVersionRouter<MiCasoUsoUseCase>(resolver)
        .register(FunctionalVersion.V1, v1)
        .register(FunctionalVersion.V2, v2));
```

## Decision Por Cliente

La version se resuelve por `FunctionalContext` usando:

```text
clientId
module
useCase
requestedVersion
```

Esto permite que dos clientes usen versiones distintas sin duplicar codigo.

