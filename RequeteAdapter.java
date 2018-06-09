package com.droidmentor.mouroujnaClean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by lenovo on 10/04/2018.
 */

public class RequeteAdapter extends RecyclerView.Adapter<RequeteAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<requete> requeteliste;

    public RequeteAdapter(Context mCtx, List<requete> requeteliste) {
        this.mCtx = mCtx;
        this.requeteliste = requeteliste;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_demande, null);
        return new ProductViewHolder(view);
    }
    public Bitmap ByteArrayToBitmap(byte[] byteArray)
    {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
        return bitmap;
    }
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        requete requete = requeteliste.get(position);
         byte[] im= Base64.decode(requete.getImage(),0);
        byte[] imgByte = requete.getImage().getBytes(StandardCharsets.UTF_8);

        //loading the image
        InputStream stream = new ByteArrayInputStream(imgByte);
        //Toast.makeText(getApplicationContext(),stream.toString(), Toast.LENGTH_SHORT).show();

        Bitmap  image = ByteArrayToBitmap(im);
        Picasso.with(mCtx)
                .load(requete.getImage())
                .resize(50, 50)
                .centerCrop()
                .into(holder.imageView);
       holder.imageView.setImageBitmap(image);

        holder.textViewTitle.setText(requete.getNom());
        holder.textViewShortDesc.setText(requete.getDate());
        holder.textViewRating.setText(requete.getReq());
        holder.textViewPrice.setText(requete.getEtat());
    }

    @Override
    public int getItemCount() {
        return requeteliste.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
