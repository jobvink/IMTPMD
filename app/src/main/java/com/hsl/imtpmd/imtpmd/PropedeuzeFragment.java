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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hsl.imtpmd.imtpmd.adapters.SpecialisatievakkenAdapter;
import com.hsl.imtpmd.imtpmd.adapters.VerplichtevakkenAdapter;
import com.hsl.imtpmd.imtpmd.model.UserModel;
import com.hsl.imtpmd.imtpmd.model.UserSpecialisatievakModel;
import com.hsl.imtpmd.imtpmd.model.UserVerplichtvakModel;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PropedeuzeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PropedeuzeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropedeuzeFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USER = "user";

    private UserModel user;

    private OnFragmentInteractionListener mListener;

    private ListView pHoofdListview;
    private ListView pSpecListView;

    public PropedeuzeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param user Parameter 1.
     * @return A new instance of fragment PropedeuzeFragment.
     */
    public static PropedeuzeFragment newInstance(String user) {
        PropedeuzeFragment fragment = new PropedeuzeFragment();
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
        Toast.makeText(getActivity(),"Welkom om Studievolg informatica",Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_propedeuse, container, false);
        pHoofdListview = view.findViewById(R.id.hoofdvakkenP);
        final ArrayList<UserVerplichtvakModel> verplichtvakModels = UserVerplichtvakModel.propedeuze(this.getContext(), user);
        ListAdapter la = new VerplichtevakkenAdapter(this.getContext(),
                android.R.layout.simple_list_item_1,
                verplichtvakModels);
        pHoofdListview.setAdapter(la);
        pHoofdListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PropedeuzeFragment.this.getContext(), CijferInvoeren.class);
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
        pSpecListView = view.findViewById(R.id.KeuzeP);
        final ArrayList<UserSpecialisatievakModel> specialisatievakModels = UserSpecialisatievakModel.propedeuze(this.getContext(), user);
        ListAdapter ls = new SpecialisatievakkenAdapter(this.getContext(),
                android.R.layout.simple_list_item_1,
                specialisatievakModels);
        pSpecListView.setAdapter(ls);
        pSpecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PropedeuzeFragment.this.getContext(), CijferInvoeren.class);
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
    public void onResume(){
        super.onResume();
        final ArrayList<UserVerplichtvakModel> verplichtvakModels = UserVerplichtvakModel.propedeuze(this.getContext(), user);
        ListAdapter la = new VerplichtevakkenAdapter(this.getContext(),
                android.R.layout.simple_list_item_1,
                verplichtvakModels);
        pHoofdListview.setAdapter(la);
        pHoofdListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PropedeuzeFragment.this.getContext(), CijferInvoeren.class);
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
        ArrayList<UserSpecialisatievakModel> temp = UserSpecialisatievakModel.propedeuze(this.getContext(), user);

        if(!user.getSpecialisatie().equals("null")){
            temp = UserSpecialisatievakModel.specialisatieFilter(temp, user.getSpecialisatie());
        }

        final ArrayList<UserSpecialisatievakModel> specialisatievakModels = temp;

        ListAdapter ls = new SpecialisatievakkenAdapter(this.getContext(),
                android.R.layout.simple_list_item_1,
                specialisatievakModels);
        pSpecListView.setAdapter(ls);
        pSpecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(PropedeuzeFragment.this.getContext(), CijferInvoeren.class);
                Bundle b = new Bundle();
                UserSpecialisatievakModel vak = specialisatievakModels.get(position);
                b.putString("id", vak.getSpecialisatievakModel().getId());
                b.putString("type", "v");
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
