package ismael.com.ejedeportes;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ismael.com.ejedeportes.adapter.SportsAdapter;
import ismael.com.ejedeportes.model.Sport;

public class SportList extends AppCompatActivity {

    SportsAdapter adapter;
    ListView sportList;
    Button btnOk;
    TextView emptyList;
    private  SearchView find;
    String searchText;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_list);

        sportList = (ListView) findViewById(R.id.sportsList);
        btnOk = (Button) findViewById(R.id.btnOk);
        adapter = new SportsAdapter(this);
        sportList.setAdapter(adapter);
        emptyList = (TextView) findViewById(R.id.txvEmpty);
        sportList.setEmptyView(emptyList);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.savePreferences();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.search_icon_hi);




    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        ArrayList<Sport> al = new ArrayList<>();
        ArrayList<Sport> listaCompleta = new ArrayList<>();
        listaCompleta.addAll(adapter.lista);
        al.addAll(adapter.getAll());
        outState.putParcelableArrayList("lista", al);
        outState.putInt("listPosition", sportList.getFirstVisiblePosition());
        outState.putString("search", String.valueOf(find.getQuery()));
        outState.putParcelableArrayList("listaCompleta", listaCompleta);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.clear();
        ArrayList<Sport> al = savedInstanceState.getParcelableArrayList("lista");
        adapter.addAll(al);
        adapter.lista.clear();
        adapter.lista.addAll(savedInstanceState.<Sport>getParcelableArrayList("listaCompleta"));
        sportList.setAdapter(adapter);
        sportList.setSelection(pos=savedInstanceState.getInt("listPosition"));
        searchText = savedInstanceState.getString("search");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        final MenuItem searchView = menu.findItem(R.id.app_bar_search);
        find = (SearchView) MenuItemCompat.getActionView(searchView);


        find.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filtrar(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filtrar(newText);
                return false;
            }
        });

        find.setQuery(searchText, false);
        if(sportList.getCount() > pos)
            sportList.setSelection(pos);

        return true;
    }


}
