package programranje.yatospace.server.basic.lang;

/**
 * Грешке услед прегледа. 
 * @author mirko
 * @version 1.0
 */
public class PreviewException extends RuntimeException{
	private static final long serialVersionUID = -7483398328336435813L;

	public PreviewException() {
		super();
	}

	public PreviewException(String message, Throwable cause) {
		super(message, cause);
	}

	public PreviewException(String message) {
		super(message);
	}

	public PreviewException(Throwable cause) {
		super(cause);
	}
}
