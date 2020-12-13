package test.scene;

import test.data.User;
import test.manager.ManagerScene;
import test.manager.ManagerUser;

import java.io.IOException;
import java.util.Scanner;

public class SceneManagerUser extends MenuHelper{
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
        ManagerScene.getInstance().pushScene(SceneSignup.create());
        ManagerScene.getInstance().display();
    }

    private void backToMenuAdmin() {
        System.out.println("system: Quay lại menu của admin");
        ManagerScene.getInstance().popScene();
        ManagerScene.getInstance().display();
    }

    private void viewALlUser() {
        System.out.println(String.format("|%20s|%20s|%20s|%20s|%20s|%20s|",
                "id", "name", "password", "power", "status", "phone"));
        ManagerUser manager = new ManagerUser();
        manager.loadData();
        manager.viewAllElement();
        reloadScene();
    }

    protected void showMenu() {
        System.out.println("1. Xem tất cả các user");
        System.out.println("2. Thêm user");
        System.out.println("3. Sửa thông tin user");
        System.out.println("4. Xóa user");
        System.out.println("5. Quay lại menu của admin");
        super.showMenu();
    }

    protected void reloadScene() {
        super.reloadScene();
        ManagerScene.getInstance().replaceScene(SceneManagerUser.create(user));
        ManagerScene.getInstance().display();
    }

}
