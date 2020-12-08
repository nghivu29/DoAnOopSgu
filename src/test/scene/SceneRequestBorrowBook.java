package test.scene;

import test.data.Request;
import test.data.User;
import test.manager.ManagerRequest;
import test.manager.ManagerScene;

import java.util.Scanner;

public class SceneRequestBorrowBook extends Scene{
    private final User user;

    private String bookNameInput;

    public SceneRequestBorrowBook(User user) {
        this.user = user;
    }

    public static SceneRequestBorrowBook create(User user){
        SceneRequestBorrowBook scene = new SceneRequestBorrowBook(user);
        if (!scene.init())
            return null;
        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // khởi tạo cho scene
        setName("SceneRequestBorrowBook");

        addFunction(this::sendRequestBorrowBook);

        return true;
    }

    private void sendRequestBorrowBook() {
        inputListener();
        Request request = Request.create(user, bookNameInput);
        ManagerRequest manager = new ManagerRequest();
        if (manager.loadData()){
            request.setId(String.format("%d", manager.size()));
            manager.add(request);
            if (manager.saveData()){
                System.out.println("system: đã gửi yêu cầu thành công");
            }else {
                System.out.println("system: gửi yêu cầu thất bại");
            }
        }else {
            System.out.println("system: lấy dữ liệu từ hệ thống thất bại");
        }


        backToSceneMainMenuMember();
    }

    private void inputListener() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên sách cần mượn: ");
        bookNameInput = sc.nextLine();

    }

    private void backToSceneMainMenuMember() {
        System.out.println("system: trở về Scene menu của thành viên");
        ManagerScene.getInstance().popScene();
        ManagerScene.getInstance().display();
    }
}
