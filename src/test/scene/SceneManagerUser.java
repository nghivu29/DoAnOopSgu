package test.scene;

import test.data.User;
import test.manager.ManagerScene;
import test.manager.ManagerUser;

import java.io.IOException;
import java.util.Scanner;

public class SceneManagerUser extends Scene{
    private final User user;

    private String chooseInput;

    public SceneManagerUser(User user) {
        this.user = user;
    }

    public static SceneManagerUser create(User user) {
        SceneManagerUser scene = new SceneManagerUser(user);
        if (!scene.init())
            return null;
        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // khởi tạo cho scene
        setName("SceneManagerUser");

        addFunction(this::showMenu);
        addFunction(this::viewALlUser);
        addFunction(this::addUser);
        addFunction(this::editUser);
        addFunction(this::deleteUser);
        addFunction(this::backToMenuAdmin);

        return true;
    }

    private void deleteUser() {
        reloadScene();
    }

    private void editUser() {
        reloadScene();
    }

    private void addUser() {
        reloadScene();
    }

    private void backToMenuAdmin() {
        System.out.println("system: Quay lại menu của admin");
        ManagerScene.getInstance().popScene();
        ManagerScene.getInstance().display();
    }

    private void viewALlUser() {
        ManagerUser manager = new ManagerUser();
        manager.loadData();
        manager.viewAllElement();
        reloadScene();
    }

    private void showMenu() {
        System.out.println("1. Xem tất cả các user");
        System.out.println("2. Thêm user");
        System.out.println("3. Sửa thông tin user");
        System.out.println("4. Xóa user");
        System.out.println("5. Quay lại menu của admin");
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
                break;
            case 5:
                functions.get(5).run();
                break;

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
        System.out.println("system: Tải lại Scene quản user");
        ManagerScene.getInstance().replaceScene(SceneManagerUser.create(user));
        ManagerScene.getInstance().display();
    }

    private void inputListener() {
        System.out.print("Your input: ");
        chooseInput = new Scanner(System.in).nextLine();
    }
}
