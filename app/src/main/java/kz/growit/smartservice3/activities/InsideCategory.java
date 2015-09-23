package kz.growit.smartservice3.activities;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import kz.growit.smartservice3.R;
import kz.growit.smartservice3.fragments.DisplayListFragment;
import kz.growit.smartservice3.singleton.AppController;

public class InsideCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_category);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarInsideCategory);
        setSupportActionBar(toolbar);


        //NAVIGATION DRAWER Добавляем
        AppController.getInstance().getDrawer(this, toolbar, null);

        DisplayListFragment fragment = new DisplayListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.containerCategory, fragment).commit();


        //just some action on fab
        findViewById(R.id.insideCategoryFAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snack = Snackbar.make(view, "Hello Snackbar", Snackbar.LENGTH_LONG);
                View view1 = snack.getView();
                view1.setBackgroundColor(getResources().getColor(R.color.snack));

                snack.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inside_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
