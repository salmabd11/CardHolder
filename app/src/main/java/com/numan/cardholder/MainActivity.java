package com.numan.cardholder;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    static final int REQUEST_CODE = 15523;
    private static final int REQUEST_CAMERA = 1;
    private static final int PICK_IMAGE = 2;
    private static final int PICK_IMAGE1 = 3;
    private ImageView mprofilImage,mprofilImage1;
    Uri imageUri,imageUri1;
    boolean imageAdded = false;

    private FloatingActionButton BtnProfile,BtnProfile1;

    EditText EtName,EtPhone,EtUrl,EtEmail,EtBusinesstype,EtmainBuinss;
    private TextView proggerstxt,Totaldonortx;
    private Button Save;
    private ProgressBar mProgressbar;
    private Spinner spinner;

    DatabaseReference DataRef;
    StorageReference StorageRef;
    private FirebaseAuth fAuth;
    String userID;
    String[] Golpotype;
    String mBusinesstype;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadTotaldonor();
        AutoPermitiom();

        spinner = findViewById(R.id.spinner1);
        Golpotype= getResources().getStringArray( R.array.SetBusinessType);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,R.layout.spnnerview,R.id.txtspnnerview,Golpotype);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mprofilImage = findViewById(R.id.profilephoto);
        mprofilImage1 = findViewById(R.id.profilephoto1);
        EtName = findViewById(R.id.NameOfWriter);
        EtPhone = findViewById(R.id.NameOfStory);
        EtEmail = findViewById(R.id.Part);
        EtUrl = findViewById(R.id.PUrl);
        EtBusinesstype = findViewById(R.id.Businesstype);
        EtmainBuinss = findViewById(R.id.mainBuinss);
        Totaldonortx = findViewById(R.id.Totaldonortx);
        BtnProfile = findViewById(R.id.Btnprofilephoto);
        BtnProfile1 = findViewById(R.id.Btnprofilephoto1);

        proggerstxt = findViewById(R.id.textviewprogress);
        mProgressbar = findViewById(R.id.Progressbar);
        Save = findViewById(R.id.SaveBTN);

        fAuth = FirebaseAuth.getInstance();
        // Golpotype = Storytype.getText().toString();
        DataRef = FirebaseDatabase.getInstance().getReference();
        //DataRef = FirebaseDatabase.getInstance().getReference().child("Golper Jhuri");
        StorageRef = FirebaseStorage.getInstance().getReference().child("CardHolder");

        BtnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(MainActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(10);

            }
        });
        BtnProfile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(MainActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(20);

            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // NameOfWriter,NameOfStory,Golpo,Part;
                final String Name = EtName.getText().toString();
                final String Phone = EtPhone.getText().toString();
                final String Email = EtEmail.getText().toString();
                final String UserUrl = EtUrl.getText().toString();
                final String MainBusniss = EtmainBuinss.getText().toString();
                final String Businesstype = EtBusinesstype.getText().toString();

                if (imageAdded != false) {
                    if (Businesstype.isEmpty()) {
                        EtBusinesstype.setError(getString(R.string.Businesstype));
                        EtBusinesstype.requestFocus();
                        return;
                    }

                    if (Name.isEmpty()) {
                        EtName.setError(getString(R.string.NameOfCardHolder));
                        EtName.requestFocus();
                        return;
                    }
                    if (Phone.isEmpty()) {
                        EtPhone.setError(getString(R.string.CardHolderPhone));
                        EtPhone.requestFocus();
                        return;
                    }
                    if (Email.isEmpty()) {
                        EtEmail.setError(getString(R.string.Useremail));
                        EtEmail.requestFocus();
                        return;
                    }

                    if (UserUrl.isEmpty()) {
                        EtUrl.setError(getString(R.string.UserPersonalUrl));
                        EtUrl.requestFocus();
                        return;
                    }
                    if (MainBusniss.isEmpty()) {
                        EtmainBuinss.setError(getString(R.string.UserPersonalUrl));
                        EtmainBuinss.requestFocus();
                        return;
                    }

                    {
                        UploadImage(Name);
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Fill all Data",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode ==10) {
                imageUri = data.getData();
                imageAdded = true;
                mprofilImage.setImageURI(imageUri);
            } else if (requestCode == 20) {
                imageUri1 = data.getData();
                imageAdded = true;
                mprofilImage1.setImageURI(imageUri1);
            }
        }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getItemAtPosition(position).equals("Set Story Type")){
            //Toast.makeText(parent.getContext(), "Please Set Blood Group", Toast.LENGTH_SHORT).show();
        }else {
            String text = parent.getItemAtPosition(position).toString();
            EtBusinesstype.setText(text);
            Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();}
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    private void UploadImage(final String Name) {
        // NameOfWriter,NameOfStory,Golpo,Part;
        final String Phone = EtPhone.getText().toString();
        final String Email = EtEmail.getText().toString();
        final String Url = EtUrl.getText().toString();
        final String MainBusniss = EtmainBuinss.getText().toString();
        proggerstxt.setVisibility(View.VISIBLE);
        mProgressbar.setVisibility(View.VISIBLE);
        userID = DataRef.push().getKey();

        // Upload Image Firebase Storage
        StorageRef.child(userID+".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StorageRef.child(userID+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //Upload Data Firebase Database
                        HashMap hashMap = new HashMap();
                        hashMap.put("name", Name);
                        hashMap.put("phone", Phone);
                        hashMap.put("email", Email);
                        hashMap.put("url", Url);
                        hashMap.put("MainBuisness", MainBusniss);
                        hashMap.put("ImageUri", uri.toString());
                        hashMap.put("ImageUri1", uri.toString());
                        AddNewUser();
                        UpdateProfile();
                        mBusinesstype = EtBusinesstype.getText().toString();
                        DataRef.child(mBusinesstype).child(userID).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Toast.makeText(MainActivity.this,"Deta Successfully Uploaded",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }



                    private void AddNewUser() {
                        String addnewDonor = Totaldonortx.getText().toString().trim();
                        if (addnewDonor.isEmpty()) {
                            Totaldonortx.setError(getString(R.string.input_error_User));
                            Totaldonortx.requestFocus();
                            return;
                        }
                        else {
                            int Addnewuser = 1;
                            String addDonor = Totaldonortx.getText().toString();
                            FirebaseDatabase  database = FirebaseDatabase.getInstance();
                            DatabaseReference mDatabaseRef = database.getReference("Total Card");//("BloodDonorCount").child("DonorCount");//TotalDonor
                            mDatabaseRef.child("CardCount").child("TotalCard").setValue( String.valueOf(Integer.parseInt(addDonor) + 1));
                            Toast.makeText(MainActivity.this, "Successfully Add new Donor", Toast.LENGTH_SHORT).show();
                           // startActivity(new Intent(getApplicationContext(), MainHome.class));
                            //Toast.makeText(getApplicationContext(), "Wait...", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (taskSnapshot.getBytesTransferred()*100)/taskSnapshot.getTotalByteCount();
                mProgressbar.setProgress((int) progress);
                proggerstxt.setText(progress+" % ");

            }
        });
    }
    private void UpdateProfile() {
        proggerstxt.setVisibility(View.VISIBLE);
        mProgressbar.setVisibility(View.VISIBLE);
        userID = DataRef.push().getKey();

        // Upload Image Firebase Storage
        StorageRef.child(userID + ".jpg").putFile(imageUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StorageRef.child(userID + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String userPhoto = uri.toString();
                        mBusinesstype = EtBusinesstype.getText().toString();
                        DataRef.child(mBusinesstype).child(userID).child("ImageUri1").setValue(userPhoto).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(getApplicationContext(), MainHome.class));
                                Toast.makeText(MainActivity.this, "Deta Successfully UpDate", Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (taskSnapshot.getBytesTransferred() * 100) / taskSnapshot.getTotalByteCount();
                mProgressbar.setProgress((int) progress);
                proggerstxt.setText(progress + " % ");

            }
        });

    }



    private void LoadTotaldonor() {
        DataRef=FirebaseDatabase.getInstance().getReference().child("Total Card").child("CardCount");
        DataRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String TDonor = dataSnapshot.child("TotalCard").getValue().toString();
                    Toast.makeText(MainActivity.this,"Total User Is "+TDonor, Toast.LENGTH_LONG).show();
                    Totaldonortx.setText(TDonor);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,
                        "No Data ", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void AutoPermitiom() {

        if (ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.CAMERA") +
                ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.READ_CONTACTS") +
                ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.CALL_PHONE") +
                ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.ACCESS_WIFI_STATE") +
                ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.INTERNET") +
                ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE") +
                ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.READ_EXTERNAL_STORAGE") == 0)
        {
            //Toast.makeText(MainActivity.this.getApplicationContext(), "Permission already granted..", Toast.LENGTH_LONG).show();

        } else if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.CAMERA")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.READ_CONTACTS")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.CALL_PHONE")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.ACCESS_WIFI_STATE")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.INTERNET")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, "android.permission.READ_EXTERNAL_STORAGE"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle( "Grant those Permission");
            builder.setMessage( "Camera, Read Contact and Read Storage");
            builder.setPositiveButton( "OK",  new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MainActivity.this,

                            new String[]
                                    {
                                            "android.permission.CAMERA",
                                            "android.permission.WRITE_EXTERNAL_STORAGE",
                                            "android.permission.INTERNET",
                                            "android.permission.READ_CONTACTS",
                                            "android.permission.CALL_PHONE",
                                            "android.permission.ACCESS_WIFI_STATE",
                                            "android.permission.READ_EXTERNAL_STORAGE"},
                            MainActivity.REQUEST_CODE);
                }
            });
            builder.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AutoPermitiom();

                }
            });
            builder.create().show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]
                            {
                                    "android.permission.CAMERA",
                                    "android.permission.WRITE_EXTERNAL_STORAGE",
                                    "android.permission.INTERNET",
                                    "android.permission.READ_CONTACTS",
                                    "android.permission.CALL_PHONE",
                                    "android.permission.ACCESS_WIFI_STATE",
                                    "android.permission.READ_EXTERNAL_STORAGE"},
                    MainActivity.REQUEST_CODE);
        }
    }
    public void onRequestPermissionsResult(int requestCode,
     @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            return;
        }
        if ((grantResults.length < 0)
                && (grantResults[0]
                + grantResults[1]
                + grantResults[2]
                + grantResults[3]
                + grantResults[4]
                + grantResults[5]
                == PackageManager.PERMISSION_GRANTED)) {
            //Toast.makeText(getApplicationContext(), "Permission Granted...", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Permission Denied...", Toast.LENGTH_LONG).show();
        }

    }
}
