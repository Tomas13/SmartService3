package kz.growit.smartservice3.fragments;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.rey.material.widget.Button;

import kz.growit.smartservice3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayMapFragment extends Fragment {


    private Button buttonClose;
    private GoogleMap map;


    public DisplayMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View viewFragment = inflater.inflate(R.layout.fragment_map, container, false);
        //SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        //.findFragmentById(R.id.mapLF);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapLF);



        buttonClose = (Button) viewFragment.findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(new LatLng(51.12638435, 71.43703759), 14));

                map.getUiSettings().setZoomControlsEnabled(false);
                map.getUiSettings().setCompassEnabled(true);
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMapToolbarEnabled(true);
                map.getUiSettings().setAllGesturesEnabled(true);

            }
        });
        return viewFragment;
    }



    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
