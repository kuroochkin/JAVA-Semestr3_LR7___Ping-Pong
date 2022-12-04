import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Logic extends JPanel implements ActionListener, KeyListener, Runnable {
    Timer time;
    Image image;
    Graphics graphics;
    Thread gameThread; // поле потока объекта Logic
    Thread Racket1T; // поле потока первой ракетки в Logic
    Thread Racket2T; // поле потока второй ракетки в Logic
    Racket racket1; // поле объекта первой ракетки в Logic
    Racket racket2; // поле объекта второй ракетки в Logic

    Ball ball; // поле объекта мяча

    int score1 = 0;
    int score2 = 0;

    static final int totalScore = 3;

    public Logic() {
        addKeyListener(this);
        newPlayers(); // Создаем ракетки
        newBall(); // Создаем мяч

        gameThread = new Thread(this); // Создаем поток Logic
        gameThread.start(); // Запускаем поток Logic

        time = new Timer(10, this);
        this.setFocusable(true);
        time.start(); // Добавляем возможность использовать клавиши
    }

    public void newPlayers() // Метод создания ракеток и запуска их потоков
    {
        racket1 = new Racket(0, 120, 10, 100, 1);
        racket2 = new Racket(575, 120, 10, 100, 2);
        Racket1T = new Thread((Runnable) racket1); // Создаем поток первой ракетки
        Racket2T = new Thread((Runnable) racket2); // Создаем поток второй ракетки
        Racket1T.start();
        Racket2T.start();
    }

    public void newBall() // Метод создания мяча и его потока
    {
        ball = new Ball(285, 150, 10, 10);
    }

    public void paint(Graphics g) {
        image = createImage(600, 400);
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g)
    {
        racket1.draw(g);
        racket2.draw(g);
        ball.draw(g);
        g.drawLine(290, 0, 290, 400);
        Font f = new Font("Arial", Font.BOLD, 25);
        g.setFont(f);
        g.drawString(String.valueOf(score1), 250, 30);
        g.drawString(String.valueOf(score2), 315, 30);

        if(score1 == totalScore)
        {
            g.drawString("Игрок_1 победил!", 50,170);
            g.drawString("Заново: Enter", 70, 300);
            time.stop();
            restart();
        }
        if(score2 == totalScore)
        {
            g.drawString("Игрок_2 победил!", 330,170);
            g.drawString("Заново: Enter", 355, 300);
            time.stop();
            restart();
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        ball.x += ball._x; // Перемещаем мячик по оси x
        ball.y += ball._y; // Перемещаем мячик по оси y
        if (ball.y < 0)
            ball._y = -ball._y;
        if (ball.y > 350)
            ball._y = -ball._y;

        if (new Rectangle(ball.x, ball.y, 10, 10).intersects(new Rectangle(572, racket2.y, 10, 100))) {
            ball._x = -ball._x;
        }
        if (new Rectangle(ball.x, ball.y, 10, 10).intersects(new Rectangle(0, racket1.y, 10, 100))) {
            ball._x = -ball._x;
        }
        if (ball.x < -20) {
            score2++;
            time.stop();
            ball.x = 285;
            ball.y = 150;
        }
        if (ball.x > 620) {
            score1++;
            time.stop();
            ball.x = 285;
            ball.y = 150;
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            racket2.y += 5;
            if (racket2.y >= 262)
                racket2.y = 262;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            racket2.y -= 5;
            if (racket2.y < 0)
                racket2.y = 0;
        }
        if ((e.getKeyCode() == KeyEvent.VK_W)) {
            racket1.y -= 5;
            if (racket1.y < 0)
                racket1.y = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            racket1.y += 5;
            if (racket1.y >= 262)
                racket1.y = 262;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            time.start();
        }
    }

    public void restart() {
        score1 = 0;
        score2 = 0;
        newPlayers();
        newBall();
    }

    @Override
    public void run()
    {
        if (score1 == totalScore)
        {
            int answer = JOptionPane.showConfirmDialog(null, "Winner - Player 1!!!\n" +
                    "Restart?", "Finish", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                restart();
                run();
            } else
                System.exit(0);
        }

        if (score2 == totalScore) {
            int answer = JOptionPane.showConfirmDialog(null, "Winner - Player 2!!!\n" +
                    "Restart?", "Finish", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                restart();
                run();
            }
            else
                System.exit(0);
        }
    }
}


