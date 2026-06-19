-- name: exists-by-id
select count(1)
from HR_TipoSeguro
where TipoSeguro = ?

-- name: find-by-id
select TipoSeguro,
       Descripcion,
       Estado,
       UltimoUsuario,
       UltimaFechaModif
from HR_TipoSeguro
where TipoSeguro = ?

-- name: find-all
select TipoSeguro,
       Descripcion,
       Estado,
       UltimoUsuario,
       UltimaFechaModif
from HR_TipoSeguro

-- name: insert
insert into HR_TipoSeguro (
    TipoSeguro,
    Descripcion,
    Estado,
    UltimoUsuario,
    UltimaFechaModif
)
values (?, ?, ?, ?, ?)

-- name: update
update HR_TipoSeguro
set Descripcion = ?,
    Estado = ?,
    UltimoUsuario = ?,
    UltimaFechaModif = ?
where TipoSeguro = ?

-- name: delete-by-id
delete from HR_TipoSeguro
where TipoSeguro = ?
