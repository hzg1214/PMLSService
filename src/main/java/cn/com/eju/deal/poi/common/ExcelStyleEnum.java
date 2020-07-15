package cn.com.eju.deal.poi.common;

/**
 * Excel每行的显示样式
 */
public enum ExcelStyleEnum {
    HEADER("header"),
    RATE_RIGHT("percent_right"),
    RATE_LEFT("percent_left"),
    RATE_CENTER("percent_center"),
    BODY("body"),
    BODY_INT("body_int"),
    BODY_BLACK("body_black"),
    BODY_RED("body_red"),
    BODY_GREEN("body_green"),
    MONEY_UNLOCKED_BLACK("money_unlocked_black"),
    MONEY_UNLOCKED_RED("money_unlocked_red"),
    MONEY_UNLOCKED_GREEN("money_unlocked_green"),
    MONEY_LOCKED_BLACK("money_locked_black"),
    MONEY_LOCKED_RED("money_locked_red"),
    DATE_BLACK("date_black"),
    DATE_RED("date_red"),
    DATE_GREEN("date_green");

    private String value;

    ExcelStyleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
