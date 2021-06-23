package com.numan.cardholder;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainHome extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    Button advideobutton;
    Button BTNDoctor,BTNNurse,BTNDentist,BTNParamedic,BTNEngineer,BTNArchitect;
    Button BTNBusinessman,BTNTeacher,BTNSubcontractor,BTNSinger,BTNLibrarian,BTNSalesman;
    Button BTNPope,BTNPriest,BTNImam,BTNDriver,BTNJournalist,BTNDetective;
    Button BTNCameraman,BTNBoxer,BTNCarpenter,BTNFireman,BTNFlightattendant,BTNFarmer,BTNForeman,BTNBuilder,BTNForestranger;
    Button BTNFootballer,BTNCricketer,BTNGardener,BTNHousekeeper,BTNSupervisor,BTNMagician,BTNMechanic,BTNPainter,BTNPilot;
    Button BTNPhotographer,BTNPostman,BTNPoliceofficer,BTNProfessor,BTNConstructionworker,BTNReporter,BTNScientist,BTNRepairman,BTNSoldier;
    Button BTNSurgeon,BTNStudent,BTNElectrician,BTNPolitician,BTNWaiter,BTNLiftSector,BTNCementSector,BTNStoneSandBrickSector,BTNRodSector;
    Button BTNChemicalSector,BTNBankSector,BTNCeramicsSector,BTNSteelSector,BTNSanitarySector,BTNElectricSector ,BTNGeneratorSector,BTNPumpSector,BTNHardwareSector;
    Button BTNProprietor ,BTNGuard,BTNOthers,BTNBroker,BTNLawyer;
    Button BtnCardAdd;
    static final int REQUEST_CODE = 123;

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
        setContentView(R.layout.activity_main_home);

        advideobutton= findViewById(R.id.advideobutton);
        AutoPermitiom();
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
                            mInterstitialAd.show(MainHome.this);
                        }else{
                            Log.d(TAG,"InterstitialAd Not Loaded" );
                        }
                        prepaperAD();
                        //BannerAds();

                    }
                });
            }
        },5000,5000, TimeUnit.SECONDS);

        BtnCardAdd=findViewById(R.id.BtnCardAdd);
        BtnCardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        BTNDoctor=findViewById(R.id.BTNDoctor);
        BTNDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNDoctor.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNNurse=findViewById(R.id.BTNNurse);
        BTNNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNNurse.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNDentist=findViewById(R.id.BTNDentist);
        BTNDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNDentist.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNParamedic=findViewById(R.id.BTNParamedic);
        BTNParamedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNParamedic.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNEngineer=findViewById(R.id.BTNEngineer);
        BTNEngineer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNEngineer.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNArchitect=findViewById(R.id.BTNArchitect);
        BTNArchitect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNArchitect.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNBusinessman=findViewById(R.id.BTNBusinessman);
        BTNBusinessman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNBusinessman.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNTeacher=findViewById(R.id.BTNTeacher);
        BTNTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNTeacher.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNSubcontractor=findViewById(R.id.BTNSubcontractor);
        BTNSubcontractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNSubcontractor.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNSinger=findViewById(R.id.BTNSinger);
        BTNSinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNSinger.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNLibrarian=findViewById(R.id.BTNLibrarian);
        BTNLibrarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNLibrarian.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNLibrarian=findViewById(R.id.BTNLibrarian);
        BTNLibrarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNLibrarian.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNPope=findViewById(R.id.BTNPope);
        BTNPope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNPope.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNPriest=findViewById(R.id.BTNPriest);
        BTNPriest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNPriest.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNImam=findViewById(R.id.BTNImam);
        BTNImam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNImam.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNDriver=findViewById(R.id.BTNDriver);
        BTNDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNDriver.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNJournalist=findViewById(R.id.BTNJournalist);
        BTNJournalist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNJournalist.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNDetective=findViewById(R.id.BTNDetective);
        BTNDetective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNDetective.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNCameraman=findViewById(R.id.BTNCameraman);
        BTNCameraman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNCameraman.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNBoxer=findViewById(R.id.BTNBoxer);
        BTNBoxer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNBoxer.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNCarpenter=findViewById(R.id.BTNCarpenter);
        BTNCarpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNCarpenter.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNFireman=findViewById(R.id.BTNFireman);
        BTNFireman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNFireman.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNFlightattendant=findViewById(R.id.BTNFlightattendant);
        BTNFlightattendant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNFlightattendant.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNFarmer=findViewById(R.id.BTNFarmer);
        BTNFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNFarmer.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNForeman=findViewById(R.id.BTNForeman);
        BTNForeman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNForeman.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNBuilder=findViewById(R.id.BTNBuilder);
        BTNBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNBuilder.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNForestranger=findViewById(R.id.BTNForestranger);
        BTNForestranger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNForestranger.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNFootballer=findViewById(R.id.BTNFootballer);
        BTNFootballer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNFootballer.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNCricketer=findViewById(R.id.BTNCricketer);
        BTNCricketer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNCricketer.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNGardener=findViewById(R.id.BTNGardener);
        BTNGardener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNGardener.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNHousekeeper=findViewById(R.id.BTNHousekeeper);
        BTNHousekeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNHousekeeper.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNSupervisor=findViewById(R.id.BTNSupervisor);
        BTNSupervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNSupervisor.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNMagician=findViewById(R.id.BTNMagician);
        BTNMagician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNMagician.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNMechanic=findViewById(R.id.BTNMechanic);
        BTNMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNMechanic.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNPainter=findViewById(R.id.BTNPainter);
        BTNPainter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNPainter.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNPilot=findViewById(R.id.BTNPilot);
        BTNPilot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNPilot.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNPhotographer=findViewById(R.id.BTNPhotographer);
        BTNPhotographer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNPhotographer.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNPostman=findViewById(R.id.BTNPostman);
        BTNPostman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNPostman.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNPoliceofficer=findViewById(R.id.BTNPoliceofficer);
        BTNPoliceofficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNPoliceofficer.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNProfessor=findViewById(R.id.BTNProfessor);
        BTNProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNProfessor.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });


        BTNConstructionworker=findViewById(R.id.BTNConstructionworker);
        BTNConstructionworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNConstructionworker.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });


        BTNReporter=findViewById(R.id.BTNReporter);
        BTNReporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNReporter.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });


        BTNScientist=findViewById(R.id.BTNScientist);
        BTNScientist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNScientist.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNRepairman=findViewById(R.id.BTNRepairman);
        BTNRepairman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNRepairman.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNSoldier=findViewById(R.id.BTNSoldier);
        BTNSoldier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNSoldier.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNSurgeon=findViewById(R.id.BTNSurgeon);
        BTNSurgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNSurgeon.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNStudent=findViewById(R.id.BTNStudent);
        BTNStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNStudent.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNElectrician=findViewById(R.id.BTNElectrician);
        BTNElectrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNElectrician.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNPolitician=findViewById(R.id.BTNPolitician);
        BTNPolitician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNPolitician.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNWaiter=findViewById(R.id.BTNWaiter);
        BTNWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNWaiter.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNLiftSector=findViewById(R.id.BTNLiftSector);
        BTNLiftSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNLiftSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNCementSector=findViewById(R.id.BTNCementSector);
        BTNCementSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNCementSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNRodSector=findViewById(R.id.BTNRodSector);
        BTNRodSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNRodSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNStoneSandBrickSector=findViewById(R.id.BTNStoneSandBrickSector);
        BTNStoneSandBrickSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNStoneSandBrickSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNChemicalSector=findViewById(R.id.BTNChemicalSector);
        BTNChemicalSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNChemicalSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNBankSector=findViewById(R.id.BTNBankSector);
        BTNBankSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNBankSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNCeramicsSector=findViewById(R.id.BTNCeramicsSector);
        BTNCeramicsSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNCeramicsSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNSteelSector=findViewById(R.id.BTNSteelSector);
        BTNSteelSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNSteelSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNSanitarySector=findViewById(R.id.BTNSanitarySector);
        BTNSanitarySector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNSanitarySector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNElectricSector=findViewById(R.id.BTNElectricSector);
        BTNElectricSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNElectricSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNGeneratorSector=findViewById(R.id.BTNGeneratorSector);
        BTNGeneratorSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNGeneratorSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNPumpSector=findViewById(R.id.BTNPumpSector);
        BTNPumpSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNPumpSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNHardwareSector=findViewById(R.id.BTNHardwareSector);
        BTNHardwareSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNHardwareSector.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNProprietor=findViewById(R.id.BTNProprietor);
        BTNProprietor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNProprietor.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNGuard=findViewById(R.id.BTNGuard);
        BTNGuard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNGuard.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNOthers=findViewById(R.id.BTNOthers);
        BTNOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNOthers.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNBroker=findViewById(R.id.BTNBroker);
        BTNBroker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNBroker.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

        BTNLawyer=findViewById(R.id.BTNLawyer);
        BTNLawyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MainDbopen = BTNLawyer.getText().toString();
                Toast.makeText(MainHome.this,"Open "+ MainDbopen, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainHome.this,Thriller.class);
                intent.putExtra("GoMainDb",MainDbopen);
                startActivity(intent);

            }
        });

    }

    public void privacypolicy(View view) {
    }



    public void Share(View view) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody= "Card Holder APP Download Link: " +
                "https://drive.google.com/file/d/1oB5Rh38SMwJ7xyv--niAVzZN7xFkScf2/view?usp=sharing";
        String shareSubject = "Card Holder App Download Link";
        sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
        startActivity(Intent.createChooser(sharingIntent, "Share By"));
    }


    @Override
    public void onBackPressed(){
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
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
                mAdview = new AdView(MainHome.this);
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
                        advideobutton.setVisibility(View.VISIBLE);

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

                   // int reward = Integer.parseInt(PointtextView.getText().toString().trim());

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



    public void VideoAdOpen(View view) {
        ViewVideoAd();
        advideobutton.setVisibility(View.INVISIBLE);
    }
    public void AutoPermitiom(){

        if (ContextCompat.checkSelfPermission(MainHome.this, "android.permission.CAMERA") +
                ContextCompat.checkSelfPermission(MainHome.this, "android.permission.READ_CONTACTS") +
                ContextCompat.checkSelfPermission(MainHome.this, "android.permission.CALL_PHONE") +
                ContextCompat.checkSelfPermission(MainHome.this, "android.permission.ACCESS_WIFI_STATE") +
                ContextCompat.checkSelfPermission(MainHome.this, "android.permission.INTERNET") +
                ContextCompat.checkSelfPermission(MainHome.this, "android.permission.WRITE_EXTERNAL_STORAGE") +
                ContextCompat.checkSelfPermission(MainHome.this, "android.permission.READ_EXTERNAL_STORAGE") == 0)
        {
            //Toast.makeText(MainActivity.this.getApplicationContext(), "Permission already granted..", Toast.LENGTH_LONG).show();

        } else if (ActivityCompat.shouldShowRequestPermissionRationale(MainHome.this, "android.permission.CAMERA")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainHome.this, "android.permission.READ_CONTACTS")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainHome.this, "android.permission.CALL_PHONE")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainHome.this, "android.permission.ACCESS_WIFI_STATE")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainHome.this, "android.permission.INTERNET")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainHome.this, "android.permission.WRITE_EXTERNAL_STORAGE")
                || ActivityCompat.shouldShowRequestPermissionRationale(MainHome.this, "android.permission.READ_EXTERNAL_STORAGE"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainHome.this);
            builder.setTitle( "Grant those Permission");
            builder.setMessage( "Camera, Read Contact and Read Storage");
            builder.setPositiveButton( "OK",  new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MainHome.this,

                            new String[]
                                    {
                                            "android.permission.CAMERA",
                                            "android.permission.WRITE_EXTERNAL_STORAGE",
                                            "android.permission.INTERNET",
                                            "android.permission.READ_CONTACTS",
                                            "android.permission.CALL_PHONE",
                                            "android.permission.ACCESS_WIFI_STATE",
                                            "android.permission.READ_EXTERNAL_STORAGE"},
                            MainHome.REQUEST_CODE);
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
            ActivityCompat.requestPermissions(MainHome.this,
                    new String[]
                            {
                                    "android.permission.CAMERA",
                                    "android.permission.WRITE_EXTERNAL_STORAGE",
                                    "android.permission.INTERNET",
                                    "android.permission.READ_CONTACTS",
                                    "android.permission.CALL_PHONE",
                                    "android.permission.ACCESS_WIFI_STATE",
                                    "android.permission.READ_EXTERNAL_STORAGE"},
                    MainHome.REQUEST_CODE);
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