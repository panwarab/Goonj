package com.abhiroj.goonj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.abhiroj.goonj.R;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_feedback);
        toolbar.setTitleTextColor(Color.BLACK);
        Drawable drawable=getDrawable(R.drawable.ic_arrow_back);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        final EditText editText=(EditText) findViewById(R.id.improv);
        FloatingActionButton floatingActionButton=(FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(isValid(editText))
             {
                 final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                 emailIntent.setType("plain/text");
                 emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"abhiroj95@gmail.com"});
                 emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "TODO Features/Suggestion/Issues");
                 emailIntent.putExtra(Intent.EXTRA_TEXT,editText.getText().toString());
                 startActivity(Intent.createChooser(emailIntent, "Choose App"));
             }
            }
        });
        }

    private boolean isValid(EditText editText) {
     String message=editText.getText().toString();
        if(message.length()==0)
        {
            String hint=editText.getHint().toString();
            if(hint.charAt(0)!='*')
            {
                editText.setHint("*"+hint);
                editText.setHintTextColor(Color.RED);
            }
            return false;
        }
        return true;
    }

}
