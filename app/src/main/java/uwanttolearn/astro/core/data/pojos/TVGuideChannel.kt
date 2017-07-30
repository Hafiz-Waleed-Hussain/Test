package uwanttolearn.astro.core.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by waleed on 29/07/2017.
 */
data class TVGuideChannel(val channelId: Long,
                     val channelStbNumber: Long,
                     val channelTitle: String,
                     val url: String,
                     val events: MutableList<TVGuideEvent>)


data class TVGuideEvent(val displayDateTime: String,
                   val programmeTitle: String,
                   val programmeId: String)