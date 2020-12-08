package test.manager;

import test.data.Card;
import test.data.Power;
import test.data.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ManagerUser extends ManagerLibObject<User> {

    public User getAccount(String username, String password) {
        try {
            return list.stream()
                    .filter(user -> user.getName().equals(username) && user.getPassword().equals(password))
                    .findFirst()
                    .get();
        }catch (NoSuchElementException e){
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
//        Card cardNghi = Card.create("nghi", "004");
//        cardNghi.setPower(Power.MEMBER);
//        list.add(User.create(cardNghi, "123"));
//
//        Card cardLuan = Card.create("luan", "004");
//        cardLuan.setPower(Power.ADMIN);
//        list.add(User.create(cardLuan, "123"));

        List<User> listUser = null;

        try{
            FileInputStream readData = new FileInputStream("data_user.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            listUser = (ArrayList<User>) readStream.readObject();
            readStream.close();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("system: Data rỗng. Bắt đầu tạo mới");
            new ManagerUser().saveData();
            return false;
        }
        list = listUser;

        return true;
    }

    @Override
    public boolean saveData() {
        try{
            FileOutputStream writeData = new FileOutputStream("data_user.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(list);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
