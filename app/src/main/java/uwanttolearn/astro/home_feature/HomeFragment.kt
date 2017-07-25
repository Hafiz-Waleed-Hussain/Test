package uwanttolearn.astro.home_feature

import android.opengl.ETC1Util
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_home.*
import uwanttolearn.astro.R
import uwanttolearn.astro.core.abstracts.AstroFragment

/**
 * Created by waleed on 25/07/2017.
 */
class HomeFragment : AstroFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater?.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activity.setSupportActionBar(HomeFeatureFragment_toolbar)
        HomeFeatureFragment_navigation_view.setCheckedItem(R.id.HomeFeature_Nav_sort_by_number)
        HomeFeatureFragment_navigation_view.setNavigationItemSelectedListener {
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
            HomeFeatureFragment_drawer_layout.closeDrawer(Gravity.RIGHT)
            true
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.home_feature, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.HomeFeatureMenu_sort -> {
            HomeFeatureFragment_drawer_layout.openDrawer(Gravity.RIGHT, true)
            true
        }
        else ->
            super.onOptionsItemSelected(item)
    }

}