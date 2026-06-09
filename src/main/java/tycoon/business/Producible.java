package tycoon.business;

import tycoon.model.User;

public interface Producible {
    void startProduction();
    boolean isReady();
    void receberLucro(User user);
    String getEmojiName();
}
