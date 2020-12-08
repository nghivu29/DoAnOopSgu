package test.scene;

import test.data.InitProtocol;
import test.data.LibObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Nếu muốn tạo ra một menu con hay một menu chức năng khác thì cứ kết thừa từ lớp này
 */
public class Scene extends LibObject implements InitProtocol {

    /**
     * danh sách chứa các hàm kiểu void func();
     * sử dụng hàm addFunction(Runnable) ở bên dưới để thêm vào danh sách này
     * xem vd dụ sử dụng bên method init của Class SceneLogin và SceneMainMenuMember
     */
    protected List<Runnable> functions;

    protected Scene() {
        functions = new ArrayList<>();
    }

    @Override
    public boolean init() {
        return true;
    }

    /**
     * chạy function đầu tiên trong danh sách. Thường là in ra hướng dẫn hoạc in ra menu
     * Lưu ý: Khi danh sách chưa có function mà gọi rát sẽ lỗi
     */
    public void display() {
        System.out.println("=================="+ name +"=================");
        if (!functions.isEmpty())
            functions.get(0).run();
    }

    public boolean addFunction(Runnable function){
        return functions.add(function);
    }

    public Runnable changeFunction(int i, Runnable function){
        return functions.set(i, function);
    }
}
