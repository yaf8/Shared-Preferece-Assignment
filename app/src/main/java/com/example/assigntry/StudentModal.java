package com.example.assigntry;

public class StudentModal {
    private String ID, FirstName, LastName;

    public StudentModal(String ID, String firstName, String lastName) {
        this.ID = ID;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName)
    {
        this.FirstName = firstName;
    }

    public String getFirstName()
    {
        return FirstName;
    }

    public String getFullName(){
        return (this.FirstName + " " + this.LastName);
    }
}
