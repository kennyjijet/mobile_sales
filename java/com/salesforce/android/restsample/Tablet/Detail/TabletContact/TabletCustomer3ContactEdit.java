package com.salesforce.android.restsample.Tablet.Detail.TabletContact;

/**
 * Created by pannikar on 8/25/16 AD.
 */
//public class TabletCustomer3ContactEdit {
//}


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBContact.Contact;
import com.salesforce.android.restsample.DB.Model.DBContact.ContactDatabaseHelper;
import com.salesforce.android.restsample.Library.GetDialogAlert;
import com.salesforce.android.restsample.MainCUSTOMERs.ShrPrefCustomerContact;
import com.salesforce.android.restsample.R;

import java.util.List;

/**
 * Created by pannikar on 4/7/2016 AD.
 */
public class TabletCustomer3ContactEdit extends Fragment implements View.OnClickListener {
    private Communicator communicator;
    String newName;
    String newId;
    String newNumber;
    String idContact;

    String newShowName;
    ContactDatabaseHelper mDBHelper3Cont;
    List<Contact> getContact;

    TextView tv05b4;
    boolean isOwn = false;
    int isDecis = 0;

    SharedPreferences sharedpreferences;
    ShrPrefCustomerContact shrPref;
    public static final String preferenceName = "prefHBDContacts";
    EditText edFirstName;
    EditText edLastName;
    private EditText edTitle;
    private EditText edPosition;
    private EditText edBirthday;
    private EditText edMobile;
    private EditText edEmail;
    private EditText edLineID;
    private EditText edFavFood;
    private EditText edNonFavFood;
    private EditText edFavDrink;
    private EditText edNonFavDrink;
    private EditText edFavAct;
    private EditText edNonFavAct;
    private String firstName;
    private String lastName;
    private String title;
    private String position;
    private String birthday;
    private String mobileNum;
    private String email;
    private String lineId;
    private String favFood;
    private String nonFavFood;
    private String favDrink;
    private String nonFavDrink;
    private String favAct;
    private String nonFavAct;

    private ImageView check1;
    private ImageView check2;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof Communicator) {
            communicator = (Communicator) activity;
            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());

        } else {
//            throw new ClassCastException(activity.toString()
//                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //        setContentView(R.layout.customer_new_contact_edit);

        View v = inflater.inflate(R.layout.detail_customer_new_contact_edit, container, false);


        sharedpreferences = getActivity().getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        shrPref = new ShrPrefCustomerContact(sharedpreferences, getActivity());

//        Bundle extras = getIntent().getExtras();
        idContact = getArguments().getString("getIdContact");
        newName= getArguments().getString("getName");//
        newId= getArguments().getString("getId");
        newNumber = getArguments().getString("getNumber");

        Log.e("Customer3ContactEdit", "Customer3ContactEdit_idContact: " + idContact);

        if (getArguments().getString("getShowName") == null || getArguments().getString("getShowName") == ""){
            newShowName = "";
        }else {
            newShowName = getArguments().getString("getShowName");
        }

        mDBHelper3Cont = new ContactDatabaseHelper(getActivity());
        getContact =  mDBHelper3Cont.getListContactSearchById2(idContact);


        LinearLayout lnRelation = (LinearLayout) v.findViewById(R.id.lnRelation);
        LinearLayout lnisOwn = (LinearLayout) v.findViewById(R.id.lnisOwn);
        LinearLayout lnisDecis = (LinearLayout) v.findViewById(R.id.lnisDecis);

        TextView tt01 = (TextView) v.findViewById(R.id.title01);
        TextView tt01s = (TextView) v.findViewById(R.id.title01s);
        TextView tt02 = (TextView) v.findViewById(R.id.title02);
        TextView tt02s = (TextView) v.findViewById(R.id.title02s);
        TextView tt03 = (TextView) v.findViewById(R.id.title03);
        TextView tt03s = (TextView) v.findViewById(R.id.title03s);
        TextView tt04 = (TextView) v.findViewById(R.id.title04);
        TextView tt04s = (TextView) v.findViewById(R.id.title04s);
        TextView tt04af = (TextView) v.findViewById(R.id.title04af);
        TextView tt04after = (TextView) v.findViewById(R.id.title04after);
        TextView tt05b4 = (TextView) v.findViewById(R.id.title05b4);
        tv05b4 = (TextView) v.findViewById(R.id.tv05b4);
        TextView tt05 = (TextView) v.findViewById(R.id.title05);
        TextView tt06 = (TextView) v.findViewById(R.id.title06);
        TextView tt07 = (TextView) v.findViewById(R.id.title07);
        TextView tt08 = (TextView) v.findViewById(R.id.title08);
        TextView tt09 = (TextView) v.findViewById(R.id.title09);
        TextView tt10 = (TextView) v.findViewById(R.id.title10);
        TextView tt11 = (TextView) v.findViewById(R.id.title11);
        TextView tt12 = (TextView) v.findViewById(R.id.title12);
        TextView tt13 = (TextView) v.findViewById(R.id.title13);
        TextView tt14 = (TextView) v.findViewById(R.id.title14);
        TextView ttFood = (TextView) v.findViewById(R.id.titleFood);
        TextView ttDrink = (TextView) v.findViewById(R.id.titleDrink);
        TextView ttActivities = (TextView) v.findViewById(R.id.titleActivities);

        TextView tv0Done = (TextView) v.findViewById(R.id.tvDone);
        TextView tv0Cancel = (TextView) v.findViewById(R.id.tvCancel);

        check1 = (ImageView) v.findViewById(R.id.check1);
        check2 = (ImageView) v.findViewById(R.id.check2);

//        check1.setVisibility(View.GONE);
//        check2.setVisibility(View.GONE);

        edFirstName = (EditText) v.findViewById(R.id.edFirstName);
        edLastName = (EditText) v.findViewById(R.id.edLastName);
        edTitle = (EditText) v.findViewById(R.id.edTitle);
        edPosition = (EditText) v.findViewById(R.id.edPosition);
        edBirthday = (EditText) v.findViewById(R.id.edBirthday);
        edMobile = (EditText) v.findViewById(R.id.edMobile);
        edEmail = (EditText) v.findViewById(R.id.edEmail);
        edLineID = (EditText) v.findViewById(R.id.edLineID);
        edFavFood = (EditText) v.findViewById(R.id.edFavFood);
        edNonFavFood = (EditText) v.findViewById(R.id.edNonFavFood);
        edFavDrink = (EditText) v.findViewById(R.id.edFavDrink);
        edNonFavDrink = (EditText) v.findViewById(R.id.edNonFavDrink);
        edFavAct = (EditText) v.findViewById(R.id.edFavAct);
        edNonFavAct = (EditText) v.findViewById(R.id.edNonFavAct);

        // for this Activity
        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");


        tt01.setTypeface(fontThSarabunBold);
        tt01s.setTypeface(fontThSarabunBold);
        tt02.setTypeface(fontThSarabunBold);
        tt02s.setTypeface(fontThSarabunBold);
        tt03.setTypeface(fontThSarabunBold);
        tt03s.setTypeface(fontThSarabunBold);
        tt04.setTypeface(fontThSarabunBold);
        tt04s.setTypeface(fontThSarabunBold);
        tt04af.setTypeface(fontThSarabunBold);
        tt04after.setTypeface(fontThSarabunBold);
        tt05b4.setTypeface(fontThSarabunBold);
        tv05b4.setTypeface(fontThSarabun);
        tt05.setTypeface(fontThSarabunBold);
        tt06.setTypeface(fontThSarabunBold);
        tt07.setTypeface(fontThSarabunBold);
        tt08.setTypeface(fontThSarabunBold);
        tt09.setTypeface(fontThSarabunBold);
        tt10.setTypeface(fontThSarabunBold);
        tt11.setTypeface(fontThSarabunBold);
        tt12.setTypeface(fontThSarabunBold);
        tt13.setTypeface(fontThSarabunBold);
        tt14.setTypeface(fontThSarabunBold);
        ttFood.setTypeface(fontThSarabun);
        ttDrink.setTypeface(fontThSarabun);
        ttActivities.setTypeface(fontThSarabun);


        tv0Done.setTypeface(fontThSarabun);
        tv0Cancel.setTypeface(fontThSarabun);

        edFirstName.setTypeface(fontThSarabun);
        edLastName.setTypeface(fontThSarabun);
        edTitle.setTypeface(fontThSarabun);
        edPosition.setTypeface(fontThSarabun);
        edBirthday.setTypeface(fontThSarabun);
        edMobile.setTypeface(fontThSarabun);
        edEmail.setTypeface(fontThSarabun);
        edLineID.setTypeface(fontThSarabun);
        edFavFood.setTypeface(fontThSarabun);
        edNonFavFood.setTypeface(fontThSarabun);
        edFavDrink.setTypeface(fontThSarabun);
        edNonFavDrink.setTypeface(fontThSarabun);
        edFavAct.setTypeface(fontThSarabun);
        edNonFavAct.setTypeface(fontThSarabun);

        if (!newShowName.equals("")){
            tv05b4.setText(newShowName);
            tv05b4.setTextColor(this.getResources().getColor(R.color.colorOrdiary));
        }

        if(getContact.size()!=0){
            edFirstName.setText(getContact.get(0).getFirstname());
            edLastName.setText(getContact.get(0).getLastname());
            edTitle.setText(getContact.get(0).getTitle());
            edPosition.setText(getContact.get(0).getPosition());
            if (tv05b4.equals(null)){
                tv05b4.setText("Relation...");
                tv05b4.setTextColor(this.getResources().getColor(R.color.colorHeavyGrey));
            }else{
                tv05b4.setText(getContact.get(0).getRelateOwner());
                tv05b4.setTextColor(this.getResources().getColor(R.color.colorOrdiary));
            }

            Log.e("checkOwn","Own_"+getContact.get(0).isOwner());
            if (getContact.get(0).isOwner() == true){
                check1.setVisibility(View.VISIBLE);
                isOwn = true;
            }else {
                check1.setVisibility(View.GONE);
            }
            if (getContact.get(0).getIsDecismaker() == 1){
                check2.setVisibility(View.VISIBLE);
                isDecis = 1;
            }else {
                check2.setVisibility(View.GONE);
            }
            edBirthday.setText(getContact.get(0).getBirthday());
            edMobile.setText(getContact.get(0).getMobile());
            edEmail.setText(getContact.get(0).getEmail());
            edLineID.setText(getContact.get(0).getLineId());
            edFavFood.setText(getContact.get(0).getFavFood());
            edNonFavFood.setText(getContact.get(0).getNonFavFood());
            edFavDrink.setText(getContact.get(0).getFavDrink());
            edNonFavDrink.setText(getContact.get(0).getNonFavFood());
            edFavAct.setText(getContact.get(0).getFavAct());
            edNonFavAct.setText(getContact.get(0).getNonFavAct());
        } else {
            edFirstName.setHint(" null ");
            edLastName.setHint(" null ");
            edTitle.setHint(" null ");
            edPosition.setHint(" null ");
            tv05b4.setHint("Relation...");
            check1.setVisibility(View.GONE);
            check2.setVisibility(View.GONE);
            edBirthday.setHint(" null ");
            edMobile.setHint(" null ");
            edEmail.setHint(" null ");
            edLineID.setHint(" null ");
            edFavFood.setHint(" null ");
            edNonFavFood.setHint(" null ");
            edFavDrink.setHint(" null ");
            edNonFavDrink.setHint(" null ");
            edFavAct.setHint(" null ");
            edNonFavAct.setHint(" null ");
        }

        LinearLayout lnLeft = (LinearLayout) v.findViewById(R.id.LnLeft);
        lnLeft.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

                showDialog();
            }
        });

        LinearLayout lnRight = (LinearLayout) v.findViewById(R.id.LnRight);
        lnRight.setOnClickListener(this);



        tv0Cancel.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {

                showDialog();
            }
        });

        tv0Done.setOnClickListener(this);

        lnRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent i = new Intent(Customer3ContactEdit.this, PickListSingleItem.class);
//
//                i.putExtra("getIdContact",idContact);
//                i.putExtra("getId", newId);
//                i.putExtra("getName", newName);
//                i.putExtra("getNumber", newNumber);
//                i.putExtra("getType", 2);
//                i.putExtra("CheckPickList","Relation");
//                i.putExtra("CheckFrgmtParent","CCEdit");
//                i.putExtra("getShowName",newShowName);
//                startActivityForResult(i, 20);
            }
        });

        lnRelation.setOnClickListener(this);

        lnisOwn.setOnClickListener(new View.OnClickListener() {
            boolean isClickOwn = false;
            @Override
            public void onClick(View view) {
                if (isClickOwn){
                    isClickOwn = !isClickOwn;
                    isOwn = false;
                    check1.setVisibility(View.GONE);
                }else {
                    isClickOwn = !isClickOwn;
                    isOwn = true;
                    check1.setVisibility(View.VISIBLE);
                }
                Log.e("testClickOwn","click_"+isClickOwn+"_"+isOwn);
            }
        });

        lnisDecis.setOnClickListener(new View.OnClickListener() {
            boolean isClickDecis = false;
            @Override
            public void onClick(View view) {
                if (isClickDecis){
                    isClickDecis = !isClickDecis;
                    isDecis = 0;
                    check2.setVisibility(View.GONE);
                }else {
                    isClickDecis = !isClickDecis;
                    isDecis = 1;
                    check2.setVisibility(View.VISIBLE);
                }
                Log.e("testClickDecis","click_"+isClickDecis+"_"+isDecis);
            }
        });

        // Original
        getActivity().setTitle(null);
        Toolbar topToolBar = (Toolbar) v.findViewById(R.id.toolbar);
        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
//        setSupportActionBar(topToolBar);
        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));

        mTitle.setTypeface(fontThSarabunBold);
        mTitle.setText("Edit Contact");

        return v;
    }

    void showDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("contact");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        String inputText = "Do you want to discard?";

        GetDialogAlert newFragment = GetDialogAlert.newInstance(inputText,"contact",idContact,
                newName, newId, newNumber);
        newFragment.show(ft, "contact");

    }

    void setNameAtTextView(String setTextView){
        if (!(setTextView.equals(""))){
            tv05b4.setText(setTextView);
            tv05b4.setTextColor(this.getResources().getColor(R.color.colorOrdiary));
        }
    }

    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 20:
                if (resultCode == Activity.RESULT_OK) {

                    firstName = shrPref.getFirstName();
                    lastName =  shrPref.getLastName();
                    title = shrPref.getTitle();
                    position = shrPref.getPosition();
                    birthday = shrPref.getBirthDay();
                    mobileNum = shrPref.getMobileNum();
                    email = shrPref.getEmail();
                    lineId = shrPref.getLineId();
                    favFood = shrPref.getFavFood();
                    nonFavFood = shrPref.getNonFavFood();
                    favDrink = shrPref.getFavDrink();
                    nonFavDrink = shrPref.getNonFavDrink();
                    favAct = shrPref.getFavAct();
                    nonFavAct = shrPref.getNonFavAct();

                    Log.e("shownameInContact","chk_share:_firstName:_"+firstName);
                    edFirstName.setText(firstName);
                    edLastName.setText(lastName);
                    edTitle.setText(title);
                    edPosition.setText(position);
                    edBirthday.setText(birthday);
                    edMobile.setText(mobileNum);
                    edEmail.setText(email);
                    edLineID.setText(lineId);
                    edFavFood.setText(favFood);
                    edNonFavFood.setText(nonFavFood);
                    edFavDrink.setText(favDrink);
                    edNonFavDrink.setText(nonFavDrink);
                    edFavAct.setText(favAct);
                    edNonFavAct.setText(nonFavAct);

                    String result = data.getStringExtra("result");
                    newShowName = result;
                    Log.e("shownameInContact","hello_"+newShowName);
                    setNameAtTextView(result);

                }
                break;
        }
    }

    public interface Communicator {

        public void Message2EditContactsCancel(String idContact, String get2Id,String  get0name,String  get1number);
        public void Message2EditContactsDone(String idContact, String get2Id,String  get0name,String  get1number);
        public void Message2EditContacts(String idContact, String get2Id,String  get0name,String  get1number,
                                            int type, String CheckPickList, String CheckFrgmtParent, String ShowName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LnLeft:
                communicator.Message2EditContactsCancel(idContact,newId,newName,newNumber);
                break;

            case R.id.tvCancel:
                communicator.Message2EditContactsCancel(idContact,newId,newName,newNumber);
                break;

            case R.id.LnRight:
                updateFragmentLnRight();
                communicator.Message2EditContactsDone(idContact, newId, newName, newNumber);
                break;

            case R.id.tvDone:
                updateFragmenttv0Done();
                communicator.Message2EditContactsDone(idContact, newId, newName, newNumber);
                break;

            case R.id.lnRelation:
                updateFragmentLnRelation();
                communicator.Message2EditContacts(idContact, newId, newName, newNumber,
                                                2, "Relation","CCEdit",newShowName);
                break;
        }
    }

    private void updateFragmentLnRelation() {
        shrPref.Save(edFirstName.getText().toString(),
                edLastName.getText().toString(),
                edTitle.getText().toString(),
                edPosition.getText().toString(),
                edBirthday.getText().toString(),
                edMobile.getText().toString(),
                edEmail.getText().toString(),
                edLineID.getText().toString(),
                edFavFood.getText().toString(),
                edNonFavFood.getText().toString(),
                edFavDrink.getText().toString(),
                edNonFavDrink.getText().toString(),
                edFavAct.getText().toString(),
                edNonFavAct.getText().toString());
    }

    private void updateFragmenttv0Done() {
        mDBHelper3Cont.updateContact(idContact,
                edFirstName.getText().toString(),
                edLastName.getText().toString(),
                edPosition.getText().toString(),
                edTitle.getText().toString(),
                edBirthday.getText().toString(),
                edEmail.getText().toString(),
                edLineID.getText().toString(),
                edMobile.getText().toString(),
                edFavDrink.getText().toString(),
                edFavFood.getText().toString(),
                edFavAct.getText().toString(),
                edNonFavDrink.getText().toString(),
                edNonFavFood.getText().toString(),
                edNonFavAct.getText().toString(),
                getContact.get(0).getImage(),
                getContact.get(0).getType(),
                isOwn,
                isDecis,
                tv05b4.getText().toString(),
                false,
                false,
                getContact.get(0).getExternalId());
    }


    private void updateFragmentLnRight() {
        mDBHelper3Cont.updateContact(idContact,
                edFirstName.getText().toString(),
                edLastName.getText().toString(),
                edPosition.getText().toString(),
                edTitle.getText().toString(),
                edBirthday.getText().toString(),
                edEmail.getText().toString(),
                edLineID.getText().toString(),
                edMobile.getText().toString(),
                edFavDrink.getText().toString(),
                edFavFood.getText().toString(),
                edFavAct.getText().toString(),
                edNonFavDrink.getText().toString(),
                edNonFavFood.getText().toString(),
                edNonFavAct.getText().toString(),
                getContact.get(0).getImage(),
                getContact.get(0).getType(),
                isOwn,
                isDecis,
                tv05b4.getText().toString(),
                true,
                false,
                getContact.get(0).getExternalId());
    }


}