Hello! This is the final project for group 27, that is:
Ryan Mailhiot 30080009
Danny Picazo 301271082
German (David) Fonseca 30061209
Krishna Shah 30114067

------------------------------------------------------------------------------------------
The compilation of our program is simple. To compile the main program without tests,
it is required that you use the MySQL connector version 8.0.23. The command we used for
compilation is shown here:

javac -cp .;lib/mysql-connector-java-8.0.23.jar FPCode/*.java

And then to run the program we used the following command: 

java -cp .;lib/mysql-connector-java-8.0.23.jar FPCode.Inter 

(where the class Inter.java is the user interface class for the program)

These commands should be compiled and ran from the working directory, for us this was
ENSF-409-Final-Project-Group-27, how ever any folder outside of the folder FPCode will
work as the working directory. NOTE: we used the file structure shown in the videos where
the working directory has a lib folder containing the necessary packages.
------------------------------------------------------------------------------------------
To compile the tests, you will require the MySQL connector version 8.0.23, JUnit version
4.13.2, and Hamcrest core version 1.3. The command we used for compilation is shown here:
javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar FPCode/tests/*.java
And then to run the test we used the following command:
java -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore FPCode.tests.<Test>
where <test> is the test that needed to be run.

For the tests, there are specific inventory files used for testing. HamperTest and OrderTest use the 
"Small Inventory.sql" file for testing, where the DatabaseItemsTest uses the "micro test inventory.sql"
Using any other file may have the tests fail because some tests are hardcoded expecting a specific database.
ClientTest requires any database with the structure of Inventory.sql to be active. It is recommended to have a 
database available for all other test files, even if they do not use it. This is because a test file may call a file which
calls a database. Even if that information is never used, a test can fail if there is no databse there. 
SQLTest.java IS NOT AN OFFICIAL TEST AND IS COMMENTED OUT FOR THIS REASON. (Making this clear so no marks are lost)
The functionality of this file you will find at the bottom of HamperTest.java as a helper. It is very useful!
------------------------------------------------------------------------------------------
