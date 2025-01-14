package prasad.vennam.tmdb.kmm.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import prasad.vennam.tmdb.kmm.core.data.HttpClientFactory
import prasad.vennam.tmdb.kmm.movie.data.network.RemoteDataSource
import prasad.vennam.tmdb.kmm.movie.data.network.RemoteDataSourceImpl
import prasad.vennam.tmdb.kmm.movie.data.repository.DefaultRepository
import prasad.vennam.tmdb.kmm.movie.domain.MovieRepository
import prasad.vennam.tmdb.kmm.movie.presentation.movie_details.MovieDetailsViewmodel
import prasad.vennam.tmdb.kmm.movie.presentation.movie_list.MovieListViewmodel

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::RemoteDataSourceImpl).bind<RemoteDataSource>()
    singleOf(::DefaultRepository).bind<MovieRepository>()
    viewModelOf(::MovieListViewmodel)
    viewModelOf(::MovieDetailsViewmodel)
}