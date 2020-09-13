package com.bibaswan.wipro_telstra.ui.canadaInfo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.bibaswan.wipro_telstra.data.repository.CanadaInfoRepository

class CanadaInfoViewModel @ViewModelInject constructor(
    private val repository: CanadaInfoRepository
) : ViewModel() {

    val infos = repository.getCanadaInfo()
}
