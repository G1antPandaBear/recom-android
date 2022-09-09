package kr.hs.pandabear.recom.data.remote.service

import kr.hs.pandabear.recom.domain.model.document.Document
import kr.hs.pandabear.recom.domain.request.SaveDataRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface DocumentService {
    @POST("document")
    suspend fun saveData(
        @Body saveDataRequest: SaveDataRequest
    ): Document
}