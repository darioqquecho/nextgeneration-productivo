from PIL import Image, ImageDraw, ImageFont


OUT = "docs/architecture/flujo-peticion-caso-uso.png"
W, H = 1800, 1150


def font(size, bold=False):
    candidates = [
        "C:/Windows/Fonts/arialbd.ttf" if bold else "C:/Windows/Fonts/arial.ttf",
        "C:/Windows/Fonts/segoeuib.ttf" if bold else "C:/Windows/Fonts/segoeui.ttf",
    ]
    for path in candidates:
        try:
            return ImageFont.truetype(path, size)
        except OSError:
            pass
    return ImageFont.load_default()


F_TITLE = font(42, True)
F_SUB = font(22)
F_BAND = font(22, True)
F_BOX_TITLE = font(20, True)
F_TEXT = font(17)
F_SMALL = font(15)
F_NUM = font(18, True)

COLORS = {
    "bg": "#f7fafc",
    "blue": "#0b4d8b",
    "blue2": "#2364aa",
    "title": "#0b2b5c",
    "text": "#243746",
    "muted": "#4a6475",
    "layer_border": "#b8ccda",
    "soft": "#eef6ff",
    "green": "#effaf4",
    "green_border": "#248a50",
    "yellow": "#fff8e6",
    "yellow_border": "#c98116",
    "red": "#fff1f1",
    "red_border": "#b42318",
    "db": "#f2f6ff",
    "db_border": "#3447a0",
    "dash": "#6c8798",
    "white": "#ffffff",
}


img = Image.new("RGB", (W, H), COLORS["bg"])
d = ImageDraw.Draw(img)


def shadow_box(x, y, w, h, fill, outline, width=2, radius=12):
    d.rounded_rectangle((x + 6, y + 8, x + w + 6, y + h + 8), radius=radius, fill="#d8e2ea")
    d.rounded_rectangle((x, y, x + w, y + h), radius=radius, fill=fill, outline=outline, width=width)


def dashed_box(x, y, w, h, fill, outline, width=2, radius=12):
    shadow_box(x, y, w, h, fill, "#d8e2ea", 1, radius)
    # Replace outline with simple dashed border.
    d.rounded_rectangle((x, y, x + w, y + h), radius=radius, fill=fill)
    step = 16
    for xx in range(x + radius, x + w - radius, step):
        d.line((xx, y, min(xx + 8, x + w - radius), y), fill=outline, width=width)
        d.line((xx, y + h, min(xx + 8, x + w - radius), y + h), fill=outline, width=width)
    for yy in range(y + radius, y + h - radius, step):
        d.line((x, yy, x, min(yy + 8, y + h - radius)), fill=outline, width=width)
        d.line((x + w, yy, x + w, min(yy + 8, y + h - radius)), fill=outline, width=width)


def text(x, y, value, fnt=F_TEXT, fill=None):
    d.text((x, y), value, font=fnt, fill=fill or COLORS["text"])


def arrow(points, color=None, width=4):
    color = color or COLORS["blue"]
    d.line(points, fill=color, width=width, joint="curve")
    x1, y1 = points[-2]
    x2, y2 = points[-1]
    # Simple arrowhead based on dominant direction.
    if abs(x2 - x1) >= abs(y2 - y1):
        sign = 1 if x2 >= x1 else -1
        head = [(x2, y2), (x2 - sign * 16, y2 - 8), (x2 - sign * 16, y2 + 8)]
    else:
        sign = 1 if y2 >= y1 else -1
        head = [(x2, y2), (x2 - 8, y2 - sign * 16), (x2 + 8, y2 - sign * 16)]
    d.polygon(head, fill=color)


def badge(x, y, n, fill):
    d.ellipse((x - 18, y - 18, x + 18, y + 18), fill=fill)
    tw = d.textlength(str(n), font=F_NUM)
    d.text((x - tw / 2, y - 10), str(n), font=F_NUM, fill=COLORS["white"])


# Title
text(90, 60, "Flujo de peticion en RRHH", F_TITLE, COLORS["title"])
text(90, 105, "Ejemplo: Mantenimiento de HR_Parametros / HR_TipoSeguro desde REST hasta SQL Server u Oracle", F_SUB, "#456070")

# Layers
for y, h, label in [
    (145, 150, "Entrada / Canal"),
    (330, 190, "API + Seguridad Web"),
    (555, 210, "Application / Caso de uso"),
    (800, 230, "Infrastructure / Adapters + Base de datos"),
]:
    shadow_box(70, y, 1660, h, COLORS["white"], COLORS["layer_border"], 2, 16)
    text(100, y + 35, label, F_BAND, COLORS["title"])

# Entrada
shadow_box(120, 205, 250, 70, COLORS["white"], COLORS["blue2"], 2, 12)
text(150, 227, "Usuario / Frontend", F_BOX_TITLE, COLORS["title"])
text(150, 253, "Click, formulario, accion")
shadow_box(470, 195, 360, 90, COLORS["white"], COLORS["blue2"], 2, 12)
text(500, 220, "Request HTTP", F_BOX_TITLE, COLORS["title"])
text(500, 248, "POST / PUT / DELETE / GET")
text(500, 272, "Headers: X-Client-Id, X-User-Id, X-Api-Key", F_SMALL, COLORS["muted"])
arrow([(370, 240), (460, 240)])

# API + seguridad
shadow_box(120, 395, 260, 90, COLORS["yellow"], COLORS["yellow_border"], 2, 12)
text(150, 420, "Filtros OWASP base", F_BOX_TITLE, COLORS["title"])
text(150, 448, "API key, rate limit")
text(150, 472, "headers seguros, CORS")
shadow_box(455, 390, 330, 100, COLORS["soft"], COLORS["blue2"], 2, 12)
text(485, 415, "Controller REST V1", F_BOX_TITLE, COLORS["title"])
text(485, 443, "MantenimientoParametroControllerV1")
text(485, 467, "Mapea request HTTP")
shadow_box(870, 390, 330, 100, COLORS["soft"], COLORS["blue2"], 2, 12)
text(900, 415, "Validacion de DTO", F_BOX_TITLE, COLORS["title"])
text(900, 443, "@Valid, tamanos, patrones")
text(900, 467, "Errores 400 controlados")
shadow_box(1285, 390, 330, 100, COLORS["soft"], COLORS["blue2"], 2, 12)
text(1315, 415, "FunctionalContext", F_BOX_TITLE, COLORS["title"])
text(1315, 443, "tenant, cliente, usuario")
text(1315, 467, "traceId, version, idioma")
arrow([(650, 285), (650, 330), (250, 330), (250, 385)])
arrow([(380, 440), (445, 440)])
arrow([(785, 440), (860, 440)])
arrow([(1200, 440), (1275, 440)])

# Application
shadow_box(120, 625, 325, 105, COLORS["green"], COLORS["green_border"], 2, 12)
text(150, 650, "Caso de uso V1 / V2", F_BOX_TITLE, COLORS["title"])
text(150, 678, "MantenimientoTablaParametrosV1UseCase")
text(150, 702, "Regla funcional y orquestacion")
shadow_box(510, 625, 275, 105, COLORS["green"], COLORS["green_border"], 2, 12)
text(540, 650, "RoyalBaseUseCase", F_BOX_TITLE, COLORS["title"])
text(540, 678, "guards, auditoria")
text(540, 702, "validaciones comunes")
shadow_box(850, 625, 300, 105, COLORS["green"], COLORS["green_border"], 2, 12)
text(880, 650, "Puertos de salida", F_BOX_TITLE, COLORS["title"])
text(880, 678, "Repository Port")
text(880, 702, "ConsultaPermisoPort")
dashed_box(1220, 610, 395, 135, COLORS["white"], COLORS["dash"], 2, 12)
text(1250, 636, "Servicios transversales", F_BOX_TITLE, COLORS["title"])
text(1250, 666, "Version router, observabilidad")
text(1250, 690, "licencia, permisos, auditoria")
text(1250, 715, "Generalizados en framework", F_SMALL, COLORS["muted"])
arrow([(1450, 490), (1450, 540), (282, 540), (282, 615)])
arrow([(445, 678), (500, 678)])
arrow([(785, 678), (840, 678)])
d.line([(647, 625), (730, 575), (1180, 575), (1390, 610)], fill=COLORS["dash"], width=3)

# Infrastructure
shadow_box(120, 875, 300, 110, COLORS["yellow"], COLORS["yellow_border"], 2, 12)
text(150, 900, "Adapter seleccionado", F_BOX_TITLE, COLORS["title"])
text(150, 930, "Por propiedad / cliente")
text(150, 954, "IN_MEMORY, SQL_SERVER, ORACLE")
shadow_box(500, 860, 305, 140, COLORS["db"], COLORS["db_border"], 2, 12)
text(530, 887, "SQL Server Adapter", F_BOX_TITLE, COLORS["title"])
text(530, 916, "SqlServerMantenimiento...")
text(530, 942, "JdbcTemplate + SQL resources")
text(530, 970, "sql/hr/maestros/*", F_SMALL, COLORS["muted"])
shadow_box(880, 860, 305, 140, COLORS["db"], COLORS["db_border"], 2, 12)
text(910, 887, "Oracle Adapter", F_BOX_TITLE, COLORS["title"])
text(910, 916, "OracleMantenimiento...")
text(910, 942, "JdbcTemplate + SQL resources")
text(910, 970, "oracle/hr/maestros/*", F_SMALL, COLORS["muted"])
shadow_box(1280, 875, 300, 110, COLORS["db"], COLORS["db_border"], 2, 12)
text(1310, 900, "Base de datos", F_BOX_TITLE, COLORS["title"])
text(1310, 930, "HR_Parametros")
text(1310, 954, "HR_TipoSeguro")
arrow([(1000, 730), (1000, 790), (270, 790), (270, 865)])
arrow([(420, 930), (490, 930)])
arrow([(420, 930), (600, 810), (800, 810), (870, 900)])
arrow([(805, 930), (1270, 930)])
arrow([(1185, 930), (1270, 930)])

# Response
d.line([(1430, 875), (1430, 790), (1575, 790), (1575, 690), (1575, 565), (1670, 565), (1670, 440), (1670, 300), (920, 305), (800, 285)], fill=COLORS["green_border"], width=4)
text(1395, 790, "Respuesta", F_BOX_TITLE, COLORS["title"])
text(1395, 818, "Result DTO + HTTP 200/400/401/429/500")

# Badges
badge(115, 207, 1, COLORS["blue"])
badge(115, 397, 2, COLORS["yellow_border"])
badge(450, 392, 3, COLORS["blue"])
badge(865, 392, 4, COLORS["blue"])
badge(115, 627, 5, COLORS["green_border"])
badge(845, 627, 6, COLORS["green_border"])
badge(115, 877, 7, COLORS["yellow_border"])
badge(1275, 877, 8, COLORS["blue"])

# Rule
shadow_box(120, 1055, 1495, 55, COLORS["red"], COLORS["red_border"], 2, 12)
text(150, 1072, "Regla clave:", F_BOX_TITLE, COLORS["title"])
text(270, 1074, "API e Infrastructure dependen hacia Application/Domain. Application usa puertos; nunca sabe si golpea SQL Server, Oracle o REST.")

img.save(OUT)
print(OUT)
