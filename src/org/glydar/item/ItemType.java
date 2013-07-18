package org.glydar.item;

public enum ItemType {

    TEST(1),
    TEST2(2);

    private final int type;

    private ItemType(int type) {
        this.type = type;
    }

    public int value() {
        return type;
    }

}
