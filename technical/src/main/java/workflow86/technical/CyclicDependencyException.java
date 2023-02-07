package workflow86.technical;

public class CyclicDependencyException
extends RuntimeException {
    public CyclicDependencyException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
