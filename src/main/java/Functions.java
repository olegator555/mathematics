import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.linear.GaussianSolver;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;
import java.util.ArrayList;

interface Base_Part {
    void qubic_spline_coeff(int[] x_nodes, double[] y_nodes);
    double qubic_spline(int x, double[] qs_coeff);
    double d_qubic_spline(double x, double[] qs_coeff);

}

public class Functions implements Base_Part {
    ArrayList<Double> coeff_c = new ArrayList<>();
    ArrayList<Double> coeff_a = new ArrayList<>();
    ArrayList<Double> coeff_b = new ArrayList<>();
    ArrayList<Double> coeff_d = new ArrayList<>();

    @Override
    public void qubic_spline_coeff(int[] x_nodes, double[] y_nodes) {

        Matrix A = new Basic2DMatrix(new double[y_nodes.length][y_nodes.length]);
        for(int i=0;i<y_nodes.length;i++)
        {
            for(int j=0;j<y_nodes.length;j++) {
                if (i == j)
                    A.set(i, j, 4);
                if (i == j + 1 || i == j - 1)
                    A.set(i, j, 1);
            }
        }


        Vector vector = new BasicVector(y_nodes.length);
        vector.set(0,0);
        vector.set(1,0);
        coeff_c.add(0.0);
        coeff_c.add(0.0);
        coeff_a.add(0.0);
        for (int i = 2; i < y_nodes.length; i++) {
            vector.set(i, y_nodes[i] - 2 * y_nodes[i - 1] + y_nodes[i - 2]);
        }
        GaussianSolver gaussianSolver = new GaussianSolver(A);
        Vector vec = gaussianSolver.solve(vector);

        for (int i = 1; i < y_nodes.length; i++) {
            coeff_c.add(vec.get(i));
            coeff_a.add(y_nodes[i-1]);
            coeff_b.add(y_nodes[i] - y_nodes[i-1] - (coeff_c.get(i + 1) +2*coeff_c.get(i))/3);
            coeff_d.add((coeff_c.get(i + 1) - coeff_c.get(i))/3);
        }


    }

    @Override
    public double qubic_spline(int x, double[] qs_coeff) {
        double result = 0;
        try {
            result = qs_coeff[0] + qs_coeff[1] * x + qs_coeff[2] * x * x + qs_coeff[3] * x * x * x;
        } catch (IndexOutOfBoundsException ignored) {}
        return result;
    }

    @Override
    public double d_qubic_spline(double x, double[] qs_coeff) {
        double result = 0;
        try {
            result = qs_coeff[0] + qs_coeff[1] + qs_coeff[2] * x * 2 + qs_coeff[3] * x * x * 3;
        } catch (IndexOutOfBoundsException ignored) {}
        return result;
    }


    void print_all_coefficients()
    {
        System.out.println("Qubic spline coefficients:");
        System.out.println(coeff_a);
        System.out.println(coeff_b);
        System.out.println(coeff_c);
        System.out.println(coeff_d);
        System.out.println();
    }
    double[] get_coefficients_matrix(int x_point, int[] x_nodes) throws OutOfRangeException {
        int index = 0;
        boolean not_found = true;

        for (int i = 0; i < x_nodes.length-1; i++) {
            if (x_point == x_nodes[i])
            {
                index = i;
                System.out.println(x_point);
                System.out.println(index);
                System.out.println(x_nodes[i]);
                not_found = false;
                break;
            }

        }
        if(not_found)
            throw new OutOfRangeException();
        double[] matrix = new double[4];
        matrix[0]=coeff_a.get(index);
        matrix[1]=coeff_b.get(index);
        matrix[2]=coeff_c.get(index);
        matrix[3]=coeff_d.get(index);
        return matrix;
    }

}
