# Prova Finale Ingegneria del Software 2020
## Gruppo GC16

- ###   890219    Alessandro Zito ([@alessandrozito98](https://github.com/alessandrozito98)) alessandro4.zito@mail.polimi.it
- ###   847567    Flavio Zecchinello ([@FlavioZecchinello](https://github.com/FlavioZecchinello)) flavio.zecchinello@mail.polimi.it

#### Legend
[![RED](http://placehold.it/15/f03c15/f03c15)]() Not Implemented &nbsp;&nbsp;&nbsp;&nbsp;[![YELLOW](http://placehold.it/15/ffdd00/ffdd00)]() Implementing&nbsp;&nbsp;&nbsp;&nbsp;[![GREEN](http://placehold.it/15/44bb44/44bb44)]() Implemented

| Functionality | Status |
|:-----------------------|:------------------------------------:|
| Basic rules | [![GREEN](http://placehold.it/15/44bb44/44bb44)]() |
| Complete rules | [![GREEN](http://placehold.it/15/44bb44/44bb44)]() |
| Socket |[![GREEN](http://placehold.it/15/44bb44/44bb44)]() |
| CLI |[![GREEN](http://placehold.it/15/44bb44/44bb44)]() |
| GUI | [![RED](http://placehold.it/15/f03c15/f03c15)]() |
| Multiple games | [![RED](http://placehold.it/15/f03c15/f03c15)]()|
| 5 Advanced Gods | [![RED](http://placehold.it/15/f03c15/f03c15)]() |
| Persistence | [![RED](http://placehold.it/15/f03c15/f03c15)]() |
| Undo Function | [![RED](http://placehold.it/15/f03c15/f03c15)]() |

<!--
[![RED](https://placehold.it/15/f03c15/f03c15)](#)
[![YELLOW](https://placehold.it/15/ffdd00/ffdd00)](#)
[![GREEN](https://placehold.it/15/44bb44/44bb44)](#)
-->


### Build instructions

The jar are built using the [Maven Install Plugin](https://maven.apache.org/plugins/maven-install-plugin/)
If you want to build yourself the executables jar files run:
```
mvn install
```

### How to start
#### Server
Requires java 14+ 

Run:
```
java -jar Server.jar
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
java -jar Client.jar
````

The following parameters will be asked in order
1. ip address
2. Socket server port
3. Nickname
4. (only for the first player connected) Number of players (between 2 or 3)
5. (only for the first player connected) N Gods from the list where N = number of players
6. (only for the other players that aren't the first one) His God
7. (only for the first player connected) the player that will start

Example:
```
127.0.0.1
2020
user
2
PAN, APOLLO
PAN
1
```

### Test Coverage

#### Model
91%

#### Contrller 
51 %


For the test coverage, there are some HTML files in
```
/deliverables/report/
```
that show the complete coverage of each class of both model and controller packages.
