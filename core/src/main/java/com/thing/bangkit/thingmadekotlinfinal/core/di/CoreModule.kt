package com.thing.bangkit.thingmadekotlinfinal.core.di

import androidx.room.Room
import com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.database.FilmRoomDatabase
import com.thing.bangkit.thingmadekotlinfinal.core.domain.repository.IFilmRepository
import com.thing.bangkit.thingmadekotlinfinal.core.helper.APIService
import com.thing.bangkit.thingmadekotlinfinal.core.helper.Utility
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FilmRoomDatabase>().filmDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("thing".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidContext(), FilmRoomDatabase::class.java,
            FilmRoomDatabase.DATABASE_NAME).fallbackToDestructiveMigration()
            .openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(Utility.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(APIService::class.java)
    }
}

val repositoryModule = module {
    single { com.thing.bangkit.thingmadekotlinfinal.core.data.localdb.LocalDataFavRepository(get()) }
    single { com.thing.bangkit.thingmadekotlinfinal.core.data.remote.RemoteFilmRepository(get()) }
    single<IFilmRepository> {
        com.thing.bangkit.thingmadekotlinfinal.core.data.FilmRepository(get(),
            get())
    }
}
