package com.example.map;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;

    Spinner spinner;

    Button btncong, btntru;

    int i =10;

    LatLng School = new LatLng(10.852207, 106.758528);

    private ProgressDialog myProgress;

    private static final String MYTAG = "MYTAG";

    public static final int REQUEST_ID_ACCESS_COURSE_FINE_LOCATION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
        addSpinner();
    }

    private void setEvent(){
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(School, i));
            }
        });
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(School, i));
            }
        });
    }

    private void setControl() {
        btntru = (Button) findViewById(R.id.tru);
        btncong = (Button) findViewById(R.id.cong);
        spinner = (Spinner) findViewById(R.id.spinner);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void addSpinner() {
        List<String> list = new ArrayList<>();
        list.add("HYBRID");
        list.add("NORMAL");
        list.add("SATELLITE");
        list.add("TERRAIN");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getItemAtPosition(position).toString() == "HYBRID") {
                    map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
                if (spinner.getItemAtPosition(position).toString() == "NORMAL") {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                if (spinner.getItemAtPosition(position).toString() == "SATELLITE") {
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
                if (spinner.getItemAtPosition(position).toString() == "TERRAIN") {
                    map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(School, 17));
        map.addMarker(new MarkerOptions()
                .title("School")
                .snippet("Cao đẳng công nghệ thủ đức")
                .position(School)
        );
    }
}
