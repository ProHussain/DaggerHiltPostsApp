package com.daggerhilt.postsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidApp
class BaseApp: Application() {
}