package uwanttolearn.astro.core.abstracts

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by waleed on 25/07/2017.
 */
abstract class AstroFragment : Fragment() {

    protected lateinit var activity: AstroActivity
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as AstroActivity
        setHasOptionsMenu(true)
    }
}