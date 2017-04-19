package bman.studios.omar.bananaconverter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;

import bman.studios.omar.bananaconverter.R;

public class MainActivity extends AppCompatActivity {

    /** The cost in USD of 1 banana. This will be used for the conversion*/
    private static final double CONVERSION_FACTOR = 0.15243;

    /** Variable for the MediaPlayer that the app uses to make noises */
    MediaPlayer mplayer;

    ImageView image;

    /** Variable for the popup message that displays conversion */ 
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-1021575853470656~1809116721");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#20a780"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        //Change this to be the money sound that you want to play.
        mplayer = MediaPlayer.create(this, R.raw.cashregister);
        mToast = null;
    }

    public void playAudio(View view) {
        mplayer.start();

    }

    public void pauseAudio(View view) {

        mplayer.pause();

    }

    public void showAToast(String str) {
        try {
            mToast.getView().isShown();
            mToast.setText(str);

        } catch (Exception e) {
            mToast = Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG);
        }
        mToast.show();
    }

    public void clickConvert(View view) {

        image = (ImageView) findViewById(R.id.currency);

        EditText currencyText = (EditText) findViewById(R.id.currencyText);
        if (!currencyText.getText().toString().equals("")) {
            this.playAudio(view);
            Double numBananas = Double.parseDouble(currencyText.getText().toString()) / CONVERSION_FACTOR;
            DecimalFormat numberFormat = new DecimalFormat("#.00");

            String message = numberFormat.format(numBananas) + " Bananas!";
            showAToast(message);

            image.setImageResource(R.drawable.bananas);
            new CountDownTimer(4000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //potential for furthering features
                }

                public void onFinish() {
                    image.setImageResource(R.drawable.currency);
                }
            }.start();
        }


    }


}