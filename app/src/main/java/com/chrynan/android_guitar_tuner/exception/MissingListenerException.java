package com.chrynan.android_guitar_tuner.exception;

/**
 * An {@link Exception} thrown when a class is supposed to implement a specific listener interface
 * but it does not.
 */
public class MissingListenerException extends RuntimeException {

    /**
     * Constructor for this exception class.
     *
     * @param listenerClass          The Listener {@link Class}.
     * @param classExpectingListener The {@link Class} that discovered the missing Listener.
     */
    public MissingListenerException(final Class<?> listenerClass, final Class<?> classExpectingListener) {
        super("Class " + classExpectingListener.getName() + " expects the listener " + listenerClass.getCanonicalName() +
                " to be set.");
    }

    /**
     * Constructor for this exception class.
     *
     * @param listenerClass            The Listener {@link Class}.
     * @param classToImplementListener The {@link Class} that should implement the Listener but does not.
     * @param classExpectingListener   The {@link Class} that discovered the missing Listener.
     */
    public MissingListenerException(final Class<?> listenerClass, final Class<?> classToImplementListener, final Class<?> classExpectingListener) {
        super("Class " + classExpectingListener.getName() + " expects the class " + classToImplementListener.getName() +
                "to implement the listener " + listenerClass.getCanonicalName());
    }
}
