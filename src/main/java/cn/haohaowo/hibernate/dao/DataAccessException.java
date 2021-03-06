package cn.haohaowo.hibernate.dao;

public class DataAccessException extends RuntimeException {
	private static final long serialVersionUID = 1029886640422483388L;

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}
	
}
