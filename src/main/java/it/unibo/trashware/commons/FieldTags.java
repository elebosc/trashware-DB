package it.unibo.trashware.commons;

public enum FieldTags {

    OPERATION_ID("IDOperazione"),
    REPRESENTATIVE("Referente"),
    SOCIETY("Società"),
    EFFECTUATION_DATE("Data effettuazione"),
    PHONE_CONTACTS("Contatti telefonici"),
    FAX("Fax"),
    EMAIL("E-mail"),
    DETAILS("Dettagli");

    private String tag;
    
    private FieldTags(final String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

}
