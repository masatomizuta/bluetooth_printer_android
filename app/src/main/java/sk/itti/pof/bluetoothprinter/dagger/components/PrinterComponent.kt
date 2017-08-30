package sk.itti.pof.bluetoothprinter.dagger.components

import dagger.Component
import sk.itti.pof.bluetoothprinter.PrinterApplication
import sk.itti.pof.bluetoothprinter.dagger.modules.ApplicationModule
import sk.itti.pof.bluetoothprinter.dagger.modules.BluetoothModule
import sk.itti.pof.bluetoothprinter.mvp.ControlPanelActivity
import sk.itti.pof.bluetoothprinter.mvp.DeviceListActivity
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