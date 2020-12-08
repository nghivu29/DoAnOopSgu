package test.scene;

import test.manager.ManagerScene;

import java.util.Scanner;

public class SceneStart extends Scene{

    // Lưu đữ liệu người dùng nhập vào
    private String input;

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
        
        return true;
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

    private void reloadScene() {
        System.out.println("system: Tải lại Scene bắt đầu");
        ManagerScene.getInstance().replaceScene(SceneStart.create());
        ManagerScene.getInstance().display();
    }

    public void showMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Đăng nhập\n2.Đăng Kí\n3.Thoát");
        System.out.println("Nhập vào số bạn chọn bên dưới");
        inputListener();
        onChooseDone();

    }

    private void onChooseDone() {
        int choose = Integer.parseInt(this.input);

        switch (choose){
            case 1:
                functions.get(1).run();
                break;
            case 2:
                functions.get(2).run();
            case 3:
                System.exit(0);
            default:
                reloadScene();
        }

    }

    private void inputListener() {
        System.out.print("Your input: ");
        input = new Scanner(System.in).nextLine();
    }
}
