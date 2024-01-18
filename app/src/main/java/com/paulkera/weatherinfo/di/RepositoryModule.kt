package com.paulkera.weatherinfo.di

import com.paulkera.weatherinfo.data.location.DefaultLocationTracker
import com.paulkera.weatherinfo.data.repository.WeatherRepositoryImp
import com.paulkera.weatherinfo.domain.location.LocationTracker
import com.paulkera.weatherinfo.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImp: WeatherRepositoryImp
    ): WeatherRepository

}