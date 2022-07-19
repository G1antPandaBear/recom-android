package kr.hs.pandabear.recom.network.service

import kr.hs.pandabear.recom.network.model.speech.Document
import kr.hs.pandabear.recom.network.response.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DocumentService {
    @POST("document/")
    suspend fun saveData(
        @Body content: String
    ): Response<Document>
}