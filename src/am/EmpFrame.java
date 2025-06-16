/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package am;

import am.vo.EmpVO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Reader;
import java.util.List;

/**
 *
 * @author 쌍용교육센터
 */
public class EmpFrame extends JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EmpFrame.class.getName());

    String[][] data;
    String[] c_name = {"사번", "이름", "입사일", "급여", "부서명"};
    SqlSessionFactory factory;


    public EmpFrame() {

        initComponents();
        init(); //db연결
        allData();

        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String start = jTextField1.getText().trim();
                String end = jTextField2.getText().trim();
                if (start.length()>0 && end.length()>0){
                    //mapper에 search_date를 호출하기 위해 지정된 파라미터 객체(parameterType
                }
            }
        });

    }


    private void allData(){
        SqlSession ss = factory.openSession();
        List<EmpVO> list = ss.selectList("emp.all");
        viewTable(list);
        ss.close();
    }

    public void viewTable( List<EmpVO> list){
        //받은 list를 2차원 배열로 변환한 후 JTable에 표현 하자
        data = new String[list.size()][c_name.length];
        int i =0;
        for (EmpVO vo : list){
            data[i][0] = vo.getEmpno(); // 사번
            data[i][1] = vo.getEname(); // 이름
            data[i][2] = vo.getHiredate(); //입사일
            data[i][3] = vo.getSal(); //급여
            data[i][4] = vo.getDname(); // 부서이름
            i++;
        }
         jTable1.setModel(new DefaultTableModel(data,c_name));
    }
    
    private void init(){
        try{
            Reader r = Resources.getResourceAsReader("am/config/conf.xml");
            factory = new SqlSessionFactoryBuilder().build(r);
            r.close();
            this.setTitle("작업준비 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jTextField2 = new JTextField();
        jButton1 = new JButton();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("시작일:");
        jPanel1.add(jLabel1);

        jTextField1.setColumns(8);
        jPanel1.add(jTextField1);

        jLabel2.setText("      ");
        jPanel1.add(jLabel2);

        jLabel3.setText("종료일:");
        jPanel1.add(jLabel3);

        jTextField2.setColumns(8);
        jPanel1.add(jTextField2);

        ImageIcon icon = new ImageIcon("src/images/search.png");
        Image img = icon.getImage().getScaledInstance(
                    21,21,Image.SCALE_SMOOTH);
        jButton1.setIcon(new ImageIcon(img));

        jButton1.setPreferredSize(new Dimension(21, 21));
        jButton1.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            data,c_name ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(() -> new EmpFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField jTextField1;
    private JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
