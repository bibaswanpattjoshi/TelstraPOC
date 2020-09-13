package com.bibaswan.wipro_telstra.data.remote

import javax.inject.Inject

class CanadaInfoRemoteDataSource @Inject constructor(private val canadaInfoService: CanadaInfoService) :
    BaseDataSource() {

    suspend fun getInfos() = getResult { canadaInfoService.getAllCharacters() }
}