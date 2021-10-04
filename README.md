### Restful Booker Api Tests with Serenity

A pet project to explore automation testing with RestAssured and Lombok based on Restful Booker API.

### Built With

* [JUnit4](https://junit.org/junit4/)
* [Gradle](https://gradle.org/)
* [Project Lombok](https://projectlombok.org/)
* [REST-assured](https://rest-assured.io/)
* [Serenity BDD](https://serenity-bdd.info/)
* [Cucumber](https://cucumber.io/)

Training public API: [Restful Booker API](https://restful-booker.herokuapp.com)

### Prerequisites

* Make sure you have annotation processing enabled:
    * IntelliJ: ```Settings/Preference > search for "Annotation Processor" > Enable annotation processing```
    * Eclipse: ```project Properties > Java Compiler > Annotation Processing > Enable annotation processing```
* Have Lombok plugin installed in your IDE ([IntelliJ](https://projectlombok.org/setup/intellij), [Eclipse](https://projectlombok.org/setup/eclipse))
* Have Cucmber/Gherkin plugin installed in your IDE ([IntelliJ](https://plugins.jetbrains.com/plugin/7212-cucumber-for-java), [Eclipse](https://marketplace.eclipse.org/content/cucumber-eclipse-plugin))

### Running the test

1. Clone the repo
   ```sh
   git clone https://github.com/ValeriePtah/restful-booker-serenity-auto-test.git
   ```
2. To see all the console output run tests from the Gradle toolbar
   ```
   Gradle > Tasks > verification > test
   ```
3. To see Serenity reports: 



## License

Distributed under the MIT License.
