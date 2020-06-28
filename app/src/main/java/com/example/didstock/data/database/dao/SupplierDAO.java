package com.example.didstock.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.didstock.data.database.entity.Supplier;

import java.util.List;

@Dao
public interface SupplierDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Supplier supplier);

    @Query("SELECT * FROM supplier WHERE id = :id")
    public Supplier getById(Integer id);

    @Update
    public void update(Supplier supplier);

    @Query("DELETE FROM supplier WHERE id = :id")
    public void deleteById(Integer id);

    @Query("SELECT * FROM supplier ORDER BY name ASC")
    public LiveData<List<Supplier>> getAllOrderedByName();

    @Query("DELETE FROM supplier")
    public void deleteAll();

}