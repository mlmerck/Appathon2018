package io.gitub.sudhanshu2.appathon2018;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;
import edu.cmu.pocketsphinx.SpeechRecognizerSetup;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText) findViewById(R.id.text_input);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 do stuff when button is pressed
                editText.getText().clear();
            }
        });

        final Button powerButton = findViewById(R.id.power_button);
        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 do stuff when button is pressed
                powerButton.setSelected(!powerButton.isSelected());
            }
        });


    }
}
