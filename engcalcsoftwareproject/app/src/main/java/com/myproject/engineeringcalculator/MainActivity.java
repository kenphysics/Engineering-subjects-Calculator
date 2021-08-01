package com.myproject.engineeringcalculator;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Fragment;
import android.app.FragmentTransaction;
import com.myproject.NAcalculator.*;
public class MainActivity extends Activity {
    private String[] Calculators;
    private ListView calculatorlist;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private int currentpos=0;
    private class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectItem((int)l);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer=(DrawerLayout)findViewById(R.id.cal_drawer);
        Calculators=getResources().getStringArray(R.array.calculator_list);
        calculatorlist=(ListView)findViewById(R.id.navigation_id);
        calculatorlist.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,Calculators));
        calculatorlist.setOnItemClickListener(new DrawerItemClickListener());
        drawerToggle=new ActionBarDrawerToggle(this,drawer,R.string.open_drawer,R.string.close_drawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager fm=getFragmentManager();
                Fragment frag=fm.findFragmentByTag("current_displayed_fragment");
                if(frag instanceof ScientificCalculatorFragment)
                    currentpos=0;
                else if(frag instanceof DSPFragment||frag instanceof DSPconvFragment || frag instanceof DSPcconvFragment || frag instanceof DSPdftFragment || frag instanceof DSPfftFragment || frag instanceof DSPidftFragment || frag instanceof DSPifftFragment)
                    currentpos=1;
                else if(frag instanceof NAFragment||frag instanceof InterpolationFragment||frag instanceof IntegrationFragment||frag instanceof EquationSolverFragment)
                    currentpos=2;
                setActionBarTitle(currentpos);
                calculatorlist.setItemChecked(currentpos,true);
            }
        });
        drawer.setDrawerListener(drawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        if(savedInstanceState!=null) {
            currentpos = savedInstanceState.getInt("position", 0);
            getActionBar().setTitle(Calculators[currentpos]);
        }
        else
            selectItem(0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void selectItem(int pos)
    {
        currentpos=pos;
        Fragment frag=null;
        /*code for switching to other calculators*/
        switch (pos)
        {
            case 1: frag=new DSPFragment(); break;
            case 2: frag=new NAFragment(); break;
         //   case 3: break;
         //   case 4: break;
            default: frag=new ScientificCalculatorFragment(); break;
        }
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.temp_frame,frag,"current_displayed_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
        setActionBarTitle(pos);
        drawer.closeDrawer(calculatorlist);
    }
    private void setActionBarTitle(int pos)
    {
        getActionBar().setTitle(Calculators[pos]);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
        {
            return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",currentpos);
    }
}