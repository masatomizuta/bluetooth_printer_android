package sk.itti.pof.bluetoothprinter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.polidea.rxandroidble.RxBleClient
import com.polidea.rxandroidble.scan.ScanFilter
import com.polidea.rxandroidble.scan.ScanResult
import com.polidea.rxandroidble.scan.ScanSettings
import rx.Subscription
import javax.inject.Inject

class DeviceListActivity : BaseActivity() {
    override val TAG : String = "DeviceListActivity"
    @Inject lateinit var bluetoothClient: RxBleClient

    private lateinit var subscribe: Subscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        application.component.inject(this)
    }

    override fun onResume() {
        super.onResume()

        val observable = bluetoothClient.scanBleDevices(
                ScanSettings.Builder()
                        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                        .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
                        .build(),
                ScanFilter.empty())

        subscribe = observable
                .distinct { t -> t.bleDevice.macAddress}
                .subscribe(
                        {s: ScanResult? -> Log.d(TAG, s.toString()) },
                        {e: Throwable? -> Log.e(TAG, e.toString()) }
                )
    }

    override fun onPause() {
        super.onPause()

        subscribe.unsubscribe()
    }

    @Suppress("unused")
            //https://stackoverflow.com/a/44810606/2144352
    fun Context.startDeviceListActivity() =
            Intent(this, DeviceListActivity::class.java).let(this::startActivity)
}
