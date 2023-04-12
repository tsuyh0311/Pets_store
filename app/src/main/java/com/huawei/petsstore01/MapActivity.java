package com.huawei.petsstore01;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapView;
import com.huawei.hms.maps.MapsInitializer;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.model.BitmapDescriptorFactory;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hms.maps.model.MarkerOptions;
import com.huawei.hms.navi.navibase.MapNavi;
import com.huawei.hms.navi.navibase.MapNaviListener;
import com.huawei.hms.navi.navibase.enums.MapNaviRoutingTip;
import com.huawei.hms.navi.navibase.enums.MapNaviSettingEnums;
import com.huawei.hms.navi.navibase.enums.VehicleType;
import com.huawei.hms.navi.navibase.model.DevServerSiteConstant;
import com.huawei.hms.navi.navibase.model.FurnitureInfo;
import com.huawei.hms.navi.navibase.model.FutureEta;
import com.huawei.hms.navi.navibase.model.HighwayExitInfo;
import com.huawei.hms.navi.navibase.model.HistoryTrafficInfo;
import com.huawei.hms.navi.navibase.model.Incident;
import com.huawei.hms.navi.navibase.model.IntersectionNotice;
import com.huawei.hms.navi.navibase.model.JamBubble;
import com.huawei.hms.navi.navibase.model.LaneInfo;
import com.huawei.hms.navi.navibase.model.MapModelCross;
import com.huawei.hms.navi.navibase.model.MapNaviStaticInfo;
import com.huawei.hms.navi.navibase.model.MapNaviTurnPoint;
import com.huawei.hms.navi.navibase.model.MapServiceAreaInfo;
import com.huawei.hms.navi.navibase.model.NaviBroadInfo;
import com.huawei.hms.navi.navibase.model.NaviInfo;
import com.huawei.hms.navi.navibase.model.NaviRequestPoint;
import com.huawei.hms.navi.navibase.model.NaviStrategy;
import com.huawei.hms.navi.navibase.model.RouteChangeInfo;
import com.huawei.hms.navi.navibase.model.RouteRecommendInfo;
import com.huawei.hms.navi.navibase.model.RoutingRequestParam;
import com.huawei.hms.navi.navibase.model.SpeedInfo;
import com.huawei.hms.navi.navibase.model.TriggerNotice;
import com.huawei.hms.navi.navibase.model.TurnPointInfo;
import com.huawei.hms.navi.navibase.model.TypeOfTTSInfo;
import com.huawei.hms.navi.navibase.model.VoiceFailedResult;
import com.huawei.hms.navi.navibase.model.VoiceResult;
import com.huawei.hms.navi.navibase.model.ZoomPoint;
import com.huawei.hms.navi.navibase.model.bus.BusNaviPathBean;
import com.huawei.hms.navi.navibase.model.currenttimebusinfo.CurrentBusInfo;
import com.huawei.hms.navi.navibase.model.locationstruct.NaviLatLng;
import com.huawei.hms.navi.navibase.model.locationstruct.NaviLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {



    private Marker mMarker;
    MapView mMapView;
    HuaweiMap hMap ;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private static final LatLng ORSAY = new LatLng(30.90, 107.78);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_page);

        // 初始化SDK
        MapsInitializer.initialize(this);
        mMapView = findViewById(R.id.mapview_mapviewdemo);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle("MapViewBundleKey");
        }
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);


    }


    public void onMapReady(HuaweiMap huaweiMap) {
        Log.d(TAG, "onMapReady: ");
        hMap = huaweiMap;
        hMap.setMapType(HuaweiMap.MAP_TYPE_NORMAL);
        hMap.setMyLocationEnabled(true);
        hMap.getUiSettings().setMyLocationButtonEnabled(true);
        hMap.addMarker(new MarkerOptions().position(ORSAY)
                .alpha(0.5f)
                .title("好养犬舍")
                .snippet("欢迎光临！")
                .clusterable(true));

    }
    public void addMarker(View view) {
        if (null != mMarker) {
            mMarker.remove();
        }
        MarkerOptions options = new MarkerOptions()
                .position(new LatLng(106.782562, 29.898888))
                .title("Hello Huawei Map")
                .snippet("This is a snippet!");
        mMarker = hMap.addMarker(options);

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}