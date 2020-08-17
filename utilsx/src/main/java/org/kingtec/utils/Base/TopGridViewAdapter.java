//package org.kingtec.utils.Base;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.support.design.widget.FloatingActionButton;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.myapp.ejasatk.R;
//
//import java.util.List;
//
///**
// * Created by Administrator on 26/03/2019.
// */
//
//
//public class TopGridViewAdapter extends BaseAdapter {
//    List<Tag> tagList;
//
//    private LayoutInflater inflater;
//    private Context context;
//
//    public TopGridViewAdapter(Context context, List<Tag> tags) {
//        this.inflater = LayoutInflater.from(context);
//        this.tagList = tags;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return tagList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @SuppressLint({"ResourceAsColor", "SetTextI18n", "InflateParams"})
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        final ViewHolder holder;
//        Tag tag =tagList.get(position);
//        if (convertView == null) {
//            holder = new ViewHolder();
//            convertView = this.inflater.inflate(R.layout._row_grad_top_item, null);
//            holder.tagImage = convertView.findViewById(R.id.tag_image);
//            holder.tagName = convertView.findViewById(R.id.tag_name);
//            holder.tagCount = convertView.findViewById(R.id.tag_count);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        View finalConvertView = convertView;
//        convertView.setOnClickListener(v -> {
//            if(tag.getOnObjectClickListener()!=null)
//                tag.getOnObjectClickListener().onClicked(tag);
//        });
//        holder.tagImage.setOnClickListener(v -> {
//            if(tag.getOnObjectClickListener()!=null)
//                tag.getOnObjectClickListener().onClicked(tag);
//        });
//        holder.tagImage.setImageResource(tag.getImage());
////        holder.tagImage.setBackgroundTintList(context.getResources().getColorStateList((tag.getColor()> Record0Manager.TAG_COLOR.length)?5:RecordManager.TAG_COLOR[tag.color]));
//        holder.tagCount.setText(tag.badge+"");
//
//        holder.tagName.setText(tag.getName());
//
//        return convertView;
//    }
//
//    private class ViewHolder {
//        FloatingActionButton tagImage;
//        TextView tagName;
//        TextView tagCount;
//    }
//}
