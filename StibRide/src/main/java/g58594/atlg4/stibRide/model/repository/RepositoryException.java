package g58594.atlg4.stibRide.model.repository;

public class RepositoryException extends Exception{
    /**
     * Creates a new instance of RepositoryException without detail
     * message.
     */
    public RepositoryException() {
        super();
    }

    /**
     * Constructs an instance of RepositoryException with the specified
     * detail message.
     *
     * @param msg message of the exception.
     */
    public RepositoryException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of RepositoryException and wrapped the
     * source exception.
     *
     * @param exception wrapped exception.
     */
    public RepositoryException(Exception exception) {
        super(exception);
    }
}
