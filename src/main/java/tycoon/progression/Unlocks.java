package tycoon.progression;

public class Unlocks {
    private String imagePath;
    private String nome;
    private long price;
    private boolean unlocked;
    private String preco;

    public Unlocks(String nome, long price,String preco ,String imagePath) {
        this.nome = nome;
        this.price = price;
        this.preco = preco;
        this.imagePath = imagePath;
        this.unlocked = false;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getNome() {
        return nome;
    }

    public String getPreco() {
        return preco;
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
