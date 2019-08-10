package com.salesforce.android.restsample.Tablet;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesforce.android.restsample.R;

/**
 * Created by pannikar on 7/12/16 AD.
 */
public class TabletDetailActivity extends Activity {

    public static String os_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        // set Vertical
        setContentView(R.layout.tablet_detail_activity);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString(os_name);
            TabletDetailFragment detailFragment = (TabletDetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
            detailFragment.setTextDetailFragment(name);
        }
    }
}
