package tick_tack_toe.skilrex.ru.tictactoe;

/**
 * Created by Stefan on 23.04.2017.
 */

public class WinnerCheckerDiagonalLeft implements WinnerCheckerInterface
{
    private Game game;

    public WinnerCheckerDiagonalLeft(Game game)
    {
        this.game = game;
    }

    public Player checkWinner()
    {
        Square[][] field = game.getField();
        Player currPlayer;
        Player lastPlayer = null;
        int successCounter = 1;
        for (int i = 0, len = field.length; i < len; i++)
        {
            currPlayer = field[i][i].getPlayer();
            if (currPlayer != null)
            {
                if (lastPlayer == currPlayer)
                {
                    successCounter++;
                    if (successCounter == len)
                    {
                        return currPlayer;
                    }
                }
            }
            lastPlayer = currPlayer;
        }
        return null;
    }
}
