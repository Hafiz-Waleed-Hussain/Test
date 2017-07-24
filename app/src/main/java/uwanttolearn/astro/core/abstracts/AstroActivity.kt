package uwanttolearn.astro.core.abstracts

import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by waleed on 24/07/2017.
 */
abstract class AstroActivity : AppCompatActivity() {

    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = ProgressDialog(this) as Dialog
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
    }

    fun showProgressDialog() {
        dialog.show()

    }

    fun hideProgressDialog() {
        dialog.dismiss()
    }
}