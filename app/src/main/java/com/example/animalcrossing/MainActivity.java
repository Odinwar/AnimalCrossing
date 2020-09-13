package com.example.animalcrossing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private CheckBox cbTiger, cbElephant, cbPanda;
    private SeekBar sbTiger, sbElephant, sbPanda;
    private ImageButton imgStartGame;
    private TextView tvScore;
    private int CaCuoc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        event();
    }

    private void event() {
        sbTiger.setEnabled(false);
        sbElephant.setEnabled(false);
        sbPanda.setEnabled(false);
        cbTiger.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbElephant.setChecked(false);
                    cbPanda.setChecked(false);
                    CaCuoc = 1;
                }
            }
        });
        cbElephant.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbTiger.setChecked(false);
                    cbPanda.setChecked(false);
                    CaCuoc = 2;
                }
            }
        });
        cbPanda.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbElephant.setChecked(false);
                    cbTiger.setChecked(false);
                    CaCuoc = 3;
                }
            }
        });
        imgStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CaCuoc == 0)
                    Toast.makeText(getApplicationContext(), "Vui long chon 1 nhan vat", Toast.LENGTH_SHORT).show();
                else {
                    imgStartGame.setVisibility(View.INVISIBLE);
                    cbTiger.setEnabled(false);
                    cbElephant.setEnabled(false);
                    cbPanda.setEnabled(false);
                    CountDownTimer countDownTimer = new CountDownTimer(10000, 100) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int tiger = sbTiger.getProgress();
                            int elephant = sbElephant.getProgress();
                            int panda = sbPanda.getProgress();
                            int max = sbTiger.getMax();
                            if (tiger == max || elephant == max || panda == max) {
                                this.onFinish();
                            } else {
                                sbTiger.setProgress(tiger + new Random().nextInt(5));
                                sbElephant.setProgress(elephant + new Random().nextInt(5));
                                sbPanda.setProgress(panda + new Random().nextInt(5));
                            }
                        }

                        @Override
                        public void onFinish() {
                            int score = Integer.parseInt(tvScore.getText().toString());
                            if(score ==0) score = 100;
                            if (CaCuoc == whoWin()) {
                                score += 10;
                            } else {
                                score -= 10;
                            }
                            tvScore.setText(score + "");
                            imgStartGame.setVisibility(View.VISIBLE);
                            cbTiger.setEnabled(true);
                            cbElephant.setEnabled(true);
                            cbPanda.setEnabled(true);
                            sbTiger.setProgress(3);
                            sbElephant.setProgress(3);
                            sbPanda.setProgress(3);
                            this.cancel();
                        }
                    }.start();
                }
            }
        });
    }

    private int whoWin() {
        int tiger = sbTiger.getProgress();
        int elephant = sbElephant.getProgress();
        int max = sbTiger.getMax();
        if (tiger == max) return 1;
        else if (elephant == max) return 2;
        else return 3;
    }

    private void anhXa() {
        // CheckBox
        cbTiger = (CheckBox) findViewById(R.id.checkbox_tiger);
        cbElephant = (CheckBox) findViewById(R.id.checkbox_elephant);
        cbPanda = (CheckBox) findViewById(R.id.checkbox_panda);
        // SeekBar
        sbTiger = (SeekBar) findViewById(R.id.seekbar_tiger);
        sbElephant = (SeekBar) findViewById(R.id.seekbar_elephant);
        sbPanda = (SeekBar) findViewById(R.id.seekbar_panda);
        //ImageButton
        imgStartGame = (ImageButton) findViewById(R.id.startgame);
        //TextView
        tvScore = (TextView) findViewById(R.id.score);
    }
}
