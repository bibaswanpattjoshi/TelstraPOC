package com.bibaswan.wipro_telstra.data.remote

import com.bibaswan.wipro_telstra.data.entities.CanadaInfoList
import retrofit2.Response
import retrofit2.http.GET

interface CanadaInfoService {
    @GET("facts.json")
    suspend fun getAllCharacters() : Response<CanadaInfoList>
}