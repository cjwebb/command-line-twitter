command-line-twitter
====================

console-based social networking application (similar to Twitter)

### To Run
````
mvn clean install
cd target
java -jar command-line-twitter-1.0-SNAPSHOT-jar-with-dependencies.jar
````

### To Use

To post as someone
````
> Alice -> Hello
> Bob -> Hello there!
````
To view someone's posts
````
> Alice
Alice - Hello (2 seconds ago)
> Bob
Bob - Hello there! (1 second ago)
````
To follow someone
````
> Alice follows Bob
````
To view someone's wall
````
> Alice wall
Bob - Hello there! (1 second ago)
Alice - Hello (2 seconds ago)
````

