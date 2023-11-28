import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TriangleRotation extends Frame {
    private int angle = 0;
    private boolean rotatingClockwise = false;
    private boolean rotatingCounterClockwise = false;

    public TriangleRotation() {
        setSize(500, 500);
        setVisible(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rotatingClockwise = true;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    rotatingCounterClockwise = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rotatingClockwise = false;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    rotatingCounterClockwise = false;
                }
            }
        });

        new Thread(() -> {
            while (true) {
                if (rotatingClockwise) {
                    angle += 10;
                    repaint();
                } else if (rotatingCounterClockwise) {
                    angle -= 10;
                    repaint();
                }

                try {
                    Thread.sleep(10); // Задержка для обновления вращения
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }

    public void paint(Graphics g) {
        int[] xPoints = {200, 300, 250}; // координаты для треугольника
        int[] yPoints = {200, 200, 100};
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, getWidth(), getHeight());

        // Поворачиваем треугольник на угол angle
        g2d.rotate(Math.toRadians(angle), 250, 150);
        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints, yPoints, 3);
    }


}