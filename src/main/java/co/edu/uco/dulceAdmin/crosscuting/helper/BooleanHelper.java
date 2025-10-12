package co.edu.uco.dulceAdmin.crosscuting.helper;


public final class BooleanHelper {
	
	private static final boolean DEFAULT_VALUE = false;
	
	private BooleanHelper() {
	}
	
	public static boolean getDefault() {
		return DEFAULT_VALUE;
	}
	
	public static boolean getDefault(final Boolean value) {
		return ObjectHelper.getDefault(value, getDefault());
	}
	
	public static boolean isTrue(final Boolean value) {
		return Boolean.TRUE.equals(value);
	}
	
	public static boolean isFalse(final Boolean value) {
		return Boolean.FALSE.equals(value);
	}
}

