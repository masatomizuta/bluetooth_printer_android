package sk.itti.pof.bluetoothprinter.dagger.modules

import android.content.Context
import com.polidea.rxandroidble.RxBleClient
import com.polidea.rxandroidble.scan.ScanFilter
import com.polidea.rxandroidble.scan.ScanSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provide all necessary bluetooth configurations and observables for BLE.
 *
 * Created by Tomas Ivan on 8/29/2017.
 */
@Module class BluetoothModule {
    @Provides @Singleton fun provideBluetooth(context: Context) =
            RxBleClient.create(context)!!

    @Provides fun provideSettings() =
            ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
                    .build()!!

    @Provides
    fun provideScanObservable(client: RxBleClient, settings: ScanSettings) =
            client.scanBleDevices(settings, ScanFilter.empty())!!

//    @Provides
//    @Named("scanDistinctObservable")
//    fun provideScanObservableDistinctByMac(client: RxBleClient, settings: ScanSettings) =
//            provideScanObservable(client, settings).distinct({ result -> result.bleDevice.macAddress })!!

    @Provides fun provideStateobservable(client: RxBleClient) =
            client.observeStateChanges().startWith(client.state)!!
}