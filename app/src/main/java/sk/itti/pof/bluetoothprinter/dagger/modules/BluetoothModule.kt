package sk.itti.pof.bluetoothprinter.dagger.modules

import android.content.Context
import com.polidea.rxandroidble.RxBleClient
import com.polidea.rxandroidble.scan.ScanFilter
import com.polidea.rxandroidble.scan.ScanSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by SlaveMaster on 8/29/2017.
 */
@Module class BluetoothModule {
    @Provides @Singleton fun provideBluetooth(context: Context) =
            RxBleClient.create(context)!!

    @Provides fun provideSettings() =
            ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
            .build()

    fun provideScanObservable(client: RxBleClient, settings: ScanSettings) =
            client.scanBleDevices(settings, ScanFilter.empty())

    @Provides fun provideScanObservableDistinctByMac(client: RxBleClient, settings: ScanSettings) =
            provideScanObservable(client, settings).distinct({ result -> result.bleDevice.macAddress })

    @Provides fun provideStateobservable(client: RxBleClient) =
            client.observeStateChanges().startWith(client.state)
}