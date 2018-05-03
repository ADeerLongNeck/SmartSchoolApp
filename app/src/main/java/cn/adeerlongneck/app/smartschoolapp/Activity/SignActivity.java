package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.adeerlongneck.app.smartschoolapp.R;

public class SignActivity extends AppCompatActivity {

    Button bt_addCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ini();
    }

    public void ini() {
        bt_addCourse = (Button) findViewById(R.id.addcourse);
        bt_addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignActivity.this, AddCourseActivity.class);
                startActivity(intent);
            }
        });


    }





}
