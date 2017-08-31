package sk.itti.pof.bluetoothprinter.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.polidea.rxandroidble.RxBleClient
import com.polidea.rxandroidble.scan.ScanFilter
import com.polidea.rxandroidble.scan.ScanResult
import com.polidea.rxandroidble.scan.ScanSettings
import kotlinx.android.synthetic.main.activity_device_list.*
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import sk.itti.pof.bluetoothprinter.R
import sk.itti.pof.bluetoothprinter.extensions.toast
import sk.itti.pof.bluetoothprinter.mvp.view.DeviceListView
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DeviceListActivity : BaseActivity(), DeviceListView {

    override val TAG : String = "DeviceListActivity"
    @Inject lateinit var bluetoothClient: RxBleClient

    private val settings: ScanSettings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
            .build()

    private val observable: Observable<ScanResult> by lazy {
        bluetoothClient.scanBleDevices(settings, ScanFilter.empty())
                .distinct { t -> t.bleDevice.macAddress}
                .doOnUnsubscribe { runOnUiThread { updateScanningState(false) } }
                .doOnSubscribe { runOnUiThread { updateScanningState(true) } }
    }

    private var state: Subscription? = null
    private var subscribe: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        application.component.inject(this)

        swipeRefreshLayout.setOnRefreshListener {
            startScanning()
        }

        state = bluetoothClient.observeStateChanges()
                .startWith(bluetoothClient.state)
                .subscribe(
                        { state: RxBleClient.State? -> toast(state.toString()) },
                        { t: Throwable? -> toast(t.toString()) }
                )
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()

        stopScanning()
        state?.unsubscribe()
    }

    fun startScanning() {
        subscribe = observable
                .take(10, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {s: ScanResult? -> Log.d(TAG, s.toString()) },
                    {e: Throwable? -> Log.e(TAG, e.toString()) }
                )
    }

    fun stopScanning() {
        subscribe?.unsubscribe()
        subscribe = null
    }

    override fun updateScanningState(scanning: Boolean) {
        toast(scanning.toString())
        swipeRefreshLayout.isRefreshing = scanning
    }

    override fun navigateToDeviceDetail() {
        startControlPanelActivity()
    }

    @Suppress("unused")
            //https://stackoverflow.com/a/44810606/2144352
    fun Context.startDeviceListActivity() =
            Intent(this, DeviceListActivity::class.java).let(this::startActivity)
}
