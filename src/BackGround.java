import java.awt.*;

public class BackGround
{
    private static final int sameColorTime = 50;

    private int[] colorComponents = {255,255,255};
    private int[] colorShifts = {1,5,8};

    Color color = new Color (255,255,255);
    long lastChangeTime = System.nanoTime();

    BackGround ()
    {

    }


    // Получить значение цвета соответсвтующее текущему времени
    public Color getCorrectColor ()
    {

        long currentTime = System.nanoTime();
        long deltaTime = (currentTime-lastChangeTime)/1_000_000;

        if (deltaTime>=sameColorTime)
        {
            shiftColors();
            lastChangeTime = System.nanoTime();
        }

        return color;
    }


    // Сдвигаем значение компонентов цвета
    public void shiftColors ()
    {
        for (int i=0; i<colorComponents.length; i++)
        {
            colorComponents[i] += colorShifts[i];

            if (colorComponents[i] < 0)
                colorComponents[i] = 0;

            if (colorComponents[i] > 255)
                colorComponents[i] = 255;

            if (colorComponents[i] == 255 || colorComponents[i] == 0)
                colorShifts[i] *= (-1);
        }

        color = new Color (colorComponents[0], colorComponents[1], colorComponents[2]);
    }


    // Геттер
    public Color getColor ()
    {
        return color;
    }
}
