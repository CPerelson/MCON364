package Lambdas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class SchulMember implements Comparable{

    private String lastNameOfMember;
    private String firstNameOfMember;
    private LocalDate birthDateOfMember;
    private String spouseFirstName;
    private String spouseLastName;
    private String[] childrenNames;
    private int yearsOfMembership;

    public SchulMember(String lastNameOfMember, String firstNameOfMember, LocalDate birthDateOfMember, String spouseFirstName, String spouseLastName, String[] childrenNames, int yearsOfMembership) {
        this.lastNameOfMember = lastNameOfMember;
        this.firstNameOfMember = firstNameOfMember;
        this.birthDateOfMember = birthDateOfMember;
        this.spouseFirstName = spouseFirstName;
        this.spouseLastName = spouseLastName;
        this.childrenNames = childrenNames;
        this.yearsOfMembership = yearsOfMembership;
    }
    public SchulMember(){
        this.lastNameOfMember = null;
        this.firstNameOfMember = null;
        this.birthDateOfMember = null;
        this.spouseFirstName = null;
        this.spouseLastName = null;
        this.childrenNames = null;
        this.yearsOfMembership = 0;
    }

    public String getLastNameOfMember() {
        return lastNameOfMember;
    }

    public String getFirstNameOfMember() {
        return firstNameOfMember;
    }

    public LocalDate getBirthDateOfMember() {
        return birthDateOfMember;
    }

    public String getSpouseFirstName() {
        return spouseFirstName;
    }

    public String getSpouseLastName() {
        return spouseLastName;
    }

    public String[] getChildrenNames() {
        return childrenNames;
    }

    public int getYearsOfMembership() {
        return yearsOfMembership;
    }

    public void setLastNameOfMember(String lastNameOfMember) {
        this.lastNameOfMember = lastNameOfMember;
    }

    public void setFirstNameOfMember(String firstNameOfMember) {
        this.firstNameOfMember = firstNameOfMember;
    }

    public void setBirthDateOfMember(LocalDate birthDateOfMember) {
        this.birthDateOfMember = birthDateOfMember;
    }

    public void setSpouseFirstName(String spouseFirstName) {
        this.spouseFirstName = spouseFirstName;
    }

    public void setSpouseLastName(String spouseLastName) {
        this.spouseLastName = spouseLastName;
    }

    public void setChildrenNames(String[] childrenNames) {
        this.childrenNames = childrenNames;
    }

    public void setYearsOfMembership(int yearsOfMembership) {
        this.yearsOfMembership = yearsOfMembership;
    }

    // for age of member

    public int compareTo(SchulMember o) {
        return o.birthDateOfMember.compareTo(this.birthDateOfMember);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
