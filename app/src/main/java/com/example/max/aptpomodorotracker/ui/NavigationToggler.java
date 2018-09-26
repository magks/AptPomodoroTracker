package com.example.max.aptpomodorotracker.ui;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

public class NavigationToggler extends ActionBarDrawerToggle  {

    public NavigationToggler(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar,
                             int openDrawerContentDescRes, int closeDrawerContentDescRes)
    {
        super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
    }

    public NavigationToggler(Activity activity, DrawerLayout drawerLayout,
                             int openDrawerContentDescRes, int closeDrawerContentDescRes)
    {
        super(activity, drawerLayout,  openDrawerContentDescRes, closeDrawerContentDescRes);
    }

    /* implements DrawerListener ?
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        // Respond when the drawer's position changes
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        // Respond when the drawer is opened
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        // Respond when the drawer is closed
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        // Respond when the drawer motion state changes
    }
*/
}
