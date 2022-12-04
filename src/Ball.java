import java.awt.*;

public class Ball extends Rectangle implements Runnable
{

    public int _x = 3; // Смещение по оси x
    public int _y = 3; // Смещение по оси y

    public Ball(int x, int y, int width, int height)
    {
        super(x,y,width,height);
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(x,y,width,height);
    }

    @Override
    public void run() {

    }
}
