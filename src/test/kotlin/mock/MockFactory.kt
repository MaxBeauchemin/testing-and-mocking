package mock

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.sample.data.AnimalDataSource
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
                objectMapper.readValue(File("src/test/resources/animals.json"))
            }
        }
    }
}
