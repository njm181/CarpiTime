package com.njm.carpintime.presentation.viewModels

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.njm.carpintime.domain.model.DataResult
import com.njm.carpintime.domain.repository.WeatherRepository
import com.njm.carpintime.domain.usesCases.GetDataUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherViewModel @ViewModelInject constructor(private var getDataUseCase: GetDataUseCase):BaseViewModel() {

    private var responseMutableLiveData: MutableLiveData<DataResult> = MutableLiveData()

    fun getDataResponse():MutableLiveData<DataResult>{
        return responseMutableLiveData
    }


    fun getData(lat: Double, lon: Double) {
        addDisposable(
            getDataUseCase.getData(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {response -> responseMutableLiveData.value = response},
                    {error ->
                        Log.e("ERROR", "get data failure: "+ error.message)
                        responseMutableLiveData.value = null
                    }
                )
        )
    }

}