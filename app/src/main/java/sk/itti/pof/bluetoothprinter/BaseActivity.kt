package sk.itti.pof.bluetoothprinter

import android.support.v7.app.AppCompatActivity

/**
 * Created by SlaveMaster on 8/29/2017.
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract val TAG: String

    val application: PrinterApplication by lazy {
        getApplication() as PrinterApplication
    }
}