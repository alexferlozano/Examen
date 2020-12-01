package com.example.examen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Build;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.drawable.DrawableUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListElement> Waifus;
    private LayoutInflater winflater;
    private Context context;
    private Activity activity;
    final private int REQUEST_CALL_PHONE = 10;
    final private int REQUEST_CAMERA = 11;

    public ListAdapter(List<ListElement> waifuList, Context context, Activity activity) {
        this.winflater = LayoutInflater.from(context);
        this.context = context;
        this.Waifus = waifuList;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return Waifus.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = winflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(Waifus.get(position));
    }

    public void setItems(List<ListElement> waifus) {
        Waifus = waifus;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView name;
        Switch status;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);
            status = itemView.findViewById(R.id.statusSwitch);
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        void bindData(final ListElement waifu) {
            InputStream x = new InputStream() {
                @Override
                public int read() throws IOException {
                    return 0;
                }
            };
            iconImage.setImageResource(R.drawable.ic_admin_panel_settings_black_24dp);
            iconImage.setColorFilter(Color.parseColor(waifu.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(waifu.getName());
            status.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    int p = ActivityCompat.checkSelfPermission(context, waifu.getPermission());
                    Toast.makeText(context, waifu.getPermission(), Toast.LENGTH_SHORT).show();

                    if (p == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(context, "This permission is already given", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            switch (waifu.getPermission()) {
                                case Manifest.permission.CALL_PHONE:
                                    activity.requestPermissions(new String[]{waifu.getPermission()}, REQUEST_CALL_PHONE);
                                    return;
                                case Manifest.permission.CAMERA:
                                    Toast.makeText(context, "Bolas", Toast.LENGTH_SHORT).show();
                                    activity.requestPermissions(new String[]{waifu.getPermission()}, REQUEST_CAMERA);
                                    return;
                            }
                        }
                    }
                }
            });
        }

        /*void requestPermission(ListElement waifu){
            //if (ActivityCompat.shouldShowRequestPermissionRationale(activity, waifu.getPermission())) {
                Toast.makeText(context, "Jala verga", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle(waifu.getPermission());
                alert.setMessage("Se solicita permiso para: "+waifu.getPermission());
                alert.setPositiveButton("ok", (dialog, which) -> ActivityCompat.requestPermissions(activity,
                        new String[]{"Manifest.permission."+waifu.getPermission()}, REQUEST_CODE_ASK_PERMISSIONS));
                alert.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
                alert.create().show();
            /*} else {
                Toast.makeText(context, "Jala puta", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(activity,
                        new String[]{waifu.getPermission()}, REQUEST_CODE_ASK_PERMISSIONS);
            }*/
    }
}
