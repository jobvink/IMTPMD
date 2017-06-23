package com.hsl.imtpmd.imtpmd.adapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hsl.imtpmd.imtpmd.R;
import com.hsl.imtpmd.imtpmd.model.Model;
import com.hsl.imtpmd.imtpmd.model.UserKeuzevakModel;
import com.hsl.imtpmd.imtpmd.model.UserVerplichtvakModel;

import java.util.List;

/**
 * Created by Job Vink on 23-6-2017.
 */

public class VakkenAdapter extends ArrayAdapter<Model> {


    public VakkenAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public VakkenAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public VakkenAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull UserVerplichtvakModel[] objects) {
        super(context, resource, objects);
    }

    public VakkenAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull UserVerplichtvakModel[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public VakkenAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<UserVerplichtvakModel> objects) {
        super(context, resource, objects);
    }

    public VakkenAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<UserVerplichtvakModel> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override    
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;        
        if (convertView == null ) {
            vh = new ViewHolder();
            LayoutInflater li = LayoutInflater.from(getContext());
            convertView = li.inflate(R.layout.vakken_list, parent, false);
            vh.name = (TextView) convertView.findViewById(R.id.subject_name);
            vh.code = (TextView) convertView.findViewById(R.id.subject_code);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Model cm = getItem(position);
        vh.name.setText((CharSequence) cm.ge());
        vh.code.setText((CharSequence) cm.getEcts());
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView code;
    }
}
