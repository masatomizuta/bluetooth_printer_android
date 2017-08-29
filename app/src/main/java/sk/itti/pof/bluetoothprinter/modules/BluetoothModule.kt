package sk.itti.pof.bluetoothprinter.modules

import android.content.Context
import com.polidea.rxandroidble.RxBleClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by SlaveMaster on 8/29/2017.
 */
@Module class BluetoothModule {
    @Provides @Singleton fun provideBluetooth(context: Context) = RxBleClient.create(context)!!
}