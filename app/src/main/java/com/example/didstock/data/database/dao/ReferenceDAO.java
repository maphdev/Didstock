package com.example.didstock.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.didstock.data.database.entity.Reference;

import java.util.List;

@Dao
public interface ReferenceDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Reference reference);

    @Query("SELECT * FROM reference WHERE code = :code")
    public Reference getReferenceByCode(String code);

    @Update
    public void update(Reference reference);

    @Delete
    public void delete(Reference reference);

    @Query("DELETE FROM reference WHERE code = :code")
    public void deleteByCode(String code);

    @Query("SELECT * FROM reference ORDER BY code ASC")
    public LiveData<List<Reference>> getAllReferenceOrderedByCode();

    @Query("DELETE FROM reference")
    public void deleteAll();

}
