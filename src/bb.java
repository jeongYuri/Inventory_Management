
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

import static java.awt.color.ICC_Profile.*;
import static javax.swing.SwingConstants.HORIZONTAL;

public class bb extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_3;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_12;
    private JTextField textField_13;
    private JTextField textField_14;
    private JTextField textField_15;
    private JTextField textField_16;
    private JTextField textField_17;
    private JTextField textField_18;
    private JTextField textField_19;
    private JTextField textField_20;
    private JTextField textField_21;
    private JTextField textField_22;
    private JTextField textField_23;
    private JTextField textField_24;
    private JTextField textField_25;
    private JTextField textField_26;
    private JTextField textField_27;
    private JTextField textField_28;
    private JTextField textField_29;
    private JTextField textField_30;
    private JTextField textField_31;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTable table=null;
    private Vector<Vector> data;
    private Vector<String> in;
    private Vector title;
    private String connetionUrl = "jdbc:mysql://localhost:3306/yuri?characterEncoding=UTF-8&serverTimezone=UTC"; private String user = "root";
    private String password = "193916";
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmtAdd = null;

    private PreparedStatement pstmtDel =null;
    private PreparedStatement pstmtUpdate = null;
    private DefaultTableModel model = null;
    //private JButton btnAdd= null;
    // private JButton btnDel = null;
    // private JButton btnUpdate= null;
    // private JButton btnClear= null;
    private JTextField textField_1= null;
    private JTextField textField_2= null;
    private JTextField textField_4= null;
    @SuppressWarnings("rawtypes")
    /**
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    bb frame = new bb();
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }


    public bb() {
        setTitle("\uAD00\uB9AC \uD504\uB85C\uADF8\uB7A8");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setBounds (100, 100, 1200, 1000);
        title = new Vector<>();
        data= new Vector<>();
        title.add("코드");
        title.add("거래처명");
        title.add("전화번호");
        getData();
        model = new DefaultTableModel();
        Vector result = selectAll();
        model.setDataVector (result, title);
        table = new JTable(model);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int index = table.getSelectedRow();
                Vector in= (Vector) data.get(index);
                String code = (String) in.get(0);
                String name = (String)in.get(1);
                String number = (String)in.get(2);
                textField_1.setText(code);
                textField_2.setText(name);
                textField_4.setText(number);
                textField_1.setEditable(false);}

        });

        textField_1 = new JTextField(8);
        textField_2 = new JTextField(10);
        textField_4 = new JTextField(20);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar (menuBar);
        JLabel lblNewLabel = new JLabel("\uAC70\uB798\uCC98\uAD00\uB9AC");
        menuBar.add(lblNewLabel);
        JComboBox comboBox_1 = new JComboBox();
        menuBar.add(comboBox_1);
        JComboBox comboBox_2 = new JComboBox();
        menuBar.add(comboBox_2);
        JComboBox comboBox_3 = new JComboBox();
        menuBar.add(comboBox_3);
        JButton btnNewButton_3 = new JButton("\uB2EB\uAE30");
        btnNewButton_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        menuBar.add(btnNewButton_3);
        contentPane = new JPanel();
        contentPane.setBorder (new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane (contentPane);
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.EAST);
        Box verticalBox_1 = Box.createVerticalBox();
        panel_1.add(verticalBox_1);
        JComboBox comboBox_8 = new JComboBox();
        String majors[]= {" ","등록"};
        JComboBox comboBox_81 = new JComboBox(majors);
        verticalBox_1.add(comboBox_81);
        JButton btnNewButton_4 = new JButton("\uc218\uC815");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                String code = textField_1.getText();
                String name = textField_2.getText();
                String number = textField_4.getText();
                update(name, number, code);
                Vector result = selectAll();
                model.setDataVector (result, title);
            } });
        verticalBox_1.add(btnNewButton_4);
        JButton btnNewButton_5 = new JButton("\uC218\uC815");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                String code= textField_1.getText();
                delete(code);
                Vector result = selectAll();
                model.setDataVector (result, title);
            } });
        verticalBox_1.add(btnNewButton_5);
        JButton btnNewButton_6 = new JButton("\uC800\uc7A5");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {}
        });

        verticalBox_1.add(btnNewButton_6);
        JButton btnNewButton_7 = new JButton("\uCDE8\uC18C");
        verticalBox_1.add(btnNewButton_7);
        JButton btnNewButton_8 = new JButton("\uCD08\uAE30\uD654");
        btnNewButton_8.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                textField_1.setText("");
                textField_2.setText("");
                textField_4.setText("");

                textField_1.setEditable(true);
                textField_2.setEditable(true);
                textField_4.setEditable(true);

                textField_1.requestFocus();
            }
        });

        verticalBox_1.add(btnNewButton_8);
        JButton btnNewButton_9 = new JButton("\uB4F1\uB85D");
        btnNewButton_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = textField_1.getText();
                String name = textField_2.getText();
                String number = textField_4.getText();
                insert(code, name, number);
                Vector result = selectAll();
                model.setDataVector(result, title);
            } });
        verticalBox_1.add(btnNewButton_9);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));
        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3, BorderLayout.NORTH);
        JPanel panel_6= new JPanel();
        panel_6.setBorder (new TitledBorder (null, "\uCC3E\uAE30", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.add(panel_6);
        textField = new JTextField();
        panel_6.add(textField);
        textField.setColumns(10);
        String majors1[]= {"  ","품목"};
        JComboBox comboBox = new JComboBox(majors1);
        panel_6.add(comboBox);
        JButton btnNewButton = new JButton("\uCC3E\uAE30");
        panel_6.add(btnNewButton);
        JButton btnNewButton_1 = new JButton("\uBAA8\uB450");
        panel_6.add(btnNewButton_1);
        JPanel panel_8 = new JPanel();
        panel_8.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder. LOWERED, new Color(255, 255, 255), new Color (160, 160, 160)), "\uBCF4\uAE30", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel_3.add(panel_8);
        JRadioButton rdbtnNewRadioButton = new JRadioButton("\uAC70\uB798\uC911");
        panel_8.add(rdbtnNewRadioButton);
        JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\uAC70\uB798\uC815\uB9AC");
        panel_8.add(rdbtnNewRadioButton_1);
        JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("\uBAA8\uB450");
        panel_8.add(rdbtnNewRadioButton_2);
        JPanel panel_9 = new JPanel();
        panel_9.setBorder (new TitledBorder (null, "\uAD00\uB9AC", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.add(panel_9);
        JButton btnNewButton_2 = new JButton("\uAC70\uB798/\uC815\uB9AC");
        panel_9.add(btnNewButton_2);
        JSplitPane splitPane = new JSplitPane();
        panel_2.add(splitPane, BorderLayout.CENTER);
        JPanel panel_4 = new JPanel();
        splitPane.setRightComponent (panel_4);
        panel_4.setLayout(new FlowLayout (FlowLayout.CENTER, 5, 5));

        Box verticalBox = Box.createVerticalBox();
        panel_4.add(verticalBox);

        JPanel panel_10= new JPanel();
        verticalBox.add(panel_10);
        GridBagLayout gbl_panel_10= new GridBagLayout();
        gbl_panel_10.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel_10.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_10.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_panel_10.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_10.setLayout(gbl_panel_10);

        JLabel lblNewLabel_2 = new JLabel("*\uAC70\uB798\uCC98\uCF54\uB4DC");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_2.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 0;
        panel_10.add(lblNewLabel_2,gbc_lblNewLabel_2);

        textField_1 = new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.insets = new Insets(0, 0, 5, 5);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 1;
        gbc_textField_1.gridy = 0;
        panel_10.add(textField_1, gbc_textField_1);
        textField_1.setColumns(10);

        JCheckBox chckbxNewCheckBox = new JCheckBox("\uCC44\uC6C0");
        GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
        gbc_chckbxNewCheckBox.insets = new Insets (0, 0, 5, 5);
        gbc_chckbxNewCheckBox.gridx = 2;
        gbc_chckbxNewCheckBox.gridy = 0;
        panel_10.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

        JLabel lblNewLabel_3 = new JLabel("*\uAC70\uB798\uCC98 \uBA85");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_3.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 1;
        panel_10.add(lblNewLabel_3, gbc_lblNewLabel_3);

        textField_2 = new JTextField();
        GridBagConstraints gbc_textField_2 = new GridBagConstraints();
        gbc_textField_2.insets = new Insets (0, 0, 5, 5);
        gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_2.gridx = 1;
        gbc_textField_2.gridy = 1;
        panel_10.add(textField_2, gbc_textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("*\uC804\uD654\uBC88\uD638");
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_5.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 0;
        gbc_lblNewLabel_5.gridy = 2;
        panel_10.add(lblNewLabel_5, gbc_lblNewLabel_5);

        textField_4 = new JTextField();
        GridBagConstraints gbc_textField_4 = new GridBagConstraints();
        gbc_textField_4.insets = new Insets(0, 0, 5, 5);
        gbc_textField_4.fill= GridBagConstraints.HORIZONTAL;
        gbc_textField_4.gridx = 1;
        gbc_textField_4.gridy = 2;
        panel_10.add(textField_4, gbc_textField_4);
        textField_4.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("\uB300\uD45C\uC790\uBA85");
        GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
        gbc_lblNewLabel_8.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_8.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_8.gridx = 2;
        gbc_lblNewLabel_8.gridy = 2;
        panel_10.add(lblNewLabel_8, gbc_lblNewLabel_8);

        textField_5 = new JTextField();
        GridBagConstraints gbc_textField_5 = new GridBagConstraints();
        gbc_textField_5.gridwidth = 2;
        gbc_textField_5.insets = new Insets (0, 0, 5, 0);
        gbc_textField_5.fill= GridBagConstraints.HORIZONTAL;
        gbc_textField_5.gridx = 3;
        gbc_textField_5.gridy = 2;
        panel_10.add(textField_5, gbc_textField_5);
        textField_5.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("\uc0C1\uD638");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4. anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_4.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy= 3;
        panel_10.add(lblNewLabel_4, gbc_lblNewLabel_4);

        textField_3 = new JTextField();
        GridBagConstraints gbc_textField_3 = new GridBagConstraints();
        gbc_textField_3.insets = new Insets(0, 0, 5, 5);
        gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_3.gridx = 1;
        gbc_textField_3.gridy = 3;
        panel_10.add(textField_3, gbc_textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel_9 = new JLabel("\uC885\uC0AC\uC5C5\uC7A5\uBC88\uD638");
        GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
        gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_9.gridx = 2;
        gbc_lblNewLabel_9.gridy = 3;
        panel_10.add(lblNewLabel_9, gbc_lblNewLabel_9);

        textField_6= new JTextField();
        GridBagConstraints gbc_textField_6 = new GridBagConstraints();
        gbc_textField_6.gridwidth = 2;
        gbc_textField_6.insets = new Insets(0, 0, 5, 0);
        gbc_textField_6.fill= GridBagConstraints.HORIZONTAL;
        gbc_textField_6.gridx = 3;
        gbc_textField_6.gridy = 3;
        panel_10.add(textField_6, gbc_textField_6);
        textField_6.setColumns(10);

        JLabel lblNewLabel_10= new JLabel("\uC0AC\uC5C5\uc7A5 \uC18C\uC7AC\uC9C0");
        GridBagConstraints gbc_lblNewLabel_10= new GridBagConstraints();
        gbc_lblNewLabel_10.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_10.gridx = 0;
        gbc_lblNewLabel_10.gridy = 4;
        panel_10.add(lblNewLabel_10, gbc_lblNewLabel_10);

        textField_9 = new JTextField();
        GridBagConstraints gbc_textField_9 = new GridBagConstraints();
        gbc_textField_9.insets = new Insets (0, 0, 5, 5);
        gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_9.gridx =1;
        gbc_textField_9.gridy=4;
        panel_10.add(textField_9, gbc_textField_9);
        textField_9.setColumns(10);

        textField_10 = new JTextField();
        GridBagConstraints gbc_textField_10 = new GridBagConstraints();
        gbc_textField_10.insets = new Insets (0, 0, 5, 5);
        gbc_textField_10.fill= GridBagConstraints.HORIZONTAL;
        gbc_textField_10.gridx =1;
        gbc_textField_10.gridy =5;
        panel_10.add(textField_10, gbc_textField_10);
        textField_10.setColumns(10);

        textField_13  = new JTextField();
        GridBagConstraints gbc_textField_13 = new GridBagConstraints();
        gbc_textField_13.insets = new Insets (0, 0, 5, 5);
        gbc_textField_13.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_13.gridx =2;
        gbc_textField_13.gridy=5;
        panel_10.add(textField_13, gbc_textField_13);
        textField_13.setColumns(10);

        textField_15 = new JTextField();
        GridBagConstraints gbc_textField_15 = new GridBagConstraints();
        gbc_textField_15.insets = new Insets (0, 0, 5, 5);
        gbc_textField_15.fill=GridBagConstraints.HORIZONTAL;
        gbc_textField_15.gridx = 3;
        gbc_textField_15.gridy=5;
        panel_10.add(textField_15, gbc_textField_15);
        textField_15.setColumns(10);

        textField_17 = new JTextField();
        GridBagConstraints gbc_textField_17 = new GridBagConstraints();
        gbc_textField_17.insets = new Insets(0, 0, 5, 0);
        gbc_textField_17.fill= GridBagConstraints. HORIZONTAL;
        gbc_textField_17.gridx = 4;
        gbc_textField_17.gridy = 5;
        panel_10.add(textField_17, gbc_textField_17);
        textField_17.setColumns(10);


        textField_11 = new JTextField();
        GridBagConstraints gbc_textField_11 = new GridBagConstraints();
        gbc_textField_11.insets = new Insets (0, 0, 5, 5);
        gbc_textField_11.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_11.gridx = 1;
        gbc_textField_11.gridy = 6;
        panel_10.add(textField_11, gbc_textField_11);
        textField_11.setColumns(10);

        textField_14 = new JTextField();
        GridBagConstraints gbc_textField_14 = new GridBagConstraints();
        gbc_textField_14.insets = new Insets (0, 0, 5, 5);
        gbc_textField_14.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_14.gridx = 2;
        gbc_textField_14.gridy = 6;
        panel_10.add(textField_14, gbc_textField_14);
        textField_14.setColumns(10);

        textField_16 = new JTextField();
        GridBagConstraints gbc_textField_16 = new GridBagConstraints();
        gbc_textField_16.insets = new Insets (0, 0, 5, 5);
        gbc_textField_16.fill= GridBagConstraints. HORIZONTAL;
        gbc_textField_16.gridx = 3;
        gbc_textField_16.gridy = 6;
        panel_10.add(textField_16, gbc_textField_16);
        textField_16.setColumns(10);

        textField_18 = new JTextField();
        GridBagConstraints gbc_textField_18 = new GridBagConstraints();
        gbc_textField_18.insets = new Insets (0, 0, 5, 0);
        gbc_textField_18.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_18.gridx = 4;
        gbc_textField_18.gridy = 6;
        panel_10.add(textField_18, gbc_textField_18);
        textField_18.setColumns(10);

        JLabel lblNewLabel_6 = new JLabel("\uc5C5\uD0DC");
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_6.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_6.gridx = 0;
        gbc_lblNewLabel_6.gridy = 7;
        panel_10.add(lblNewLabel_6, gbc_lblNewLabel_6);

        textField_12 = new JTextField();
        GridBagConstraints gbc_textField_12 = new GridBagConstraints(); gbc_textField_12.insets = new Insets (0, 0, 0, 5);
        gbc_textField_12.fill= GridBagConstraints.HORIZONTAL;
        gbc_textField_12.gridx = 1;
        gbc_textField_12.gridy = 7;
        panel_10.add(textField_12, gbc_textField_12);
        textField_12.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("\uC885\uBAA9");
        GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints(); gbc_lblNewLabel_7.insets = new Insets (0, 0, 0, 5); gbc_lblNewLabel_7.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_7.gridx = 2;
        gbc_lblNewLabel_7.gridy = 7;
        panel_10.add(lblNewLabel_7, gbc_lblNewLabel_7);

        textField_19 = new JTextField();
        GridBagConstraints gbc_textField_19 = new GridBagConstraints();
        gbc_textField_19.gridwidth = 2;
        gbc_textField_19.fill =GridBagConstraints.HORIZONTAL;
        gbc_textField_19.gridx = 3;
        gbc_textField_19.gridy = 7;
        panel_10.add(textField_19, gbc_textField_19);
        textField_19.setColumns(10);

        JPanel panel_11= new JPanel();
        verticalBox.add(panel_11);
        GridBagLayout gbl_panel_11 = new GridBagLayout();
        gbl_panel_11.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_panel_11.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel_11.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panel_11.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE}; panel_11.setLayout(gbl_panel_11);
        JLabel lblNewLabel_12 = new JLabel("\uB2F4\uB2F91");
        GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
        gbc_lblNewLabel_12.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_12.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_12.gridx = 0;
        gbc_lblNewLabel_12.gridy = 0;
        panel_11.add(lblNewLabel_12, gbc_lblNewLabel_12);

        textField_20 = new JTextField();
        GridBagConstraints gbc_textField_20 = new GridBagConstraints(); gbc_textField_20.insets = new Insets(0, 0, 5, 5);
        gbc_textField_20. fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_20.gridx = 1;
        gbc_textField_20.gridy = 0;
        panel_11.add(textField_20,gbc_textField_20);
        textField_20.setColumns(10);

        JLabel lblNewLabel_13 = new JLabel("\uD734\uB300\uD3F01");
        GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
        gbc_lblNewLabel_13.anchor =GridBagConstraints.EAST;
        gbc_lblNewLabel_13.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_13.gridx = 3;
        gbc_lblNewLabel_13.gridy = 0;
        panel_11.add(lblNewLabel_13, gbc_lblNewLabel_13);

        textField_21 = new JTextField();
        GridBagConstraints gbc_textField_21 = new GridBagConstraints();
        gbc_textField_21.gridwidth = 2;
        gbc_textField_21.insets = new Insets (0, 0, 5, 5);
        gbc_textField_21.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_21.gridx = 4;
        gbc_textField_21.gridy = 0;
        panel_11.add(textField_21, gbc_textField_21);
        textField_21.setColumns(10);

        JLabel lblNewLabel_14= new JLabel("e-mail");
        GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
        gbc_lblNewLabel_14.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_14.gridx = 0;
        gbc_lblNewLabel_14.gridy = 1;
        panel_11.add(lblNewLabel_14,gbc_lblNewLabel_14);

        textField_22 = new JTextField();
        GridBagConstraints gbc_textField_22 = new GridBagConstraints();
        gbc_textField_22.gridwidth = 3;
        gbc_textField_22.insets = new Insets (0, 0, 5, 5);
        gbc_textField_22. fill= GridBagConstraints. HORIZONTAL;
        gbc_textField_22.gridx = 1;
        gbc_textField_22.gridy = 1;
        panel_11.add(textField_22, gbc_textField_22);
        textField_22.setColumns(10);

        JLabel lblNewLabel_15 = new JLabel("\uB2F4\uB2F92");
        GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
        gbc_lblNewLabel_15.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_15.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_15.gridx = 0;
        gbc_lblNewLabel_15.gridy = 2;
        panel_11.add(lblNewLabel_15, gbc_lblNewLabel_15);

        textField_23 = new JTextField();
        GridBagConstraints gbc_textField_23 = new GridBagConstraints();
        gbc_textField_23.insets = new Insets (0, 0, 5, 5);
        gbc_textField_23. fill= GridBagConstraints.HORIZONTAL;
        gbc_textField_23.gridx = 1;
        gbc_textField_23.gridy = 2;
        panel_11.add(textField_23, gbc_textField_23);
        textField_23.setColumns(10);

        JLabel lblNewLabel_11 = new JLabel("\uD734\uB300\uD3F02");
        GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
        gbc_lblNewLabel_11.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_11.gridx = 3;
        gbc_lblNewLabel_11.gridy = 2;
        panel_11.add(lblNewLabel_11, gbc_lblNewLabel_11);

        textField_24 = new JTextField();
        GridBagConstraints gbc_textField_24 = new GridBagConstraints();
        gbc_textField_24.gridwidth = 2;
        gbc_textField_24.insets = new Insets (0, 0, 5, 5);
        gbc_textField_24.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_24.gridx = 4;
        gbc_textField_24.gridy = 2;
        panel_11.add(textField_24, gbc_textField_24);
        textField_24.setColumns(10);

        JLabel lblNewLabel_17 = new JLabel("e-mail2");
        GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
        gbc_lblNewLabel_17.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_17.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_17.gridx = 0;
        gbc_lblNewLabel_17.gridy = 3;
        panel_11.add(lblNewLabel_17, gbc_lblNewLabel_17);

        textField_25 = new JTextField();
        GridBagConstraints gbc_textField_25 = new GridBagConstraints();
        gbc_textField_25.gridwidth = 3;
        gbc_textField_25.insets = new Insets(0, 0, 5, 5);
        gbc_textField_25.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_25.gridx = 1;
        gbc_textField_25.gridy = 3;
        panel_11.add(textField_25, gbc_textField_25);
        textField_25.setColumns(10);

        JPanel panel_12= new JPanel();
        verticalBox.add(panel_12);
        GridBagLayout gbl_panel_12 = new GridBagLayout();
        gbl_panel_12.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel_12.rowHeights = new int[]{0, 0, 0};
        gbl_panel_12.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panel_12.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panel_12.setLayout(gbl_panel_12);

        JLabel lblNewLabel_16 = new JLabel("\uC804\uD654\uBC88\uD638");
        GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
        gbc_lblNewLabel_16.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_16.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_16.gridx = 0; gbc_lblNewLabel_16.gridy = 0;
        panel_12.add(lblNewLabel_16, gbc_lblNewLabel_16);

        textField_26 = new JTextField();
        GridBagConstraints gbc_textField_26 = new GridBagConstraints();
        gbc_textField_26.insets = new Insets(0, 0, 5, 5);
        gbc_textField_26.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_26.gridx = 1;
        gbc_textField_26.gridy = 0;
        panel_12.add(textField_26, gbc_textField_26);
        textField_26.setColumns(10);

        JLabel lblNewLabel_19 = new JLabel("\uC804\uD654\uBC88\uD6382");
        GridBagConstraints gbc_lblNewLabel_19 = new GridBagConstraints();
        gbc_lblNewLabel_19. anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_19.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_19.gridx =3;
        gbc_lblNewLabel_19.gridy = 0;
        panel_12.add(lblNewLabel_19, gbc_lblNewLabel_19);

        textField_28 = new JTextField();
        GridBagConstraints gbc_textField_28 = new GridBagConstraints();
        gbc_textField_28.insets = new Insets(0, 0, 5, 0);
        gbc_textField_28.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_28.gridx = 4;
        gbc_textField_28.gridy = 0;
        panel_12.add(textField_28, gbc_textField_28);
        textField_28.setColumns(10);

        JLabel lblNewLabel_18= new JLabel("Fax");
        GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints();
        gbc_lblNewLabel_18.anchor =GridBagConstraints. EAST;
        gbc_lblNewLabel_18.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_18.gridx = 0;
        gbc_lblNewLabel_18.gridy = 1;
        panel_12.add(lblNewLabel_18, gbc_lblNewLabel_18);

        textField_27 = new JTextField();
        GridBagConstraints gbc_textField_27 = new GridBagConstraints();
        gbc_textField_27.insets = new Insets(0, 0, 0, 5);
        gbc_textField_27.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_27.gridx = 1;
        gbc_textField_27.gridy = 1;
        panel_12.add(textField_27, gbc_textField_27);
        textField_27.setColumns(10);

        JLabel lblNewLabel_20= new JLabel("\uAC70\uB798\uC2DC\uC791\uc77C");
        GridBagConstraints gbc_lblNewLabel_20= new GridBagConstraints();
        gbc_lblNewLabel_20.anchor =GridBagConstraints.EAST;
        gbc_lblNewLabel_20.insets = new Insets (0, 0, 0, 5);
        gbc_lblNewLabel_20.gridx = 3;
        gbc_lblNewLabel_20.gridy = 1;
        panel_12.add(lblNewLabel_20, gbc_lblNewLabel_20);

        JComboBox comboBox_4= new JComboBox();
        GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
        gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_4.gridx = 4;
        gbc_comboBox_4.gridy = 1;
        panel_12.add(comboBox_4, gbc_comboBox_4);

        JPanel panel_13 = new JPanel();
        verticalBox.add(panel_13);
        GridBagLayout gbl_panel_13 = new GridBagLayout();
        gbl_panel_13.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel_13.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_panel_13.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_13.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_13.setLayout(gbl_panel_13);

        JLabel lblNewLabel_22 = new JLabel("\uAD00\ub9AC/\uB2F4\uB2F9\uC0AC\uC6D0");
        lblNewLabel_22.setForeground (Color.BLUE);
        GridBagConstraints gbc_lblNewLabel_22 = new GridBagConstraints();
        gbc_lblNewLabel_22. anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_22.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_22.gridx = 0;
        gbc_lblNewLabel_22.gridy = 0;
        panel_13.add(lblNewLabel_22, gbc_lblNewLabel_22);

        textField_29 = new JTextField();
        GridBagConstraints gbc_textField_29 = new GridBagConstraints();
        gbc_textField_29.insets = new Insets(0, 0, 5, 5);
        gbc_textField_29.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_29.gridy = 0;
        gbc_textField_29.gridx = 1;
        panel_13.add(textField_29, gbc_textField_29);
        textField_29.setColumns(10);


        JLabel lblNewLabel_23 = new JLabel("\uC8FC\uACB0\uC81C\uC7A5\uBD80");
        lblNewLabel_23.setForeground (Color.BLUE);
        GridBagConstraints gbc_lblNewLabel_23 = new GridBagConstraints();
        gbc_lblNewLabel_23.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_23.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_23.gridx = 0;
        gbc_lblNewLabel_23.gridy = 1;
        panel_13.add(lblNewLabel_23, gbc_lblNewLabel_23);

        textField_30 = new JTextField();
        GridBagConstraints gbc_textField_30= new GridBagConstraints(); gbc_textField_30.insets = new Insets(0, 0, 5, 5);
        gbc_textField_30.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_30.gridx = 1;
        gbc_textField_30.gridy = 1;
        panel_13.add(textField_30, gbc_textField_30);
        textField_30.setColumns(10);

        JLabel lblNewLabel_24 = new JLabel("\uAC70\uB798\uCC98 \uAD6C\uBD84");
        GridBagConstraints gbc_lblNewLabel_24 = new GridBagConstraints();
        gbc_lblNewLabel_24.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_24.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_24.gridx = 0;
        gbc_lblNewLabel_24.gridy = 2;
        panel_13.add(lblNewLabel_24, gbc_lblNewLabel_24);

        JComboBox comboBox_5 = new JComboBox();
        GridBagConstraints gbc_comboBox_5 = new GridBagConstraints();
        gbc_comboBox_5.insets = new Insets (0, 0, 5, 5);
        gbc_comboBox_5.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_5.gridx = 1;
        gbc_comboBox_5.gridy = 2;
        panel_13.add(comboBox_5, gbc_comboBox_5);

        JLabel lblNewLabel_27 = new JLabel("\uCD9C\uACE0\uB4F1\uAE09");
        GridBagConstraints gbc_lblNewLabel_27 = new GridBagConstraints();
        gbc_lblNewLabel_27.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_27.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_27.gridx = 3;
        gbc_lblNewLabel_27.gridy = 2;
        panel_13.add(lblNewLabel_27, gbc_lblNewLabel_27);

        JComboBox comboBox_7 = new JComboBox();
        GridBagConstraints gbc_comboBox_7 = new GridBagConstraints();
        gbc_comboBox_7.insets = new Insets (0, 0, 5, 0);
        gbc_comboBox_7.fill =GridBagConstraints.HORIZONTAL;
        gbc_comboBox_7.gridx =4;
        gbc_comboBox_7.gridy = 2;


        JLabel lblNewLabel_25 = new JLabel("\uACFC\uC138\uAD6C\uBD84"); GridBagConstraints gbc_lblNewLabel_25 = new GridBagConstraints();
        gbc_lblNewLabel_25.anchor =GridBagConstraints.EAST;
        gbc_lblNewLabel_25.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_25.gridx = 0;
        gbc_lblNewLabel_25.gridy = 3;
        panel_13.add(lblNewLabel_25, gbc_lblNewLabel_25);

        JComboBox comboBox_6 = new JComboBox();
        GridBagConstraints gbc_comboBox_6 = new GridBagConstraints();
        gbc_comboBox_6.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_6.fill =GridBagConstraints.HORIZONTAL;
        gbc_comboBox_6.gridx = 1;
        gbc_comboBox_6.gridy = 3;
        panel_13.add(comboBox_6, gbc_comboBox_6);

        JLabel lblNewLabel_26 = new JLabel("\uAE30\uCD08\uc794\uC561");
        GridBagConstraints gbc_lblNewLabel_26 = new GridBagConstraints();
        gbc_lblNewLabel_26.anchor= GridBagConstraints.EAST; gbc_lblNewLabel_26.insets = new Insets (0, 0, 5, 5);
        gbc_lblNewLabel_26.gridx = 0;
        gbc_lblNewLabel_26.gridy =4;
        panel_13.add(lblNewLabel_26, gbc_lblNewLabel_26);

        textField_31 = new JTextField();
        GridBagConstraints gbc_textField_31 = new GridBagConstraints();
        gbc_textField_31.insets = new Insets(0, 0, 5, 5);
        gbc_textField_31.fill= GridBagConstraints.HORIZONTAL;
        gbc_textField_31.gridx = 1;
        gbc_textField_31.gridy = 4;
        panel_13.add(textField_31, gbc_textField_31);
        textField_31.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\uC801\uC815\uC794\uC561");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor= GridBagConstraints.EAST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 3;
        gbc_lblNewLabel_1.gridy = 4;
        panel_13.add(lblNewLabel_1, gbc_lblNewLabel_1);

        textField_7 = new JTextField();
        GridBagConstraints gbc_textField_7 = new GridBagConstraints();
        gbc_textField_7.insets = new Insets(0, 0, 5, 0);
        gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_7.gridx = 4;
        gbc_textField_7.gridy = 4;
        panel_13.add(textField_7, gbc_textField_7);
        textField_7.setColumns(10);


        JLabel lblNewLabel_21 = new JLabel("\uBA54\uBAA8");
        GridBagConstraints gbc_lblNewLabel_21 = new GridBagConstraints();
        gbc_lblNewLabel_21.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_21.insets = new Insets (0, 0, 0, 5);
        gbc_lblNewLabel_21.gridx = 0;
        gbc_lblNewLabel_21.gridy = 6;
        panel_13.add(lblNewLabel_21, gbc_lblNewLabel_21);

        textField_8 = new JTextField();
        GridBagConstraints gbc_textField_8 = new GridBagConstraints(); gbc_textField_8.gridwidth = 4;
        gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_8.gridx = 1;
        gbc_textField_8.gridy = 6;
        panel_13.add(textField_8, gbc_textField_8);
        textField_8.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        splitPane.setLeftComponent(scrollPane);
        scrollPane.setViewportView(table);
        Panel panel_5 = new Panel();
        contentPane.add(panel_5, BorderLayout.SOUTH);
    }
    private Vector selectAll() {
        data.clear();
        try{
            ResultSet rs = stmt.executeQuery("select * from supervision order by code ");
            while(rs.next()){

                Vector in = new Vector<String>();
                String code= rs.getString(1);
                String name = rs.getString(2);
                String number= rs.getString(3);
                in.add(code);
                in.add(name);
                in.add(number);

                data.add(in);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    private void insert(String code, String name, String number){
        try{
            pstmtAdd = conn.prepareStatement("insert into supervision values (?,?,?)");
            pstmtAdd.setString(1, code);
            pstmtAdd.setString(2, name);
            pstmtAdd.setString(3, number);
            pstmtAdd.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void delete(String code){
        try{
            pstmtDel = conn.prepareStatement("delete from supervision where code = ?");
            pstmtDel.setString(1, code);
            pstmtDel.executeUpdate();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void update(String name, String number, String code){
        try{
            pstmtUpdate = conn.prepareStatement("update supervision set name = ?, number = ? where code = ?");
            pstmtUpdate.setString(1, name);
            pstmtUpdate.setString(2, number);
            pstmtUpdate.setString(3, code);
            pstmtUpdate.executeUpdate();

        }catch(Exception e) { e.printStackTrace();
        }
    }
    private void getData() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        try{
            conn = DriverManager.getConnection(connetionUrl, user, password);
            stmt = conn.createStatement();
            String sql = "select * from supervision";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                in = new Vector<>();
                in.add(rs.getString(1));
                in.add(rs.getString(2));
                in.add(rs.getString(3));
                data.add(in);
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();}
    }
}