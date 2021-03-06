package tick_tack_toe.skilrex.ru.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe extends AppCompatActivity {


    private Game game;

    public class Listener implements View.OnClickListener
    {
        private int x = 0;
        private int y = 0;

        public Listener(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view)
        {
            Button button = (Button) view;
            Game g = game;
            Player player = g.getCurrentActivePlayer();
            if (makeTurn(x, y))
            {
                button.setText(player.getName());
            }
            Player winner = g.checkWinner();
            if (winner != null)
            {
                gameOver(winner);
            }
            if (g.isFieldFilled())
            {  // в случае, если поле заполнено
                gameOver();
            }
        }
    }

    private void gameOver(Player player)
    {
        CharSequence text = "Player \"" + player.getName() + "\" won!";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }

    private void gameOver()
    {
        CharSequence text = "Draw";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }

    private boolean makeTurn(int x, int y)
    {
        return game.makeTurn(x, y);
    }

    private void refresh()
    {
        Square[][] field = game.getField();

        for (int i = 0, len = field.length; i < len; i++)
        {
            for (int j = 0, len2 = field[i].length; j < len2; j++)
            {
                if (field[i][j].getPlayer() == null)
                {
                    buttons[i][j].setText("");
                }
                else
                {
                    buttons[i][j].setText(field[i][j].getPlayer().getName());
                }
            }
        }
    }

    private TableLayout tablelayout; // экземпляр нашего табличного макета

    private Button[][] buttons = new Button[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tablelayout = (TableLayout) findViewById(R.id.main_l);
        buildGameField(); // создание игрового поля

        TextView tvResult = (TextView)findViewById(R.id.textView1);

//Узнаем размеры экрана из ресурсов
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();

        //узнаём размеры экрана из класса Display
        Display display= getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);

        tvResult.setText("[Используя ресурсы]\n"+
                "Ширина: " + displaymetrics.widthPixels + "\n" +
                "Высота: " + displaymetrics.heightPixels + "\n"
                + "\n" +
                "[Используя Display] \n" +
                "Ширина: " + metricsB.widthPixels + "\n" +
                "Высота: " + metricsB.heightPixels + "\n");
    }

    public TicTacToe()
    {
        game = new Game();
        game.start(); // будет реализован позже
    }

    private void buildGameField()
    {
        Square[][] field = game.getField();
        for (int i = 0, lenI = field.length; i < lenI; i++ ) {
            TableRow row = new TableRow(this); // создание строки таблицы
            for (int j = 0, lenJ = field[i].length; j < lenJ; j++)
            {
                Button button = new Button(this);
                buttons[i][j] = button;
                button.setOnClickListener(new Listener(i, j)); // установка слушателя, реагирующего на клик по кнопке
                row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT)); // добавление кнопки в строку таблицы
                button.setWidth(240);
                button.setHeight(250);
            }
            tablelayout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT)); // добавление строки в таблицу
        }
    }

}
