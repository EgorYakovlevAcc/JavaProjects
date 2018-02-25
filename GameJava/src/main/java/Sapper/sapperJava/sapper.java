package Sapper.sapperJava;

public enum sapper {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMED;

    public Object image;

    sapper nextNumber() {
        return sapper.values()[this.ordinal() + 1];
    }
}
