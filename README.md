## Zopa Technical Test

Check the document at `````/resources/zopa-challenge.pdf````` to learn more about the requirements

## Requirements for running the application

1. clone the project
2. Install Java

## How to run the application

1. Open your terminal
2. Navigate to the project's root directly
3. type ```java -jar target/kabir-zopa-1.0-SNAPSHOT.jar [loan amount]```
   example ```java -jar target/kabir-zopa-1.0-SNAPSHOT.jar 1000```This will run the application with the pre-configured lenders

### How to run more test cases

1. Open your terminal
2. Navigate to the project's root directly
3. type ```java -jar target/kabir-zopa-1.0-SNAPSHOT.jar [loan amount] [lenders file path] ```
example ```java -jar target/kabir-zopa-1.0-SNAPSHOT.jar  /Users/mac/Desktop/zopa/kabir-zopa/src/main/resources/lenders.csv``` (The ```lenders.csv``` file you provide must have the same structure as the one in ```/resources/lenders.csv```)