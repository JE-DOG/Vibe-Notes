RUN ./gradlew installDist --no-daemon --exclude-task test

FROM mirror.gcr.io/openjdk:17-oracle

RUN ./build/install/vibe-notes-back/bin/vibe-notes-back
