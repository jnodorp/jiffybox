package eu.df.jiffybox.internal;

import eu.df.jiffybox.builders.Builder;
import eu.df.jiffybox.builders.Finished;

/**
 * This class is used to represent a finished builder. It is basically a wrapper
 * around the builder which only marks it as finished. This can be used to
 * prevent invalid builders from being handled to method calls.
 */
class FinishedImpl<T extends Builder> implements Finished<T> {

    /**
     * The builder to mark as finished.
     */
    private final T builder;

    /**
     * Mark the handled builder as finished.
     *
     * @param builder The finished builder.
     */
    public FinishedImpl(final T builder) {
        this.builder = builder;
    }

    @Override
    public T getBuilder() {
        return builder;
    }
}
