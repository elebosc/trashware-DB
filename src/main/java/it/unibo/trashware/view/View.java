package it.unibo.trashware.view;

import java.io.IOException;

public interface View {

    void setPane(PagesConfig pane) throws IOException ;

    void show();

}
