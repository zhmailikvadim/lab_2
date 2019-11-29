package lab_2.Pack;

import java.time.LocalDateTime;

public class Doctor extends Human implements Therapy, Operation {

    public Doctor(String name, String gender, int age) {
        super(name, gender, age);
    }
    public Doctor(String name, Gender gender, int age) {
        super(name, gender, age);
    }

    public void appointDiagnosis (Patient patient, String diagnosis) {
        patient.setDiagnosis(diagnosis);
    }

    @Override
    public void appointOperation(Patient patient) {
        LocalDateTime d = LocalDateTime.now();
        patient.setOperations(d);
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
