package waka.wakamarket.wakamarket;

/**
 * Created by alome on 4/19/2018.
 */
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class first extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.activity_scrolling,
                container, false);
        setHasOptionsMenu(true);


        final List<Pizza> pizzas = new ArrayList<>();
        ArrayList<String> items = new ArrayList<>();
        final RecyclerView recyclerView;

        SearchView searchView;
        final ImageView proImage;


        RelativeLayout relativeLayout;
        final Dialog myD = new Dialog(getActivity());
        myD.setContentView(R.layout.no_network);
        final PizzaAdapter pAdapter;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("menus");
        ProgressDialog pDialog;


        pAdapter = new PizzaAdapter(pizzas, getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        // Create grids with 2 items in a row


        pDialog = new ProgressDialog(getActivity());



        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        String pic = pizzas.get(position).getImageUrl();
                        String sear = pizzas.get(position).getName();
                        String text = getResources().getString(R.string.clicked_item, pizzas.get(position).getName());
                        String price = getResources().getString(R.string.clicked_item, pizzas.get(position).getPrice());
                        String storeN=getResources().getString(R.string.clicked_item, pizzas.get(position).getStoreName());
                        String storeAdd=getResources().getString(R.string.clicked_item, pizzas.get(position).getStAdd());
                        String storeTime=getResources().getString(R.string.clicked_item, pizzas.get(position).getStTime());
                        String storeImage=getResources().getString(R.string.clicked_item, pizzas.get(position).getStoreImage());


                        String proD = text;
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), Main3Activity.class);
                        intent.putExtra("image",pic);
                        intent.putExtra("proD", proD);
                        intent.putExtra("price", price);
                        intent.putExtra("storeN",storeN);
                        intent.putExtra("storeAdd",storeAdd);
                        intent.putExtra("storeTime",storeTime);
                        intent.putExtra("storeImage",storeImage);

                        startActivity(intent);


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                        final  Dialog confirm = new Dialog(getActivity());
                        confirm.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        confirm.setContentView(R.layout.favourite);
                        confirm.show();

                    }
                })
        );

        if (pAdapter.getItemCount() == 0) {
            pDialog.setMessage("Preparing Your Menu...");
            pDialog.show();
Toast.makeText(getActivity(),"Your Menu is empty !",Toast.LENGTH_LONG).show();

        }
        else {
            pDialog.dismiss();
            pDialog.hide();

        }
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


       Pizza pizza=new Pizza("Rice","50","https://chat4meal.com/storage/2018/08/chat4meal_logo_v.png","Alome Store","Alapere","9am -10pm","https://chat4meal.com/storage/2018/08/chat4meal_logo_v.png");
       pizzas.add(pizza);


        return view;
    }


    }

