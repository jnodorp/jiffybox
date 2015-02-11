package eu.df.jiffybox.builders;

/**
 * This class is used to represent a finished builder. It is basically a wrapper
 * around the builder which only marks it as finished. This can be used to
 * prevent invalid builders from being handled to method calls.
 */
public interface Finished<T extends Builder> {

    /**
     * Get the finished builder.
     *
     * @return The finished builder.
     */
    T getBuilder();
}
