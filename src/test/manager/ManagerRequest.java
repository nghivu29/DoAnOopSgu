package test.manager;

import test.data.Book;
import test.data.BorrowStatus;
import test.data.Request;
import test.data.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerRequest extends ManagerLibAction<Request> {

    /**
     * phương thức chưa hoàn thành
     * đùng để lấy tất cả các yêu cầu của 1 cuốn sách nào đó
     * @param name
     * @return
     */
    public ManagerRequest getRequestsByBookName(String name){
        return null;
    }

    /**
     * phương thức chưa hoàn thành
     * lấy tất cả các yêu cầu theo ngày
     * @param date
     * @return một new ManagerRequest
     */
    public ManagerRequest getRequestsByDate(LocalDate date){
        return null;
    }

    /**
     * phương thức đựa vào phương thức getRequestByDate ở trên
     * @return
     */
    public ManagerRequest getRequestsToday(){
        return getRequestsByDate(LocalDate.now());
    }

    /**
     * phương thức chưa hoàn thành
     * dùng đẻ chấp nhận yêu cầu mượn của các user
     * @param request
     * @return true nếu trạng thái sách phù hợp để có thể cho mượn
     */
    boolean acceptRequest(Request request){
        ManagerBook managerBook = new ManagerBook();
        managerBook.loadData();
        ManagerBook managerBook2 = managerBook.getBooksByName(request.getBookName());
        if (managerBook2 == null) return false;
        ManagerBook managerBook3 = managerBook2.getBooksByBorrowStatus(BorrowStatus.AVAILABLE);
        if (managerBook3 == null) return false;
        if (managerBook3.size() > 0){
            Book book = managerBook3.list.get(0);
            request.setHaveBook(true);
            request.setBook(book);
            request.setBookStatusBefore(book.getBookStatus());
            book.setBorrowStatus(BorrowStatus.RESERVED);
            //managerBook.getBookById(book.getId()).setBorrowStatus(BorrowStatus.RESERVED);
            managerBook.saveData();
            saveData();
            return true;
        }
        return false;
    }

    public void acceptAllRequest(){
        list.forEach(this::acceptRequest);
    }

    public ManagerRequest getRequestsByUser(User user) {
        ManagerRequest manager = new ManagerRequest();
        manager.loadData();
        manager.list = list.stream()
                .filter(request -> request.getUser().getName().equals(user.getName()))
                .collect(Collectors.toList());
        return manager;
    }

    @Override
    public boolean loadData() {
        List<Request> listRequest = new ArrayList<>();

        try{
            FileInputStream readData = new FileInputStream("data_request.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            listRequest = (ArrayList<Request>) readStream.readObject();
            readStream.close();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("system: Data rỗng. Bắt đầu tạo mới");
            new ManagerRequest().saveData();
            return false;
        }
        list = listRequest;

        return true;
    }

    @Override
    public boolean saveData() {
        try{
                FileOutputStream writeData = new FileOutputStream("data_request.txt");
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

    public Request getRequestsById(String code) {
        return list.stream().filter(request -> request.getId().equals(code))
                .findFirst().get();
    }
}
