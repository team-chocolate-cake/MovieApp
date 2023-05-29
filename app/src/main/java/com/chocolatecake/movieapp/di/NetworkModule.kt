package com.chocolatecake.movieapp.di

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.remote.service.AuthInterceptor
import com.chocolatecake.movieapp.data.remote.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService{
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverter)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideClient(authInterceptor: AuthInterceptor): OkHttpClient{
       return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }
}