package test.data;

public class Book extends LibObject {

    /**
     * các thuộc tính
     *
     */
    protected Author author;
    protected Imprint imprint;
    protected int publishYear;
    protected int pageNumber;
    protected double price;
    protected BookClassification bookClassification;
    protected BookStatus bookStatus;
    protected BorrowStatus borrowStatus;
    protected String description;

    public static Book create(String name, double price){
        Book book = new Book();
        book.name = name;
        book.price = price;
        book.borrowStatus = BorrowStatus.AVAILABLE;
        book.bookStatus = BookStatus.LIKE_NEW;

        if (!book.init()){
            return null;
        }
        return book;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;
        // code phần định nghĩa sách ở đây

        return true;
    }

    // CONSTRUCTOR

    protected Book() {
    }


    // CÁC GETTER VÀ SETTER

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Imprint getImprint() {
        return imprint;
    }

    public void setImprint(Imprint imprint) {
        this.imprint = imprint;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookClassification getBookClassification() {
        return bookClassification;
    }

    public void setBookClassification(BookClassification bookClassification) {
        this.bookClassification = bookClassification;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public BorrowStatus getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(BorrowStatus borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("|%20s|%20s|%20s|%20s|%20s|%20s|%20s|"
        , id, name, price, borrowStatus, bookStatus, author, bookClassification);
    }
}

