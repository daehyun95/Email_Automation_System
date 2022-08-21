package problem1;

/**
 * Class of InvalidCombinationArgument
 */
public class InvalidCombinationArgument extends Throwable {
  /**
   * Constructs a new exception with the specified detail message.  The cause is not initialized, and
   * may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   *                {@link #getMessage()} method.
   */
  public InvalidCombinationArgument(String message) {
    super(message);
  }
}
