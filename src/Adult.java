/*
 * Class Name: Adult
 * Author: s3685754
 * Date: 25th March 2018
 * Version 1
 */
import java.util.*;

public class Adult extends Person {
    private String[] children;

    public Adult(String name, int age, String image, String status, String[] friend_list, String[] children){
        super(name, age, image, status, friend_list);
        this.children=children;
    }

    public String[] getChildren(){
        return children;
    }

    public boolean addedTo(Person p1) {

        if(p1 instanceof Dependent){
            System.out.println(p1.getName() +" is a Dependant. Dependant cannot be a friend of Adult.");
            return false;
        }else{
            return super.addedTo(p1);
        }

    }

    public void viewDetails(){
        super.viewDetails();
        System.out.println("Children list: " +showChildren());
    }

    public String showChildren() {
        return Arrays.toString(children);
    }


}


