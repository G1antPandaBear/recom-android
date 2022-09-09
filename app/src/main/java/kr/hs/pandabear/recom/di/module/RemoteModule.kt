package kr.hs.pandabear.recom.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.pandabear.recom.data.network.remote.DocumentRemote
import kr.hs.pandabear.recom.data.network.service.DocumentService
import kr.hs.pandabear.recom.presentation.feature.solo.SoloViewModel
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideSoloViewModel(retrofit: Retrofit): DocumentRemote =
        DocumentRemote(retrofit.create(DocumentService::class.java))


}