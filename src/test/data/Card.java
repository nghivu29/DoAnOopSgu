package test.data;

import java.time.LocalDate;

public class Card extends LibObject{


    /**
     * các thuộc tính
     *
     */
    protected LocalDate valid_from;
    protected LocalDate valid_thru;
    protected String address;
    protected String phone;
    protected Power power;
    protected CardStatus cardStatus;

    public static Card create(String name, String phone){
        Card card = new Card();
        card.setName(name);
        card.setPhone(phone);

        if (!card.init()){
            return null;
        }
        return card;
    }

    @Override
    public boolean init() {
        if (!super.init()) {
            return false;
        }

        // code phần định nghĩa ở đây
        setValid_from(LocalDate.now());
        setValid_thru(getValid_from().plusDays(MyDefine.VALID_TIME));
        setCardStatus(CardStatus.NORMAL);
        setPower(Power.GUEST);

        return true;
    }

    // GETTER AND SETTER

    public LocalDate getValid_from() {
        return valid_from;
    }

    public void setValid_from(LocalDate valid_from) {
        this.valid_from = valid_from;
    }

    public LocalDate getValid_thru() {
        return valid_thru;
    }

    public void setValid_thru(LocalDate valid_thru) {
        this.valid_thru = valid_thru;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    @Override
    public String toString() {
        return "Card{" +
                "valid_from=" + valid_from +
                ", valid_thru=" + valid_thru +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", power=" + power +
                ", cardStatus=" + cardStatus +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
