package sk.itti.pof.bluetoothprinter.mvp.view

/**
 * Created by SlaveMaster on 8/30/2017.
 */
interface DeviceListView : BaseView {
    fun updateScanningState(scanning: Boolean)
    fun navigateToDeviceDetail()
}