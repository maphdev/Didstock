package com.example.didstock.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.didstock.data.database.dao.ReferenceDAO;
import com.example.didstock.data.database.dao.SupplierDAO;
import com.example.didstock.data.database.entity.Reference;
import com.example.didstock.data.database.entity.Supplier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Supplier.class, Reference.class}, version = 1)
public abstract class DidstockDatabase extends RoomDatabase {

    // DAO
    public abstract SupplierDAO supplierDAO();
    public abstract ReferenceDAO referenceDAO();

    // singleton
    private static volatile DidstockDatabase INSTANCE;

    public static DidstockDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DidstockDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            DidstockDatabase.class,
                            "didstock_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

    // thread pool to run database operations asynchronously on a background thread
    public static final ExecutorService databaseWriteExecutor =
            Executors.newSingleThreadExecutor();

}