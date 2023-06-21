package com.sample

import com.sample.service.AnimalService
import io.kotest.assertions.fail
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest

@MicronautTest
class AnimalTests(
    private val animalService: AnimalService
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
})
