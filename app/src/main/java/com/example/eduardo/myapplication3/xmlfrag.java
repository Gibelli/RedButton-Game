package com.example.eduardo.myapplication3;


import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;
import java.util.Timer;


public class xmlfrag extends Activity {

    static int tempo = 0;
    static int horas = 0;
    static int minutos = 0;

    static int contador = 0;
    static boolean comecou = false;
    static Timer starter = new Timer();
    static int crono = 0;
    static int backupcrono = 0;

    static boolean jajogou = false;

    CountDownTimer regressivo;

    ViewFlipper viewFlipper;

    Animation slide_in_left,slide_out_right;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_xmlfrag);
        setContentView(R.layout.file);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);

        slide_in_left = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        viewFlipper.setAnimation(slide_in_left);
        viewFlipper.setAnimation(slide_out_right);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.xmlfrag, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void soma (View b){
        Button mais = (Button) b;
        if(tempo <59){
            tempo++;
        }

        TextView periodo = (TextView) findViewById(R.id.timeSeconds);
        periodo.setText("" + tempo);

    }

    public void subtrai (View b){
        Button menos = (Button) b;
        if(tempo > 0){
            tempo--;
        }

        TextView periodo = (TextView) findViewById(R.id.timeSeconds);
        periodo.setText("" + tempo);

    }

    public void somaMinuto (View b){

        if(minutos <59){
            minutos++;
        }

        TextView periodo = (TextView) findViewById(R.id.timeMinutes);
        periodo.setText("" + minutos);

    }

    public void subtraiMinuto (View b){

        if(minutos > 0){
            minutos--;
        }

        TextView periodo = (TextView) findViewById(R.id.timeMinutes);
        periodo.setText("" + minutos);

    }

    public void somaHora(View b) {

        if(horas <59){
            horas++;
        }

        TextView periodo = (TextView) findViewById(R.id.timeHours);
        periodo.setText("" + horas);

    }

    public void subtraiHora (View b){

        if(horas > 0){
            horas--;
        }

        TextView periodo = (TextView) findViewById(R.id.timeHours);
        periodo.setText("" + horas);

    }

    public void SwitchLayout (View b){

        crono = tempo + (horas * 3600) + (minutos * 60);
        if(crono == 0)
            return;

        viewFlipper.showNext();

        ImageButton red = (ImageButton) findViewById(R.id.imageButton);
        red.setClickable(true);
        red.setEnabled(true);

        Button back = (Button) findViewById(R.id.back);
        back.setClickable(true);
        back.setEnabled(true);

        Button retry = (Button) findViewById(R.id.button3);
        retry.setEnabled(true);
        retry.setClickable(true);

    }

    public void comingBack (View b){

        Retry(b);

        viewFlipper.showPrevious();

        Button start = (Button) findViewById(R.id.inicio);
        start.setEnabled(true);
        start.setClickable(true);

    }

    public void redButton (View b){
        if(crono == 0)
            return;

        if(comecou == false) {
            comecou = true;
            crono = tempo + (horas * 3600) + (minutos * 60);
            backupcrono = crono;

            TextView timer = (TextView) findViewById(R.id.timelft);
            timer.setText("" + crono );
            timer.setVisibility(View.VISIBLE);

            TextView start = (TextView) findViewById(R.id.startText);
            start.setVisibility(View.INVISIBLE);


            regressivo = new CountDownTimer(crono * 1000,1000){
                public  void onTick(long millisUntilFinished){
                    atualizatempo();
                }
                public void onFinish(){

                    ImageButton red = (ImageButton) findViewById(R.id.imageButton);
                    red.setClickable(false);
                    red.setEnabled(false);
                    crono = 1;

                    atualizatempo();
            }
        }.start();



        }

        contador++;
        Button placar = (Button) findViewById(R.id.button5);
        placar.setText("" + contador);

    }

    public void atualizatempo(){
        crono--;
        TextView timer = (TextView) findViewById(R.id.timelft);
        String h;
        String min;
        String s;

        int h1 = crono/3600;
        int h2 = crono % 3600;
        int min1 = h2/60;
        int s1 = crono % 60;


        if(h1>= 10){
          h = "" + h1;
        }
        else{
            h = "0"+ h1;
        }

        if (min1 >= 10){
            min = "" + min1;
        }
        else
        {
            min = "0" + min1;
        }
        if(crono%60 >= 10){
            s = "" + s1;
        }
        else
        {
            s = "0" + s1;
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(h);
        stringBuilder.append(": ");
        stringBuilder.append(min);
        stringBuilder.append(": ");
        stringBuilder.append(s);

        String finalString = stringBuilder.toString();

        timer.setText(finalString);



    }

    public void Retry (View r){
        comecou = false;
        regressivo.cancel();


        crono = backupcrono;

        contador = 0;
        Button placar = (Button) findViewById(R.id.button5);
        placar.setText("" + contador);

        TextView timer = (TextView) findViewById(R.id.timelft);
        timer.setVisibility(View.INVISIBLE);

        TextView start = (TextView) findViewById(R.id.startText);
        start.setVisibility(View.VISIBLE);

        ImageButton red = (ImageButton) findViewById(R.id.imageButton);
        red.setClickable(true);
        red.setEnabled(true);

    }




}
