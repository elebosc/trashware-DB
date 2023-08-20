package it.unibo.trashware.view;

import java.io.IOException;

public interface View {
    
    void setScene(ScenesConfig scene) throws IOException;

    void setPane(PanesConfig pane) throws IOException;

    void show();

}
