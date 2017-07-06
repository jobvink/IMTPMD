package com.hsl.imtpmd.imtpmd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.hsl.imtpmd.imtpmd.adapters.KeuzevakkenAdapter;
import com.hsl.imtpmd.imtpmd.adapters.SpecialisatievakkenAdapter;
import com.hsl.imtpmd.imtpmd.adapters.VerplichtevakkenAdapter;
import com.hsl.imtpmd.imtpmd.model.KeuzevakModel;
import com.hsl.imtpmd.imtpmd.model.UserKeuzevakModel;
import com.hsl.imtpmd.imtpmd.model.UserModel;
import com.hsl.imtpmd.imtpmd.model.UserSpecialisatievakModel;
import com.hsl.imtpmd.imtpmd.model.UserVerplichtvakModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Hoofdfase1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Hoofdfase1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Hoofdfase1Fragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USER = "user";

    private UserModel user;

    private Hoofdfase1Fragment.OnFragmentInteractionListener mListener;

    private ListView hoofdvakkenh1;
    private ListView KeuzeH1;
    private ListView SpecialisatieH1;

    public Hoofdfase1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @return A new instance of fragment Hoofdfase1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Hoofdfase1Fragment newInstance(String user) {
        Hoofdfase1Fragment fragment = new Hoofdfase1Fragment();
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
        View view = inflater.inflate(R.layout.content_hoofdfase1, container, false);

        hoofdvakkenh1 = view.findViewById(R.id.hoofdvakkenh1);
        KeuzeH1 = view.findViewById(R.id.KeuzeH1);
        SpecialisatieH1 = view.findViewById(R.id.SpecialisatieH1);
        this.fillListViews();

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

    @Override
    public void onResume(){
        super.onResume();
        // Inflate the layout for this fragment
        this.fillListViews();
    }

    private void fillListViews(){
        final ArrayList<UserVerplichtvakModel> verplichtvakModels = UserVerplichtvakModel.hoofdfase1(this.getContext(), user);
        ListAdapter la = new VerplichtevakkenAdapter(this.getContext(),
                android.R.layout.simple_list_item_1,
                verplichtvakModels);
        hoofdvakkenh1.setAdapter(la);
        hoofdvakkenh1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Hoofdfase1Fragment.this.getContext(), CijferInvoeren.class);
                Bundle b = new Bundle();
                UserVerplichtvakModel vak = verplichtvakModels.get(position);
                b.putString("id", vak.getVerplichtvak().getId());
                b.putString("type", "v");
                b.putString("user", user.getGebruikersnaam());
                b.putString("code", vak.getVerplichtvak().getCode());
                b.putString("naam", vak.getVerplichtvak().getNaam());
                b.putDouble("cijfer", vak.getCijfer());
                b.putBoolean("behaald", vak.getBehaald());
                i.putExtras(b);
                startActivity(i);
            }
        });
        final ArrayList<UserKeuzevakModel> keuzevakModels = UserKeuzevakModel.all(this.getContext(), user);
        ListAdapter lk = new KeuzevakkenAdapter(this.getContext(),
                android.R.layout.simple_list_item_1,
                keuzevakModels);
        KeuzeH1.setAdapter(lk);
        KeuzeH1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Hoofdfase1Fragment.this.getContext(), CijferInvoeren.class);
                Bundle b = new Bundle();
                UserKeuzevakModel vak = keuzevakModels.get(position);
                b.putString("id", vak.getKeuzevak().getId());
                b.putString("type", "k");
                b.putString("user", user.getGebruikersnaam());
                b.putString("code", vak.getKeuzevak().getCode());
                b.putString("naam", vak.getKeuzevak().getNaam());
                b.putDouble("cijfer", vak.getCijfer());
                b.putBoolean("behaald", vak.getBehaald());
                i.putExtras(b);
                startActivity(i);
            }
        });

        ArrayList<UserSpecialisatievakModel> temp = UserSpecialisatievakModel.hoofdfase1(this.getContext(), user);

        if (user.getSpecialisatie() != null) {
            if (!user.getSpecialisatie().equals("null")) {
                temp = UserSpecialisatievakModel.specialisatieFilter(temp, user.getSpecialisatie());
            }
        }

        final ArrayList<UserSpecialisatievakModel> specialisatievakModels = temp;        ListAdapter ls = new SpecialisatievakkenAdapter(this.getContext(),
                android.R.layout.simple_list_item_1,
                specialisatievakModels);
        SpecialisatieH1.setAdapter(ls);
        SpecialisatieH1.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Hoofdfase1Fragment.this.getContext(), CijferInvoeren.class);
                Bundle b = new Bundle();
                UserSpecialisatievakModel vak = specialisatievakModels.get(position);
                b.putString("id", vak.getSpecialisatievakModel().getId());
                b.putString("type", "s");
                b.putString("user", user.getGebruikersnaam());
                b.putString("code", vak.getSpecialisatievakModel().getCode());
                b.putString("naam", vak.getSpecialisatievakModel().getNaam());
                b.putDouble("cijfer", vak.getCijfer());
                b.putBoolean("behaald", vak.getBehaald());
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}
