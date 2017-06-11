package facebook;

import javafx.scene.control.Button;

public class FacebookButton extends Button{

    DataProfileManager DPM;

    FacebookButton(String s, DataProfileManager dataProfileManager){
        super(s);
        this.DPM = dataProfileManager;
    }

}
