package test.scene;

import test.data.Card;
import test.data.Power;
import test.data.User;
import test.manager.ManagerScene;
import test.manager.ManagerUser;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * làm tương tự scene login
 */
public class SceneSignup extends Scene{
    private String nameInput;
    private String passwordInput;
    private String phoneInput;
    private String addressInput;
    private String adminCodeInput;

    public static SceneSignup create(){
        SceneSignup scene = new SceneSignup();
        if (!scene.init())
            return null;

        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // bắt đầu định nghĩa cho sene này
        this.setName("SceneSignup");
        this.addFunction(this::signup);
        return true;
    }

    private void signup() {
        inputListener();
        if (!checkUsernameExist()){
            ManagerUser managerUser = new ManagerUser();
            managerUser.loadData();

            Card card = Card.create(nameInput, phoneInput);
            card.setAddress(addressInput);
            card.setValid_from(LocalDate.now());
            card.setValid_thru(LocalDate.now().plusYears(1));
            card.setId(String.format("M%d", managerUser.length));
            if (adminCodeInput.equals("admin"))
                card.setPower(Power.ADMIN);
            else card.setPower(Power.MEMBER);

            User user = User.create(card, passwordInput);
            user.setId(card.getId());

            managerUser.add(user);

            if (managerUser.saveData()){
                System.out.println("system: Đăng kí thành công");
            }else {
                System.out.println("system: Đăng kí thất bại. Không thể lưu dữ liệu về hệ thống");
            }
        }else {
            System.out.println("system: Đăng kí thất bại do tên tài khoảng bị trùng");
        }
        backToStartScene();
    }

    private void inputListener() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tài khoảng: ");
        nameInput = sc.nextLine();
        System.out.print("Nhập mật khẩu: ");
        passwordInput = sc.nextLine();
        System.out.print("Nhập số điện thoại: ");
        phoneInput = sc.nextLine();
        System.out.print("Nhập địa chỉ: ");
        addressInput = sc.nextLine();
        System.out.print("Nhập mã admin (nếu không có hãy nhập 1 kí tự bất kì):");
        adminCodeInput = sc.nextLine();
    }

    private boolean checkUsernameExist() {
        ManagerUser managerUser = new ManagerUser();
        managerUser.loadData();
        if (managerUser.getElementsByName(nameInput).length>0)
            return true;
        return false;
    }

    private void backToStartScene(){
        System.out.println("system: trở về Scene bắt đầu");
        ManagerScene.getInstance().popScene();
        ManagerScene.getInstance().display();
    }
}
