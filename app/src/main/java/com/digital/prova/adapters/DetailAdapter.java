package com.digital.prova.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.digital.prova.R;
import com.digital.prova.jsndata.JsnDetail;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.CustomViewHolder> {
    private static final String TAG = "CustomAdapter";
    private List<JsnDetail> detailList;
    private Context context;

    public DetailAdapter(Context context, List<JsnDetail> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        final TextView txtId;
        final TextView tvTitle;
        private final ImageView iconImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtId = mView.findViewById(R.id.id_category);
            tvTitle = mView.findViewById(R.id.name);
            iconImage = mView.findViewById(R.id.img_category);
        }
    }

    @NonNull
    @Override
    public DetailAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new DetailAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.txtId.setText(detailList.get(position).getId());
        holder.tvTitle.setText(detailList.get(position).getTitle());

        RequestOptions requestOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_foreground)
                .fitCenter();
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(detailList.get(position).getImage())
                .into(holder.iconImage);

        holder.mView.setOnClickListener(v -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Title")
                    .setMessage(detailList.get(position).getTitle())
                    .setCancelable(false)
                    .setPositiveButton("OK!", (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }
}
