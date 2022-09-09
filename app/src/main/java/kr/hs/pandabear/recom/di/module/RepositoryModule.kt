package kr.hs.pandabear.recom.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.pandabear.recom.data.repository.DocumentRepositoryImpl
import kr.hs.pandabear.recom.domain.repository.DocumentRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDocumentRepository(documentRepositoryImpl: DocumentRepositoryImpl): DocumentRepository = documentRepositoryImpl

}