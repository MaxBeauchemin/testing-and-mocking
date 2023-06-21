package com.sample.data

import com.sample.dto.Animal
import jakarta.inject.Singleton

@Singleton
class AnimalDataSource {
    fun get(): List<Animal> {
        // In here would normally be code to connect to a remote server to retrieve the data
        return emptyList()
    }
}
