package com.example.didstock.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.didstock.data.database.DidstockDatabase;
import com.example.didstock.data.database.dao.SupplierDAO;
import com.example.didstock.data.database.entity.Supplier;

import java.util.List;

public class SupplierRepository {

    private SupplierDAO supplierDAO;

    public SupplierRepository(Application application) {
        DidstockDatabase db = DidstockDatabase.getDatabase(application);
        supplierDAO = db.supplierDAO();
    }

    public void insert(Supplier supplier) {
        DidstockDatabase.databaseWriteExecutor.execute(() -> supplierDAO.insert(supplier));
    }

    public Supplier getById(Integer id) {
        Supplier supplier;
        try {
            supplier = DidstockDatabase.databaseWriteExecutor.submit(() -> supplierDAO.getById(id)).get();
        } catch (Exception e) {
            supplier = null;
        }
        return supplier;
    }

    public void update(Supplier supplier) {
        DidstockDatabase.databaseWriteExecutor.execute(() -> supplierDAO.update(supplier));
    }

    public void deleteById(Integer id) {
        DidstockDatabase.databaseWriteExecutor.execute(() -> supplierDAO.deleteById(id));
    }

    public LiveData<List<Supplier>> getAllOrderedByName() {
        return supplierDAO.getAllOrderedByName();
    }

    public void deleteAll() {
        DidstockDatabase.databaseWriteExecutor.execute(() -> supplierDAO.deleteAll());
    }

}
