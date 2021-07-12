import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Todo {
    public static void main(String[] args) throws IOException {
        File todo = new File("C:\\Users\\User", "Todo list");


        try {
            Todo.allWork(todo);
        } catch (IOException IOE) {
            System.out.println("Something gone wrong with stream ! try again! " + IOE.getMessage());
        } catch (Exception e) {
            System.out.println("Something gone wrong! try again !" + e.getMessage());
        }

    }

    public static void fullNotes(LinkedList<Note> ll, File todo) throws IOException {
        noteList(ll, todo);
        if (ll.isEmpty()) {
            System.out.println("----------------------------------------------------------");
            System.out.println("You don't have any note try to create one!");
            System.out.println("----------------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------------");
            for (int i = 0; i < ll.size(); i++) {
                System.out.println(i + 1 + "." + "Summery: " + ll.get(i).summery);
                System.out.println("Text of note: " + ll.get(i).noteText);
                System.out.println("Date of creation: " + ll.get(i).dateOfCreation);
                System.out.println("Label is: " + ll.get(i).label);
                System.out.println("----------------------------------------------------------");
            }
            System.out.println();
        }


    }

    public static void shortNotes(LinkedList<Note> ll, File todo) throws IOException {
        noteList(ll, todo);
        Scanner s = new Scanner(System.in);
        String answer = "";

        if (ll.isEmpty()) {
            System.out.println("----------------------------------------------------------");
            System.out.println("You don't have any note try to create one!");
            System.out.println("----------------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------------");
            for (int i = 0; i < ll.size(); i++) {
                System.out.println(i + 1 + ". " + "Summery: " + ll.get(i).summery);
            }

            System.out.println();

            while (!answer.equalsIgnoreCase("back")) {
                System.out.println("Available commands: ");
                System.out.println("1. \"back\" - if you write this you will came back");
                System.out.println("2. \"number of note\" - if you write this you will choose note (just write number)");
                System.out.println("Write command: ");
                answer = s.nextLine();

                if (answer.equalsIgnoreCase("back")) {
                    System.out.println("----------------------------------------------------------");
                    System.out.println("You came back!");
                    System.out.println("----------------------------------------------------------");
                } else if (answer.matches("\\d+") && ll.size() > Integer.parseInt(answer) - 1) {
                    System.out.println("----------------------------------------------------------");
                    int index = Integer.parseInt(answer) - 1;
                    System.out.println(index + 1 + ". " + "Summery: " + ll.get(index).summery);
                    System.out.println("Text of note: " + ll.get(index).noteText);
                    System.out.println("Date of creation: " + ll.get(index).dateOfCreation);
                    System.out.println("Label is: " + ll.get(index).label);
                    System.out.println("----------------------------------------------------------");
                } else {
                    System.out.println("Command is wrong try again!");
                }
            }
        }

    }

    public static void noteList(LinkedList<Note> ll, File todo) throws IOException {
        ll.clear();
        FileReader fr = new FileReader(todo);
        Scanner sr = new Scanner(fr);
        StringBuilder sb = new StringBuilder();

        while (sr.hasNextLine()) {
            sb.append(sr.nextLine());
        }

        String name = "";
        String text = "";
        String date = "";
        String label = "";

        int i = 0;
        int j = 0;

        for (String end : sb.toString().split("▄")) {
            for (String midend : end.split("◙ ")) {

                if (j == 0) {
                    name = midend;
                    j++;
                } else if (j == 1) {
                    text = midend;
                    j++;
                } else if (j == 2) {
                    date = midend;
                    j++;
                } else if (j == 3) {
                    label = midend;

                    ll.add(i, new Note(name, text, date, label));
                }

            }
            i++;
            j = 0;
        }
    }


    public static void create(File todo) throws IOException {
        FileWriter fw = new FileWriter(todo, true);
        Scanner sc = new Scanner(System.in);
        String time = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());


        System.out.println("Write summery of note: ");
        String text = sc.nextLine();
        fw.write(text + "\n" + "◙ \n");

        System.out.println("If you want end creation of note write: \"text.end\" ");
        System.out.println("Write text of note: ");

        while (!text.equalsIgnoreCase("text.end")) {
            text = sc.nextLine();
            if (text.equalsIgnoreCase("text.end")) {
                fw.write("◙ \n");
            } else {
                fw.write(text);
                fw.write("\n");
            }
        }


        fw.write(time + "\n" + "◙ \n");

        System.out.println("Write label to note: ");
        text = sc.nextLine();
        fw.write(text + "\n" + "▄ \n");


        System.out.println("----------------------------------------------------------");
        System.out.println("Note created!");
        System.out.println("----------------------------------------------------------");

        fw.close();

    }

    public static void delete(LinkedList<Note> ll, File todo) throws IOException {
        FileWriter fw = new FileWriter(todo, true);
        noteList(ll, todo);

        Scanner s = new Scanner(System.in);
        String answer = "";

        if (ll.isEmpty()) {
            System.out.println("----------------------------------------------------------");
            System.out.println("You don't have any note try to create one!");
            System.out.println("----------------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------------");
            for (int i = 0; i < ll.size(); i++) {
                System.out.println(i + 1 + ". " + "Summery: " + ll.get(i).summery);
            }

            System.out.println();

            while (!answer.equalsIgnoreCase("back")) {

                System.out.println("Available commands: ");
                System.out.println("1. \"back\" - if you write this you will came back");
                System.out.println("2. \"number of note\" - if you write this you will delete note with this number (just write number)");
                System.out.println("Write command: ");
                answer = s.nextLine();

                if (answer.equalsIgnoreCase("back")) {
                    System.out.println("----------------------------------------------------------");
                    System.out.println("You came back!");
                    System.out.println("----------------------------------------------------------");
                } else if (answer.matches("\\d+") && ll.size() > Integer.parseInt(answer) - 1) {
                    reCreate(todo);
                    int index = Integer.parseInt(answer) - 1;
                    ll.remove(index);
                    for (int i = 0; i < ll.size(); i++) {
                        fw.write(ll.get(i).summery + "\n◙\n");
                        fw.write(ll.get(i).noteText + "\n◙\n");
                        fw.write(ll.get(i).dateOfCreation + "\n◙\n");
                        fw.write(ll.get(i).label + "\n▄\n");
                    }
                    fw.close();
                    System.out.println("You removed note N" + answer);
                    System.out.println("----------------------------------------------------------");
                } else {
                    System.out.println("----------------------------------------------------------");
                    System.out.println("You write number of not exist note OR some word. Try again!");
                    System.out.println("----------------------------------------------------------");
                }

            }
        }
    }

    public static void redact(LinkedList<Note> ll, File todo) throws IOException {
        FileWriter fw = new FileWriter(todo, true);
        noteList(ll, todo);
        String summery;
        String textOfNote;
        String dateOfCreation;
        String label;

        Scanner s = new Scanner(System.in);
        String answer = "";

        if (ll.isEmpty()) {
            System.out.println("----------------------------------------------------------");
            System.out.println("You don't have any note try to create one!");
            System.out.println("----------------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------------");
            for (int i = 0; i < ll.size(); i++) {
                System.out.println(i + 1 + ". " + "Summery: " + ll.get(i).summery);
            }

            System.out.println();

            while (!answer.equalsIgnoreCase("back")) {

                System.out.println("Available commands: ");
                System.out.println("1. \"back\" - if you write this you will came back");
                System.out.println("2. \"number of note\" - if you write this you will choose note  to redact with number which you write (just write number)");
                System.out.println("Write command: ");
                answer = s.nextLine();

                if (answer.equalsIgnoreCase("back")) {
                    System.out.println("----------------------------------------------------------");
                    System.out.println("You came back!");
                    System.out.println("----------------------------------------------------------");
                } else if (answer.matches("\\d+") && ll.size() > Integer.parseInt(answer) - 1) {
                    reCreate(todo);
                    int index = Integer.parseInt(answer) - 1;
                    summery = ll.get(index).summery;
                    textOfNote = ll.get(index).noteText;
                    dateOfCreation = ll.get(index).dateOfCreation;
                    label = ll.get(index).label;

                    System.out.println("----------------------------------------------------------");

                    System.out.println("Current summery is: " + ll.get(index).summery);
                    System.out.println("If you want to change summery write \"yes\" if no write \"no\" ");
                    answer = s.nextLine();
                    if (answer.equalsIgnoreCase("yes")) {
                        System.out.println("New summery is: ");
                        summery = s.nextLine();
                        System.out.println("Summery is changed to: " + summery);
                    }

                    System.out.println();

                    System.out.println("Current text of note is: " + ll.get(index).noteText);
                    System.out.println("If you want to change text write \"yes\" if no write \"no\" ");
                    answer = s.nextLine();
                    if (answer.equalsIgnoreCase("yes")) {
                        System.out.println("New text of note is: ");
                        textOfNote = s.nextLine();
                        System.out.println("text of note  is changed to: " + textOfNote);
                    }

                    System.out.println();

                    System.out.println("Current label is: " + ll.get(index).label);
                    System.out.println("If you want to change label write \"yes\" if no write \"no\" ");
                    answer = s.nextLine();
                    if (answer.equalsIgnoreCase("yes")) {
                        System.out.println("New label of note is: ");
                        label = s.nextLine();
                        System.out.println("label is changed to: " + label);
                    }

                    ll.add(index, new Note(summery, textOfNote, dateOfCreation, label));
                    ll.remove(index + 1);

                    for (int i = 0; i < ll.size(); i++) {
                        fw.write(ll.get(i).summery + "\n◙\n");
                        fw.write(ll.get(i).noteText + "\n◙\n");
                        fw.write(ll.get(i).dateOfCreation + "\n◙\n");
                        fw.write(ll.get(i).label + "\n▄\n");
                    }

                    fw.close();
                    System.out.println("----------------------------------------------------------");
                } else {
                    System.out.println("----------------------------------------------------------");
                    System.out.println("You write number of not exist note OR some word. Try again!");
                    System.out.println("----------------------------------------------------------");
                }

            }
        }

    }


    public static void reCreate(File todo) throws IOException {
        FileWriter fw = new FileWriter(todo, false);

        fw.write("");

//        System.out.println("----------------------------------------------------------");
//        System.out.println("File is recreated!");
//        System.out.println("----------------------------------------------------------");
    }

    public static void labels(LinkedList<Note> ll, File todo) throws IOException {
        noteList(ll, todo);
        NavigableSet<String> ns = new TreeSet<String>();
        Scanner s = new Scanner(System.in);
        String answer = "";
        for (int i = 0; i < ll.size(); i++) {
            ns.add(ll.get(i).label);
        }

        if (ll.isEmpty()) {
            System.out.println("----------------------------------------------------------");
            System.out.println("You don't have any note try to create one!");
            System.out.println("----------------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------------");
            System.out.println("labels you have: ");
            int i = 1;
            for (String a : ns) {
                System.out.println(i + ")" + a);
                i++;
            }
            System.out.println("----------------------------------------------------------");
            System.out.println("1.If you want to check all notes with this label just write label(1. not number 2. If you have space before label write this space too)");
            System.out.println("2.If you want to back write back");
            answer = s.nextLine();
            System.out.println();
            if (answer.equalsIgnoreCase("back")) {
                System.out.println("----------------------------------------------------------");
                System.out.println("You came back!");
                System.out.println("----------------------------------------------------------");
            } else {
                int k = 1;
                System.out.println("----------------------------------------------------------");
                for (int j = 0; j < ll.size(); j++) {
                    if (ll.get(j).label.equals(answer)) {
                        System.out.println(k + ". " + "Summery:" + ll.get(j).summery);
                        System.out.println("Text of note:" + ll.get(j).noteText);
                        System.out.println("Date of creation:" + ll.get(j).dateOfCreation);
                        System.out.println("Label is:" + ll.get(j).label);
                        System.out.println("----------------------------------------------------------");
                        k++;
                    }
                }
                System.out.println();
                System.out.println("If you didn't get your note you did something wrong");
                System.out.println();
            }
        }


    }

    public static void sorts(LinkedList<Note> ll, File todo) throws IOException {
        noteList(ll, todo);
        Scanner s = new Scanner(System.in);

        System.out.println("Which sort you want to use: ");
        System.out.println("1. \"back\"        - If you write this you will go back");
        System.out.println("2. \"summery\"     - If you write this you will get list of notes which sorted by summery from A to Z");
        System.out.println("3. \"Date\"        - If you write this you will get list of notes which sorted by date of creation from old to new");
        String answer = s.nextLine();

        if (answer.equals("back")) {
            System.out.println("----------------------------------------------------------");
            System.out.println("you came back");
            System.out.println("----------------------------------------------------------");
        } else if (answer.equalsIgnoreCase("summery")) {
            summerySort(ll, todo, answer);
        } else if (answer.equalsIgnoreCase("date")) {
            dateOfCreationSort(ll,todo);
        } else {
            System.out.println("----------------------------------------------------------");
            System.out.println("Command is wrong try again!");
            System.out.println("----------------------------------------------------------");
        }
    }

    public static void summerySort(LinkedList<Note> ll, File todo, String answer) throws IOException {
        Scanner s = new Scanner(System.in);
        LinkedList<String> bySummery = new LinkedList<String>();
        for (int i = 0; i < ll.size(); i++) {
            bySummery.add(i, ll.get(i).summery);
        }
        Collections.sort(bySummery);
        System.out.println("Your sorted notes: ");
        for (int i = 0; i < bySummery.size(); i++) {
            System.out.println(i + 1 + ")" + bySummery.get(i));
        }
        System.out.println();
        System.out.println("Write summery of note which you want to open: (if summery have space you must to write space) ");
        answer = s.nextLine();
        System.out.println();

        for (int j = 0; j < ll.size(); j++) {
            if (ll.get(j).summery.equals(answer)) {
                System.out.println("----------------------------------------------------------");
                System.out.println("Summery:" + ll.get(j).summery);
                System.out.println("Text of note:" + ll.get(j).noteText);
                System.out.println("Date of creation:" + ll.get(j).dateOfCreation);
                System.out.println("Label is:" + ll.get(j).label);
                System.out.println("----------------------------------------------------------");
            }
        }
        System.out.println();
        System.out.println("If you didn't get your note you did something wrong");
        System.out.println();

    }

    public static void dateOfCreationSort(LinkedList<Note> ll, File todo) throws IOException {
        Scanner s = new Scanner(System.in);
        String answer = "";
        for(int i =0; i<ll.size(); i++){
            System.out.println(i + 1 + ")" + ll.get(i).summery);
            System.out.println(ll.get(i).dateOfCreation);
        }
        System.out.println();
        System.out.println("Write number of note you want to choose: ");
        answer = s.nextLine();
        System.out.println();

        if(answer.matches("\\d+") && ll.size() > Integer.parseInt(answer) - 1){
            int index = Integer.parseInt(answer)-1;
            System.out.println("----------------------------------------------------------");
            System.out.println(index +1 + "Summery:" + ll.get(index).summery);
            System.out.println("Text of note:" + ll.get(index).noteText);
            System.out.println("Date of creation:" + ll.get(index).dateOfCreation);
            System.out.println("Label is:" + ll.get(index).label);
            System.out.println("----------------------------------------------------------");
        }

        else{
            System.out.println("You write non exist note or word");
            System.out.println();
        }

    }

    public static void allWork(File todo) throws IOException {
        Scanner s = new Scanner(System.in);
        LinkedList<Note> ll = new LinkedList<Note>();


        while (true) {
            System.out.println("Available commands: ");
            System.out.println("1. \"create\"     - If you write this you will start of creating note");
            System.out.println("2. \"stop\"       - If you write this you will stop program");
            System.out.println("3. \"fullNotes\"  - If you write this you will get full list of Notes(summery,text Of Note, date creation)");
            System.out.println("4. \"shortNotes\" - If you write this you will get short list of Notes(only summery but after this you can choose note)");
            System.out.println("5. \"reCreate\"   - If you write this you will recreate a file");
            System.out.println("6. \"delete\"     - If you write this you will delete One or some Notes");
            System.out.println("7. \"redact\"     - If you write this you can redact your created note");
            System.out.println("8. \"labels\"     - If you write this you will get full list of labels");
            System.out.println("9. \"sorts\"      - If you write this you will get some ways to sort your");
            System.out.println("Write command: ");

            String str = s.nextLine();

            if (str.equalsIgnoreCase("create")) {
                create(todo);
            } else if (str.equalsIgnoreCase("stop")) {
                System.out.println("----------------------------------------------------------");
                System.out.println("Program stopped!");
                System.out.println("----------------------------------------------------------");
                break;
            } else if (str.equalsIgnoreCase("fullNotes")) {
                fullNotes(ll, todo);
            } else if (str.equalsIgnoreCase("shortNotes")) {
                shortNotes(ll, todo);
            } else if (str.equalsIgnoreCase("reCreate")) {
                reCreate(todo);
            } else if (str.equalsIgnoreCase("delete")) {
                delete(ll, todo);
            } else if (str.equalsIgnoreCase("redact")) {
                redact(ll, todo);
            } else if (str.equalsIgnoreCase("labels")) {
                labels(ll, todo);
            } else if (str.equalsIgnoreCase("sorts")) {
                sorts(ll, todo);
            } else {
                System.out.println("Command is wrong try again!");
            }

        }

    }

}

