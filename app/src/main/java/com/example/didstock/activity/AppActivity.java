package com.example.didstock.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.didstock.R;
import com.example.didstock.databinding.ActivityAppBinding;
import com.example.didstock.fragment.CollaboratorsListFragment;
import com.example.didstock.fragment.ReferencesListFragment;
import com.example.didstock.fragment.SettingsFragment;
import com.example.didstock.fragment.SuppliersListFragment;
import com.example.didstock.fragment.ListManagementFragment;
import com.google.android.material.navigation.NavigationView;

public class AppActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityAppBinding binding;

    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // place the app name or the options menu in the toolbar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        // set the toggle button for the drawer navigation on the toolbar
        drawer = binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // set the navigation view menu item listener
        navigationView = binding.navView;
        navigationView.setNavigationItemSelectedListener(this);

        // init the fragment container with a specific fragment the first time the activity is created
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListManagementFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_list_management);
        }
    }

    @Override
    public void onBackPressed() {
        // if drawer on right screen, then GravityCompat.END
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_list_management:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListManagementFragment()).commit();
                break;
            case R.id.nav_scanner:
                Toast.makeText(this, "Scanner non implémenté", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_references:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReferencesListFragment()).commit();
                break;
            case R.id.nav_suppliers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SuppliersListFragment()).commit();
                break;
            case R.id.nav_collaborators:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CollaboratorsListFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}