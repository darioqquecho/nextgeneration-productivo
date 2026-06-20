# Arquitectura Multicliente

El objetivo es evitar una copia de la aplicacion por cliente. La variacion por
cliente debe vivir en configuracion, no en forks de codigo.

## Datos Que Viajan En Cada Request

```text
X-Tenant-Id
X-Client-Id
X-User-Id
X-Functional-Version
```

Si los headers no llegan, la API usa defaults configurables:

```properties
royal.context.default-tenant
royal.context.default-client
royal.context.default-user
```

## Catalogo De Clientes

Cada API puede cargar un catalogo externo:

```properties
royal.client.catalog=classpath:client-catalog.xml
```

En Tomcat o Kubernetes debe apuntar a un archivo externo:

```properties
royal.client.catalog=file:/etc/royal/rrhh/client-catalog.xml
```

Ejemplo:

```xml
<client tenantId="tenant-acme" clientId="acme">
	<module code="HR"/>
	<property name="persistence.adapter" value="SQL_SERVER"/>
</client>
```

El catalogo permite centralizar:

- modulos licenciados por cliente
- propiedades tecnicas por cliente
- decisiones futuras de datasource, flags o integraciones

## Regla

No crear ramas, proyectos o paquetes por cliente. Si cambia una configuracion,
va al catalogo. Si cambia una regla funcional, debe resolverse con versionamiento
o extension controlada del caso de uso.

## Seguridad

Los valores de seguridad, como `ROYAL_SECURITY_API_KEY`, deben venir de Secret o
variables del entorno. Nunca deben copiarse por cliente dentro del codigo fuente.
