package uwanttolearn.astro.core.data.pojos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by waleed on 26/07/2017.
 */
open class ChannelInfo(
        @PrimaryKey var channelId: Int = -1,
        var channelTitle: String = "",
        var channelStbNumber: Int = -1,
        var url: String = "",
        var isSave: Boolean = false) : RealmObject()