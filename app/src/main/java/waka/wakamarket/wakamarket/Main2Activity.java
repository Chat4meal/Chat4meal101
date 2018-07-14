package waka.wakamarket.wakamarket;

import android.app.Notification;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import de.hdodenhof.circleimageview.CircleImageView;
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

import com.google.android.gms.tasks.OnCompleteListener;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();
    private TextView txtDetails;
    private EditText inputName, inputEmail;
    private Button btnSave;
    private ImageView chooseDp;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    Uri uri;
    private final Integer PICK_IMAGE_REQUEST=1;
    Bitmap bitmap;
    private Uri mImageUri ;
    private ProgressDialog mProgress;
    private static final int GALLERY_REQUEST = 1;
    private final Integer request_code=1;

    StorageReference mStorage;




    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    private String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        txtDetails = (TextView) findViewById(R.id.txt_user);
        inputName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        btnSave=(Button)findViewById(R.id.btn_save);
       chooseDp=(ImageView) findViewById(R.id.choosedp);


        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("wakamarket101");

        // store app title to 'app_title' node


        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);


                // update toolbar title

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });

       chooseDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);

            }
        });


        // Save / update the user
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = inputName.getText().toString();
                final String email = inputEmail.getText().toString();
                if(validate()){



                    StorageReference filepath = mStorage.child("contact_image").child(mImageUri.getLastPathSegment());


                    filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            final Uri downloadUri = taskSnapshot.getDownloadUrl();

                            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    mFirebaseDatabase.child(userId).child("name").setValue(name);
                                   mFirebaseDatabase.child(userId).child("address").setValue(email);
                                  mFirebaseDatabase.child(userId).child("image_add").setValue(downloadUri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                startActivity(new Intent(Main2Activity.this, MainActivity.class));
                                            }
                                        }
                                    });
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });

                            Toast.makeText(getApplicationContext(), " Adding Complete!! ", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                        }
                    });
                }
                    else {
                    Snackbar.make(view, "Please fill Required", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();}
            }
        });
    }


    // Changing button text

    /**
     * Creating new user node under 'users'
     */


    private boolean validate(){
        boolean isValid=false;
      String  email=inputName.getText().toString();
      String  password=inputEmail.getText().toString();
        if(TextUtils.isEmpty(email))
            inputName.setError("Required");
        else if(TextUtils.isEmpty(password))
          inputEmail.setError("Required");
        else
            isValid=true;
        return isValid;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data!= null){
            mImageUri = data.getData();
     chooseDp.setImageURI(mImageUri);
        }
    }
}
