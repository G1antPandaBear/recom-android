package kr.hs.pandabear.recom.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.pandabear.recom.network.service.DocumentService
import kr.hs.pandabear.recom.viewmodel.fragment.SoloViewModel
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Singleton
    @Provides
    fun provideSoloViewModel(retrofit: Retrofit): SoloViewModel =
        SoloViewModel(retrofit.create(DocumentService::class.java))
}