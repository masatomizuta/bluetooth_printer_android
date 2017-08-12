package sk.itti.pof.bluetoothprinter

import android.app.Application
import android.os.StrictMode

/**
 * Created by Tomas on 12/08/2017.
 */
class PrinterApplication : Application() {
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
    }
}