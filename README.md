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
