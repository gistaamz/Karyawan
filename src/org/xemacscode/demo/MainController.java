/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xemacscode.demo;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Windows 10
 */
public class MainController implements Initializable {
   
   
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTittle;
    @FXML
    private TextField tfAuthor;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfPages;
    @FXML
    private TableView<Karyawans> tvKaryawans;
    @FXML
    private TableColumn<Karyawans, Integer> colId;
    @FXML
    private TableColumn<Karyawans, String> colTittle;
    @FXML
    private TableColumn<Karyawans, String> colAuthor;
    @FXML
    private TableColumn<Karyawans, Integer> colYear;
    @FXML
    private TableColumn<Karyawans, Integer> colPages;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
        if(event.getSource() == btninsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
    }else if (event.getSource() == btnDelete) {
        deleteButton();
    }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showKaryawans();
    }  
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<Karyawans> getKaryawansList(){
        ObservableList<Karyawans> karyawanList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM karyawans";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Karyawans karyawans;
            while(rs.next()){
                karyawans = new Karyawans(rs.getInt("id"), rs.getString("nama"), rs.getString("jabatan"), rs.getInt("tahunMasuk"),rs.getInt("pages"));
                karyawanList.add(karyawans);
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return karyawanList;
    }
    
    public void showKaryawans(){
        ObservableList<Karyawans> list = getKaryawansList();
        
        colId.setCellValueFactory(new PropertyValueFactory<Karyawans, Integer>("id"));
        colTittle.setCellValueFactory(new PropertyValueFactory<Karyawans, String>("nama"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Karyawans, String>("jabatan"));
        colYear.setCellValueFactory(new PropertyValueFactory<Karyawans, Integer>("tahunMasuk"));
        colPages.setCellValueFactory(new PropertyValueFactory<Karyawans, Integer>("pages"));
        
        tvKaryawans.setItems(list);
    }
    private void insertRecord(){
        String query = "INSERT INTO karyawans VALUES (" + tfId.getText() + ",'" + tfTittle.getText() + "','" + tfAuthor.getText() + "'," + tfYear.getText() + "," + tfPages.getText() + ")";
        executeQuery(query);
        showKaryawans();
    }
private void updateRecord(){
    String query = "UPDATE karyawans SET nama = '" + tfTittle.getText() + "', jabatan='"+tfAuthor.getText()+ "', tahunMasuk = "+tfYear.getText()+", pages = "+tfPages.getText()+" WHERE id = " + tfId.getText() + "";
    executeQuery(query);
    showKaryawans();
}
private void deleteButton(){
    String query = "DELETE FROM karyawans WHERE id =" + tfId.getText() + "";
    executeQuery(query);
    showKaryawans();
}
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
        st = conn.createStatement();
        st.executeUpdate(query);
        
    }catch(Exception ex){
         ex.printStackTrace();
         
            }
    }
    @FXML
    private void handleMouseAction(MouseEvent event){ 
        Karyawans karyawan = tvKaryawans.getSelectionModel().getSelectedItem();
        tfId.setText("" +karyawan.getId());
        tfTittle.setText(karyawan.getNama());
        tfAuthor.setText(karyawan.getJabatan());
        tfYear.setText("" +karyawan.getTahunMasuk());
        tfPages.setText("" +karyawan.getPages());
    }

    }
    

