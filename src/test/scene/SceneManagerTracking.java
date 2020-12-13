package test.scene;

import test.data.*;
import test.manager.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class SceneManagerTracking extends MenuHelper{
    private final User user;

    public SceneManagerTracking(User user) {
        this.user = user;
    }

    public static SceneManagerTracking create(User user) {
        SceneManagerTracking scene = new SceneManagerTracking(user);
        if (!scene.init())
            return null;
        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // khởi tạo cho scene
        setName("SceneManagerTracking");

        addFunction(this::showMenu);
        addFunction(this::viewAllTracking);
        addFunction(this::deliverBook);
        addFunction(this::receiveBook);
        addFunction(this::backToMenuAdmin);

        return true;
    }

    private void deliverBook() {
        ManagerTracking managerTracking = new ManagerTracking();
        managerTracking.loadData();

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã mượn sách (là id của Request): ");
        String code = sc.nextLine();

        ManagerRequest managerRequest = new ManagerRequest();
        managerRequest.loadData();
        Request request = null;

        ManagerBook managerBook = new ManagerBook();
        managerBook.loadData();

        try {
            request = managerRequest.getRequestsById(code);
        }catch (Exception e){
            System.out.println("system: không tìm thấy mã mượn này");
            reloadScene();
        }

        if (request!=null){
            if (request.isHaveBook()){
                Tracking tracking = Tracking.create(user, request);
                tracking.setId(request.getId());
                tracking.setDeliver(user);
                managerTracking.add(tracking);
                managerRequest.saveData();
                managerTracking.saveData();
                managerBook.getElementById(request.getBook().getId()).setBorrowStatus(BorrowStatus.UNAVAILABLE);
                managerBook.saveData();
                System.out.println("system: bàn giao sách thành công");
            }
        }else {
            System.out.println("system: hãy kiểm tra lại mã mượn sách");
        }
        reloadScene();
    }

    private void receiveBook() {
        ManagerTracking managerTracking = new ManagerTracking();
        managerTracking.loadData();

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sách: ");
        String bookId = sc.nextLine();
        System.out.print("Nhập trạng thái sách (1: NEW, 2: LIKE_NEW, 3: GOOD, 4: ACCEPTABLE, 5: UNREADABLE, 6: LOST): ");
        String inputBookStatus = sc.nextLine();
        BookStatus bookStatus = null;

        try {
            switch (Integer.parseInt(inputBookStatus)){
                case 1:
                    bookStatus = BookStatus.NEW;
                    break;
                case 2:
                    bookStatus = BookStatus.LIKE_NEW;
                    break;
                case 3:
                    bookStatus = BookStatus.GOOD;
                    break;
                case 4:
                    bookStatus = BookStatus.ACCEPTABLE;
                    break;
                case 5:
                    bookStatus = BookStatus.UNREADABLE;
                    break;
                case 6:
                    bookStatus = BookStatus.LOST;
                    break;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Tracking tracking = managerTracking.getTrackingByBookId(bookId);
        if (tracking == null) {
            reloadScene();
            return;
        }
        if (bookStatus == null)
            tracking.setBookStatusAfter(tracking.getBookStatusBefore());
        else tracking.setBookStatusAfter(bookStatus);

        tracking.setReceiver(user);
        tracking.setDateReturn(LocalDate.now());

        ManagerBook managerBook = new ManagerBook();
        managerBook.loadData();
        Book book = managerBook.getBookById(bookId);
        book.setBorrowStatus(BorrowStatus.AVAILABLE);
        book.setBookStatus(bookStatus);


        managerBook.saveData();
        managerTracking.saveData();


        System.out.println("system: nhận sách thành công");
        reloadScene();
    }

    private void viewAllTracking() {
        System.out.println(String.format("|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|",
                "deliver", "receiver", "borrower", "bookId", "bookName", "dateBorrow", "dateReturnDeadline", "dateReturn", "bookStatusBefore", "bookStatusAfter"));
        ManagerTracking manager = new ManagerTracking();
        manager.loadData();
        manager.viewAllElement();
        reloadScene();
    }

    private void backToMenuAdmin() {
        System.out.println("system: Quay lại menu của admin");
        ManagerScene.getInstance().popScene();
        ManagerScene.getInstance().display();
    }

    protected void showMenu() {
        System.out.println("1. Xem tất cả tiến trình mượn trả sách");
        System.out.println("2. Bàn giao sách cho người mượn");
        System.out.println("3. Nhận sách từ người mượn");
        System.out.println("4. Quay lại menu của admin");
        super.showMenu();
    }

    protected void reloadScene() {
        super.reloadScene();
        ManagerScene.getInstance().replaceScene(SceneManagerTracking.create(user));
        ManagerScene.getInstance().display();
    }
}
