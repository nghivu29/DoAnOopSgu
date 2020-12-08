package test.manager;

import test.data.LibAction;

public class ManagerLibAction<T extends LibAction> extends Manager<T> {


    /**
     * lấy các hành đồng được thực hiện bơi User bằng Card id của người đó
     * @param id
     * @return một đối trượng ManagerLibAction mới quản lý các hành động thực hiện bởi người này
     */
    ManagerLibAction<T> getActionsByCardId(String id) {
        ManagerLibAction<T> manager = new ManagerLibAction<>();
        list.forEach(t -> {
            if (t.getUser().getId().equals(id))
                manager.add(t);
        });
        return manager;
    }

    @Override
    public boolean loadData() {
        return false;
    }

    @Override
    public boolean saveData() {
        return false;
    }
}
