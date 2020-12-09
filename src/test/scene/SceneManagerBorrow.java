package test.scene;

import test.data.User;
import test.manager.ManagerRequest;
import test.manager.ManagerScene;

import java.io.IOException;
import java.util.Scanner;

public class SceneManagerBorrow extends Scene{
    private final User user;

    private String chooseInput;

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
        addFunction(this::acceptAllRequestWithID);
        addFunction(this::backToMenuAdmin);

        return true;
    }

    private void acceptAllRequestWithID() {
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

    private void showMenu() {
        System.out.println("1. Xem tất cả các yêu cầu mượn sách");
        System.out.println("2. Tự động đồng ý tất cả các yêu cầu mượn sách");
        System.out.println("3. Đồng ý yêu cầu mươn theo mã số");
        System.out.println("4. Quay lại menu của admin");
        inputListener();
        onChooseDone();
    }

    private void inputListener() {
        System.out.print("Your input: ");
        chooseInput = new Scanner(System.in).nextLine();
    }

    private void viewAllRequest() {
        ManagerRequest manager = new ManagerRequest();
        manager.loadData();
        manager.viewAllElement();
        reloadScene();
    }

    protected void onChooseDone() {
        int choose = Integer.parseInt(this.chooseInput);

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
//            case 5:
//                functions.get(5).run();
//                break;
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
        System.out.println("system: Tải lại Scene quản lý yêu cầu mượn sách");
        ManagerScene.getInstance().replaceScene(SceneManagerBorrow.create(user));
        ManagerScene.getInstance().display();
    }
}
