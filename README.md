# Royal ERP Next Framework v1.0

Baseline productivo inicial para el nuevo framework ERP Royal.

## Alcance Nivel C

Incluye código fuente Java para cubrir los requisitos de:

- ARCHITECTURE.md
- AS_IS_ARCHITECTURE.md
- MODULES.md

## Casos de uso implementados

- CU-001 CRUD completo `HR_Parametros`
- CU-002 RegistrarCapacitacion con V1/V2
- CU-003 AprobarRequerimientoPersonal con V1/V2 e integración por puerto con Aprobaciones
- CU-004 Login
- CU-005 ConsultarAuditoria

## Ejecución JAR

```bash
mvn clean install
mvn spring-boot:run -pl royal-rrhh-bootstrap
```

## Empaquetado WAR Tomcat

```bash
mvn clean package -pl royal-rrhh-war -am
```

El WAR queda en:

```text
royal-rrhh-war/target/royal-rrhh-api.war
```

## Kubernetes

```bash
kubectl apply -f royal-rrhh-bootstrap/k8s/deployment.yaml
```

## Persistencia configurable

```properties
royal.persistence.adapter=IN_MEMORY
```

Valores previstos:

```text
IN_MEMORY
SQL_SERVER
ORACLE
POSTGRESQL
MYSQL
```

## Versionamiento funcional por cliente

El mismo artefacto puede resolver distintas versiones por cliente, modulo y caso de uso desde un catalogo XML externo:

```properties
royal.functional-version.default=V1
royal.functional-version.catalog=classpath:functional-version-catalog.xml
```

Para produccion se puede apuntar a un archivo fuera del WAR:

```properties
royal.functional-version.catalog=file:/opt/royal/config/functional-version-catalog.xml
```

Formato del catalogo:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<functional-version-catalog>
	<mapping clientId="cliente-a" module="RRHH" useCase="CrearParametroUseCase" version="V1"/>
	<mapping clientId="cliente-b" module="RRHH" useCase="CrearParametroUseCase" version="V2"/>
</functional-version-catalog>
```

Tambien se puede forzar una version por request con el header HTTP:

```http
X-Functional-Version: V2
```

## VS Code

El proyecto incluye `.vscode` con extensiones recomendadas.

## Eclipse IDE

Importar como:

```text
File > Import > Maven > Existing Maven Projects
```


# Módulo Alertas Migrado

## Ejecución JAR

```bash
mvn clean install
mvn spring-boot:run -pl royal-alertas-bootstrap
```

## WAR Tomcat

```bash
mvn clean package -pl royal-alertas-war -am
```

## Endpoints principales

```http
GET    /api/alertas/{entityName}
POST   /api/alertas/{entityName}
PUT    /api/alertas/{entityName}
POST   /api/alertas/{entityName}/obtener
PUT    /api/alertas/{entityName}/anular
DELETE /api/alertas/{entityName}
POST   /api/alertas/sql/{queryName}
ANY    /spring/alertas/{resource}/**
```

## SQL Server

Configurar:

```properties
spring.datasource.url=jdbc:sqlserver://servidor:1433;databaseName=SGALERTASSYS;encrypt=true;trustServerCertificate=true
spring.datasource.username=usuario
spring.datasource.password=clave
```
