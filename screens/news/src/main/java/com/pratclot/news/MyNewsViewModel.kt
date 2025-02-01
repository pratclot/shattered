package com.pratclot.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.pratclot.IdlingResourceProvider
import com.pratclot.IdlingResourceWrapper
import com.pratclot.common.UriValidator
import com.pratclot.domain.NewsItem
import com.pratclot.logE
import com.pratclot.throwE
import com.pratclot.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyNewsViewModel @Inject constructor(
    private val useCase: NewsUseCase,
    private val uriValidator: UriValidator,
    private val idlingResourceWrapper: IdlingResourceWrapper,
    val imageLoader: ImageLoader,
) : ViewModel(), IdlingResourceProvider by idlingResourceWrapper {

    private val _items = MutableLiveData<List<NewsItem>>()
    val items: LiveData<List<NewsItem>> get() = _items

    private val _contentLoading = MutableLiveData<Boolean>()
    val contentLoading: LiveData<Boolean> get() = _contentLoading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = _error

    private val _openLinkEvent = MutableSharedFlow<String>()
    val openLinkEvent: SharedFlow<String> get() = _openLinkEvent

    init {
        start()
    }

    /**
     * [RxJava] for sure looks more readable, at least more readable compared to monstrosities I create with [Coroutines].
     */
    private fun start() = viewModelScope.run {
        async {
            idlingResourceWrapper.setBusy()
            _contentLoading.value = true
            retrieveNews()
        }.let {
            launch {
                try {
                    updateNews(it.await())
                } catch (ex: CancellationException) {
                    throwE(ex)
                } catch (ex: Throwable) {
                    logE(ex)
                    _error.value = true
                } finally {
                    _contentLoading.value = false
                    idlingResourceWrapper.setFree()
                }
            }
        }
    }

    private fun updateNews(items: List<NewsItem>) {
        _items.value = items
    }

    private suspend fun retrieveNews(): List<NewsItem> {
        return useCase.retrieveNews()
    }

    fun refreshTriggered() {
        start()
    }

    fun userClickedOn(newsItem: NewsItem) {
        viewModelScope.launch {
            if (uriValidator.check(newsItem.news_link)) {
                _openLinkEvent.emit(newsItem.news_link)
            } else {
                _error.value = true
            }
        }
    }
}
