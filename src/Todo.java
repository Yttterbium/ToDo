import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

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
        System.out.println(ll);
        if (ll.isEmpty()) {
            System.out.println("----------------------------------------------------------");
            System.out.println("You don't have any note try to create one!");
            System.out.println("----------------------------------------------------------");
        }
        else{
            System.out.println("----------------------------------------------------------");
            for (int i = 0; i < ll.size(); i++) {
                System.out.println(i + 1 + "." + "Summery: " + ll.get(i).summery);
                System.out.println("Text of note: " + ll.get(i).noteText);
                System.out.println("Date of creation: " + ll.get(i).dateOfCreation);
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

        int i = 0;
        int j = 0;

        for (String end : sb.toString().split("▄")) {
            for (String midend : end.split("◙")) {

                if (j == 0) {
                    name = midend;
                    j++;
                } else if (j == 1) {
                    text = midend;
                    j++;
                } else if (j == 2) {
                    date = midend;

                    ll.add(i, new Note(name, text, date));
                }

            }
            i++;
            j = 0;
        }
    }


    public static void create(File todo) throws IOException {
        FileWriter fw = new FileWriter(todo, true);
        Scanner sc = new Scanner(System.in);
        String time = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());

        System.out.println("Write name of note: ");
        String text = sc.nextLine() + "\n";
        fw.write(text);
        fw.write("◙ \n");

        System.out.println("If you want end creation of note write: \"create.end\" ");
        System.out.println("Write text of note: ");

        while (!text.equalsIgnoreCase("create.end")) {
            text = sc.nextLine();
            if (text.equalsIgnoreCase("create.end")) {
                fw.write("◙ \n");
                System.out.println("----------------------------------------------------------");
                System.out.println("Note created!");
                System.out.println("----------------------------------------------------------");
            } else {
                fw.write(text);
                fw.write("\n");
            }
        }

        time = "Day of creating is: " + time + "\n";
        fw.write(time);
        fw.write("▄ \n");
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
        }

        else {
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
                        fw.write(ll.get(i).dateOfCreation + "\n▄\n");
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


    public static void reCreate(File todo) throws IOException {
        FileWriter fw = new FileWriter(todo, false);

        fw.write("");

        System.out.println("----------------------------------------------------------");
        System.out.println("File is recreated!");
        System.out.println("----------------------------------------------------------");
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
            } else {
                System.out.println("Command is wrong try again!");
            }

        }

    }

}

