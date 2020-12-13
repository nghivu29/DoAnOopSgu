package test.scene;

import test.data.User;
import test.manager.ManagerBook;
import test.manager.ManagerRequest;
import test.manager.ManagerScene;
import test.manager.ManagerTracking;

import java.io.IOException;
import java.util.Scanner;

/**
 * làm tương tự scene login
 */
public class SceneMainMenuMember extends MenuHelper{
    protected User user;


    // Lưu đữ liệu người dùng nhập vào
    protected String input;

    public SceneMainMenuMember(User user) {
        this.user = user;
    }

    public static SceneMainMenuMember create(User user){
        SceneMainMenuMember scene = new SceneMainMenuMember(user);
        if (!scene.init())
            return null;
        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // khởi tạo cho scene
        setName("SceneMainMenuMember");

        // thêm các chức năng của menu ở đây. Hãy để vị trí functions[0] là showMenu
        addFunction(this::showMenu);
        addFunction(this::viewAllBook);
        addFunction(this::viewBookByName);
        addFunction(this::gotoSceneRequestBorrowBook);
        addFunction(this::showMyRequestBorrowBook);
        addFunction(this::showMyTracking);
        addFunction(this::backScene);

        return true;
    }

    private void showMyTracking() {
        System.out.println(String.format("|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|%20s|",
                "deliver", "receiver", "borrower", "bookId", "bookName", "dateBorrow", "dateReturnDeadline", "dateReturn", "bookStatusBefore", "bookStatusAfter"));
        ManagerTracking manager = new ManagerTracking();
        manager.loadData();
        manager.getTrackingByUser(user).viewAllElement();
        reloadScene();
    }

    protected void showMyRequestBorrowBook() {
        System.out.println(String.format("|%20s|%20s|%20s|%20s|%20s|",
                "id", "userName" , "bookName", "dateRequest", "haveBook"));
        ManagerRequest manager = new ManagerRequest();
        manager.loadData();
        manager.getRequestsByUser(user).viewAllElement();
        reloadScene();
    }

    protected void gotoSceneRequestBorrowBook() {
        System.out.println("system: chuyển đến Scene đăng kí mượn sách");
        ManagerScene.getInstance().pushScene(SceneRequestBorrowBook.create(user));
        ManagerScene.getInstance().display();
    }

    protected void showMenu() {
        System.out.println("1. Xem tất cả sách trong thư viện");
        System.out.println("2. Tìm sách theo tên");
        System.out.println("3. Mượn sách");
        System.out.println("4. Xem yêu cầu mượn sách đã gửi");
        System.out.println("5. Theo dõi lịch mượn trả sách");
        System.out.println("6. Quay lại Scene bắt đầu");
        System.out.println("Nhập vào số bạn chọn bên dưới");
        super.showMenu();
    }

    /**
     * xem tất cả các sách trong thư viện
     */
    protected void viewAllBook(){
        ManagerBook manager = new ManagerBook();
        System.out.println(String.format("|%20s|%20s|%20s|%20s|%20s|%20s|%20s|"
                , "id", "name", "price", "borrowStatus", "bookStatus", "author", "bookClassification"));
        manager.loadData();
        manager.viewAllElement();
        reloadScene();
    }

    /**
     * Xem sách theo tên
     */
    protected void viewBookByName(){
        System.out.print("Nhập vào tên sách (cần nhập đúng từng chữ): ");
        input = new Scanner(System.in).nextLine();

        System.out.println(String.format("|%20s|%20s|%20s|%20s|%20s|%20s|%20s|"
                , "id", "name", "price", "borrowStatus", "bookStatus", "author", "bookClassification"));
        ManagerBook manager = new ManagerBook();
        manager.loadData();
        manager.getElementsByName(input).viewAllElement();
        reloadScene();
    }

    protected void reloadScene(){
        super.reloadScene();
        ManagerScene.getInstance().replaceScene(SceneMainMenuMember.create(user));
        ManagerScene.getInstance().display();
    }

}
