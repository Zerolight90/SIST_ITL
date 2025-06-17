package pm.client;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

import pm.vo.EmpVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 100
 */
public class MyDialog extends javax.swing.JDialog {
    EmpFrame parent;

    public MyDialog(EmpFrame parent, boolean modal, String str){
        super(parent, modal);
        this.parent = parent;
        this.setTitle(str);

        initComponents(); // 화면 구성
        jButton1.setText(str);
        empno_tf.setEditable(true); //활성화

        //이벤트 감시자 등록
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //추가모드에서 클릭 했는지? 아니면 검색 모드에서 클릭 했는지?
                String msg = e.getActionCommand(); // Jbutton1의 값을 가져 온다
//                System.out.println(msg);
                //검색 기능
                if (msg.equalsIgnoreCase("검색")){
                    //사용자가 입력한 값이 무엇인지 모르기 때문에 정보를 모두 가져온다.
                    String empno = empno_tf.getText().trim();
                    String ename = ename_tf.getText().trim();
                    String job = job_tf.getText().trim();
                    String hdate = hdate_tf.getText().trim();
                    String sal = sal_tf.getText().trim();
                    String dname = dname_tf.getText().trim();

                    EmpVO vo = new EmpVO();
                    if(empno.length() > 0){
                        vo.setEmpno(empno);
                    }
                    if(ename.length() > 0){
                        vo.setEname(ename);
                    }
                    if(job.length() > 0){
                        vo.setJob(job);
                    }
                    if(hdate.length() > 0){
                        vo.setHiredate(hdate);
                    }
                    if(sal.length() > 0){
                        vo.setSal(sal);
                    }
                    if(dname.length() > 0){
                        vo.setDname(dname);
                    }
                    parent.search(vo);
                }// 바깥쪽 if문 종료
            }
        });

        this.setVisible(true);
    }

    public MyDialog(EmpFrame parent, boolean modal, EmpVO vo) {
        super(parent, modal);
        this.parent = parent;

        initComponents(); // 화면 구성
        empno_tf.setText(vo.getEmpno());
        ename_tf.setText(vo.getEname());
        job_tf.setText(vo.getJob());
        hdate_tf.setText(vo.getHiredate());
        comm_tf.setText(vo.getComm());
        sal_tf.setText(vo.getSal());
        dname_tf.setText(vo.getDname());
        setVisible(true);

        // 이벤트 감지자 등록
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 저장버튼을 클릭할 때마다 수행하는 곳
                String empno = empno_tf.getText().trim();
                String emape = ename_tf.getText().trim();
                String job = job_tf.getText().trim();
                String hdate = hdate_tf.getText().trim();
                String sal = sal_tf.getText().trim();
                String comm = comm_tf.getText().trim();
                if (comm.length()<1){
                    comm = null;
                }
                String dname = dname_tf.getText().trim();

                // 위에서 수정된 정보들을 EmpVO에 담아 보낸다.
                EmpVO vo = new EmpVO();
                vo.setEmpno(empno);
                vo.setEname(emape);
                vo.setJob(job);
                vo.setHiredate(hdate);
                vo.setSal(sal);
                vo.setComm(comm);
                vo.setDname(dname);

                parent.updateData(vo); // EmpFrame에 있는 함수 호출
                dispose();
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        empno_tf = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ename_tf = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        job_tf = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        hdate_tf = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        sal_tf = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        comm_tf = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dname_tf = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(8, 1));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel1.setText("사번:");
        jPanel1.add(jLabel1);

        empno_tf.setColumns(8);
        empno_tf.setEditable(false);
        jPanel1.add(empno_tf);

        getContentPane().add(jPanel1);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel2.setText("이름:");
        jPanel2.add(jLabel2);

        ename_tf.setColumns(8);
        jPanel2.add(ename_tf);

        getContentPane().add(jPanel2);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel3.setText("직종:");
        jPanel3.add(jLabel3);

        job_tf.setColumns(8);
        jPanel3.add(job_tf);

        getContentPane().add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel4.setText("입사일:");
        jPanel4.add(jLabel4);

        hdate_tf.setColumns(8);
        jPanel4.add(hdate_tf);

        getContentPane().add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel5.setText("급여:");
        jPanel5.add(jLabel5);

        sal_tf.setColumns(8);
        jPanel5.add(sal_tf);

        getContentPane().add(jPanel5);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel6.setText("보너스:");
        jPanel6.add(jLabel6);

        comm_tf.setColumns(8);
        jPanel6.add(comm_tf);

        getContentPane().add(jPanel6);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel7.setText("부서명:");
        jPanel7.add(jLabel7);

        dname_tf.setColumns(8);
        dname_tf.setEditable(false);
        jPanel7.add(dname_tf);

        getContentPane().add(jPanel7);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton1.setText("저장");
        jPanel8.add(jButton1);

        jButton2.setText("취소");
        jPanel8.add(jButton2);

        getContentPane().add(jPanel8);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField comm_tf;
    private javax.swing.JTextField dname_tf;
    private javax.swing.JTextField empno_tf;
    private javax.swing.JTextField ename_tf;
    private javax.swing.JTextField hdate_tf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField job_tf;
    private javax.swing.JTextField sal_tf;
    // End of variables declaration//GEN-END:variables
}
