package com.salesforce.android.restsample.AboutTime;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by pannikar on 6/14/2016 AD.
 */
public class ClassifiedTime {

    int nowyear;
    int nowmonth;
    int nowday;

    String result;
    String resultCompareDate2;

    String year, month, day;

    public int componentTimeToTimestamp(int year, int month, int day) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
//        c.set(Calendar.HOUR, hour);
//        c.set(Calendar.MINUTE, minute);
//        c.set(Calendar.SECOND, 0);
//        c.set(Calendar.MILLISECOND, 0);

        return (int) (c.getTimeInMillis() / 1000L);
    }

    // return string
    public String getFormatDate(int timeStamp){

        java.util.Date time=new java.util.Date((long)timeStamp*1000);

        DateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm a");

        // Get the date today using Calendar object.
//        Date stDate = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String reportDate = df.format(time);

        return reportDate ;
    }

    public String getFormatDateBigDecimal(long timeStamp){

        java.util.Date time=new java.util.Date((long)timeStamp);

        DateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm a");

        // Get the date today using Calendar object.
//        Date stDate = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String reportDate = df.format(time);

        return reportDate ;
    }

    public String getFormatDateNoTime(int timeStamp){

        java.util.Date time=new java.util.Date((long)timeStamp*1000);

        DateFormat df = new SimpleDateFormat("MMM dd, yyyy");

        // Get the date today using Calendar object.
//        Date stDate = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String reportDate = df.format(time);

        return reportDate ;

    }

    public String getFormatDateFromMMMDYinstance(){

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Change Date to compare
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);

        String formatDate = new SimpleDateFormat("MMM d, yyyy").format(c.getTime());

        return formatDate ;
    }

    public String getFormatDateFromMMMDY(int year, int month, int day){

        final Calendar c = Calendar.getInstance();
//        year = c.get(Calendar.YEAR);
//        month = c.get(Calendar.MONTH);
//        day = c.get(Calendar.DAY_OF_MONTH);

        // Change Date to compare
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);

        String formatDate = new SimpleDateFormat("MMM d, yyyy").format(c.getTime());

        return formatDate ;
    }



    public String compareDate(int timeStamp){

        Log.e(" Check", "classifiedPlan:_compareDate" + timeStamp);
//        java.util.Date date = new java.util.Date((long)timeStamp);
        java.util.Date date = new java.util.Date((long)timeStamp*1000);

        String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date);//Thursday
        String stringMonth = (String) android.text.format.DateFormat.format("MMM", date); //Jun
        month = (String) android.text.format.DateFormat.format("MM", date); //06
        year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
        day = (String) android.text.format.DateFormat.format("dd", date); //20

//        Log.e(" Check", "classifiedPlan:_compareDate_date_m-d-y" +"m: "+month+" d: "+ day+" y: "+year);

        result = compareEach(year, month, day);

        return result;

    }

    public String compareEach(String year, String month, String day){

        int qyear = Integer.parseInt(year);
        int qmonth = Integer.parseInt(month);
        int qday = Integer.parseInt(day);

        Calendar c = Calendar.getInstance();
        nowyear = c.get(Calendar.YEAR);
        nowmonth = c.get(Calendar.MONTH) + 1;
        nowday = c.get(Calendar.DAY_OF_MONTH);

        if(qyear == nowyear){

            if(qmonth == nowmonth){

                if(qday == nowday){

                    resultCompareDate2 = "today";

                }else if (qday < nowday){

                    resultCompareDate2 = "past";

                }else {

                    int tomorrow = qday - nowday;
                    if(tomorrow == 1){

                        resultCompareDate2 = "tomorrow";

                    }else {

                        resultCompareDate2 = "future";
                    }

                }

            }else if (qmonth < nowmonth){

                resultCompareDate2 = "past";

            }else {

                resultCompareDate2 = "future";

            }

        }else if (qyear < nowyear){

            resultCompareDate2 = "past";

        }else {

            resultCompareDate2 = "future";

        }
        return resultCompareDate2;
    }
}
