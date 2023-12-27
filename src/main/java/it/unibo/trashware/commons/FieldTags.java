package it.unibo.trashware.commons;

public enum FieldTags {

    OPERATION_ID("IDOperazione"),
    REPRESENTATIVE("Referente"),
    SOCIETY("Società"),
    REQUEST_TYPE("Tipo richiesta"),
    REASON("Motivazione"),
    DETAILS("Dettagli"),
    EFFECTUATION_DATE("Data effettuazione"),
    DEADLINE("Data limite"),
    PRIORITY("Priorità"),
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
