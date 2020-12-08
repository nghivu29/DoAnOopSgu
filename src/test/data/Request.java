package test.data;

import java.time.LocalDate;

public class Request extends Tracking {
    /**
     * các thuộc tính
     */
    private String bookName;
    private LocalDate dateRequest;
    private boolean haveBook;

    public static Request create(User user, String bookName){
        Request request = new Request();
        request.setUser(user);
        request.setCard(user.getCard());
        request.setBookName(bookName);
        request.setDateRequest(LocalDate.now());
        request.setHaveBook(false);
        return request;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                "bookName='" + bookName + '\'' +
                ", dateRequest=" + dateRequest +
                ", haveBook=" + haveBook +
                ", username=" + user.getName() +
                '}';
    }

    // CÁC GETTER VÀ SETTER

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public boolean isHaveBook() {
        return haveBook;
    }

    public void setHaveBook(boolean haveBook) {
        this.haveBook = haveBook;
    }
}
