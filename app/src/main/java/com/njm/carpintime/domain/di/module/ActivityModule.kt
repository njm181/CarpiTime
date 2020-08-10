package com.njm.carpintime.domain.di.module

import com.njm.carpintime.data.repositoryImp.WeatherRepositoryImp
import com.njm.carpintime.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindWeahterRepositoryImp(weatherRepositoryImp: WeatherRepositoryImp): WeatherRepository

}