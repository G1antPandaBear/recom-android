package kr.hs.pandabear.recom.domain.base.usecase

import kotlinx.coroutines.flow.Flow
import kr.hs.pandabear.recom.domain.utils.Resource

abstract class ParamsUseCase<PR, R> : BaseUseCase<R>() {

    abstract operator fun invoke(params: PR): Flow<Resource<R>>
}
