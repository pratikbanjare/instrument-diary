package com.pratik.instrument.fetch.utils;

public class SpecificationHandler {

    public static String specHeaderToCamel (String header) {

        StringBuilder stringBuilder = new StringBuilder();

        boolean nextCapital = false;

        for (int i = 0; i < header.length(); ++i) {
            if (Character.isLetter(header.charAt(i))) {
                char c = header.charAt(i);
                if (nextCapital) {
                    stringBuilder.append(Character.toUpperCase(c));
                } else {
                    stringBuilder.append(Character.toLowerCase(c));
                }
                nextCapital = false;
            } else {
                nextCapital = true;
            }

        }
        return stringBuilder.toString();
    }
}
