package com.app.di

import com.app.ui.AppNavigator
import com.app.ui.AppNavigatorInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by Musharib Ali on 11/2/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@InstallIn(ActivityComponent::class)
@Module
abstract class AppNavigatorModule {
    @Binds
    abstract fun bindNavigator(appNavigator: AppNavigator): AppNavigatorInterface
}