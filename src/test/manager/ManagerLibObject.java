package test.manager;

import test.data.LibObject;

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
        return list.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .get();
    }

    /**
     * xóa phần tử dựa vào id
     * @param id
     * @return true nếu phần tử này có trong list và đã xóa thành công
     */
    public boolean removeElementById(String id){
        T obj = getElementById(id);
        return list.remove(obj);
    }

    /**
     * lấy phần tất cả các phần tử có tên giống nhau ra
     * @param name
     * @return một đối trượng Manager mới quản lý các phần tử có tên giống nhau này
     */
    public ManagerLibObject<T> getElementsByName(String name){
        ManagerLibObject<T> manager = new ManagerLibObject<>();
        list.forEach(t -> {
            if (t.getName().equals(name))
                manager.add(t);
        });
        return manager;
    }

}
