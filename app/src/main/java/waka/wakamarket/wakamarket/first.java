package waka.wakamarket.wakamarket;

/**
 * Created by Muhammad on 4/19/2018.
 */
import android.app.Dialog;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class first extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_scrolling,
                container, false);


        final List<Pizza> pizzas = new ArrayList<>();
        ArrayList<String> items=new ArrayList<>();
        RecyclerView recyclerView;
        final PizzaAdapter pAdapter;
         SearchView searchView;
        final ImageView proImage;

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        pAdapter = new PizzaAdapter(pizzas, getActivity());
        RelativeLayout relativeLayout;
        final Dialog myD = new Dialog(getActivity());
        myD.setContentView(R.layout.no_network);



        // Create grids with 2 items in a row
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pAdapter);
        pAdapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        int pic = pizzas.get(position).getImageResource();
                        String sear=pizzas.get(position).getName();
                        Toast.makeText(getActivity(), getResources().getString(R.string.clicked_item, pizzas.get(position).getName()), Toast.LENGTH_SHORT).show();
                        String text = getResources().getString(R.string.clicked_item, pizzas.get(position).getName());
                        String price = getResources().getString(R.string.clicked_item, pizzas.get(position).getPrice());
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), pic);
                        String proD = text;
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), Main3Activity.class);
                        intent.putExtra("Bitmap", bitmap);
                        intent.putExtra("proD", proD);
                        intent.putExtra("price", price);
                        startActivity(intent);


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );



        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


            Pizza pizza = new Pizza("Ewedu", R.drawable.eat2, "440");
            pizzas.add(pizza);
            pizza = new Pizza("Fufu", R.drawable.eat3, "365");
            pizzas.add(pizza);
            pizza = new Pizza("Banana", R.drawable.images, "365");
            pizzas.add(pizza);
            pizza = new Pizza("Egusi", R.drawable.soup, "365");
            pizzas.add(pizza);
            pizza = new Pizza("Egusi Soup", R.drawable.egusi, "440");
            pizzas.add(pizza);
            pizza = new Pizza("Eba", R.drawable.eba, "440");
            pizzas.add(pizza);
            pizza = new Pizza("Dodo", R.drawable.dodo, "525");
            pizzas.add(pizza);
            pizza = new Pizza("Non-Veg Supreme", R.drawable.non_veg_supreme, "525");
            pizzas.add(pizza);
            pizza = new Pizza("ALOME", R.drawable.nonetwork, "525");
            pizzas.add(pizza);

            return view;




    }

    }


