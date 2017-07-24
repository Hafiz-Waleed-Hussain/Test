package uwanttolearn.astro.core.data.source

import io.reactivex.Observable
import java.util.*

/**
 * Created by waleed on 24/07/2017.
 */
interface AstroRepositoryDataSource {

    fun getAllChannels(): Observable<Any>
}