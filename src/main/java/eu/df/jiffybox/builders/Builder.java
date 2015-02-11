package eu.df.jiffybox.builders;

/**
 * This interface is implement builders. Basically it is used to make sure, that
 * all builders of a specific type return a finished object of the builders
 * type. This prevents, that any builders result can be used as a parameter for
 * any method.
 */
public interface Builder<T extends Builder> {

    /**
     * Finish the current build process and return a finished builder of a
     * specific type.
     *
     * @return The finished builder of a specified type.
     */
    public Finished<T> build();
}
