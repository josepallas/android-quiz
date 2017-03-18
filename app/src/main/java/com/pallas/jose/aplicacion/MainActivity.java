package com.pallas.jose.aplicacion;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private TextSwitcher mPreguntaText;
    private List<Pregunta> preguntas;
    private TextView mAciertosText;
    private int aciertos=0;
    private int posicion=0;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        preguntas = new ArrayList<Pregunta>();
        preguntas.add(new Pregunta("El Prisionero de Azkaban es la mejor película de Harry Potter.", true));
        preguntas.add(new Pregunta("Las Reliquias de la muerte es la peor película de Harry Potter", false));
        preguntas.add(new Pregunta("Es Flipendo el hechizo más poderoso de Harry Potter", false));
        preguntas.add(new Pregunta("Es creíble que Harry venza siempre a Voldemort", false));

        random = new Random();


        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mPreguntaText = (TextSwitcher) findViewById(R.id.pregunta_text);
        mAciertosText = (TextView) findViewById(R.id.aciertos_text);
        mPreguntaText.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView switcherTextView = new TextView(getApplicationContext());
                switcherTextView.setText("Click The Below Button");
                switcherTextView.setShadowLayer(6, 6, 6, Color.BLACK);
                return switcherTextView;
            }
        });
        mPreguntaText.setInAnimation(this, R.anim.slide_out_left);
        mPreguntaText.setOutAnimation(this, R.anim.slide_in_right);

        mAciertosText.setText(getResources().getString(R.string.aciertos_text) + "0");

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Respuesta(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Respuesta(false);
            }
        });
        CambiarPregunta();

    }
    private void Respuesta(boolean seleccion){
        boolean esRespuesta=preguntas.get(posicion).isSolucion();
        if (esRespuesta) {
            Toast.makeText(MainActivity.this,
                    R.string.correct_toast,
                    Toast.LENGTH_SHORT).show();
            aciertos=aciertos+1;
            String respuesta=getResources().getString(R.string.aciertos_text)+String.valueOf(aciertos);
            mAciertosText.setText(respuesta);
            CambiarPregunta();
        }

        else
            Toast.makeText(MainActivity.this,
                    R.string.incorrect_toast,
                    Toast.LENGTH_SHORT).show();
    }
    private void CambiarPregunta(){
        posicion=random.nextInt(preguntas.size());
        mPreguntaText.setText(preguntas.get(posicion).getPregunta());
    }
}
