import javax.swing.*;
import java.awt.*;

public class GamePanel
{

    public GamePanel()
    {
        JFrame gamepanel = new JFrame("Ping-Pong");
        Logic game = new Logic();
        gamepanel.add(game);
        gamepanel.setSize(600,400);
        gamepanel.setBackground(Color.BLACK);
        gamepanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamepanel.setVisible(true);
        gamepanel.setResizable(false);
    }
}
