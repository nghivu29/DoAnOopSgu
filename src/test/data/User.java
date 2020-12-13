package test.data;

import java.io.Serializable;

public class User extends LibObject{
    /**
     * các thuộc tính của user
     *
     */
    private String password;
    private Card card;


    public static User create(Card card, String password){
        User user = new User();
        user.setCard(card);
        user.setPassword(password);
        if (!user.init()){
            return null;
        }
        return user;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // code phần định nghĩa ở dưới đây
        this.name = card.getName();

        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return String.format("|%20s|%20s|%20s|%20s|%20s|%20s|",
                id, name, password, card.power, card.cardStatus, card.phone);
    }
}
