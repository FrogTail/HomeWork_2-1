import java.awt.*;

public class Sprite
{
    protected float x;
    protected float y;
    protected float halfWidth;  // А здесь зачем флоат?
    protected float halfHeight;


    // Геттеры размера
    protected float getWidth()
    {return 2f * halfWidth;}

    protected float getHeight()
    {return 2f * halfHeight;}


    // Геттеры координат границ
    protected float getLeft()
    {return x - halfWidth;}

    protected float getRight()
    {return x + halfWidth;}

    protected float getTop()
    {return y - halfHeight;}

    protected float getBottom()
    {return y + halfHeight;}


    // Сеттеры координат центра с рассчетом от краев
    protected void setLeft(float left)
    {x = left + halfWidth;}

    protected void setRight(float right)
    {x = right - halfWidth;}

    protected void setTop(float top)
    {y = top + halfHeight;}

    protected void setBottom(float bottom)
    {y = bottom - halfHeight;}


    void update(Stage stage, long deltaTime) {}
    void render(Stage stage, Graphics g) {}
    boolean doesDotBelong (int x, int y) {return false;}
}
