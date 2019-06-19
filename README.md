1.1     Install Maven plugin in Intellij idea in pom.xml file. To install Apache Maven on Windows, you just need to
            download the Maven’s zip file from http://maven.apache.org/download.cgi, unzip it to a folder, and configure the Windows environment variables.
            Add a MAVEN_HOME system variables, and point it to the Maven folder.
            In “Environment variables” dialog, System variables, Clicks on the New... button and add a MAVEN_HOME variable and point it to c:\opt\apache-maven-X.X.X
            In system variables, find PATH, clicks on the Edit... button. In “Edit environment variable” dialog, clicks on the New button and add this %MAVEN_HOME%\bin
            Start a new command prompt, type "mvn –version"
1.2     Install Rest-assured plugin in Intellij idea in pom.xml file.
            Download lastest working version dependescies from Maven repository: https://mvnrepository.com/artifact/io.rest-assured/rest-assured/4.0.0
            Add the following dependency to your pom.xml.
            You should place rest-assured before the JUnit dependency declaration in your pom.xml in order to make sure that the
            correct version of Hamcrest is used.
1.3     Install Junit plugin in Intellij idea in pom.xml file.
1.3.1     First, download the latest version of JUnit, referred to below as junit.zip from http://sourceforge.net/project/showfiles.php?group_id=15278
            Then install JUnit on your platform.
            Unzip the junit.zip distribution file to a directory referred to as %JUNIT_HOME%.
            Add JUnit to the classpath: set CLASSPATH=%CLASSPATH%;%JUNIT_HOME%\junit.jar.
1.3.2 Test the installation by running the sample tests distributed with JUnit.
            Note that the sample tests are located in the installation directory directly, not the junit.jar file.
            Therefore, make sure that the JUnit installation directory is on your CLASSPATH.
            Then simply type: java org.junit.runner.JUnitCore org.junit.tests.AllTests
            All the tests should pass with an "OK" message.
            If the tests don't pass, verify that junit.jar is in the CLASSPATH.
1.3.3 Finally, read the documentation.
1.4.1     Install Serenity plugin in Intellij idea in pom.xml file.
            Add the Serenity plugin to the build path
            Adds the aggregate and check tasks to the build
1.4.2     Install Slf4j plugin in Intellij idea in pom.xml file
1.5       Add the following dependency to your pom.xml
1.6       If you plan to use reporting in test, you must change Rest-assured plugin to Serenity plugin in pom.xml file in dependensies.
