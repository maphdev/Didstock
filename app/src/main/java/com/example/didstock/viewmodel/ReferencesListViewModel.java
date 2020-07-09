package com.example.didstock.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.didstock.data.database.entity.Reference;
import com.example.didstock.data.repository.ReferenceRepository;

import java.util.List;

public class ReferencesListViewModel extends AndroidViewModel {

    private ReferenceRepository referenceRepository;
    private LiveData<List<Reference>> referencesList;

    public ReferencesListViewModel(@NonNull Application application) {
        super(application);
        referenceRepository = new ReferenceRepository(application);
        referencesList = referenceRepository.getAllReferenceOrderedByCode();
    }

    public LiveData<List<Reference>> getAll() {
        return referenceRepository.getAllReferenceOrderedByCode();
    }

    public void insert(Reference reference) {
        referenceRepository.insert(reference);
    }

    public void update(Reference reference) {
        referenceRepository.update(reference);
    }

    public void delete(Reference reference) {
        referenceRepository.delete(reference);
    }

    public void deleteAll() {
        referenceRepository.deleteAll();
    }

}
