package prasad.vennam.tmdb.kmm;


import android.app.Application;
import prasad.vennam.tmdb.kmm.di.initKoin
import org.koin.android.ext.koin.androidContext

class MovieApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MovieApplication)
        }
    }
}
