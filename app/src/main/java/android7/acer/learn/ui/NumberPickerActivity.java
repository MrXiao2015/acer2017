package android7.acer.learn.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;

import android7.acer.learn.R;

public class NumberPickerActivity extends AppCompatActivity {

    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);

        numberPicker = (NumberPicker) findViewById(R.id.np_1);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(25);
        numberPicker.setValue(12);
        numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return value + "期";
            }
        });
    }
}
