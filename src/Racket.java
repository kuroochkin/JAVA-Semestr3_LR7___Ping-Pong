import javax.swing.*;
import java.awt.*;

public class Racket extends Rectangle implements Runnable {
    int id;


    public Racket(int x, int y, int width, int height, int id)
    {
        super(x, y, width, height);
        this.id = id;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x,y,width,height);
    }

    @Override
    public void run() {

    }
}
