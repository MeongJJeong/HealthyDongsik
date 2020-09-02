package com.example.dongsik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dongsik.R;
import com.example.dongsik.database.Dictionary;

import java.util.ArrayList;

public class ChronoViewAdapter extends RecyclerView.Adapter<ChronoViewAdapter.CustomViewHolder> {

    ArrayList<Dictionary> list;
    Context context;

    public ChronoViewAdapter(ArrayList<Dictionary> list, Context context){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_chrono, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //holder.linearLayout.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_out_anim));
        holder.num.setText(position+1+"");
        holder.spc.setText(list.get(position).getSpecies());
        holder.time.setText(list.get(position).getChrono_time());

    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView num,spc,time;
        LinearLayout linearLayout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            num = (TextView)itemView.findViewById(R.id.item_chrono_num);
            spc = (TextView)itemView.findViewById(R.id.item_chrono_interval);
            time = (TextView)itemView.findViewById(R.id.item_chrono_time);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.flipper_chrono_LinearLayout);
        }
    }
}
