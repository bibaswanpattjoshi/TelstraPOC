package com.bibaswan.wipro_telstra.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bibaswan.wipro_telstra.data.entities.CanadaInfo
import com.bibaswan.wipro_telstra.data.entities.CanadaInfoList

@Dao
interface CanadaInfoDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(canadaInfos: CanadaInfoList)

    @Query("SELECT * FROM CanadaInfoList")
    fun getAllInfo() : LiveData<CanadaInfoList>



    @Query("DELETE FROM CanadaInfo")
    suspend fun deleteAll()


}