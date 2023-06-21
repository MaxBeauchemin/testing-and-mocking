package com.sample.service

import com.sample.data.AnimalDataSource
import com.sample.dto.Animal
import com.sample.enum.BioClass
import jakarta.inject.Singleton

@Singleton
class AnimalService(
    private val animalDataSource: AnimalDataSource
) {
    fun getAllAnimals(): List<Animal> {
        return animalDataSource.get()
    }

    fun getAnimalById(id: Int): Animal? {
        return animalDataSource.get().firstOrNull()
    }

    fun getAnimalsByPartialName(namePart: String): List<Animal> {
        return animalDataSource.get()
    }

    fun getAnimalsByBioClass(bioClass: BioClass): List<Animal> {
        return animalDataSource.get()
    }
}
