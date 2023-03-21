package Domain;

public enum ContestType {
    SWIMMING(1),
    RUNNING(2),
    CYCLING(3);

    private final int value;
    ContestType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ContestType fromValue(int value) {
        for (var raceType : ContestType.values()) {
            if (raceType.getValue() == value) {
                return raceType;
            }
        }
        return null;
    }

}
