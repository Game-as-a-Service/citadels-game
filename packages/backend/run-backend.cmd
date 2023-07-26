call mvnw.cmd package
cd /d "%~dp0\spring\target"
move citadels-backend-0.0.1-SNAPSHOT.jar "%~dp0\spring"
cd /d "%~dp0\spring"
call docker-compose up -d --build