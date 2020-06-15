# Prova Finale Ingegneria del Software 2019
## Gruppo GC16

- ###   10617579    Alessandro Zito ([@alessandrozito98](https://github.com/alessandrozito98))alessandro4-zito@mail.polimi.it
- ###   10530016    Federico Cazzola ([@FlavioZecchinello](https://github.com/FlavioZecchinello))flavio.zecchinello@mail.polimi.it

| Functionality | State |
|:-----------------------|:------------------------------------:|
| Basic rules | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| Complete rules | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| Socket | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| CLI | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| GUI | [![RED](https://placehold.it/15/f03c15/f03c15)](#) |
| Multiple games | [![GREEN](https://placehold.it/15/44bb44/44bb44)](#) |
| Persistence | [![RED](https://placehold.it/15/f03c15/f03c15)](#) |
| Undo | [![RED](https://placehold.it/15/f03c15/f03c15)](#) |
| Advanced Gods | [![RED](https://placehold.it/15/f03c15/f03c15)](#) |

<!--
[![RED](https://placehold.it/15/f03c15/f03c15)](#)
[![YELLOW](https://placehold.it/15/ffdd00/ffdd00)](#)
[![GREEN](https://placehold.it/15/44bb44/44bb44)](#)
-->


### Build instructions

The jar are built using the [Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin/)
If you want to build yourself the executables jar files run:
```
mvn clean package
```

### How to start
#### Server
Requires java 14+ 

Run:
```
java -jar server.jar
```

The following parameters will be asked in order:
1. Socket port number for the server

Esempio:
```
2020
```

#### Client
Requires java 14+

Run:
```
java -jar client.jar
````

The following parameters will be asked in order
1. Socket server port
2. Nickname
3. (Only if first player entered in lobby) Numero board

Example:
```
2020
user
```
