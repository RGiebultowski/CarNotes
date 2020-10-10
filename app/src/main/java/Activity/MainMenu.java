package Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.courseapp1.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {

    public static final String SPECIAL_DATA = "Special_Data";
    public static final String AUTO_PREF = "AUTO_PREF";
    public static final Integer REQUEST_CODE = 123;

    private Button tankInsertButton;
    private Button newCarButton;
    private Spinner autoChooseSpinner;

    private ArrayList<AutoData> cars;
    private ArrayAdapter<AutoData> arrayAdapter;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initViews();


    }

    @Override
    protected void onResume() {
        super.onResume();
        //zapisujemy dane
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(AUTO_PREF, gson.toJson(cars));
        editor.apply();
    }

    private void initViews() {
        tankInsertButton = findViewById(R.id.tankInsertButton);
        newCarButton = findViewById(R.id.newCarButton);
        autoChooseSpinner = findViewById(R.id.autoChooseSpinner);

        initAutoList();
        initArrayAdapter();

        autoChooseSpinner.setAdapter(arrayAdapter);

        tankInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GasTankUp.class);
                intent.putExtra(SPECIAL_DATA, getCurrentCar());
                startActivity(intent);
            }
        });

        newCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddCar.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        if (cars.isEmpty()){
            Intent intent = new Intent(context, AddCar.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                if (data != null){
                    AutoData newAutoData = (AutoData) data.getExtras().get(AddCar.NEW_CAR);
                    cars.add(newAutoData);
                }
            }
        }
    }

    private void initArrayAdapter() {
        arrayAdapter = new ArrayAdapter<AutoData>(context, android.R.layout.simple_spinner_item, cars);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void initAutoList() {
        /*cars = new ArrayList<AutoData>();
        cars.add(new AutoData("Mito", "Alfa-Romeo", "Czerwony"));
        cars.add(new AutoData("Mondeo", "Ford", "szary"));*/

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String string = sharedPreferences.getString(AUTO_PREF, null);
        Gson gson = new Gson();
        ArrayList<AutoData> newAutoList = gson.fromJson(string, new TypeToken<ArrayList<AutoData>>(){}.getType());

        if (newAutoList != null){
            cars = newAutoList;
        }else{
            cars = new ArrayList<>();
        }
    }

    private AutoData getCurrentCar() {
        return (AutoData) autoChooseSpinner.getSelectedItem();
    }
}
