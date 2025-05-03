# Assignment Project

This project implements a directory structure represented as a CSV file using Java 17 and Spring Boot. The application provides functionality to parse the directory structure, generate an indented tree representation, and classify files based on their sensitivity.

## Project Structure

```
assignment-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── assignment/
│   │   │               └── AssignmentApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/
│       └── resources/
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.properties
│       └── gradle-wrapper.jar
```

## Setup Instructions

1. **Clone the Repository**
   ```
   git clone <repository-url>
   cd assignment-project
   ```

2. **Build the Project**
   Use Maven to build the project:
   ```
   ./mvnw clean install
   ```

3. **Run the Application**
   Start the Spring Boot application:
   ```
   ./mvnw spring-boot:run
   ```

## Usage

- The application reads the `directory-structure.csv` file to populate the directory structure.
- It provides various functionalities to generate tree structures and classify files based on their sensitivity (Top secret, Secret, etc.).
- The output can be verified against the provided text files in the resources directory.

## Testing

Unit tests are included for the `DirectoryService` and `ClassificationService` classes to ensure the functionality works as expected. Run the tests using:
```
./mvnw test
```

## License

This project is licensed under the MIT License.