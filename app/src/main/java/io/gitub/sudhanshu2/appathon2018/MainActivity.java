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
//              Clears the textbox when user clicks in
                editText.getText().clear();
            }
        });

        final Button powerButton = findViewById(R.id.power_button);
        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Activate the application stuff
                powerButton.setSelected(!powerButton.isSelected());
                if(powerButton.isSelected()) {
                    // what to do when the power button is checked
                } else {
                    // what happens when the power button is not checked
                }
            }
        });

        final Switch startAux = (Switch) findViewById(R.id.start_with_aux);
        final Switch stopMusic = (Switch) findViewById(R.id.stop_music);
        final Switch lowerVolume = (Switch) findViewById(R.id.lower_volume);
        Switch playTone = (Switch) findViewById(R.id.play_tone);
        final Button submitButton = findViewById(R.id.submit_button);
        final String wordToLookFor;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              This updates the settings of the app
                if(startAux.isChecked()) {
                    // put code here for starting app on aux connection
                }
                if (stopMusic.isChecked()) {
                    // put code here that stops music when word heard
                }
                if (lowerVolume.isChecked()) {
                    // put code here that lowers volume when word heard
                }
                wordToLookFor = editText.getText().toString();
            }
        });





    }
}
