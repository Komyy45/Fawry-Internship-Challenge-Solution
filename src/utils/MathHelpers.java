package utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public final class MathHelpers {
    public static String round(double number, int decimalPlaces)
    {
        BigDecimal bd = BigDecimal.valueOf(number).setScale(decimalPlaces, RoundingMode.HALF_UP).stripTrailingZeros();
        return bd.toPlainString();
    }
}
