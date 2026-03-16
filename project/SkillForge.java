

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class SkillForge{

    static String studentFile = "student.csv";
    static String assignmentFile = "assignments.csv";
    static String scoreFile = "scores.csv";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== SkillForge System =====");
            System.out.println("1. Faculty");
            System.out.println("2. Student");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    facultyMenu(sc);
                    break;

                case 2:
                    studentMenu(sc);
                    break;

                case 3:
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // FACULTY MENU
    static void facultyMenu(Scanner sc) {

        while (true) {

            System.out.println("\n----- Faculty Menu -----");
            System.out.println("1. Show All Students");
            System.out.println("2. Search Student");
            System.out.println("3. Add Assignment");
            System.out.println("4. View Assignments");
            System.out.println("5. Add Student Score");
            System.out.println("6. View Student Scores");
            System.out.println("7. Back");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    showStudents();
                    break;

                case 2:
                    searchStudent(sc);
                    break;

                case 3:
                    addAssignment(sc);
                    break;

                case 4:
                    viewAssignments();
                    break;

                case 5:
                    addScore(sc);
                    break;

                case 6:
                    viewScores();
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // STUDENT MENU
    static void studentMenu(Scanner sc) {

        while (true) {

            System.out.println("\n----- Student Menu -----");
            System.out.println("1. Register Student");
            System.out.println("2. Search My Profile");
            System.out.println("3. View Assignments");
            System.out.println("4. View My Scores");
            System.out.println("5. Back");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent(sc);
                    break;

                case 2:
                    searchStudent(sc);
                    break;

                case 3:
                    viewAssignments();
                    break;

                case 4:
                    viewMyScores(sc);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // REGISTER STUDENT
    static void addStudent(Scanner sc) {

        try {

            System.out.print("Enter Student ID: ");
            String id = sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            String age = sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Faculty ID: ");
            String facultyId = sc.nextLine();

            System.out.print("Enter Faculty Name: ");
            String facultyName = sc.nextLine();

            FileWriter fw = new FileWriter(studentFile, true);

            fw.write(id + "," + name + "," + age + "," + course + "," + gender + "," + facultyId + "," + facultyName + "\n");

            fw.close();

            System.out.println("Student registered successfully!");

        } catch (Exception e) {
            System.out.println("Error writing student file.");
        }
    }

    // SHOW ALL STUDENTS
    static void showStudents() {

        try {

            BufferedReader br = new BufferedReader(new FileReader(studentFile));
            String line;

            System.out.println("\n------ Student List ------");

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading student file.");
        }
    }

    // SEARCH STUDENT
    static void searchStudent(Scanner sc) {

        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        try {

            BufferedReader br = new BufferedReader(new FileReader(studentFile));
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data[0].equals(id)) {

                    System.out.println("\nStudent Found:");
                    System.out.println(line);

                    br.close();
                    return;
                }
            }

            br.close();
            System.out.println("Student not found.");

        } catch (Exception e) {
            System.out.println("Error searching student.");
        }
    }

    // ADD ASSIGNMENT
    static void addAssignment(Scanner sc) {

        try {

            System.out.print("Enter Assignment ID: ");
            String id = sc.nextLine();

            System.out.print("Enter Assignment Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Faculty ID: ");
            String facultyId = sc.nextLine();

            FileWriter fw = new FileWriter(assignmentFile, true);

            fw.write(id + "," + title + "," + course + "," + facultyId + "\n");

            fw.close();

            System.out.println("Assignment added successfully!");

        } catch (Exception e) {
            System.out.println("Error writing assignment file.");
        }
    }

    // VIEW ASSIGNMENTS
    static void viewAssignments() {

        try {

            BufferedReader br = new BufferedReader(new FileReader(assignmentFile));
            String line;

            System.out.println("\n--------------------------------------------------");
            System.out.printf("%-15s %-20s %-10s %-10s\n",
                    "AssignmentID", "Title", "Course", "FacultyID");
            System.out.println("--------------------------------------------------");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 4) {
                    System.out.printf("%-15s %-20s %-10s %-10s\n",
                            data[0], data[1], data[2], data[3]);
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading assignments file.");
        }
    }

    // ADD SCORE
    static void addScore(Scanner sc) {

        try {

            System.out.print("Enter Score ID: ");
            String scoreId = sc.nextLine();

            System.out.print("Enter Student ID: ");
            String studentId = sc.nextLine();

            System.out.print("Enter Assignment ID: ");
            String assignmentId = sc.nextLine();

            System.out.print("Enter Score: ");
            String score = sc.nextLine();

            String date = LocalDate.now().toString();

            FileWriter fw = new FileWriter(scoreFile, true);

            fw.write(scoreId + "," + studentId + "," + assignmentId + "," + score + "," + date + "\n");

            fw.close();

            System.out.println("Score added successfully!");

        } catch (Exception e) {
            System.out.println("Error writing score file.");
        }
    }

    // VIEW ALL SCORES (FACULTY)
    static void viewScores() {

        try {

            BufferedReader br = new BufferedReader(new FileReader(scoreFile));
            String line;

            System.out.println("\nScoreID | StudentID | AssignmentID | Score | Date");
            System.out.println("-------------------------------------------------");

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading scores file.");
        }
    }

    // VIEW ONLY THAT STUDENT SCORES
    static void viewMyScores(Scanner sc) {

        System.out.print("Enter your Student ID: ");
        String studentId = sc.nextLine();

        boolean found = false;

        try {

            BufferedReader br = new BufferedReader(new FileReader(scoreFile));
            String line;

            System.out.println("\n---------------------------------------------------");
            System.out.printf("%-10s %-12s %-15s %-10s %-12s\n",
                    "ScoreID","StudentID","AssignmentID","Score","Date");
            System.out.println("---------------------------------------------------");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length >= 5 && data[1].equals(studentId)) {

                    System.out.printf("%-10s %-12s %-15s %-10s %-12s\n",
                            data[0], data[1], data[2], data[3], data[4]);

                    found = true;
                }
            }

            br.close();

            if (!found) {
                System.out.println("No scores found for this student.");
            }

        } catch (Exception e) {
            System.out.println("Error reading scores file.");
        }
    }
}