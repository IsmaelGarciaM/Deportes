package ismael.com.ejedeportes.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.util.Pools;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ismael.com.ejedeportes.R;
import ismael.com.ejedeportes.model.Sport;
import ismael.com.ejedeportes.repository.SportsRep;

/**
 * Created by ismael on 10/12/16.
 */

public class SportsAdapter extends ArrayAdapter<Sport> {

    public final String PREFERENCES_KEY = "prefs";
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    public ArrayList<Sport> lista;

    public SportsAdapter(Context c){
        super(c, R.layout.sport_item, SportsRep.getInstance().getSports());
        context = c;
        sp = context.getSharedPreferences(PREFERENCES_KEY,Context.MODE_PRIVATE);
        ed = sp.edit();
        lista = new ArrayList<>();
        lista.addAll(getAll());
        loadPreferences();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = convertView;
        SportHolder sh;

        if(item == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.sport_item, null);

            sh = new SportHolder();
            sh.img = (ImageView)item.findViewById(R.id.imgSport);
            sh.txvName = (TextView) item.findViewById(R.id.txvNameSport);
            sh.selected = (CheckBox) item.findViewById(R.id.cbxSelected);
            item.setTag(sh);
        }
        else
            sh = (SportHolder) item.getTag();

        sh.id = getItem(position).getId();
        sh.img.setImageResource(getItem(position).getImage());
        sh.txvName.setText(getItem(position).toString());
        sh.selected.setChecked(getItem(position).isSelected());


        sh.selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v.findViewById(R.id.cbxSelected)).isChecked())
                    getItem(position).setSelected(true);
                else
                    getItem(position).setSelected(false);

            }
        });


        return item;
    }


    class SportHolder{
        int id;
        ImageView img;
        TextView txvName;
        CheckBox selected;
    }

    public ArrayList<Sport> getAll(){
        return SportsRep.getInstance().getSports();
    }

    public void savePreferences(){
        ed.clear();
        for (int i = 0; i < getCount(); i++){
            if(getItem(i).isSelected())
                ed.putString(String.valueOf(getItem(i).getId()), String.valueOf(getItem(i).getId()));
        }
        ed.commit();
    }

    public void loadPreferences(){
        for (int i = 0; i < getCount(); i++){
            if(sp.contains(String.valueOf(getItem(i).getId())))
                getItem(i).setSelected(true);
        }
    }

    public void filtrar(String query){

        ArrayList<Sport> tmp = new ArrayList<>();
        tmp.addAll(lista);
        clear();

        if(query.isEmpty()) {
            addAll(tmp);
        }
        else{
            Sport s;
            for(int i=0;i<tmp.size(); i++){
                s = tmp.get(i);
                if(s.getName().toLowerCase().startsWith(query.toLowerCase()))
                    add(s);
            }
        }
    }
}
