package com.pratclot

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import com.pratclot.domain.NewsItem
import com.pratclot.news.R
import com.pratclot.usecase.NewsUseCase
import com.pratclot.usecase.di.UseCaseModule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(UseCaseModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@LargeTest
internal class MyNewsActivityTestFailure : BaseUITest() {

    @BindValue
    @JvmField
    val newsUseCase: NewsUseCase = object : NewsUseCase {

        override suspend fun retrieveNews(): List<NewsItem> {
            TODO("Not yet implemented")
        }
    }

    @Test
    fun whenDataLoadingError_thenShowError() {
        myNews {
            displayed {
                id(R.id.error_view)
            }
        }
    }
}
