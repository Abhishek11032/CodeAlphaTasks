import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simple3DGameEngine extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private double rotationAngleX = 0.01;
    public Simple3DGameEngine() {
        super("Simple 3D Game Engine");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();

        setVisible(true);
    }

    private void update() {
        rotationAngleX += 0.01;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.translate(WIDTH / 2, HEIGHT / 2);
        g2d.rotate(rotationAngleX, 0, 0);

        drawCube(g2d);

        g2d.rotate(-rotationAngleX, 0, 0);
        g2d.translate(-WIDTH / 2, -HEIGHT / 2);
    }

    private void drawCube(Graphics2D g2d) {
        int size = 100;

        g2d.drawRect(-size / 2, -size / 2, size, size);
        g2d.drawRect(-size / 2, -size / 2, size, size);
        g2d.drawLine(-size / 2, -size / 2, -size / 2, -size / 2);
        g2d.drawLine(size / 2, -size / 2, size / 2, -size / 2);
        g2d.drawLine(-size / 2, size / 2, -size / 2, size / 2);
        g2d.drawLine(size / 2, size / 2, size / 2, size / 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Simple3DGameEngine();
            }
        });
    }
}
