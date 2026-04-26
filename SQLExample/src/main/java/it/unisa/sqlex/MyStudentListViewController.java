/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.sqlex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 *
 * @author lucagreco
 */
public class MyStudentListViewController implements Initializable {
    
    
    @FXML
    private MenuItem saveButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField codeField;
    @FXML
    private Button removeButton;
    @FXML
    private TableView<Studente> studentTable;
    @FXML
    private TableColumn<Studente, String> nameClm;
    @FXML
    private TableColumn<Studente, String> surnameClm;
    @FXML
    private TableColumn<Studente, String> codeClm;
    @FXML
    private TextField searchBar;

    private ObservableList<Studente> studenti;
    
    
    private StudenteDAO db;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameClm.setCellValueFactory(new PropertyValueFactory("nome"));
        surnameClm.setCellValueFactory(new PropertyValueFactory("cognome"));
        codeClm.setCellValueFactory(new PropertyValueFactory("matricola"));
        
        nameClm.setCellFactory(TextFieldTableCell.forTableColumn());

        studenti = FXCollections.observableArrayList();

        db = new StudenteDAO();
        
        try {
            studenti.addAll(db.elencaTutti());
        } catch (SQLException ex) {
            showWarning("Errore di connessione.", ex.getMessage());
            Logger.getLogger(MyStudentListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        studentTable.setItems(studenti);

        searchBar.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                studentTable.setItems(studenti);
            }
            else {
                try {
                    //Se non avessi avuto il DB sarebbe stato utile utilizzare una FilteredList
                    // e settare il Predicate in questo modo:
                    /*
                     * FilteredList<Studente> listaFiltrata = new FilteredList<>(studenti, s -> {
                     *      if (searchBar.getText().isEmpty) return true;
                     *      else return
                     *              s.getMatricola.toLowerCase().contains(searchBar.getText().toLowerCase()) ||
                     *              s.getNome().toLowerCase().contains(searchBar.getText().toLowerCase()) ||
                     *              s.getCognome().toLowerCase().contains(searchBar.getText().toLowerCase());
                     * })
                     *
                     * o in alternativa usare gli stream:
                     * List<Studente> l = studenti.stream().filter(s -> s.getMatricola().toLowerCase().contains(searchBar.getText().toLowerCase()) ||
                            s.getNome().toLowerCase().contains(searchBar.getText().toLowerCase()) ||
                            s.getCognome().toLowerCase().contains(searchBar.getText().toLowerCase())).toList();
                     *
                     * ObservableList<Studente> listaFiltrata = FXCollections.observableArrayList(l);
                     * studentTable.setItems(listaFiltrata);
                     */


                    List<Studente> risultati = db.cerca(new String[]{newValue});

                    ObservableList<Studente> listaFiltrata = FXCollections.observableArrayList(risultati);
                    studentTable.setItems(listaFiltrata);
                } catch (SQLException e) {
                    showWarning("Errore di ricerca", e.getMessage());
                }
            }
        }));
    }

    private void initItems() {
        studenti.add(new Studente("Mario", "Rossi", "06127001"));
        studenti.add(new Studente("Ernesto", "Rossi", "06127002"));
        studenti.add(new Studente("Davide", "Rossi", "06127003"));
    }

    @FXML
    private void openFile(ActionEvent event) {
        //è richiesto di modificare StudenteDAO per supportare operazioni su DB con nome generico. Non usare.

        Window window = nameField.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Apri DB di Studenti");

        File chosenDB = fc.showOpenDialog(window);

        try {
            if (chosenDB == null) {
                showWarning("DB inesistente", "Il DB scelto è inesistente, riprovare");
            }
            else {
                StudenteDAO sd = new StudenteDAO("jdbc:sqlite:" + chosenDB.getAbsolutePath(), "", "");

                this.db = sd;

                studentTable.setItems(FXCollections.observableArrayList(db.elencaTutti()));
            }
        } catch (SQLException e) {
            showWarning("Errore", e.getMessage());
        }

    }

    //da modificare in caso si volesse fare il commit della transazione manualmente (setAutoCommit(false))
    @FXML
    private void saveFile(ActionEvent event) {
        
        FileChooser fc = new FileChooser();

        File file = fc.showSaveDialog(nameField.getParent().getScene().getWindow());
        
        if(file != null) {
        
            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {

                for(Studente s : studenti) {
                    
                    pw.append(s.getNome() + ';');
                    pw.append(s.getCognome() + ';');
                    pw.append(s.getMatricola() + '\n');
                }

            } catch (IOException ex) {
                Logger.getLogger(MyStudentListViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void quitApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void addStudent(ActionEvent event) {
        Studente s = new Studente(nameField.getText(), surnameField.getText(),codeField.getText());
        
        try {
            db.aggiungi(s);
            studenti.add(s);
        } catch (SQLException ex) {
            
            showWarning("Impossibile aggiungere.", ex.getMessage());
            
            Logger.getLogger(MyStudentListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void removeStudent(ActionEvent event) {
        Studente s = studentTable.getSelectionModel().getSelectedItem();
        
        try {
            db.rimuovi(s);
            studenti.remove(s);
        } catch (SQLException ex) {
            showWarning("Impossibile rimuovere.", ex.getMessage());

            Logger.getLogger(MyStudentListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void updateName(TableColumn.CellEditEvent<Studente, String> event) {
        Studente s = studentTable.getSelectionModel().getSelectedItem();
        
        s.setNome(event.getNewValue());
        
        try {
            db.aggiorna(s);
            
        } catch (SQLException ex) {
            showWarning("Impossibile aggiornare.", ex.getMessage());
            
            s.setNome(event.getOldValue());
            
            Logger.getLogger(MyStudentListViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private void showWarning(String title, String content) {
        Alert a = new Alert(AlertType.WARNING);
        
        a.setTitle(title);
        a.setHeaderText(title);
        a.setContentText(content);
        
        a.show();

    }

    private void showInfo(String title, String content) {
        Alert a = new Alert(AlertType.INFORMATION);

        a.setTitle(title);
        a.setHeaderText(title);
        a.setContentText(content);

        a.show();

    }

    @FXML
    public void onPressSearch(ActionEvent actionEvent) {
        String searchWord = searchBar.getText();

        try {
            Studente s = db.cerca(searchWord);
            showInfo("Studente trovato!", s.toString());
        } catch (SQLException e) {
            showWarning("Studente non trovato", "Lo studente di matricola: " + searchWord + " non esiste.");
        }

    }
}
