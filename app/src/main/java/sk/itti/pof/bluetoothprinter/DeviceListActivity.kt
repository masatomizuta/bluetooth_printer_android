package sk.itti.pof.bluetoothprinter

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.polidea.rxandroidble.RxBleClient
import com.polidea.rxandroidble.scan.ScanFilter
import com.polidea.rxandroidble.scan.ScanResult
import com.polidea.rxandroidble.scan.ScanSettings
import rx.Observable
import rx.Observer
import rx.Subscription

class DeviceListActivity : AppCompatActivity() {

//    val TAG : String = "DeviceListActivity"
//    val client = RxBleClient.create(this)!!
//
//    var subscribe: Subscription? = null
//    var  observable: Observable<ScanResult>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)
    }

    override fun onResume() {
        super.onResume()

//        observable = client.scanBleDevices(
//                ScanSettings.Builder()
//                        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
//                        .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
//                        .build(),
//                ScanFilter.empty())

//        subscribe = observable?.subscribe(object: Observer<ScanResult?> {
//            override fun onError(e: Throwable?) {
//                Log.e(TAG, e.toString())
//            }
//
//            override fun onNext(t: ScanResult?) {
//                Log.d(TAG, t.toString())
//            }
//
//            override fun onCompleted() {
//                Log.d(TAG, "Scan was completed")
//            }
//        })
    }



    override fun onPause() {
        super.onPause()

//        subscribe?.unsubscribe()
    }

    @Suppress("unused")
            //https://stackoverflow.com/a/44810606/2144352
    fun Context.startDeviceListActivity() =
            Intent(this, DeviceListActivity::class.java).let(this::startActivity)
}
