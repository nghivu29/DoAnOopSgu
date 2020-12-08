package test.manager;

import java.util.ArrayList;
import java.util.List;

public abstract class Manager <T> {
    protected List<T> list;

    public Manager(){
        list = new ArrayList<>();
    }

    /**
     * thêm phần tử vào danh sách
     * @param element
     * @return true nếu thêm thành công
     */
    public boolean add(T element){
        return list.add(element);
    }

    /**
     * hiển thị tất cả các phần tử trong danh sách
     */
    public void viewAllElement(){
        list.forEach(System.out::println);
    }

    public int size(){
        return list.size();
    }

    /**
     * dùng để tải dử liệu từ file lên
     * một số chổ t đang dùng method này tạo mặc định dữ liệu để test trước
     * @return true nếu load file thành công
     */
    abstract public boolean loadData();

    /**
     * dùng để ghi dữ liệu vào file
     * @return
     */
    abstract public boolean saveData();
}
