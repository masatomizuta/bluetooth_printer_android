package sk.itti.pof.bluetoothprinter

import dagger.Component
import sk.itti.pof.bluetoothprinter.modules.ApplicationModule
import sk.itti.pof.bluetoothprinter.modules.BluetoothModule
import javax.inject.Singleton

/**
 * Created by SlaveMaster on 8/29/2017.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, BluetoothModule::class))
interface PrinterComponent {
    fun inject(app: PrinterApplication)

    fun inject(activity: ControlPanelActivity)
    fun inject(activity: DeviceListActivity)
}