package com.hamid.company.calcudroid;

/**
 * Created by Mohamed on 3/6/2015.
 */
public enum KeyboardButton {

    ZERO("0"), ONE("1"),
    TWO("2"), THREE("3"),
    FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"),
    EIGHT("8"), NINE("9"),
    DECPNT("."),DEL("DEL"),
    PLUS("+"),CLSDBRCKT(")"),
    MINUS("-"), DIV("/"),
    MUL("*"), OPNBRCKT("("),
    ANS("="), DUMMY("");

    String buttonText;

    KeyboardButton(String buttonText){
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }

}
