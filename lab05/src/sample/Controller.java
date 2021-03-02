package sample;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
public class Controller {

    @FXML
    private TableColumn student_id;
    @FXML
    private TableColumn midterm;
    @FXML
    private TableColumn assignments;
    @FXML
    private TableColumn final_mark;
    @FXML
    private TableColumn letter_grade;
    @FXML
    private TableColumn final_exam;
    @FXML
    private TableView table_view;

    public void initialize(){
        student_id.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        assignments.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        midterm.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        final_mark.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        letter_grade.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        final_exam.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        table_view.setItems(DataSource.getAllMarks());
        table_view.getColumns().addAll(student_id,assignments,midterm,final_exam,final_mark,letter_grade);
    }
}