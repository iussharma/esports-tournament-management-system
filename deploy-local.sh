#!/usr/bin/env bash
# deploy-local.sh - Build and deploy WAR to Tomcat (Linux/macOS)

set -euo pipefail

if [ -z "${CATALINA_HOME:-}" ]; then
  echo "ERROR: Please set CATALINA_HOME to your Tomcat installation path."
  exit 1
fi

if [ -f pom.xml ]; then
  echo "Building with Maven..."
  mvn -B clean package
  WAR="target/eSportsTeamManagementSystem.war"
elif [ -f build.xml ]; then
  echo "Building with Ant..."
  ant clean dist
  WAR="dist/eSportsTeamManagementSystem.war"
else
  echo "No Maven or Ant build found"
  exit 1
fi

if [ ! -f "$WAR" ]; then
  echo "WAR not found at $WAR"
  exit 1
fi

echo "Stopping Tomcat..."
"$CATALINA_HOME/bin/shutdown.sh" || true
sleep 3

echo "Copying WAR to Tomcat webapps..."
cp -f "$WAR" "$CATALINA_HOME/webapps/"

echo "Starting Tomcat..."
"$CATALINA_HOME/bin/startup.sh"

echo "Waiting 5 seconds for Tomcat to start..."
sleep 5

if [ -f "$CATALINA_HOME/logs/catalina.out" ]; then
  echo "Last 200 lines of catalina.out:"
  tail -n 200 "$CATALINA_HOME/logs/catalina.out"
else
  echo "Log file not found: $CATALINA_HOME/logs/catalina.out"
fi

echo "Deployment complete."
