package lab_2.Pack;

import java.time.LocalDateTime;

public class Nurse extends Human implements Therapy {

    public Nurse(String name, String gender, int age) {
        super(name, gender, age);
    }
    public Nurse(String name, Gender gender, int age) {
        super(name, gender, age);
    }

    @Override
    public void appointProcedure(Patient patient) {
        LocalDateTime d = LocalDateTime.now();
        patient.setProcedures(d);
    }

    @Override
    public void appointMedicament(Patient patient, String medicamentName) {
        patient.setMedicaments(medicamentName);
    }
}
