package com.abhiroj.goonj.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.data.Constants;
import com.abhiroj.goonj.data.EventData;
import com.abhiroj.goonj.utils.Utility;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.abhiroj.goonj.data.Constants.EVENT_PATH;
import static com.abhiroj.goonj.data.Constants.events;
import static com.abhiroj.goonj.utils.Utility.HANDLER_DELAY_TIME;
import static com.abhiroj.goonj.utils.Utility.checkNotNull;
import static com.abhiroj.goonj.utils.Utility.showSnackBar;
import static com.abhiroj.goonj.utils.Utility.showToast;

public class AddEvent extends AppCompatActivity {

    private static final String TAG=AddEvent.class.getSimpleName();
    private EditText eventtime;
    private EditText eventdate;
    EditText eventname;
    EditText eventrules;
    EditText eventvenue;
    EditText eventcateg;
    private Button submit;
    private LinearLayout parent;


    private Runnable fieldCheckRunnable=new Runnable() {
        @Override
        public void run() {
              if(allEditTextFill())
              {
                  submit.setClickable(true);
                  submit.setTextColor(Color.BLACK);
              }
              else
              {
                  submit.setClickable(false);
                  submit.setTextColor(Color.LTGRAY);
                  fieldCheckHandler.postDelayed(fieldCheckRunnable,HANDLER_DELAY_TIME);
              }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    if(checkNotNull(fieldCheckHandler))
    {
        fieldCheckHandler.post(fieldCheckRunnable);
    }
    }

    private void removeHandler() {
        if(checkNotNull(fieldCheckHandler)) {
            fieldCheckHandler.removeCallbacksAndMessages(null);
            fieldCheckHandler = null;
        }
    }

    private Handler fieldCheckHandler=makeHandler();

    private Handler makeHandler() {
        return new Handler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.add_event);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        parent=(LinearLayout) findViewById(R.id.parent);
        eventname=(EditText) findViewById(R.id.event_name);
        eventrules=(EditText) findViewById(R.id.event_rules);
        eventtime=(EditText) findViewById(R.id.event_time);
        eventvenue=(EditText) findViewById(R.id.event_venue);
        eventcateg=(EditText) findViewById(R.id.event_category);
        eventcateg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(AddEvent.this).setTitle(R.string.event_category).setItems(events, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      eventcateg.setText(events[which]);
                    }
                });
                alertDialog.show();
            }
        });
        submit=(Button) findViewById(R.id.submit_event);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   removeHandler();
                   EventData eventData=new EventData();
                    eventData.setName(getText(eventname));
                    eventData.setDate(getText(eventdate));
                    eventData.setRules(getText(eventrules));
                    eventData.setTime(getText(eventtime));
                    eventData.setVenue(getText(eventvenue));
                    eventData.setCategory(getText(eventcateg));
                    if(getFinalCategory(eventData.getCategory()))
                    {
                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                    final DatabaseReference databaseReference=firebaseDatabase.getReference().child(EVENT_PATH).child(eventData.getCategory());
                    databaseReference.push().setValue(eventData, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                showSnackBar(AddEvent.this, R.string.firebase_error);
                            } else {
                                setResult(RESULT_OK);
                                finish();
                            }
                        }
                        });
            }
            else
                    {
                        showToast(AddEvent.this,R.string.category_error);
                    }
        }});
        eventtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog=new TimePickerDialog(AddEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                      eventtime.setText(hourOfDay+":"+minute);
                    }
                },hour,minute,false);
                timePickerDialog.setTitle(R.string.set_time);
                timePickerDialog.show();
            }
        });
        eventdate=(EditText) findViewById(R.id.event_date);
        eventdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                final int day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(AddEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        eventdate.setText(day_of_month+"."+month+"."+year);
                    }
                },year,month,day_of_month);
                datePickerDialog.setTitle(R.string.set_date);
                datePickerDialog.show();
            }
        });
    }

    private boolean getFinalCategory(String category) {
    switch(category)
    {
        case "Dramatics":
            return true;
        case "Literary":
            return true;
        case "Arts":
            return true;
        case "Music":
            return true;
        case "Dance":
            return true;
        case "Photography":
            return true;
        case "Other":
            return true;
        default:
            return false;
    }
    }

    private String getText(EditText field) {
    return field.getText().toString();
    }

    /**
     * checks wether all edit text arefilled or not
     * @return
     */
    private boolean allEditTextFill() {

        if(isEmpty(eventname)|| isEmpty(eventrules)||isEmpty(eventvenue)||isEmpty(eventtime)||isEmpty(eventdate))
        {
            return false;
        }
        return true;
    }

    private boolean isEmpty(EditText field) {

        String text=field.getText().toString().trim();
        if(text==null||text.length()==0)
        {
            /*if(field.getHint().toString().charAt(0)!='*') {
                field.setHint(new StringBuffer().append("*").append(field.getHint()));
                field.setHintTextColor(Color.RED);
            }*/
                return true;
        }
        return false;
    }


}
