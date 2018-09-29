package waka.wakamarket.wakamarket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alome
 */

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.CustomViewHolder> {
    private Context mContext;
    private List<Pizza> pizzas,items;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;
    public PizzaAdapter(List<Pizza> pizzas, Context mContext) {
        this.mContext = mContext;
        this.pizzas = pizzas;


    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pizza_card, parent, false);


        return new CustomViewHolder(itemView);
    }


    /**
     *  Populate the views with appropriate Text and Images
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        final Pizza pizza = pizzas.get(position);
        Picasso.get()
                .load(pizza.getImageUrl())
                .into(holder.image);
        holder.name.setText(pizza.getName());
        holder.price.setText(pizza.getPrice());



    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder  {
        public TextView name, price;
        public ImageView image, menu;
        public int position=4;
        CardView card;
        /**
         * Constructor to initialize the Views
         *
         * @param itemView
         */
        public CustomViewHolder(final View itemView)  {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.pizzaName);
            price = (TextView) itemView.findViewById(R.id.pizzaPrice);
            image = (ImageView) itemView.findViewById(R.id.pizzaImage);
            card= (CardView) itemView.findViewById(R.id.car);
        }
    }
}
