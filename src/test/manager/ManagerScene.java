package test.manager;

import test.scene.Scene;

/**
 * Lớp này được sử dụng singleton.
 * Có nghĩa là lớp này chỉ tạo được 1 đối tượng duy nhất trong suốt chương trình
 * Scene ở cuối danh sách sẽ là scene đang hiển thị
 */
public class ManagerScene extends Manager<Scene> {
    private static ManagerScene instance;

    private ManagerScene() {
        super();
    }

    public static ManagerScene getInstance(){
        if (instance == null)
            instance = new ManagerScene();
        return instance;
    }

    @Override
    public boolean loadData() {
        return false;
    }

    @Override
    public boolean saveData() {
        return false;
    }

    /**
     * thay thế scene hiện tại bằng scene mới và không gửi lại trong list
     * @param scene
     * @return scene bị thay thế
     */
    public Scene replaceScene(Scene scene){
        return list.set(list.size()-1, scene);
    }

    /**
     * đổi scene hiện tại thành scene mới nhưng vẫn gữi scene hiện tại trong list
     * @param scene
     * @return true nếu thêm thành công
     */
    public boolean pushScene(Scene scene){
        return list.add(scene);
    }

    /**
     * đẩy scene hiện tại ra và quay lại scene trước đó
     * @return scene bị đẩy ra
     */
    public Scene popScene(){
        return list.remove(list.size()-1);
    }

    /**
     * tải lên màn hình scene ở cuối list
     */
    public void display(){
        list.get(list.size()-1).display();
    }

}
