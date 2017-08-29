package sk.itti.pof.bluetoothprinter.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import sk.itti.pof.bluetoothprinter.PrinterApplication
import javax.inject.Singleton

/**
 * Created by SlaveMaster on 8/29/2017.
 */
@Module class ApplicationModule(val app: PrinterApplication) {
    @Provides @Singleton fun provideApplication() = app
    @Provides @Singleton fun provideContext() : Context = app
}