package com.salesforce.android.restsample.Tablet.Detail;

import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.salesforce.android.restsample.HomeActivitySQLiteDBnew2;
import com.salesforce.android.restsample.ItemSaleSummary.EntryAdapterSaleSummary;
import com.salesforce.android.restsample.ItemSaleSummary.EntryItemSaleSummary;
import com.salesforce.android.restsample.ItemSaleSummary.ItemSaleSummary;
import com.salesforce.android.restsample.Library.RoundCornerProgressBar;
import com.salesforce.android.restsample.Library.TextRoundCornerProgressBar;
import com.salesforce.android.restsample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pannikar on 7/13/16 AD.
 */
public class TabletDetailProgressBar extends Fragment {

    RoundCornerProgressBar Progress;
    int progressIndex = 0, progressIndexAll = 10;

    public TextRoundCornerProgressBar[] progressBars = new TextRoundCornerProgressBar[10];
    public TextView[] textViews = new TextView[10];
    public List<TextRoundCornerProgressBar> progressBarArrayList;
    public List<TextView> textViewArrayList;
    public int dataMock [] = {10,20,20,20,20,20,20,20,20,20};
    public int index = 0;
    public List<Integer> counter;
    public List<Integer> current;
    public int counter_test = 0;
    public int current_test = 100;

    //Thread myThreads[] = new Thread[10];
    Thread myThreads;
    TextView title;
//    TextView back;
//    LinearLayout lnBack;
    private ListView mListView;

    JSONArray jsonArray;
    JSONObject jsonObject;
    int gradColor1;
    int gradColor2;
    int gradColor3;
    int height;
    ArrayList<ItemSaleSummary> itemSaleSummaries = new ArrayList<ItemSaleSummary>();
    private TextView tvTitle;
    private TextView tvActual;
    private TextView tvTarget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View view = inflater.inflate(R.layout.detail_fragment, container, false);
        View v = inflater.inflate(R.layout.tablet_activity_progressbar, container, false);
        Typeface fontThSarabun = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabun.ttf");
        Typeface fontThSarabunBold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/THSarabunBold.ttf");

        title = (TextView) v.findViewById(R.id.toolbar_title);
//        lnBack = (LinearLayout) v.findViewById(R.id.LnLeft);
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvActual = (TextView) v.findViewById(R.id.tvActual);
        tvTarget = (TextView) v.findViewById(R.id.tvTarget);

        tvTitle.setTypeface(fontThSarabunBold);
        tvActual.setTypeface(fontThSarabunBold);
        tvTarget.setTypeface(fontThSarabunBold);

        title.setTypeface(fontThSarabunBold);

        String string = "[" +
                "{" +
                "\"title\":\"Tensoplast\"," +
                "\"actual\":50," +
                "\"target\":300" +
                "},{" +
                "\"title\":\"Kangaroo\"," +
                "\"actual\":200," +
                "\"target\":200" +
                "},{" +
                "\"title\":\"Bios Life\"," +
                "\"actual\":150," +
                "\"target\":300" +
                "},{" +
                "\"title\":\"Jackchia\"," +
                "\"actual\":100," +
                "\"target\":200" +
                "}" +
                "]";

        textViewArrayList = new ArrayList<TextView>();
        progressBarArrayList = new ArrayList<TextRoundCornerProgressBar>();
        counter = new ArrayList<Integer>();
        current = new ArrayList<Integer>();
        mListView = (ListView) v.findViewById(R.id.list_item_sale_summary);

        try{
            boolean isPhone = true;
            jsonArray = new JSONArray(string);
            Log.e("jsonarray",jsonArray.toString());
            for (int i=0;i<jsonArray.length();i++){
                jsonObject = jsonArray.getJSONObject(i);
                Log.e("jsonObj" + (i + 1), jsonObject.toString());
                height = (int) getResources().getDimension(R.dimen.progressBarHeight_phone);
                setColorTextRoundCornerProgressBar(jsonObject.optInt("actual"), jsonObject.optInt("target"));

                itemSaleSummaries.add(new EntryItemSaleSummary(jsonObject.optString("title"),
                        jsonObject.optInt("actual"),
                        jsonObject.optInt("target"),
                        height,
                        gradColor1,
                        gradColor2,
                        gradColor3));

                Log.e("jsonarray", "jsonObject:_1:_" + jsonObject.optInt("actual"));
                Log.e("jsonarray", "jsonObject:_2:_" + jsonObject.optInt("target"));
                Log.e("jsonarray", "jsonObject:_3:_" + height);
                Log.e("jsonarray", "jsonObject:_4:_" + gradColor1);
                Log.e("jsonarray", "jsonObject:_5:_" + gradColor2);
                Log.e("jsonarray", "jsonObject:_6:_" + gradColor3);

                final EntryAdapterSaleSummary entry = new EntryAdapterSaleSummary(getActivity(),itemSaleSummaries);
                mListView.setAdapter(entry);

            }

        }catch (JSONException e){ e.printStackTrace(); }
        return v;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public void setColorTextRoundCornerProgressBar (float base, float max)
    {
        float percentage = (base/max) * 100;
        if(percentage <= 30)
        {
            gradColor1 = getResources().getColor(R.color.colorGradientRed1);
            gradColor2 = getResources().getColor(R.color.colorGradientRed2);
            gradColor3 = getResources().getColor(R.color.colorGradientRed3);

        }else if(percentage >= 80){
            gradColor1 = getResources().getColor(R.color.colorGradientGreen1);
            gradColor2 = getResources().getColor(R.color.colorGradientGreen2);
            gradColor3 = getResources().getColor(R.color.colorGradientGreen3);

        }else{
            gradColor1 = getResources().getColor(R.color.colorGradientYellow1);
            gradColor2 = getResources().getColor(R.color.colorGradientYellow2);
            gradColor3 = getResources().getColor(R.color.colorGradientYellow3);

        }
    }

}

