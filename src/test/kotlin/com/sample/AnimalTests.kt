package com.sample

import com.sample.data.AnimalDataSource
import com.sample.service.AnimalService
import io.kotest.assertions.fail
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.retry.exception.RetryException
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import java.util.concurrent.TimeoutException

@MicronautTest
class AnimalTests(
    private val animalService: AnimalService,
    private val animalDataSource: AnimalDataSource
) : StringSpec({
    "getAllAnimals()" {
        val response = animalService.getAllAnimals()

        response.shouldHaveSize(10)
    }
    "getAnimalById(id: Int) - Animal with Id Exists" {
        val response = animalService.getAnimalById(6)

        response.shouldNotBeNull().also { animal ->
            animal.name shouldBe "Frog"
        }
    }
    "getAnimalById(id: Int) - Animal with Id Doesn't Exist" {
        val response = animalService.getAnimalById(1000)

        response.shouldBeNull()
    }
    "getAnimalById(id: Int) - Negative Id Provided" {
        fail("NOT IMPLEMENTED")
    }
    "getAnimalsByPartialName(namePart: String) - Some Results" {
        fail("NOT IMPLEMENTED")
    }
    "getAnimalsByPartialName(namePart: String) - No Results" {
        fail("NOT IMPLEMENTED")
    }
    "getAnimalsByBioClass(bioClass: BioClass) - Some Results" {
        fail("NOT IMPLEMENTED")
    }
    "getAnimalsByBioClass(bioClass: BioClass) - No Results" {
        fail("NOT IMPLEMENTED")
    }
    "getAllAnimals() - Retry on Timeout" {
        // Override The Mock

        val successfulGetResponse = animalDataSource.get()
        var invokeCount = 0

        every {
            animalDataSource.get()
        } answers {
            invokeCount++
            if (invokeCount >= 3) {
                successfulGetResponse
            } else {
                throw TimeoutException()
            }
        }

        val response = animalService.getAllAnimals()

        response.shouldHaveSize(10)
    }
    "getAllAnimals() - Retry maximum 3 times" {
        // Override The Mock (again)

        every {
            animalDataSource.get()
        } throws TimeoutException()

        shouldThrow<RetryException> {
            animalService.getAllAnimals()
        }
    }
})
