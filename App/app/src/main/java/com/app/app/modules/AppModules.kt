package com.app.app.modules

import com.app.app.repository.MovieRepository
import com.app.app.repository.RepositoryImpl
import com.app.app.service.Service
import com.app.app.ui.viewmodel.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
    }
}

val serviceModule = module {
    single { get<Retrofit>().create(Service::class.java) }
}

val repositoryModule = module {
    single<MovieRepository> { RepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { ViewModel(get()) }
}