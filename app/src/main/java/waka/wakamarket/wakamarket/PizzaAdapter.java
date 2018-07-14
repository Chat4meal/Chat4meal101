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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alome
 */

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.CustomViewHolder> {
    private Context mContext;
    private List<Integer> selectedIds = new ArrayList<>();
    private List<Pizza> contactListFiltered;
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
        holder.name.setText(pizza.getName());
        holder.price.setText(pizza.getPrice());
        holder.image.setImageResource(pizza.getImageResource());


        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

    }





    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    contactListFiltered = pizzas;
                } else {
                    List<Pizza> filteredList = new ArrayList<>();
                    for (Pizza row :pizzas ) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getPrice().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    contactListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = contactListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                contactListFiltered = (ArrayList<Pizza>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    public void setSelectedIds(List<Integer> selectedIds) {
        this.selectedIds = selectedIds;
        notifyDataSetChanged();
    }
    /**
     * Display options on click of menu icon (3 dots)
     *
     * @param view
     */
    private void showOptionsMenu(View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.men, popup.getMenu());
        popup.setOnMenuItemClickListener(new PizzaMenuItemClickListener());
        popup.show();
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
            menu = (ImageView) itemView.findViewById(R.id.menuDots);
            card= (CardView) itemView.findViewById(R.id.car);
        }
    }




    private class PizzaMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        /**
         * Display Toast message on click of the options in the menu
         *
         * @param item
         * @return
         */
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_add_favourite:

                    Toast.makeText(mContext, "Added to favourite"  , Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.order_now:
                    Intent intent = new Intent(mContext, Main3Activity.class);
                  mContext.startActivity(intent);




            }
            return false;
        }
    }


}
