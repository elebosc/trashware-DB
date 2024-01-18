package it.unibo.trashware.commons;

public enum FieldTags {

    OPERATION_ID("IDOperazione"),
    REPRESENTATIVE("Referente"),
    SOCIETY("Società"),
    REQUEST_TYPE("Tipo richiesta"),
    REASON("Motivazione"),
    DEVICES_LIST("Elenco Dispositivi"),
    EFFECTUATION_DATE("Data effettuazione"),
    DEADLINE("Data limite"),
    COMPLETION_DATE("Data completamento"),
    DELIVERY_DATE("Data consegna"),
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
    STORAGE_01_TYPE("Tipo Memoria 01"),
    STORAGE_01_SIZE("Dimensione Memoria 01"),
    STORAGE_02_TYPE("Tipo Memoria 02"),
    STORAGE_02_SIZE("Dimensione Memoria 02"),
    CHASSIS_ID("ID Chassis"),
    CHASSIS_BRAND("Marca Chassis"),
    CHASSIS_MODEL("Modello Chassis"),
    CHASSIS_COLOR("Colore Chassis"),
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
    PERIPHERAL_TYPE("Tipo Periferica"),
    KEYBOARD_ID("ID Tastiera"),
    MOUSE_ID("ID Mouse"),
    SPEAKERS_ID("ID Casse"),
    ASSIGNED_TO_REQUEST("Assegnato a richiesta");

    private String tag;

    private FieldTags(final String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

}
