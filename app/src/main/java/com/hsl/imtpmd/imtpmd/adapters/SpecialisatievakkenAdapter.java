package com.hsl.imtpmd.imtpmd.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hsl.imtpmd.imtpmd.R;
import com.hsl.imtpmd.imtpmd.model.UserSpecialisatievakModel;
import com.hsl.imtpmd.imtpmd.model.UserSpecialisatievakModel;

import java.util.ArrayList;

/**
 * Created by Job Vink on 23-6-2017.
 */

public class SpecialisatievakkenAdapter extends ArrayAdapter<UserSpecialisatievakModel> {

    public SpecialisatievakkenAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<UserSpecialisatievakModel> objects) {
        super(context, resource, objects);
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
        UserSpecialisatievakModel cm = getItem(position);
        vh.name.setText((CharSequence) cm.getSpecialisatievakModel().getEc());
        vh.code.setText((CharSequence) cm.getSpecialisatievakModel().getCode());
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView code;
    }
}
