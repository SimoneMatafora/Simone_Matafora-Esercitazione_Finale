package it.tcgroup.vilear.coursemanager.entity.enumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import it.tcgroup.vilear.coursemanager.common.exception.BadParametersException;

public enum SpecialInitiativesCourseEnum {
    ACCORDO_08_GIUGNO_2011("Accordo 08 Giugno 2011"),
    COSP_ITINERA_F_BASE("COPS - Itinera (F/Base)"),
    COSP_ITINERA_F_PROF("COPS - Itinera (F/Prof)"),
    ESONERO_PLACEMENT("Esonero Placement (Acc. 11/06/2009)"),
    FAD_TI_IN_FORMAZIONE_SINGOLA("FAD-TI in formazione singola MAX 2"),
    ITALIA_150("Italia 150°"),
    MOD_SICUREZZA_ACC_STATO_REGIONI("Mod Sicurezza Acc. Stato/Regioni"),
    OCCUPATO_TO("Occupato (TO)"),
    PROGETTO_RIQUALIFICAZIONE("Progetto Riqualificazione ART.25"),
    PROT_PROV_BOLOGNA("Prot Prov Bologna"),
    RICOLLOCA_MI("Ricolloca (MI)"),
    RICOLLOCA_MI_MOD_SIC_ACC_STATO_REGIONI("Ricolloca (MI) con Mod. Sic. Acc. Stato Regioni"),
    SICUREZZA_AREZZO("Sicurezza Arezzo"),
    SICUREZZA_FIRENZE("Sicurezza Firenze"),
    SICUREZZA_GROSSETO("Sicurezza Grosseto"),
    SICUREZZA_LAZIODISU("Sicurezza Laziodisu"),
    SICUREZZA_LIVORNO("Sicurezza Livorno"),
    SICUREZZA_LUCCA("Sicurezza Lucca"),
    SICUREZZA_MASSA_CARRARA("Sicurezza Massa Carrara"),
    SICUREZZA_PISA("Sicurezza Pisa"),
    SICUREZZA_PISTOIA("Sicurezza Pistoia"),
    SICUREZZA_PRATO("Sicurezza Prato"),
    SICUREZZA_RAVENNA("Sicurezza Ravenna"),
    SICUREZZA_REGGIO_EMILIA("Sicurezza Reggio Emilia"),
    SICUREZZA_SIENA("Sicurezza Siena");

    private String specialInitiativesCourse;

    SpecialInitiativesCourseEnum(String specialInitiativesCourse){
        this.specialInitiativesCourse = specialInitiativesCourse;
    }

    public String getValue() {
        return this.specialInitiativesCourse;
    }

    @JsonCreator
    public static SpecialInitiativesCourseEnum create(String specialInitiativesCourse) {

        if (specialInitiativesCourse != null) {
            for (SpecialInitiativesCourseEnum val : SpecialInitiativesCourseEnum.values()) {
                if (specialInitiativesCourse.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        throw new BadParametersException("Bad parameters exception. Enum class " + SpecialInitiativesCourseEnum.class.getSimpleName() + " doesn't accept this value: " + specialInitiativesCourse);
    }

    @JsonValue
    public String toValue() {
        for (SpecialInitiativesCourseEnum val : SpecialInitiativesCourseEnum.values()) {
            if (this.getValue().equalsIgnoreCase(val.getValue())) {
                return val.getValue();
            }
        }
        return null;
    }
}
