package sk.itti.pof.bluetoothprinter

import android.app.Application
import android.os.StrictMode
import sk.itti.pof.bluetoothprinter.modules.ApplicationModule
import sk.itti.pof.bluetoothprinter.modules.BluetoothModule

/**
 * Created by Tomas on 12/08/2017.
 */
class PrinterApplication : Application() {

    val component: PrinterComponent by lazy {
        DaggerPrinterComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .bluetoothModule(BluetoothModule())
                .build()
    }

    override fun onCreate() {
        if(BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())

            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyDeath()
                    .build())
        }

        super.onCreate()
        component.inject(this)
    }
}