package com.njm.carpintime.domain.usesCases.base

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class SingleBaseUseCase <T>{

    internal abstract fun buildUseCaseSingle(): Single<T>
    //onSuccess: ((t: T) -> Unit // Funcion de orden superior lambda, funcion que pasa por parametro otra funcion y devuelve un Unit/Void
    //Las funciones que se pasen seran implementadas cuando se implemente execute. Ahi se les indica que hacen dentro de llaves { do something }
    //Al ser Generics (T) toman el dato que reciben cada vez que es llamada por un use case
    fun execute( onSuccess: ((t: T) -> Unit), onError: ((t: Throwable) -> Unit)){
        DisposableManager.addDisposable(
            buildUseCaseSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError))

    }


}


