package TestData;

public enum ProductNames {
    ZARA("ZARA COAT 3");

    private final String value;

    ProductNames(String value) {
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
