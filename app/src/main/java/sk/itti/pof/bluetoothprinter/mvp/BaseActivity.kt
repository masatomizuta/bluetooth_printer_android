package sk.itti.pof.bluetoothprinter.mvp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import sk.itti.pof.bluetoothprinter.PrinterApplication

/**
 * Created by SlaveMaster on 8/29/2017.
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract val TAG: String

    val application: PrinterApplication by lazy {
        getApplication() as PrinterApplication
    }

    //https://stackoverflow.com/a/44810606/2144352
    fun startControlPanelActivity() =
            Intent(this, ControlPanelActivity::class.java).let(this::startActivity)
}