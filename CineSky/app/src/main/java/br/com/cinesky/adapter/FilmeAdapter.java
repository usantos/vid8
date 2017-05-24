package br.com.cinesky.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import br.com.cinesky.model.Filme;
import br.com.cinesky.R;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class FilmeAdapter extends BaseAdapter {

    private List<Filme> mFilmeItens;
    private Context mContext;

    public FilmeAdapter(Context ctx, List<Filme> itens){
        mFilmeItens = itens;
        mContext = ctx;
    }

    @Override
    public int getCount() {
        return mFilmeItens.size();
    }

    @Override
    public Object getItem(int position) {
        return mFilmeItens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.gridview_item_layout, null);

            holder = new ViewHolder();

            holder.imgPhoto = (ImageView) convertView
                    .findViewById(R.id.imgPhoto);
            holder.tvDescription = (TextView) convertView
                    .findViewById(R.id.tvDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Filme item = mFilmeItens.get(position);
        if (item.getTitle().equals("")) {
            holder.tvDescription.setText("Sem descrição");
        } else {
            holder.tvDescription.setText(item.getTitle());
        }

        Picasso.with(mContext)
                .load(Uri.parse(item.getCover_url()))
                .tag(mContext)
                .resize(345,480)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.imgPhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        Picasso.with(mContext)
                                .load(Uri.parse(item.getCover_url()))
                                .error(R.drawable.ic_sky)
                                .into(holder.imgPhoto);
                    }
                });

        return convertView;
    }
    static class ViewHolder {
        ImageView imgPhoto;
        TextView tvDescription;
    }
}