package kr.hs.pandabear.recom.data.repository

import kr.hs.pandabear.recom.data.network.remote.DocumentRemote
import kr.hs.pandabear.recom.domain.model.document.Document
import kr.hs.pandabear.recom.domain.repository.DocumentRepository
import kr.hs.pandabear.recom.domain.request.SaveDocumentRequest
import javax.inject.Inject

class DocumentRepositoryImpl @Inject constructor(
    private val documentRemote: DocumentRemote,
) : DocumentRepository {

    override suspend fun saveDocument(saveDocumentRequest: SaveDocumentRequest): Document {
        return documentRemote.saveDocument(saveDocumentRequest)
    }

}