package tycoon.business;

public class NewspaperDelivery extends ItemMenu {

    public NewspaperDelivery() { super("Newspaper Delivery", 60, 0, 60, 3000L, 1.15); }

    @Override public String getEmojiName() { return "newspaper"; }
}
