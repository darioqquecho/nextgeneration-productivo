$files = Get-ChildItem -Recurse -Filter *.java
$errors = @()
foreach ($f in $files) {
  $text = Get-Content $f.FullName -Raw
  if ($text -match "public (class|interface|record|enum)" -and $text -notmatch "/\*\*") {
    $errors += $f.FullName
  }
}
if ($errors.Count -gt 0) {
  Write-Host "ERROR: Archivos públicos sin JavaDoc"
  $errors
  exit 1
}
Write-Host "OK: Revisión básica de JavaDoc superada"
