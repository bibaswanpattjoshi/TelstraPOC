package com.bibaswan.wipro_telstra.data.repository

import com.bibaswan.wipro_telstra.data.local.CanadaInfoDao
import com.bibaswan.wipro_telstra.data.remote.CanadaInfoRemoteDataSource
import com.bibaswan.wipro_telstra.utils.performGetOperation
import javax.inject.Inject

class CanadaInfoRepository @Inject constructor(
    private val remoteDataSource: CanadaInfoRemoteDataSource,
    private val localDataSource: CanadaInfoDao
) {


    fun getCanadaInfo() = performGetOperation(
        databaseQuery = { localDataSource.getAllInfo() },
        networkCall = { remoteDataSource.getInfos() },
        saveCallResult = { localDataSource.insertAll(it) },
        deleteCallResult = { localDataSource.deleteAll() }
    )
}