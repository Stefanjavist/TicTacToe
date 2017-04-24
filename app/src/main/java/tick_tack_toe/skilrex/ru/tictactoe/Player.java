package tick_tack_toe.skilrex.ru.tictactoe;

/**
 * Created by Stefan on 23.04.2017.
 */

public class Player {
    private String name;

    public Player(String name)
    {
        this.name = name;
    }

    public CharSequence getName()
    {
        return (CharSequence) name;
    }

}

