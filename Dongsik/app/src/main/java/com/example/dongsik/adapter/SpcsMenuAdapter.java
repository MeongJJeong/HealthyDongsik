package com.example.dongsik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dongsik.R;

import java.util.ArrayList;

public class SpcsMenuAdapter extends RecyclerView.Adapter<SpcsMenuAdapter.CustomViewHolder>{

    ArrayList<String> list;
    Context context;

    public SpcsMenuAdapter(ArrayList<String> list, Context context){
        this.list = list;
        this.context = context;
    }

    public interface OnItemClicklistener{
        void onItemClick(View v,int pos);
    }

    private OnItemClicklistener onItemClicklistener = null;

    public void setOnItemClicklistener(OnItemClicklistener onItemClicklistener){
        this.onItemClicklistener = onItemClicklistener;

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_spcs,viewGroup,false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.textView.setText(list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        CardView cardView;
        public CustomViewHolder(@NonNull final View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.item_spcs_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (onItemClicklistener != null){
                            onItemClicklistener.onItemClick(v,pos);
                        }
                    }
                }
            });
        }
    }
}