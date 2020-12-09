package test.manager;

import java.io.Serializable;

public abstract class Manager <T> implements Serializable {
    public T[] list;
    public int length;
    public int length_max = 50;

    public Manager(){
        list = (T[]) new Object[length_max];
        length = 0;
    }

    public T[] getList() {
        return list;
    }


    /**
     * thêm phần tử vào danh sách
     * @param element
     * @return true nếu thêm thành công
     */
    public boolean add(T element){
        if (length >= 0 && length < length_max){
            list[length] = element;
            length++;
            return true;
        }
        return false;
    }

    public T get(int i){
        return (T) list[i];
    }

    public void set(int i, T t){
        list[i] = t;
    }

    public T remove(int index){
        T t = list[index];
        for (int i = index; i < length - 1; i++) {
            list[i] = list[i + 1];
        }
        length--;
        return t;
    }

    /**
     * hiển thị tất cả các phần tử trong danh sách
     */
    public void viewAllElement(){
        for (int i = 0; i < length; i++) {
            System.out.println(list[i].toString());
        }
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
