/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pm.client;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pm.vo.DeptVO;
import pm.vo.EmpVO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 100
 */
public class EmpFrame extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EmpFrame.class.getName());

    String[][] data;
    String[] c_name = {"사번", "이름", "입사일", "급여", "부서명"};

    SqlSessionFactory factory;
    List<EmpVO> list;
    List<DeptVO> depts;
    int i;

    JMenuBar bar;
    JMenu file_M;
    JMenuItem add_item, search_item, exit_item;

    public EmpFrame() {
        initComponents(); // 화면 구성

        //메뉴 작업
        bar = new JMenuBar();
        file_M = new JMenu("파일");
        add_item = new JMenuItem("추가");
        search_item = new JMenuItem("검색");
        exit_item = new JMenuItem("종료");

        file_M.add(add_item);
        file_M.add(search_item);
        file_M.addSeparator(); //구분선
        file_M.add(exit_item);

        bar.add(file_M);
        this.setJMenuBar(bar);

        init(); // DB연결
        allData(); // 적용된 data를 보여줌

        // 이벤트 감지자 등록
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); // 프로그램 종료
            }
        });

        // ----------------------------------- 날짜를 입력하고 버튼을 눌렀을 때 실행-------------------
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 검색 버튼을 누를 때 수행함!
                ArrayList<String> dept_list = new ArrayList<>();
                //위의 List에 Map에 dept_list라킄 키로 저장될 객체다.

                //사용자가선택한 checkbox가 무엇인지 알아 내자
                for (JCheckBox box : chk_ar){
                    if (box.isSelected()) {//선택되었다면 true , 안됐다면 false
                        //선택된 체크박스의 문자열
                        String str = box.getText();
//                        System.out.println(str);
                        //str을 depts라는 List에서 DeptVO를 대상으로 찾아낸다.
                        // 부서코드를 dept_list에 저장
                        for (DeptVO dvo : depts){
                            if(dvo.getDname().equalsIgnoreCase(str)){
                                dept_list.add(dvo.getDeptno());
                                break;

                            }
                        }
                    }
                }// for의 끝
                System.out.println(dept_list);
                Map<String, ArrayList<String>> map = new HashMap<>();
                map.put("dept_list",dept_list);

                SqlSession ss = factory.openSession();
                List<EmpVO> list = ss.selectList("emp.search_deptno", map);

                viewTable(list);
              }
        });

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 테이블에서 더블클릭을 했는지 확인
                int cnt = e.getClickCount();
                if(cnt == 2){
                    // JTable에 선택된 행, index를 얻어내자
                    i = jTable1.getSelectedRow();
//                    setTitle(String.valueOf(i));
                    // 위의 i는 List<EmpVO>에 접근하기 위한 index다.
                    EmpVO vo = list.get(i);
//                    setTitle(vo.getEname());
                    new MyDialog(EmpFrame.this,false, vo);
                }
            }
        });

        add_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyDialog(EmpFrame.this,false, "추가");
            }
        });

        search_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MyDialog(EmpFrame.this,false, "검색");
            }
        });
    } // 생성자 끝

    private void init() {
        try {
            Reader r = Resources.getResourceAsReader("pm/config/conf.xml");

            factory = new SqlSessionFactoryBuilder().build(r);
            r.close();
            this.setTitle("준비완료");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void allData() {
        SqlSession ss = factory.openSession();
        depts = ss.selectList("emp.dept_all");
        viewDept();
        ss.close();
    }

    private void viewDept(){
        //맴버변수인 depts의 길이 만큼 chk_ar이라는 배열을 생성
        chk_ar = new JCheckBox[depts.size()];
        // 아직 JCheckVBox가 만들어 지지 않았따.
        int i = 0;
        for (DeptVO dvo : depts){
            chk_ar[i] = new JCheckBox(dvo.getDname());
            jPanel1.add(chk_ar[i]);
            i++;
        }
    }

    private void viewTable(List<EmpVO> list) {
        // 받은 list를 2차원 배열로 변환한 후 JTable에 표현
        data = new String[list.size()][c_name.length]; // 첫번째자리는 컬럼들의 수만큼,
        int i = 0;
        for(EmpVO vo : list){
            data[i][0] = vo.getEmpno(); // 사번
            data[i][1] = vo.getEname(); // 이름
            data[i][2] = vo.getHiredate(); // 입사일
            data[i][3] = vo.getSal(); // 급여
            data[i][4] = vo.getDname(); // 부서명
            i++;
        }
        jTable1.setModel(new DefaultTableModel(data, c_name){
            @Override
            public boolean isCellEditable(int row, int column) { // Table 수정 못하게 함
                return false;
            }
        }); // 바뀐 데이터의 값을 Table에 적용함
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();

        jButton1 = new JButton();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        jLabel1.setText("부서검색:");
        jPanel1.add(jLabel1);




        ImageIcon icon = new ImageIcon("src/images/Search.png");
        Image img = icon.getImage().getScaledInstance(21, 21, Image.SCALE_SMOOTH);
        jButton1.setIcon(new ImageIcon(img));
        jButton1.setPreferredSize(new Dimension(21, 21));
//        jButton1.setBorder(new BevelBorder(BevelBorder.RAISED));
        jButton1.setBorder(BorderFactory.createLineBorder(Color.green, 1)); // 버튼의 효과 추가
        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, BorderLayout.PAGE_START);

        jTable1.setModel(new DefaultTableModel(
                data, c_name));
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
    private  JCheckBox[] chk_ar; //부서 수만큼 만들기 위해 배열로 준비
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;


    public void updateData(EmpVO vo) {
        SqlSession ss = factory.openSession();
        int cnt = ss.update("emp.edit", vo);
        if(cnt > 0) {
            ss.commit();
            // JTable값을 갱신한다. 사용자가 JTable에서 더블클릭한
            // 행번호(index)를 알아야 한다.
            jTable1.setValueAt(vo.getEname(), i, 1); // 이름
            jTable1.setValueAt(vo.getHiredate(), i, 2); // 입사일
            jTable1.setValueAt(vo.getSal(), i, 3); // 급여
            // 멤버변수 List의 내용도 변경해야 한다.
            list.set(i, vo);
            viewTable(list);
        } else
            ss.rollback();
        ss.close();
    }

    public void search(EmpVO vo){
        SqlSession ss = factory.openSession();
        list = ss.selectList("emp.search", vo);
        viewTable(list);

        ss.close();
    }
    // End of variables declaration//GEN-END:variables
}
