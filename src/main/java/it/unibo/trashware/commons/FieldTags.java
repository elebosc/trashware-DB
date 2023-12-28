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
    EMAIL("E-mail"),
    PCID("IDPC"),
    BRAND("Marca"),
    MODEL("Modello"),
    COLOR("Colore"),
    SCREENSIZE("Dimensione"),
    ETH("Ethernet"),
    WIFI("WiFi"),
    BLUETOOTH("Bluetooth"),
    NOTES("Note"),
    CPU_BRAND("Marca CPU"),
    CPU_MODEL("Modello CPU"),
    CPU_ARC("Arch CPU"),
    RAM_SIZE("Dimensione RAM"),
    STORAGE_TYPE("Tipo Memoria"),
    STORAGE_SIZE("Dimensione Memoria"),
    OS_VERSION("Versione SO"),
    OS_UPDATE("Aggiornamento SO"),
    MONITOR_ID("Monitor ID"),
    CONNECTIVITY("Connettività"),
    MONITOR_TYPE("Tipo Monitor"),
    MONITOR_SIZE("Dimensione Monitor"),
    RATIO("Ratio"),
    VGA("VGA"),
    DVI("DVI"),
    EMBEDDED_AUDIO("Audio Integrato"),
    ASSIGNEDTOPC("Asssegnato a PC");

    private String tag;

    private FieldTags(final String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

}
