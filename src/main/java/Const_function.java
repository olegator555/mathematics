import static java.lang.Math.*;

public class Const_function implements Functions {
    final double g = 9.81;
    Functions f;
    public Const_function(Functions f) {

        this.f=f;
    }

    @Override
    public double getFunction(double x) {

        return sqrt((1+pow(sin(2*x),2))/(0.5*C*(1-cos(2*x))))*C*(1-cos(2*x));
    }

}
