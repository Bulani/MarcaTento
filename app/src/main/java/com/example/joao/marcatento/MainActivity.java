package com.example.joao.marcatento;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuZerar:
                definirTextos();
                zerarJogo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private Button adicionarA, adicionarB, retirarA, retirarB, btnTrucoA, btnTrucoB;
    private TextView timeA, timeB, placarA, placarB, pontosA, pontosB;

    private int contadorA = 0, contadorB = 0, cplacarA = 0, cplacarB = 0;

    private String erroPontoNegativo = "Os pontos não podem ser negativos!";
    private String erroTruco11 = "Não é permitido trucar com a mão de 11!";

    private AdView mAdView;

    private InterstitialAd mInterstitialAd;

    private void definirTextos(){
        pontosA.setText(String.valueOf(contadorA));
        pontosB.setText(String.valueOf(contadorB));
    }

    private void zerarJogo(){
        contadorA = 0;
        contadorB = 0;
        cplacarA = 0;
        cplacarB = 0;
        pontosA.setText(String.valueOf(contadorA));
        pontosB.setText(String.valueOf(contadorB));
        placarA.setText("");
        placarB.setText("");

        mInterstitialAd.show();
    }

    private void adicionarPontoA(){
        if (contadorA == 11){
            contadorA = -1;
            contadorB = 0;
            pontosA.setText(String.valueOf(contadorA));
            pontosB.setText(String.valueOf(contadorB));
            cplacarA ++;
            placarA.setText("Partidas ganhas: " + cplacarA);

            mInterstitialAd.show();
        }

        if (timeA.getText().toString().equals("R1BOLINHAL2QUADRADO")){
            contadorA++;
            contadorA++;
            pontosA.setText(String.valueOf(contadorA));
        }

        contadorA++;
        pontosA.setText(String.valueOf(contadorA));
    }

    private void trucoA(){
        if (contadorA == 12){
            contadorA = -1;
            contadorB = 0;
            pontosA.setText(String.valueOf(contadorA));
            pontosB.setText(String.valueOf(contadorB));
            cplacarA ++;
            placarA.setText("Partidas ganhas: " + cplacarA);

            mInterstitialAd.show();
        }

        else {
            contadorA = contadorA + 3;
            pontosA.setText(String.valueOf(contadorA));
        }
    }

    private void retirarPontoA(){
        if (contadorA == 0){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, erroPontoNegativo, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            contadorA--;
            pontosA.setText(String.valueOf(contadorA));
        }
    }

    private void adicionarPontoB(){
        if (contadorB == 11){
            contadorB = -1;
            contadorA = 0;
            pontosB.setText(String.valueOf(contadorB));
            pontosA.setText(String.valueOf(contadorA));
            cplacarB ++;
            placarB.setText("Partidas ganhas: " + cplacarB);

            mInterstitialAd.show();
        }

        contadorB++;
        pontosB.setText(String.valueOf(contadorB));
    }

    private void trucoB(){
        if (contadorB == 12){
            contadorB = -1;
            contadorA = 0;
            pontosB.setText(String.valueOf(contadorB));
            pontosA.setText(String.valueOf(contadorA));
            cplacarB ++;
            placarB.setText("Partidas ganhas: " + cplacarB);

            mInterstitialAd.show();
        }

        else {
            contadorB = contadorB + 3;
            pontosB.setText(String.valueOf(contadorB));
        }
    }

    private void retirarPontoB(){
        if (contadorB == 0){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, erroPontoNegativo, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            contadorB--;
            pontosB.setText(String.valueOf(contadorB));
        }
    }

    private void verificadorA(){
        if (contadorA >= 12){
            contadorA = 0;
            contadorB = 0;
            pontosA.setText(String.valueOf(contadorA));
            pontosB.setText(String.valueOf(contadorB));
            cplacarA ++;
            placarA.setText("Partidas ganhas: " + cplacarA);

            mInterstitialAd.show();
        }
    }

    private void verificadorB(){
        if (contadorB >= 12){
            contadorB = 0;
            contadorA = 0;
            pontosB.setText(String.valueOf(contadorB));
            pontosA.setText(String.valueOf(contadorA));
            cplacarB ++;
            placarB.setText("Partidas ganhas: " + cplacarB);

            mInterstitialAd.show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bootstrap();

        definirTextos();

        adicionarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarPontoA();
            }
        });

        btnTrucoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contadorA == 11){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, erroTruco11, Toast.LENGTH_SHORT);
                    toast.show();
                }

                else { trucoA(); }

                verificadorA();

            }
        });

        retirarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retirarPontoA();
            }
        });

        adicionarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarPontoB();
            }
        });

        btnTrucoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contadorB == 11){
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, erroTruco11, Toast.LENGTH_SHORT);
                    toast.show();
                }

                else { trucoB(); }

                verificadorB();

            }
        });

        retirarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retirarPontoB();
            }
        });

        MobileAds.initialize(this, "ca-app-pub-8851031145457703~3046887167");

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.admob_banner_teste));

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_intersticial_teste));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

    }

    private void bootstrap(){

        timeA = (TextView) findViewById(R.id.txtViewTimeA);
        timeB = (TextView) findViewById(R.id.txtViewTimeB);

        pontosA = (TextView) findViewById(R.id.txtViewPontosA);
        pontosB = (TextView) findViewById(R.id.txtViewPontosB);

        placarA = (TextView) findViewById(R.id.txtViewPlacarA);
        placarB = (TextView) findViewById(R.id.txtViewPlacarB);

        adicionarA = (Button) findViewById(R.id.btnPontoA);
        retirarA = (Button) findViewById(R.id.btnPontoAmenos);

        adicionarB = (Button) findViewById(R.id.btnPontoB);
        retirarB = (Button) findViewById(R.id.btnPontoBmenos);

        btnTrucoA = (Button) findViewById(R.id.btnTrucoA);
        btnTrucoB = (Button) findViewById(R.id.btnTrucoB);

        mAdView = findViewById(R.id.adView);

    }


    //MUDAR NOME DO TIME
    public void onClickA(View view) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.activity_nome, null);
        final EditText nomeA = (EditText) mView.findViewById(R.id.editTextNomeTime);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        Button btnGravar = (Button) mView.findViewById(R.id.btnGravarNome);
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nomeA.getText().toString().equals("")){
                    timeA.setText(timeA.getText().toString());
                    dialog.dismiss();
                }
                else{
                    timeA.setText(nomeA.getText().toString());
                    dialog.dismiss();
                }
            }
        });

    }

    public void onClickB(View view) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.activity_nome, null);
        final EditText nomeB = (EditText) mView.findViewById(R.id.editTextNomeTime);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        Button btnGravar = (Button) mView.findViewById(R.id.btnGravarNome);
        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nomeB.getText().toString().equals("")){
                    timeB.setText(timeB.getText().toString());
                    dialog.dismiss();
                }
                else {
                    timeB.setText(nomeB.getText().toString());
                    dialog.dismiss();
                }
            }
        });
    }
}
