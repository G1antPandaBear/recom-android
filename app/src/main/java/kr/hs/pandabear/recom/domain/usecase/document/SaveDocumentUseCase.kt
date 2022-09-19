package kr.hs.pandabear.recom.domain.usecase.document

import kotlinx.coroutines.flow.Flow
import kr.hs.pandabear.recom.domain.base.usecase.ParamsUseCase
import kr.hs.pandabear.recom.domain.model.document.Document
import kr.hs.pandabear.recom.domain.repository.DocumentRepository
import kr.hs.pandabear.recom.domain.request.SaveDocumentRequest
import kr.hs.pandabear.recom.domain.utils.Resource
import javax.inject.Inject

class SaveDocumentUseCase @Inject constructor(
    private val documentRepository: DocumentRepository
) : ParamsUseCase<SaveDocumentUseCase.Params, Document>() {

    override fun invoke(params: Params): Flow<Resource<Document>> = execute {
        documentRepository.saveDocument(
            SaveDocumentRequest(
                content = params.content,
                address = params.address,
                title = params.title
            )
        )
    }

    data class Params(
        val content: String,
        val address: String,
        val title: String
    )
}
