package tycoon.progression;

public class Unlocks {
    private String imagePath;
    private String nome;
    private long price;
    private boolean unlocked;

    public Unlocks(String nome, long price, String imagePath) {
        this.nome = nome;
        this.price = price;
        this.imagePath = imagePath;
        this.unlocked = false;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getNome() {
        return nome;
    }

    public long getPrice() {
        return price;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void unlock() {
        this.unlocked = true;
    }

}
