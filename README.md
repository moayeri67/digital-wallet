# Digital Wallet
This project provide some basic feature of digital wallet server.

# Description
The project consist of a server that is based on *[spark java framework](http://sparkjava.com/)*. The server expose several restfull api like “deposit”, “withdrawal”, “balance” and “transfer” for the clients and it will keep  track  of  a  user’s  monetary  balance  in the system.

# Server
```
Records balance in user Wallet.
Expose API for Depositing Money, Withdrawing, Getting Balance and Transfering in different currencies.
```
**Technologies**
* Java 8.
* Spark java framework.
* Google Guice.
* Embedded H2 database.
* Ibatis.
* Google Gson.
* JUnit.
* Log4j.

# How to start Server
**Note** to run this project you require at least JDK 8 or above version.

I've tried to write the project as simple as I can, So you don't need to install any requirements.

To run the server on linux follow the blow steps:

1. Clone or download the project.
```
  git clone https://github.com/moayeri67/digital-wallet
```
2. Uzip the project.
```
  $ unzip digital-wallet-master.zip
  $ cd digital-wallet-master
```
3. To compile and install the project type the bellow command.
```
  $ mvn clean install
```
4. Finlly to run the project.
```
  $ cd target/
  $ java -jar digital-wallet-1.0-SNAPSHOT-jar-with-dependencies.jar 
```
