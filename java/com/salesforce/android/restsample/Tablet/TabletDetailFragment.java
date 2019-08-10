package com.salesforce.android.restsample.Tablet;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salesforce.android.restsample.R;

/**
 * Created by pannikar on 7/12/16 AD.
 */
public class TabletDetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View view = inflater.inflate(R.layout.detail_fragment, container, false);
        View view = inflater.inflate(R.layout.tablet_detail_fragment, container, false);
        return view;
    }

    // we call this method when button from listfragment is clicked
    public void setTextDetailFragment(String item) {
        TextView view = (TextView) getView().findViewById(R.id.display_tv);
        view.setText(item);
    }
}
