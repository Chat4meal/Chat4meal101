package waka.wakamarket.wakamarket;

import android.app.Notification;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import de.hdodenhof.circleimageview.CircleImageView;
import jonathanfinerty.once.Once;

import android.Manifest;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.media.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;
import com.vistrav.ask.Ask;

import java.io.IOException;
import java.io.InputStream;

public  class Main2Activity  extends AppCompatActivity  implements IPickResult {

    private static final String TAG = Main2Activity.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputName, inputEmail;
    private Button btnSave;
    private ImageView chooseDp;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private final Integer PICK_IMAGE_REQUEST = 1;
    Bitmap bitmap;
    StorageReference mStore;
    private Uri mImageUri;
    private ProgressDialog mProgress;
    private static final int GALLERY_REQUEST = 1;
    private final Integer request_code = 1;
    EditText inputAddress;
    ProgressDialog progressDialog;
    pref2 prefManager;
    String imagepath;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefManager = new pref2(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        setContentView(R.layout.activity_main2);



        Ask.on(this)
                .id(1234)
                .forPermissions(Manifest.permission.CAMERA
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .withRationales("permission needed for Camera",
                        "In order to save file you will need to grant storage permission","Allow Location","Allow Location2") //optional
                .go();

        txtDetails = (TextView) findViewById(R.id.txt_user);
        inputName = (EditText) findViewById(R.id.usename);
        inputEmail = (EditText) findViewById(R.id.phone);
        btnSave = (Button) findViewById(R.id.btn_save);
        chooseDp = (ImageView) findViewById(R.id.choosedp1);
        chooseDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PickImageDialog.build(new PickSetup()).show(Main2Activity.this);
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = inputName.getText().toString();
                final String email = inputEmail.getText().toString();
                if (validate()) {
                    Intent intent = new Intent();
                    intent.setClass(Main2Activity.this, MainActivity.class);
                    intent.putExtra("username", name);
                    intent.putExtra("number",email);

                    intent.putExtra("uri",imagepath);

                    finish();
                  startActivity(intent);
                    prefManager.setFirstTimeLaunch(false);



                } else {
                    Snackbar.make(view, "Please fill Required", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });


    }





    private boolean validate() {
        boolean isValid = false;
        String email = inputName.getText().toString();
        String password = inputEmail.getText().toString();

        if (TextUtils.isEmpty(email))
            inputName.setError("Required");
        else if (TextUtils.isEmpty(password))
            inputEmail.setError("Required");
        else
            isValid = true;
        return isValid;
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {

         r.getBitmap();
         r.getUri();
imagepath=r.getPath();
chooseDp.setImageURI(Uri.parse(imagepath));

        } else {
            //Handle possible errors

            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(Main2Activity.this,MainActivity.class));
        finish();
    }

}

