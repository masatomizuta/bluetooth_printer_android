package sk.itti.pof.bluetoothprinter.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.polidea.rxandroidble.RxBleClient
import com.polidea.rxandroidble.scan.ScanResult
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
    private val SCAN_DURATION_SEC = 10

    @Inject lateinit var scanObservable: Observable<ScanResult>
    @Inject lateinit var stateObservable: Observable<RxBleClient.State>

    private var state: Subscription? = null
    private var subscribe: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)

        application.component.inject(this)

        swipeRefreshLayout.setOnRefreshListener {
            startScanning()
        }
    }

    override fun onResume() {
        super.onResume()

        state = stateObservable.subscribe(
                { state: RxBleClient.State? -> toast(state.toString()) },
                { t: Throwable? -> toast(t.toString()) }
        )
    }

    override fun onPause() {
        super.onPause()

        stopScanning()
        state?.unsubscribe()
    }

    private fun startScanning() {
        subscribe = scanObservable
                .distinct { it.bleDevice.macAddress }
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { updateScanningState(true) }
                .doOnUnsubscribe { updateScanningState(false) }
                .take(SCAN_DURATION_SEC.toLong(), TimeUnit.SECONDS)
                .subscribe (
                    {s: ScanResult? -> toast(s.toString()) },
                    {e: Throwable? -> toast(e.toString()) }
                )
    }

    private fun stopScanning() {
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
}

//https://stackoverflow.com/a/44810606/2144352
fun Context.startDeviceListActivity() =
        Intent(this, DeviceListActivity::class.java).let(this::startActivity)
