Write-Host "Validando proyecto Royal ERP Next Framework"
mvn clean install
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
Write-Host "Validación Maven completada"
