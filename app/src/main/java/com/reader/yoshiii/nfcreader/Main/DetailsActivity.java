package com.reader.yoshiii.nfcreader.Main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.reader.yoshiii.nfcreader.R;

import su.whs.watl.ui.MultiColumnTextViewEx;


public class DetailsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    SliderLayout sliderShow;
    FrameLayout frameLayout;
    TextView landmarkName;
    ImageView playButton;
    Button btn;
    private YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Variable initializations

        Config.videoCode = "_oEA18Y8gM0";

        landmarkName = (TextView) findViewById(R.id.landmarkName);
        sliderShow = (SliderLayout) findViewById(R.id.slider);
        frameLayout = (FrameLayout) findViewById(R.id.frame);
        playButton = (ImageView) findViewById(R.id.playButton);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!"_oEA18Y8gM0".equals(Config.videoCode)) {

                    final AlertDialog.Builder popupMsg = new AlertDialog.Builder(DetailsActivity.this);

                    try {

                        youTubeView = new YouTubePlayerView(DetailsActivity.this);
                        youTubeView.initialize(Config.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
                            @Override
                            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                YouTubePlayer player, boolean wasRestored) {

                                if (!wasRestored) {

                                    // loadVideo() will auto play video
                                    // Use cueVideo() method, if you don't want to play it automatically
                                    player.loadVideo(Config.videoCode);

                                    // Hiding player controls
                                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                                }

                            }

                            @Override
                            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                YouTubeInitializationResult errorReason) {
                                if (errorReason.isUserRecoverableError()) {
                                    errorReason.getErrorDialog(DetailsActivity.this, 1).show();
                                } else {
                                    String errorMessage = "There was an error initializing the YouTube Player.";
                                    Toast.makeText(DetailsActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    } catch (Exception err) {

                        Toast.makeText(DetailsActivity.this, "Warning: No videos found", Toast.LENGTH_LONG).show();
                        Log.e("Error", err.toString());

                    }

                    popupMsg.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });

                    popupMsg.setView(youTubeView);
                    popupMsg.show();

                } else {

                    Toast.makeText(DetailsActivity.this, "No video found.", Toast.LENGTH_LONG).show();

                }

            }
        });

        //Getting intent extras from previous calling class

        setupDetails(getIntent().getExtras().getString("TAG_ID"));

    }

    //Setting up slideshow and layout details

    public void setupDetails(String tagCode){

        switch(tagCode){

            case "cas":

                landmarkName.setText("Cuartel De Santa Lucia");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_cas, null));

                DefaultSliderView defaultSliderView = new DefaultSliderView(this);

                defaultSliderView.image(getResources().getIdentifier("cas1", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView);

                DefaultSliderView defaultSliderView2 = new DefaultSliderView(this);

                defaultSliderView2.image(getResources().getIdentifier("cas2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2);

                DefaultSliderView defaultSliderView3 = new DefaultSliderView(this);

                defaultSliderView3.image(getResources().getIdentifier("cas3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3);

                DefaultSliderView defaultSliderView4 = new DefaultSliderView(this);

                defaultSliderView4.image(getResources().getIdentifier("cas4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4);

                DefaultSliderView defaultSliderView5 = new DefaultSliderView(this);

                defaultSliderView5.image(getResources().getIdentifier("cas5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5);

                DefaultSliderView defaultSliderView6 = new DefaultSliderView(this);

                defaultSliderView6.image(getResources().getIdentifier("cas6", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView6);

                Config.videoCode = "UclN3nW0bic";

                TextView _1781 = (TextView) findViewById(R.id.date_1781);
                TextView _1901 = (TextView) findViewById(R.id.date_1901);
                TextView _1905 = (TextView) findViewById(R.id.date_1905);
                TextView _1778 = (TextView) findViewById(R.id.date_1778to1787);
                TextView _1781_ = (TextView) findViewById(R.id.date_1781to1791);
                TextView _1778_ = (TextView) findViewById(R.id.dated_1778to1787);
                TextView _1948 = (TextView) findViewById(R.id.date_1948);

                final MultiColumnTextViewEx l_1781 = (MultiColumnTextViewEx) findViewById(R.id.date_1871_layout);
                final MultiColumnTextViewEx l_1901 = (MultiColumnTextViewEx) findViewById(R.id.date_1901_layout);
                final MultiColumnTextViewEx l_1905 = (MultiColumnTextViewEx) findViewById(R.id.date_1905_layout);
                final MultiColumnTextViewEx l_1778 = (MultiColumnTextViewEx) findViewById(R.id.date_1778to1787_layout);
                final MultiColumnTextViewEx l_1781_ = (MultiColumnTextViewEx) findViewById(R.id.date_1781to1791_layout);
                final MultiColumnTextViewEx l_1778_ = (MultiColumnTextViewEx) findViewById(R.id.date__1778to1787_layout);
                final MultiColumnTextViewEx l_1948 = (MultiColumnTextViewEx) findViewById(R.id.date_1948_layout);

                _1781.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1781.getVisibility() == View.GONE)
                            l_1781.setVisibility(View.VISIBLE);
                        else
                            l_1781.setVisibility(View.GONE);
                    }
                });

                _1901.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1901.getVisibility() == View.GONE)
                            l_1901.setVisibility(View.VISIBLE);
                        else
                            l_1901.setVisibility(View.GONE);
                    }
                });

                _1905.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1905.getVisibility() == View.GONE)
                            l_1905.setVisibility(View.VISIBLE);
                        else
                            l_1905.setVisibility(View.GONE);
                    }
                });

                _1778.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1778.getVisibility() == View.GONE)
                            l_1778.setVisibility(View.VISIBLE);
                        else
                            l_1778.setVisibility(View.GONE);
                    }
                });

                _1781_.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1781_.getVisibility() == View.GONE)
                            l_1781_.setVisibility(View.VISIBLE);
                        else
                            l_1781_.setVisibility(View.GONE);
                    }
                });

                _1778_.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1778_.getVisibility() == View.GONE)
                            l_1778_.setVisibility(View.VISIBLE);
                        else
                            l_1778_.setVisibility(View.GONE);
                    }
                });

                _1948.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1948.getVisibility() == View.GONE)
                            l_1948.setVisibility(View.VISIBLE);
                        else
                            l_1948.setVisibility(View.GONE);
                    }
                });

                break;

            case "for":

                landmarkName.setText("Fort Santiago");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_fort, null));

                DefaultSliderView defaultSliderViewfrt = new DefaultSliderView(this);

                defaultSliderViewfrt.image(getResources().getIdentifier("fort1", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewfrt);

                DefaultSliderView defaultSliderViewfrt2 = new DefaultSliderView(this);

                defaultSliderViewfrt2.image(getResources().getIdentifier("fort2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewfrt2);

                DefaultSliderView defaultSliderViewfrt3 = new DefaultSliderView(this);

                defaultSliderViewfrt3.image(getResources().getIdentifier("fort3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewfrt3);

                DefaultSliderView defaultSliderViewfrt4 = new DefaultSliderView(this);

                defaultSliderViewfrt4.image(getResources().getIdentifier("fort4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewfrt4);

                DefaultSliderView defaultSliderViewfrt5 = new DefaultSliderView(this);

                defaultSliderViewfrt5.image(getResources().getIdentifier("fort5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewfrt5);

                DefaultSliderView defaultSliderViewfrt6 = new DefaultSliderView(this);

                defaultSliderViewfrt6.image(getResources().getIdentifier("fort6", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewfrt6);

                DefaultSliderView defaultSliderViewfrt7 = new DefaultSliderView(this);

                defaultSliderViewfrt7.image(getResources().getIdentifier("fort7", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewfrt7);

                Config.videoCode = "_jH9d1lpH_k";

                TextView _orig_fort = (TextView) findViewById(R.id.orig_fort);
                TextView _1571 = (TextView) findViewById(R.id.dated_1571);
                TextView _1574 = (TextView) findViewById(R.id.date_1574);
                TextView _1580to1594 = (TextView) findViewById(R.id.date_1580to1594);
                TextView _1590to1593 = (TextView) findViewById(R.id.date_1590to1593);
                TextView _1906 = (TextView) findViewById(R.id.date_1906);

                final MultiColumnTextViewEx l_orig_fort = (MultiColumnTextViewEx) findViewById(R.id.orig_fort_layout);
                final MultiColumnTextViewEx l_1578 = (MultiColumnTextViewEx) findViewById(R.id.date_1571_layout);
                final MultiColumnTextViewEx l_1574 = (MultiColumnTextViewEx) findViewById(R.id.date_1574_layout);
                final MultiColumnTextViewEx l_1580to1594 = (MultiColumnTextViewEx) findViewById(R.id.date_1580to1594_layout);
                final MultiColumnTextViewEx l_1590to1593 = (MultiColumnTextViewEx) findViewById(R.id.date_1590to1593_layout);
                final MultiColumnTextViewEx l_1906 = (MultiColumnTextViewEx) findViewById(R.id.date_1906_layout);

                _orig_fort.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_orig_fort.getVisibility() == View.GONE)
                            l_orig_fort.setVisibility(View.VISIBLE);
                        else
                            l_orig_fort.setVisibility(View.GONE);
                    }
                });

                _1571.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1578.getVisibility() == View.GONE)
                            l_1578.setVisibility(View.VISIBLE);
                        else
                            l_1578.setVisibility(View.GONE);
                    }
                });

                _1574.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1574.getVisibility() == View.GONE)
                            l_1574.setVisibility(View.VISIBLE);
                        else
                            l_1574.setVisibility(View.GONE);
                    }
                });

                _1580to1594.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1580to1594.getVisibility() == View.GONE)
                            l_1580to1594.setVisibility(View.VISIBLE);
                        else
                            l_1580to1594.setVisibility(View.GONE);
                    }
                });

                _1590to1593.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1590to1593.getVisibility() == View.GONE)
                            l_1590to1593.setVisibility(View.VISIBLE);
                        else
                            l_1590to1593.setVisibility(View.GONE);
                    }
                });


                _1906.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1906.getVisibility() == View.GONE)
                            l_1906.setVisibility(View.VISIBLE);
                        else
                            l_1906.setVisibility(View.GONE);
                    }
                });


                break;

            case "let":

                landmarkName.setText("Letran College");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_let, null));

                DefaultSliderView defaultSliderViewlet = new DefaultSliderView(this);

                defaultSliderViewlet.image(getResources().getIdentifier("letran1", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewlet);

                DefaultSliderView defaultSliderView2let = new DefaultSliderView(this);

                defaultSliderView2let.image(getResources().getIdentifier("letran2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2let);

                DefaultSliderView defaultSliderView3let = new DefaultSliderView(this);

                defaultSliderView3let.image(getResources().getIdentifier("letran3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3let);

                DefaultSliderView defaultSliderView4let = new DefaultSliderView(this);

                defaultSliderView4let.image(getResources().getIdentifier("letran4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4let);

                DefaultSliderView defaultSliderView5let = new DefaultSliderView(this);

                defaultSliderView5let.image(getResources().getIdentifier("letran5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5let);

                DefaultSliderView defaultSliderView6let = new DefaultSliderView(this);

                defaultSliderView6let.image(getResources().getIdentifier("letran6", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView6let);


                TextView let = (TextView) findViewById(R.id.let);
                TextView _1620 = (TextView) findViewById(R.id.date_1620);
                TextView _1690 = (TextView) findViewById(R.id.date_1690);
                TextView _1738 = (TextView) findViewById(R.id.date_1738);
                TextView _1865 = (TextView) findViewById(R.id.date_1865);
                TextView _1886 = (TextView) findViewById(R.id.date_1886);
                TextView _1894to1900 = (TextView) findViewById(R.id.date_1894to1900);
                TextView _1941to1944 = (TextView) findViewById(R.id.date_1941to1944);
                TextView _1946 = (TextView) findViewById(R.id.date_1946);


                final MultiColumnTextViewEx let_layout = (MultiColumnTextViewEx) findViewById(R.id.let_layout);
                final MultiColumnTextViewEx l_1620 = (MultiColumnTextViewEx) findViewById(R.id.date_1620_layout);
                final MultiColumnTextViewEx l_1690 = (MultiColumnTextViewEx) findViewById(R.id.date_1690_layout);
                final MultiColumnTextViewEx l_1738 = (MultiColumnTextViewEx) findViewById(R.id.date_1738_layout);
                final MultiColumnTextViewEx l_1865 = (MultiColumnTextViewEx) findViewById(R.id.date_1865_layout);
                final MultiColumnTextViewEx l_1886 = (MultiColumnTextViewEx) findViewById(R.id.date_1886_layout);
                final MultiColumnTextViewEx l_1894to1900= (MultiColumnTextViewEx) findViewById(R.id.date_1894to1900_layout);
                final MultiColumnTextViewEx l_1941to1944 = (MultiColumnTextViewEx) findViewById(R.id.date_1941to1944_layout);
                final MultiColumnTextViewEx l_1946 = (MultiColumnTextViewEx) findViewById(R.id.date_1946_layout);

                let.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(let_layout.getVisibility() == View.GONE)
                            let_layout.setVisibility(View.VISIBLE);
                        else
                            let_layout.setVisibility(View.GONE);
                    }
                });

                _1620.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1620.getVisibility() == View.GONE)
                            l_1620.setVisibility(View.VISIBLE);
                        else
                            l_1620.setVisibility(View.GONE);
                    }
                });

                _1690.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1690.getVisibility() == View.GONE)
                            l_1690.setVisibility(View.VISIBLE);
                        else
                            l_1690.setVisibility(View.GONE);
                    }
                });

                _1738.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1738.getVisibility() == View.GONE)
                            l_1738.setVisibility(View.VISIBLE);
                        else
                            l_1738.setVisibility(View.GONE);
                    }
                });


                _1865.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1865.getVisibility() == View.GONE)
                            l_1865.setVisibility(View.VISIBLE);
                        else
                            l_1865.setVisibility(View.GONE);
                    }
                });

                _1886.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1886.getVisibility() == View.GONE)
                            l_1886.setVisibility(View.VISIBLE);
                        else
                            l_1886.setVisibility(View.GONE);
                    }
                });

                _1894to1900.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1894to1900.getVisibility() == View.GONE)
                            l_1894to1900.setVisibility(View.VISIBLE);
                        else
                            l_1894to1900.setVisibility(View.GONE);
                    }
                });


                _1941to1944.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1941to1944.getVisibility() == View.GONE)
                            l_1941to1944.setVisibility(View.VISIBLE);
                        else
                            l_1941to1944.setVisibility(View.GONE);
                    }
                });

                _1946.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1946.getVisibility() == View.GONE)
                            l_1946.setVisibility(View.VISIBLE);
                        else
                            l_1946.setVisibility(View.GONE);
                    }
                });


                break;

            case "lyc":

                landmarkName.setText("Lyceum of the Philippines University");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_lyc, null));

                DefaultSliderView defaultSliderViewlyc = new DefaultSliderView(this);

                defaultSliderViewlyc.image(getResources().getIdentifier("lpu1", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewlyc);

                DefaultSliderView defaultSliderView2lyc = new DefaultSliderView(this);

                defaultSliderView2lyc.image(getResources().getIdentifier("lpu2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2lyc);

                DefaultSliderView defaultSliderView3lyc = new DefaultSliderView(this);

                defaultSliderView3lyc.image(getResources().getIdentifier("lpu3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3lyc);

                DefaultSliderView defaultSliderView4lyc = new DefaultSliderView(this);

                defaultSliderView4lyc.image(getResources().getIdentifier("lpu4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4lyc);

                DefaultSliderView defaultSliderView5lyc = new DefaultSliderView(this);

                defaultSliderView5lyc.image(getResources().getIdentifier("lpu5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5lyc);

                DefaultSliderView defaultSliderView6lyc = new DefaultSliderView(this);

                defaultSliderView6lyc.image(getResources().getIdentifier("lpu6", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView6lyc);


                TextView lyc = (TextView) findViewById(R.id.lyc);
                TextView _1920 = (TextView) findViewById(R.id.date_1920);
                TextView _1952 = (TextView) findViewById(R.id.date_1952);
                TextView _1966= (TextView) findViewById(R.id.date_1966);
                TextView _2008 = (TextView) findViewById(R.id.date_2008);


                final MultiColumnTextViewEx l_lyc = (MultiColumnTextViewEx) findViewById(R.id.lyc_content);
                final MultiColumnTextViewEx l_1920 = (MultiColumnTextViewEx) findViewById(R.id.date_1920_layout);
                final MultiColumnTextViewEx l_1952 = (MultiColumnTextViewEx) findViewById(R.id.date_1952_layout);
                final MultiColumnTextViewEx l_1966 = (MultiColumnTextViewEx) findViewById(R.id.date__1966_layout);
                final MultiColumnTextViewEx l_2008 = (MultiColumnTextViewEx) findViewById(R.id.date_2008_layout);

                lyc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_lyc.getVisibility() == View.GONE)
                            l_lyc.setVisibility(View.VISIBLE);
                        else
                            l_lyc.setVisibility(View.GONE);
                    }
                });

                _1920.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1920.getVisibility() == View.GONE)
                            l_1920.setVisibility(View.VISIBLE);
                        else
                            l_1920.setVisibility(View.GONE);
                    }
                });

                _1952.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1952.getVisibility() == View.GONE)
                            l_1952.setVisibility(View.VISIBLE);
                        else
                            l_1952.setVisibility(View.GONE);
                    }
                });

                _1966.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1966.getVisibility() == View.GONE)
                            l_1966.setVisibility(View.VISIBLE);
                        else
                            l_1966.setVisibility(View.GONE);
                    }
                });


                _2008.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_2008.getVisibility() == View.GONE)
                            l_2008.setVisibility(View.VISIBLE);
                        else
                            l_2008.setVisibility(View.GONE);
                    }
                });


                break;

            case "man":

                landmarkName.setText("Manila Cathedral");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_man, null));

                DefaultSliderView defaultSliderViewman = new DefaultSliderView(this);

                defaultSliderViewman.image(getResources().getIdentifier("man1", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewman);

                DefaultSliderView defaultSliderView2man = new DefaultSliderView(this);

                defaultSliderView2man.image(getResources().getIdentifier("man2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2man);

                DefaultSliderView defaultSliderView3man = new DefaultSliderView(this);

                defaultSliderView3man.image(getResources().getIdentifier("man3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3man);

                DefaultSliderView defaultSliderView4man = new DefaultSliderView(this);

                defaultSliderView4man.image(getResources().getIdentifier("man4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4man);

                DefaultSliderView defaultSliderView5man = new DefaultSliderView(this);

                defaultSliderView5man.image(getResources().getIdentifier("man5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5man);

                DefaultSliderView defaultSliderView6man = new DefaultSliderView(this);

                defaultSliderView6man.image(getResources().getIdentifier("man6", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView6man);

                Config.videoCode = "chKfmfYN55U";


                TextView _1521 = (TextView) findViewById(R.id.date_1521);
                TextView _1571_ = (TextView) findViewById(R.id.dated_1571);
                TextView __1571 = (TextView) findViewById(R.id.dates_1571);
                TextView _1583 = (TextView) findViewById(R.id.date_1583);
                TextView _1879 = (TextView) findViewById(R.id.date_1879);
                TextView _1614to1945 = (TextView) findViewById(R.id.date_1614to1945);
                TextView __1906 = (TextView) findViewById(R.id.date_1906);
                TextView _1681to1863 = (TextView) findViewById(R.id.date_1958);
                TextView __1879 = (TextView) findViewById(R.id.date_1879);
                TextView _1958 = (TextView) findViewById(R.id.date_1958);
                TextView _tele = (TextView) findViewById(R.id.tele);
                TextView _mass = (TextView) findViewById(R.id.mass);

                final MultiColumnTextViewEx l_1521 = (MultiColumnTextViewEx) findViewById(R.id.date_1521_layout);
                final MultiColumnTextViewEx l_1571_ = (MultiColumnTextViewEx) findViewById(R.id.dat_1571_layout);
                final MultiColumnTextViewEx l__1571 = (MultiColumnTextViewEx) findViewById(R.id.dates_1571_layout);
                final MultiColumnTextViewEx l_1583 = (MultiColumnTextViewEx) findViewById(R.id.date_1583_layout);
                final MultiColumnTextViewEx l_1879= (MultiColumnTextViewEx) findViewById(R.id.date_1879_layout);
                final MultiColumnTextViewEx l_1614to1945= (MultiColumnTextViewEx) findViewById(R.id.date_1614to1945_layout);
                final MultiColumnTextViewEx l__1906= (MultiColumnTextViewEx) findViewById(R.id.dat_1906_layout);
                final MultiColumnTextViewEx l_1681to1863= (MultiColumnTextViewEx) findViewById(R.id.date__1681to1863_layout);
                final MultiColumnTextViewEx l_879= (MultiColumnTextViewEx) findViewById(R.id.date_1879_layout);
                final MultiColumnTextViewEx l_1958= (MultiColumnTextViewEx) findViewById(R.id.date_1958_layout);
                final MultiColumnTextViewEx l_tele= (MultiColumnTextViewEx) findViewById(R.id.tele_layout);
                final MultiColumnTextViewEx l_mass= (MultiColumnTextViewEx) findViewById(R.id.mass_layout);



                _1521.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1521.getVisibility() == View.GONE)
                            l_1521.setVisibility(View.VISIBLE);
                        else
                            l_1521.setVisibility(View.GONE);
                    }
                });

                _1571_.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1571_.getVisibility() == View.GONE)
                            l_1571_.setVisibility(View.VISIBLE);
                        else
                            l_1571_.setVisibility(View.GONE);
                    }
                });

                __1571.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l__1571.getVisibility() == View.GONE)
                            l__1571.setVisibility(View.VISIBLE);
                        else
                            l__1571.setVisibility(View.GONE);
                    }
                });

                _1583.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1583.getVisibility() == View.GONE)
                            l_1583.setVisibility(View.VISIBLE);
                        else
                            l_1583.setVisibility(View.GONE);
                    }
                });


                _1879.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1879.getVisibility() == View.GONE)
                            l_1879.setVisibility(View.VISIBLE);
                        else
                            l_1879.setVisibility(View.GONE);
                    }
                });

                _1614to1945.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1614to1945.getVisibility() == View.GONE)
                            l_1614to1945.setVisibility(View.VISIBLE);
                        else
                            l_1614to1945.setVisibility(View.GONE);
                    }
                });

                __1906.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l__1906.getVisibility() == View.GONE)
                            l__1906.setVisibility(View.VISIBLE);
                        else
                            l__1906.setVisibility(View.GONE);
                    }
                });

                _1681to1863.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1681to1863.getVisibility() == View.GONE)
                            l_1681to1863.setVisibility(View.VISIBLE);
                        else
                            l_1681to1863.setVisibility(View.GONE);
                    }
                });

                _1879.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1879.getVisibility() == View.GONE)
                            l_1879.setVisibility(View.VISIBLE);
                        else
                            l_1879.setVisibility(View.GONE);
                    }
                });

                _1958.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1958.getVisibility() == View.GONE)
                            l_1958.setVisibility(View.VISIBLE);
                        else
                            l_1958.setVisibility(View.GONE);
                    }
                });


                _tele.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_tele.getVisibility() == View.GONE)
                            l_tele.setVisibility(View.VISIBLE);
                        else
                            l_tele.setVisibility(View.GONE);
                    }
                });

                _mass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_mass.getVisibility() == View.GONE)
                            l_mass.setVisibility(View.VISIBLE);
                        else
                            l_mass.setVisibility(View.GONE);
                    }
                });


                break;


            case "map":

                landmarkName.setText("Mapua Institute of Technology");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_map, null));

                DefaultSliderView defaultSliderViewmap = new DefaultSliderView(this);

                defaultSliderViewmap.image(getResources().getIdentifier("map1", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewmap);

                DefaultSliderView defaultSliderView2map = new DefaultSliderView(this);

                defaultSliderView2map.image(getResources().getIdentifier("map2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2map);

                DefaultSliderView defaultSliderView3map = new DefaultSliderView(this);

                defaultSliderView3map.image(getResources().getIdentifier("map3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3map);

                DefaultSliderView defaultSliderView4map = new DefaultSliderView(this);

                defaultSliderView4map.image(getResources().getIdentifier("map4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4map);

                DefaultSliderView defaultSliderView5map = new DefaultSliderView(this);

                defaultSliderView5map.image(getResources().getIdentifier("map5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5map);

                DefaultSliderView defaultSliderView6map = new DefaultSliderView(this);

                defaultSliderView6map.image(getResources().getIdentifier("map6", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView6map);

                TextView _map = (TextView) findViewById(R.id.map);
                TextView _1903 = (TextView) findViewById(R.id.date_1903);
                TextView _don = (TextView) findViewById(R.id.don);

                final MultiColumnTextViewEx l_map = (MultiColumnTextViewEx) findViewById(R.id.map_content);
                final MultiColumnTextViewEx l_1903 = (MultiColumnTextViewEx) findViewById(R.id.date_1903_layout);
                final MultiColumnTextViewEx l_don = (MultiColumnTextViewEx) findViewById(R.id.don_layout);


                _map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_map.getVisibility() == View.GONE)
                            l_map.setVisibility(View.VISIBLE);
                        else
                            l_map.setVisibility(View.GONE);
                    }
                });

                _1903.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1903.getVisibility() == View.GONE)
                            l_1903.setVisibility(View.VISIBLE);
                        else
                            l_1903.setVisibility(View.GONE);
                    }
                });

                _don.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_don.getVisibility() == View.GONE)
                            l_don.setVisibility(View.VISIBLE);
                        else
                            l_don.setVisibility(View.GONE);
                    }
                });

                break;

            case "plm":

                landmarkName.setText("Pamantasan ng Lungsod ng Maynila");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_plm, null));

                DefaultSliderView defaultSliderViewplmm = new DefaultSliderView(this);

                defaultSliderViewplmm.image(getResources().getIdentifier("plmm1", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewplmm);

                DefaultSliderView defaultSliderView2plmm = new DefaultSliderView(this);

                defaultSliderView2plmm.image(getResources().getIdentifier("plmm2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2plmm);

                DefaultSliderView defaultSliderView3plmm = new DefaultSliderView(this);

                defaultSliderView3plmm.image(getResources().getIdentifier("plmm3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3plmm);

                DefaultSliderView defaultSliderView4plmm = new DefaultSliderView(this);

                defaultSliderView4plmm.image(getResources().getIdentifier("plmm4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4plmm);

                DefaultSliderView defaultSliderView5plmm = new DefaultSliderView(this);

                defaultSliderView5plmm.image(getResources().getIdentifier("plmm5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5plmm);

                DefaultSliderView defaultSliderView6plmm = new DefaultSliderView(this);

                defaultSliderView6plmm.image(getResources().getIdentifier("plmm6", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView6plmm);


                TextView _1951to1960 = (TextView) findViewById(R.id.date_1951to1960);
                TextView _1963 = (TextView) findViewById(R.id.date_1963);
                TextView _1964 = (TextView) findViewById(R.id.date_1964);
                TextView _1965 = (TextView) findViewById(R.id.date_1965);
                TextView _app = (TextView) findViewById(R.id.app);
                TextView _day = (TextView) findViewById(R.id.day);
                TextView _1967 = (TextView) findViewById(R.id.date_1967);
                TextView _1970 = (TextView) findViewById(R.id.date_date_1970);

                final MultiColumnTextViewEx l_1951to1960 = (MultiColumnTextViewEx) findViewById(R.id.date_1951to1960_layout);
                final MultiColumnTextViewEx l_1963 = (MultiColumnTextViewEx) findViewById(R.id.date_1963_layout);
                final MultiColumnTextViewEx l_1964 = (MultiColumnTextViewEx) findViewById(R.id.date_1964_layout);
                final MultiColumnTextViewEx l_1965 = (MultiColumnTextViewEx) findViewById(R.id.date_1965_layout);
                final MultiColumnTextViewEx l_app= (MultiColumnTextViewEx) findViewById(R.id.app_layout);
                final MultiColumnTextViewEx l_day= (MultiColumnTextViewEx) findViewById(R.id.day_layout);
                final MultiColumnTextViewEx l_1967= (MultiColumnTextViewEx) findViewById(R.id.date_1967_layout);
                final MultiColumnTextViewEx l_1970= (MultiColumnTextViewEx) findViewById(R.id.date_1970_layout);



                _1951to1960.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1951to1960.getVisibility() == View.GONE)
                            l_1951to1960.setVisibility(View.VISIBLE);
                        else
                            l_1951to1960.setVisibility(View.GONE);
                    }
                });

                _1963.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1963.getVisibility() == View.GONE)
                            l_1963.setVisibility(View.VISIBLE);
                        else
                            l_1963.setVisibility(View.GONE);
                    }
                });

                _1964.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1964.getVisibility() == View.GONE)
                            l_1964.setVisibility(View.VISIBLE);
                        else
                            l_1964.setVisibility(View.GONE);
                    }
                });

                _1965.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1965.getVisibility() == View.GONE)
                            l_1965.setVisibility(View.VISIBLE);
                        else
                            l_1965.setVisibility(View.GONE);
                    }
                });


                _app.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_app.getVisibility() == View.GONE)
                            l_app.setVisibility(View.VISIBLE);
                        else
                            l_app.setVisibility(View.GONE);
                    }
                });

                _day.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_day.getVisibility() == View.GONE)
                            l_day.setVisibility(View.VISIBLE);
                        else
                            l_day.setVisibility(View.GONE);
                    }
                });

                _1967.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1967.getVisibility() == View.GONE)
                            l_1967.setVisibility(View.VISIBLE);
                        else
                            l_1967.setVisibility(View.GONE);
                    }
                });

                _1970.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1970.getVisibility() == View.GONE)
                            l_1970.setVisibility(View.VISIBLE);
                        else
                            l_1970.setVisibility(View.GONE);
                    }
                });

                break;

            case "san":

                landmarkName.setText("San Agustin Church");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_san, null));

                DefaultSliderView defaultSliderViewsan = new DefaultSliderView(this);

                defaultSliderViewsan.image(getResources().getIdentifier("san1", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewsan);

                DefaultSliderView defaultSliderView2san = new DefaultSliderView(this);

                defaultSliderView2san.image(getResources().getIdentifier("san2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2san);

                DefaultSliderView defaultSliderView3san = new DefaultSliderView(this);

                defaultSliderView3san.image(getResources().getIdentifier("san3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3san);

                DefaultSliderView defaultSliderView4san = new DefaultSliderView(this);

                defaultSliderView4san.image(getResources().getIdentifier("san4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4san);

                DefaultSliderView defaultSliderView5san = new DefaultSliderView(this);

                defaultSliderView5san.image(getResources().getIdentifier("san5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5san);

                DefaultSliderView defaultSliderView6san = new DefaultSliderView(this);

                defaultSliderView6san.image(getResources().getIdentifier("san6", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView6san);

                Config.videoCode = "3rnc6aKmiko";


                TextView __1571_ = (TextView) findViewById(R.id.dat_1571);
                TextView _1574to1583 = (TextView) findViewById(R.id.date_1574to1583);
                TextView _1586 = (TextView) findViewById(R.id.date_1586);
                TextView _1945 = (TextView) findViewById(R.id.date_1945);
                TextView _1993 = (TextView) findViewById(R.id.date_1993);
                TextView _tel = (TextView) findViewById(R.id.tel);
                TextView _mas = (TextView) findViewById(R.id.mas);

                final MultiColumnTextViewEx l__1571_ = (MultiColumnTextViewEx) findViewById(R.id.date_1571_layout);
                final MultiColumnTextViewEx l_1574to1583 = (MultiColumnTextViewEx) findViewById(R.id.date_1574to1583_layout);
                final MultiColumnTextViewEx l_1586 = (MultiColumnTextViewEx) findViewById(R.id.date_1586_layout);
                final MultiColumnTextViewEx l_1945 = (MultiColumnTextViewEx) findViewById(R.id.date_1945_Layout);
                final MultiColumnTextViewEx l_1993= (MultiColumnTextViewEx) findViewById(R.id.date_1993_layout);
                final MultiColumnTextViewEx l_tel= (MultiColumnTextViewEx) findViewById(R.id.tel_layout);
                final MultiColumnTextViewEx l_mas= (MultiColumnTextViewEx) findViewById(R.id.mas_layout);


                __1571_.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l__1571_.getVisibility() == View.GONE)
                            l__1571_.setVisibility(View.VISIBLE);
                        else
                            l__1571_.setVisibility(View.GONE);
                    }
                });

                _1574to1583.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1574to1583.getVisibility() == View.GONE)
                            l_1574to1583.setVisibility(View.VISIBLE);
                        else
                            l_1574to1583.setVisibility(View.GONE);
                    }
                });

                _1586.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1586.getVisibility() == View.GONE)
                            l_1586.setVisibility(View.VISIBLE);
                        else
                            l_1586.setVisibility(View.GONE);
                    }
                });

                _1945.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1945.getVisibility() == View.GONE)
                            l_1945.setVisibility(View.VISIBLE);
                        else
                            l_1945.setVisibility(View.GONE);
                    }
                });


                _1993.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1993.getVisibility() == View.GONE)
                            l_1993.setVisibility(View.VISIBLE);
                        else
                            l_1993.setVisibility(View.GONE);
                    }
                });

                _tel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_tel.getVisibility() == View.GONE)
                            l_tel.setVisibility(View.VISIBLE);
                        else
                            l_tel.setVisibility(View.GONE);
                    }
                });

                _mas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_mas.getVisibility() == View.GONE)
                            l_mas.setVisibility(View.VISIBLE);
                        else
                            l_mas.setVisibility(View.GONE);
                    }
                });

                break;

            case "adu":

                landmarkName.setText("Aduana Building");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_adu, null));

                DefaultSliderView defaultSliderViewadu = new DefaultSliderView(this);

                defaultSliderViewadu.image(getResources().getIdentifier("aduana", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewadu);

                DefaultSliderView defaultSliderView2adu = new DefaultSliderView(this);

                defaultSliderView2adu.image(getResources().getIdentifier("aduana2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2adu);

                DefaultSliderView defaultSliderView3adu = new DefaultSliderView(this);

                defaultSliderView3adu.image(getResources().getIdentifier("aduana3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3adu);

                DefaultSliderView defaultSliderView4adu = new DefaultSliderView(this);

                defaultSliderView4adu.image(getResources().getIdentifier("aduana4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4adu);

                DefaultSliderView defaultSliderView5adu = new DefaultSliderView(this);

                defaultSliderView5adu.image(getResources().getIdentifier("aduana5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5adu);

                Config.videoCode    = "L09F2kZDLcI";

                TextView _1822to1823 = (TextView) findViewById(R.id.date_1822to1823);
                TextView _1829 = (TextView) findViewById(R.id.date_1829);
                TextView _1874to1876 = (TextView) findViewById(R.id.date_1874to1876);
                TextView _1941to1945 = (TextView) findViewById(R.id.date_1941to1945);
                TextView _1997 = (TextView) findViewById(R.id.date_1997);

                final MultiColumnTextViewEx l_1822to1823 = (MultiColumnTextViewEx) findViewById(R.id.date_1822to1823_layout);
                final MultiColumnTextViewEx l_1829 = (MultiColumnTextViewEx) findViewById(R.id.date_1829_layout);
                final MultiColumnTextViewEx l_1874to1876 = (MultiColumnTextViewEx) findViewById(R.id.date_1874to1876_layout);
                final MultiColumnTextViewEx l_1941to1945 = (MultiColumnTextViewEx) findViewById(R.id.date_1941to1945_layout);
                final MultiColumnTextViewEx l_1997 = (MultiColumnTextViewEx) findViewById(R.id.date_1997_layout);


                _1822to1823.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1822to1823.getVisibility() == View.GONE)
                            l_1822to1823.setVisibility(View.VISIBLE);
                        else
                            l_1822to1823.setVisibility(View.GONE);
                    }
                });

                _1829.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1829.getVisibility() == View.GONE)
                            l_1829.setVisibility(View.VISIBLE);
                        else
                            l_1829.setVisibility(View.GONE);
                    }
                });

                _1874to1876.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1874to1876.getVisibility() == View.GONE)
                            l_1874to1876.setVisibility(View.VISIBLE);
                        else
                            l_1874to1876.setVisibility(View.GONE);
                    }
                });

                _1941to1945.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1941to1945.getVisibility() == View.GONE)
                            l_1941to1945.setVisibility(View.VISIBLE);
                        else
                            l_1941to1945.setVisibility(View.GONE);
                    }
                });

                _1997.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1997.getVisibility() == View.GONE)
                            l_1997.setVisibility(View.VISIBLE);
                        else
                            l_1997.setVisibility(View.GONE);
                    }
                });

                break;

            case "bay":

                landmarkName.setText("Bayleaf Hotel");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_bay, null));

                DefaultSliderView defaultSliderViewbay = new DefaultSliderView(this);

                defaultSliderViewbay.image(getResources().getIdentifier("bay", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewbay);

                DefaultSliderView defaultSliderView2bay = new DefaultSliderView(this);

                defaultSliderView2bay.image(getResources().getIdentifier("bay2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2bay);

                DefaultSliderView defaultSliderView3bay = new DefaultSliderView(this);

                defaultSliderView3bay.image(getResources().getIdentifier("bay3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3bay);

                DefaultSliderView defaultSliderView4bay = new DefaultSliderView(this);

                defaultSliderView4bay.image(getResources().getIdentifier("bay4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4bay);

                DefaultSliderView defaultSliderView5bay = new DefaultSliderView(this);

                defaultSliderView5bay.image(getResources().getIdentifier("bay5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5bay);

                DefaultSliderView defaultSliderView6bay = new DefaultSliderView(this);

                defaultSliderView6bay.image(getResources().getIdentifier("bay6", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView6bay);

                Config.videoCode = "j-dsjVisTAc";

                TextView _dining = (TextView) findViewById(R.id.dining_layout);
                TextView _hall = (TextView) findViewById(R.id.hall);
                TextView _2011 = (TextView) findViewById(R.id.date_2011);
                TextView _2012 = (TextView) findViewById(R.id.date_2012);
                TextView _2013 = (TextView) findViewById(R.id.date_2013);
                TextView _2014to2015 = (TextView) findViewById(R.id.date_2014to2015);


                final MultiColumnTextViewEx l_dining = (MultiColumnTextViewEx) findViewById(R.id.dining_Layout);
                final MultiColumnTextViewEx l_hall = (MultiColumnTextViewEx) findViewById(R.id.hall_Layout);
                final MultiColumnTextViewEx l_2011 = (MultiColumnTextViewEx) findViewById(R.id.date_2011_layout);
                final MultiColumnTextViewEx l_2012 = (MultiColumnTextViewEx) findViewById(R.id.date_2012_Layout);
                final MultiColumnTextViewEx l_2013 = (MultiColumnTextViewEx) findViewById(R.id.date_2013_Layout);
                final MultiColumnTextViewEx l_2014to2015 = (MultiColumnTextViewEx) findViewById(R.id.date_2014to2015_Layout);


                _dining.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_dining.getVisibility() == View.GONE)
                            l_dining.setVisibility(View.VISIBLE);
                        else
                            l_dining.setVisibility(View.GONE);
                    }
                });

                _hall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_hall.getVisibility() == View.GONE)
                            l_hall.setVisibility(View.VISIBLE);
                        else
                            l_hall.setVisibility(View.GONE);
                    }
                });

                _2011.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_2011.getVisibility() == View.GONE)
                            l_2011.setVisibility(View.VISIBLE);
                        else
                            l_2011.setVisibility(View.GONE);
                    }
                });

                _2012.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_2012.getVisibility() == View.GONE)
                            l_2012.setVisibility(View.VISIBLE);
                        else
                            l_2012.setVisibility(View.GONE);
                    }
                });

                _2013.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_2013.getVisibility() == View.GONE)
                            l_2013.setVisibility(View.VISIBLE);
                        else
                            l_2013.setVisibility(View.GONE);
                    }
                });

                _2014to2015.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_2014to2015.getVisibility() == View.GONE)
                            l_2014to2015.setVisibility(View.VISIBLE);
                        else
                            l_2014to2015.setVisibility(View.GONE);
                    }
                });


                break;

            case "mhs":

                landmarkName.setText("Manila High School");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_mhs, null));

                DefaultSliderView defaultSliderViewmhs = new DefaultSliderView(this);

                defaultSliderViewmhs.image(getResources().getIdentifier("mhs", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewmhs);

                DefaultSliderView defaultSliderView2mhs = new DefaultSliderView(this);

                defaultSliderView2mhs.image(getResources().getIdentifier("mhs2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2mhs);

                DefaultSliderView defaultSliderView3mhs = new DefaultSliderView(this);

                defaultSliderView3mhs.image(getResources().getIdentifier("mhs3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3mhs);

                DefaultSliderView defaultSliderView4mhs = new DefaultSliderView(this);

                defaultSliderView4mhs.image(getResources().getIdentifier("mhs4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4mhs);

                DefaultSliderView defaultSliderView5mhs = new DefaultSliderView(this);

                defaultSliderView5mhs.image(getResources().getIdentifier("mhs5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5mhs);

                Config.videoCode = "fpuSv-1N1aw";

                TextView _1921 = (TextView) findViewById(R.id.date_1921);
                TextView _1930 = (TextView) findViewById(R.id.date_1930);
                TextView _1947 = (TextView) findViewById(R.id.date_1947);
                TextView _1948_ = (TextView) findViewById(R.id.date_1948);
                TextView _1953 = (TextView) findViewById(R.id.date_1953);
                TextView _1958_ = (TextView) findViewById(R.id.date_1958);
                TextView __1963 = (TextView) findViewById(R.id.date_1963);
                TextView __1964= (TextView) findViewById(R.id.date_1964);


                final MultiColumnTextViewEx l_1921 = (MultiColumnTextViewEx) findViewById(R.id.date_1921_Layout);
                final MultiColumnTextViewEx l_1930 = (MultiColumnTextViewEx) findViewById(R.id.date_1930_Layout);
                final MultiColumnTextViewEx l_1947 = (MultiColumnTextViewEx) findViewById(R.id.date_1947_Layout);
                final MultiColumnTextViewEx l_1948_ = (MultiColumnTextViewEx) findViewById(R.id.date_1948_Layout);
                final MultiColumnTextViewEx l_1953 = (MultiColumnTextViewEx) findViewById(R.id.date_1953_Layout);
                final MultiColumnTextViewEx l_1958_ = (MultiColumnTextViewEx) findViewById(R.id.date_1958_Layout);
                final MultiColumnTextViewEx l_1963_ = (MultiColumnTextViewEx) findViewById(R.id.date_1963_Layout);
                final MultiColumnTextViewEx l_1964_ = (MultiColumnTextViewEx) findViewById(R.id.date_1964_Layout);


                _1921.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1921.getVisibility() == View.GONE)
                            l_1921.setVisibility(View.VISIBLE);
                        else
                            l_1921.setVisibility(View.GONE);
                    }
                });

                _1930.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1930.getVisibility() == View.GONE)
                            l_1930.setVisibility(View.VISIBLE);
                        else
                            l_1930.setVisibility(View.GONE);
                    }
                });

                _1947.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1947.getVisibility() == View.GONE)
                            l_1947.setVisibility(View.VISIBLE);
                        else
                            l_1947.setVisibility(View.GONE);
                    }
                });

                _1948_.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1948_.getVisibility() == View.GONE)
                            l_1948_.setVisibility(View.VISIBLE);
                        else
                            l_1948_.setVisibility(View.GONE);
                    }
                });

                _1953.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1953.getVisibility() == View.GONE)
                            l_1953.setVisibility(View.VISIBLE);
                        else
                            l_1953.setVisibility(View.GONE);
                    }
                });

                _1958_.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1958_.getVisibility() == View.GONE)
                            l_1958_.setVisibility(View.VISIBLE);
                        else
                            l_1958_.setVisibility(View.GONE);
                    }
                });

                __1963.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1963_.getVisibility() == View.GONE)
                            l_1963_.setVisibility(View.VISIBLE);
                        else
                            l_1963_.setVisibility(View.GONE);
                    }
                });

                __1964.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(l_1964_.getVisibility() == View.GONE)
                            l_1964_.setVisibility(View.VISIBLE);
                        else
                            l_1964_.setVisibility(View.GONE);
                    }
                });

                break;

            case "mnb":

                landmarkName.setText("Manila Bulletin");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_mnlb, null));

                DefaultSliderView defaultSliderViewmnlb = new DefaultSliderView(this);

                defaultSliderViewmnlb.image(getResources().getIdentifier("bulletin", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewmnlb);

                DefaultSliderView defaultSliderView2mnlb = new DefaultSliderView(this);

                defaultSliderView2mnlb.image(getResources().getIdentifier("bulletin2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2mnlb);

                DefaultSliderView defaultSliderView3mnlb = new DefaultSliderView(this);

                defaultSliderView3mnlb.image(getResources().getIdentifier("bulletin3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3mnlb);

                DefaultSliderView defaultSliderView4mnlb = new DefaultSliderView(this);

                defaultSliderView4mnlb.image(getResources().getIdentifier("bulletin4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4mnlb);

                DefaultSliderView defaultSliderView5mnlb = new DefaultSliderView(this);

                defaultSliderView5mnlb.image(getResources().getIdentifier("bulletin5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5mnlb);

                Config.videoCode = "ydRCim_fNCo";

                TextView _1990 = (TextView) findViewById(R.id.date_1990);
                TextView _1986 = (TextView) findViewById(R.id.date_1930);
                TextView _1990to2000 = (TextView) findViewById(R.id.date_1947);


                final MultiColumnTextViewEx l_1990 = (MultiColumnTextViewEx) findViewById(R.id.date_1990_Layout);
                final MultiColumnTextViewEx l_1986 = (MultiColumnTextViewEx) findViewById(R.id.date_1986_Layout);
                final MultiColumnTextViewEx l_1990to2000 = (MultiColumnTextViewEx) findViewById(R.id.date_1990to2000_Layout);


                _1990.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1990.getVisibility() == View.GONE)
                            l_1990.setVisibility(View.VISIBLE);
                        else
                            l_1990.setVisibility(View.GONE);
                    }
                });

                _1986.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1986.getVisibility() == View.GONE)
                            l_1986.setVisibility(View.VISIBLE);
                        else
                            l_1986.setVisibility(View.GONE);
                    }
                });

                _1990to2000.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1990to2000.getVisibility() == View.GONE)
                            l_1990to2000.setVisibility(View.VISIBLE);
                        else
                            l_1990to2000.setVisibility(View.GONE);
                    }
                });

                break;

            case "pue":

                landmarkName.setText("Puerta del Parian");
                frameLayout.addView(getLayoutInflater().inflate(R.layout.activity_pue, null));

                DefaultSliderView defaultSliderViewpue = new DefaultSliderView(this);

                defaultSliderViewpue.image(getResources().getIdentifier("parian", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderViewpue);

                DefaultSliderView defaultSliderView2pue = new DefaultSliderView(this);

                defaultSliderView2pue.image(getResources().getIdentifier("parian2", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView2pue);

                DefaultSliderView defaultSliderView3pue = new DefaultSliderView(this);

                defaultSliderView3pue.image(getResources().getIdentifier("parian3", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView3pue);

                DefaultSliderView defaultSliderView4pue = new DefaultSliderView(this);

                defaultSliderView4pue.image(getResources().getIdentifier("parian4", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView4pue);

                DefaultSliderView defaultSliderView5pue = new DefaultSliderView(this);

                defaultSliderView5pue.image(getResources().getIdentifier("parian5", "drawable", getPackageName()));
                sliderShow.addSlider(defaultSliderView5pue);

                Config.videoCode = "e_wJCKfzssw";


                TextView _pu = (TextView) findViewById(R.id.pu);
                TextView _1593 = (TextView) findViewById(R.id.date_1593);
                TextView _1764 = (TextView) findViewById(R.id.date_1764);
                TextView _1982 = (TextView) findViewById(R.id.dated_1982);


                final MultiColumnTextViewEx l_pu = (MultiColumnTextViewEx) findViewById(R.id.pue_layout);
                final MultiColumnTextViewEx l_1593 = (MultiColumnTextViewEx) findViewById(R.id.date_1593_Layout);
                final MultiColumnTextViewEx l_1764 = (MultiColumnTextViewEx) findViewById(R.id.date_1764_Layout);
                final MultiColumnTextViewEx l_1982 = (MultiColumnTextViewEx) findViewById(R.id.date_1982_Layout);



                _pu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_pu.getVisibility() == View.GONE)
                            l_pu.setVisibility(View.VISIBLE);
                        else
                            l_pu.setVisibility(View.GONE);
                    }
                });

                _1593.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1593.getVisibility() == View.GONE)
                            l_1593.setVisibility(View.VISIBLE);
                        else
                            l_1593.setVisibility(View.GONE);
                    }
                });

                _1764.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1764.getVisibility() == View.GONE)
                            l_1764.setVisibility(View.VISIBLE);
                        else
                            l_1764.setVisibility(View.GONE);
                    }
                });

                _1982.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (l_1982.getVisibility() == View.GONE)
                            l_1982.setVisibility(View.VISIBLE);
                        else
                            l_1982.setVisibility(View.GONE);
                    }
                });


                break;

        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, 1).show();
        } else {
            String errorMessage = "There was an error initializing the YouTube Player.";
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(Config.videoCode);

            // Hiding player controls
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, DetailsActivity.this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    @Override
    protected void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

}
