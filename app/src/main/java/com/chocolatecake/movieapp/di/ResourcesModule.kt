package com.chocolatecake.movieapp.di

import com.chocolatecake.bases.NavigationRes
import com.chocolatecake.bases.StringsRes
import com.chocolatecake.movieapp.resourses_helper.NavigationResImpl
import com.chocolatecake.movieapp.resourses_helper.StringsResImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourcesModule {

    @Binds
    @Singleton
    abstract fun bindStringsRes(stringsResImpl: StringsResImpl): StringsRes

    @Binds
    @Singleton
    abstract fun bindNavigationRes(navigationResImpl: NavigationResImpl): NavigationRes
}