import java.awt.*;

public class Ball extends Sprite
{
    private static final int MIN_SIZE = 20;
    private static final int MAX_SIZE = 70;

    private static final float MIN_SPEED = 3f;
    private static final float MAX_SPEED = 30f;

    private Color color;
    private float vX;
    private float vY;


    Ball()
    {
        initBall();
    }


    Ball (int x, int y)
    {
        super.x = x;
        super.y = y;
        initBall();
    }


    // Инициализируем шарик
    private void initBall()
    {
        vX = (float)(MIN_SPEED + (Math.random() * (MAX_SPEED-MIN_SPEED)));
        vY = (float)(MIN_SPEED + (Math.random() * (MAX_SPEED-MIN_SPEED)));

        double maxTotalSpeed = Math.sqrt(Math.pow(MAX_SPEED,2) + Math.pow(MAX_SPEED,2));
        double totalSpeed = Math.sqrt(Math.pow(vX,2) + Math.pow(vY,2));
        double ratio = (maxTotalSpeed-totalSpeed)/maxTotalSpeed;

        // Размер завязываем на скорость: чем шарик больше, тем он медленнее
        halfHeight = MIN_SIZE + MAX_SIZE * (float)ratio;

        halfWidth = halfHeight;

        color = new Color(
                (int)(Math.random() * 255),
                (int)(Math.random() * 255),
                (int)(Math.random() * 255)
        );
    }


    // Отрисовываем шарик
    @Override
    void render(Stage stage, Graphics g)
    {
        g.setColor(color);
        g.fillOval( (int) getLeft(),
                    (int) getTop(),
                    (int) getWidth(),
                    (int) getHeight());
    }


    // Обновление данных шарика
    @Override
    void update(Stage stage, long deltaTime)
    {
        x += vX * deltaTime / 100;
        y += vY * deltaTime / 100;

        if (getRight() > stage.getRight())
        {
            setRight(stage.getRight());
            vX = -vX;
        }
        if (getLeft() < stage.getLeft())
        {
            setLeft(stage.getLeft());
            vX = -vX;
        }
        if (getTop() < stage.getTop())
        {
            setTop(stage.getTop());
            vY = -vY;
        }
        if (getBottom() > stage.getBottom())
        {
            setBottom(stage.getBottom());
            vY = -vY;
        }
    }


    // Проверяем принадлежность точки кругу.
    // Другие варианты формы в рассчет не беру, ибо это все-таки шарик.
    @Override
    boolean doesDotBelong (int x, int y)
    {
        int dX = Math.abs(x - (int)this.x);
        int dY = Math.abs(y - (int)this.y);

        double radius = Math.sqrt(Math.pow(dX,2) + Math.pow(dY,2));

        if (radius <= halfHeight)
            return true;

        return false;
    }
}
