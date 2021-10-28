import javax.swing.*;

public class Main {
    private static void run(Application application) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                application.createAndShowGUI();
            }
        });
    }

    public static void main(String[] args) {
        Functions f = new Variable_function(1,8,7);
        Integrates integrates  = new Integrates();
        Functions const_f = new Const_function(f);
        double reference_value = 22062.3; // this value was calculated by Wolfram Alpha
        Graph graph = new Graph(reference_value, const_f);
        Application application = new Application();
        application.setLabel("Задание 1:");
        application.setLabel("Вывод composite_simpson:"+integrates.composite_simpson(-1,1,5,f).toString());
        application.setLabel("Вывод composite_trapezoid:"+integrates.composite_trapezoid(-1,1,5,f).toString());
        application.setLabel("Задание 2:");
        application.setLabel("Вывод composite_simpson:"+integrates.composite_trapezoid(3,9999,1000,const_f).toString());
        application.setLabel("Вывод composite_trapezoid:"+integrates.composite_trapezoid(3,9999,1000,const_f).toString());
        application.setLabel("Задание 3:");
        application.setGraph(graph);
        run(application);
    }
}
