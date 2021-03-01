package com.app.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.data.model.BookedOrder

/**
 * Created by Musharib Ali on 11/2/21
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@Dao
interface OrderHistroyListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookListModel: BookedOrder)

    @Query("Select * from history_table ORDER BY time DESC")
    fun getHistoryList(): LiveData<List<BookedOrder>>

}