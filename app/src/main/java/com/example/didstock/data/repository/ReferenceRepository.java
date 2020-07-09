package com.example.didstock.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.didstock.data.database.DidstockDatabase;
import com.example.didstock.data.database.dao.ReferenceDAO;
import com.example.didstock.data.database.entity.Reference;

import java.util.List;

public class ReferenceRepository {

    private ReferenceDAO referenceDAO;

    public ReferenceRepository(Application application) {
        DidstockDatabase db = DidstockDatabase.getDatabase(application);
        referenceDAO = db.referenceDAO();
    }

    public void insert(Reference reference) {
        DidstockDatabase.databaseWriteExecutor.execute(() -> referenceDAO.insert(reference));
    }

    public Reference getReferenceByCode(String code) {
        Reference reference;
        try {
            reference = DidstockDatabase.databaseWriteExecutor.submit(() -> referenceDAO.getReferenceByCode(code)).get();
        } catch (Exception e) {
            reference = null;
        }
        return reference;
    }

    public void update(Reference reference) {
        DidstockDatabase.databaseWriteExecutor.execute(() -> referenceDAO.update(reference));
    }
    public void delete(Reference reference) {
        DidstockDatabase.databaseWriteExecutor.execute(() -> referenceDAO.delete(reference));
    }

    public void deleteByCode(String code) {
        DidstockDatabase.databaseWriteExecutor.execute(() -> referenceDAO.deleteByCode(code));
    }

    public LiveData<List<Reference>> getAllReferenceOrderedByCode() {
        return referenceDAO.getAllReferenceOrderedByCode();
    }

    public void deleteAll() {
        DidstockDatabase.databaseWriteExecutor.execute(() -> referenceDAO.deleteAll());
    }
}
