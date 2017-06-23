package com.hsl.imtpmd.imtpmd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hsl.imtpmd.imtpmd.adapters.KeuzevakkenAdapter;
import com.hsl.imtpmd.imtpmd.adapters.SpecialisatievakkenAdapter;
import com.hsl.imtpmd.imtpmd.adapters.VerplichtevakkenAdapter;
import com.hsl.imtpmd.imtpmd.model.UserKeuzevakModel;
import com.hsl.imtpmd.imtpmd.model.UserModel;
import com.hsl.imtpmd.imtpmd.model.UserSpecialisatievakModel;
import com.hsl.imtpmd.imtpmd.model.UserVerplichtvakModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Hoofdfase34Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Hoofdfase34Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Hoofdfase34Fragment extends Fragment {
    private static final String USER = "user";

    private UserModel user;

    private OnFragmentInteractionListener mListener;

    private ListView hoofdvakkenh23;
    private ListView KeuzeH23;
    private ListView SpecialisatieH23;

    public Hoofdfase34Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @return A new instance of fragment Hoofdfase34Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Hoofdfase34Fragment newInstance(String user) {
        Hoofdfase34Fragment fragment = new Hoofdfase34Fragment();
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
        View view = inflater.inflate(R.layout.content_hoofdfase34, container, false);
        hoofdvakkenh23 = view.findViewById(R.id.hoofdvakkenh23);
        final ArrayList<UserVerplichtvakModel> verplichtvakModels = UserVerplichtvakModel.hoofdfase34(this.getContext(), user);
        ListAdapter la = new VerplichtevakkenAdapter(this.getContext(),
                android.R.layout.simple_list_item_1,
                verplichtvakModels);
        hoofdvakkenh23.setAdapter(la);
        hoofdvakkenh23.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Hoofdfase34Fragment.this.getContext(), CijferInvoeren.class);
                Bundle b = new Bundle();
                UserVerplichtvakModel vak = verplichtvakModels.get(position);
                b.putString("code", vak.getVerplichtvak().getCode());
                b.putString("naam", vak.getVerplichtvak().getNaam());
                b.putDouble("cijfer", vak.getCijfer());
                b.putBoolean("behaald", vak.getBehaald());
                i.putExtras(b);
                startActivity(i);
            }
        });
        KeuzeH23 = view.findViewById(R.id.KeuzeH23);
        final ArrayList<UserKeuzevakModel> keuzevakModels = UserKeuzevakModel.all(this.getContext(), user);
        ListAdapter lk = new KeuzevakkenAdapter(this.getContext(),
                android.R.layout.simple_list_item_1,
                keuzevakModels);
        KeuzeH23.setAdapter(lk);
        KeuzeH23.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Hoofdfase34Fragment.this.getContext(), CijferInvoeren.class);
                Bundle b = new Bundle();
                UserKeuzevakModel vak = keuzevakModels.get(position);
                b.putString("code", vak.getKeuzevak().getCode());
                b.putString("naam", vak.getKeuzevak().getNaam());
                b.putDouble("cijfer", vak.getCijfer());
                b.putBoolean("behaald", vak.getBehaald());
                i.putExtras(b);
                startActivity(i);
            }
        });

        SpecialisatieH23 = view.findViewById(R.id.SpecialisatieH23);
        final ArrayList<UserSpecialisatievakModel> specialisatievakModels = UserSpecialisatievakModel.hoofdfase34(this.getContext(), user);
        ListAdapter ls = new SpecialisatievakkenAdapter(this.getContext(),
                android.R.layout.simple_list_item_1,
                specialisatievakModels);
        SpecialisatieH23.setAdapter(ls);
        SpecialisatieH23.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Hoofdfase34Fragment.this.getContext(), CijferInvoeren.class);
                Bundle b = new Bundle();
                UserSpecialisatievakModel vak = specialisatievakModels.get(position);
                b.putString("code", vak.getSpecialisatievakModel().getCode());
                b.putString("naam", vak.getSpecialisatievakModel().getNaam());
                b.putDouble("cijfer", vak.getCijfer());
                b.putBoolean("behaald", vak.getBehaald());
                i.putExtras(b);
                startActivity(i);
            }
        });
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
