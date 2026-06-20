param(
    [Parameter(Mandatory = $true)]
    [string]$Module,

    [Parameter(Mandatory = $true)]
    [string]$Process,

    [Parameter(Mandatory = $true)]
    [string]$UseCase,

    [string]$Entity = $UseCase,

    [string]$Version = "V1"
)

$ErrorActionPreference = "Stop"

function To-PackageSegment([string]$value) {
    return $value.Trim().ToLowerInvariant() -replace '[^a-z0-9]+', ''
}

function To-ClassName([string]$value) {
    $parts = ($value -replace '[^A-Za-z0-9]+', ' ').Trim().Split(' ', [System.StringSplitOptions]::RemoveEmptyEntries)
    return ($parts | ForEach-Object { $_.Substring(0, 1).ToUpperInvariant() + $_.Substring(1) }) -join ''
}

$moduleSegment = To-PackageSegment $Module
$processSegment = To-PackageSegment $Process
$useCaseSegment = To-PackageSegment $UseCase
$className = To-ClassName $UseCase
$entityName = To-ClassName $Entity

$basePath = "royal-$moduleSegment-application/src/main/java/net/royal/erp/modules/$moduleSegment/application/$processSegment/$useCaseSegment"
$package = "net.royal.erp.modules.$moduleSegment.application.$processSegment.$useCaseSegment"

New-Item -ItemType Directory -Force -Path "$basePath/dto" | Out-Null
New-Item -ItemType Directory -Force -Path "$basePath/port" | Out-Null

$interfacePath = "$basePath/${className}UseCase.java"
$v1Path = "$basePath/${className}${Version}UseCase.java"
$observedPath = "$basePath/Observed${className}UseCase.java"
$versionedPath = "$basePath/${className}VersionedUseCase.java"

if (Test-Path $interfacePath) {
    throw "Ya existe: $interfacePath"
}

@"
package $package;

import net.royal.erp.framework.kernel.FunctionalContext;

public interface ${className}UseCase {
    ${className}Result execute(${className}Command command, FunctionalContext context);
}
"@ | Set-Content -Encoding UTF8 $interfacePath

@"
package $package;

public record ${className}Command(String codigo) {
}
"@ | Set-Content -Encoding UTF8 "$basePath/${className}Command.java"

@"
package $package;

public record ${className}Result(String codigo, String estado, String traceId) {
}
"@ | Set-Content -Encoding UTF8 "$basePath/${className}Result.java"

@"
package $package;

import net.royal.erp.framework.application.RoyalBaseUseCase;
import net.royal.erp.framework.audit.AuditPort;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.security.UseCaseGuards;

public class ${className}${Version}UseCase extends RoyalBaseUseCase implements ${className}UseCase {
    public ${className}${Version}UseCase(UseCaseGuards guards, AuditPort auditPort) {
        super("$($Module.ToUpperInvariant())", "$entityName", "$Version", guards, auditPort);
    }

    public ${className}Result execute(${className}Command command, FunctionalContext context) {
        checkGuards(context);
        auditOk(context, command.codigo());
        return new ${className}Result(command.codigo(), "OK", context.traceId());
    }
}
"@ | Set-Content -Encoding UTF8 $v1Path

@"
package $package;

import net.royal.erp.framework.application.RoyalObservedUseCase;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.observability.ObservabilityPort;

public class Observed${className}UseCase extends RoyalObservedUseCase implements ${className}UseCase {
    private final ${className}UseCase delegate;

    public Observed${className}UseCase(${className}UseCase delegate, ObservabilityPort observability) {
        super(observability);
        this.delegate = delegate;
    }

    public ${className}Result execute(${className}Command command, FunctionalContext context) {
        return observe(context, () -> delegate.execute(command, context));
    }
}
"@ | Set-Content -Encoding UTF8 $observedPath

@"
package $package;

import net.royal.erp.framework.application.*;
import net.royal.erp.framework.kernel.FunctionalContext;
import net.royal.erp.framework.versioning.*;

public class ${className}VersionedUseCase extends RoyalBaseVersionedUseCase<${className}UseCase> implements ${className}UseCase {
    public ${className}VersionedUseCase(FunctionalVersionResolver resolver, ${className}UseCase v1) {
        super(new ModuleVersionRouter<${className}UseCase>(resolver).register(FunctionalVersion.$Version, v1));
    }

    public ${className}Result execute(${className}Command command, FunctionalContext context) {
        return delegate(context).execute(command, context);
    }
}
"@ | Set-Content -Encoding UTF8 $versionedPath

Write-Host "Caso de uso generado en $basePath"
