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
    CPU_ID("ID CPU"),
    CPU_BRAND("Marca CPU"),
    CPU_MODEL("Modello CPU"),
    CPU_ARC("Arch CPU"),
    RAM_ID("ID RAM"),
    RAM_SIZE("Dimensione RAM"),
    STORAGE_ID("ID Memoria di Massa"),
    STORAGE_TYPE("Tipo Memoria"),
    STORAGE_SIZE("Dimensione Memoria"),
    CHASSIS_ID("ID Chassis"),
    COMPONENT_ID("ID Componente"),
    COMPONENT_TYPE("Tipo Componente"),
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
    ASSIGNED_TO_PC("Asssegnato a PC"),
    PERIPHERAL_ID("ID Periferica"),
    PERIPHERAL_TYPE("Tipo Periferica");

    private String tag;

    private FieldTags(final String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

}
