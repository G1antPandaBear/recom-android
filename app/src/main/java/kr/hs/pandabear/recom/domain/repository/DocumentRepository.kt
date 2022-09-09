package kr.hs.pandabear.recom.domain.repository

import kr.hs.pandabear.recom.domain.model.document.Document
import kr.hs.pandabear.recom.domain.request.SaveDocumentRequest

interface DocumentRepository {

    suspend fun saveDocument(saveDocumentRequest: SaveDocumentRequest): Document

}