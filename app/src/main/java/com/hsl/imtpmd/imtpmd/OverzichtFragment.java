package com.hsl.imtpmd.imtpmd;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.hsl.imtpmd.imtpmd.model.UserModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OverzichtFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OverzichtFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverzichtFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USER = "user";

    private UserModel user;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private BarChart chart;
    float barWidth;
    float barSpace;
    float groupSpace;

    public OverzichtFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @return A new instance of fragment OverzichtFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OverzichtFragment newInstance(String user) {
        OverzichtFragment fragment = new OverzichtFragment();
        Bundle args = new Bundle();
        args.putString(USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = UserModel.getUser(getContext(), getArguments().getString(USER));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_punten_overzicht, container, false);

        barWidth = 0.5f;
        barSpace = 0f;
        groupSpace = 0.6f;

        chart = (BarChart) view.findViewById(R.id.barChart);
        chart.setDescription(null);
        chart.setPinchZoom(true);
        chart.setScaleEnabled(true);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        int groupCount = 2;

        ArrayList xVals = new ArrayList();

        xVals.add("Propedeuse");
        xVals.add("Hoofdfase 1");

        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();

        int propedeuze = user.getPropedeuze_ec();
        int bachalor = user.getHoofdfase_ec();

        yVals1.add(new BarEntry(1, (float) 60));
        yVals2.add(new BarEntry(1, (float) propedeuze));
        yVals1.add(new BarEntry(2, (float) 180));
        yVals2.add(new BarEntry(2, (float) bachalor));



        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1, "Punten totaal");
        set1.setColor(Color.GRAY);
        set2 = new BarDataSet(yVals2, "Punten behaald");
        set2.setColor(Color.BLUE);
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
