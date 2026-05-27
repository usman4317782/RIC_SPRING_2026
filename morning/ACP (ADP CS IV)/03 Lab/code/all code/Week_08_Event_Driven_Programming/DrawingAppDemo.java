import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Lab Task 3: Develop a simple drawing application with mouse events.
 * 
 * To compile: javac DrawingAppDemo.java
 * To run: java DrawingAppDemo
 */
public class DrawingAppDemo extends JFrame {
    private List<List<Point>> strokes = new ArrayList<>();
    private List<Point> currentStroke;

    public DrawingAppDemo() {
        super("Simple Mouse Drawing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);

        // Header Label
        add(new JLabel(" Click and drag mouse to draw. Press 'Clear' to start fresh.", JLabel.CENTER), BorderLayout.NORTH);

        // Canvas Panel
        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                
                // Draw all strokes
                for (List<Point> stroke : strokes) {
                    for (int i = 0; i < stroke.size() - 1; i++) {
                        Point p1 = stroke.get(i);
                        Point p2 = stroke.get(i + 1);
                        g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }
                
                // Draw current active stroke
                if (currentStroke != null) {
                    for (int i = 0; i < currentStroke.size() - 1; i++) {
                        Point p1 = currentStroke.get(i);
                        Point p2 = currentStroke.get(i + 1);
                        g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }
            }
        };
        canvas.setBackground(Color.WHITE);

        // Mouse Adapter to capture clicks and drags
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentStroke = new ArrayList<>();
                currentStroke.add(e.getPoint());
                canvas.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentStroke != null) {
                    currentStroke.add(e.getPoint());
                    canvas.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentStroke != null) {
                    strokes.add(currentStroke);
                    currentStroke = null;
                    canvas.repaint();
                }
            }
        };

        canvas.addMouseListener(mouseHandler);
        canvas.addMouseMotionListener(mouseHandler);
        add(canvas, BorderLayout.CENTER);

        // Clear Button at South
        JButton clearBtn = new JButton("Clear Canvas");
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strokes.clear();
                canvas.repaint();
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clearBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DrawingAppDemo().setVisible(true);
        });
    }
}
