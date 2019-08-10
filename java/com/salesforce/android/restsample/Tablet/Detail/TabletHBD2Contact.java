package com.salesforce.android.restsample.Tablet.Detail;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salesforce.android.restsample.DB.Model.DBContact.Contact;
import com.salesforce.android.restsample.DB.Model.DBContact.ContactDatabaseHelper;
import com.salesforce.android.restsample.R;
import com.salesforce.android.restsample.Tablet.CommunicatorV4;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by pannikar on 7/25/16 AD.
 */
public class TabletHBD2Contact extends Fragment implements View.OnClickListener {
    private CommunicatorV4 communicatorV4;

    String idContact;

    ContactDatabaseHelper mDBHelper3Cont;
    List<Contact> getContact;
    String parentId;

    private ImageView downloadedImg;
    String imageId;
    String downloadUrl = "";

    private ProgressDialog simpleWaitDialog;
    boolean chckImg = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CommunicatorV4) {
            communicatorV4 = (CommunicatorV4) activity;
            Log.e("MyListFragment","Row101_activity.toString():_" + activity.toString());

        } else {
//            throw new ClassCastException(activity.toString()
//                    + " must implemenet MyListFragment.OnItemSelectedListener");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detail_contact_detail_edit2, container, false);


//        setContentView(R.layout.contact_detail_edit2);

        // for this Activity
        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        downloadedImg = (ImageView) v.findViewById(R.id.imgURL);


        idContact = getArguments().getString("getIdContact");

        mDBHelper3Cont = new ContactDatabaseHelper(getActivity());
//        getContact =  mDBHelper3Cont.getListContactSearchById(newId);

        getContact =  mDBHelper3Cont.getListContactSearchById2(idContact);

        TextView tt01 = (TextView) v.findViewById(R.id.title01);
        TextView tt02 = (TextView) v.findViewById(R.id.title02);
        TextView tt03 = (TextView) v.findViewById(R.id.title03);
        TextView tt04 = (TextView) v.findViewById(R.id.title04);
        TextView tt05 = (TextView) v.findViewById(R.id.title05);
        TextView tt06 = (TextView) v.findViewById(R.id.title06);
        TextView tt07 = (TextView) v.findViewById(R.id.title07);
        TextView tt08 = (TextView) v.findViewById(R.id.title08);
        TextView tt09 = (TextView) v.findViewById(R.id.title09);
        TextView tt10 = (TextView) v.findViewById(R.id.title10);
        TextView tt11 = (TextView) v.findViewById(R.id.title11);
        TextView tt12 = (TextView) v.findViewById(R.id.title12);
//        TextView tt13 = (TextView) v.findViewById(R.id.title13);

        TextView tvBack = (TextView) v.findViewById(R.id.toolbar_back);
        TextView tvEdit = (TextView) v.findViewById(R.id.toolbar_edit);

        TextView tvName = (TextView) v.findViewById(R.id.tvName);
        TextView tvNumber = (TextView) v.findViewById(R.id.tvNumber);

        TextView tvTitleProfile = (TextView) v.findViewById(R.id.tvTitleProfile);
        TextView tvTitleValue = (TextView) v.findViewById(R.id.tvTitleValue);
        TextView tvPosition = (TextView) v.findViewById(R.id.tvPosition);
        TextView tvPositionValue = (TextView) v.findViewById(R.id.tvPositionValue);

        // title
        TextView tvOwner = (TextView) v.findViewById(R.id.tvOwner);
        TextView tvDecisionMaker = (TextView) v.findViewById(R.id.tvDecision_maker);
        TextView tvRelation = (TextView) v.findViewById(R.id.tvRelation);

        TextView tvBirthday = (TextView) v.findViewById(R.id.tvBirthday);
        TextView tvMobile = (TextView) v.findViewById(R.id.tvMobile);
        TextView tvEmail = (TextView) v.findViewById(R.id.tvEmail);
        TextView tvLine = (TextView) v.findViewById(R.id.tvLine);
        TextView titleFood = (TextView) v.findViewById(R.id.titleFood);
        TextView tvLikeFood = (TextView) v.findViewById(R.id.tvLikeFood);
        TextView tvDisLikeFood = (TextView) v.findViewById(R.id.tvDisLikeFood);
        TextView titleDrink = (TextView) v.findViewById(R.id.titleDrink);
        TextView tvLikeDrink = (TextView) v.findViewById(R.id.tvLikeDrink);
        TextView tvDisLikeDrink = (TextView) v.findViewById(R.id.tvDisLikeDrink);
//        TextView titleActivities = (TextView) v.findViewById(R.id.titleActivities);
        TextView tvFavoriteAct = (TextView) v.findViewById(R.id.tvFavoriteAct);
//        TextView tvNonFavoriteAct = (TextView) v.findViewById(R.id.tvNonFavAct);


        tt01.setTypeface(fontThSarabunBold);
        tt02.setTypeface(fontThSarabunBold);
        tt03.setTypeface(fontThSarabunBold);
        tt04.setTypeface(fontThSarabunBold);
        tt05.setTypeface(fontThSarabunBold);
        tt06.setTypeface(fontThSarabunBold);
        tt07.setTypeface(fontThSarabunBold);
        tt08.setTypeface(fontThSarabunBold);
        tt09.setTypeface(fontThSarabunBold);
        tt10.setTypeface(fontThSarabunBold);
        tt11.setTypeface(fontThSarabunBold);
        tt12.setTypeface(fontThSarabunBold);
//        tt13.setTypeface(fontThSarabunBold);

        tvBack.setTypeface(fontThSarabun);
        tvEdit.setTypeface(fontThSarabun);

        tvName.setTypeface(fontThSarabun);
        tvNumber.setTypeface(fontThSarabun);
        tvTitleProfile.setTypeface(fontThSarabun);
        tvTitleValue.setTypeface(fontThSarabun);
        tvPosition.setTypeface(fontThSarabun);
        tvPositionValue.setTypeface(fontThSarabun);
        tvOwner.setTypeface(fontThSarabun);
        tvDecisionMaker.setTypeface(fontThSarabun);
        tvRelation.setTypeface(fontThSarabun);
        tvBirthday.setTypeface(fontThSarabun);
        tvMobile.setTypeface(fontThSarabun);
        tvEmail.setTypeface(fontThSarabun);
        tvLine.setTypeface(fontThSarabun);
        titleFood.setTypeface(fontThSarabunBold);
        tvLikeFood.setTypeface(fontThSarabun);
        tvDisLikeFood.setTypeface(fontThSarabun);
        titleDrink.setTypeface(fontThSarabunBold);
        tvLikeDrink.setTypeface(fontThSarabun);
        tvDisLikeDrink.setTypeface(fontThSarabun);
//        titleActivities.setTypeface(fontThSarabunBold);
        tvFavoriteAct.setTypeface(fontThSarabun);
//        tvNonFavoriteAct.setTypeface(fontThSarabun);


        if(getContact.size()!=0){
            tvName.setText(getContact.get(0).getFirstname()+" "+getContact.get(0).getLastname());
            tvTitleValue.setText(getContact.get(0).getTitle()); // newNumber
            tvPositionValue.setText(getContact.get(0).getPosition());
            tvBirthday.setText(getContact.get(0).getBirthday());
            tvMobile.setText(getContact.get(0).getMobile());
            tvEmail.setText(getContact.get(0).getEmail());
            tvLine.setText(getContact.get(0).getLineId());
            tvLikeFood.setText(getContact.get(0).getFavFood());
            tvDisLikeFood.setText(getContact.get(0).getNonFavFood());
            tvLikeDrink.setText(getContact.get(0).getFavDrink());
            tvDisLikeDrink.setText(getContact.get(0).getNonFavDrink());
            tvFavoriteAct.setText(getContact.get(0).getFavAct());
            // tvNonFavoriteAct.setText(getContact.get(0).getNonFavAct());


            imageId = getContact.get(0).getImage();
            downloadUrl = "https://c.ap2.content.force.com/servlet/servlet.ImageServer?id="
                    +imageId//+"01528000001NHlxAAG"
                    +"&oid=00D2800000149LJEAY";

            boolean correct = "null".equals(imageId);
            if(correct == false){

                // check Image in Local Database or not
                chckImg = loadImageFromStorage(imageId);
                if(chckImg ==false){
                    new ImageDownloader().execute(downloadUrl);
                    Log.e("loadImageFromStorage", "loadImageFromStorage:_chckImg_" + chckImg);
                }

            }

        } else {
            tvName.setText(" null ");
            tvTitleValue.setText(" null ");
            tvPositionValue.setText(" null ");
            tvBirthday.setText(" null ");
            tvMobile.setText(" null ");
            tvEmail.setText(" null ");
            tvLine.setText(" null ");
            tvLikeFood.setText(" null ");
            tvDisLikeFood.setText(" null ");
            tvLikeDrink.setText(" null ");
            tvDisLikeDrink.setText(" null ");
            tvFavoriteAct.setText(" null ");
//            tvNonFavoriteAct.setText(" null ");
        }
////
        tvBack.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {
//                Intent i = new Intent(HBD2Contact.this, HomeHBDActivity.class);
////                parentId = getContact.get(0).getParentId();
////                i.putExtra("getId", parentId);
//                startActivity(i);
            }
        });

        tvEdit.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {

//                Intent i = new Intent(HBD2Contact.this, HBD2ContactEdit.class);
//                i.putExtra("getIdContact", idContact);
//
//                startActivity(i);
            }
        });


        // Original
        getActivity().setTitle(null);
        Toolbar topToolBar = (Toolbar) v.findViewById(R.id.toolbar);
        TextView mTitle = (TextView) topToolBar.findViewById(R.id.toolbar_title);
        // setSupportActionBar(topToolBar);
        topToolBar.setTitleTextColor(getResources().getColor(android.R.color.background_light));
        topToolBar.setLogoDescription(getResources().getString(R.string.logo_desc));

        mTitle.setTypeface(fontThSarabunBold);

        return v;
    }


    @Override
    public void onClick(View v) {

    }

    // class for show image
    class ImageDownloader extends AsyncTask<String, String, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... param) {
            // TODO Auto-generated method stub
            return downloadBitmap(param[0]);
        }

        @Override
        protected void onPreExecute() {
            Log.i("Async-Example", "onPreExecute Called");
            simpleWaitDialog = ProgressDialog.show(getActivity(),
                    "Wait", "Downloading Image");

        }

        @Override
        protected void onPostExecute(Bitmap result) {
            Log.i("Async-Example", "onPostExecute Called");
            downloadedImg.setImageBitmap(result);
            saveImageToInternalStorage(result);
            simpleWaitDialog.dismiss();

        }

        private Bitmap downloadBitmap(String url) {
            // initilize the default HTTP client object
            final DefaultHttpClient client = new DefaultHttpClient();

            //forming a HttoGet request
            final HttpGet getRequest = new HttpGet(url);
            try {

                HttpResponse response = client.execute(getRequest);

                //check 200 OK for success
                final int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode != HttpStatus.SC_OK) {
                    Log.w("ImageDownloader", "Error " + statusCode +
                            " while retrieving bitmap from " + url);
                    return null;

                }

                final HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputStream = null;
                    try {
                        // getting contents from the stream
                        inputStream = entity.getContent();

                        // decoding stream data back into image Bitmap that android understands
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        return bitmap;
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        entity.consumeContent();
                    }
                }
            } catch (Exception e) {
                // You Could provide a more explicit error message for IOException
                getRequest.abort();
                Log.e("ImageDownloader", "Something went wrong while" +
                        " retrieving bitmap from " + url + e.toString());
            }

            return null;
        }
    }
    // Start
    public boolean saveImageToInternalStorage(Bitmap image) {

        try {
// Use the compress method on the Bitmap object to write image to
// the OutputStream
            FileOutputStream fos = getActivity().openFileOutput(imageId+".jpg", Context.MODE_PRIVATE);

// Writing the bitmap to the output stream
            image.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();

            return true;
        } catch (Exception e) {
            Log.e("saveToInternalStorage()", "why_cannot:_" + e.getMessage());
            return false;
        }
    }
    // End

    private boolean loadImageFromStorage(String imageId)
    {

        try {
            File f=new File(getActivity().getFilesDir(), imageId+".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            if(b != null){
                chckImg = true;
                downloadedImg.setImageBitmap(b);
                Log.e("loadImageFromStorage", "loadImageFromStorage:_chckImg_" + chckImg);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return chckImg;
    } // end loadImageFromStorage

}
