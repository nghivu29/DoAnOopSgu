package test.scene;

import test.data.Book;
import test.data.User;
import test.manager.ManagerBook;
import test.manager.ManagerScene;

import java.io.IOException;
import java.util.Scanner;

public class SceneManagerBook extends Scene{

    private final User user;

    private String chooseInput;

    public SceneManagerBook(User user) {
        this.user = user;
    }

    public static SceneManagerBook create(User user) {
        SceneManagerBook scene = new SceneManagerBook(user);
        if (!scene.init())
            return null;
        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // khởi tạo cho scene
        setName("SceneManagerBook");

        addFunction(this::showMenu);
        addFunction(this::addBook);
        addFunction(this::editBook);
        addFunction(this::deleteBook);
        addFunction(this::backToMenuAdmin);

        return true;
    }

    private void editBook() {
        reloadScene();
    }

    private void deleteBook() {
        reloadScene();
    }


    private void backToMenuAdmin() {
        System.out.println("system: Quay lại menu của admin");
        ManagerScene.getInstance().popScene();
        ManagerScene.getInstance().display();
    }

    private void addBook() {
        ManagerBook manager = new ManagerBook();
        manager.loadData();

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên sách: ");
        String bookNameInput = sc.nextLine();
        System.out.print("Nhập giá sách: ");
        double bookPriceInput = 0;
        try {
            bookPriceInput = Double.parseDouble(sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            reloadScene();
        }
        Book book = Book.create(bookNameInput, bookPriceInput);
        book.setId(String.format("%d", manager.length));

        manager.add(book);
        manager.saveData();

        System.out.println("system: Thêm sách thành công");

        reloadScene();
    }

    private void showMenu() {
        System.out.println("1. Thêm sách");
        System.out.println("2. Sửa sách");
        System.out.println("3. Xóa sách");
        System.out.println("4. Quay lại menu của admin");
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
        System.out.println("system: Tải lại Scene quản lý sách");
        ManagerScene.getInstance().replaceScene(SceneManagerBook.create(user));
        ManagerScene.getInstance().display();
    }

    private void inputListener() {
        System.out.print("Your input: ");
        chooseInput = new Scanner(System.in).nextLine();
    }

}
