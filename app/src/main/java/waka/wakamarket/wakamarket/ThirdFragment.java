package waka.wakamarket.wakamarket;

/**
 * Created by Muhammad on 4/18/2018.
 */
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class  ThirdFragment extends Fragment {
 @Nullable
 @Override
 public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

  //returning our layout file
  //change R.layout.yourlayoutfilename for each of your fragments
  return inflater.inflate(R.layout.thirdfragment, container, false);
 }


 @Override
 public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
  super.onViewCreated(view, savedInstanceState);
  Button set=(Button) view.findViewById(R.id.btnset);
set.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
  Intent intent = new Intent(getActivity(), Main2Activity.class);
  startActivity(intent);
 }
});
  //you can set the title for your toolbar here for different fragments different titles

 }
}
