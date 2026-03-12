#!/bin/bash
set -e
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
JAR="$SCRIPT_DIR/logistics-mock-service.jar"
export JAVA_HOME="${JAVA_HOME:-/opt/jdk-17}"

# Java detection
if [ -x "$JAVA_HOME/bin/java" ]; then
    JAVA="$JAVA_HOME/bin/java"
elif command -v java &>/dev/null; then
    JAVA="java"
else
    echo "ERROR: Java not found. Set JAVA_HOME or add java to PATH."
    exit 1
fi

JAVA_VER=$("$JAVA" -version 2>&1 | head -1 | awk -F '"' '{print $2}')

echo ""
echo " ============================================="
echo "  Logistics Management App"
echo " ============================================="
echo "  Java: $JAVA"
echo "  Version: $JAVA_VER"
echo "  Port: 8080"
echo "  URL:  http://localhost:8080"
echo " ============================================="
echo ""

# Launch browser after 5-second delay
(sleep 5 && (xdg-open http://localhost:8080 2>/dev/null || open http://localhost:8080 2>/dev/null || true)) &

# Start the service
exec "$JAVA" -jar "$JAR"
