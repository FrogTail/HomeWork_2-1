import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Stage extends JPanel
{
    // Чисто визуально для быстро летящих шариков 60 - маловато,
    // либо у меня где-то ошибка и пауза считается некорректно
    // Ну, либо это глюки восприятия ))
    private static final int FPS = 120;

    int x = 0;
    long lastFrameTime;
    private BubbleGame gameController;
    private BackGround backGround;


    Stage (BubbleGame gameController)
    {
        lastFrameTime = System.nanoTime();

        backGround = new BackGround();

        this.gameController = gameController;

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                super.mousePressed(e);
                gameController.checkClick(e.getX(),e.getY());
            }
        });
    }


    // Оверрайдим существующий метод отрисовки
    // Насколько я понял - для того, чтобы получить цикл
    @Override
    protected void paintComponent(Graphics g)
    {
        // Смотрим, сколько прошло времени с момента последней рисовки
        long startPaintingTime = System.nanoTime();
        long deltaTime = (startPaintingTime-lastFrameTime)/1000000;

        // Вызываем изначальный метод отрисовки, чтобы ничего не поломалось
        super.paintComponent(g);

        // Рисуемся с учетом изменений за прошедшее время
        gameController.onDrawFrame(this, g, deltaTime);

        setBackground(backGround.getCorrectColor());

        // Записываем время окончания отрисовки
        lastFrameTime = System.nanoTime();

        // Смотрим, сколько заняла отрисовка
        deltaTime = (int)(lastFrameTime-startPaintingTime)/1000000;


        // Рассчитываем длинну паузы
        long pause;

        if (deltaTime <= (1000/FPS))
            pause = 1000/FPS - deltaTime;
        else
            pause = 0;


        // Пауза
        try
        {
            Thread.sleep(pause);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        // Создаем цикл или рекурсию вызывая метод перерисовки
        // опосредованно через добавление команды "перерисоваться"
        // в лист действий окна
        repaint();
    }





    // Геттеры
    public int getLeft()
    { return 0; }

    public int getRight()
    { return getWidth() - 1; }

    public int getTop()
    { return 0; }

    public int getBottom()
    { return getHeight() - 1; }
}
