package at.boringbs.demo.di.hilt

import at.boringbs.demo.network.retrofit.GitHubService
import at.boringbs.demo.network.retrofit.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofitProvider(): RetrofitProvider = RetrofitProvider()

    @Singleton
    @Provides
    fun provideGitHubService(provider: RetrofitProvider): GitHubService =
        provider.retrofitService
}