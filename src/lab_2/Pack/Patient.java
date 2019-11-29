package lab_2.Pack;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Human {
    /*
    * Fields.
    * */
    private String diagnosis;
    private List<LocalDateTime> operations = new ArrayList<>();
    private List<LocalDateTime> procedures = new ArrayList<>();
    private List<String> medicaments = new ArrayList<>();

    /*
    * Constructors.
    * */
    public Patient(String name, String gender, int age) {
        super(name, gender, age);
    }
    public Patient(String name, Gender gender, int age) {
        super(name, gender, age);
    }

    /*
    * Setters.
    * */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public void setOperations(LocalDateTime d) {this.operations.add(d);}
    public void setProcedures(LocalDateTime d) {this.procedures.add(d);}
    public void setMedicaments(String m) {this.medicaments.add(m);}

    /*
    * Getters.
    * */
    public String getDiagnosis() {
        return diagnosis;
    }
    public List<String> getMedicaments() {
        return medicaments;
    }
    public List<LocalDateTime> getOperations() {
        return operations;
    }
    public List<LocalDateTime> getProcedures() {
        return procedures;
    }

    public String print() {
        StringBuilder builder = new StringBuilder();

        builder.append(getName()).append("\t").append(getAge()).append("\t").append(getGender()).append(System.lineSeparator());
        builder.append("\noperations:\n");
        for (LocalDateTime operation : getOperations()) {
            builder.append(operation).append(System.lineSeparator());
        }

        builder.append("\nprocedures:\n");
        for (LocalDateTime procedure : getProcedures()) {
            builder.append(procedure).append(System.lineSeparator());
        }

        builder.append("\nmedicaments:\n");
        for (String medicament : getMedicaments()) {
            builder.append(medicament).append(System.lineSeparator());
        }

        builder.append("\ndiagnosis: ");
        if (diagnosis != null)
            builder.append(getDiagnosis());

        return builder.toString();
    }
}

