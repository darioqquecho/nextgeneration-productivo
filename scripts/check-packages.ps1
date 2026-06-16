$bad = Get-ChildItem -Recurse -Filter *.java | Select-String -Pattern "package com\.royal\.erp"
if ($bad) {
  Write-Host "ERROR: Se encontraron packages prohibidos com.royal.erp"
  $bad
  exit 1
}
Write-Host "OK: No existen packages com.royal.erp"
