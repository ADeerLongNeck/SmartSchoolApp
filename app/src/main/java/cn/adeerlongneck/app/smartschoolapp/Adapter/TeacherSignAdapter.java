package cn.adeerlongneck.app.smartschoolapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.adeerlongneck.app.smartschoolapp.Activity.CreateSignActivity;
import cn.adeerlongneck.app.smartschoolapp.Activity.HistoryActivity;
import cn.adeerlongneck.app.smartschoolapp.Model.CourseModel;
import cn.adeerlongneck.app.smartschoolapp.Model.HistoryModel;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.View.TeacherSignView;

/**
 * Created by chao on 2018/5/10.
 */

public class TeacherSignAdapter extends RecyclerView.Adapter<TeacherSignAdapter.ViewHolder> {
    List<CourseModel> list;
    TeacherSignView mmmview;
    CourseModel mcour;
    int pos;
Context context;
    public TeacherSignAdapter(List<CourseModel> list, TeacherSignView view,Context context) {
        this.list = list;
        this.context=context;
        this.mmmview = view;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_teacherclass, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CourseModel courseModel = list.get(position);
        Log.d("2222", "2222222222");
        this.mcour = list.get(position);
        holder.im_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delClass(courseModel.getId());
            }
        });
        holder.bt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CreateSignActivity.class);
                intent.putExtra("courseid",courseModel.getId());
                context.startActivity(intent);
            }
        });
        holder.bt_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,HistoryActivity.class);
                intent.putExtra("courseid",courseModel.getId());
                context.startActivity(intent);
            }
        });
        holder.tx_coursename.setText(courseModel.getName());
        holder.tx_courseinfo.setText(courseModel.getInfo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void delClass(String id) {
        Map<String, String> map = new HashMap<>();

        map.put("id", id);

        HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                Log.d("1", object.toString());
                String data = object.toString();
                mmmview.re();


            }

            @Override
            public void onFail(String error) {
                Log.d("1", "11111111113211111111111111111111111--------------------1" + error);

            }
        });
        httpUtil.sendPostHttp("http://www.adeerlongneck.cn:8000/DelectClass", map);


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tx_coursename;
        TextView tx_courseinfo;
        Button bt_create;
        Button bt_history;
        ImageView im_del;
        View mview;

        public ViewHolder(View view) {
            super(view);
            mview = view;
            bt_create=(Button)view.findViewById(R.id.button24);
            bt_history=(Button)view.findViewById(R.id.button23);
            tx_coursename = (TextView) view.findViewById(R.id.textView6);
            tx_courseinfo = (TextView) view.findViewById(R.id.textView7);
            im_del = (ImageView) view.findViewById(R.id.del);

        }
    }
}
