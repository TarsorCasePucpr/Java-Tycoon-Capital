package tycoon.business;

public class PizzaDelivery extends ItemMenu {

    public PizzaDelivery() { super("Pizza Delivery", 8640, 0, 4320, 12000L, 1.13); }

    @Override public String getEmojiName() { return "pizza"; }
}
