package Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GPSActivity extends AppCompatActivity {

    private int bestTime100;
    private int secTo100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //savedInstance moze byc nullem dlatego trzeba go zabezpieczyc
        // TODO: 10.10.2020 ustawienie widoku i takich pierdół
        if (savedInstanceState != null){
            // odzyskiwanie odczytu
            //odzyskanie rekordu
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        // TODO: 10.10.2020 podpięcie gps
    }

    @Override
    protected void onPause() {
        super.onPause();
        // TODO: 10.10.2020 odpięcie gps 
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        /// TODO: 10.10.2020 zapisanie do bundla ostatni odczyt
        //zapisanie rekoru przejazdu
    }
}
