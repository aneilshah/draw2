package com.asi.draw1;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int num_sides=5;
    private int min_sides=5;
    private SeekBar seekBar;
    private TextView textView;
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIDs();
        seekBar.setMax(10);
        seekBar.setProgress(0);

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                setSides(seekBar.getProgress()+min_sides);
                //Toast.makeText(getApplicationContext(), "Changing slider value", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Started tracking slider", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Stopped tracking slider", Toast.LENGTH_SHORT).show();
            }
        });
        // Initialize Display
       setSides(min_sides);
     }

    public void onClickB1(View view) {setSides(5);}
    public void onClickB2(View view) {setSides(10);}
    public void onClickB3(View view) {setSides(15);}

    // get IDs
    private void getIDs() {
        seekBar = (SeekBar) findViewById(R.id.slider1);
        textView = (TextView) findViewById(R.id.sliderText);
        drawView = (DrawView) findViewById(R.id.draw_view);
    }

    private void setSides(int n) {
        num_sides = n;

        drawView.set_num_sides(num_sides);
        drawView.postInvalidate();
        seekBar.setProgress(n - min_sides);
        //textView.setText("Number of Sides (" + num_sides + "/" + (min_sides + seekBar.getMax())+")");
        textView.setText("Number of Sides (" + num_sides + ")");
    }
}
