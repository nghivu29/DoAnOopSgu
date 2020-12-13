package test.manager;

import test.data.LibObject;

import java.util.Arrays;

public class ManagerLibObject <T extends LibObject> extends Manager<T> {

    public ManagerLibObject() {
        super();
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
     * Lấy phần tử dựa vào id
     * @param id
     * @return phần tử cẩn tìm
     */
    public T getElementById(String id){
        for (int i = 0; i < length; i++) {
            if (get(i).getId().equals(id))
                return get(i);
        }
        return null;
    }

    /**
     * xóa phần tử dựa vào id
     * @param id
     * @return true nếu phần tử này có trong list và đã xóa thành công
     */
    public boolean removeElementById(String id){
        for (int i = 0; i < length; i++) {
            if (get(i).getId().equals(id)){
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * lấy phần tất cả các phần tử có tên giống nhau ra
     * @param name
     * @return một đối trượng Manager mới quản lý các phần tử có tên giống nhau này
     */
    public ManagerLibObject<T> getElementsByName(String name){
        ManagerLibObject<T> manager = new ManagerLibObject<>();
        for (int i = 0; i < length; i++) {
            if (get(i).getName().equals(name))
                manager.add(get(i));
        }
        return manager;
    }

}
