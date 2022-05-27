package com.misael.escuelabd;

public class Utilities {

    public static String validate(String string) {
        if (string == null && string.isBlank()) {
            throw new NullPointerException("El string no es válido");
        } else {
            return string;
        }
    }

    public static int validate(int index) {
        if (index == 0) {
            throw new NumberFormatException("El número no es válido");
        } else {
            return index;
        }
    }

}
