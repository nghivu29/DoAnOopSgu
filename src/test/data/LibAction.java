package test.data;

import java.io.Serializable;

public class LibAction implements InitProtocol, Serializable {
    /**
     * các thuộc tính
     */
    protected User user;
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static LibAction create(User user){
        LibAction libAction = new LibAction();
        libAction.setUser(user);
        if (!libAction.init())
            return null;
        return libAction;
    }

    /**
     *
     * @return - true nếu thẻ không bị block
     */
    @Override
    public boolean init() {
        if (user.getCard().getCardStatus() == CardStatus.BLOCK)
            return false;
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
