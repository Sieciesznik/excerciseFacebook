package facebook;

public enum Gender {
    MALE, FEMALE;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
