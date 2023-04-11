package fr.cerfcraft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private String[] mDataset;
    private LayoutInflater inflater;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(TextView v){
            super(v);
            textView = v;
        }
    }

    public SearchAdapter(Context context, String[] myDataset)
    {
        this.inflater = LayoutInflater.from(context);
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
//            View view = inflater.inflate(R.layout.search_item, parent, false);
//            return new ViewHolder((TextView) view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.textView.setText(mDataset[position]);

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
