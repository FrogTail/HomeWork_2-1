// Java 2, HomeWork 1 by FrogTail

import javax.swing.*;
import java.awt.*;

public class BubbleGame extends JFrame
{
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private Stage stage;
    private Sprite[] sprites = new Sprite[10];


    public static void main (String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BubbleGame();
            }
        });
    }


    BubbleGame()
    {
        initWindow();
        buildBalls();
    }


    private void initWindow ()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Bubble Game");
        setVisible(true);

        stage = new Stage(this);
        add(stage);
    }


    private void buildBalls ()
    {
        for (int i=0; i<sprites.length; i++)
        {
            sprites[i] = new Ball();
        }
    }


    // Создание кадра
    void onDrawFrame(Stage stage, Graphics g, long deltaTime)
    {
        update(stage, deltaTime);
        render(stage, g);
    }


    // Обновление данных спрайтов
    private void update(Stage stage, long deltaTime)
    {
        for (int i = 0; i < sprites.length; i++)
        {
            sprites[i].update(stage, deltaTime);
        }
    }


    // Отрисовка спрайтов
    private void render(Stage stage, Graphics g)
    {
        for (int i = 0; i < sprites.length; i++)
        {
            sprites[i].render(stage, g);
        }
    }


    // Проверяем, что должно произойти по клику
    public void checkClick (int x, int y)
    {
        for (int i=sprites.length-1; i >=0; i--)
        {
            if(sprites[i].doesDotBelong(x,y))
            {
                deleteBall(i);
                return;
            }
        }

        addBall(x,y);
    }


    // Удаляем шарик по номеру
    private void deleteBall (int ballNumber)
    {
        Sprite[] newArray = new Sprite[sprites.length-1];

        int j = 0;
        for (int i=0; i<sprites.length; i++)
        {
            if (i!=ballNumber)
            {
                newArray[j]=sprites[i];
                j++;
            }
        }

        sprites = newArray;
    }


    // Добавляем шарик в точку клика
    private void addBall (int x, int y)
    {
        Sprite[] newArray = new Sprite[sprites.length+1];

        newArray[0] = new Ball(x,y);

        for (int i=0; i<sprites.length; i++)
        {
            newArray[i+1]=sprites[i];
        }

        sprites = newArray;
    }
}
