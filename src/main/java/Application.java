import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Application {
    private Graph graph;
    static ArrayList<String> list = new ArrayList<>();
    static JFrame frame = new JFrame("Lab2");
    void setGraph(Graph graph) {
        this.graph = graph;
    }
    public void setLabel(String s)
    {
        list.add(s);
    }
    private void addGraph(Graph graph) {
        frame.getContentPane().add(graph.plot());
    }
    private void addComponentsToPane(Container pane, ArrayList<String> list) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        for (String s:list
             ) {
            addLabel(s, pane);
        }
    }

    private void addLabel(String text, Container container) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(label);
    }

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane(),list);
        addGraph(graph);
        frame.pack();
        frame.setVisible(true);
    }

}