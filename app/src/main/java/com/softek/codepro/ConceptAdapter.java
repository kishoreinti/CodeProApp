package com.softek.codepro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConceptAdapter extends RecyclerView.Adapter<ConceptAdapter.ViewHolder> {

    private final List<String> conceptList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String conceptName);
    }

    public ConceptAdapter(List<String> conceptList, OnItemClickListener listener) {
        this.conceptList = conceptList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_concept, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String conceptName = conceptList.get(position); // ✅ Fix: Directly get string
        holder.conceptTextView.setText(conceptName);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(conceptName));
    }

    @Override
    public int getItemCount() {
        return conceptList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView conceptTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            conceptTextView = itemView.findViewById(R.id.conceptTextView);
        }
    }
}
