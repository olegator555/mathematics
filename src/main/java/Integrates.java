public class Integrates {
    Double composite_simpson(double a, double b, int n,Functions f){
        final double width = (b-a)/n;
        double result = 0;
        for (int i=0;i<n;i++){
            final double x1 = a+i*width;
            final double x2 = a+(i+1)*width;
            result +=(x2-x1)/6*(f.getFunction(x1)+4* f.getFunction(0.5*(x1+x2))+ f.getFunction(x2));
        }


        return result;
    }
    Double composite_trapezoid(double a, double b, int n,Functions f){
        final double width = (b-a)/n;
        double result = 0;
        for (int i=0;i<n;i++){
            final double x1 = a+i*width;
            final double x2 = a+(i+1)*width;
            result +=(x2-x1)*0.5*(f.getFunction(x1)+ f.getFunction(x2));
        }

        return result;
    }
}
