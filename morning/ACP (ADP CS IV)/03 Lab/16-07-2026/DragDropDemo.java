import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DragDropDemo extends JFrame {

    private JPanel canvas;                     // where we drop the labels
    private JLabel draggedLabel = null;        // the component being dragged
    private int dragOffsetX, dragOffsetY;      // offset between mouse and label's top‑left

    public DragDropDemo() {
        setTitle("Drag & Drop with Event Listeners");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // --- Canvas (null layout so we can position components freely) ---
        canvas = new JPanel(null);             // no layout manager
        canvas.setBackground(Color.WHITE);

        // --- Create some draggable coloured labels ---
        addDraggableLabel("Red", Color.RED, 50, 50);
        addDraggableLabel("Green", Color.GREEN, 150, 50);
        addDraggableLabel("Blue", Color.BLUE, 250, 50);
        addDraggableLabel("Yellow", Color.YELLOW, 100, 150);

        add(canvas);
    }

    /** Creates a JLabel that can be dragged, adds it to the canvas. */
    private void addDraggableLabel(String text, Color bg, int x, int y) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(bg);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        label.setBounds(x, y, 100, 40);        // size and initial position

        // --- MouseListener: start / end drag ---
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                draggedLabel = (JLabel) e.getSource();
                dragOffsetX = e.getX();        // where inside the label we clicked
                dragOffsetY = e.getY();
                draggedLabel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                canvas.setComponentZOrder(draggedLabel, 0); // bring to front
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggedLabel = null;           // drop finished
                if (e.getSource() instanceof JLabel) {
                    ((JLabel) e.getSource()).setCursor(Cursor.getDefaultCursor());
                }
            }
        });

        // --- MouseMotionListener: move while dragging ---
        label.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggedLabel != null) {
                    // new position = current mouse location on canvas – offset
                    int newX = draggedLabel.getX() + e.getX() - dragOffsetX;
                    int newY = draggedLabel.getY() + e.getY() - dragOffsetY;
                    draggedLabel.setLocation(newX, newY);
                }
            }
        });

        canvas.add(label);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DragDropDemo().setVisible(true));
    }
}