package com.limpiezas.neteo.ui;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.limpiezas.neteo.R;
import com.limpiezas.neteo.ui.menu.MenuFragment;
import com.limpiezas.neteo.ui.menu.MenuParentView;

/**
 * Created by alavpa on 3/8/16.
 */
public class BaseToolbarActivity extends BaseActivity implements MenuParentView {

    public Toolbar toolbar;
    public DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;
    MenuFragment menuFragment;
    Navigator navigator = new Navigator();

    public void initMenu(int position){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        menuFragment = MenuFragment.getInstance(position);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu,menuFragment).commit();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);
        drawerLayout.setStatusBarBackground(R.color.colorPrimary);
        toggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_reservar){
            navigator.startReservas2(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hideMenu() {
        drawerLayout.closeDrawers();
    }
}
