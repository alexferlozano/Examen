package com.example.examen;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListElement> Waifus;
    private LayoutInflater winflater;
    private Context context;
    private Activity activity;
    public final int REQUEST_CODE_ASK_PERMISSIONS = 1001;

    public ListAdapter(List<ListElement> waifuList, Context context, Activity activity){
        this.winflater = LayoutInflater.from(context);
        this.context = context;
        this.Waifus = waifuList;
        this.activity = activity;
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
        Switch status;
        private static final int REQUEST_CODE_ASK_PERMISSION = 123;
        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);
            status = itemView.findViewById(R.id.statusSwitch);
        }

        void bindData(final ListElement waifu){
            iconImage.setColorFilter(Color.parseColor(waifu.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(waifu.getName());
            status.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
//                    Toast.makeText(context, "Se presionó el boton para el permiso " + waifu.getPermission(),Toast.LENGTH_SHORT).show();
//                    requestPermissions((Activity) context, new String[]{"Manifest.permission."+waifu.getPermission()}, REQUEST_CODE_ASK_PERMISSION);
                    int p = ActivityCompat.checkSelfPermission(activity, "Manifest.permission."+waifu.getPermission());
                    if (p == 0) {
                        Toast.makeText(context, "This permission is already given", Toast.LENGTH_SHORT).show();
                    } else{
                        requestPermission(waifu);
                    }
                }
            });
        }

        void requestPermission(ListElement waifu){
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, "Manifest.permission."+waifu.getPermission())) {
                Toast.makeText(context, "Jala verga", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle(waifu.getPermission());
                alert.setMessage("Se solicita permiso para: "+waifu.getPermission());
                alert.setPositiveButton("ok", (dialog, which) -> ActivityCompat.requestPermissions(activity,
                        new String[]{"Manifest.permission."+waifu.getPermission()}, REQUEST_CODE_ASK_PERMISSIONS));
                alert.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
                alert.create().show();
            } else {
                Toast.makeText(context, "Jala puta", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(activity,
                        new String[]{"Manifest.permission."+waifu.getPermission()}, REQUEST_CODE_ASK_PERMISSIONS);
            }
        }
    }

}
