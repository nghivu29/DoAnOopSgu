package test.scene;

import test.data.Author;
import test.data.Book;
import test.data.BookStatus;
import test.data.User;
import test.manager.ManagerBook;
import test.manager.ManagerScene;

import java.util.Scanner;

public class SceneEditBook extends MenuHelper{
    private ManagerBook managerBook;
    private Book book;

    public SceneEditBook(ManagerBook managerBook, Book book) {
        this.managerBook = managerBook;
        this.book = book;
    }

    public static SceneEditBook create(ManagerBook managerBook, Book book) {
        SceneEditBook scene = new SceneEditBook(managerBook, book);
        if (!scene.init())
            return null;
        return scene;
    }

    @Override
    public boolean init() {
        if (!super.init())
            return false;

        // khởi tạo cho scene
        setName("SceneEditBook");

        addFunction(this::showMenu);
        addFunction(this::editBookStatus);
        addFunction(this::editBookName);
        addFunction(this::editBookPrice);
        addFunction(this::editBookAuthorName);
        addFunction(this::backScene);

        return true;
    }

    private void editBookAuthorName() {
        System.out.print("Nhập tên tác giả mới: ");
        String input = new Scanner(System.in).nextLine();
        book.setAuthor(Author.create(input));
        managerBook.saveData();
        System.out.println("system: thay đổi thành công");
        backScene();
    }

    private void editBookPrice() {
        System.out.print("Nhập giá mới: ");
        String input = new Scanner(System.in).nextLine();
        try {
            book.setPrice(Integer.parseInt(input));
        }catch (Exception e){
            System.out.println(e.getMessage());
            reloadScene();
        }
        managerBook.saveData();
        System.out.println("system: thay đổi thành công");
        backScene();
    }

    private void editBookName() {
        System.out.print("Nhập tên mới: ");
        String inputBookName = new Scanner(System.in).nextLine();
        book.setName(inputBookName);
        managerBook.saveData();
        System.out.println("system: thay đổi thành công");
        backScene();
    }

    private void editBookStatus() {
        System.out.print("Nhập trạng thái sách mới (1: NEW, 2: LIKE_NEW, 3: GOOD, 4: ACCEPTABLE, 5: UNREADABLE, 6: LOST): ");
        String inputBookStatus = new Scanner(System.in).nextLine();
        BookStatus bookStatus = null;

        try {
            switch (Integer.parseInt(inputBookStatus)){
                case 1:
                    bookStatus = BookStatus.NEW;
                    break;
                case 2:
                    bookStatus = BookStatus.LIKE_NEW;
                    break;
                case 3:
                    bookStatus = BookStatus.GOOD;
                    break;
                case 4:
                    bookStatus = BookStatus.ACCEPTABLE;
                    break;
                case 5:
                    bookStatus = BookStatus.UNREADABLE;
                    break;
                case 6:
                    bookStatus = BookStatus.LOST;
                    break;
                default:
                    System.out.println("system: nhập sai");
                    reloadScene();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        book.setBookStatus(bookStatus);
        managerBook.saveData();
        System.out.println("system: thay đổi thành công");
        backScene();
    }

    @Override
    protected void showMenu() {
        System.out.println("1. Sửa trạng thái sách");
        System.out.println("2. Sửa tên sách");
        System.out.println("3. Sủa giá");
        System.out.println("4. Sủa tên tác giả");
        System.out.println("5. Quay lại");
        super.showMenu();
    }

    @Override
    protected void reloadScene() {
        super.reloadScene();
        ManagerScene.getInstance().replaceScene(SceneEditBook.create(managerBook, book));
        ManagerScene.getInstance().display();
    }
}
