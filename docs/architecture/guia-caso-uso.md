# Guia Para Crear Casos De Uso

Esta guia define la forma estandar de crear casos de uso en el ERP. El objetivo es evitar copias por cliente, mantener orden funcional y reducir codigo repetido.

## Regla Principal

La regla de negocio vive en el caso de uso concreto. La infraestructura repetida vive en clases maestras.

## Estructura Esperada

```text
royal-<modulo>-application
  src/main/java/.../<modulo>/application/<proceso>/<casouso>
    <CasoUso>UseCase.java
    <CasoUso>V1UseCase.java
    <CasoUso>V2UseCase.java
    <CasoUso>VersionedUseCase.java
    Observed<CasoUso>UseCase.java
    dto
    port
```

Ejemplo canonico:

```text
hr/application/maestros/parametro
```

## Herencia Obligatoria

```text
Controller              -> RoyalBaseController
UseCase con negocio     -> RoyalBaseUseCase
UseCase delegador/V2    -> RoyalDelegatingUseCase o una clase base funcional
UseCase versionado      -> RoyalBaseVersionedUseCase
UseCase observado       -> RoyalObservedUseCase
Adapter SQL             -> RoyalBaseSqlAdapter
REST entre modulos      -> RestApiClient
```

## Prohibido En Casos De Uso Concretos

Evitar repetir directamente:

```text
guards.check(...)
new FunctionalAuditRecord(...)
ExecutionTimer.start()
```

Usar:

```text
checkGuards(context)
auditOk(context, entityId)
audit(context, result, entityId, message)
observe(context, () -> ...)
```

## Patron De Caso De Uso

```java
public class MiCasoUsoV1UseCase extends RoyalBaseUseCase implements MiCasoUsoUseCase {
    public MiCasoUsoV1UseCase(MiRepository repository, UseCaseGuards guards, AuditPort auditPort) {
        super("HR", "MiEntidad", "V1", guards, auditPort);
        this.repository = repository;
    }

    public MiResult ejecutar(MiCommand command, FunctionalContext context) {
        checkGuards(context);

        // regla de negocio

        auditOk(context, "ID");
        return result;
    }
}
```

