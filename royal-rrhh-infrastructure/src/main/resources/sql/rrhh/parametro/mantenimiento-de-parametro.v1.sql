-- name: exists-by-id
select count(1)
from HR_Parametros
where compania = ?
  and codigo = ?

-- name: find-by-id
select compania,
       codigo,
       nombre,
       estado,
       UltimoUsuario,
       UltimaFechaModif
from HR_Parametros
where compania = ?
  and codigo = ?

-- name: find-all
select compania,
       codigo,
       nombre,
       estado,
       UltimoUsuario,
       UltimaFechaModif
from HR_Parametros

-- name: insert
insert into HR_Parametros (
    compania,
    codigo,
    nombre,
    estado,
    UltimoUsuario,
    UltimaFechaModif
)
values (?, ?, ?, ?, ?, ?)

-- name: update
update HR_Parametros
set nombre = ?,
    estado = ?,
    UltimoUsuario = ?,
    UltimaFechaModif = ?
where compania = ?
  and codigo = ?

-- name: delete-by-id
delete from HR_Parametros
where compania = ?
  and codigo = ?
