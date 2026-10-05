package com.cheatbreaker.recovery;

/** Preserves bytecode rethrows where checked exception metadata was removed. */
public final class ExceptionRethrow {
    private ExceptionRethrow() {
    }

    @SuppressWarnings("unchecked")
    public static <E extends Throwable> void rethrow(Throwable exception) throws E {
        throw (E) exception;
    }
}
