package Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.courseapp1.R;

public class AddCar extends AppCompatActivity {

    public static final String NEW_CAR = "NEW_CAR";

    EditText brand;
    EditText model;
    EditText color;
    Button confirmCar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        brand = findViewById(R.id.brandEditText);
        model = findViewById(R.id.modelEditText);
        color = findViewById(R.id.colorEditText);

        confirmCar = findViewById(R.id.confirmCarButton);
        confirmCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoData autoData = new AutoData(model.getText().toString(), brand.getText().toString(), color.getText().toString());
                Intent intent = new Intent();
                intent.putExtra(NEW_CAR,autoData);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
