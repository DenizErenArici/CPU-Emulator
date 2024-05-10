import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * @author Deniz Eren Arıcı
 * @StudentNumber 20220808040
 * 26.03.2024
 */

public class CPU {
    public static int accumulator;
    public static int PC;
    public static boolean isStart = false;
    public static int f;
    public static int[] memory = new int[256];
    public static int value;
    public static ArrayList<String> INSTRUCTIONS ;


    public static void main(String[] args) throws FileNotFoundException {
        INSTRUCTIONS = getData();

        for (int i = 0; i  < INSTRUCTIONS.size(); i++) {

            String[] currentInstruction = INSTRUCTIONS.get(i).split(" ");

            PC = i + 1;

            String instruction = currentInstruction[0];
            int value = 0;

            if (!(instruction.equals("START") || instruction.equals("DISP") || instruction.equals("HALT"))) {
                value = Integer.parseInt(currentInstruction[1]);
            }

            if(isStart||instruction.equals("START"))
            switch (instruction) {
                case "START" -> {
                    START();
                    break;
                }
                case "LOAD" -> {
                    LOAD(value);
                    break;
                }
                case "ADD" -> {
                    ADD(value);
                    break;
                }
                case "JMP" -> {
                    JMP(value);
                    break;
                }
                case "ADDM" -> {
                    ADDM(value);
                    break;
                }
                case "CJMP" -> {
                    CJMP(value);
                    break;
                }
                case "CMPM" -> {
                    CMPM(value);
                    break;
                }
                case "DISP" -> {
                    DISP();
                    break;
                }
                case "HALT" -> {
                    HALT();
                    break;
                }
                case "LOADM" -> {
                    LOADM(value);
                    break;
                }
                case "STORE" -> {
                    STORE(value);
                    break;
                }
                case "MUL" -> {
                    MUL(value);
                    break;
                }
                case "SUB" -> {
                    SUB(value);
                    break;
                }
                case "MULM" -> {
                    MULM(value);
                    break;
                }
                case "SUBM" -> {
                    SUBM(value);
                    break;
                }
            }
        }


    }

    public static void incrementPC() {
        PC++;
    }

    public static void START() {
        isStart = true;
        incrementPC();
    }

    public static void LOAD(int x) {
        accumulator = x;
        incrementPC();
    }

    public static void LOADM(int x) {
        accumulator = memory[x];
        incrementPC();
    }

    public static void STORE(int x) {
        memory[x] = accumulator;
        incrementPC();
    }

    public static void CMPM(int x) {
        if (accumulator > memory[x]) {
            f = 1;
        } else if (accumulator < memory[x]) {
            f = -1;
        } else {
            f = 0;
        }
        incrementPC();
    }

    public static void CJMP(int x) {
        if (f > 0) {
            PC = x;
        }
        incrementPC();
    }

    public static void JMP(int x) {
        PC = x;
    }

    public static void ADD(int x) {
        accumulator += x;
        incrementPC();
    }

    public static void ADDM(int x) {
        accumulator += memory[x];
        incrementPC();
    }

    public static void SUBM(int x) {
        accumulator -= memory[x];
        incrementPC();
    }

    public static void SUB(int x) {
        accumulator -= x;
        incrementPC();
    }

    public static void MUL(int x) {
        accumulator *= x;
        incrementPC();
    }

    public static void MULM(int x) {
        accumulator *= memory[x];
        incrementPC();
    }

    public static void DISP() {
        System.out.print(accumulator);
        incrementPC();
    }

    public static void HALT() {
        isStart = false;
    }
    private static ArrayList<String> getData() throws FileNotFoundException  {
        ArrayList<String> commands = new ArrayList<>();
        File file = new File("file.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine().toUpperCase().trim();
                if (!str.contains("%")) {
                    while (str.contains("  "))
                        str = str.replace("  ", " ");
                    str = str.substring(str.indexOf(" ") + 1);
                    if (!str.equals("")) commands.add(str);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return commands;
    }


}






