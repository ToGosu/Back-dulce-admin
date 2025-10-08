package co.edu.uco.dulceAdmin.crosscuting.exception;

import co.edu.uco.dulceAdmin.crosscuting.helper.ObjectHelper;
import co.edu.uco.dulceAdmin.crosscuting.helper.TextHelper;

public final class DulceAdminException extends RuntimeException {
	
	private static final long serialVersionUID= 512335343454L;
	private Throwable rootException;
	private static String userMessage;
	private String technicalMessage;
	
	private DulceAdminException(final Throwable rootException, final String suerMessage, String userMessage2) {
		setRootException(rootException);
		setUserMessage(suerMessage);
		setTechnicalMessage(suerMessage);
	
	}
	
	public static DulceAdminException create(final String userMessage) {
		return new DulceAdminException(new Exception(), userMessage, userMessage);
	}
	
	public static DulceAdminException create(final String userMessage, final String technicalMessage) {
		return new DulceAdminException(new Exception(), userMessage, technicalMessage);
	}
	
	public static DulceAdminException create(final Throwable rootException, final String userMessage, final String technicalMessage) {
		return new DulceAdminException(rootException, userMessage, technicalMessage);
	}
	
	private Throwable getRootException() {
		return rootException;
	}
	private void setRootException(Throwable rootException) {
		this.rootException = ObjectHelper.getDefault(rootException, new Exception());
	}
	public String getUserMessage() {
		return userMessage;
	}
	private void setUserMessage(final String userMessage) {
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
