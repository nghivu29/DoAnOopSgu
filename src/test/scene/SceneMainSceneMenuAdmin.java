package test.scene;

import test.data.CardStatus;
import test.data.User;
import test.manager.ManagerScene;

import java.io.IOException;

/**
 * làm tương tự scene login
 */
public class SceneMainSceneMenuAdmin extends SceneMainSceneMenuMember {

    public SceneMainSceneMenuAdmin(User user) {
        super(user);
    }

    public static SceneMainSceneMenuAdmin create(User user){
        SceneMainSceneMenuAdmin scene = new SceneMainSceneMenuAdmin(user);
        if (!scene.init())
            return null;
        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // khởi tạo cho scene
        this.setName("SceneMainMenuAdmin");
        
        // thêm các chức năng của menu ở đây. Hãy để vị trí functions[0] là showMenu
        changeFunction(0, this::showMenu);
        addFunction(this::gotoSceneManagerBorrow);
        addFunction(this::gotoSceneManagerTracking);
        addFunction(this::gotoSceneManagerBook);
        addFunction(this::gotoSceneManagerUser);

        return true;
    }

    private void gotoSceneManagerTracking() {
        if (user.getCard().getCardStatus() == CardStatus.WARING){
            System.out.println("system: tài khoảng của bạn đã bị cảnh báo không thể thực hiện chức năng này");
            reloadScene();
        }

        System.out.println("system: Chuyển đến Scene theo dõi mượn trả sách");
        ManagerScene.getInstance().pushScene(SceneManagerTracking.create(user));
        ManagerScene.getInstance().display();
    }

    private void gotoSceneManagerUser() {
        if (user.getCard().getCardStatus() == CardStatus.WARING){
            System.out.println("system: tài khoảng của bạn đã bị cảnh báo không thể thực hiện chức năng này");
            reloadScene();
        }

        System.out.println("system: Chuyển đến Scene quản user");
        ManagerScene.getInstance().pushScene(SceneManagerUser.create(user));
        ManagerScene.getInstance().display();
    }

    private void gotoSceneManagerBook() {
        if (user.getCard().getCardStatus() == CardStatus.WARING){
            System.out.println("system: tài khoảng của bạn đã bị cảnh báo không thể thực hiện chức năng này");
            reloadScene();
        }

        System.out.println("system: Chuyển đến Scene quản lý sách");
        ManagerScene.getInstance().pushScene(SceneManagerBook.create(user));
        ManagerScene.getInstance().display();
    }

    protected void gotoSceneManagerBorrow() {
        if (user.getCard().getCardStatus() == CardStatus.WARING){
            System.out.println("system: tài khoảng của bạn đã bị cảnh báo không thể thực hiện chức năng này");
            reloadScene();
        }

        System.out.println("system: Chuyển đến Scene quản lý yêu cầu mượn sách");
        ManagerScene.getInstance().pushScene(SceneManagerBorrow.create(user));
        ManagerScene.getInstance().display();
    }

    @Override
    protected void showMenu() {
        System.out.println("***Phần chức năng của member");
        System.out.println("1. Xem tất cả sách trong thư viện");
        System.out.println("2. Tìm sách theo tên");
        System.out.println("3. Mượn sách");
        System.out.println("4. Xem yêu cầu mượn sách đã gửi");
        System.out.println("5. Theo dõi lịch mượn trả sách");
        System.out.println("6. Quay lại Scene bắt đầu");
        System.out.println("***Phần chức năng của admin");
        System.out.println("7. Quản lý các yêu cầu mượn sách");
        System.out.println("8. Theo dõi mượn trả sách");
        System.out.println("9. Quản lý sách");
        System.out.println("10. Quản lý user");

        System.out.println("Nhập vào số bạn chọn bên dưới");
        inputListener();
        onChooseDone();
    }

    @Override
    protected void reloadScene() {
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("system: tải lại " + this.name);
        ManagerScene.getInstance().replaceScene(SceneMainSceneMenuAdmin.create(user));
        ManagerScene.getInstance().display();
    }
}
