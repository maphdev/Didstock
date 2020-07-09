package com.example.didstock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.didstock.R;
import com.example.didstock.data.database.entity.Reference;

import java.util.ArrayList;
import java.util.List;

public class ReferenceAdapter extends RecyclerView.Adapter<ReferenceAdapter.ReferenceHolder> {

    private List<Reference> referencesList = new ArrayList<>();

    @NonNull
    @Override
    public ReferenceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reference_item, parent, false);
        return new ReferenceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferenceHolder holder, int position) {
        Reference currentReference = referencesList.get(position);
        holder.textViewTitle.setText(currentReference.getDescription());
        holder.textViewSubtitle1.setText(currentReference.getSupplierId().toString());
        holder.textViewSubtitle2.setText(currentReference.getCode());
    }

    @Override
    public int getItemCount() {
        return referencesList.size();
    }

    public void setReferencesList(List<Reference> referencesList) {
        this.referencesList = referencesList;
        notifyDataSetChanged();
    }

    class ReferenceHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewSubtitle1;
        private TextView textViewSubtitle2;

        public ReferenceHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.reference_item_tv_title);
            textViewSubtitle1 = (TextView) itemView.findViewById(R.id.reference_item_tv_subtitle1);
            textViewSubtitle2 = (TextView) itemView.findViewById(R.id.reference_item_tv_subtitle2);
        }

    }
}
