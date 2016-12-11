package ismael.com.ejedeportes.repository;

import java.util.ArrayList;
import java.util.List;

import ismael.com.ejedeportes.R;
import ismael.com.ejedeportes.model.Sport;

/**
 * Created by ismael on 10/12/16.
 */
public class SportsRep{
    private static SportsRep newInstance;
    ArrayList sportList;

    public static SportsRep getInstance() {

        if(newInstance == null)
            newInstance = new SportsRep();
        return newInstance;
    }


    private SportsRep() {
        sportList = new ArrayList();
        addSport(new Sport(1,"Baloncesto", R.drawable.basketball_icon, false));
        addSport(new Sport(2,"Fútbol", R.drawable.football_icon, false));
        addSport(new Sport(3,"Motociclismo", R.drawable.moto_icon, false));
        addSport(new Sport(4,"Natación", R.drawable.swim_icon, false));
        addSport(new Sport(5,"Golf", R.drawable.golf_icon, false));
        addSport(new Sport(6,"Atletismo", R.drawable.ath_icon, false));
        addSport(new Sport(7,"Ajedrez", R.drawable.chess_icon, false));
        addSport(new Sport(8,"BasketBall", R.drawable.basketball_icon, false));
        addSport(new Sport(9,"Americano", R.drawable.football_icon, false));
        addSport(new Sport(10,"COches", R.drawable.moto_icon, false));
        addSport(new Sport(11,"Arlequin", R.drawable.swim_icon, false));
        addSport(new Sport(12,"ibiza", R.drawable.golf_icon, false));
        addSport(new Sport(13,"bilbao", R.drawable.ath_icon, false));
        addSport(new Sport(14,"queso", R.drawable.chess_icon, false));
    }

    public ArrayList getSports(){
        return this.sportList;
    }

    public void addSport(Sport sport){
        sportList.add(sport);
    }


}
