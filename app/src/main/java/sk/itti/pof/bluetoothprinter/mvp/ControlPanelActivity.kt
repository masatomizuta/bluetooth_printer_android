package sk.itti.pof.bluetoothprinter.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import sk.itti.pof.bluetoothprinter.R

class ControlPanelActivity : BaseActivity() {
    override val TAG: String = "ControlPanelActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_panel)
    }
}

fun Context.startControlPanelActivity() =
        Intent(this, DeviceListActivity::class.java).let(this::startActivity)