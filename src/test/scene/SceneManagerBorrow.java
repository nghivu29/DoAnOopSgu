package test.scene;

import test.data.User;
import test.manager.ManagerRequest;
import test.manager.ManagerScene;

import java.io.IOException;
import java.util.Scanner;

public class SceneManagerBorrow extends MenuHelper{
    private final User user;

    public SceneManagerBorrow(User user) {
        this.user = user;
    }


    public static SceneManagerBorrow create(User user){
        SceneManagerBorrow scene = new SceneManagerBorrow(user);
        if (!scene.init())
            return null;
        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // khởi tạo cho scene
        setName("SceneManagerBorrow");

        addFunction(this::showMenu);
        addFunction(this::viewAllRequest);
        addFunction(this::acceptAllRequest);
        addFunction(this::acceptRequestWithID);
        addFunction(this::backToMenuAdmin);

        return true;
    }

    private void acceptRequestWithID() {
        ManagerRequest manager = new ManagerRequest();
        manager.loadData();
        System.out.print("Nhâp mã yêu cầu: ");
        String requestID = new Scanner(System.in).nextLine();
        manager.acceptRequestWithID(requestID);
        System.out.println("system: đồng ý yêu cầu mượn sách thành công");
        reloadScene();
    }

    private void acceptAllRequest() {
        ManagerRequest manager = new ManagerRequest();
        manager.loadData();
        manager.acceptAllRequest();
        manager.saveData();
        System.out.println("system: đã cập nhật");
        reloadScene();
    }

    private void backToMenuAdmin() {
        System.out.println("system: Quay lại menu của admin");
        ManagerScene.getInstance().popScene();
        ManagerScene.getInstance().display();
    }

    protected void showMenu() {
        System.out.println("1. Xem tất cả các yêu cầu mượn sách");
        System.out.println("2. Tự động đồng ý tất cả các yêu cầu mượn sách");
        System.out.println("3. Đồng ý yêu cầu mươn theo mã số");
        System.out.println("4. Quay lại menu của admin");
        super.showMenu();
    }


    private void viewAllRequest() {
        ManagerRequest manager = new ManagerRequest();
        System.out.println(String.format("|%20s|%20s|%20s|%20s|%20s|",
                "id", "userName" , "bookName", "dateRequest", "haveBook"));
        manager.loadData();
        manager.viewAllElement();
        reloadScene();
    }

    protected void reloadScene() {
        super.reloadScene();
        ManagerScene.getInstance().replaceScene(SceneManagerBorrow.create(user));
        ManagerScene.getInstance().display();
    }
}
