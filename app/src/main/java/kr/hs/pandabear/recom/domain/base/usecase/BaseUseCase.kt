package kr.hs.pandabear.recom.domain.base.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.pandabear.recom.domain.utils.Resource
import retrofit2.HttpException
import java.io.IOException

open class BaseUseCase<R> {

    fun execute(action: suspend () -> R): Flow<Resource<R>> = flow {
        try {
            emit(Resource.Loading<R>())
            val result = action.invoke()
            emit(Resource.Success<R>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<R>(e.message ?: "네트워크에 연결할 수 없습니다."))
        } catch (e: IOException) {
            emit(Resource.Error<R>("알 수 없는 에러가 발생했습니다."))
        }
    }
}
