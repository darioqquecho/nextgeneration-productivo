# Seguridad OWASP

Esta base aplica controles iniciales para endurecer APIs:

- API key configurable para APIs internas.
- Headers HTTP seguros por defecto.
- CORS cerrado por defecto y habilitable solo por allowlist.
- Rate limit simple por cliente/IP.
- Validacion de DTOs HTTP con Bean Validation.
- Errores de validacion sin stack trace ni detalles tecnicos.

## Propiedades

```properties
royal.security.api-key.enabled=true
royal.security.api-key.value=${ROYAL_SECURITY_API_KEY}
royal.security.public-paths=/actuator/health,/actuator/info
royal.security.rate-limit.enabled=true
royal.security.rate-limit.requests-per-minute=120
royal.security.cors.allowed-origins=https://erp.empresa.com
```

## Pendiente Para Pentest Formal

- JWT/OAuth2 con firma y expiracion.
- Autorizacion por recurso para evitar BOLA/IDOR.
- SAST/SCA/DAST en pipeline.
- Pruebas OWASP ZAP sobre endpoints desplegados.
- Politica de secretos y rotacion.
