/*
 * Class: Driver
 * Author: s3682248
 * Date: 25th March 2018
 * Version 1
 */

import java.util.*;

public class Driver {
    Person[] allProfiles = new Person[8];
    Person curPerson = null;
    final Scanner sc = new Scanner(System.in);

    private void topLevelHandler() {
        int opt1 = getMainMenu(sc);
        menuAction(opt1);

    }

    private void menuAction(int option) {
        switch (option) {
            case 1: { //list everyone
                displayNet();
                topLevelHandler();
                break;
            }
            case 2: { //select a person

                displayNet();
                System.out.println("Select a person by entering a name from above list:");
                String name = sc.next();
                curPerson = getPerson(name);

                if (curPerson != null) {
                    System.out.println("You selected " + curPerson.getName());
                    subMenuAction();

                } else {
                    System.out.println("Invalid name:");
                    menuAction(option);

                }


            }
            case 3: {
                displayNet();
                System.out.println("Enter two names (you will be prompted twice to enter two names) :");
                System.out.println("Enter name of first user :");
                String f1 = sc.next();
                Person P1 = getPerson(f1);

                if(P1 != null){
                    System.out.println("Enter name of second user :");
                    String f2 = sc.next();
                    Person P2 = getPerson(f2);
                    if(P2 != null){

                        if(P1.isFriendOf(P2)){
                            System.out.println(P1.getName() + " and " + P2.getName() + " are friends.");
                        }else{
                            System.out.println(P1.getName() + " and " + P2.getName() + " are not friends.");

                        }
                        topLevelHandler();
                        break;
                    }else{
                        System.out.println("Invalid name :");
                        menuAction(option);
                    }


                }else{
                    System.out.println("Invalid name :");
                    menuAction(option);
                }

            }// case 3 ends

            case 4: {
                System.out.println("\nYou quit the MiniNet");
                System.exit(0);
            }// case 4 ends
        } // switch ends
    }// menuAction() ends


    private void subMenuAction() {

        System.out.print("\nCurrent user: " + curPerson.getName());
        int choice = getSubMenu(sc);

        if(choice == 1){
            System.out.println("\n--------------------------");
            curPerson.viewDetails();
            System.out.println("\n--------------------------");
            subMenuAction();

        } else if (choice == 2) { //Add a friend

            displayNet();
            System.out.println("Select a name to be friend with " + curPerson.getName());
            String nameAdd = sc.next();

            Person newPerson1 = getPerson(nameAdd);

            if (newPerson1 != null) {

                System.out.println("Friends of " + curPerson.getName());
                System.out.println(curPerson.showFriends());

                if(curPerson.addedTo(newPerson1)) {
                    updateAllProfilesList(curPerson);
                    updateAllProfilesList(newPerson1);
                }

                System.out.println("Current friends of " + curPerson.getName());
                System.out.println(curPerson.showFriends());

                subMenuAction();

            } else {
                System.out.println("Sorry, " + nameAdd + " is not a valid network user. Enter a name from below list.");

                subMenuAction();

            }

        } else if (choice == 3) { //Delete a friend

            displayNet();
            System.out.println("Select a name to be deleted with" + curPerson.getName());
            String nameDel = sc.next();

            Person newPerson2 = getPerson(nameDel);

            if (newPerson2 != null) {

                System.out.println("Friends of " + curPerson.getName());
                System.out.println(curPerson.showFriends());

                if(curPerson.deletedFrom(newPerson2)) {
                    updateAllProfilesList(curPerson);
                    updateAllProfilesList(newPerson2);
                }

                System.out.println("Current friends of " + curPerson.getName());
                System.out.println(curPerson.showFriends());

            } else {
                System.out.println("Sorry, " + nameDel + " is not a valid network user.");
                subMenuAction();
            }


        } else if (choice == 4) { //show friends' details
            System.out.println("Friends of " +curPerson.getName() + " : " + curPerson.showFriends());
            System.out.println("Enter a friend's name to get details");
            String nameLook = sc.next();

            Person newPerson3 = getPerson(nameLook);

            if (newPerson3 != null){
                if(curPerson.isFriendOf(newPerson3)){
                    System.out.println("\n--------------------------");
                    newPerson3.viewDetails();
                    System.out.println("\n--------------------------");
                }else{
                    System.out.println("Entered user is not a friend of "+curPerson.getName());
                }
            }else{
                System.out.println("Not a valid user.");
            }
            subMenuAction();

    } else if (choice == 5) {
            topLevelHandler();

    }else if (choice == 6) {
            menuAction(4);
        }

    }

    private void displayNet() {
        System.out.println("All Profiles\n====================");
        for (int i = 0; i < allProfiles.length; i++) {
            String userType = null;

             if(allProfiles[i] instanceof Adult){
                 userType = "Adult";
             }else{
                 userType = "Dependant";
             }

            System.out.println("Name: " + allProfiles[i].getName() + " (Type: " +userType+ " )");
        }
    }

    private Person findPerson(String name) { // To get the Person position from the array based on his/her name
        for (int i = 0; i < allProfiles.length; i++) {
            if (allProfiles[i].getName().toLowerCase() == name.trim().toLowerCase())
                return allProfiles[i];
        }
        return null;
    }

    private static int getMainMenu(Scanner sc) {
        int opt1 = 0;
        boolean done1 = false;
        String mainMenu[] = {"\nMiniNet Menu",
                "================================= ",
                "1. List everyone",
                "2. Select a person (Sub menu will appear after selecting a Person))",
                "3. Check given two users are friends ",
                "4. Exit"
        };

        for (int i = 0; i < mainMenu.length; i++)
            System.out.println(mainMenu[i]);
        System.out.print("\nEnter an option from 1,2,3 and 4: ");


        do {
            try {
                opt1 = sc.nextInt();
                if (opt1 <= 0 || opt1 > 4)
                    System.out.print("\nInvalid input. Your option must be between 1-4.\nEnter an option: ");
                else
                    done1 = true;
            } catch (InputMismatchException ex) {
                System.out.print("\nInvalid input. Your option must be an Integer. \nEnter an option: ");
                sc.nextLine();
            }
        } while (!done1);

        return opt1;
    }


    private static int getSubMenu(Scanner sc) {
        int opt2 = 0;
        boolean done1 = false;

        String menu[] = {"\nMiniNet Sub-Menu",
                "=====================",
                "1. Show details",
                "2. Add a friend",
                "3. Delete a friend",
                "4. Show selected friend's details",
                "5. Go to main menu",
                "6. Exit"
        };

        for (int i = 0; i < menu.length; i++)
            System.out.println(menu[i]);
        System.out.print("\nEnter an option from 1,2,3,4,5 and 6: ");


        do {
            try {
                opt2 = sc.nextInt();
                if (opt2 <= 0 || opt2 > 5)
                    System.out.print("\nInvalid input. Your option must be between 1-4.\nEnter an option: ");
                else
                    done1 = true;
            } catch (InputMismatchException ex) {
                System.out.print("\nInvalid input. Your option must be an Integer. \nEnter an option: ");
                sc.nextLine();
            }
        } while (!done1);

        return opt2;
    }


    public void drive() {

        allProfiles[0] = new Adult("Alice", 25, "Alice.photo", "Doctor", new String[]{"James", "Ron"},
                new String[]{"Ben"});
        allProfiles[1] = new Adult("James", 22, "James.photo", "Lecturer", new String[]{"Alice"},
                new String[]{"Ben"});
          allProfiles[2] = new Adult("Ronald", 35, "Ronald.photo", "Teacher", new String[]{null},
                new String[]{null});
        allProfiles[3] = new Adult("Anne", 32, "Anne.photo", "Dancer", new String[]{"Sam"},
                new String[]{"Steve"});
        allProfiles[4] = new Adult("Mike", 41, "Mike.photo", "Business man", new String[]{"Anne"},
                new String[]{"Steve"});


        allProfiles[5] = new Dependent("Ben", 12, "Ben.photo", "student", new String[]{null},
                "Alice", "James");

        allProfiles[6] = new Dependent("Steve", 14, "None", "Student", new String[]{null},
                "Anne", "Mike");

        allProfiles[7] = new Dependent("Jacob", 1, "ben1.img", "Baby", new String[]{null},
                "Anne", "Mike");

        topLevelHandler();
    }


    Person getPerson(String personName) {
        for (Person psn : allProfiles) {
            if (psn.getName().toLowerCase().equals(personName.trim().toLowerCase())) {
                return psn;
            }
        }
        return null;
    }

    public void updateAllProfilesList(Person P) {

        for (int i = 0; i < allProfiles.length; i++){

            if (allProfiles[i].getName().toLowerCase().equals(P.getName())) {
                allProfiles[i] = P;
                break;
            }
        }


    }

}


