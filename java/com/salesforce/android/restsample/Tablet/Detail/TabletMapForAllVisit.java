//package com.salesforce.android.restsample.Tablet.Detail;
//
//import android.animation.ValueAnimator;
//import android.app.Activity;
//import android.app.Fragment;
////import android.support.v4.app.Fragment;
//import android.app.FragmentManager;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.graphics.Typeface;
//import android.location.Location;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.MapView;
//import com.google.android.gms.maps.MapsInitializer;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.salesforce.android.restsample.DB.Model.DBAccount.Account;
//import com.salesforce.android.restsample.DB.Model.DBAccount.AccountDatabaseHelper;
//import com.salesforce.android.restsample.DB.Model.DBAddress.Address;
//import com.salesforce.android.restsample.DB.Model.DBAddress.AddressDatabaseHelper;
//import com.salesforce.android.restsample.ItemSaleSummary.EntryAdapterSaleSummary;
//import com.salesforce.android.restsample.ItemSaleSummary.EntryItemSaleSummary;
//import com.salesforce.android.restsample.ItemSaleSummary.ItemSaleSummary;
//import com.salesforce.android.restsample.Library.ImageConverter;
//import com.salesforce.android.restsample.Library.RoundCornerProgressBar;
//import com.salesforce.android.restsample.Library.TextRoundCornerProgressBar;
//import com.salesforce.android.restsample.MainPLANs.ClassifiedPlan.ClassifiedPlan;
//import com.salesforce.android.restsample.R;
//import com.salesforce.android.restsample.Tablet.CommunicatorV4;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptor;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.LatLngBounds;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.Polygon;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.Status;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//
//import pl.tajchert.nammu.Nammu;
//import pl.tajchert.nammu.PermissionCallback;
//
///**
// * Created by pannikar on 8/5/16 AD.
// */
////public class TabletMapForAllVisit extends Fragment implements OnMapReadyCallback {
//public class TabletMapForAllVisit extends Fragment implements OnMapReadyCallback {
//
//    private static final int REQUEST_LOCATION = 1;
//    private String mCurrentLocStr;
//    private TextView mLocTextView;
//    private GoogleMap mMapView;
//    private static final long UPDATE_INTERVAL = 5000;
//    private static final long FASTEST_INTERVAL = 1000;
//    private List<LatLng> listOfLatLng = new ArrayList<>();
//    private LocationRequest mRequest;
//    public static int PLACE_AUTOCOMPLETE_REQUEST_CODE = 2;
//    private GoogleApiClient mApiClient;
//    private Polygon mLastPolygon;
//    private MarkerOptions mapMarker;
//    int currentPt;
//    int mAnimationZoom = 15;
//
//    int mPinDrawables[] = new int[]{R.drawable.pin_06,
//            R.drawable.pin_07,
//            R.drawable.pin_06,
//            R.drawable.pin_07,
//            R.drawable.pin_06,
//            R.drawable.pin_07,
//            R.drawable.pin_06};
//    private int pinCount = 0;
//    private ImageView mClearMarkersBtn;
//    private ImageView mAnimationBtn;
//    private ImageView mGeoCodingBtn;
//    private ValueAnimator vAnimator;
//    private LatLng defaultLocation;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // View view = inflater.inflate(R.layout.detail_fragment, container, false);
//        View v = inflater.inflate(R.layout.tablet_all_map_visit, container, false);
//        bindWidget(v);
//
//        return v;
//    }
//
//    private void bindWidget(View v) {
//
////        SupportMapFragment mySupportMapFragment = (SupportMapFragment) getSupportFragmentManager()
////                                                                    .findFragmentById(R.id.mMapView);
//
//        MapFragment mySupportMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mMapView);
//        mySupportMapFragment.getMapAsync(this);
//
//        mLocTextView = (TextView) v.findViewById(R.id.mLocationTextView);
//
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMapView = googleMap;
//        setupMap();
//        // requestRunTimePermission();
//    }
//
//    public void setupMap() {
//        mMapView.getUiSettings().setZoomControlsEnabled(true);
//        mMapView.setTrafficEnabled(true);
//
//        /*
//         * MAP_TYPE_NONE No base map tiles. MAP_TYPE_NORMAL Basic maps.
//		 * MAP_TYPE_SATELLITE Satellite maps with no labels. MAP_TYPE_HYBRID
//		 * Satellite maps with a transparent layer of major streets.
//		 * MAP_TYPE_TERRAIN
//		 */
//        mMapView.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//
////        mMapView.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
////            @Override
////            public View getInfoWindow(Marker marker) {
////                View v = getLayoutInflater().inflate(R.layout.marker_info_content, null);
////                TextView title = (TextView) v.findViewById(R.id.tv_title);
////                if (marker.getTitle() != null && !marker.getTitle().equals("")) {
////                    title.setText(marker.getTitle());
////                    title.setVisibility(View.VISIBLE); // VISIBLE, INVISIBLE, GONE
////                } else {
////                    title.setVisibility(View.GONE);
////                }
////                LatLng latLng = marker.getPosition();
////                TextView poistionTextView = (TextView) v.findViewById(R.id.position);
////                DecimalFormat formatter = new DecimalFormat("#,###.000");
////
////                String lat = formatter.format(latLng.latitude) + "°";
////                String lng = formatter.format(latLng.longitude) + "°";
////                poistionTextView.setText(lat + "," + lng);
////
////                return v;
////            }
////
////            @Override
////            public View getInfoContents(Marker marker) {
////                return null;
////            }
////        });
//
//        mMapView.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                mMapView.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));
//                marker.showInfoWindow();
//                return true;
//            }
//        });
//
////        mMapView.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
////            @Override
////            public void onInfoWindowClick(Marker marker) {
////                Intent i = new Intent(getApplicationContext(), StreetViewActivity.class);
////                i.putExtra("lat", marker.getPosition().latitude);
////                i.putExtra("lng", marker.getPosition().longitude);
////                startActivity(i);
////            }
////        });
//
//    } // setUpMap
//
////    public void requestRunTimePermission(){
////
////        Nammu.init(getActivity());
////
////        Nammu.askForPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION, new PermissionCallback() {
////            @Override
////            public void permissionGranted() {
////                startLocationTracking();
////            }
////
////            @Override
////            public void permissionRefused() {
////                finish();
////            }
////        });
////    }
//
//    @SuppressWarnings({"MissingPermission"})
//    public void startLocationTracking() {
//
//        mApiClient = new GoogleApiClient.Builder(getActivity())
//                .addApi(LocationServices.API)
//                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
//                    @Override
//                    public void onConnected(Bundle bundle) {
//
//                        mMapView.setMyLocationEnabled(true);
//
//                        // get last location
//                        getLastLocation();
//
//                        // set request
//                        mRequest = LocationRequest.create();
//                        mRequest.setInterval(UPDATE_INTERVAL);
//                        mRequest.setFastestInterval(FASTEST_INTERVAL);
//                        mRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                        LocationServices.FusedLocationApi.requestLocationUpdates(mApiClient, mRequest, new LocationListener() {
//                            @Override
//                            public void onLocationChanged(Location location) {
//                                // animateToDefaultLocation();
//                                updateLocationTextView(location);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onConnectionSuspended(int i) {
//                       // Toast.makeText(getApplicationContext(), "Connection is susppended!", Toast.LENGTH_LONG).show();
//
//                    }
//                }).build();
//
//        mApiClient.connect();
//
//    }
//
//    @SuppressWarnings({"MissingPermission"})
//    public Location getLastLocation() {
//        Location location = LocationServices.FusedLocationApi.getLastLocation(
//                mApiClient);
//        if (location != null) {
//            updateLocationTextView(location);
//        }
//        return location;
//    }
//
//    private void updateLocationTextView(Location location) {
//        DecimalFormat formatter = new DecimalFormat("#,###.00");
//        final String lat = formatter.format(location.getLatitude());
//        final String lng = formatter.format(location.getLongitude());
//
//        mCurrentLocStr = String.format("Lat: %s°, Long: %s°", lat, lng);
//        mLocTextView.setText(mCurrentLocStr);
//    }
//
//    // A callback interface for reporting when a task is complete or cancelled.
//    GoogleMap.CancelableCallback animationTask =
//            new GoogleMap.CancelableCallback() {
//
//                @Override
//                public void onCancel() {
//                  //  Toast.makeText(getApplicationContext(), "On Cancel", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFinish() {
//
//                    if (++currentPt < listOfLatLng.size()) {
//
//                        //Get the current location
//                        Location startingLocation = new Location("starting point");
//                        startingLocation.setLatitude(mMapView.getCameraPosition().target.latitude);
//                        startingLocation.setLongitude(mMapView.getCameraPosition().target.longitude);
//
//                        //Get the target location
//                        Location endingLocation = new Location("ending point");
//                        endingLocation.setLatitude(listOfLatLng.get(currentPt).latitude);
//                        endingLocation.setLongitude(listOfLatLng.get(currentPt).longitude);
//
//                        //Find the Bearing from current location to next location
//                        float targetBearing = startingLocation.bearingTo(endingLocation);
//
//                        LatLng targetLatLng = listOfLatLng.get(currentPt);
//
//                        //Create a new CameraPosition
//                        CameraPosition cameraPosition =
//                                new CameraPosition.Builder()
//                                        .target(targetLatLng)
//                                        .bearing(targetBearing)
//                                        .zoom(mAnimationZoom)
//                                        .build();
//
//
//                        mMapView.animateCamera(
//                                CameraUpdateFactory.newCameraPosition(cameraPosition),
//                                5000,
//                                animationTask);
//
//                        // cancel when call stopAnimation
//                        // mMapView.stopAnimation();
//
//
////                        Toast.makeText(getApplicationContext(), "Animate to: " + listOfLatLng.get(currentPt) + "\n" +
////                                "Bearing: " + targetBearing, Toast.LENGTH_SHORT).show();
//
//
//                    } else {
////                        Toast.makeText(getApplicationContext(), "onFinish", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            };
//
//}
