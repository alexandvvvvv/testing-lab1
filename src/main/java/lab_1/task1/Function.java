package lab_1.task1;

public class Function {

    private static final double CLOSEBORDER = 0.96;

    private static double doubleFact(double n){
        if (n <= 0) {
            return 1;
        }
        return (n-1) / n * doubleFact(n-2);
    }

    private static double seriesTeilor(double x, double eps){
        int n = 1;
        double result = x;
        double prevResult;
        do {
            prevResult = result;
            double fac = doubleFact(2 * n);
            result += fac * Math.pow(x, 2 * n + 1) / (2 * n + 1);
            n++;
        } while (Math.abs(result - prevResult) > eps);
        return result;
    }

    public static double arccos(double x, double eps){
        if (Math.abs(x) > 1.0){
            return Double.NaN;
        }
        if (Math.abs(x) > CLOSEBORDER){
            double newX = Math.sqrt(1 - x*x);
            return Math.PI / 2 - Math.signum(x) * (Math.PI/2 - seriesTeilor(newX, eps));
        }
        else {
            return Math.PI / 2 - seriesTeilor(x, eps);
        }
    }
}
