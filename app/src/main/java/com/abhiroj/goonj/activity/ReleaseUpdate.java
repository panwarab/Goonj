package com.abhiroj.goonj.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.abhiroj.goonj.R;

import static com.abhiroj.goonj.data.Constants.UPDATE_MESSAGE;
import static com.abhiroj.goonj.data.Constants.UPDATE_TITLE;

public class ReleaseUpdate extends Activity {

    private EditText uppd_title;
    private EditText uppd_mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_release_update);
        this.setFinishOnTouchOutside(false);
         uppd_title=(EditText) findViewById(R.id.upd_title);
        uppd_mess=(EditText) findViewById(R.id.upd_message);
        TextView go=(TextView) findViewById(R.id.ok);
        TextView cancel=(TextView) findViewById(R.id.cancel);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDataValid()) {
                    Intent ret_dat = new Intent();
                    ret_dat.putExtra(UPDATE_TITLE, uppd_title.getText().toString());
                    ret_dat.putExtra(UPDATE_MESSAGE, uppd_mess.getText().toString());
                    setResult(RESULT_OK, ret_dat);
                    finish();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED,null);
                finish();
            }
        });
    }

    private boolean isDataValid() {
        String title= uppd_title.getText().toString();
        String message=uppd_mess.getText().toString();
        if(title.length()==0)
        {
            if(uppd_title.getHint().toString().charAt(0)!='*') {
                uppd_title.setHint("*" + uppd_title.getHint().toString());
                uppd_title.setHintTextColor(Color.RED);
            }
                return false;
        }
        if(message.length()==0)
        {
            if(uppd_mess.getHint().toString().charAt(0)!='*') {
                uppd_mess.setHint("*" + uppd_mess.getHint().toString());
                uppd_mess.setHintTextColor(Color.RED);
            }
        return false;
        }

        return true;
    }
}
