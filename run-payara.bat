@echo off

cd /d %PAYARA_HOME%\bin

title starting payara...

call asadmin start-domain --debug

cd %PAYARA_HOME%\glassfish\domains\domain1\logs

title watching payara log...

tail -f server.log
