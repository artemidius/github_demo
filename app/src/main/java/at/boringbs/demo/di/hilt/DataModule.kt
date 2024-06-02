package at.boringbs.demo.di.hilt

import at.boringbs.demo.domain.GetUserUseCase
import at.boringbs.demo.domain.GetUserUseCaseImpl
import at.boringbs.demo.domain.GetUsersListUseCase
import at.boringbs.demo.domain.GetUsersListUseCaseImpl
import at.boringbs.demo.domain.GitHubRepository
import at.boringbs.demo.domain.GitHubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    abstract fun provideGitHubRepository(gitHubRepositoryImpl: GitHubRepositoryImpl): GitHubRepository

    @Binds
    abstract fun bindGetUsersListUseCase(impl: GetUsersListUseCaseImpl): GetUsersListUseCase

    @Binds
    abstract fun bindGetGetUserUseCase(impl: GetUserUseCaseImpl): GetUserUseCase

}