package com.huaji.phonetester;

import android.content.DialogInterface;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.LinearLayout;

import android.widget.TextView;

import android.widget.Toast;



import androidx.appcompat.app.AlertDialog;

import androidx.recyclerview.widget.RecyclerView;



import org.litepal.LitePal;



import java.io.File;

import java.util.List;





public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<History> mList;



    public class ViewHolder extends RecyclerView.ViewHolder{



        TextView ScoreTextView;
        TextView TypeTextView;
        TextView BBTextView;
        TextView TimeTextView;
        LinearLayout linearLayout;



        public ViewHolder(View view){

            super(view);

            ScoreTextView = view.findViewById(R.id.score);
            TypeTextView = view.findViewById(R.id.type);
            BBTextView = view.findViewById(R.id.BB);
            TimeTextView = view.findViewById(R.id.time);
            linearLayout = view.findViewById(R.id.layout);



        }

    }



    //传入展示数据,并且赋值给全局变量

    public RecyclerViewAdapter(List<History> List){

        mList=List;

    }



    //创建ViewHolder实例,将item布局加载出来

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        ViewHolder holder=new ViewHolder(view);

        return holder;

    }



    //对RecyclerView的子项数据进行赋值

    @Override

    public void onBindViewHolder(final ViewHolder holder, final int position) {

        History history = mList.get(position);
        String type="跑分类型：";
        String BB= history.getBB();
        String time = history.getTime();
        if (history.getType()==0)
        {
            type = "CPU";
        }
        if (history.getType()==1)
        {
            type =  "内存";
        }

        holder.ScoreTextView.setText(String.valueOf(history.getScore()));
        holder.TypeTextView.setText("类型:" +type);
        holder.BBTextView.setText(BB);
        holder.TimeTextView.setText(time);



        //点击事件

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(final View v) {

                int position=holder.getAdapterPosition();

                 History history= mList.get(position);

                final int i = position;





                //Toast.makeText(v.getContext(),history.getScore() + "当前位置" + position,Toast.LENGTH_SHORT).show();

                //LitePal.deleteAll(History.class, "name = ?" ,history.getScore() );





            }

        });

    }

    //获取RecyclerView有多少子项

    @Override

    public int getItemCount() {

        return mList.size();

    }

}

