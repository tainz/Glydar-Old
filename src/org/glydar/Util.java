package org.glydar;

import java.util.ArrayList;
import java.util.List;

public final class Util {

    public static List<Byte> toByteList(byte[] arr) {

        List<Byte> byteList = new ArrayList<Byte>();

        for (byte b : arr) {
            byteList.add(new Byte(b));
        }

        return byteList;

    }

}
