package kr.hs.pandabear.recom.data.network.remote

import kr.hs.pandabear.recom.data.network.service.DocumentService
import kr.hs.pandabear.recom.domain.model.document.Document
import kr.hs.pandabear.recom.domain.request.SaveDocumentRequest
import javax.inject.Inject

class DocumentRemote @Inject constructor(
    private val service: DocumentService
) {

    suspend fun saveDocument(saveDocumentRequest: SaveDocumentRequest): Document =
        service.saveData(saveDocumentRequest)
}