package lab_2;

import lab_2.DataWorkers.DBFormatWorker;
import lab_2.Pack.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static DataWork dataWork;
    private static Hospital hospital;

   /* private static void menu() {
        System.out.println("Считывание и запись информации из:");
        System.out.println("1 - txt");
        System.out.println("2 - bin");
        System.out.println("3 - db");
        System.out.print("Выберите пункт меню: ");
    }*/

    private static int therapyMenu() throws IOException {
        int i;

        do {
            System.out.println("\n1 - Назначить операцию.");
            System.out.println("2 - Назначить процедуру.");
            System.out.println("3 - Назначить лекарства.");
            System.out.println("4 - Поставить диагноз.");
            System.out.print("Выберите пункт меню: ");
            String item = reader.readLine();
            i = Integer.parseInt(item);
        } while (i <= 0 | i > 5);

        return i;
    }

    //@Override
    public static void main(String... args) throws IOException {

    dataWork = new DBFormatWorker();

         menuHandler();
    }

    private static void menuHandler() throws IOException {
        int index;
        while (true) {
            System.out.println("\n\n1 - Считать данные");
            System.out.println("2 - Записать данные");
            System.out.println("3 - Назначить лечение пациенту");
            System.out.println("4 - Просмотреть информацию по пациенту");
            System.out.println("5 - Выйти");
            System.out.print("Chose menu item: ");

            String item = reader.readLine();
            int chosen = Integer.parseInt(item);

            switch (chosen) {
                case 1:
                    if (dataWork.getClass().getSimpleName().equals("DBFormatWorker")) {
                        hospital = dataWork.read("");
                    }
     /*               else {
                        hospital = dataWork.read(askPath());
                    }*/

                    System.out.println("Данные были успешно извлечены и записаны в программу.");
                    break;
                case 2:
                    if (hospital != null) {
                        dataWork.write( hospital);
                    }
                    else {
                        System.out.println("Отказано. Сперва считайте информацию из хранилища, а после повторите попытку.");
                    }
                    break;
                case 3:
                    index = therapyMenu();
                    therapyWorker(index);
                    break;
                case 4:
                    if (hospital == null) {
                        System.out.println("Отказано. Сперва считайте информацию из хранилища, а после повторите попытку.");
                        break;
                    }
                    if (hospital.getPatientList().size() > 0) {
                        for (int i = 0; i < hospital.getPatientList().size(); i++) {
                            System.out.printf("%d.\t%s\n", i + 1, hospital.getPatientList().get(i).getName());
                        }
                        String str = reader.readLine();
                        index = Integer.parseInt(str);

                        if (index > 0 && index <= hospital.getPatientList().size()) {
                            System.out.println(hospital.getPatientList().get(index - 1).print());
                        }
                        else {
                            System.out.println("Не найдено.");
                        }
                    }
                    else {
                        System.out.println("Список пациентов пуст");
                    }

                    break;
                case 5: System.exit(0);
                default: break;
            }
        }
    }

    private static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    private static void therapyWorker(int i) throws IOException {
        selectPersonName("Врачи", hospital.getDoctorList());
        selectPersonName("Мед сёстры", hospital.getNurseList());
        System.out.print("Введите имя: ");
        String employName = reader.readLine();

        selectPersonName("Пациенты", hospital.getPatientList());
        String patientName = reader.readLine();

        switch (i) {
            case 1:
                hospital.treatment(employName, patientName);
                break;
            case 2:
                hospital.procedure(employName, patientName);
                break;
            case 3:
                System.out.println("Введите название лекарства");
                String med = reader.readLine();
                hospital.medicament(employName, patientName, med);
                break;
            case 4:
                System.out.println("Введите диагноз");
                String diag = reader.readLine();
                hospital.treatment(employName, patientName, diag);
                break;
        }
    }

    private static void selectPersonName(String title, List<?> persons) {
        System.out.println("\n" + title);
        printList(persons);
    }

    private static void printList(List<?> humans) {
        for (Object human : humans) {
            System.out.println(human.toString());
        }
    }
}
