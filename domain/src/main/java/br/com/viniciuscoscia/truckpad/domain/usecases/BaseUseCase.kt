package br.com.viniciuscoscia.truckpad.domain.usecases

abstract class BaseUseCase<ReturnType, in Params> {
    abstract suspend fun execute(params: Params): ReturnType
}