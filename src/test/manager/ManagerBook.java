package test.manager;

import test.data.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerBook extends ManagerLibObject<Book> {
    public ManagerBook() {
        super();
    }

    public ManagerBook getBooksByClassification(BookClassification classification){
        return null;
    }

    public Book getBookById(String id){
        for(Book book : list){
            if (book.getId().equals(id))
                return book;
        }
        return null;
    }

    public ManagerBook getBooksByBorrowStatus(BorrowStatus status){
        ManagerBook managerBook = new ManagerBook();
        for(Book book : list){
            if (book.getBorrowStatus()==status)
                managerBook.list.add(book);
        }
        return managerBook;
    }

    public ManagerBook getBooksByBookStatus(BookStatus status){
        return null;
    }

    public ManagerBook getBooksByName(String name) {
        ManagerBook managerBook = new ManagerBook();
        getElementsByName(name).list.forEach(book -> managerBook.list.add(book));
        return managerBook;
    }

    /**
     * code đọc file cần đọc tất cả thông tin và tạo đối tượng tương ứng với thông tin đó để thêm vào danh sách
     * code ở dưới tạo nhanh vài giá trị để test
     * @return
     */
    @Override
    public boolean loadData() {
//        this.add(Book.create("Sach Den", 1000));
//        this.add(Book.create("Sach Trang", 0));
//        this.add(Book.create("Sach Trang", 0));
//        this.add(Book.create("Sach Giao Khoa", 0));
//        this.add(Book.create("Sach Trang", 0));

        List<Book> listRequest = null;

        try{
            FileInputStream readData = new FileInputStream("data_book.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            listRequest = (ArrayList<Book>) readStream.readObject();
            readStream.close();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("system: Data rỗng. Bắt đầu tạo mới");
            new ManagerBook().saveData();
            return false;
        }
        list = listRequest;

        return true;
    }

    /**
     * Cái này t copy trên mạng chưa test nữa
     * @return
     */
    @Override
    public boolean saveData() {
        try{
            FileOutputStream writeData = new FileOutputStream("data_book.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(list);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
