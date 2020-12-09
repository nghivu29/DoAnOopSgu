package test.manager;

import test.data.Card;
import test.data.Power;
import test.data.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ManagerUser extends ManagerLibObject<User> {

    public User getAccount(String username, String password) {
        try {
            return Arrays.stream(list)
                    .filter(user -> user.getName().equals(username) && user.getPassword().equals(password))
                    .findFirst()
                    .get();
        }catch (Exception e){
            System.out.println("Tài khoảng hoặc mật khẩu sai");
            return null;
        }
    }


    /**
     * phương thức này đang được dùng để test.
     * chỉ tạo data ảo để test chứ chưa load từ file
     * cần viết lại
     *
     * @return true nếu load thành công
     */
    @Override
    public boolean loadData() {
        ManagerUser managerUser = new ManagerUser();

        try{
            FileInputStream readData = new FileInputStream("data_user.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            managerUser = (ManagerUser) readStream.readObject();
            readStream.close();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("system: Data rỗng. Bắt đầu tạo mới");
            new ManagerUser().saveData();
            return false;
        }

        list = managerUser.getList();
        length = managerUser.length;

        return true;
    }

    @Override
    public boolean saveData() {
        try{
            FileOutputStream writeData = new FileOutputStream("data_user.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(this);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
