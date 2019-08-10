//package com.salesforce.android.restsample.Tablet.Detail;
//
//import android.animation.ValueAnimator;
//import android.app.Activity;
//import android.app.Fragment;
//import android.content.Intent;
//import android.location.Location;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.CameraUpdate;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.model.BitmapDescriptor;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.LatLngBounds;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.Polygon;
//import com.salesforce.android.restsample.R;
//import com.salesforce.android.restsample.Tablet.CommunicatorV4;
//import com.salesforce.android.restsample.Tablet.Map.util.MapUtil;
//import com.salesforce.android.restsample.Tablet.widget.SegmentedButton;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import pl.tajchert.nammu.Nammu;
//
///**
// * Created by pannikar on 8/8/16 AD.
// */
//public class TabletMapForAllVisit2 extends Fragment implements OnMapReadyCallback {
//
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
////    int mPinDrawables[] = new int[]{R.drawable.pin_06,
////            R.drawable.pin_07,
////            R.drawable.pin_06,
////            R.drawable.pin_07,
////            R.drawable.pin_06};
//
////    int mPinDrawables[] = new int[]{R.drawable.pinrsz01_3,
////                                    R.drawable.pinrsz02,
////                                    R.drawable.pinrsz03,
////                                    R.drawable.pinrsz04,
////                                    R.drawable.pinrsz05};
//
//    int mPinDrawablesToday[] = new int[]{R.drawable.pincyan1,
//                                    R.drawable.pincyan2,
//                                    R.drawable.pincyan3,
//                                    R.drawable.pincyan4,
//                                    R.drawable.pincyan5};
//
//    int mPinDrawablesTomorrow[] = new int[]{R.drawable.pindp1,
//                                    R.drawable.pindp2,
//                                    R.drawable.pindp3,
//                                    R.drawable.pindp4,
//                                    R.drawable.pindp5};
//
//    int mPinDrawablesFuture[] = new int[]{R.drawable.pinlime1,
//                                    R.drawable.pinlime2,
//                                    R.drawable.pinlime3,
//                                    R.drawable.pinlime4,
//                                    R.drawable.pinlime5};
//
//    private int pinCount1 = 0;
//    private int pinCount2 = 0;
//    private int pinCount3 = 0;
//    private ImageView mClearMarkersBtn;
//    private ImageView mAnimationBtn;
//    private ImageView mGeoCodingBtn;
//    private ValueAnimator vAnimator;
//    private LatLng defaultLocation;
//    private CommunicatorV4 communicator;
//
//    private int numPinToday = 0;
//    private int numPinTomorrow = 0;
//    private int numPinFuture = 0;
//    private int numPin1 = 0;
//    private int numPin2 = 0;
//    private int numPin3 = 0;
//    private MarkerOptions markerOption;
//    private Marker mak1;
//    private Marker mak2;
//    private Marker mak3;
//
////    ArrayList<LatLng> pointsToday = new ArrayList<LatLng>();
////    ArrayList<LatLng> pointsTomorrow = new ArrayList<LatLng>();
////    ArrayList<LatLng> pointsFuture = new ArrayList<LatLng>();
////
////    ArrayList<Integer> pointsTodayStr = new ArrayList<Integer>();
////    ArrayList<Integer> pointsTomorrowStr = new ArrayList<Integer>();
////    ArrayList<Integer> pointsFutureStr = new ArrayList<Integer>();
//
//
//    /**
//     * Please Copy All Utilities
//     */
//    public LatLng convertToLatLng(Location location) {
//        return new LatLng(location.getLatitude(), location.getLongitude());
//    }
//
//
//
//
//
//    @SuppressWarnings({"MissingPermission"})
//    public Location getLastLocation() {
//        Location location = LocationServices.FusedLocationApi.getLastLocation(mApiClient);
//        if (location != null) {
//            updateLocationTextView(location);
//        }
//        return location;
//    }
//
//    // End of Utility
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        if (activity instanceof CommunicatorV4) {
//            communicator = (CommunicatorV4) activity;
//            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());
//
//        } else {
////            throw new ClassCastException(activity.toString()
////                    + " must implemenet MyListFragment.OnItemSelectedListener");
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // View view = inflater.inflate(R.layout.detail_fragment, container, false);
//
//        View v = inflater.inflate(R.layout.tablet_all_map_visit, container, false);
//        bindWidget(v);
//
////        pointsToday = (ArrayList<LatLng>) getArguments().getSerializable("ltlngToday");
////        pointsTomorrow = (ArrayList<LatLng>) getArguments().getSerializable("ltlngTomorrow");
////        pointsFuture  = (ArrayList<LatLng>) getArguments().getSerializable("ltlngFuture");
////
////        Log.e("chk","chk_Map2_Today:_size:_" + pointsToday.get(0).latitude);
////        Log.e("chk","chk_Map2_Tomorrow:_size:_" + pointsTomorrow.get(0).latitude);
////        Log.e("chk","chk_Map2_Future:_size:_" + pointsFuture.get(0).latitude);
//
//        return v;
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMapView = googleMap;
//        setupMap();
////        requestRunTimePermission();
//        startLocationTracking();
//    }
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
//                                animateToDefaultLocation();
//                                updateLocationTextView(location);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onConnectionSuspended(int i) {
//                        //Toast.makeText(getApplicationContext(), "Connection is susppended!", Toast.LENGTH_LONG).show();
//
//                    }
//                }).build();
//
//        mApiClient.connect();
//
//    }
//
//    public void animateToDefaultLocation() {
//        if (defaultLocation == null) {
//            defaultLocation = convertToLatLng(getLastLocation());
//            mMapView.animateCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 15));
//
////            placeMarker(13.76816227, 100.59597763, "Integra8t.,co.th");
//            //
////            placeMarker(13.76820135, 100.59603664, "Integra8t.,co.th");
//
////            placeMarker(13.768328999999998, 100.5964041, "Cambodian Embassy in Thailand" );
////            LatLng latLng = new LatLng(13.768328999999998, 100.5964041);
////            drawDirection(latLng);
//
////            placeMarker(13.768164, 100.597053, "ระเบียงแซ่บ" );
//
////            placeMarker(13.7644119, 100.5990535, "Golden Place" );
////            LatLng latLng2 = new LatLng(13.7644119, 100.5990535);
////            drawDirection(latLng2);
//
//            placeMarker(16.7754026223, 101.2424239331, "กล่องยาเภสัช", 1);
//            placeMarker(14.976210217, 102.097149063, "กล่องยา HQ", 1);
////            placeMarkerTomorrow(15.08721, 102.097749, "กล่องยา HQ 2" );
//
//        }
//    }
//
//
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (mMapView != null) {
//            getFragmentManager().beginTransaction()
//                    .remove(getFragmentManager().findFragmentById(R.id.mMapView))
//                    .commit();
//
//        }
//
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        Nammu.onRequestPermissionsResult(requestCode,permissions,grantResults);
//    }
//
//    public void setupMap() {
//        mMapView.getUiSettings().setZoomControlsEnabled(true);
//        //mMapView.setTrafficEnabled(true);
//        mMapView.setTrafficEnabled(false);
//
//        /*
//         * MAP_TYPE_NONE No base map tiles. MAP_TYPE_NORMAL Basic maps.
//		 * MAP_TYPE_SATELLITE Satellite maps with no labels. MAP_TYPE_HYBRID
//		 * Satellite maps with a transparent layer of major streets.
//		 * MAP_TYPE_TERRAIN
//		 */
//        //mMapView.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        mMapView.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//
//        mMapView.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//            @Override
//            public View getInfoWindow(Marker marker) {
//                View v = ((Activity) getActivity()).getLayoutInflater().inflate(R.layout.marker_info_content, null);
////                View v = inflate.inflate(R.layout.marker_info_content, null);
//                TextView title = (TextView) v.findViewById(R.id.tv_title);
//                if (marker.getTitle() != null && !marker.getTitle().equals("")) {
//                    title.setText(marker.getTitle());
//                    title.setVisibility(View.VISIBLE); // VISIBLE, INVISIBLE, GONE
//                } else {
//                    title.setVisibility(View.GONE);
//                }
//                LatLng latLng = marker.getPosition();
//                TextView poistionTextView = (TextView) v.findViewById(R.id.position);
//                DecimalFormat formatter = new DecimalFormat("#,###.000");
//
//                String lat = formatter.format(latLng.latitude) + "°";
//                String lng = formatter.format(latLng.longitude) + "°";
////                here
//                poistionTextView.setText(lat + "," + lng);
//
//                return v;
//            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//                return null;
//            }
//        });
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
//        mMapView.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                //Intent i = new Intent(getApplicationContext(), StreetViewActivity.class);
//                Intent i = new Intent(getActivity(), StreetViewActivity.class);
//                i.putExtra("lat", marker.getPosition().latitude);
//                i.putExtra("lng", marker.getPosition().longitude);
//                startActivity(i);
//            }
//        });
//
//    }
//
//    private void bindWidget(View v) {
//
//        // Try to set Map Start
//        // Create the segmented buttons
//        SegmentedButton buttons = (SegmentedButton) v.findViewById(R.id.segmented);
//        buttons.clearButtons();
////        buttons.addButtons(
////                getString(R.string.friendsactivity_btn_recent),
////                getString(R.string.friendsactivity_btn_nearby));
//
//        buttons.addButtons(getString(R.string.friendsactivity_btn_today),
//                            getString(R.string.friendsactivity_btn_tomorrow),
//                            getString(R.string.friendsactivity_btn_future));
//
//        // First button is selected
//        buttons.setPushedButtonIndex(0);
//        buttons.setPushedButtonIndex(1);
//        buttons.setPushedButtonIndex(2);
//
//        // Some example click handlers. Note the click won't get executed
//        // if the segmented button is already selected (dark blue)
//        buttons.setOnClickListener(new SegmentedButton.OnClickListenerSegmentedButton() {
//            @Override
//            public void onClick(int index) {
//
//                switch (index){
//                    case 0: numPin1++;
//                        if(numPin1 == 1){
//
//                        } else if(numPin1 == 2){
//                           // placeMarker(15.08721, 103.097749, "กล่องยา HQ 2", 2);
//                            numPin1 = 0;
//                        }
//                        break;
//
//                    case 1: numPin2++;
//                        Log.e("test", "chk:_numPin2:_" + numPin2);
//                        if(numPin2 == 1){
//                            placeMarkerTomorrow(15.08721, 103.097749, "กล่องยา HQ 2", 1);
//                        } else if(numPin2 == 2){
//                            placeMarkerTomorrow(15.08721, 103.097749, "กล่องยา HQ 2", 2);
//                            numPin2 = 0;
//                        }
//                        break;
//
//                    case 2: numPin3++;
//                        if(numPin3 == 1){
//                            placeMarkerFuture(15.08721, 100.097749, "กล่องยา HQ 3", 1);
//                        } else if(numPin3 == 2){
//                            placeMarkerFuture(15.08721, 100.097749, "กล่องยา HQ 3", 2);
//                            numPin3 = 0;
//                        }
//                        break;
//                }
//
////                if (index == 0) {
////
//////                    Toast.makeText(getActivity(), "Sort by recent", Toast.LENGTH_SHORT).show();
//////                    for(int i=0; i<pointsToday.size(); i++){
//////                        placeMarker(pointsToday.get(i).latitude, pointsToday.get(i).longitude, "Today");
//////                    }
////
////                } else if (index == 1)  {
////
////                    placeMarkerTomorrow(15.08721, 102.097749, "กล่องยา HQ 2" );
////
//////                    placeMarkerTomorrow(13.76820135, 100.59603664, "Integra8t.,co.th");
//////                    Toast.makeText(getActivity(), "Sort by nearby", Toast.LENGTH_SHORT).show();
////
//////                    for(int i=0; i<pointsTomorrow.size(); i++){
//////                        placeMarkerTomorrow(pointsTomorrow.get(i).latitude, pointsTomorrow.get(i).longitude, "Tomorrow");
//////                    }
////
//////                    for(int i=0; i<pointsToday.size(); i++){
//////                        placeMarker(pointsToday.get(i).latitude, pointsToday.get(i).longitude, "Today");
//////                    }
////
////                } else {
//////                    placeMarkerFuture(13.7644119, 100.5990535, "Golden Place" );
//////                    Toast.makeText(getActivity(), "Sort by nearby", Toast.LENGTH_SHORT).show();
//////                    for(int i=0; i<pointsFuture.size(); i++){
//////                        placeMarkerFuture(pointsFuture.get(i).latitude, pointsFuture.get(i).longitude, "Future");
//////                    }
////                }
//            }
//        });
//        // Try to set Map End
//
//        MapFragment mySupportMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mMapView);
//        mySupportMapFragment.getMapAsync(this);
//
//        mLocTextView = (TextView) v.findViewById(R.id.mLocationTextView);
//
//    }
//
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
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        MapUtil.requestLocationTrackingOn(getActivity());
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mApiClient != null) {
//            mApiClient.disconnect();
//        }
//    }
//
//    public void zoomTwoPosition(LatLng origin, LatLng dest) {
//        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//        builder.include(origin);
//        builder.include(dest);
//
//        LatLngBounds bounds = builder.build();
//        int padding = 300; // offset from edges of the map in pixels
//        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
//        mMapView.animateCamera(cu);
//    }
//
//    private void zoomAllMarkers() {
//
//        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//        for (LatLng position : listOfLatLng) {
//            builder.include(position);
//        }
//        LatLngBounds bounds = builder.build();
//        int padding = 200; // offset from edges of the map in pixels
//        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
//        mMapView.animateCamera(cu);
//
//    }
//
//    private void placeMarker(double lat, double lng, String title, int chkRemove) {
//        LatLng latLng = new LatLng(lat, lng);
//
//        MarkerOptions markerOption = new MarkerOptions();
//        markerOption.position(latLng);
//        markerOption.title(title);
//        Log.e("MainActivity4", "title: " + title.toString());
//        markerOption.icon(getDummyMarkerToday());
//
//        mMapView.addMarker(markerOption);
//        mMapView.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7)); // 15
//
////        if(chkRemove == 1){
////            LatLng latLng = new LatLng(lat, lng);
////
////            markerOption = new MarkerOptions();
////            markerOption.position(latLng);
////            markerOption.title(title);
////            Log.e("MainActivity4", "numPinTomorrow:_1:_" + numPinToday);
////
////            markerOption.icon(getDummyMarkerToday());
////
////            mak1 = mMapView.addMarker(markerOption);
////        } else if(chkRemove == 2){
////
////            numPinToday = 0;
////            pinCount1 = 0;
////
////            Log.e("MainActivity4", "numPinTomorrow:_3:_" + numPinToday);
////
////            mak1.remove();
////        }
//
//    }
//
//    private void placeMarkerTomorrow(double lat, double lng, String title, int chkRemove) {
//
//        if(chkRemove == 1){
//            LatLng latLng = new LatLng(lat, lng);
//
//            markerOption = new MarkerOptions();
//            markerOption.position(latLng);
//            markerOption.title(title);
//            Log.e("MainActivity4", "numPinTomorrow:_1:_" + numPinTomorrow);
//
//            markerOption.icon(getDummyMarkerTomorrow());
//
//            mak2 = mMapView.addMarker(markerOption);
//        } else if(chkRemove == 2){
//
//            numPinTomorrow = 0;
//            pinCount2 = 0;
//
//            Log.e("MainActivity4", "numPinTomorrow:_3:_" + numPinTomorrow);
//
//            mak2.remove();
//        }
//    }
//
//    private void placeMarkerFuture(double lat, double lng, String title, int chkRemove) {
//        if(chkRemove == 1){
//            LatLng latLng = new LatLng(lat, lng);
//
//            markerOption = new MarkerOptions();
//            markerOption.position(latLng);
//            markerOption.title(title);
//            Log.e("MainActivity4", "numPinFuture:_1:_" + numPinFuture);
//
//            markerOption.icon(getDummyMarkerFuture());
//
//            mak3 = mMapView.addMarker(markerOption);
//        } else if(chkRemove == 2){
//
//            numPinTomorrow = 0;
//            pinCount3 = 0;
//
//            Log.e("MainActivity4", "numPinFuture:_2:_" + numPinFuture);
//
//            mak3.remove();
//
//        }
//    }
//
//
//
//
//    public BitmapDescriptor getDummyMarkerToday() {
//
//        int random = pinCount1++ % 7;
//        return BitmapDescriptorFactory.fromResource(mPinDrawablesToday[random]);
//    }
//
//    public BitmapDescriptor getDummyMarkerTomorrow() {
//
//        int random = pinCount2++ % 7;
//        return BitmapDescriptorFactory.fromResource(mPinDrawablesTomorrow[random]);
//    }
//
//    public BitmapDescriptor getDummyMarkerFuture() {
//
//        int random = pinCount3++ % 7;
//        return BitmapDescriptorFactory.fromResource(mPinDrawablesFuture[random]);
//    }
//
//    // A callback interface for reporting when a task is complete or cancelled.
//    GoogleMap.CancelableCallback animationTask =
//            new GoogleMap.CancelableCallback() {
//
//                @Override
//                public void onCancel() {
//                    Toast.makeText(getActivity(), "On Cancel", Toast.LENGTH_SHORT).show();
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
//                        Toast.makeText(getActivity(), "Animate to: " + listOfLatLng.get(currentPt) + "\n" +
//                                "Bearing: " + targetBearing, Toast.LENGTH_SHORT).show();
//
//
//                    } else {
//                        Toast.makeText(getActivity(), "onFinish", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            };
//
//}
