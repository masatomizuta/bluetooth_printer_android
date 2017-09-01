package sk.itti.pof.bluetoothprinter.mvp

import android.support.v7.app.AppCompatActivity
import sk.itti.pof.bluetoothprinter.PrinterApplication

/**
 * Base activity class for every other project Activities.
 *
 * Created by Tomas Ivan on 8/29/2017.
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract val TAG: String

    val application: PrinterApplication by lazy {
        getApplication() as PrinterApplication
    }
}