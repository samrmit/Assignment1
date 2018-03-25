/*
 * Class: Person
 * Author: s3685754
 * Date: 25th March 2018
 * Version 1
 */

import java.util.*;

public abstract class Person {

    private String name;
    private int age;
    private String image;
    private String status;
    private String[] friend_list;

    public Person(String name, int age, String image, String status,
                  String[] friend_list) {
        this.name = name;
        this.age = age;
        this.image = image;
        this.status = status;
        this.friend_list = friend_list;
    }

    public boolean addedTo(Person p1) {

        if (isFriendOf(p1)) {
            System.out.println("Cannot added because " +p1.name + " is already a friend.");
            return false;
        } else {
            friend_list = push(friend_list, p1.name);
            System.out.println(p1.name + " is added as a friend ");
            p1.friend_list = push(p1.friend_list, name);
            return true;
        }
    }

    public boolean deletedFrom(Person p1) {

        if (isFriendOf(p1)) {
            friend_list = remove(friend_list, p1.name);
            p1.friend_list = remove(p1.friend_list, name);

            System.out.println(p1.name + " is removed as a friend from " + this.name);
            return true;

        } else {
            System.out.println(p1.name + " is not a friend of " + this.name);
            return false;
        }

    }


    public void viewDetails() {
        System.out.println("Name: " + name + "\nage: " + age + "\nImage: " + image + "\nStatus: " + status +
                "\nFriends list: " + showFriends());
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public String[] getFriend_list() {
        return friend_list;
    }

    public void setImage(String newImage) {
        image = newImage;
    }

    public void setStatus(String newStatus) {
        status = newStatus;
    }

    public boolean isFriendOf(Person P) {
        List<String> list = Arrays.asList(friend_list);
        return list.contains(P.name);
    }

    public String showFriends() {
        return Arrays.toString(friend_list);
    }

    public String[] push(String[] array, String newItem) {
        String[] longer = new String[array.length + 1];
        System.arraycopy(array, 0, longer, 0, array.length);
        longer[array.length] = newItem;
        return longer;
    }

    public String[] remove(String[] array, String delItem) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == delItem) {
                String[] copy = new String[array.length - 1];
                System.arraycopy(array, 0, copy, 0, i);
                System.arraycopy(array, i + 1, copy, i, array.length - i - 1);
                return copy;
            }
        }
        return array;
    }


}
