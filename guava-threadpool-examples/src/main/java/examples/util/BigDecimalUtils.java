package examples.util;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @function 金额计算工具类扩展
 */
public final class BigDecimalUtils {

    //默认金额格式化
    private static final DecimalFormat DEFAULT_DECIMALFORMAT = new DecimalFormat("0.00");
    //默认浮点型数值比较差距值
    private static final double DEFAULT_DELTA = 1e-6;
    //double数值0
    public static final double DOUBLE_ZERO = 0.0D;
    public static final BigDecimal BIG_DECIMAL_ZERO = BigDecimal.ZERO;
    //double数值0
    public static final double DOUBLE_ONE = 1.0D;
    //int数值0
    public static final int INTEGER_ZERO = 0;
    //默认保留四位小数
    private static final int DEFAULT_SCALE = 4;

    public final static int ROUND_UP = 0;

    public final static int ROUND_DOWN = 1;

    public final static int ROUND_CEILING = 2;

    public final static int ROUND_FLOOR = 3;

    public final static int ROUND_HALF_UP = 4;

    public final static int ROUND_HALF_DOWN = 5;

    public final static int ROUND_HALF_EVEN = 6;

    public final static int ROUND_UNNECESSARY = 7;

    /**
     * 加法
     *
     * @param augend 被加数
     * @param addend 加数
     * @return
     */
    public static double add(double augend, double addend) {
        BigDecimal n1 = BigDecimal.valueOf(augend);
        BigDecimal n2 = BigDecimal.valueOf(addend);
        BigDecimal value = n1.add(n2);
        return value.doubleValue();
    }

    /**
     * 减法
     *
     * @param minuend    被减数
     * @param subtractor 减数
     * @return
     */
    public static double subtract(double minuend, double subtractor) {
        BigDecimal n1 = BigDecimal.valueOf(minuend);
        BigDecimal n2 = BigDecimal.valueOf(subtractor);
        BigDecimal value = n1.subtract(n2);

        return value.doubleValue();
    }

    /**
     * 乘法  -- 修正NULL值
     *
     * @param multiplicand 被乘数
     * @param multiplier   乘数
     * @return
     */
    public static double multiplyEx(Double multiplicand, Double multiplier) {
        if (null == multiplicand || multiplicand.equals(BIG_DECIMAL_ZERO.doubleValue())) {
            return DOUBLE_ZERO;
        }
        if (null == multiplier || multiplier.equals(BIG_DECIMAL_ZERO.doubleValue())) {
            return DOUBLE_ZERO;
        }
        BigDecimal n1 = BigDecimal.valueOf(multiplicand);
        BigDecimal n2 = BigDecimal.valueOf(multiplier);
        BigDecimal value = n1.multiply(n2);

        return value.doubleValue();
    }

    /**
     * 乘法
     *
     * @param multiplicand 被乘数
     * @param multiplier   乘数
     * @return
     */
    public static double multiply(double multiplicand, double multiplier) {
        BigDecimal n1 = BigDecimal.valueOf(multiplicand);
        BigDecimal n2 = BigDecimal.valueOf(multiplier);
        BigDecimal value = n1.multiply(n2);
        return value.doubleValue();
    }

    /**
     * 乘法
     *
     * @param multiplicand 被乘数
     * @param multiplier   乘数
     * @param scala        小数位
     * @return
     */
    public static double multiply(double multiplicand, double multiplier, int scala, int roundMode) {
        BigDecimal n1 = BigDecimal.valueOf(multiplicand);
        BigDecimal n2 = BigDecimal.valueOf(multiplier);
        BigDecimal value = n1.multiply(n2);
        return value.setScale(scala, roundMode).doubleValue();
    }

    /**
     * 乘法
     *
     * @param multiplicand 被乘数
     * @param multipliers  乘数
     * @return
     */
    public static double multiply(double multiplicand, double... multipliers) {
        BigDecimal n1 = BigDecimal.valueOf(multiplicand);
        BigDecimal value = n1;
        for (double multiplier : multipliers) {
            BigDecimal n2 = BigDecimal.valueOf(multiplier);
            value = value.multiply(n2);
        }
        return value.doubleValue();
    }

    /**
     * 除法
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    @Deprecated
    public static double divide(double dividend, double divisor) {
        BigDecimal n1 = BigDecimal.valueOf(dividend);
        BigDecimal n2 = BigDecimal.valueOf(divisor);
        BigDecimal value = n1.divide(n2);

        return value.doubleValue();
    }

    /**
     * 除法 -- 修正NULL值
     *
     * @param dividend  被除数
     * @param divisor   除数
     * @param roundMode 舍入模式
     * @return double
     */
    public static double divideEx(Double dividend, double divisor, int roundMode) {
        if (null == dividend || dividend.equals(BIG_DECIMAL_ZERO.doubleValue())) {
            return DOUBLE_ZERO;
        }
        return divide(dividend, divisor, DEFAULT_SCALE, roundMode);
    }

    /**
     * 除法 -- 修正NULL值
     *
     * @param dividend  被除数
     * @param divisor   除数
     * @param scale    精度
     * @param roundMode 舍入模式
     * @return double
     */
    public static double divideEx(Double dividend, double divisor, int scale, int roundMode) {
        if (null == dividend || dividend.equals(BIG_DECIMAL_ZERO.doubleValue())) {
            return DOUBLE_ZERO;
        }
        return divide(dividend, divisor, scale, roundMode);
    }

    /**
     * 除法 -- 修正NULL值
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return double
     */
    public static double defaultDivideEx(Double dividend, double divisor) {
        if (null == dividend || dividend.equals(BIG_DECIMAL_ZERO.doubleValue())) {
            return DOUBLE_ZERO;
        }
        return divide(dividend, divisor, DEFAULT_SCALE, ROUND_CEILING);
    }

    /**
     * 除法
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    精度
     * @return
     */
    public static double divide(double dividend, double divisor, int scale, int roundMode) {
        BigDecimal n1 = BigDecimal.valueOf(dividend);
        BigDecimal n2 = BigDecimal.valueOf(divisor);
        BigDecimal value = n1.divide(n2, scale, roundMode);
        return value.doubleValue();
    }

    /**
     * 向下取整，忽略小数位
     *
     * @param value
     * @return
     */
    public static String round(BigDecimal value) {
        return value.setScale(0, BigDecimal.ROUND_FLOOR).toString();
    }

    /**
     * 向下取整，忽略小数位
     *
     * @param value
     * @return
     */
    public static String round(double value) {
        return BigDecimal.valueOf(value).setScale(0, BigDecimal.ROUND_FLOOR).toString();
    }

    /**
     * 向下取整
     *
     * @param value
     * @param digit 保留小数位位数
     * @return
     */
    public static String round(BigDecimal value, int digit) {
        return value.setScale(digit, BigDecimal.ROUND_FLOOR).toString();
    }

    /**
     * 向下取整
     *
     * @param value
     * @param digit 保留小数位位数
     * @return
     */
    public static String round(double value, int digit) {
        return BigDecimal.valueOf(value).setScale(digit, BigDecimal.ROUND_FLOOR).toString();
    }

    /**
     * roundCeiling 取整
     *
     * @param value
     * @param digit 保留小数位位数
     * @return
     */
    public static String roundCeiling(double value, int digit) {
        return BigDecimal.valueOf(value).setScale(digit, BigDecimal.ROUND_CEILING).toString();
    }

    /**
     * roundCeiling 取整
     *
     * @param value
     * @return
     */
    public static String roundCeiling(double value) {
        return BigDecimal.valueOf(value).setScale(DEFAULT_SCALE, BigDecimal.ROUND_CEILING).toString();
    }

    /**
     * 取整
     *
     * @param value
     * @param digit        保留小数位位数
     * @param roundingMode 取整模式
     * @return
     */
    public static double round(double value, int digit, int roundingMode) {
        return BigDecimal.valueOf(value).setScale(digit, roundingMode).doubleValue();
    }

    /**
     * bigDecimal不定参数迭加
     *
     * @param first   first
     * @param amounts amounts
     * @return double
     */
    public static Double bigDecimalAddHandler(Double first, Double... amounts) {
        Double tmp = first;
        for (Double amount : amounts) {
            tmp = add(tmp, amount);
        }
        return tmp;
    }

    /**
     * bigDecimal不定参数迭加 修正null为0d
     *
     * @param first   first
     * @param amounts amounts
     * @return double
     */
    public static Double bigDecimalAddHandlerEx(Double first, Double... amounts) {
        Double tmp = null == first ? DOUBLE_ZERO : first;
        for (Double d : amounts) {
            tmp = addEx(tmp, d);
        }
        return tmp;
    }

    /**
     * bigDecimal不定参数迭乘 修正null为0d
     *
     * @param first   first
     * @param amounts amounts
     * @return double
     */
    public static Double bigDecimalMultiplyHandlerEx(Double first, Double... amounts) {
        if (null == first || first.equals(BIG_DECIMAL_ZERO.doubleValue())) {
            return DOUBLE_ZERO;
        }
        for (Double d : amounts) {
            if (null == d || d.equals(BIG_DECIMAL_ZERO.doubleValue())) {
                return DOUBLE_ZERO;
            }
        }
        Double temp = first;
        for (Double d : amounts) {
            temp = multiply(temp, d);
        }
        return temp;
    }

    /**
     * bigDecimal不定参数迭乘 修正null为0d
     *
     * @param first   first
     * @param amounts amounts
     * @return double
     */
    public static Double bigDecimalMultiplyHandler(double first, double... amounts) {
        double temp = first;
        for (double d : amounts) {
            temp = multiply(temp, d);
        }
        return temp;
    }

    /**
     * @param augend 被加数
     * @param addend 加数
     */
    public static Double addEx(Double augend, Double addend) {
        return addEx(augend, addend, true);
    }

    /**
     * 多参数相加
     *
     * @param addend
     * @return
     */
    public static Double addEx(Double... addend) {
        BigDecimal n1 = BigDecimal.valueOf(0.0d);
        BigDecimal n2;
        for (int i = 0; i < addend.length; i++) {
            n2 = BigDecimal.valueOf(addend[i]);
            n1 = n1.add(n2);
        }
        return n1.doubleValue();
    }

    /**
     * @param augend 被加数
     * @param addend 加数
     */
    public static BigDecimal bigDecimalAddEx(BigDecimal augend, BigDecimal addend) {
        if (augend != null && addend != null) {
            return BigDecimal.valueOf(addEx(augend.doubleValue(), addend.doubleValue(), true));
        } else {
            throw new RuntimeException("Addent must not be null!");
        }
    }

    /**
     * 多参数相加
     *
     * @param augends 加数
     */
    public static BigDecimal bigDecimalAddEx(BigDecimal... augends) {
        BigDecimal n1 = BigDecimal.valueOf(0.0d);
        for (BigDecimal augend : augends) {
            n1 = bigDecimalAddEx(n1, augend);
        }
        return n1;
    }

    /**
     * 加法 -- 修复null值为0D
     *
     * @param augend 被加数
     * @param addend 加数
     */
    public static Double addEx(Double augend, Double addend, boolean fixNull) {
        if (fixNull) {
            augend = null == augend ? DOUBLE_ZERO : augend;
            addend = null == addend ? DOUBLE_ZERO : addend;
        }
        BigDecimal n1 = BigDecimal.valueOf(augend);
        BigDecimal n2 = BigDecimal.valueOf(addend);
        BigDecimal value = n1.add(n2);

        return value.doubleValue();
    }

    /**
     * 减法 -- 修复null值为0D
     *
     * @param minuend    minuend
     * @param subtractor subtractor
     * @return Double
     */
    public static Double subtractEx(Double minuend, Double subtractor) {
        return subtractEx(minuend, subtractor, true);
    }

    /**
     * 减法 -- 修复null值为0D
     *
     * @param minuend
     * @param subtractor
     * @return
     */
    public static Double subtractEx(Double minuend, Double... subtractor) {
        Double tmp = null == minuend ? DOUBLE_ZERO : minuend;
        for (Double d : subtractor) {
            tmp = subtractEx(tmp, d);
        }
        return tmp;
    }

    /**
     * 减法 -- 修复null值为0D
     *
     * @param minuend    minuend
     * @param subtractor subtractor
     * @return Double
     */
    public static BigDecimal bigDecimalSubtractEx(BigDecimal minuend, BigDecimal subtractor) {
        if (minuend != null && subtractor != null) {
            return BigDecimal.valueOf(subtractEx(minuend.doubleValue(), subtractor.doubleValue(), true));
        } else {
            throw new RuntimeException("Subtrahend must not be null!");
        }
    }

    /**
     * 减法 -- 修复null值为0D
     *
     * @param minuend    minuend
     * @param subtractor subtractor
     * @param fixNull    fixNull
     * @return Double
     */
    public static Double subtractEx(Double minuend, Double subtractor, boolean fixNull) {
        if (fixNull) {
            minuend = null == minuend ? DOUBLE_ZERO : minuend;
            subtractor = null == subtractor ? DOUBLE_ZERO : subtractor;
        }
        return subtract(minuend, subtractor);
    }


    /**
     * 金额格式化过滤,ROUND_UP模式并且保留两位小数
     *
     * @param df     df
     * @param amount amount
     * @return string
     */
    public static String formatAmount(DecimalFormat df, Double amount) {
        return formatAmount(df, amount, 2, BigDecimal.ROUND_UP);
    }

    /**
     * 默认保留两位小数,ROUND_UP模式
     *
     * @param amount amount
     * @return Double
     */
    public static Double digitDefault(Double amount) {
        return round(amount, 2, BigDecimal.ROUND_UP);
    }

    /**
     * 金额格式化过滤
     *
     * @param digit     digit
     * @param df        df
     * @param amount    amount
     * @param roundMode roundMode
     * @return string
     */
    public static String formatAmount(DecimalFormat df, Double amount, int digit, int roundMode) {
        return df.format(round(amount, digit, roundMode));
    }


    /**
     * 默认金额格式化 -- DecimalFormat:0.00 ,保留两位小数 , ROUND_UP模式
     *
     * @param amount amount
     * @return String
     */
    public static String defaultFormatAmount(Double amount) {
        return DEFAULT_DECIMALFORMAT.format(digitDefault(amount));
    }

    /**
     * 元转分
     *
     * @param value value
     * @return double
     */
    public static double yuan2cent(double value) {
        return multiply(value, 100D);
    }

    /**
     * 分转元
     *
     * @param value value
     * @return double
     */
    public static double cent2yuan(double value) {
        return divide(value, 100D);
    }


    /**
     * 分转元
     *
     * @param value value
     * @return double
     */
    public static double cent2yuan(Number value) {
        return divide(value.doubleValue(), 100D);
    }

    /**
     * 判断是否为0, 精度为10的-6次方
     *
     * @param value value
     * @return boolean
     */
    public static boolean isZero(double value) {
        return Math.abs(value - 0) < 1e-6;
    }

    /**
     * 判断是否为0, 精度为10的-6次方
     * 判NULL
     * @param value value
     * @return boolean
     */
    public static boolean isZeroHandlEx(Double value) {

        Double tmp = null == value ? DOUBLE_ZERO : value;

        return Math.abs(tmp - 0) < 1e-6;
    }

    /**
     * 判断是否为0, 精度为10的-6次方
     *
     * @param value value
     * @return boolean
     */
    public static boolean isZeroEx(double value) {
        return isEqual(value, DOUBLE_ZERO);
    }

    /**
     * 判断是否为0, 精确判断
     *
     * @param value value
     * @return boolean
     */
    public static boolean isZeroExact(double value) {
        return Double.compare(value, DOUBLE_ZERO) == 0;
    }

    /**
     * 比较浮点数是否相等  -- 差距值可控
     *
     * @param d1    浮点数1
     * @param d2    浮点数2
     * @param delta 差距值，一般是足够小的小数，如1e-6
     * @return boolean
     */
    public static boolean isEqual(double d1, double d2, double delta) {
        if (Double.compare(d1, d2) == 0) {
            return true;
        }
        return (Math.abs(d1 - d2) <= delta);
    }

    /**
     * 比较浮点数是否相等  -- 差距值为 1e-6
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean isEqual(double d1, double d2) {
        if (Double.compare(d1, d2) == 0) {
            return true;
        }
        return (Math.abs(d1 - d2) <= DEFAULT_DELTA);
    }

    /**
     * 比较浮点数是否相等  -- 差距值为 1e-6
     * 判NULL
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean isEqualHandlerEx(Double d1, Double d2) {
        Double tmp1 = null == d1 ? DOUBLE_ZERO : d1;
        Double tmp2 = null == d2 ? DOUBLE_ZERO : d2;
        if (Double.compare(tmp1, tmp2) == 0) {
            return true;
        }
        return (Math.abs(tmp1 - tmp2) <= DEFAULT_DELTA);
    }

    /**
     * 比较浮点数是否相等  精确相等
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean isEqualExact(double d1, double d2) {
        return Double.compare(d1, d2) == 0;
    }

    /**
     * 比较两个浮点数的大小 -- d1大于d2
     *
     * @param d1    浮点数1
     * @param d2    浮点数2
     * @param delta 差距值，一般是足够小的小数，如1e-6
     * @return boolean
     */
    public static boolean moreThan(double d1, double d2, double delta) {
        return Double.compare(d1, d2) == 1 || d1 - d2 > delta;
    }

    /**
     * 比较两个浮点数的大小 -- d1大于d2
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean moreThan(double d1, double d2) {
        return Double.compare(d1, d2) == 1 || d1 - d2 > DEFAULT_DELTA;
    }

    /**
     * 比较两个浮点数的大小 d1小于d2
     *
     * @param d1    浮点数1
     * @param d2    浮点数2
     * @param delta 差距值，一般是足够小的小数，如1e-6
     * @return boolean
     */
    public static boolean lessThan(double d1, double d2, double delta) {
        return Double.compare(d1, d2) == -1 || d1 - d2 < delta;
    }

    /**
     * 比较两个浮点数的大小 -- d1小于d2
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean lessThan(double d1, double d2) {
        return Double.compare(d1, d2) == -1 || d1 - d2 < DEFAULT_DELTA;
    }

    /**
     * 精确比较两个浮点数的大小  -- 精准大于 d1大于d2
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean moreThanExact(double d1, double d2) {
        return Double.compare(d1, d2) == 1;
    }

    /**
     * 精确比较两个浮点数的大小  -- 精准小于 d1小于d2
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean lessThanExact(double d1, double d2) {
        return Double.compare(d1, d2) == -1;
    }

    /**
     * 比较两个浮点数的大小 d1大于等于d2
     *
     * @param d1    浮点数1
     * @param d2    浮点数2
     * @param delta 差距值，一般是足够小的小数，如1e-6
     * @return boolean
     */
    public static boolean moreThanEq(double d1, double d2, double delta) {
        return Double.compare(d1, d2) == 1 || Double.compare(d1, d2) == 0 || d1 - d2 >= delta;
    }

    /**
     * 比较两个浮点数的大小 d1大于等于d2
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean moreThanEq(double d1, double d2) {
        return Double.compare(d1, d2) == 1 || Double.compare(d1, d2) == 0 || d1 - d2 >= DEFAULT_DELTA;
    }

    /**
     * 比较两个浮点数的大小 d1小于等于d2
     *
     * @param d1    浮点数1
     * @param d2    浮点数2
     * @param delta 差距值，一般是足够小的小数，如1e-6
     * @return boolean
     */
    public static boolean lessThanEq(double d1, double d2, double delta) {
        return Double.compare(d1, d2) == -1 || Double.compare(d1, d2) == 0 || d1 - d2 <= delta;
    }

    /**
     * 比较两个浮点数的大小  d1小于等于d2
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean lessThanEq(double d1, double d2) {
        return Double.compare(d1, d2) == -1 || Double.compare(d1, d2) == 0 || d1 - d2 <= DEFAULT_DELTA;
    }

    /**
     * 比较两个浮点数的大小  d1小于等于d2
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean lessThanEqEx(Double d1, Double d2) {

        d1 = null == d1 ? DOUBLE_ZERO : d1;
        d2 = null == d2 ? DOUBLE_ZERO : d2;
        return Double.compare(d1, d2) == -1 || Double.compare(d1, d2) == 0 || d1 - d2 <= DEFAULT_DELTA;
    }

    /**
     * 比较两个浮点数的大小 -- 精准的 d1大于等于d2
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean moreThanEqExact(double d1, double d2) {
        return Double.compare(d1, d2) == 1 || Double.compare(d1, d2) == 0;
    }

    /**
     * 比较两个浮点数的大小 -- 精准的 d1小于等于d2
     *
     * @param d1 浮点数1
     * @param d2 浮点数2
     * @return boolean
     */
    public static boolean lessThanEqExact(double d1, double d2) {
        return Double.compare(d1, d2) == -1 || Double.compare(d1, d2) == 0;
    }


    /**
     * 向上取整，保留整数
     *
     * @param v v
     * @return double
     */
    public static double integerUp(double v) {
        return BigDecimal.valueOf(v).setScale(0, RoundingMode.UP).doubleValue();
    }

    /**
     * 乘方 0^0 没有意义：请注意不要让baseNumber 为 0 null
     * @param baseNumber 基数
     * @param exponent 指数
     * @return
     */
    public static double pow(Double baseNumber, Integer exponent) {
        if (null == baseNumber || BigDecimal.ZERO.compareTo(new BigDecimal(baseNumber)) == 0) {
            return DOUBLE_ZERO;
        }
        if (null == exponent || INTEGER_ZERO == exponent) {
            return DOUBLE_ONE;
        }
        BigDecimal n1 = BigDecimal.valueOf(baseNumber);
        BigDecimal value = n1.pow(exponent);

        return value.doubleValue();
    }


}
