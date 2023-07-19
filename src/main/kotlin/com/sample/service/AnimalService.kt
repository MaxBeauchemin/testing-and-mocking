package com.sample.service

import com.sample.data.AnimalDataSource
import com.sample.dto.Animal
import com.sample.enum.BioClass
import io.micronaut.retry.exception.RetryException
import jakarta.inject.Singleton

@Singleton
class AnimalService(
    private val animalDataSource: AnimalDataSource
) {
    private val maxRetryCount = 3

    fun getAllAnimals(): List<Animal> {
        return animalDataSource.retryableGet()
    }

    fun getAnimalById(id: Int): Animal? {
        return animalDataSource.retryableGet().firstOrNull()
    }

    fun getAnimalsByPartialName(namePart: String): List<Animal> {
        return animalDataSource.retryableGet()
    }

    fun getAnimalsByBioClass(bioClass: BioClass): List<Animal> {
        return animalDataSource.retryableGet()
    }

    private fun AnimalDataSource.retryableGet(): List<Animal> {
        repeat(maxRetryCount) {
            kotlin.runCatching {
                return get()
            }
        }
        throw RetryException("Retried $maxRetryCount times")
    }
}
