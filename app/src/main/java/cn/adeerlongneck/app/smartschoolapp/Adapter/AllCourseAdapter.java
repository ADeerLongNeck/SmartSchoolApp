package cn.adeerlongneck.app.smartschoolapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.adeerlongneck.app.smartschoolapp.Activity.HistoryInfoActivity;
import cn.adeerlongneck.app.smartschoolapp.Model.CourseModel;
import cn.adeerlongneck.app.smartschoolapp.Model.HistoryModel;
import cn.adeerlongneck.app.smartschoolapp.Model.studentSignModel;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.View.AddCourseView;

public class AllCourseAdapter extends RecyclerView.Adapter<AllCourseAdapter.ViewHolder> {
    List<CourseModel> list;
    Context context;
AddCourseView addCourseView;

    @Override
    public AllCourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_course, parent, false);
        // 实例化viewholder
        AllCourseAdapter.ViewHolder viewHolder = new  AllCourseAdapter.ViewHolder(v);


        return viewHolder;


    }
    public AllCourseAdapter(List<CourseModel> list,Context context,AddCourseView addCourseView){
        this.list = list;
        this.context=context;
        this.addCourseView=addCourseView;
    }

    @Override
    public void onBindViewHolder(AllCourseAdapter.ViewHolder holder, int position) {
        final CourseModel courseModel = list.get(position);
        Log.d("2222", "2222222222");
        holder.tx_info.setText(courseModel.getInfo());
        holder.tx_name.setText(courseModel.getName());
        holder.bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCourseView.add(courseModel.getId());
            }
        });
//
//        holder.mview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context,HistoryInfoActivity.class);
//                intent.putExtra("random",historyModel.getRandoms());
//                context.startActivity(intent);
//            }
//        });
//        holder.tx_random.setText(historyModel.getRandoms());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tx_name;
            TextView tx_info;
            Button bt_add;
            View mview;

            public ViewHolder(View view) {

                super(view);
                mview=view;

                tx_name=(TextView)view.findViewById(R.id.course_name);
                tx_info=(TextView)view.findViewById(R.id.course_info);
                bt_add=(Button)view.findViewById(R.id.bt_add);
            }
        }

}
