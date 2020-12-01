package com.example.examen;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListElement> Waifus;
    private LayoutInflater winflater;
    private Context context;

    public ListAdapter(List<ListElement> waifuList, Context context){
        this.winflater = LayoutInflater.from(context);
        this.context = context;
        this.Waifus = waifuList;
    }

    @Override
    public int getItemCount(){
        return Waifus.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = winflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(Waifus.get(position));
    }

    public void setItems(List<ListElement> waifus){
        Waifus = waifus;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name;
        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);
        }

        void bindData(final ListElement waifu){
            iconImage.setColorFilter(Color.parseColor(waifu.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(waifu.getName());
        }
    }

}
