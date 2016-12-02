package Project;

import jbotsim.Node;

public class Cherry extends Node {
    @Override
    public void onStart() {
        disableWireless();
        setIcon("/Project/cherry.png"); // To be adapted (package path)
    }
}