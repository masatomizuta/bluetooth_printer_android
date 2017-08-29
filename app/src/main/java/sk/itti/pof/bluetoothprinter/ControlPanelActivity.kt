package sk.itti.pof.bluetoothprinter

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class ControlPanelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_panel)
    }

    //https://stackoverflow.com/a/44810606/2144352
    fun Context.startControlPanelActivity() =
            Intent(this, ControlPanelActivity::class.java).let(this::startActivity)
}
