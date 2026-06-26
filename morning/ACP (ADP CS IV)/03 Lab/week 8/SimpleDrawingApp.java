import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Task 3: Develop a simple drawing application with mouse events.
 */
public class SimpleDrawingApp extends JFrame {
    private final List<Point> points = new ArrayList<>();

    public SimpleDrawingApp() {
        setTitle("Simple Freehand Drawing Canvas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Drawing Panel Canvas
        DrawingPanel canvas = new DrawingPanel();
        canvas.setBackground(Color.WHITE);
        add(canvas, BorderLayout.CENTER);

        // Control Panel at Bottom
        JPanel controlPanel = new JPanel();
        JButton clearButton = new JButton("Clear Canvas");
        clearButton.addActionListener(e -> {
            points.clear();
            canvas.repaint();
        });
        controlPanel.add(clearButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Register Mouse Adapters for drawing
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Add first point of the stroke
                points.add(e.getPoint());
                canvas.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // Add continuing points as mouse drags
                points.add(e.getPoint());
                canvas.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Add a null value to demarcate end of current stroke
                points.add(null);
            }
        };

        canvas.addMouseListener(mouseHandler);
        canvas.addMouseMotionListener(mouseHandler);

        setLocationRelativeTo(null);
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE); // Stroke color

            // Draw line segments between sequential points
            for (int i = 0; i < points.size() - 1; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get(i + 1);

                if (p1 != null && p2 != null) {
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleDrawingApp().setVisible(true);
            }
        });
    }
}
