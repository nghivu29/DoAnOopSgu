package test;

import test.manager.ManagerScene;
import test.scene.SceneStart;

public class Main2 {
    public static void main(String[] args) {
        ManagerScene manager = ManagerScene.getInstance();
        manager.pushScene(SceneStart.create());
        manager.display();
    }
}
