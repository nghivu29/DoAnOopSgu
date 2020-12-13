package test.scene;

import test.data.User;
import test.manager.ManagerScene;
import test.manager.ManagerUser;

import java.util.Scanner;

public class SceneLogin extends Scene{
    private User user;

    private String nameInput;
    private String passwordInput;

    public static SceneLogin create(){
        SceneLogin scene = new SceneLogin();
        if (!scene.init())
            return null;

        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // bắt đầu định nghĩa cho sene này
        this.setName("SceneLogin");
        this.addFunction(this::login);
        return true;
    }


    public void login() {
        inputListener();
        user = checkAccount();
        gotoMainMenu(user);
    }

    /**
     * Lấy thông tin tài khoảng và mật khẩu từ bàn phím
     */
    public void inputListener() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tài khoảng: ");
        nameInput = sc.nextLine();
        System.out.print("Nhập mật khẩu: ");
        passwordInput = sc.nextLine();
    }

    /**
     * Method chưa hoàn thành
     * kiểm tra trong hệ thống có user này không
     * @return user đó nếu đúng tài khoản và mật khẩu
     */
    User checkAccount() {
        ManagerUser managerUser = new ManagerUser();
        managerUser.loadData();
        return managerUser.getAccount(nameInput, passwordInput);
    }

    /**
     * Method chưa hoàn thành
     * Method chuyển scene
     * @param user
     */
    void gotoMainMenu(User user){
        if (user == null)
        {
            System.out.println("Đăng nhập thất bại");
            //System.out.println("Tải lại scene này. Viết code tải lại sau dòng này. Đây là 1 vd lặp không dùng do while");
            backScene();
            return;
        }

        switch (user.getCard().getPower()){
            case ADMIN:
                gotoMainMenuAdmin();
                break;
            case MEMBER:
                gotoMainMenuMember();
                break;
            case GUEST:
                System.out.println("system: Chuyển đến menu dành cho Guess");
                gotoMainMenuGuess();
                break;
            default:
                System.out.println("system: Nếu phần này được in ra thì kiểm tra lại code.");
        }
    }

    /**
     * Di chuyển đến scene MainMenuMember
     */
    void gotoMainMenuMember(){
        System.out.println("system: Chuyển đến menu dành cho Member");
        ManagerScene.getInstance().replaceScene(SceneMainMenuMember.create(user));
        ManagerScene.getInstance().display();
    }

    /**
     * phương thức chưa hoàn thành
     * Di chuyển đến scene MainMenuAdmin
     */
    void gotoMainMenuAdmin(){
        System.out.println("system: Chuyển đến menu dành cho Admin");
        ManagerScene.getInstance().replaceScene(SceneMainMenuAdmin.create(user));
        ManagerScene.getInstance().display();
    }

    /**
     * phương thức chưa hoàn thành
     * Di chuyển đến scene MainMenuGuess
     */
    void gotoMainMenuGuess(){
        System.out.println("Code chua duoc viet");
    }

}
