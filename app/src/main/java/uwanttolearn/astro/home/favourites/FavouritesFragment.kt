package uwanttolearn.astro.home.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uwanttolearn.astro.R
import uwanttolearn.astro.core.abstracts.AstroFragment

/**
 * Created by waleed on 25/07/2017.
 */
class FavouritesFragment : AstroFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_favourites, container, false)
    }
}