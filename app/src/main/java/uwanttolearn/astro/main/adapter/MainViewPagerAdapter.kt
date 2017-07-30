package uwanttolearn.astro.main.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by waleed on 25/07/2017.
 */
class HomeViewPagerAdapter(val context: Context, fm: FragmentManager, val lisOfFragments: List<Pair<Fragment, Int>>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = lisOfFragments[position].first

    override fun getCount() = lisOfFragments.size

    override fun getPageTitle(position: Int): String = context.getString(lisOfFragments[position].second)

}

class FixedViewPager : ViewPager {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onInterceptTouchEvent(ev: MotionEvent) = false
    override fun onTouchEvent(ev: MotionEvent) = false
}