package mock

import com.fasterxml.jackson.databind.ObjectMapper
import com.sample.data.AnimalDataSource
import com.sample.dto.Animal
import io.micronaut.context.annotation.Context
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.mockk.every
import io.mockk.mockk
import java.io.File

@Factory
class MockFactory(
    private val objectMapper: ObjectMapper
) {

    @Context
    @Replaces(AnimalDataSource::class)
    fun mockAnimalDataSource(): AnimalDataSource {
        return mockk<AnimalDataSource>().also {
            every {
                it.get()
            } answers {
                val file = File("src/test/resources/animals.json")
                val rawList = objectMapper.readValue(file, List::class.java)
                val animals = rawList.map {
                    objectMapper.readValue(objectMapper.writeValueAsString(it), Animal::class.java)
                }.toList()
                animals
            }
        }
    }
}
