package sk.itti.pof.bluetoothprinter.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by SlaveMaster on 8/31/2017.
 */

fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()