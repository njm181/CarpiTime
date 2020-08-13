package com.njm.carpintime.domain.usesCases.base

import io.reactivex.rxjava3.disposables.CompositeDisposable

import io.reactivex.rxjava3.disposables.Disposable

object DisposableManager {
        var compositseDisposable: CompositeDisposable? = null

        fun addDisposable(disposable: Disposable){
            getCompositeDisposableInstance().add(disposable)
        }

        fun disposeDisposable(){
            getCompositeDisposableInstance().clear()
        }

        private fun getCompositeDisposableInstance(): CompositeDisposable {
            if(compositseDisposable == null || compositseDisposable!!.isDisposed){
                compositseDisposable = CompositeDisposable()
            }
            return compositseDisposable as CompositeDisposable
        }
       //private fun DisposableManager() {}



}