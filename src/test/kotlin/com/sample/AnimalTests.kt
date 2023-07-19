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

    // Override The Mock

    val successfulGet = animalDataSource.get()
    var getInvokeCount = 0
    var timeoutCount = 0
    beforeTest {
        getInvokeCount = 0
    }

    every {
        animalDataSource.get()
    } answers {
        getInvokeCount++
        if (getInvokeCount > timeoutCount) {
            successfulGet
        } else {
            throw TimeoutException()
        }
    }

    "getAllAnimals() - Retry on Timeout" {
        timeoutCount = 2 // Timeout twice and then succeed

        val response = animalService.getAllAnimals()

        response.shouldHaveSize(10)

        getInvokeCount shouldBe 3
    }
    "getAllAnimals() - Retry maximum 3 times" {
        timeoutCount = 3 // Timeout three times

        shouldThrow<RetryException> {
            animalService.getAllAnimals()
        }

        getInvokeCount shouldBe 3
    }
})
