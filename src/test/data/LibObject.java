package test.data;

import java.io.Serializable;

public class LibObject implements InitProtocol, Serializable {
    /**
     * các thuộc tính
     *
     */
    protected String id;
    protected String name;

    /**
     * Không thể new LibObject ở các lớp không kế thừa nó
     */
    protected LibObject(){}

    // CÁC GETTER VÀ SETTER

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean init() {
        return true;
    }

    /**
     * tạm thời để test. Sau khi hoàn thành mới sửa lại cho dẹp
     * @return
     */
    @Override
    public String toString() {
        return "LibObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
