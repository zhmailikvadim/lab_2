package lab_2.Pack;

import java.io.Serializable;

public class Human implements Serializable {
    private String name;
    private Gender gender;
    private int age;

    public Human(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Human(String name, String gend, int age) {
        this.name = name;
        this.age = age;

        switch (gend.toLowerCase()) {
            case "man" : this.gender = Gender.MALE; break;
            case "male" : this.gender = Gender.MALE; break;
            case "female" : this.gender = Gender.FEMALE; break;
            case "woman" : this.gender = Gender.FEMALE; break;
            default: this.gender = Gender.MALE; break;
        }
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(getName()).append(",");
        if (getGender() == Gender.MALE) {
            builder.append("male").append(",");
        } else {
            builder.append("female").append(",");
        }
        builder.append(getAge()).append(";");

        return builder.toString();
    }
}
