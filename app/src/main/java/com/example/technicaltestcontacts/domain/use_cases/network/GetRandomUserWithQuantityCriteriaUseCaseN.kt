package com.example.technicaltestcontacts.domain.use_cases.network

import com.example.technicaltestcontacts.data.repository.network.RandomUserRepositoryN
import javax.inject.Inject

class GetRandomUserWithQuantityCriteriaUseCaseN @Inject constructor(private val randomUserRepositoryN: RandomUserRepositoryN) {
}