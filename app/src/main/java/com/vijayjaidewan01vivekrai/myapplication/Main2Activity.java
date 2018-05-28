package com.vijayjaidewan01vivekrai.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Years;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collector;

public class Main2Activity extends AppCompatActivity {

    private InterstitialAd interstitialAdSecond;

    Button currentDateButton;
    Button birthDateButton;
    Button calculate;
    Button clear;
    TextView year;
    TextView month;
    TextView day;

    EditText currentDate;
    EditText birthDate;

    int y1, m1, d1;

    Date dateObjectCurrent;
    Date dateObjectBirth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        MobileAds.initialize(Main2Activity.this , "ca-app-pub-5029277223580789~3481843384");
        AdView adViewThird = findViewById(R.id.adViewThird);
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewThird.loadAd(adRequest);

        calculate = findViewById(R.id.calculate);
        clear = findViewById(R.id.clear);
        currentDateButton = findViewById(R.id.currentDateButton);
        birthDateButton = findViewById(R.id.birthDateButton);
        currentDate = findViewById(R.id.currentDate);
        birthDate = findViewById(R.id.birthDate);
        year = findViewById(R.id.year);
        month = findViewById(R.id.month);
        day = findViewById(R.id.day);


//        setting the year, month and day using Calendar Class
        Calendar calendar = Calendar.getInstance();
        y1 = calendar.get(Calendar.YEAR);
        m1 = calendar.get(Calendar.MONTH);
        d1 = calendar.get(Calendar.DAY_OF_MONTH);



        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c1 = currentDate.getText().toString();
                String c2 = birthDate.getText().toString();
                if (c1.equals("") ){
                    currentDate.setError("Enter Current Date");
                }else if (c2.equals("")){
                    birthDate.setError("Enter Birth Date");
                }else {



                    try {

                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                        current Date Extraction
                        String dob_varCurrent = currentDate.getText().toString();
                        dateObjectCurrent = formatter.parse(dob_varCurrent);
                        int currentDay= dateObjectCurrent.getDate();
                        int currentMonth = dateObjectCurrent.getMonth() + 1;
                        int currentYear = dateObjectCurrent.getYear()+ 1900;
                        String cd = String.valueOf(currentDay);
                        String cm = String.valueOf(currentMonth);
                        String cy = String.valueOf(currentYear);
                        String strCurrentDate = cd+" "+cm+" "+ cy;

//                        birth Date Extraction
                        String dob_varBirth = birthDate.getText().toString();
                        dateObjectBirth = formatter.parse(dob_varBirth);
                        int birthDay= dateObjectBirth.getDate();
                        int birthMonth = dateObjectBirth.getMonth() + 1;
                        int birthYear = dateObjectBirth.getYear()+ 1900;
                        String bd = String.valueOf(birthDay);
                        String bm = String.valueOf(birthMonth);
                        String by = String.valueOf(birthYear);
                        String strBirthDate = bd+" "+bm+" "+ by;

//                        int finalYear = currentYear - birthYear;
//                        if (birthMonth > currentMonth && (currentYear >= birthYear)){
//                            finalYear -= 1;
//                            String finalYearString = String.valueOf(finalYear);
//                            year.setText(finalYearString);
//                        }else if (birthMonth < currentMonth && (currentYear >= birthYear)){
//                            String finalYearString = String.valueOf(finalYear);
//                            year.setText(finalYearString);
//                        }else if ((birthMonth == currentMonth) && (birthDay < currentDay) && (currentYear >= birthYear)){
//                            String finalYearString = String.valueOf(finalYear);
//                            year.setText(finalYearString);
//                        }else if ((birthMonth == currentMonth) && (birthDay > currentDay) && (currentYear > birthYear)){
//                            finalYear -=1;
//                            String finalYearString = String.valueOf(finalYear);
//                            year.setText(finalYearString);
//                        } else if ((birthMonth == currentMonth) && (birthDay == currentDay) && (currentYear >= birthYear)){
//                            String finalYearString = String.valueOf(finalYear);
//                            year.setText(finalYearString);
//                        }else if ((birthMonth == currentMonth) && (birthDay > currentDay) && (currentYear == birthYear)){
//                            Toast.makeText(Main2Activity.this, "Birth Date cannot be greater than Current Date ", Toast.LENGTH_SHORT).show();
//                        }  else if ( currentYear < birthYear){
//                            Toast.makeText(Main2Activity.this, "Birth Year Cannot be greater than Current Year", Toast.LENGTH_LONG).show();
//                        }

                        if (currentYear < birthYear){
                            Toast.makeText(Main2Activity.this, "Birth Year Cannot be greater than Current Year", Toast.LENGTH_SHORT).show();

                        }else if ((currentYear == birthYear) && (birthMonth > currentMonth)){
                            Toast.makeText(Main2Activity.this, "Birth Month Cannot be greater than Current Month", Toast.LENGTH_SHORT).show();

                        }else if ((currentYear == birthYear) && (birthMonth == currentMonth) && (currentDay < birthDay)){
                            Toast.makeText(Main2Activity.this, "Birth Day Cannot be greater than Current Day", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            LocalDate localBirth = new LocalDate(dateObjectBirth);
                            LocalDate localCurrent = new LocalDate(dateObjectCurrent);

                            Years finY = Years.yearsBetween(localBirth, localCurrent);
                            Months monthy = Months.monthsBetween(localBirth, localCurrent);
                            Days dayy = Days.daysBetween(localBirth, localCurrent);

                            String stringFiny = String.valueOf(finY);
                            String stringMonthy = String.valueOf(monthy);
                            String stringDayy = String.valueOf(dayy);

                            String stringFinyNeed = stringFiny.substring(1, stringFiny.lastIndexOf('Y'));
                            String stringMonthyNeed = stringMonthy.substring(1, stringMonthy.lastIndexOf('M'));
                            String stringDayyNeed = stringDayy.substring(1, stringDayy.lastIndexOf('D'));
                            year.setText(stringFinyNeed);
                            month.setText(stringMonthyNeed);
                            day.setText(stringDayyNeed);

                        }


                    }catch (Exception e){
                        Toast.makeText(Main2Activity.this, "Wrong Format Passed", Toast.LENGTH_SHORT).show();
                    }




                }
            }
        });
        interstitialAdSecond = new InterstitialAd(Main2Activity.this);
        interstitialAdSecond.setAdUnitId("ca-app-pub-5029277223580789/7425791973");
        AdRequest adRequestInterstitialSecond = new AdRequest.Builder().build();
        interstitialAdSecond.loadAd(adRequestInterstitialSecond);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAdSecond.isLoaded()){
                    interstitialAdSecond.show();
                }
                currentDate.setText("");
                birthDate.setText("");
                year.setText("");
                month.setText("");
                day.setText("");
            }
        });




        currentDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 += 1;
                        currentDate.setText(i2 + "/" + i1 + "/" + i);

                    }
                }, y1, m1, d1);
                datePickerDialog.show();

            }
        });

        birthDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 += 1;
                        birthDate.setText(i2 + "/" + i1 + "/" + i);
                    }
                }, y1, m1, d1);
                datePickerDialog.show();



            }
        });

    }

//    private void getAge(){
//        Calendar current = (Calendar) currentDate.getText();
//        Calendar birth = (Calendar) birthDate.getText();
//        int age = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR) ;
//        String reqAge = String.valueOf(age);
//        year.setText(reqAge);
//
//    }

}


