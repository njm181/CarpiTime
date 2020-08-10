package com.njm.carpintime.domain.usesCases

import com.njm.carpintime.data.repositoryImp.WeatherRepositoryImp
import com.njm.carpintime.domain.model.DataResult
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetDataUseCase @Inject constructor(private val weatherRepositoryImp: WeatherRepositoryImp){


    fun getData(lat: Double, lon: Double): Single<DataResult> {
        return weatherRepositoryImp.getData(lat, lon)
    }

}