package test.data;

import java.time.LocalDate;

public class Tracking extends LibAction{
    /**
     * các thuộc tính
     *
     */
    private Book book;
    private Card card;
    private LocalDate dateBorrow;
    private LocalDate dateReturn;
    private LocalDate dateReturnDeadline;
    private BookStatus bookStatusBefore;
    private BookStatus bookStatusAfter;
    private String description;

    public static Tracking create(User admin, Request request){
        request.getBook().setBorrowStatus(BorrowStatus.UNAVAILABLE);
        Tracking tracking = new Tracking();
        tracking.setUser(admin);
        tracking.setBook(request.getBook());
        tracking.setCard(request.getCard());
        tracking.setDateBorrow(LocalDate.now());
        tracking.setDateReturnDeadline(LocalDate.now().plusDays(20));
        tracking.setBookStatusBefore(request.getBookStatusBefore());
        return tracking;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "book_id=" + book.getId() +
                //", borrower=" + card.getName() +
                ", admin_deliver=" + user.getName() +
                ", dateBorrow=" + dateBorrow +
                ", dateReturn=" + dateReturn +
                ", dateReturnDeadline=" + dateReturnDeadline +
                ", bookStatusBefore=" + bookStatusBefore +
                ", bookStatusAfter=" + bookStatusAfter +
                '}';
    }

    // CÁC SETTER VÀ GETTER

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(LocalDate dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public LocalDate getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(LocalDate dateReturn) {
        this.dateReturn = dateReturn;
    }

    public LocalDate getDateReturnDeadline() {
        return dateReturnDeadline;
    }

    public void setDateReturnDeadline(LocalDate dateReturnDeadline) {
        this.dateReturnDeadline = dateReturnDeadline;
    }

    public BookStatus getBookStatusBefore() {
        return bookStatusBefore;
    }

    public void setBookStatusBefore(BookStatus bookStatusBefore) {
        this.bookStatusBefore = bookStatusBefore;
    }

    public BookStatus getBookStatusAfter() {
        return bookStatusAfter;
    }

    public void setBookStatusAfter(BookStatus bookStatusAfter) {
        this.bookStatusAfter = bookStatusAfter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
