public class Variable_function implements Functions{
    private final double coeff_a;
    private final double coeff_b;
    private final double coeff_c;

    public Variable_function(double coeff_a, double coeff_b, double coeff_c) {
        this.coeff_a = coeff_a;
        this.coeff_b = coeff_b;
        this.coeff_c = coeff_c;
    }

    public double getFunction(double x){

        return coeff_a*Math.pow(x,2)+coeff_b*x+coeff_c;
    }
    public double getDerivative(double x)
    {

        return coeff_a*x*2+coeff_b;
    }
}
