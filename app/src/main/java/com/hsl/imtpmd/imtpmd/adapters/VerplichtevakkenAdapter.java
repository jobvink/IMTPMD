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
import com.hsl.imtpmd.imtpmd.model.Vak;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Job Vink on 23-6-2017.
 */

public class VerplichtevakkenAdapter extends ArrayAdapter<UserVerplichtvakModel> {

    public VerplichtevakkenAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<UserVerplichtvakModel> objects) {
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
            vh.cijfer = (TextView) convertView.findViewById(R.id.subject_cijfer);
            vh.behaald = (TextView) convertView.findViewById(R.id.subject_behaald);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        UserVerplichtvakModel cm = getItem(position);
        if (cm != null) {
            vh.name.setText(String.format("%s%s", "EC: ", cm.getVerplichtvak().getEc()));
            vh.code.setText((CharSequence) cm.getVerplichtvak().getCode());
            if (cm.getCijfer() != 0.0)  {
                vh.cijfer.setText(String.format("%s%s", "cijfer: ", Double.toString(cm.getCijfer())));
            } else {
                vh.cijfer.setText("Geen cijfer");
            }
            if (cm.getCijfer() >= 5.5){
                vh.behaald.setText("behaald: ja");
            } else {
                vh.behaald.setText("behaald: nee");
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView code;
        TextView cijfer;
        TextView behaald;
    }
}
