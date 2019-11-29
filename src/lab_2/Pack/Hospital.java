package lab_2.Pack;

import java.io.Serializable;
import java.util.List;

public class Hospital implements Serializable {
    private List<Doctor> doctorList;
    private List<Nurse> nurseList;
    private List<Patient> patientList;

    public Hospital(List<Doctor> doctorList, List<Nurse> nurseList, List<Patient> patientList) {
        this.doctorList = doctorList;
        this.nurseList = nurseList;
        this.patientList = patientList;
    }

    private boolean checking(String employName, String patientName) {
        Doctor doctor = findHuman(employName, doctorList);
        Patient patient = findHuman(patientName, patientList);

        return !(doctor == null | patient == null);
    }

    public void treatment(String employName, String patientName, String diagnosis) {
        if (checking(employName, patientName)) {
            getDoctorByName(employName).appointDiagnosis(getPatientByName(patientName), diagnosis);
        }
    }

    public void treatment(String employName, String patientName) {
        if (checking(employName, patientName)) {
            getDoctorByName(employName).appointOperation(getPatientByName(patientName));
        }
    }

    public void procedure(String employName, String patientName) {
        Patient patient = findHuman(patientName, patientList);

        if(checkEmploy(employName, patientName)) return;
        getEmployByName(employName).appointProcedure(patient);
    }

    public void medicament(String employName, String patientName, String medicament) {
        Patient patient = findHuman(patientName, patientList);

        if(checkEmploy(employName, patientName)) return;
        getEmployByName(employName).appointMedicament(patient, medicament);
    }

    private boolean checkEmploy(String employName, String patientName) {
        if(findHuman(patientName, patientList) != null) return false;
        return !(findHuman(employName, doctorList) != null | findHuman(employName, nurseList) != null);
    }

    private Doctor getDoctorByName(String doctorName) {
        Doctor doctor = findHuman(doctorName, doctorList);
        return doctor;
    }

    private Patient getPatientByName(String patientName) {
        Patient patient = findHuman(patientName, patientList);
        return patient;
    }

    private <T extends Therapy> T getEmployByName(String employName) {
        Doctor doctor = findHuman(employName, doctorList);
        Nurse nurse = findHuman(employName, nurseList);

        if(doctor != null) return(T) doctor;
        else return(T) nurse;
    }

    private <T extends Human> T findHuman(String name, List<T> list) {
        for(T t : list) {
            if(t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }
    public List<Nurse> getNurseList() {
        return nurseList;
    }
    public List<Patient> getPatientList() {
        return patientList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\nHOSPITAL INFORMATION\n");
        builder.append("\nDoctors\n");
        for(Doctor doctor : doctorList) {
            builder.append(doctor.toString()).append(System.lineSeparator());
        }

        builder.append("\nNurse\n");
        for(Nurse nurse : nurseList) {
            builder.append(nurse.toString()).append(System.lineSeparator());
        }

        builder.append("\nPatient\n");
        for(Patient patient : patientList) {
            builder.append(patient.toString()).append(System.lineSeparator());
        }

        return builder.toString();
    }
}
