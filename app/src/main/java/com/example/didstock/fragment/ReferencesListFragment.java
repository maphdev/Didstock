package com.example.didstock.fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.didstock.R;
import com.example.didstock.adapter.ReferenceAdapter;
import com.example.didstock.data.database.entity.Reference;
import com.example.didstock.databinding.FragmentReferencesListBinding;
import com.example.didstock.viewmodel.ReferencesListViewModel;

import java.util.List;

public class ReferencesListFragment extends Fragment {

    private ReferencesListViewModel referencesListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_references_list, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        ReferenceAdapter adapter = new ReferenceAdapter();
        recyclerView.setAdapter(adapter);

        referencesListViewModel = ViewModelProviders.of(this).get(ReferencesListViewModel.class);
        referencesListViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Reference>>() {
            @Override
            public void onChanged(List<Reference> references) {
                Log.i("NUMBER", Integer.toString(references.size()));
                adapter.setReferencesList(references);
            }
        });

        return rootView;
    }
}
