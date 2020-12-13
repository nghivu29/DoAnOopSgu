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
    private User borrower;
    private User receiver;
    private User deliver;

    public static Tracking create(User admin, Request request){
        request.getBook().setBorrowStatus(BorrowStatus.UNAVAILABLE);
        User unknown = new User();
        unknown.setName("unknown");
        Tracking tracking = new Tracking();
        tracking.setUser(admin);
        tracking.setDeliver(unknown);
        tracking.setReceiver(unknown);
        tracking.setBorrower(request.getUser());
        tracking.setBook(request.getBook());
        tracking.setCard(request.getCard());
        tracking.setDateBorrow(LocalDate.now());
        tracking.setDateReturnDeadline(LocalDate.now().plusDays(20));
        tracking.setBookStatusBefore(request.getBookStatusBefore());
        return tracking;
    }

    @Override
    public String toString() {
        return String.format("|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|",
                deliver.getName(), receiver.getName(), borrower.getName(), book.getId(), book.getName(), dateBorrow, dateReturnDeadline, dateReturn, bookStatusBefore, bookStatusAfter);
    }

    // CÁC SETTER VÀ GETTER

    public Book getBook() {
        return book;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getDeliver() {
        return deliver;
    }

    public void setDeliver(User deliver) {
        this.deliver = deliver;
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

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }
}
