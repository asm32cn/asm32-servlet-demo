@echo off

::set cmdLine=javac -cp lib\servlet-api.jar servlet_status.java -d ..\classes -Xlint:deprecation
set cmdLine=javac -cp lib\servlet-api.jar -d ..\classes -encoding utf-8  servlet_status.java

echo %cmdLine%

%cmdLine%

pause
