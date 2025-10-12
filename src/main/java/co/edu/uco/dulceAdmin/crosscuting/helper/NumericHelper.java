package co.edu.uco.dulceAdmin.crosscuting.helper;

public final class NumericHelper {
	
	private static final int ZERO = 0;

	private NumericHelper() {
	}
	
	public static int getDefault() {
		return ZERO;
	}
	
	public static int getDefault(final Integer value) {
		return ObjectHelper.getDefault(value, getDefault());
	}
	
	public static boolean isZero(final Integer value) {
		return getDefault(value) == ZERO;
	}
	
	public static boolean isPositive(final Integer value) {
		return getDefault(value) > ZERO;
	}
	
	public static boolean isNegative(final Integer value) {
		return getDefault(value) < ZERO;
	}
}
