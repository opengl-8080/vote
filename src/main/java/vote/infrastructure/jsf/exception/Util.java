package vote.infrastructure.jsf.exception;

import java.util.Objects;

public class Util {

    public static boolean hasRootCause(Throwable th, Class<? extends Throwable> expectedRootCause) {
        Throwable rootCause = getRootCause(th);
        return Objects.equals(rootCause.getClass(), expectedRootCause);
    }

    private static Throwable getRootCause(Throwable th) {
        Throwable cause = th.getCause();

        if (cause == null) {
            return th;
        }

        return getRootCause(cause);
    }

    private Util() {}
}
