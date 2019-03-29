#!/bin/sh
java $JAVA_OPTIONS -jar -Dspring.profiles.active=production /app/spring-boot-app-exec.jar
