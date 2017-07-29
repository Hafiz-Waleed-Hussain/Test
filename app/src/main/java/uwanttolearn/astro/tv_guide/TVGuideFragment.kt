package uwanttolearn.astro.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uwanttolearn.astro.R
import uwanttolearn.astro.core.abstracts.AstroFragment
import uwanttolearn.astro.core.data.pojos.TVGuideChannel
import uwanttolearn.astro.core.data.pojos.TVGuideEvent

/**
 * Created by waleed on 25/07/2017.
 */
class TVGuideFragment : AstroFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_tv_guide, container, false)
    }


//    val l = listOf(1,2,3,4,5,6,7,8,9)
//
//
//
//    astroRepository.getTodayEvents(l.map { "$it" })
//    .subscribeOn(Schedulers.io())
//    .observeOn(AndroidSchedulers.mainThread())
//    .subscribe {
//        val tvGuide: MutableList<TVGuideChannel> = arrayListOf()
//
//        for (i in l) {
//            val map = it.getevent.filter { it.channelId.toInt() == i }
//                    .map {
//                        TVGuideEvent(it.displayDateTime, it.programmeTitle, it.programmeId)
//                    }
//            val first = it.getevent.filter { it.channelId.toInt() == i }.first()
//            tvGuide.add(
//                    TVGuideChannel(first.channelId, first.channelStbNumber.toLong(), first.channelTitle, "", map))
//        }
//
//    }
}