package com.numan.cardholder;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class Thriller extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Category, CategoryViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private FloatingActionButton mFab;
    private EditText inputSerch;
    private TextView mainDb;

    private final String TAG ="--->AdMob";
    AdView mAdview;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    String bannerid;
    String FBInsAdUnit;
    Button videoButton;
    RewardedAd rewardedAd;
    private InterstitialAd mInterstitialAd;
    String MainDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thriller);
        mainDb=findViewById(R.id.mainDb);
        mainDb.setText(getIntent().getStringExtra("GoMainDb"));
        MainDB=mainDb.getText().toString().trim();

        videoButton = findViewById(R.id.advideobutton);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Toast.makeText(Thriller.this,"Open "+ MainDB, Toast.LENGTH_LONG).show();

                // prepaperAD();
            }
        });



        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //displayAd();
            }
        });


        inputSerch= findViewById(R.id.inputSerch);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        BannerAds();

        //Firebase init

        databaseReference = FirebaseDatabase.getInstance().getReference(MainDB);
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, SignUp.class));
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadData("");
        inputSerch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString()!=null){
                    loadData(editable.toString());
                }else{  loadData("");}
            }
        });

    }
    /*
    public void displayAd() {
        rewardedAd.show(this, new RewardedAdCallback() {
            @Override
            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                videoButton.setVisibility(View.INVISIBLE);

            }
        });

    }

     */


    private void loadData(String data){
        Query query = databaseReference.orderByChild("MainBuisness").startAt(data).endAt(data+"\uf8ff");
        FirebaseRecyclerOptions options =new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(query,Category.class).build();
        recyclerAdapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position, @NonNull Category model) {

                holder.txName.setText(model.getName());
                holder.txPhone.setText(model.getPhone());
                holder.txEmail.setText(model.getEmail());
                holder.txmainbuisness.setText(model.getMainBuisness());
                holder.txurl.setText(model.getUrl());
                Picasso.get().load(model.getImageUri()).into(holder.ProimageView);
                Picasso.get().load(model.getImageUri1()).into(holder.ProimageView1);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Name = holder.txName.getText().toString();
                        String Phone = holder.txPhone.getText().toString();
                        String Email = holder.txEmail.getText().toString();
                        String MBusiness = holder.txmainbuisness.getText().toString();
                        String Url = holder.txurl.getText().toString();
                        String Photo = model.getImageUri().trim();
                        String Photo1 = model.getImageUri1().trim();
                        Intent intent = new Intent(Thriller.this,ViewActivity.class);
                        intent.putExtra("DonorKye",getRef(position).getKey());
                        intent.putExtra("ExName",Name);
                        intent.putExtra("ExPhone",Phone);
                        intent.putExtra("ExEmail",Email);
                        intent.putExtra("ExMBusiness",MBusiness);
                        intent.putExtra("ExUrl",Url);
                        intent.putExtra("ExPhoto",Photo);
                        intent.putExtra("ExPhoto1",Photo1);
                        startActivity(intent);

                    }

                });

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_view,viewGroup,false);
                return new CategoryViewHolder(view);
            }
        };
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.startListening();
        recyclerView.setAdapter(recyclerAdapter);
    }
    public void BannerAds(){
        DatabaseReference roofRef = FirebaseDatabase.getInstance().getReference().child("Adunits");
        roofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bannerid = String.valueOf(dataSnapshot.child("Banner").getValue().toString());
                FBInsAdUnit = String.valueOf(dataSnapshot.child("Interstitial").getValue().toString());
                prepaperAD();
                View view = findViewById(R.id.rlbanner);
                mAdview = new AdView(Thriller.this);
                mAdview.setAdSize(AdSize.BANNER);
                ((RelativeLayout) view).addView(mAdview);
                mAdview.setAdUnitId(bannerid);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdview.loadAd(adRequest);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void  prepaperAD() {
        AdRequest adRequest = new AdRequest.Builder().build();
        PersonalAdReq(adRequest);
    }
    private void PersonalAdReq(AdRequest adRequest) {
        //"ca-app-pub-3940256099942544/1033173712"
        InterstitialAd.load(this,FBInsAdUnit,
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "INS  Ad Loaded");

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d(TAG, "The ad was dismissed.");
                                startActivity(new Intent(getApplicationContext(), MainHome.class));
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d(TAG, "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;
                                Log.d(TAG, "The ad was shown.");
                            }
                        });

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(Thriller.this);
            finish();
        } else {
            super.onBackPressed();
            Log.d(TAG, "The interstitial ad wasn't ready yet.");
            startActivity(new Intent(getApplicationContext(), MainHome.class));
        }
    }


    public void privacypolicy(View view) {
        Toast.makeText(Thriller.this,"Comeing Soon.....  ", Toast.LENGTH_LONG).show();
    }
}
