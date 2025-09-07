package TestData;

public enum GeneralNamings {
    CONFIRMATION_TEXT("Thankyou for the order.");

    private final String value;

    GeneralNamings(String value) {
        this.value = value;
    }

    public String get(){
        return value;
    }

    @Override
    public String toString(){
        return value;
    }
}
