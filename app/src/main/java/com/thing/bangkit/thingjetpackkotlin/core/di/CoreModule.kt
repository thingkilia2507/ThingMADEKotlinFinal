package com.thing.bangkit.thingjetpackkotlin.core.di

import androidx.room.Room
import com.thing.bangkit.thingjetpackkotlin.core.data.FilmRepository
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.LocalDataFavRepository
import com.thing.bangkit.thingjetpackkotlin.core.data.localdb.database.FilmRoomDatabase
import com.thing.bangkit.thingjetpackkotlin.core.data.remote.RemoteFilmRepository
import com.thing.bangkit.thingjetpackkotlin.core.domain.repository.IFilmRepository
import com.thing.bangkit.thingjetpackkotlin.core.helper.APIService
import com.thing.bangkit.thingjetpackkotlin.core.helper.Utility
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
        Room.databaseBuilder(androidContext(), FilmRoomDatabase::class.java,
            FilmRoomDatabase.DATABASE_NAME).fallbackToDestructiveMigration().build()
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
    single { LocalDataFavRepository(get()) }
    single { RemoteFilmRepository(get()) }
    single<IFilmRepository> { FilmRepository(get(), get()) }
}
