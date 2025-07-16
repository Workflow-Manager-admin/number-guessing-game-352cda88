#!/bin/bash
cd /home/kavia/workspace/code-generation/number-guessing-game-352cda88/pick_a_number_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

