# DemoBankruptsBot
Telegram bot for checking the version of the bankruptcy service https://bankrot.fedresurs.ru/help.aspx?attempt=1

run.bat
```bat
set JAVA_HOME=C:\prog\jdk-11.0.9
set BOT_NAME=<name_bot>
set BOT_TOKEN=<token>
java -version
java -jar bankruptsbot-0.0.1-SNAPSHOT.jar
```
The parser needs a file "geckodriver.exe" in the folder C:\\Program Files (x86)\\Common Files\\Oracle\\Java\\javapath\\ (last version https://github.com/mozilla/geckodriver/releases)
