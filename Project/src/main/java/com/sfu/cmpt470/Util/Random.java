package com.sfu.cmpt470.Util;

import java.security.SecureRandom;
import java.util.Locale;

public class Random {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String LOWER = UPPER.toLowerCase(Locale.ROOT);

    private static final String DIGITS = "0123456789";

    private static final int LENGTH = 21;

    private static final String ALPHANUM = UPPER + LOWER + DIGITS;

    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        char[] buf = new char[LENGTH];
        char[] symbols = ALPHANUM.toCharArray();
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}
