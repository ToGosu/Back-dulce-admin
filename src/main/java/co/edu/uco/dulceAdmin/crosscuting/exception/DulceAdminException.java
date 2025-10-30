package co.edu.uco.dulceAdmin.crosscuting.exception;

import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;

public final class DulceAdminException extends RuntimeException {
	
private static final long serialVersionUID = -433023700129543247L;
	private Throwable rootException;
	private String userMessage;
	private String technicalMessage;

	public DulceAdminException(final Throwable rootException,final String userMessage,final String technicalMessage) {
		setRootException(rootException);
		setUserMessage(userMessage);
		setTechnicalMessage(technicalMessage);
	}

	public static DulceAdminException create(final String userMessage) {
		return new DulceAdminException(new Exception(), userMessage, userMessage);
	}

	public static DulceAdminException create(final String userMessage, final String technicalMessage) {
		return new DulceAdminException(new Exception(), userMessage, technicalMessage);
	}

	public static DulceAdminException create(final Throwable rootExceltion, final String userMessage, final String technicalMessage) {
		return new DulceAdminException(rootExceltion, userMessage, technicalMessage);
	}

	public Throwable getRootException() {
		return rootException;
	}

	private void setRootException(Throwable rootException) {
		this.rootException = ObjectHelper.getDefault(rootException, new Exception());
	}

	public String getUserMessage() {
		return userMessage;
	}

	private void setUserMessage(String userMessage) {
		this.userMessage = TextHelper.getDefaultWithTrim(userMessage);
	}

	public String getTechnicalMessage() {
		return technicalMessage;
	}

	private void setTechnicalMessage(final String technicalMessage) {
		this.technicalMessage = TextHelper.getDefaultWithTrim(technicalMessage);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}