import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {


    public static void main(String[] args) {
        final int[] x_nodes = IntStream.range(1995,2019).toArray();
        final double[] y_nodes = {14285,20078,23425,26296,48232,73056,89436,108305,132082,170272,216098,269172,332475,412768,388072,463085,602825,681639,731339,791997,832326,860102,920893,1036266};

        Functions functions = new Functions();
        functions.qubic_spline_coeff(x_nodes,y_nodes);
        functions.print_all_coefficients();

        Scanner scanner = new Scanner(System.in);
        int x_point;
        System.out.print("Enter x: ");
        try {
            x_point = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new RuntimeException("Integer only");
        }

        double[] qs_coeff = new double[0];
        try {
            qs_coeff = functions.get_coefficients_matrix(x_point,x_nodes);
        } catch (OutOfRangeException e) {
            System.out.println("x must be between 1995 and 2017");
        }

        double spline = functions.qubic_spline(x_point,qs_coeff);
        double spline_derivative = functions.d_qubic_spline(x_point,qs_coeff);
        System.out.println("P(x)="+spline);
        System.out.println("P'(x)="+spline_derivative);

        int[] x_from_table = {0,1,2,3,4,5,6,7,8,9,10};
        double[] y_from_table = {33.7,39.5,37.3,35.9,31.5,30.5,38.6,36.0,37.0,30.2};
        Graph graph = new Graph(x_from_table,y_from_table);
        graph.get_spline();
        graph.plot();
    }


}

