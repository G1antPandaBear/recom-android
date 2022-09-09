package kr.hs.pandabear.recom.data.network.service

import kr.hs.pandabear.recom.domain.model.document.Document
import kr.hs.pandabear.recom.domain.request.SaveDocumentRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface DocumentService {

    @POST("document")
    suspend fun saveData(
        @Body saveDocumentRequest: SaveDocumentRequest
    ): Document
}
