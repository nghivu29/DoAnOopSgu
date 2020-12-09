package test.manager;

import test.data.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerBook extends ManagerLibObject<Book> {
    public ManagerBook() {
        super();
    }

    public ManagerBook getBooksByClassification(BookClassification classification){
        return null;
    }

    public Book getBookById(String id){
        for (int i = 0; i < length; i++) {
            if (get(i).getId().equals(id))
                return get(i);
        }
        return null;
    }

    public ManagerBook getBooksByBorrowStatus(BorrowStatus status){
        ManagerBook managerBook = new ManagerBook();

        for (int i = 0; i < length; i++) {
            if (get(i).getBorrowStatus()==status)
                managerBook.add(get(i));
        }
        return managerBook;
    }

    public ManagerBook getBooksByBookStatus(BookStatus status){
        return null;
    }

    public ManagerBook getBooksByName(String name) {
        ManagerBook managerBook = new ManagerBook();
        for (int i = 0; i < length; i++) {
            if (get(i).getName().equals(name))
                managerBook.add(get(i));
        }
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

        ManagerBook managerBook = new ManagerBook();

        try{
            FileInputStream readData = new FileInputStream("data_book.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            managerBook = (ManagerBook) readStream.readObject();
            readStream.close();
        }catch (Exception e) {
            System.out.println("system: Data rỗng. Bắt đầu tạo mới");
            new ManagerBook().saveData();
            return false;
        }

        list = managerBook.getList();
        length = managerBook.length;

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

            writeStream.writeObject(this);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
