package sample;

public class StudentRecord {

    String student_id;
    float assignments;
    float midterm;
    float final_exam;
    float final_mark;
    String letter_grade;

    public StudentRecord(String id1, float assignments1, float midterm1, float finalExam1) {
        this.student_id =  id1;
        this.assignments = assignments1;
        this.midterm = midterm1;
        this.final_exam = finalExam1;
    }
    public String getStudentID() {
        return this.student_id;
    }
    public float getAssignments() {
        return this.assignments;
    }
    public float getMidterm() {
        return this.midterm;
    }
    public float getFinalExam() {
        return this.final_exam;
    }

    public float getFinalMark() {
        float fin_mark = (20 * this.assignments + 30 * this.midterm + 50 * this.final_exam) / (20 + 30 + 50);
        this.final_mark = fin_mark;
        return this.final_mark;
    }
    public String setLetter() {
        int set_letter = ((int) getFinalMark());
        if (set_letter >= 0 && set_letter <= 49)
        {
            return "F";
        } else if (set_letter > 49 && set_letter <= 59)
        {
            return "D";
        } else if (set_letter > 59 && set_letter <= 69)
        {
            return "C";
        } else if (set_letter > 69 && set_letter <= 79)
        {
            return "B";
        } else if (set_letter > 79 && set_letter <= 100)
        {
            return "A";
        }
        else
        {
            return "ERROR";
        }
    }
    public String getLetterGrade(){

        return this.letter_grade= setLetter();
    }
}
