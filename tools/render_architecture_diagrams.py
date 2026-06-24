from pathlib import Path
from textwrap import dedent

from PIL import Image, ImageDraw, ImageFont


OUT = Path("docs/architecture")


def font(size, bold=False):
    candidates = [
        "C:/Windows/Fonts/segoeuib.ttf" if bold else "C:/Windows/Fonts/segoeui.ttf",
        "C:/Windows/Fonts/arialbd.ttf" if bold else "C:/Windows/Fonts/arial.ttf",
    ]
    for path in candidates:
        try:
            return ImageFont.truetype(path, size)
        except OSError:
            pass
    return ImageFont.load_default()


def wrap(draw, value, fnt, width):
    words = value.split()
    lines = []
    current = ""
    for word in words:
        test = f"{current} {word}".strip()
        if current and draw.textlength(test, font=fnt) > width:
            lines.append(current)
            current = word
        else:
            current = test
    if current:
        lines.append(current)
    return lines


COLORS = {
    "bg": "#f7fafc",
    "title": "#0b2b5c",
    "muted": "#486171",
    "border": "#b7cad8",
    "blue": "#2364aa",
    "blue_fill": "#eef6ff",
    "green": "#248a50",
    "green_fill": "#effaf4",
    "yellow": "#c98116",
    "yellow_fill": "#fff8e6",
    "red": "#b42318",
    "red_fill": "#fff1f1",
    "purple": "#6d4aff",
    "purple_fill": "#f2f0ff",
    "gray": "#6c8798",
    "white": "#ffffff",
    "text": "#243746",
}


def svg_header(width, height, title, desc):
    return f"""<svg xmlns="http://www.w3.org/2000/svg" width="{width}" height="{height}" viewBox="0 0 {width} {height}" role="img" aria-labelledby="title desc">
  <title id="title">{title}</title>
  <desc id="desc">{desc}</desc>
  <defs>
    <style>
      .bg {{ fill: {COLORS['bg']}; }}
      .title {{ fill: {COLORS['title']}; font: 700 44px Arial, sans-serif; }}
      .subtitle {{ fill: {COLORS['muted']}; font: 400 22px Arial, sans-serif; }}
      .band-title {{ fill: {COLORS['title']}; font: 700 21px Arial, sans-serif; }}
      .box-title {{ fill: {COLORS['title']}; font: 700 19px Arial, sans-serif; }}
      .text {{ fill: {COLORS['text']}; font: 400 16px Arial, sans-serif; }}
      .small {{ fill: {COLORS['muted']}; font: 400 14px Arial, sans-serif; }}
      .tiny {{ fill: {COLORS['muted']}; font: 400 12px Arial, sans-serif; }}
      .layer {{ fill: #ffffff; stroke: {COLORS['border']}; stroke-width: 2; rx: 16; }}
      .box {{ fill: #ffffff; stroke: {COLORS['blue']}; stroke-width: 2.2; rx: 10; }}
      .box-blue {{ fill: {COLORS['blue_fill']}; stroke: {COLORS['blue']}; stroke-width: 2.2; rx: 10; }}
      .box-green {{ fill: {COLORS['green_fill']}; stroke: {COLORS['green']}; stroke-width: 2.2; rx: 10; }}
      .box-yellow {{ fill: {COLORS['yellow_fill']}; stroke: {COLORS['yellow']}; stroke-width: 2.2; rx: 10; }}
      .box-purple {{ fill: {COLORS['purple_fill']}; stroke: {COLORS['purple']}; stroke-width: 2.2; rx: 10; }}
      .box-red {{ fill: {COLORS['red_fill']}; stroke: {COLORS['red']}; stroke-width: 2.2; rx: 10; }}
      .shadow {{ filter: drop-shadow(0 8px 18px rgba(11, 43, 92, 0.12)); }}
      .arrow {{ stroke: #0b4d8b; stroke-width: 4; fill: none; marker-end: url(#arrow); }}
      .arrow-soft {{ stroke: {COLORS['gray']}; stroke-width: 3; fill: none; stroke-dasharray: 8 7; marker-end: url(#arrow-soft); }}
      .return {{ stroke: {COLORS['green']}; stroke-width: 4; fill: none; marker-end: url(#arrow-green); }}
      .pill {{ fill: {COLORS['title']}; }}
      .pill-text {{ fill: #ffffff; font: 700 15px Arial, sans-serif; }}
    </style>
    <marker id="arrow" markerWidth="12" markerHeight="10" refX="10" refY="5" orient="auto">
      <polygon points="0 0, 12 5, 0 10" fill="#0b4d8b"/>
    </marker>
    <marker id="arrow-soft" markerWidth="12" markerHeight="10" refX="10" refY="5" orient="auto">
      <polygon points="0 0, 12 5, 0 10" fill="{COLORS['gray']}"/>
    </marker>
    <marker id="arrow-green" markerWidth="12" markerHeight="10" refX="10" refY="5" orient="auto">
      <polygon points="0 0, 12 5, 0 10" fill="{COLORS['green']}"/>
    </marker>
  </defs>
"""


def svg_box(x, y, w, h, cls, title, lines):
    out = [f'  <rect class="{cls} shadow" x="{x}" y="{y}" width="{w}" height="{h}"/>']
    out.append(f'  <text class="box-title" x="{x + 22}" y="{y + 32}">{title}</text>')
    yy = y + 60
    for line in lines:
        out.append(f'  <text class="text" x="{x + 22}" y="{yy}">{line}</text>')
        yy += 25
    return "\n".join(out)


def svg_pill(x, y, label):
    return (
        f'  <rect class="pill" x="{x}" y="{y}" width="34" height="24" rx="12"/>\n'
        f'  <text class="pill-text" x="{x + 9}" y="{y + 17}">{label}</text>'
    )


def render_capas_svg():
    parts = [
        svg_header(
            2100,
            1280,
            "Capas y dependencias actuales",
            "Arquitectura actual de RRHH y Autenticacion: modulos Maven, framework compartido, puertos, adapters, SQL Server, Oracle e integracion REST.",
        ),
        '  <rect class="bg" x="0" y="0" width="2100" height="1280"/>',
        '  <text class="title" x="70" y="70">Capas y dependencias actuales</text>',
        '  <text class="subtitle" x="70" y="106">Clean Architecture pragmatica + puertos/adapters + Maven multimodulo</text>',
        '  <rect class="layer shadow" x="55" y="150" width="1990" height="155"/>',
        '  <text class="band-title" x="85" y="190">Canales de entrada</text>',
        svg_box(110, 215, 330, 65, "box", "Frontend / sistemas externos", ["HTTP REST + headers funcionales"]),
        svg_box(520, 205, 395, 85, "box-yellow", "Filtros web compartidos", ["ApiKeyAuthenticationFilter", "RateLimitFilter, CORS, security headers"]),
        svg_box(995, 205, 415, 85, "box-blue", "royal-framework-web", ["RoyalResponse, errores estandar", "FunctionalContextFactory, RestApiClient"]),
        '  <path class="arrow" d="M440 248 H510"/>',
        '  <path class="arrow" d="M915 248 H985"/>',
        '  <rect class="layer shadow" x="55" y="335" width="960" height="620"/>',
        '  <text class="band-title" x="85" y="375">Modulo RRHH</text>',
        svg_box(95, 415, 370, 120, "box-blue", "royal-rrhh-api", ["Controllers REST V1", "MantenimientoParametro, TipoSeguro", "Reporte, aprobacion masiva, capacitacion"]),
        svg_box(95, 575, 370, 140, "box-green", "royal-rrhh-application", ["Use cases V1/V2 + commands/results", "Ports por caso de uso", "Functional version proxy + guards"]),
        svg_box(95, 755, 370, 110, "box", "royal-rrhh-domain", ["Parametro, TipoSeguro", "Capacitacion, Requerimiento", "Sin Spring ni JDBC"]),
        svg_box(575, 515, 365, 185, "box-purple", "royal-rrhh-infrastructure", ["Adapters SQL Server y Oracle", "Adapters InMemory demo/test", "RestConsultaPermisoAdapter", "JasperReporteParametrosDocumentGenerator"]),
        svg_box(575, 755, 365, 145, "box-yellow", "royal-rrhh-bootstrap / war", ["Spring Boot wiring", "royal.persistence.adapter", "client-catalog.xml, functional-version-catalog.xml", "JAR, WAR, Docker, Kubernetes"]),
        '  <path class="arrow" d="M280 535 V565"/>',
        '  <path class="arrow" d="M280 715 V745"/>',
        '  <path class="arrow" d="M465 635 H565"/>',
        '  <path class="arrow-soft" d="M760 755 V710"/>',
        '  <path class="arrow-soft" d="M575 820 H475"/>',
        '  <rect class="layer shadow" x="1085" y="335" width="460" height="620"/>',
        '  <text class="band-title" x="1115" y="375">Modulo Autenticacion</text>',
        svg_box(1125, 415, 380, 100, "box-blue", "royal-autenticacion-api", ["LoginController", "ObtenerPermisoController"]),
        svg_box(1125, 555, 380, 115, "box-green", "royal-autenticacion-application", ["LoginSimpleUseCase", "ObtenerPermisoSimpleUseCase", "PermissionChecker"]),
        svg_box(1125, 710, 380, 90, "box", "royal-autenticacion-domain", ["Modelo de autenticacion", "Sin dependencias web"]),
        svg_box(1125, 835, 380, 80, "box-yellow", "royal-autenticacion-bootstrap", ["Spring Boot + framework-security"]),
        '  <path class="arrow" d="M1315 515 V545"/>',
        '  <path class="arrow" d="M1315 670 V700"/>',
        '  <path class="arrow-soft" d="M940 610 C1020 570, 1055 470, 1115 470"/>',
        '  <text class="small" x="960" y="575">REST permisos / X-Api-Key</text>',
        '  <rect class="layer shadow" x="1615" y="335" width="430" height="620"/>',
        '  <text class="band-title" x="1645" y="375">Persistencia e integraciones</text>',
        svg_box(1655, 415, 350, 100, "box-purple", "Adapter configurable", ["IN_MEMORY, SQL_SERVER, ORACLE", "Resuelto en bootstrap por propiedad"]),
        svg_box(1655, 555, 350, 115, "box-blue", "SQL resources", ["sql/hr/maestros/*", "oracle/hr/maestros/*", "JDBC support por motor"]),
        svg_box(1655, 710, 350, 100, "box", "Bases de datos", ["SQL Server / Oracle", "HR_Parametros, HR_TipoSeguro"]),
        svg_box(1655, 850, 350, 80, "box-green", "Observabilidad / auditoria", ["Actuator + Prometheus", "Console/JDBC audit"]),
        '  <path class="arrow" d="M940 570 C1180 330, 1560 340, 1650 465"/>',
        '  <path class="arrow" d="M1830 515 V545"/>',
        '  <path class="arrow" d="M1830 670 V700"/>',
        '  <rect class="layer shadow" x="55" y="995" width="1990" height="210"/>',
        '  <text class="band-title" x="85" y="1035">Framework tecnico compartido</text>',
    ]
    framework = [
        ("Kernel", "ClientCatalog, BusinessException"),
        ("Web", "Response, filtros, REST client"),
        ("Security", "UseCaseGuards, PermissionChecker"),
        ("Application", "RoyalBaseUseCase, version proxy"),
        ("Audit", "Console/JDBC/Composite"),
        ("Versioning", "FunctionalVersionResolver"),
        ("Licensing", "ClientCatalogLicenseChecker"),
        ("Observability", "Micrometer, timers"),
        ("Database", "PersistenceAdapterResolver"),
    ]
    x = 95
    for title, line in framework:
        parts.append(svg_box(x, 1070, 200, 85, "box-blue", title, [line]))
        x += 215
    parts.extend(
        [
            '  <rect class="box-red shadow" x="55" y="1220" width="1990" height="40"/>',
            '  <text class="text" x="85" y="1246">Regla de dependencia: API e Infrastructure apuntan hacia Application/Domain. Application usa ports; Domain no conoce Spring, JDBC, REST ni framework web.</text>',
            "</svg>",
        ]
    )
    (OUT / "capas-dependencias-rrhh.svg").write_text("\n".join(parts), encoding="utf-8")


def render_flujo_svg():
    parts = [
        svg_header(
            1900,
            1220,
            "Flujo de peticion en RRHH",
            "Flujo actual de una peticion REST RRHH: filtros web, controller, contexto funcional, versionamiento, caso de uso, ports, adapters, base de datos y API de autenticacion.",
        ),
        '  <rect class="bg" x="0" y="0" width="1900" height="1220"/>',
        '  <text class="title" x="70" y="70">Flujo de peticion en RRHH</text>',
        '  <text class="subtitle" x="70" y="106">Ejemplo: mantenimiento/reporte/aprobacion de parametros, tipo seguro, capacitacion o requerimiento</text>',
    ]
    bands = [
        (145, 140, "1. Entrada"),
        (325, 190, "2. API + seguridad web"),
        (555, 245, "3. Application"),
        (840, 235, "4. Infrastructure + datos"),
    ]
    for y, h, label in bands:
        parts.append(f'  <rect class="layer shadow" x="55" y="{y}" width="1790" height="{h}"/>')
        parts.append(f'  <text class="band-title" x="85" y="{y + 35}">{label}</text>')

    parts.extend(
        [
            svg_box(110, 205, 280, 60, "box", "Usuario / sistema", ["Accion o integracion REST"]),
            svg_box(495, 190, 450, 90, "box", "Request HTTP", ["GET / POST / PUT / DELETE", "X-Client-Id, X-User-Id, X-Api-Key"]),
            svg_box(1090, 190, 360, 90, "box-blue", "Endpoint versionado", ["/api/v1/hr/...", "Contratos OpenAPI en docs/openapi"]),
            '  <path class="arrow" d="M390 235 H485"/>',
            '  <path class="arrow" d="M945 235 H1080"/>',
            svg_box(110, 390, 330, 95, "box-yellow", "Filtros OWASP", ["API key, rate limit, CORS", "Security headers"]),
            svg_box(520, 380, 330, 110, "box-blue", "Controller REST V1", ["Mapeo HTTP a Command/Query", "@Valid y errores 400"]),
            svg_box(930, 380, 330, 110, "box-blue", "FunctionalContextFactory", ["Cliente, usuario, traceId", "Idioma y version funcional"]),
            svg_box(1340, 380, 330, 110, "box-blue", "RoyalResponse", ["ResponseBodyAdvice", "GlobalApiExceptionHandler"]),
            '  <path class="arrow" d="M1270 280 C1270 325, 275 325, 275 380"/>',
            '  <path class="arrow" d="M440 437 H510"/>',
            '  <path class="arrow" d="M850 437 H920"/>',
            '  <path class="arrow-soft" d="M1260 437 H1330"/>',
            svg_box(110, 625, 375, 120, "box-green", "Use case V1/V2", ["MantenimientoTablaParametros", "Reporte, aprobacion, TipoSeguro", "Capacitacion, Requerimiento"]),
            svg_box(555, 610, 345, 150, "box-green", "RoyalBaseUseCase", ["Guards de licencia/permisos", "Auditoria funcional", "Errores de negocio controlados"]),
            svg_box(970, 610, 345, 150, "box-purple", "Version router", ["FunctionalVersionResolver", "RoyalFunctionalVersionProxyFactory", "catalogo XML por cliente/caso"]),
            svg_box(1385, 610, 360, 150, "box-green", "Ports de salida", ["Repository por caso de uso", "ConsultaPermisoPort", "Aprobaciones/Capacitacion ports"]),
            '  <path class="arrow" d="M1095 490 C1095 540, 297 540, 297 615"/>',
            '  <path class="arrow" d="M485 682 H545"/>',
            '  <path class="arrow-soft" d="M900 682 H960"/>',
            '  <path class="arrow" d="M1315 682 H1375"/>',
            svg_box(110, 910, 330, 120, "box-yellow", "Adapter seleccionado", ["royal.persistence.adapter", "IN_MEMORY / SQL_SERVER / ORACLE", "Bean wiring en bootstrap"]),
            svg_box(500, 895, 330, 150, "box-purple", "Adapters JDBC", ["SQL Server y Oracle por version", "JdbcTemplate + SQL resources", "Jasper para reportes"]),
            svg_box(890, 895, 300, 150, "box", "Base de datos", ["HR_Parametros", "HR_TipoSeguro", "SQL Server u Oracle"]),
            svg_box(1250, 895, 300, 150, "box-blue", "API Autenticacion", ["ObtenerPermisoController", "X-Api-Key entre APIs"]),
            svg_box(1610, 895, 205, 150, "box-green", "Retorno", ["Result DTO", "HTTP 200/400/401/429/500"]),
            '  <path class="arrow" d="M1565 760 C1565 820, 275 820, 275 900"/>',
            '  <path class="arrow" d="M440 970 H490"/>',
            '  <path class="arrow" d="M830 970 H880"/>',
            '  <path class="arrow-soft" d="M1565 682 C1600 760, 1440 830, 1400 885"/>',
            '  <path class="return" d="M1190 1000 C1330 1060, 1500 1060, 1600 1000"/>',
            '  <path class="return" d="M1550 970 H1600"/>',
            '  <path class="return" d="M1712 895 C1712 790, 1765 790, 1765 437 C1765 350, 1665 350, 1545 380"/>',
            '  <rect class="box-red shadow" x="110" y="1110" width="1705" height="70"/>',
            '  <text class="box-title" x="140" y="1140">Regla clave</text>',
            '  <text class="text" x="265" y="1140">El caso de uso solo habla con ports. El motor de BD, Jasper y la API de autenticacion quedan en Infrastructure/Framework.</text>',
            '  <text class="small" x="265" y="1164">Los filtros web y RoyalResponse estandarizan seguridad, trazabilidad, respuestas y errores antes/despues del caso de uso.</text>',
            "</svg>",
        ]
    )
    (OUT / "flujo-peticion-caso-uso.svg").write_text("\n".join(parts), encoding="utf-8")


def draw_box(draw, xy, fill, outline, title, lines, fonts):
    x, y, w, h = xy
    draw.rounded_rectangle((x + 6, y + 8, x + w + 6, y + h + 8), radius=10, fill="#d8e2ea")
    draw.rounded_rectangle((x, y, x + w, y + h), radius=10, fill=fill, outline=outline, width=2)
    draw.text((x + 18, y + 14), title, font=fonts["box"], fill=COLORS["title"])
    yy = y + 42
    for line in lines:
        for wrapped in wrap(draw, line, fonts["text"], w - 36):
            draw.text((x + 18, yy), wrapped, font=fonts["text"], fill=COLORS["text"])
            yy += 19


def draw_arrow(draw, points, color="#0b4d8b", width=4):
    draw.line(points, fill=color, width=width, joint="curve")
    x1, y1 = points[-2]
    x2, y2 = points[-1]
    if abs(x2 - x1) >= abs(y2 - y1):
        sign = 1 if x2 >= x1 else -1
        head = [(x2, y2), (x2 - sign * 14, y2 - 7), (x2 - sign * 14, y2 + 7)]
    else:
        sign = 1 if y2 >= y1 else -1
        head = [(x2, y2), (x2 - 7, y2 - sign * 14), (x2 + 7, y2 - sign * 14)]
    draw.polygon(head, fill=color)


def render_png(path, width, height, title, subtitle, boxes, bands, arrows):
    img = Image.new("RGB", (width, height), COLORS["bg"])
    draw = ImageDraw.Draw(img)
    fonts = {
        "title": font(44, True),
        "sub": font(22),
        "band": font(21, True),
        "box": font(18, True),
        "text": font(14),
    }
    draw.text((70, 32), title, font=fonts["title"], fill=COLORS["title"])
    draw.text((70, 82), subtitle, font=fonts["sub"], fill=COLORS["muted"])
    for x, y, w, h, label in bands:
        draw.rounded_rectangle((x + 6, y + 8, x + w + 6, y + h + 8), radius=16, fill="#d8e2ea")
        draw.rounded_rectangle((x, y, x + w, y + h), radius=16, fill=COLORS["white"], outline=COLORS["border"], width=2)
        draw.text((x + 28, y + 28), label, font=fonts["band"], fill=COLORS["title"])
    for item in boxes:
        draw_box(draw, item[:4], item[4], item[5], item[6], item[7], fonts)
    for points, color in arrows:
        draw_arrow(draw, points, color)
    img.save(path)


def render_pngs():
    render_png(
        OUT / "capas-dependencias-rrhh.png",
        2100,
        1280,
        "Capas y dependencias actuales",
        "Clean Architecture pragmatica + puertos/adapters + Maven multimodulo",
        [
            (110, 215, 330, 65, COLORS["white"], COLORS["blue"], "Frontend / sistemas externos", ["HTTP REST + headers funcionales"]),
            (520, 205, 395, 85, COLORS["yellow_fill"], COLORS["yellow"], "Filtros web compartidos", ["API key, rate limit, CORS", "Security headers"]),
            (995, 205, 415, 85, COLORS["blue_fill"], COLORS["blue"], "royal-framework-web", ["RoyalResponse, errores", "FunctionalContext, RestApiClient"]),
            (95, 415, 370, 120, COLORS["blue_fill"], COLORS["blue"], "royal-rrhh-api", ["Controllers REST V1", "Parametro, TipoSeguro, Reporte", "Aprobacion, Capacitacion"]),
            (95, 575, 370, 140, COLORS["green_fill"], COLORS["green"], "royal-rrhh-application", ["Use cases V1/V2", "Commands/queries/results", "Ports + guards"]),
            (95, 755, 370, 110, COLORS["white"], COLORS["blue"], "royal-rrhh-domain", ["Parametro, TipoSeguro", "Capacitacion, Requerimiento", "Sin Spring/JDBC"]),
            (575, 515, 365, 185, COLORS["purple_fill"], COLORS["purple"], "royal-rrhh-infrastructure", ["SQL Server/Oracle adapters", "InMemory demo/test", "REST permisos", "Jasper reportes"]),
            (575, 755, 365, 145, COLORS["yellow_fill"], COLORS["yellow"], "royal-rrhh-bootstrap / war", ["Spring Boot wiring", "Adapter y catalogos XML", "JAR/WAR/Docker/K8s"]),
            (1125, 415, 380, 100, COLORS["blue_fill"], COLORS["blue"], "royal-autenticacion-api", ["LoginController", "ObtenerPermisoController"]),
            (1125, 555, 380, 115, COLORS["green_fill"], COLORS["green"], "royal-autenticacion-application", ["LoginSimpleUseCase", "ObtenerPermisoSimpleUseCase", "PermissionChecker"]),
            (1125, 710, 380, 90, COLORS["white"], COLORS["blue"], "royal-autenticacion-domain", ["Modelo de autenticacion", "Sin web"]),
            (1125, 835, 380, 80, COLORS["yellow_fill"], COLORS["yellow"], "royal-autenticacion-bootstrap", ["Spring Boot + security"]),
            (1655, 415, 350, 100, COLORS["purple_fill"], COLORS["purple"], "Adapter configurable", ["IN_MEMORY, SQL_SERVER, ORACLE", "Wiring en bootstrap"]),
            (1655, 555, 350, 115, COLORS["blue_fill"], COLORS["blue"], "SQL resources", ["sql/hr/maestros/*", "oracle/hr/maestros/*"]),
            (1655, 710, 350, 100, COLORS["white"], COLORS["blue"], "Bases de datos", ["SQL Server / Oracle", "HR_Parametros, HR_TipoSeguro"]),
            (1655, 850, 350, 80, COLORS["green_fill"], COLORS["green"], "Observabilidad / auditoria", ["Actuator + Prometheus", "Console/JDBC audit"]),
        ]
        + [
            (95 + 215 * i, 1070, 200, 85, COLORS["blue_fill"], COLORS["blue"], title, [line])
            for i, (title, line) in enumerate(
                [
                    ("Kernel", "ClientCatalog"),
                    ("Web", "Response/filtros"),
                    ("Security", "Guards/permisos"),
                    ("Application", "BaseUseCase/proxy"),
                    ("Audit", "Console/JDBC"),
                    ("Versioning", "Resolver XML"),
                    ("Licensing", "Licencias"),
                    ("Observability", "Micrometer"),
                    ("Database", "Adapter resolver"),
                ]
            )
        ],
        [
            (55, 150, 1990, 155, "Canales de entrada"),
            (55, 335, 960, 620, "Modulo RRHH"),
            (1085, 335, 460, 620, "Modulo Autenticacion"),
            (1615, 335, 430, 620, "Persistencia e integraciones"),
            (55, 995, 1990, 210, "Framework tecnico compartido"),
        ],
        [
            ([(440, 248), (510, 248)], "#0b4d8b"),
            ([(915, 248), (985, 248)], "#0b4d8b"),
            ([(280, 535), (280, 565)], "#0b4d8b"),
            ([(280, 715), (280, 745)], "#0b4d8b"),
            ([(465, 635), (565, 635)], "#0b4d8b"),
            ([(940, 570), (1180, 330), (1560, 340), (1650, 465)], "#0b4d8b"),
            ([(940, 610), (1115, 470)], COLORS["gray"]),
            ([(1830, 515), (1830, 545)], "#0b4d8b"),
            ([(1830, 670), (1830, 700)], "#0b4d8b"),
        ],
    )
    render_png(
        OUT / "flujo-peticion-caso-uso.png",
        1900,
        1220,
        "Flujo de peticion en RRHH",
        "Ejemplo: parametros, tipo seguro, capacitacion o requerimiento",
        [
            (110, 205, 280, 60, COLORS["white"], COLORS["blue"], "Usuario / sistema", ["Accion o integracion REST"]),
            (495, 190, 450, 90, COLORS["white"], COLORS["blue"], "Request HTTP", ["GET/POST/PUT/DELETE", "X-Client-Id, X-User-Id, X-Api-Key"]),
            (1090, 190, 360, 90, COLORS["blue_fill"], COLORS["blue"], "Endpoint versionado", ["/api/v1/hr/...", "Contratos OpenAPI"]),
            (110, 390, 330, 95, COLORS["yellow_fill"], COLORS["yellow"], "Filtros OWASP", ["API key, rate limit, CORS", "Security headers"]),
            (520, 380, 330, 110, COLORS["blue_fill"], COLORS["blue"], "Controller REST V1", ["Mapeo HTTP", "@Valid y errores 400"]),
            (930, 380, 330, 110, COLORS["blue_fill"], COLORS["blue"], "FunctionalContextFactory", ["Cliente, usuario, traceId", "Idioma y version funcional"]),
            (1340, 380, 330, 110, COLORS["blue_fill"], COLORS["blue"], "RoyalResponse", ["ResponseBodyAdvice", "GlobalApiExceptionHandler"]),
            (110, 625, 375, 120, COLORS["green_fill"], COLORS["green"], "Use case V1/V2", ["Parametros, TipoSeguro", "Capacitacion, Requerimiento"]),
            (555, 610, 345, 150, COLORS["green_fill"], COLORS["green"], "RoyalBaseUseCase", ["Licencia/permisos", "Auditoria funcional", "Errores de negocio"]),
            (970, 610, 345, 150, COLORS["purple_fill"], COLORS["purple"], "Version router", ["FunctionalVersionResolver", "Proxy factory", "catalogo XML"]),
            (1385, 610, 360, 150, COLORS["green_fill"], COLORS["green"], "Ports de salida", ["Repository por caso", "ConsultaPermisoPort", "Aprobaciones/Capacitacion"]),
            (110, 910, 330, 120, COLORS["yellow_fill"], COLORS["yellow"], "Adapter seleccionado", ["royal.persistence.adapter", "IN_MEMORY/SQL_SERVER/ORACLE"]),
            (500, 895, 330, 150, COLORS["purple_fill"], COLORS["purple"], "Adapters JDBC", ["SQL Server y Oracle", "JdbcTemplate + SQL", "Jasper reportes"]),
            (890, 895, 300, 150, COLORS["white"], COLORS["blue"], "Base de datos", ["HR_Parametros", "HR_TipoSeguro"]),
            (1250, 895, 300, 150, COLORS["blue_fill"], COLORS["blue"], "API Autenticacion", ["ObtenerPermisoController", "X-Api-Key entre APIs"]),
            (1610, 895, 205, 150, COLORS["green_fill"], COLORS["green"], "Retorno", ["Result DTO", "HTTP status"]),
            (110, 1110, 1705, 70, COLORS["red_fill"], COLORS["red"], "Regla clave", ["El caso de uso solo habla con ports; DB, Jasper y autenticacion quedan fuera de Application."]),
        ],
        [
            (55, 145, 1790, 140, "1. Entrada"),
            (55, 325, 1790, 190, "2. API + seguridad web"),
            (55, 555, 1790, 245, "3. Application"),
            (55, 840, 1790, 235, "4. Infrastructure + datos"),
        ],
        [
            ([(390, 235), (485, 235)], "#0b4d8b"),
            ([(945, 235), (1080, 235)], "#0b4d8b"),
            ([(1270, 280), (1270, 325), (275, 325), (275, 380)], "#0b4d8b"),
            ([(440, 437), (510, 437)], "#0b4d8b"),
            ([(850, 437), (920, 437)], "#0b4d8b"),
            ([(1095, 490), (1095, 540), (297, 540), (297, 615)], "#0b4d8b"),
            ([(485, 682), (545, 682)], "#0b4d8b"),
            ([(900, 682), (960, 682)], COLORS["gray"]),
            ([(1315, 682), (1375, 682)], "#0b4d8b"),
            ([(1565, 760), (1565, 820), (275, 820), (275, 900)], "#0b4d8b"),
            ([(440, 970), (490, 970)], "#0b4d8b"),
            ([(830, 970), (880, 970)], "#0b4d8b"),
            ([(1190, 1000), (1330, 1060), (1500, 1060), (1600, 1000)], COLORS["green"]),
            ([(1550, 970), (1600, 970)], COLORS["green"]),
        ],
    )


def main():
    OUT.mkdir(parents=True, exist_ok=True)
    render_capas_svg()
    render_flujo_svg()
    render_pngs()
    print("Generated architecture diagrams in docs/architecture")


if __name__ == "__main__":
    main()
