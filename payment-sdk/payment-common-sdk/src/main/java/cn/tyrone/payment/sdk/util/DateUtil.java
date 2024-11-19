package cn.tyrone.payment.sdk.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * 日期工具类
 */
public class DateUtil {

    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Pattern.NORM_DATE_PATTERN.getPattern());
//        LocalDateTime localDateTime = LocalDateTime.now();
//        String format = localDateTime.format(dateTimeFormatter);
//        System.out.println(format);

//        LocalDateTime localDateTime = LocalDateTime.now();
//        localDateTime = localDateTime.minusDays(1);
//
//        String format = localDateTime.format(dateTimeFormatter);
//        System.out.println(format);

        LocalDate localDateTime = getLocalDate("2021-04-09", Pattern.NORM_DATE_CLOSE_FORMAT);
        System.out.println(localDateTime);

    }

    /**
     * 获取日期格式化器
     * @param pattern
     * @return
     */
    private static DateTimeFormatter getDateTimeFormatter(Pattern pattern){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern.getPattern());
        return dateTimeFormatter;
    }

    /**
     * 获取当前日期字符
     * 默认日期格式: yyyy-MM-dd
     * @return
     */
    public static String getLocalDate(){
        String currentTime = getLocalDateTime(Pattern.NORM_DATE_PATTERN);
        return currentTime;
    }

    /**
     * 获取当前时间字符
     * 默认日期格式: yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getLocalDateTime(){
        String currentTime = getLocalDateTime(Pattern.NORM_DATETIME_PATTERN);
        return currentTime;
    }

    /**
     * 获取指定日期格式的日期字符串
     * @param pattern
     * @return
     */
    public static String getLocalDateTime(Pattern pattern){
        LocalDateTime localDateTime = LocalDateTime.now();
        String localDateTimeString = getLocalDateTime(localDateTime, pattern);
        return localDateTimeString;
    }

    /**
     * 获取指定日期格式的日期字符串
     * @param pattern
     * @return
     */
    public static String getLocalDateTime(LocalDateTime localDateTime, Pattern pattern){
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);
        String currentTime = localDateTime.format(dateTimeFormatter);
        return currentTime;
    }

    /**
     * 获取指定日期格式的日期字符串
     * @param pattern
     * @return
     */
    public static String getLocalDate(LocalDate localDate, Pattern pattern){
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(pattern);
        String currentTime = localDate.format(dateTimeFormatter);
        return currentTime;
    }

    /**
     * 根据指定字符串获取指定格式的日期
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static LocalDateTime getLocalDateTime(String localDateTime, Pattern pattern) {
        LocalDateTime parse = LocalDateTime.parse(localDateTime, getDateTimeFormatter(pattern));
        return parse;
    }

    /**
     * 根据指定字符串获取指定格式的日期
     * @param localDate
     * @param pattern
     * @return
     */
    public static LocalDate getLocalDate(String localDate, Pattern pattern) {
        LocalDate parse = LocalDate.parse(localDate, getDateTimeFormatter(pattern));
        return parse;
    }

    /**
     * 指定日期加减天数
     * @param localDate
     * @param pattern
     * @return
     */
    public static String plusDays(LocalDate localDate, Pattern pattern, int days){

        String localDateString = getLocalDate(localDate.plusDays(days), pattern);

        return localDateString;

    }

    /**
     * 判断当前日期是否是周末
     * @param localDate
     * @return
     */
    public static boolean ifWeekday(LocalDate localDate) {
        boolean ifWeekday = false;

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            ifWeekday = true;
        }

        return ifWeekday;
    }

    /**
     * 当前日期是否是法定个假日
     * @param localDate
     * @return
     */
    public static boolean ifHoliday(LocalDate localDate) {
        boolean ifHoliday = false;
        List<String> holidays = Holidays.lawHolidays;
        if (holidays.contains(localDate.toString())) {
            ifHoliday = true;
        }

        return ifHoliday;
    }

    public static class Holidays {

        public static List<String> lawHolidays = Arrays.asList(
                "2021-06-12","2021-06-13","2021-06-14","2021-09-19","2021-09-20","2021-09-21",
                "2021-10-01","2021-10-02","2021-10-03","2021-10-04","2021-10-05","2021-10-06","2021-10-07"
        );

    }

    /**
     * 日期格式枚举类
     */
    public enum Pattern{

        NORM_DATE_PATTERN("yyyy-MM-dd"),
        NORM_DATETIME_PATTERN("yyyy-MM-dd HH:mm:ss"),
        NORM_DATE_CLOSE_FORMAT("yyyyMMdd"),
        NORM_DATETIME_CLOSE_PATTERN("yyyyMMddHHmmss"),
        TIME_STAMP_1("HHmmss")
        
        ;

        Pattern(String pattern) {
            this.pattern = pattern;
        }

        /**
         * 日期格式
         */
        private String pattern;

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }
    }


}
