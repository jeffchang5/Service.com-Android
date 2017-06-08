package jeffreychang.xyz.servicecom_android_challenge.ui.users;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.PendingResult;
import com.google.maps.model.GeocodingResult;
import com.squareup.picasso.Picasso;

import jeffreychang.xyz.servicecom_android_challenge.BuildConfig;
import jeffreychang.xyz.servicecom_android_challenge.MainActivity;
import jeffreychang.xyz.servicecom_android_challenge.R;
import jeffreychang.xyz.servicecom_android_challenge.models.User;
import jeffreychang.xyz.servicecom_android_challenge.ui.common.BaseFragment;

/**
 * Created by chang on 6/7/2017.
 */

public class DetailFragment extends BaseFragment implements PendingResult.Callback<GeocodingResult[]>, Callback, OnMapReadyCallback, View.OnClickListener {

    private static final int PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private MapView mMapView;
    private GoogleMap mMap;
    private com.google.android.gms.maps.model.LatLng mLatLong;
    private String mPhoneNumber;
    private String mEmail;

    public DetailFragment() {
    }


    public static DetailFragment newInstance(User user) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();

        args.putString(User.NAME, user.getName());
        args.putString(User.EMAIL, user.getEmail());
        args.putString(User.ADDRESS, user.getAddress().toString());
        args.putString(User.PHONE, user.getPhone());
        args.putString(User.ROLE, user.getRole());
        args.putString(User.PROFILE_PIC, user.getProfilePic());
        detailFragment.setArguments(args);

        return detailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        
        Bundle bundle = getArguments();
        
        
        String address = bundle.getString(User.ADDRESS);
        mEmail = bundle.getString(User.EMAIL);
        mMapView = (MapView) view.findViewById(R.id.user_map_view);
        GeoApiContext context = new GeoApiContext().setApiKey(BuildConfig.GOOGLE_GEOENCODING_API_KEY);
        GeocodingApiRequest req = GeocodingApi.newRequest(context).address(address);
        req.setCallback(this);

        mMapView.onCreate(savedInstanceState);

        String name = bundle.getString(User.NAME);
        Button callButton = (Button) view.findViewById(R.id.call_button);
        Button emailButton = (Button) view.findViewById(R.id.email_button);

        callButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);

        ImageView profileImageView = (ImageView) view.findViewById(R.id.profileImageView);
        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView roleTextView = (TextView) view.findViewById(R.id.roleTextView);
        TextView emailTextView = (TextView) view.findViewById(R.id.emailTextView);
        TextView phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
        TextView address1TextView = (TextView) view.findViewById(R.id.address1TextView);
        TextView address2TextView = (TextView) view.findViewById(R.id.address2TextView);
        FrameLayout topColorView = (FrameLayout) view.findViewById(R.id.frameLayout);




        mPhoneNumber = bundle.getString(User.PHONE);

        nameTextView.setText(name);
        emailTextView.setText(mEmail);
        String role = getArguments().getString(User.ROLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            phoneTextView.setText(PhoneNumberUtils.formatNumber(mPhoneNumber, "US"));
        } else {
            phoneTextView.setText(PhoneNumberUtils.formatNumber(mPhoneNumber));
        }
        if (address != null) {
            String[] splitAddress = address.split(",");
            address1TextView.setText(splitAddress[0].concat(","));
            address2TextView.setText(splitAddress[1].substring(1));
        }
        if (role != null) {
            if (role.equals("consumer")) {
                int pink = ContextCompat.getColor(getContext(), R.color.pink_400);
                ((MainActivity) getActivity()).changeColorTheme(pink);
                emailButton.setTextColor(pink);
                callButton.setTextColor(pink);
                topColorView.setBackgroundColor(pink);
            } else {
                int yellow = ContextCompat.getColor(getContext(), R.color.material_yellow);
                ((MainActivity) getActivity()).changeColorTheme(yellow);
                emailButton.setTextColor(yellow);
                callButton.setTextColor(yellow);
                topColorView.setBackgroundColor(yellow);
            }
        }
        if (role != null) {
            roleTextView.setText(String.format("%s%s", role.substring(0, 1).toUpperCase(), role.substring(1)));
        }

        Picasso.with(getContext())
                .load(bundle.getString(User.PROFILE_PIC))
                .placeholder(R.color.colorAccent)
                .into(profileImageView);

        return view;
    }


    @Override
    public void onResult(final GeocodingResult[] result) {
        mLatLong = new com.google.android.gms.maps.model.LatLng(result[0].geometry.location.lat, result[0].geometry.location.lng);
        getActivity().runOnUiThread(new RunOnUIThread(result[0].geometry.location.lat, result[0].geometry.location.lng, this));
    }

    @Override
    public void onFailure(final Throwable e) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void callback(double lat, double lng) {
        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLong, 15));
        UiSettings mapUiSettings = mMap.getUiSettings();
        mapUiSettings.setZoomControlsEnabled(false);
        mapUiSettings.setMapToolbarEnabled(false);
        mapUiSettings.setAllGesturesEnabled(false);
        mMapView.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_button: {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Work");
                i.putExtra(Intent.EXTRA_TEXT   , "I have a request.");
                startActivity(Intent.createChooser(i, "Send mail"));
                break;

            }
            case R.id.call_button: {
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            PERMISSIONS_REQUEST_CALL_PHONE);

                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPhoneNumber));
                        startActivity(intent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }

    public class RunOnUIThread implements Runnable {
        private double longitude;
        private double latitude;
        Callback callback;


        RunOnUIThread(Double longitude, Double latitude, Callback callback) {
            this.longitude = longitude;
            this.latitude = latitude;
            this.callback = callback;
        }

        @Override
        public void run() {
            callback.callback(latitude, longitude);
        }
    }
}
