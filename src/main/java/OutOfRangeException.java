public class OutOfRangeException extends Throwable {
    public OutOfRangeException() {
        super();
        System.out.println("x must be between 1995 and 2017");
    }
}
