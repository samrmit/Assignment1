/*
 * Class: Dependent
 * Author: s3685754
 * Date: 25th March 2018
 * Version 1
 */
public class Dependent extends Person {

    private String parentName1;
    private String parentName2;

    public Dependent(String name, int age, String image, String status, String[] friend_list,
                     String parentName1, String parentName2) {
        super(name, age, image, status, friend_list);
        this.parentName1 = parentName1;
        this.parentName2 = parentName2;
    }

    public String getParentName1() {
        return parentName1;
    }

    public String getParentName2() {
        return parentName2;
    }

    public boolean addedTo(Person p1) {

        if (p1 instanceof Dependent) {

            if (p1.getAge() < 2 || this.getAge() < 2) {
                System.out.println("Cannot added as friends. Both the dependants should not be less than 2 years of age to become friends.");
                System.out.println(this.getName() + " is " + this.getAge() + " and ." + p1.getName() + " is " + p1.getAge());
                return false;

            } else if (Math.abs(p1.getAge() - super.getAge()) > 3) {
                System.out.println(p1.getName() + " cannot be added as a friend. These two users' age gap is " + Math.abs(p1.getAge()) + ".");
                System.out.println("Dependants' age gap should not exceed 3 years to become friends. ");
                return false;
            } else {
                return super.addedTo(p1);

            }


        } else {

            System.out.println(p1.getName() + " is not a Dependant. Dependant can have only dependant friends.");
            return false;
        }

    }

    public void viewDetails() {
        super.viewDetails();
        System.out.println("Parent Name 1: " +parentName1+"\nParent Name 2: "+parentName2);
    }


}

