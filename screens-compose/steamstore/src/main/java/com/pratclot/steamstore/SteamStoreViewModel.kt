package com.pratclot.steamstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.pratclot.domain.SteamStoreItemDomain
import com.pratclot.logE
import com.pratclot.steamstore.data.SteamStoreItemUi
import com.pratclot.throwE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SteamStoreViewModel @Inject constructor(
    private val steamStoreUseCase: SteamStoreUseCase,
    val imageLoader: ImageLoader,
) : ViewModel() {

    val loadingState = MutableStateFlow(false)
    private val _items = MutableLiveData<List<SteamStoreItemUi>>()
    val items: LiveData<List<SteamStoreItemUi>> get() = _items

    init {
        start()
    }

    private fun start() {
        viewModelScope.async {
            loadingState.value = true
//            steamStoreUseCase.getBrutalLegend()
            steamStoreUseCase.getBrutalLegendShuffled()
        }.run {
            viewModelScope.launch {
                try {
                    updateData(await())
                } catch (ex: CancellationException) {
                    throwE(ex)
                } catch (ex: Throwable) {
                    logE(ex)
                } finally {
                    loadingState.value = false
                }
            }
        }
    }

    private fun updateData(list: List<SteamStoreItemDomain>) {
        _items.value = list.map(::mapToUiItems)
    }

    fun refreshRequested() {
        start()
    }

    private fun mapToUiItems(steamStoreItem: SteamStoreItemDomain): SteamStoreItemUi =
        with(steamStoreItem) {
            SteamStoreItemUi(
                name,
                img,
            )
        }
}
