package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import com.example.courseapp1.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GasTankUp extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String NOWE_TANKOWANIE = "Nowe Tankowanie";

    private EditText dateEditText;
    private EditText milageEditText;
    private EditText litersEditText;
    private EditText costEditText;

    private Button confirmTankButton;
    private AutoData autoData;
    private DateFormat dateFormat;

    private TextView milageTextView;
    private TextView costTextView;
    private TextView litersTextView;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas_tank_up);
        obtainExtras();
        viewInit();
        setTitle(NOWE_TANKOWANIE);
    }

    private void obtainExtras() {
        autoData = (AutoData) getIntent().getExtras().getSerializable(MainMenu.SPECIAL_DATA);
    }

    private void viewInit() {
        dateEditText = findViewById(R.id.date);
        milageEditText = findViewById(R.id.milage);
        milageTextView = findViewById(R.id.milageTextView);
        litersTextView = findViewById(R.id.litersTextView);
        costTextView = findViewById(R.id.costTextView);
        litersEditText = findViewById(R.id.liters);
        costEditText = findViewById(R.id.cost);
        confirmTankButton = findViewById(R.id.confirmTankButton);

        dateEditText.setText(getCurrentDate());

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, GasTankUp.this, year, month, day);

                datePickerDialog.show();
            }
        });

        confirmTankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateMileage()){
                    TankUpRecord tank = new TankUpRecord(getTankUpDate(), getMileageInteger(), getLitersInteger(), getCostInteger());
                    autoData.getTankUpRecord().add(tank);
                }
            }
        });

        milageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //wyjscie z kontrolki
                if (hasFocus == false){
                    validateMileage();
                }
            }
        });

        costEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false){
                    validateCosts();
                }
            }
        });

        litersEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == false){
                    validateLiters();
                }
            }
        });

    }

    private boolean validateLiters() {
        if (TextUtils.isEmpty(litersEditText.getText().toString())){
            litersTextView.setText("Litry muszą zostać podane!");
            litersTextView.setTextColor(getResources().getColor(R.color.red));
            return false;
        }else {
            litersTextView.setText("Zatankowane Litry");
            litersTextView.setTextColor(getResources().getColor(R.color.black));
            return true;
        }
    }

    private boolean validateCosts() {
        if (TextUtils.isEmpty(costEditText.getText().toString())){
            costTextView.setText("Koszty muszą zostać podane!");
            costTextView.setTextColor(getResources().getColor(R.color.red));
            return false;
        }else {
            costTextView.setText("Koszty Paliwa");
            costTextView.setTextColor(getResources().getColor(R.color.black));
            return true;
        }
    }

    private boolean validateMileage() {
        if (TextUtils.isEmpty(milageEditText.getText().toString())){
            milageTextView.setText("Przebieg musi zostać podany!");
            milageTextView.setTextColor(getResources().getColor(R.color.red));
            return false;
        }
        if (Integer.valueOf(milageEditText.getText().toString()) <= 0){
            milageTextView.setText("Przebieg musi być wiekszy niz 0!");
            milageTextView.setTextColor(getResources().getColor(R.color.red));
            return false;
        }else{
            milageTextView.setText("Przebieg");
            milageTextView.setTextColor(getResources().getColor(R.color.black));
        }
        int size = autoData.getTankUpRecord().size();
        if (autoData.getTankUpRecord().size() !=0){
           Integer newMilage = Integer.valueOf(milageEditText.getText().toString());
           Integer oldMilage = autoData.getTankUpRecord().get(size - 1).getMileage();
           if (newMilage <= oldMilage){
               milageTextView.setText("Nieprawidłowy Przebieg!");
               milageTextView.setTextColor(getResources().getColor(R.color.red));
               return false;
           }else{
               milageTextView.setText("Przebieg");
               milageTextView.setTextColor(getResources().getColor(R.color.black));
               return true;
           }
        }
        return true;
    }

    private Date getTankUpDate() {

        try {
            return dateFormat.parse(dateEditText.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateFormat = DateFormat.getDateInstance();
        Date date = new Date();
        return date;
    }

    private Integer getCostInteger() {
        return Integer.valueOf(costEditText.getText().toString());
    }

    private Integer getLitersInteger() {
        return Integer.valueOf(litersEditText.getText().toString());
    }

    private Integer getMileageInteger() {
        return Integer.valueOf(milageEditText.getText().toString());
    }

    private String getCurrentDate() {
        dateFormat = DateFormat.getDateInstance();
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
        dateEditText.setText(dateFormat.format(calendar.getTime()));
    }
}