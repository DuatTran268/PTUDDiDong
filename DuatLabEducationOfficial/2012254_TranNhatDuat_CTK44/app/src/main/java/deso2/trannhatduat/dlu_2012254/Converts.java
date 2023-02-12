package deso2.trannhatduat.dlu_2012254;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Converts {
    public static String convertVND(int value){
        Locale locale = new Locale("vi", "VN");
        String pattern = "###,###,###";
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);

        return decimalFormat.format(value) + "đ";
    }
}
