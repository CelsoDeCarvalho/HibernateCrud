/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.sidratech.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import mz.com.sidratech.model.bean.Estudante;
import mz.com.sidratech.model.dao.EstudanteDAO;
/**
 *
 * @author celso
 */
public class TabelaDeEStudantes extends JFrame{
    
    private  JButton save_update,delete,search;
    private  JPanel painel;
    private  JTextField nome,id;
    private  JTable tabela;
    private DefaultTableModel modelo;
    JScrollPane rolo;
    JLabel _nome,_id;
    EstudanteDAO aO=null;
    Object[]colunas={"Id","Nome"};
    Object[]dados=new Object[2];
    
    public TabelaDeEStudantes(){
        setSize(500,350);
        setResizable(false);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        
        initComponents();
        layoutComponents();
        designComponents();
        action();
        actualizarTabela();
    }
    
    public static void main(String[] args) {
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
           }
        } catch (Exception e) {
              e.printStackTrace();
          }
        
        new TabelaDeEStudantes().setVisible(true);
    }

    
    private  void initComponents(){
        painel=new JPanel();
        painel.setLayout(null);
        add(painel);
        tabela=new JTable();
        modelo=new DefaultTableModel();
        rolo= new JScrollPane(tabela);
        painel.add(rolo);
        
        modelo.setColumnIdentifiers(colunas);
        tabela.setModel(modelo);
        
        nome =new JTextField();
        id =new JTextField();
        
        painel.add(nome);
        painel.add(id);
        
        save_update=new JButton("save/update");
        delete=new JButton("delete");
        search=new JButton("search"); 
        
        
        painel.add(save_update);
        painel.add(delete);
        painel.add(search);
        
        _nome =new JLabel("NOME");
        _id =new JLabel("ID");
        
        painel.add(_nome);
        painel.add(_id);
    }

    private void layoutComponents(){
        rolo.setBounds(0,120,500,195);
        nome.setBounds(10,40,150,25);
        id.setBounds(170,40,50,25);
        save_update.setBounds(10,80,150,25);
        delete.setBounds(180,80,80,25);
        search.setBounds(280,80,90,25);
        _nome.setBounds(10,10,70,30);
        _id.setBounds(170,10,70,30);
    }

    private void designComponents(){
        painel.setBackground(Color.darkGray);
        _nome.setForeground(Color.WHITE);
        _id.setForeground(Color.WHITE);
    }
    
    private void action(){
        
        delete.addActionListener((ActionEvent e) -> {
            aO=new EstudanteDAO();
            
            int i = tabela.getSelectedRow();
                if(i >= 0){    
                    aO.delete(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
                    actualizarTabela();
                }    
                else{
                    JOptionPane.showMessageDialog(null,"Nenhum funcionario selecionado","",JOptionPane.INFORMATION_MESSAGE);
                }
            
        });
        
        save_update.addActionListener((ActionEvent e) -> {
            aO=new EstudanteDAO(); 
            if(nome.getText()!=null || !nome.getText().isEmpty() || id.getText()!=null || !id.getText().isEmpty()){
                Estudante estudante=new Estudante(nome.getText(), Integer.parseInt(id.getText()));
                aO.create_update(estudante);
                actualizarTabela();
            }    
        });
                
        search.addActionListener((ActionEvent e) -> {
            aO=new EstudanteDAO();
            modelo.setNumRows(0);
            Estudante estudante=aO.search(Integer.parseInt(id.getText()));
            dados[0]=estudante.getId();
            dados[1]=estudante.getNome();
            modelo.addRow(dados);
            
        });
                        
    }
    
    private void actualizarTabela(){ 
        modelo.setNumRows(0);
        aO=new EstudanteDAO(); 
        for(Estudante es:aO.list()){
           dados[0]=es.getId();
           dados[1]=es.getNome();
           modelo.addRow(dados);
        }
    }
    
}
