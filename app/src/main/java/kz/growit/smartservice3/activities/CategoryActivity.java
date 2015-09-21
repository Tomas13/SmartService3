package kz.growit.smartservice3.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import kz.growit.smartservice3.R;
import kz.growit.smartservice3.adapters.CategoryRVAdapter;
import kz.growit.smartservice3.models.Category;
import kz.growit.smartservice3.singleton.AppController;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.categoriesRV);
        ArrayList<Category> categories = AppController.getInstance().getCategories();
        ArrayList<Drawable> icons = new ArrayList<>();

        icons.add(getResources().getDrawable(R.drawable.cat1));
        icons.add(getResources().getDrawable(R.drawable.cat2));
        icons.add(getResources().getDrawable(R.drawable.cat3));
        icons.add(getResources().getDrawable(R.drawable.cat4));
        icons.add(getResources().getDrawable(R.drawable.cat5));
        icons.add(getResources().getDrawable(R.drawable.cat6));
        icons.add(getResources().getDrawable(R.drawable.cat7));
        icons.add(getResources().getDrawable(R.drawable.cat8));
        icons.add(getResources().getDrawable(R.drawable.cat9));
        icons.add(getResources().getDrawable(R.drawable.cat10));
        icons.add(getResources().getDrawable(R.drawable.cat11));
        icons.add(getResources().getDrawable(R.drawable.cat12));
        icons.add(getResources().getDrawable(R.drawable.cat13));
        icons.add(getResources().getDrawable(R.drawable.cat14));
        icons.add(getResources().getDrawable(R.drawable.cat15));

        for (int i = 0; i < icons.size(); i++) {
            icons.get(i).setColorFilter(new
                    PorterDuffColorFilter(Color.parseColor("#FFFFFFFF"), PorterDuff.Mode.SRC_ATOP));
        }

        CategoryRVAdapter myAdapter = new CategoryRVAdapter(categories, icons);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(llm);

        recyclerView.setAdapter(myAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
