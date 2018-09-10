# oracle-client
Simple Oracle JDBC client for checking connection via CLI

### Prerequisites

- java

```
$ java -version
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
```

- maven

```
$ mvn --version
Apache Maven 3.5.3 (3383c37e1f9e9b3bc3df5050c29c8aff9f295297; 2018-02-24T20:49:05+01:00)
Maven home: C:\APPS\apache-maven-3.5.3
Java version: 1.8.0_151, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.8.0_151\jre
Default locale: de_DE, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

- Oracle JDBC

download ojdbc8.jar from [http://www.oracle.com/technetwork/database/application-development/jdbc/downloads/index.html] and install into your local maven repository

```
mvn install:install-file -Dfile=<Downloads folder>/ojdbc8.jar -DgroupId=com.oracle.jdbc -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar
```

### Build

```
$ mvn assembly
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building oracle-client 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
...
[INFO] Building jar: ...\oracle-client-0.0.1-SNAPSHOT-jar-with-dependencies.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.790 s
[INFO] Finished at: 2018-09-10T15:01:22+02:00
[INFO] Final Memory: 24M/313M
[INFO] ------------------------------------------------------------------------
```

### Run

```
java -cp target/oracle-client-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.example.oracleclient.OracleClient
```

```
$ java -cp target/oracle-client-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.example.oracleclient.OracleClient --help
```
usage: java -cp /path/to/<fat>.jar com.example.oracleclient.OracleClient

 -h,--help             show usage help

 -m,--host <arg>       host as reachable IP or (resolvable) hostname
                       default is localhost

 -p,--port <arg>       port number, default is 1521

 -s,--sid <arg>        Oracle SID, default is orcl

 -u,--username <arg>   database user name, default is scott

 -v,--verbose          show progress with verbosity

 -w,--password <arg>   password of the user, default is tiger

```
$ java -cp target/oracle-client-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.example.oracleclient.OracleClient --host localhost --port 1523 --sid orcl --username scott --password tiger --verbose
```
