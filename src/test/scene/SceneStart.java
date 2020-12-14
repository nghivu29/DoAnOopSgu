package test.scene;

import test.manager.ManagerScene;

public class SceneStart extends SceneMenuHelper {

    public static SceneStart create(){
        SceneStart scene = new SceneStart();
        if (!scene.init())
            return null;
        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // bắt đầu định nghĩa cho sene này
        this.setName("SceneStart");
        // thêm các chức năng của menu ở đây. Hãy để vị trí functions[0] là showMenu
        addFunction(this::showMenu);
        addFunction(this::gotoSceneLogin);
        addFunction(this::gotoSceneSignup);
        addFunction(this::exit);

        return true;
    }

    private void exit() {
        System.exit(0);
    }

    private void gotoSceneSignup() {
        System.out.println("system: Chuyển đến Scene đăng kí");
        ManagerScene.getInstance().pushScene(SceneSignup.create());
        ManagerScene.getInstance().display();
    }

    private void gotoSceneLogin() {
        System.out.println("system: Chuyển đến Scene đăng nhập");
        ManagerScene.getInstance().pushScene(SceneLogin.create());
        ManagerScene.getInstance().display();
    }

    protected void reloadScene() {
        System.out.println("system: Tải lại Scene bắt đầu");
        ManagerScene.getInstance().replaceScene(SceneStart.create());
        ManagerScene.getInstance().display();
    }

    public void showMenu(){
        System.out.println("1.Đăng nhập\n2.Đăng Kí\n3.Thoát");
        System.out.println("Nhập vào số bạn chọn bên dưới");
        super.showMenu();
    }
}
