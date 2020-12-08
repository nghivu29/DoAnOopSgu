package test;

import test.scene.SceneLogin;
import test.manager.ManagerScene;
import test.scene.SceneStart;

public class Main {
    public static void main(String[] args) {
        ManagerScene manager = ManagerScene.getInstance();
        manager.pushScene(SceneStart.create());
        manager.display();
    }
}
