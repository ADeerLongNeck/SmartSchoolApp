package cn.adeerlongneck.app.smartschoolapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.adeerlongneck.app.smartschoolapp.MyApplication;
import cn.adeerlongneck.app.smartschoolapp.Presenster.AddCoursePresent;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.View.AddCourseView;

public class AddCourseActivity extends AppCompatActivity implements AddCourseView {
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        MyApplication application = (MyApplication) getApplicationContext();
        final String stuid = application.getStuid();

        editText = (EditText) findViewById(R.id.add);
        button = (Button) findViewById(R.id.bt_add);
        final AddCoursePresent addCoursePresent = new AddCoursePresent(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCoursePresent.add(editText.getText().toString(), stuid);
            }
        });


    }

    @Override
    public void success() {
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void fail() {
        Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }
}
