import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Todo {
    public static void main(String[] args) throws IOException {
        File todo = new File("C:\\Users\\User", "Todo list");
        Todo.note(todo);
    }

    public static void noteList(File todo) throws IOException {
        FileReader fr = new FileReader(todo);
        Scanner sr = new Scanner(fr);

        while(sr.hasNextLine()){
            System.out.println(sr.nextLine());
            System.out.println("Name of note: " + sr.nextLine());
            System.out.println("Text of note: " + sr.nextLine());

            System.out.println();
        }

        fr.close();
    }

    public static void write(File todo,int numberOfNote) throws IOException {
        FileWriter fw = new FileWriter(todo,true);
        Scanner sc = new Scanner(System.in);
        fw.write(numberOfNote + "note: \n");

        System.out.println("Write name of note: ");
        String text = sc.nextLine() + "\n";
        fw.write(text);

        System.out.println("Write text of note: ");
        text = sc.nextLine() + "\n";
        fw.write(text);

        fw.close();
    }
    public static void reCreate(File todo) throws IOException {
        FileWriter fw = new FileWriter(todo,false);

        fw.write("");

        System.out.println("File is recreated!");
    }


    public static void note(File todo) throws IOException {
        Scanner s = new Scanner(System.in);
        int numberOfNote = 0;

        System.out.println("If you want create note write: create");
        System.out.println("If you want stop program write: stop");
        System.out.println("If you want open NoteList write: note list");
        System.out.println("If you want delete NoteList write :reCreate");

        System.out.println();

        while (true) {
            System.out.println("Write command: ");

            String str = s.nextLine();

            if (str.equalsIgnoreCase("create")) {
                numberOfNote++;
                write(todo,numberOfNote);
            } else if (str.equalsIgnoreCase("Note list")) {
              noteList(todo);
            } else if (str.equalsIgnoreCase("stop")) {
                System.out.println("Program stopped!");
                break;
            }
            else if (str.equalsIgnoreCase("reCreate")){
                reCreate(todo);
            }
                else {
                System.out.println("Command is wrong try again!");
            }

        }

    }

}

