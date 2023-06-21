# Testing and Mocking

## Description

This project is intended to serve as a very basic introduction to Feature Testing your code and the powerful idea of Mocking your dependencies in order to isolate the logic you are testing

## Important Files

- `src/main/kotlin/com/sample/`
  - `/data/AnimalDataSource` - The real code for the Animal Data Source which would typically connect to a remote server to obtain this data
  - `/service/AnimalService` - The implementation of our logic that interacts with the `AnimalDataSource`
- `src/test/kotlin/`
  - `com/sample/AnimalTests` - All the tests written to verify the functionality of the `AnimalService`
  - `mock/MockFactory` - The "Fake" implementation of the `AnimalDataSource` so that our tests aren't dependent on a remote server

## Project Setup

1. Clone the repository to your machine
2. Install Java 17
   - `curl -s "https://get.sdkman.io" | bash`
   - `source "$HOME/.sdkman/bin/sdkman-init.sh"`
   - `sdk install java`
   - `export JAVA_HOME='/Users/{you}/.sdkman/candidates/java/current'`

## Running Tests

CD into the root directory of the project and run the following command
`./gradlew build koverReport --no-build-cache`

If tests pass, you should see a Code Coverage report available for you to see which lines of code were tested
