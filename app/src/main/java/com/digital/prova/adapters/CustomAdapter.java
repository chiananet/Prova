package com.digital.prova.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.digital.prova.ListDetailActivity;
import com.digital.prova.R;
import com.digital.prova.jsndata.JsnData;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private static final String TAG = "CustomAdapter";
    private final List<JsnData> dataList;
    private final Context context;

    public CustomAdapter(Context context, List<JsnData> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        final TextView txtIdCategory;
        final TextView tvName;
        final ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtIdCategory = mView.findViewById(R.id.id_category);
            tvName = mView.findViewById(R.id.name);
            coverImage = mView.findViewById(R.id.img_category);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtIdCategory.setText(dataList.get(position).getIdCategory());
        holder.tvName.setText(dataList.get(position).getName());

        RequestOptions requestOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_foreground)
                .fitCenter();
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList.get(position).getImageCategory())
                .into(holder.coverImage);

        holder.mView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ListDetailActivity.class);
            intent.putExtra("getIdCategory", dataList.get(position).getIdCategory());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
