package lab_2.DataWorkers;

import lab_2.Pack.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBFormatWorker implements DataWork {
//default C:\ProgramData\MySQL\MySQL Server 8.0\Data\db
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static Connection con1;
    private static Statement stmt1;
    private static ResultSet rs1;
    private static ConnectionPool connectionPool = new ConnectionPool(
            MySQLConnector.getUrl(),
            MySQLConnector.getDriver(),
            MySQLConnector.getUser(),
            MySQLConnector.getPassword(),
            2);
    private static ConnectionPool connectionPool1 = new ConnectionPool(
            MySQLConnector.getUrl(),
            MySQLConnector.getDriver(),
            MySQLConnector.getUser(),
            MySQLConnector.getPassword(),
            2);

    @Override
    public Hospital read(String path) {
        List<Doctor> doctors = new ArrayList<>();
        List<Nurse> nurses = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();
        String query1 = "select * from patients";
        String query2 = "select * from employees";
        String query3 = "select * from medicaments";
        try {
            //con = DriverManager.getConnection(MySQLConnector.getUrl(), MySQLConnector.getUser(), MySQLConnector.getPassword());
            con = connectionPool.retrieve();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query1);

            while (rs.next()) {
                String name = rs.getString(1);
                String gender = rs.getString(2);
                int age = rs.getInt(3);
                patients.add(new Patient(name, gender, age));
            }

            rs = stmt.executeQuery(query2);

            while (rs.next()) {
                String name = rs.getString(1);
                String gender = rs.getString(2);
                int age = rs.getInt(3);
                String position = rs.getString(4);

                if (position.equals("Doctors"))
                    doctors.add(new Doctor(name, gender, age));
                else
                    nurses.add(new Nurse(name, gender, age));
            }
            rs = stmt.executeQuery(query3);

            while (rs.next()) {
                String name = rs.getString(1);
                String medicament = rs.getString(2);
                for (int i = 0; i < patients.size(); i++) {
                    if (patients.get(i).getName().equalsIgnoreCase(name)){
                        patients.get(i).getMedicaments().add(medicament);
                    }
                }
            }

            connectionPool.putback(con);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return new Hospital(doctors, nurses, patients);
    }
    public void ExecuteSqlInsert(String sqlString) {
         try {
            con1 = connectionPool1.retrieve();
            stmt1 = con1.createStatement();
            boolean numRowsChanged = stmt1.execute(sqlString);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

    @Override
    public void write(Hospital hospital) {
         // = "INSERT INTO `medicaments` (`patientname`, `medicament`) VALUES ('Van Dam', 'Aspirin')";
        for (int i = 0; i < hospital.getPatientList().size(); i++) {
            for (int j = 0; j < hospital.getPatientList().get(i).getMedicaments().size(); j++) {

            System.out.printf("%d.\t%s\n", i + 1, hospital.getPatientList().get(i).getName());
            String queryAddMedicament = "INSERT INTO `medicaments` (`patientname`, `medicament`) VALUES ("
                                  + "'" + hospital.getPatientList().get(i).getName() + "',"
                                  + "'" + hospital.getPatientList().get(i).getMedicaments().get(j) + "')";
            ExecuteSqlInsert(queryAddMedicament);
            int vadim = 1;
            }
        }

        /*
        try {
            con = DriverManager.getConnection(MySQLConnector.getUrl(), MySQLConnector.getUser(), MySQLConnector.getPassword());
            stmt = con.createStatement();
            //rs = stmt.executeQuery();



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {  }
            try {
                stmt.close();
            } catch (SQLException se) { }
            try {
                rs.close();
            } catch (SQLException se) {  }
        }
    }*/
    }


}
