package it.unibo.trashware.commons;

public enum FieldTags {

    OPERATION_ID("IDOperazione"),
    REPRESENTATIVE("Referente"),
    SOCIETY("Società"),
    NUM_DESKTOPS("Desktop"),
    NUM_LAPTOPS("Portatili"),
    NUM_MONITORS("Monitor"),
    NUM_KEYBOARDS("Tastiere"),
    NUM_MOUSE("Mouse"),
    NUM_OTHER_PERIPHERALS("Altre periferiche"),
    NUM_COMPONENTS("Componenti"),
    EFFECTUATION_DATE("Data effettuazione"),
    PHONE_CONTACTS("Contatti telefonici"),
    FAX("Fax"),
    EMAIL("E-mail");

    private String tag;
    
    private FieldTags(final String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

}
