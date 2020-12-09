package test.scene;

import test.data.*;
import test.manager.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class SceneManagerTracking extends Scene{
    private final User user;

    private String chooseInput;

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

        try {
            request = managerRequest.getRequestsById(code);
        }catch (Exception e){
            System.out.println("system: không tìm thấy mã mượn này");
            reloadScene();
        }

        if (request!=null){
            if (request.isHaveBook()){
                if (request.getBook().getBorrowStatus()!= BorrowStatus.UNAVAILABLE){
                    Tracking tracking = Tracking.create(user, request);
                    managerTracking.add(tracking);
                    managerRequest.saveData();
                    managerTracking.saveData();
                    System.out.println("system: Đã bàn giao sách");
                }
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

        Tracking tracking = managerTracking.getTrackingByBookId(bookId);
        if (tracking == null) {
            reloadScene();
            return;
        }
        tracking.setBookStatusAfter(tracking.getBookStatusBefore());
        tracking.setDateReturn(LocalDate.now());
        managerTracking.saveData();

        ManagerBook managerBook = new ManagerBook();
        managerBook.loadData();
        Book book = managerBook.getBookById(bookId);
        book.setBorrowStatus(BorrowStatus.AVAILABLE);
        managerBook.saveData();

        reloadScene();
    }

    private void viewAllTracking() {
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

    private void showMenu() {
        System.out.println("1. Xem tất cả tiến trình mượn trả sách");
        System.out.println("2. Bàn giao sách cho người mượn");
        System.out.println("3. Nhận sách từ người mượn");
        System.out.println("4. Quay lại menu của admin");
        inputListener();
        onChooseDone();
    }

    private void onChooseDone() {
        int choose = -1;
        try {
            choose = Integer.parseInt(this.chooseInput);
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }

        switch (choose) {
            case 1:
                functions.get(1).run();
                break;
            case 2:
                functions.get(2).run();
                break;
            case 3:
                functions.get(3).run();
                break;
            case 4:
                functions.get(4).run();

            default:
                reloadScene();
        }
    }

    private void reloadScene() {
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("system: Tải lại Scene theo dõi mượn trả");
        ManagerScene.getInstance().replaceScene(SceneManagerTracking.create(user));
        ManagerScene.getInstance().display();
    }

    private void inputListener() {
        System.out.print("Your input: ");
        chooseInput = new Scanner(System.in).nextLine();
    }
}
