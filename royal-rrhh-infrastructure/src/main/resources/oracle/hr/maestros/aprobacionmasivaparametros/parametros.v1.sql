-- name: find-by-id
select compania,
       codigo,
       nombre,
       estado,
       Precio,
       cantidad,
       FechaProceso,
       UltimoUsuario,
       UltimaFechaModif
from HR_Parametros
where compania = ?
  and codigo = ?

-- name: approve-if-pending
update HR_Parametros
set estado = 'AP',
    UltimoUsuario = ?,
    UltimaFechaModif = ?
where compania = ?
  and codigo = ?
  and estado = 'A'
