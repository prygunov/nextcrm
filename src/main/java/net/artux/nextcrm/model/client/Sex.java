package net.artux.nextcrm.model.client;

public enum Sex {
    Male("Мужской"),
    Female("Женский");

    private final String title;

    Sex(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
