package tick_tack_toe.skilrex.ru.tictactoe;

/**
 * Created by Stefan on 23.04.2017.
 */

public class Square
{
    private Player player = null;

    public void fill(Player player)
    {
        this.player = player;
    }

    public boolean isFilled()
    {
        if (player != null)
        {
            return true;
        }
        return false;
    }

    public Player getPlayer()
    {
        return player;
    }
}