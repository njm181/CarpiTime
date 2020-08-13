package com.njm.carpintime.domain.utils

import io.reactivex.rxjava3.disposables.CompositeDisposable

import io.reactivex.rxjava3.disposables.Disposable

class DisposableManager {

    companion object{
        lateinit var compositseDispo: CompositeDisposable

        fun addDisposable(disposable: Disposable){
            getCompositeDisposable().add(disposable)
        }

        fun disposeDisposable(){
            getCompositeDisposable().clear()
        }

        fun getCompositeDisposable(): CompositeDisposable {

            if(compositseDispo == null || compositseDispo.isDisposed){
                compositseDispo = CompositeDisposable()
            }
            return compositseDispo
        }
       //private fun DisposableManager() {}
    }


}