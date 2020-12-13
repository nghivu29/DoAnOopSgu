package test.scene;

import test.data.Author;
import test.data.Book;
import test.data.User;
import test.manager.Manager;
import test.manager.ManagerBook;
import test.manager.ManagerScene;

import java.io.IOException;
import java.util.Scanner;

public class SceneManagerBook extends MenuHelper{

    private final User user;

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
        ManagerBook manager = new ManagerBook();
        manager.loadData();

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sách: ");
        String bookIdInput = sc.nextLine();

        Book book = manager.getElementById(bookIdInput);

        if (book != null){
            gotoSceneEditBook(manager, book);
        }else {
            System.out.println("system: mã sách không tồn tại");
        }
        reloadScene();
    }

    private void gotoSceneEditBook(ManagerBook managerBook, Book book) {
        System.out.println("system: Chuyển đến scene sửa thông tin sách");
        ManagerScene.getInstance().pushScene(SceneEditBook.create(managerBook, book));
        ManagerScene.getInstance().display();
    }

    private void deleteBook() {
        ManagerBook manager = new ManagerBook();
        manager.loadData();

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sách: ");
        String bookIdInput = sc.nextLine();

        if (manager.removeElementById(bookIdInput)){
            System.out.println("system: xoá thành công");
            manager.saveData();
        }else System.out.println("system: xóa thất bại. Hãy kiểm tra lại mã sách");
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
        System.out.print("Nhập tên tác giả: ");
        String authorName = sc.nextLine();
        System.out.print("Nhập giá sách: ");
        double bookPriceInput = 0;
        int bookId = manager.length;
        try {
            bookPriceInput = Double.parseDouble(sc.nextLine());
            bookId = Integer.parseInt(manager.get(manager.length-1).getId())+1;
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
            reloadScene();
        }

        Book book = Book.create(bookNameInput, bookPriceInput);
        book.setId(String.format("%d", bookId));
        book.setAuthor(Author.create(authorName));

        manager.add(book);
        manager.saveData();

        System.out.println("system: Thêm sách thành công");

        reloadScene();
    }

    protected void showMenu() {
        System.out.println("1. Thêm sách");
        System.out.println("2. Sửa sách");
        System.out.println("3. Xóa sách");
        System.out.println("4. Quay lại menu của admin");
        super.showMenu();
    }

    protected void reloadScene() {
        super.reloadScene();
        ManagerScene.getInstance().replaceScene(SceneManagerBook.create(user));
        ManagerScene.getInstance().display();
    }

}
