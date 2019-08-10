package com.salesforce.android.restsample.Tablet.Expand;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.salesforce.android.restsample.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<String> _listDataHeader; // header titles
	List<String> _listDataHeaderNumber;
	List<String> _listDataHeaderAddress;
	// child data in format of header title, child title
	private HashMap<String, List<String>> _listDataChild;
	private Typeface fontThSarabun, fontThSarabunBold;

	public ExpandableListAdapter(Context context,
								 List<String> listDataHeaderNumber,
								 // List<String> listDataHeaderAddress,
								 List<String> listDataHeader,
								 HashMap<String, List<String>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
		this._listDataHeaderNumber = listDataHeaderNumber;
		// this._listDataHeaderAddress = listDataHeaderAddress;

		fontThSarabun = Typeface.createFromAsset(context.getAssets(), "fonts/THSarabun.ttf");
		fontThSarabunBold = Typeface.createFromAsset(context.getAssets(), "fonts/THSarabunBold.ttf");
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}


	/* item */
	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = (String) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.exp_list_item, null);
		}

		TextView txtListChild = (TextView) convertView .findViewById(R.id.lblListItem);
		txtListChild.setText(childText);
		txtListChild.setTypeface(fontThSarabun);

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		String headerNumber = _listDataHeaderNumber.get(groupPosition).toString();
		// String headerAddress = _listDataHeaderAddress.get(groupPosition).toString();
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.exp_list_group, null);
		}

		TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);
		lblListHeader.setTypeface(fontThSarabunBold);

		//lblListHeaderAddress (Name = Address)
//		TextView lblListHeaderAddress = (TextView) convertView.findViewById(R.id.lblListHeaderAddress);
//		lblListHeaderAddress.setText(headerAddress);
//		lblListHeaderAddress.setTypeface(fontThSarabun);

		/* set Number Header */
		TextView lblListHeaderNumber = (TextView) convertView.findViewById(R.id.lblListHeaderNumber);
		lblListHeaderNumber.setTypeface(null, Typeface.BOLD);
		lblListHeaderNumber.setText(headerNumber);
		lblListHeaderNumber.setTypeface(fontThSarabunBold);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
