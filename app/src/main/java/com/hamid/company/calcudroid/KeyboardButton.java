package com.hamid.company.calcudroid;

/**
 * Created by Mohamed on 3/6/2015.
 */
public enum KeyboardButton {

    ZERO("0", KeyboardButtonCategory.NUM), ONE("1", KeyboardButtonCategory.NUM),
    TWO("2", KeyboardButtonCategory.NUM), THREE("3", KeyboardButtonCategory.NUM),
    FOUR("4",KeyboardButtonCategory.NUM), FIVE("5", KeyboardButtonCategory.NUM),
    SIX("6", KeyboardButtonCategory.NUM), SEVEN("7", KeyboardButtonCategory.NUM),
    EIGHT("8", KeyboardButtonCategory.NUM), NINE("9", KeyboardButtonCategory.NUM),
     DECPNT(".", KeyboardButtonCategory.NUM), C("C", KeyboardButtonCategory.OPT),
    R("DEL", KeyboardButtonCategory.OPT), PLUS("+", KeyboardButtonCategory.OPT),
    MINUS("-", KeyboardButtonCategory.OPT), DIV("/", KeyboardButtonCategory.OPT),
    MUL("x", KeyboardButtonCategory.OPT), BRCKT("( )", KeyboardButtonCategory.OPT),
    ANS("=", KeyboardButtonCategory.OPT), DUMMY("", KeyboardButtonCategory.OPT);

    String buttonText;
    KeyboardButtonCategory category;

    KeyboardButton(String buttonText, KeyboardButtonCategory category){
        this.buttonText = buttonText;
        this.category = category;
    }

    public String getButtonText() {
        return buttonText;
    }

    public KeyboardButtonCategory getCategory() {
        return category;
    }
}
