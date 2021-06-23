package com.numan.cardholder;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ViewActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    ImageView imageView,imageView1;
    TextView Name,Email,Phone,MainBuisness,Url,TXTImageUri,TXTImageUri1,PointtextView;
    WebView webView;
    FirebaseAuth mAuth;
    ImageButton BtnPhoneCall,EmailBtn,SmsBtn;

    private final String TAG ="--->AdMob";
    DatabaseReference ref;
    AdView mAdview;
    DatabaseReference mDatabase;
    String bannerid;
    String FBInsAdUnit;
    String FBVideoAdUnit;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        imageView = findViewById(R.id.singleimageviewVA);
        imageView1 = findViewById(R.id.singleimageviewVA1);
        Name = findViewById(R.id.TxtName);
        Phone = findViewById(R.id.TxtPhone);
        Email = findViewById(R.id.TxtEmail);
        MainBuisness = findViewById(R.id.TxtmainBuinss);
        Url = findViewById(R.id.TxtmainBuinss);
        webView = findViewById(R.id.TxtUrl);

        TXTImageUri = findViewById(R.id.TXTImageUri);
        TXTImageUri1 = findViewById(R.id.TXTImageUri1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        PointtextView = findViewById(R.id.RePoint);

        BtnPhoneCall = findViewById(R.id.PhoneCall);
        EmailBtn = findViewById(R.id.EmailBtn);
        SmsBtn = findViewById(R.id.SmsBtn);


        Name.setText(getIntent().getStringExtra("ExName"));
        Phone.setText(getIntent().getStringExtra("ExPhone"));
        Email.setText(getIntent().getStringExtra("ExEmail"));
        MainBuisness.setText(getIntent().getStringExtra("ExMBusiness"));
        PointtextView.setText(getIntent().getStringExtra("ExUrl"));
        TXTImageUri.setText(getIntent().getStringExtra("ExPhoto"));
        TXTImageUri1.setText(getIntent().getStringExtra("ExPhoto1"));
        String PhotoUri =TXTImageUri.getText().toString();
        String PhotoUri1 =TXTImageUri1.getText().toString();

        Picasso.get().load(PhotoUri).into(imageView);
        Picasso.get().load(PhotoUri1).into(imageView1);
        String mWebUrl = PointtextView.getText().toString().trim();
        webView.loadUrl(mWebUrl);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        BannerAds();
        //UserInfo();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                // prepaperAD();
            }
        });

        ScheduledExecutorService scheduledExecutorService =
                Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(ViewActivity.this);
                        }else{
                            Log.d(TAG,"InterstitialAd Not Loaded" );
                        }
                        prepaperAD();
                        //BannerAds();

                    }
                });
            }
        },50000,50000, TimeUnit.SECONDS);
        BtnPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        EmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ExEmail = Email.getText().toString();
                Intent intent = new Intent(ViewActivity.this, EmailActivity.class);
                intent.putExtra("xEmail",ExEmail);
                startActivity(intent);
                finish();
            }
        });
        SmsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ExPhone = Phone.getText().toString();
                Intent intent = new Intent(ViewActivity.this, SmsActivity.class);
                intent.putExtra("xPhone",ExPhone);
                startActivity(intent);
                finish();
            }
        });

    }
    private void makePhoneCall() {
        String number = Phone.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(ViewActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ViewActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(ViewActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

/*

    private void UserInfo() {
        ref = FirebaseDatabase.getInstance().getReference().child("Love Story");
        String uid = getIntent().getStringExtra("DonorKye");
        ref.child(uid).addValueEventListener(new ValueEventListener() {
            // @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String NameWriter = dataSnapshot.child("NameOfWriter").getValue().toString();
                    String NameStory = dataSnapshot.child("NameOfStory").getValue().toString();
                    String MainGolpo = dataSnapshot.child("Golpo").getValue().toString();
                    String Partname = dataSnapshot.child("Part").getValue().toString();
                    String imageUri = dataSnapshot.child("ImageUri").getValue().toString();
                    Picasso.get().load(imageUri).into(imageView);
                    NameOfWriter.setText(NameWriter);
                    NameOfStory.setText(NameStory);
                    Golpo.loadUrl(MainGolpo);
                    Part.setText(Partname);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

 */


    public void BannerAds(){
        DatabaseReference roofRef = FirebaseDatabase.getInstance().getReference().child("Adunits");
        roofRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bannerid = String.valueOf(dataSnapshot.child("Banner").getValue().toString());
                FBInsAdUnit = String.valueOf(dataSnapshot.child("Interstitial").getValue().toString());
                FBVideoAdUnit = String.valueOf(dataSnapshot.child("Rewarded Video").getValue().toString());
                prepaperAD();
                loadRewardedAd();
                View view = findViewById(R.id.rlbanner);
                mAdview = new AdView(ViewActivity.this);
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

    private void loadRewardedAd(){
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, FBVideoAdUnit,
                adRequest, new RewardedAdLoadCallback(){
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
                        Log.d(TAG, "onAdFailedToLoad");
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d(TAG, "Video Ad is Loaded");
                        showRewardedAd();
                        imageView.setVisibility(View.VISIBLE);

                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d(TAG, "Ad was shown.");
                                mRewardedAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.d(TAG, "Ad failed to show.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Don't forget to set the ad reference to null so you
                                // don't show the ad a second time.
                                Log.d(TAG, "Ad was dismissed.");
                                loadRewardedAd();
                            }
                        });
                    }
                });
    }
    private void showRewardedAd(){

            }

    private void ViewVideoAd() {
        if (mRewardedAd != null) {
            mRewardedAd.show(this, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d(TAG, "The user earned the reward.");
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();

                    int reward = Integer.parseInt(PointtextView.getText().toString().trim());

                   // PointtextView.setText(String.valueOf(reward + rewardAmount));
                }
            });
        } else {
            Log.d(TAG, "The rewarded ad wasn't ready yet.");
        }
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
                        Log.i(TAG, "INS Ad Loaded");

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                Log.d(TAG, "The ad was dismissed.");
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
                        Log.i("....onAdFailedToLoad", loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(ViewActivity.this);
            startActivity(new Intent(getApplicationContext(), Thriller.class));
        } else {
            startActivity(new Intent(getApplicationContext(), Thriller.class));
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    public void VideoAdOpen(View view) {
        ViewVideoAd();
        imageView.setVisibility(View.INVISIBLE);
    }
}
