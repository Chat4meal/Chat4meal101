package waka.wakamarket.wakamarket;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vistrav.ask.Ask;

/**
 * Created by Muhammad on 4/19/2018.
 */

public class Second extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.secondfragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Ask.on(getActivity())
                .id(123) // in case you are invoking multiple time Ask from same activity or fragment
                .forPermissions(android.Manifest.permission.CAMERA
                        , android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withRationales("permission needed for Camera",
                        "In order to save file you will need to grant storage permission") //optional
                .go();
        ImageView selected= view.findViewById(R.id.ivProfile);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(getActivity(),Main2Activity.class);
                startActivity(intent);
            }
        });

        //you can set the title for your toolbar here for different fragments different titles

    }

}
