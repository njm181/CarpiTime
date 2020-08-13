package com.njm.carpintime.domain.usesCases.base

import com.njm.carpintime.domain.utils.DisposableManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class SingleBaseUseCase <T>: BaseUseCase(){

    internal abstract fun buildUseCaseSingle(): Single<T>

    fun execute( onSuccess: ((t: T) -> Unit), onError: ((t: Throwable) -> Unit)){
        /*lastDisposable = buildUseCaseSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
        lastDisposable?.let {
            compositeDisposable.add(it)
        }
        disposeLast()*/
        DisposableManager.addDisposable(
            buildUseCaseSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError))

    }


}


