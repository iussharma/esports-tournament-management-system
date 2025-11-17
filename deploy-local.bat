@echo off
REM deploy-local.bat - Build and deploy WAR to local Tomcat (Windows)

SETLOCAL ENABLEDELAYEDEXPANSION

if "%CATALINA_HOME%"=="" (
  echo ERROR: Please set CATALINA_HOME to your Tomcat installation path.
  exit /b 1
)

REM Build: prefer Maven if pom.xml exists
if exist pom.xml (
  echo Building with Maven...
  mvn -B clean package || (echo Maven build failed & exit /b 1)
  set WAR=target\eSportsTeamManagementSystem.war
) else (
  echo Building with Ant (looking for build.xml)...
  ant clean dist || (echo Ant build failed & exit /b 1)
  set WAR=dist\eSportsTeamManagementSystem.war
)

if not exist %WAR% (
  echo ERROR: WAR not found at %WAR%
  exit /b 1
)

echo Stopping Tomcat...
"%CATALINA_HOME%\bin\shutdown.bat"
timeout /t 3 /nobreak >nul

echo Copying WAR to Tomcat webapps...
copy /Y "%WAR%" "%CATALINA_HOME%\webapps\" >nul

echo Starting Tomcat...
"%CATALINA_HOME%\bin\startup.bat"

echo Waiting 5 seconds for Tomcat to start...
timeout /t 5 /nobreak >nul

echo Showing last 200 lines of catalina.out (if available):
powershell -Command "if (Test-Path \"%CATALINA_HOME%\logs\catalina.out\") { Get-Content -Path \"%CATALINA_HOME%\logs\catalina.out\" -Tail 200 } else { Write-Host 'Log file not found: %CATALINA_HOME%\logs\catalina.out' }"

echo Deployment complete.
ENDLOCAL
