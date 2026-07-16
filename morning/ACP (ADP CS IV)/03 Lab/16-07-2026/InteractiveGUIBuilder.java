import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class InteractiveGUIBuilder extends JFrame {

    // --- The design canvas where dropped components live ---
    private JPanel canvas;
    // --- Palette panel (source of draggable component types) ---
    private JPanel palette;
    // --- Property display ---
    private JLabel lblName, lblPos, lblSize;
    // --- Event log ---
    private JTextArea logArea;

    // --- Currently selected component ---
    private JComponent selectedComponent = null;
    private Border defaultBorder = null;
    private static final Border SELECTED_BORDER = BorderFactory.createLineBorder(Color.BLUE, 2);

    // --- Counters for unique component names ---
    private int buttonCount = 0, labelCount = 0, textFieldCount = 0;

    // --- Map to store component->name for identification ---
    private Map<JComponent, String> componentNames = new HashMap<>();

    // --- Drag‑and‑drop support objects ---
    private DragSource dragSource = DragSource.getDefaultDragSource();

    public InteractiveGUIBuilder() {
        setTitle("Interactive GUI Builder – Drag & Drop + Event Listeners");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // ---------- Main layout ----------
        setLayout(new BorderLayout());

        // 1. PALETTE (left side)
        palette = new JPanel();
        palette.setLayout(new BoxLayout(palette, BoxLayout.Y_AXIS));
        palette.setBorder(new TitledBorder("Component Palette"));
        palette.setPreferredSize(new Dimension(130, 0));
        addPaletteItem("Button", "JButton");
        addPaletteItem("Label", "JLabel");
        addPaletteItem("TextField", "JTextField");

        // 2. CANVAS (center) – null layout for free positioning
        canvas = new JPanel(null);
        canvas.setBackground(Color.WHITE);
        canvas.setBorder(new TitledBorder("Design Area (drop here)"));
        new DropTarget(canvas, new CanvasDropTargetListener());

        // 3. RIGHT PANEL (properties + event log)
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(220, 0));

        // --- Property panel ---
        JPanel propPanel = new JPanel(new GridLayout(6, 1, 2, 2));
        propPanel.setBorder(new TitledBorder("Properties"));
        lblName = new JLabel("Name: -");
        lblPos = new JLabel("Position: -");
        lblSize = new JLabel("Size: -");
        propPanel.add(lblName);
        propPanel.add(lblPos);
        propPanel.add(lblSize);
        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.addActionListener(e -> deleteSelected());
        propPanel.add(btnDelete);
        rightPanel.add(propPanel, BorderLayout.NORTH);

        // --- Event log ---
        logArea = new JTextArea(10, 20);
        logArea.setEditable(false);
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(new TitledBorder("Event Log"));
        rightPanel.add(logScroll, BorderLayout.CENTER);

        add(palette, BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    // ---------- Palette item creation with drag support ----------
    private void addPaletteItem(String displayName, String componentType) {
        JLabel item = new JLabel(displayName, SwingConstants.CENTER);
        item.setOpaque(true);
        item.setBackground(Color.LIGHT_GRAY);
        item.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        item.setMaximumSize(new Dimension(120, 40));
        item.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        DragGestureRecognizer dgr = dragSource.createDefaultDragGestureRecognizer(
                item, DnDConstants.ACTION_COPY, new DragGestureListener() {
                    @Override
                    public void dragGestureRecognized(DragGestureEvent dge) {
                        StringSelection transferable = new StringSelection(componentType);
                        dge.startDrag(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR),
                                transferable, new PaletteDragSourceListener());
                    }
                });
        palette.add(item);
        palette.add(Box.createVerticalStrut(10));
    }

    // ---------- Drag source listener (palette side) ----------
    class PaletteDragSourceListener extends DragSourceAdapter {
        @Override
        public void dragDropEnd(DragSourceDropEvent dsde) {
            if (dsde.getDropSuccess()) {
                log("Dropped a new component successfully.");
            }
        }
    }

    // ---------- Drop target listener (canvas side) ----------
    class CanvasDropTargetListener extends DropTargetAdapter {
        @Override
        public void drop(DropTargetDropEvent dtde) {
            try {
                Transferable tr = dtde.getTransferable();
                if (tr.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    String componentType = (String) tr.getTransferData(DataFlavor.stringFlavor);
                    Point dropPoint = dtde.getLocation();
                    addComponentToCanvas(componentType, dropPoint);
                    dtde.dropComplete(true);
                } else {
                    dtde.rejectDrop();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                dtde.rejectDrop();
            }
        }
    }

    // ---------- Add a new component to the canvas ----------
    private void addComponentToCanvas(String type, Point location) {
        JComponent comp = null;
        String baseName = "";
        switch (type) {
            case "JButton":
                baseName = "button";
                comp = new JButton("Button");
                buttonCount++;
                comp.setName(baseName + buttonCount);
                break;
            case "JLabel":
                baseName = "label";
                comp = new JLabel("Label");
                labelCount++;
                comp.setName(baseName + labelCount);
                break;
            case "JTextField":
                baseName = "textField";
                comp = new JTextField("TextField");
                textFieldCount++;
                comp.setName(baseName + textFieldCount);
                break;
        }
        if (comp == null) return;

        // --- Make a final copy for use inside lambdas ---
        JComponent finalComp = comp;

        // --- Action listener only for JButton ---
        if (finalComp instanceof JButton) {
            ((JButton) finalComp).addActionListener(e ->
                    log("ActionEvent: " + finalComp.getName() + " clicked"));
        }

        componentNames.put(finalComp, finalComp.getName());
        finalComp.setOpaque(!type.equals("JLabel"));
        if (type.equals("JButton")) finalComp.setBackground(Color.LIGHT_GRAY);
        if (type.equals("JTextField")) finalComp.setBackground(Color.WHITE);
        finalComp.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        finalComp.setBounds(location.x, location.y, 100, 30);

        // ----- Attach interaction listeners -----
        InteractionHandler handler = new InteractionHandler();
        finalComp.addMouseListener(handler);
        finalComp.addMouseMotionListener(handler);

        // Right‑click popup menu for deletion
        JPopupMenu popup = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        // Use finalComp (effectively final) in lambda
        deleteItem.addActionListener(e -> deleteComponent(finalComp));
        popup.add(deleteItem);
        finalComp.setComponentPopupMenu(popup);

        canvas.add(finalComp);
        canvas.revalidate();
        canvas.repaint();
        log("Added " + finalComp.getName() + " at (" + location.x + ", " + location.y + ")");
    }

    // ---------- Mouse interaction handler (selection, moving, resizing) ----------
    class InteractionHandler extends MouseAdapter {
        private Point startPoint;
        private boolean isResizing = false;

        @Override
        public void mousePressed(MouseEvent e) {
            JComponent comp = (JComponent) e.getSource();
            int w = comp.getWidth();
            int h = comp.getHeight();
            Rectangle resizeRect = new Rectangle(w - 12, h - 12, 12, 12);
            if (resizeRect.contains(e.getPoint())) {
                isResizing = true;
                startPoint = e.getPoint();
                comp.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
            } else {
                isResizing = false;
                startPoint = e.getPoint();
                comp.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }
            selectComponent(comp);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            JComponent comp = (JComponent) e.getSource();
            if (isResizing) {
                int dx = e.getX() - startPoint.x;
                int dy = e.getY() - startPoint.y;
                int newW = Math.max(40, comp.getWidth() + dx);
                int newH = Math.max(20, comp.getHeight() + dy);
                comp.setSize(newW, newH);
                startPoint = e.getPoint();
                updateProperties(comp);
            } else {
                int dx = e.getX() - startPoint.x;
                int dy = e.getY() - startPoint.y;
                Point newLoc = new Point(comp.getX() + dx, comp.getY() + dy);
                newLoc.x = Math.max(0, Math.min(newLoc.x, canvas.getWidth() - comp.getWidth()));
                newLoc.y = Math.max(0, Math.min(newLoc.y, canvas.getHeight() - comp.getHeight()));
                comp.setLocation(newLoc);
                updateProperties(comp);
                log(comp.getName() + " moved to (" + newLoc.x + ", " + newLoc.y + ")");
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JComponent comp = (JComponent) e.getSource();
            comp.setCursor(Cursor.getDefaultCursor());
            if (isResizing) {
                log(comp.getName() + " resized to " + comp.getWidth() + "x" + comp.getHeight());
            }
            isResizing = false;
        }
    }

    // ---------- Selection handling ----------
    private void selectComponent(JComponent comp) {
        if (selectedComponent != null && selectedComponent != comp) {
            selectedComponent.setBorder(defaultBorder);
        }
        selectedComponent = comp;
        defaultBorder = comp.getBorder();
        comp.setBorder(SELECTED_BORDER);
        updateProperties(comp);
        log("Selected " + comp.getName());
    }

    private void updateProperties(JComponent comp) {
        if (comp == null) {
            lblName.setText("Name: -");
            lblPos.setText("Position: -");
            lblSize.setText("Size: -");
            return;
        }
        lblName.setText("Name: " + comp.getName());
        lblPos.setText("Position: (" + comp.getX() + ", " + comp.getY() + ")");
        lblSize.setText("Size: " + comp.getWidth() + " x " + comp.getHeight());
    }

    // ---------- Delete operations ----------
    private void deleteSelected() {
        if (selectedComponent != null) {
            deleteComponent(selectedComponent);
        }
    }

    private void deleteComponent(JComponent comp) {
        if (comp != null) {
            canvas.remove(comp);
            componentNames.remove(comp);
            if (comp == selectedComponent) {
                selectedComponent = null;
                updateProperties(null);
            }
            canvas.revalidate();
            canvas.repaint();
            log("Deleted " + comp.getName());
        }
    }

    // ---------- Logging ----------
    private void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    // ---------- Main ----------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InteractiveGUIBuilder().setVisible(true));
    }
}