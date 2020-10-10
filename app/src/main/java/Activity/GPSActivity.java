package Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.courseapp1.R;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GPSActivity extends AppCompatActivity implements LocationListener {

    private TextView currentSpeed;
    private TextView bestSpeed;
    private Button startRun;
    private LocationManager locationManager;
    private Date startTime;
    private boolean moreThan100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_layout);

        currentSpeed.findViewById(R.id.currentSpeed);
        bestSpeed.findViewById(R.id.bestSpeed);
        startRun.findViewById(R.id.startRun);

        startRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = new Date();
                moreThan100 = false;
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //savedInstance moze byc nullem dlatego trzeba go zabezpieczyc
        if (savedInstanceState != null){
            // odzyskiwanie odczytu
            //odzyskanie rekordu
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500L, 2.0f, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
        // TODO: 10.10.2020 odpięcie gps 
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        /// TODO: 10.10.2020 zapisanie do bundla ostatni odczyt
        //zapisanie rekoru przejazdu
    }

    @Override
    public void onLocationChanged(Location location) {
        //ctr + alt + v skrót do tworzenia zmiennej ez
        float speed = location.getSpeed();
        float kmhSpeed = speed * 3600 / 1000;
        currentSpeed.setText(kmhSpeed+ " km/h");

        // TODO: 10.10.2020 uruchomienie stopera w momencie kiedy samochod ruszy czyli od 5km/h  
        
        if (kmhSpeed >= 100 && !moreThan100){
            long diffInMs = new Date().getTime() - startTime.getTime();
            long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs);
            bestSpeed.setText("Ostatni rekord:" + diffInSec + " sec.");
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
