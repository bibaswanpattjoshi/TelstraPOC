package com.bibaswan.wipro_telstra.data.local

import android.content.Context
import androidx.room.*
import com.bibaswan.wipro_telstra.data.entities.CanadaInfo
import com.bibaswan.wipro_telstra.data.entities.CanadaInfoList
import com.bibaswan.wipro_telstra.data.entities.InfoTypeConverter

@Database(entities = [CanadaInfo::class,CanadaInfoList::class], version = 9, exportSchema = false)
@TypeConverters(InfoTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CanadaInfoDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "CanadaInfos")
                .fallbackToDestructiveMigration()
                .build()
    }

}