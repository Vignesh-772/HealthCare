package com.hackathon.healthcare.ui.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.hackathon.healthcare.BuildConfig;
import com.hackathon.healthcare.R;
import com.hackathon.healthcare.databinding.FragmentDashboardBinding;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    View root;
    double latitude = 11.4970126;
    double longitude = 77.2749137;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        mMapView = (MapView) root.findViewById(R.id.mapView);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        mMapView.setBuiltInZoomControls(true);
        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(17);
        GeoPoint gPt = new GeoPoint(latitude,longitude);
        mMapController.setCenter(gPt);
        Marker marker = new Marker(mMapView);
        marker.setPosition(new GeoPoint(latitude, longitude));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Drawable dr = getResources().getDrawable(R.drawable.ic_gps_location_2);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));
        marker.setIcon(d);
        marker.setInfoWindow(null);
        mMapView.getOverlays().add(marker);
        return root;
    }

    private MapView mMapView;
    private MapController mMapController;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}