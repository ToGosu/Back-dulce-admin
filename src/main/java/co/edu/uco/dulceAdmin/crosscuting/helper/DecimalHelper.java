package co.edu.uco.dulceAdmin.crosscuting.helper;

import java.math.BigDecimal;

public final class DecimalHelper {
    private static final BigDecimal ZERO = BigDecimal.ZERO;
    public static BigDecimal getDefault() { return ZERO; }
    public static BigDecimal getDefault(BigDecimal value) {
        return ObjectHelper.getDefault(value, ZERO);
    }
}
