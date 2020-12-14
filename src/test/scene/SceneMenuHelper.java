package test.scene;

import java.util.Scanner;

public class SceneMenuHelper extends Scene{
    protected String chooseInput;

    protected void inputListener() {
        System.out.print("Your input: ");
        chooseInput = new Scanner(System.in).nextLine();
    }

    protected void onChooseDone() {
        int choose = -1;
        try {
            choose = Integer.parseInt(this.chooseInput);
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }

        if (choose > 0 && choose < functions.size())
            functions.get(choose).run();
        else reloadScene();
    }

    protected void showMenu() {
        inputListener();
        onChooseDone();
    }
}
