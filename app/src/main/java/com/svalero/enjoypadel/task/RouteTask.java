package com.svalero.enjoypadel.task;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.DirectionsApi;

import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import com.svalero.enjoypadel.R;
import com.svalero.enjoypadel.utils.DirectionUtils;
import com.svalero.enjoypadel.view.CenterDetailView;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;


public class RouteTask extends AsyncTask<Void, Void, DirectionsResult> {

    private boolean error = false;
    private CenterDetailView view;
    private LatLng initialMarker, finalMarker;
    private DirectionsApiRequest request;
    private FusedLocationProviderClient fusedLocationClient;
    private double wayLatitude;
    private double wayLongitude;

    public RouteTask(CenterDetailView view,LatLng initialMarker, LatLng finalMarker) {
        this.view = view;
        this.initialMarker = initialMarker;
        this.finalMarker = finalMarker;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(view.getApplicationContext());
    }

    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3)
                .setApiKey(view.getApplicationContext().getString(R.string.google_maps_key))
                .setConnectTimeout(3, TimeUnit.SECONDS)
                .setReadTimeout(3, TimeUnit.SECONDS)
                .setWriteTimeout(3, TimeUnit.SECONDS);
    }

    @Override
    protected DirectionsResult doInBackground(Void... input) {
        try {
            return request.await();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (ApiException apie) {
            error = true;
            apie.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (ActivityCompat.checkSelfPermission(view.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(view.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return;
        }


        request = DirectionsApi.newRequest(getGeoContext())
                .mode(TravelMode.DRIVING)
                .origin(DirectionUtils.fromMapsToDirectionsApi(initialMarker))
                .destination(DirectionUtils.fromMapsToDirectionsApi(finalMarker))
                .departureTime(DateTime.now())
                .alternatives(true);
    }

    @Override
    protected void onPostExecute(DirectionsResult result) {
        super.onPostExecute(result);

        if (error) {
            Toast.makeText(view.getApplicationContext(), "Error al conectar con la API", Toast.LENGTH_SHORT).show();
        }

        if (result.routes.length == 0) {
            Toast.makeText(view.getApplicationContext(), "No hay rutas entre los 2 puntos", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
