package it.unibo.trashware.view;

import java.io.IOException;

public interface View {

    void setPage(PagesConfig page) throws IOException;

    void show();

}
