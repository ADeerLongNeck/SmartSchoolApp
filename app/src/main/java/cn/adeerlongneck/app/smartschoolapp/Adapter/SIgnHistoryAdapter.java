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
import cn.adeerlongneck.app.smartschoolapp.Activity.HistoryInfoActivity;
import cn.adeerlongneck.app.smartschoolapp.Model.CourseModel;
import cn.adeerlongneck.app.smartschoolapp.Model.HistoryModel;
import cn.adeerlongneck.app.smartschoolapp.R;
import cn.adeerlongneck.app.smartschoolapp.Utility.HttpUtil;
import cn.adeerlongneck.app.smartschoolapp.View.TeacherSignView;

/**
 * Created by chao on 2018/5/10.
 */

public class SIgnHistoryAdapter extends RecyclerView.Adapter<SIgnHistoryAdapter.ViewHolder> {
    List<HistoryModel> list;
    TeacherSignView mmmview;
    int pos;
    Context context;
    public SIgnHistoryAdapter(List<HistoryModel> list,Context context) {
        this.list = list;
        this.context=context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HistoryModel historyModel = list.get(position);
        Log.d("2222", "2222222222");

        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,HistoryInfoActivity.class);
                intent.putExtra("random",historyModel.getRandoms());
                context.startActivity(intent);
            }
        });
        holder.tx_random.setText(historyModel.getRandoms());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    public void delClass(String id) {
//        Map<String, String> map = new HashMap<>();
//
//        map.put("id", id);
//
//        HttpUtil httpUtil = new HttpUtil(new HttpUtil.HttpResponse() {
//            @Override
//            public void onSuccess(Object object) {
//                Log.d("1", object.toString());
//                String data = object.toString();
//                mmmview.re();
//
//
//            }
//
//            @Override
//            public void onFail(String error) {
//                Log.d("1", "11111111113211111111111111111111111--------------------1" + error);
//
//            }
//        });
//        httpUtil.sendPostHttp("http://www.adeerlongneck.cn:8000/DelectClass", map);
//
//
//    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tx_random;
        View mview;

        public ViewHolder(View view) {

            super(view);
            mview=view;
            tx_random=(TextView)view.findViewById(R.id.random);
        }
    }
}
