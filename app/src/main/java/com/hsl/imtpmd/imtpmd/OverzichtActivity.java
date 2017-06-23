package com.hsl.imtpmd.imtpmd;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;

import com.hsl.imtpmd.imtpmd.model.UserModel;

public class OverzichtActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PropedeuzeFragment.OnFragmentInteractionListener,
        Hoofdfase1Fragment.OnFragmentInteractionListener,
        Hoofdfase34Fragment.OnFragmentInteractionListener,
        OverzichtFragment.OnFragmentInteractionListener,
        SpecialisatieFragment.OnFragmentInteractionListener{

    private UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overzicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle bundle = getIntent().getExtras();
        PropedeuzeFragment fragment = PropedeuzeFragment.newInstance(bundle.getString("user"));

        user = UserModel.getUser(getApplicationContext(), getIntent().getExtras().getString("user"));

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
                ft.replace(R.id.overpichtContent, fragment);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Propedeuse) {
            PropedeuzeFragment fragment = PropedeuzeFragment.newInstance(getIntent().getExtras().getString("user"));
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
//            ft.replace(R.id.overpichtContent, fragment);
            ft.replace(R.id.overpichtContent, fragment);
            // Complete the changes added above
            ft.commit();
        } else if (id == R.id.Hoofdfase1) {
            Hoofdfase1Fragment fragment = Hoofdfase1Fragment.newInstance(getIntent().getExtras().getString("user"));
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
//            ft.replace(R.id.overpichtContent, fragment);
            ft.replace(R.id.overpichtContent, fragment);
            // Complete the changes added above
            ft.commit();
        } else if (id == R.id.Hoofdfase23) {
            Hoofdfase34Fragment fragment = Hoofdfase34Fragment.newInstance(getIntent().getExtras().getString("user"));
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
//            ft.replace(R.id.overpichtContent, fragment);
            ft.replace(R.id.overpichtContent, fragment);
            // Complete the changes added above
            ft.commit();
        } else if (id == R.id.Puntenoverzicht) {
            OverzichtFragment fragment = OverzichtFragment.newInstance(getIntent().getExtras().getString("user"));
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
//            ft.replace(R.id.overpichtContent, fragment);
            ft.replace(R.id.overpichtContent, fragment);
            // Complete the changes added above
            ft.commit();
        } else if (id == R.id.Specialisatiekeuze) {
            SpecialisatieFragment fragment = SpecialisatieFragment.newInstance(getIntent().getExtras().getString("user"));

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.overpichtContent, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle("Studievolg informatica");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        Log.d("Test", "Test");
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.bdamradiobutton:
                if (checked)
                    // spec 2
                    user.setSpecialisatie(getApplicationContext(), "2");
                break;
            case R.id.fictradiobutton:
                if (checked)
                    // spec 1
                    user.setSpecialisatie(getApplicationContext(), "3");
                break;
            case R.id.mtradiobutton:
                if (checked)
                    //spec 4
                    user.setSpecialisatie(getApplicationContext(), "1");
                break;
            case R.id.seradiobutton:
                if (checked)
                    //spec 3
                    user.setSpecialisatie(getApplicationContext(), "4");
                break;
        }
    }
}
