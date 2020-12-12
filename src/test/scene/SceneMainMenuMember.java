package test.scene;

import test.data.User;
import test.manager.ManagerBook;
import test.manager.ManagerRequest;
import test.manager.ManagerScene;

import java.io.IOException;
import java.util.Scanner;

/**
 * làm tương tự scene login
 */
public class SceneMainMenuMember extends Scene{
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
        addFunction(this::backToStartScene);

        return true;
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


    protected void backToStartScene() {
        System.out.println("system: trở về Scene bắt đầu");
        ManagerScene.getInstance().popScene();
        ManagerScene.getInstance().display();
    }

    protected void showMenu() {
        System.out.println("1. Xem tất cả sách trong thư viện");
        System.out.println("2. Tìm sách theo tên");
        System.out.println("3. Mượn sách");
        System.out.println("4. Xem yêu cầu mượn sách đã gửi");
        System.out.println("5. Quay lại Scene bắt đầu");
        System.out.println("Nhập vào số bạn chọn bên dưới");
        inputListener();
        onChooseDone();
    }

    /**
     * Được gọi khi chon xọng chức năng trên menu chính
     */
    protected void onChooseDone() {
        int choose = -1;
        try {
            choose = Integer.parseInt(this.input);
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }

        switch (choose){
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
                break;
            case 5:
                functions.get(5).run();
                break;
            default:
                reloadScene();
        }
    }

    /**
     * Sử dụng khi cần nhập vào gì đó
     */
    protected void inputListener() {
        System.out.print("Your input: ");
        input = new Scanner(System.in).nextLine();
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
        System.out.println("Nhập vào tên sách (cần nhập đúng từng chữ)");
        inputListener();

        System.out.println(String.format("|%20s|%20s|%20s|%20s|%20s|%20s|%20s|"
                , "id", "name", "price", "borrowStatus", "bookStatus", "author", "bookClassification"));
        ManagerBook manager = new ManagerBook();
        manager.loadData();
        manager.getElementsByName(input).viewAllElement();
        reloadScene();
    }

    protected void reloadScene(){
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("system: Tải lại Scene menu của thành viên");
        ManagerScene.getInstance().replaceScene(SceneMainMenuMember.create(user));
        ManagerScene.getInstance().display();
    }

}
