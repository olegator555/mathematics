public interface Functions {
     int C = 2;
     int T = 1;
     double getFunction(double x);
     default double getDerivative(double x) {
         return 0;
    }
}
