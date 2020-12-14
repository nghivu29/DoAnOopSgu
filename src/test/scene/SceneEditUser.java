package test.scene;

import test.data.Book;
import test.data.BookStatus;
import test.data.CardStatus;
import test.data.User;
import test.manager.ManagerBook;
import test.manager.ManagerUser;

import java.util.Scanner;

public class SceneEditUser extends SceneMenuHelper{
    private ManagerUser managerUser;
    private User user;

    public SceneEditUser(ManagerUser managerUser, User user) {
        this.managerUser = managerUser;
        this.user = user;
    }

    public static SceneEditUser create(ManagerUser managerUser, User user) {
        SceneEditUser scene = new SceneEditUser(managerUser, user);
        if (!scene.init())
            return null;
        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // khởi tạo cho scene
        setName("SceneEditUser");

        addFunction(this::showMenu);
        addFunction(this::editUserStatus);
        addFunction(this::backScene);

        return true;
    }

    @Override
    protected void showMenu() {
        System.out.println("1. Sửa trạng thái user");
        System.out.println("2. Quay lại");
        super.showMenu();
    }

    private void editUserStatus() {
        System.out.print("Nhập trạng thái sách mới (1: VIP, 2: NORMAL, 3: WARNING, 4: BLOCK): ");
        String inputBookStatus = new Scanner(System.in).nextLine();
        CardStatus status = null;

        try {
            switch (Integer.parseInt(inputBookStatus)){
                case 1:
                    status = CardStatus.VIP;
                    break;
                case 2:
                    status = CardStatus.NORMAL;
                    break;
                case 3:
                    status = CardStatus.WARING;
                    break;
                case 4:
                    status = CardStatus.BLOCK;
                    break;
                default:
                    System.out.println("system: nhập sai");
                    reloadScene();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        user.getCard().setCardStatus(status);
        managerUser.saveData();
        System.out.println("system: thay đổi thành công");
        backScene();
    }
}
