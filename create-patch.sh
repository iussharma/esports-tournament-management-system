#!/usr/bin/env bash
# create-patch.sh - produce fixes.zip containing changed files
set -euo pipefail

if [ -d .git ]; then
  echo "Using git to determine changed files..."
  git status --porcelain | awk '{print $2}' > /tmp/changed_files.txt || true
else
  echo "No git repo found - add filenames to fixes.txt then run this script."
  if [ ! -f fixes.txt ]; then
    echo "Create fixes.txt with one file path per line to include in fixes.zip"
    exit 1
  fi
  cp fixes.txt /tmp/changed_files.txt
fi

OUT=fixes.zip
rm -f "$OUT"
cd "$(git rev-parse --show-toplevel 2>/dev/null || echo .)"
zip -r "$OUT" -@ < /tmp/changed_files.txt
echo "Created $OUT containing:" && unzip -l "$OUT"
