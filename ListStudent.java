
// import java.io.BufferedInputStream;
// import java.io.DataInputStream;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.io.OutputStreamWriter;
// import java.io.RandomAccessFile;
// import java.io.File;
// import javax.swing.SpringLayout;
// import javax.swing.GroupLayout.Alignment;
// import javax.swing.border.Border;
// import javax.swing.border.EmptyBorder;
// import javax.swing.table.DefaultTableModel;
// import javax.swing.JScrollBar;
// import javax.swing.JList;
// import java.awt.FlowLayout;
// import java.awt.GridLayout;
// import java.awt.TextArea;
// import java.awt.Container;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.*;
import java.io.FileReader;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.*;

class student {
    private String id;
    private String name;
    private String mark;
    private String avatar; // save path to student's avatar
    private String address;
    private String note;

    public student() {
        id = "001";
        name = address = note = mark = "aaa";
        avatar = System.getProperty("user.dir") + "\\media\\default-user.png";
    }

    public student(String ms, String ten, String ava, String diachi, String ghichu, String diem) {
        id = ms;
        name = ten;
        avatar = ava;
        address = diachi;
        note = ghichu;
        mark = diem;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNote() {
        return note;
    }

    public String getMark() {
        return mark;
    }

    public String getAddress() {
        return address;
    }

    public void setId(String value) {
        id = value;
    }

    public void setName(String value) {
        name = value;
    }

    public void setAvatar(String value) {
        avatar = value;
    }

    public void setAddress(String value) {
        address = value;
    }

    public void setNote(String value) {
        note = value;
    }

    public void setMark(String value) {
        mark = value;
    }
};

public class ListStudent extends JFrame {
    private List<student> list;

    public void AddStudent(student a) {
        list.add(a);
    }

    public String UpdateStudent(String id, String criteria, String newValue) {
        // find student

        int i;
        for (i = 0; i < list.size(); ++i) {
            if (list.get(i).getId().equals(id))
                break;
        }
        if (i < list.size()) {
            if (criteria.equals("ID"))
                list.get(i).setId(newValue);
            else if (criteria.equals("Name"))
                list.get(i).setName(newValue);
            else if (criteria.equals("Address"))
                list.get(i).setAddress(newValue);
            else if (criteria.equals("Note"))
                list.get(i).setNote(newValue);
            else if (criteria.equals("Avatar"))
                list.get(i).setAvatar(newValue);
            else if (criteria.equals("Mark"))
                list.get(i).setMark(newValue);
            else
                return ("Tiêu chí cập nhật không phù hợp, vui lòng kiểm tra lại");
        } else {
            return ("Không tim thấy học sinh cần cập nhật thông tin!!!");
        }
        return ("Cập nhật thành công");
    }

    public void DeleteStudent(String id) {
        int i = 0;
        for (i = 0; i < list.size(); ++i)
            if (list.get(i).getId().equals(id))
                break;
        if (i < list.size()) {
            list.remove(i);
        } else {
            System.err.println("Không tìm thấy ID của sinh viên cần xóa");
        }
    }

    public void PrintAStudent(int index) {
        if (index < list.size()) {
            System.out.println("ID sinh vien:  " + list.get(index).getId());
            System.out.println("Ho ten sinh vien:  " + list.get(index).getName());
            System.out.println("Dia chi sinh vien:  " + list.get(index).getAddress());
            System.out.println("Avatar sinh vien:  " + list.get(index).getAvatar());
            System.out.println("Ghi chu sinh vien:  " + list.get(index).getNote());
            System.out.println("Diem sinh vien:  " + list.get(index).getMark());

        } else {
            System.err.println("chi so khong phu hop(invalid index)!!!");
        }
    }

    public void ViewList() {
        for (int i = 0; i < list.size(); ++i)
            PrintAStudent(i);
    }

    public String WriteToFile(String dir) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(dir));
            for (int i = 0; i < list.size(); ++i) {
                bw.write(list.get(i).getId() + "\n");
                bw.write(list.get(i).getName() + "\n");
                bw.write(list.get(i).getAvatar() + "\n");
                bw.write(list.get(i).getAddress() + "\n");
                bw.write(list.get(i).getNote() + "\n");
                bw.write(list.get(i).getMark() + "\n");
            }
            bw.close();
            return ("Export Success!!!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ("Export Failed!!!");

        }
    }

    public boolean idIsExist(String id) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getId().equals(id))
                return true;
        }
        return false;
    }

    public String ReadFromFile(String dir) {
        BufferedReader br;
        String root = System.getProperty("user.dir");
        try {
            br = new BufferedReader(new FileReader(dir));
            // đọc dữ liệu
            String line = br.readLine();
            while (line != null) {
                String id = line;
                String name = br.readLine();
                // String avatar = root + "\\media\\" + br.readLine();
                String avatar = br.readLine();
                String addr = br.readLine();
                String note = br.readLine();
                String mark = br.readLine();
                student a = new student(id, name, avatar, addr, note, mark);
                list.add(a);
                line = br.readLine();
            }
            return ("Import Success!!!");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ("Import Failed!!");
        }
    }

    public ListStudent() {
        setTitle("Manager Student");
        list = new ArrayList<>();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setBackground(Color.cyan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                list.clear();
                list = null;
            }
        });

        String dir = System.getProperty("user.dir");

        // Object
        JButton btnAdd = new JButton("Add Student");
        btnAdd.setFont(new Font("Arial", Font.BOLD, 25));
        btnAdd.setBackground(Color.WHITE);
        btnAdd.setFocusable(false);
        JButton btnView = new JButton("View List Student");
        btnView.setFont(new Font("Arial", Font.BOLD, 25));
        btnView.setBackground(Color.WHITE);
        btnView.setFocusable(false);
        JButton btnImport = new JButton("Import");
        btnImport.setFont(new Font("Arial", Font.BOLD, 25));
        btnImport.setBackground(Color.WHITE);
        btnImport.setFocusable(false);
        JButton btnExport = new JButton("Export");
        btnExport.setFont(new Font("Arial", Font.BOLD, 25));
        btnExport.setBackground(Color.WHITE);
        btnExport.setFocusable(false);
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 25));
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setFocusable(false);
        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 25));
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setFocusable(false);
        JLabel header = new JLabel("Add Student Function");

        // style
        Dimension btnSize = new Dimension(300, 50);
        Dimension sidebarSize = new Dimension(340, 700);
        Dimension DialogBoxSize = new Dimension(600, 300);
        Dimension fullScreen = new Dimension(1000, 1000);
        btnAdd.setPreferredSize(btnSize);
        btnAdd.setMinimumSize(btnSize);
        btnAdd.setMaximumSize(btnSize);
        btnView.setPreferredSize(btnSize);
        btnView.setMaximumSize(btnSize);
        btnView.setMinimumSize(btnSize);
        btnExport.setPreferredSize(btnSize);
        btnExport.setMinimumSize(btnSize);
        btnExport.setMaximumSize(btnSize);
        btnImport.setPreferredSize(btnSize);
        btnImport.setMinimumSize(btnSize);
        btnImport.setMaximumSize(btnSize);
        btnUpdate.setPreferredSize(btnSize);
        btnUpdate.setMinimumSize(btnSize);
        btnUpdate.setMaximumSize(btnSize);
        btnDelete.setPreferredSize(btnSize);
        btnDelete.setMinimumSize(btnSize);
        btnDelete.setMaximumSize(btnSize);
        header.setFont(new Font("Arial", Font.PLAIN, 25));
        header.setAlignmentX(CENTER_ALIGNMENT);

        JPanel mainPanel = new JPanel();
        BoxLayout box = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        // set up sidebar
        JPanel sidebar = new JPanel();
        BoxLayout boxSidebar = new BoxLayout(sidebar, BoxLayout.Y_AXIS);
        sidebar.setPreferredSize(sidebarSize);
        sidebar.setMaximumSize(sidebarSize);
        sidebar.setMinimumSize(sidebarSize);
        sidebar.setBackground(new Color(32, 178, 170));

        sidebar.setLayout(boxSidebar);
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // tạo khoảng cách cho đối tượng trên cùng
        sidebar.add(btnAdd);
        sidebar.add(Box.createVerticalStrut(20)); // tạo khoảng cách giữa các đối tượng(cố định?)
        sidebar.add(btnView);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnUpdate);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnDelete);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnImport);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnExport);
        sidebar.add(Box.createVerticalStrut(20));

        // set up content
        // sử dụng cardlayout để hiển thị màn hình tương ấn khi chọn các nút chức năng
        JPanel overal = new JPanel();
        BoxLayout boxoveral = new BoxLayout(overal, BoxLayout.Y_AXIS);
        overal.setLayout(boxoveral);
        overal.setBackground(new Color(150, 205, 205));

        JPanel content = new JPanel();

        CardLayout card = new CardLayout();
        content.setLayout(card);
        content.setBackground(Color.BLACK);

        // màn hình thêm 1 học sinh
        addStudentScreen addScreen = new addStudentScreen();
        addScreen.setPreferredSize(DialogBoxSize);
        addScreen.setMinimumSize(DialogBoxSize);
        addScreen.setMaximumSize(DialogBoxSize);
        addScreen.setVisible(true);
        addScreen.setBackground(new Color(150, 205, 205));
        content.add("addscreen", addScreen);

        // màn hình update thông tin học sinh
        UpdateStudentScreen updateScreen = new UpdateStudentScreen();
        updateScreen.setVisible(true);
        updateScreen.setBackground(new Color(150, 205, 205));
        content.add("updatescreen", updateScreen);
        // màn hình hiển thị toàn bộ danh sách học sinh
        viewStudentScreen viewScreen = new viewStudentScreen();
        viewScreen.setBackground(new Color(150, 205, 205));
        viewScreen.setVisible(true);
        content.add("viewscreen", viewScreen);
        // màn hình import
        ImportStudentScreen importScreen = new ImportStudentScreen();
        importScreen.setBackground(new Color(150, 205, 205));
        importScreen.setVisible(true);
        content.add("importscreen", importScreen);
        // màn hình export
        ExportStudentScreen exportScreen = new ExportStudentScreen();
        exportScreen.setBackground(new Color(150, 205, 205));
        exportScreen.setVisible(true);
        content.add("exportscreen", exportScreen);
        // màn hình delete
        DeleteStudentScreen deleteScreen = new DeleteStudentScreen();
        deleteScreen.setBackground(new Color(150, 205, 205));
        deleteScreen.setVisible(true);
        content.add("deletescreen", deleteScreen);
        // xử lý giao diện khi nhấn nút add
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.setText("Add Student Function");
                card.show(content, "addscreen");
            }
        });
        // xử lý giao diện khi nhấn nút update
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.setText("Update Student Function");
                card.show(content, "updatescreen");
            }
        });
        // xử lý giao diện khi nhấn nút import
        JFileChooser chooseFile = new JFileChooser();
        btnImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.setText("Import Student Function");
                int returnVal = chooseFile.showOpenDialog(content);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = chooseFile.getSelectedFile();
                    importScreen.updateInImport(file.getAbsolutePath());

                } else {
                    System.out.println("open command cancelled by user ");

                }
                card.show(content, "importscreen");
            }
        });
        // xử lý giao diện khi nhấn nút export
        btnExport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.setText("Export Student Function");
                exportScreen.updateInExport();
                card.show(content, "exportscreen");
                JOptionPane.showMessageDialog(exportScreen,
                        "Vừa Export danh sách sinh viên ra file export.csv");
            }
        });
        // xử lý giao diện khi nhấn nút view
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.setText("View List Student");
                viewScreen.updateInView();
                card.show(content, "viewscreen");

            }
        });
        // xử lý giao diện khi nhấn nút delete
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                header.setText("Delete Student Function");
                deleteScreen.updateInDelete();
                card.show(content, "deletescreen");
            }
        });

        overal.add(header);
        overal.add(content);
        // xử lý mainpanel
        mainPanel.setLayout(box);
        mainPanel.add(sidebar);
        mainPanel.add(overal);
        add(mainPanel);
        pack();
    }

    // done
    class addStudentScreen extends JPanel {
        public String avataPath;

        public addStudentScreen() {
            Dimension dialog = new Dimension(750, 300);
            Dimension btnDimension = new Dimension(60, 20);
            JPanel temp = new JPanel();
            temp.setPreferredSize(dialog);
            temp.setMinimumSize(dialog);
            temp.setMaximumSize(dialog);
            JLabel labId = new JLabel("Id:          ");
            labId.setFont(new Font("Arial", Font.PLAIN, 20));
            JLabel labName = new JLabel("Name:          ");
            labName.setFont(new Font("Arial", Font.PLAIN, 20));
            JLabel labMark = new JLabel("Mark:          ");
            labMark.setFont(new Font("Arial", Font.PLAIN, 20));
            JLabel labAddr = new JLabel("Address:           ");
            labAddr.setFont(new Font("Arial", Font.PLAIN, 20));
            JLabel labNote = new JLabel("Note:          ");
            labNote.setFont(new Font("Arial", Font.PLAIN, 20));
            JLabel labAvatar = new JLabel("Avatar:          ");
            labAvatar.setFont(new Font("Arial", Font.PLAIN, 20));
            JTextField fieldId = new JTextField(40);
            JTextField fieldName = new JTextField(40);
            JButton fieldAva = new JButton("Choose avatar");
            JFileChooser chooseFile = new JFileChooser();
            avataPath = "";

            JTextField fieldAddr = new JTextField(40);
            JTextField fieldNote = new JTextField(40);
            JTextField fieldMark = new JTextField(40);
            JButton btnOk = new JButton("Add");
            btnOk.setFont(new Font("Arial", Font.PLAIN, 15));
            JButton btnCancel = new JButton("Cancel");
            btnCancel.setFont(new Font("Arial", Font.PLAIN, 15));

            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints constraint = new GridBagConstraints();
            constraint.insets = new Insets(0, 20, 0, 0);
            temp.setLayout(layout);
            temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            temp.setBackground(new Color(32, 178, 170));
            constraint.gridx = 0;
            constraint.gridy = 0;
            constraint.fill = GridBagConstraints.BOTH;
            constraint.insets = new Insets(5, 0, 0, 5);

            temp.add(labId, constraint);
            constraint.gridy = 1;
            temp.add(labName, constraint);
            constraint.gridy = 2;
            temp.add(labAddr, constraint);
            constraint.gridy = 3;
            temp.add(labMark, constraint);
            constraint.gridy = 4;
            temp.add(labAvatar, constraint);
            constraint.gridy = 5;
            temp.add(labNote, constraint);
            constraint.gridy = 6;
            temp.add(btnCancel, constraint);
            constraint.fill = GridBagConstraints.BOTH;
            constraint.gridx = 1;
            constraint.gridy = 0;
            temp.add(fieldId, constraint);
            constraint.gridy = 1;
            temp.add(fieldName, constraint);
            constraint.gridy = 2;
            temp.add(fieldAddr, constraint);
            constraint.gridy = 3;
            temp.add(fieldMark, constraint);
            constraint.gridy = 4;
            temp.add(fieldAva, constraint);
            constraint.gridy = 5;
            temp.add(fieldNote, constraint);
            constraint.gridy = 6;
            constraint.gridwidth = 1;
            temp.add(btnOk, constraint);
            btnOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String id = fieldId.getText().toString();
                    String name = fieldName.getText().toString();
                    String addr = fieldAddr.getText().toString();
                    String note = fieldNote.getText().toString();
                    String avatar = avataPath;
                    String mark = fieldMark.getText();
                    fieldId.setText("");
                    fieldAddr.setText("");
                    fieldAva.setText("");
                    fieldMark.setText("");
                    fieldNote.setText("");
                    fieldName.setText("");
                    student a = new student(id, name, avatar, addr, note, mark);
                    AddStudent(a);
                    JOptionPane.showMessageDialog(addStudentScreen.this,
                            "Add Success!!!");
                }
            });
            btnCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fieldAddr.setText("");
                    fieldAva.setText("");
                    fieldId.setText("");
                    fieldId.setText("");
                    fieldMark.setText("");
                    fieldNote.setText("");
                }
            });
            fieldAva.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int returnVal = chooseFile.showOpenDialog(temp);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        java.io.File file = chooseFile.getSelectedFile();
                        avataPath = file.getAbsolutePath();
                        fieldAva.setText(avataPath);
                    } else {
                        System.out.println("open command cancelled by user");
                    }
                }
            });
            add(temp);

        }
    }

    // done

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = { "Avatar", "ID", "Name", "Address", "Mark", "Note" };
        private Object[][] data;

        public MyTableModel(Object[][] data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0)
                return ImageIcon.class;
            else
                return String.class;
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return true;
        }

        public void setData(Object[][] data) {
            this.data = data;
        }
    }

    // xử lý hình ảnh avatar
    class viewStudentScreen extends JPanel {
        JTable jTable;
        MyTableModel myTableModel;
        Object[][] data;

        public viewStudentScreen() {
            data = new Object[list.size()][6];
            for (int i = 0; i < list.size(); ++i) {
                // data[i][0] = new ImageIcon(getClass().get)
                if (list.get(i).getAvatar().lastIndexOf("\\") == -1)
                    data[i][0] = new ImageIcon(getClass().getResource("/media/" + list.get(i).getAvatar()));
                else
                    data[i][0] = new ImageIcon(list.get(i).getAvatar());
                data[i][1] = list.get(i).getId().toString();
                data[i][2] = list.get(i).getName().toString();
                data[i][3] = list.get(i).getAddress().toString();
                data[i][4] = list.get(i).getMark().toString();
                data[i][5] = list.get(i).getNote().toString();
            }
            myTableModel = new MyTableModel(data);
            jTable = new JTable(myTableModel);
            jTable.setBackground(new Color(32, 178, 170));
            jTable.setRowHeight(50);
            jTable.setAutoCreateRowSorter(true);
            jTable.setPreferredScrollableViewportSize(new Dimension(700, 600));
            jTable.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable.getColumnModel().getColumn(3).setPreferredWidth(170);
            jTable.getColumnModel().getColumn(5).setPreferredWidth(250);

            jTable.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected,
                        boolean hasFocus, int row, int column) {
                    if (value instanceof ImageIcon) {
                        JLabel label = new JLabel();
                        label.setIcon((ImageIcon) value);
                        return label;
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected,
                            hasFocus, row, column);
                }
            });
            JScrollPane pane = new JScrollPane(jTable);
            add(pane, BorderLayout.CENTER);
        }

        public void updateInView() {
            Object[][] data = new Object[list.size()][6];
            for (int i = 0; i < list.size(); ++i) {
                // data[i][0] = new ImageIcon(list.get(i).getAvatar());
                if (list.get(i).getAvatar().lastIndexOf("\\") == -1)
                    data[i][0] = new ImageIcon(getClass().getResource("/media/" + list.get(i).getAvatar()));
                else
                    data[i][0] = new ImageIcon(list.get(i).getAvatar());
                data[i][1] = list.get(i).getId().toString();
                data[i][2] = list.get(i).getName().toString();
                data[i][3] = list.get(i).getAddress().toString();
                data[i][4] = list.get(i).getMark().toString();
                data[i][5] = list.get(i).getNote().toString();
            }
            myTableModel.setData(data);
            myTableModel.fireTableDataChanged();

        }
    }

    // done
    class UpdateStudentScreen extends JPanel {

        public UpdateStudentScreen() {

            Dimension dialog = new Dimension(750, 300);
            JPanel temp = new JPanel();
            temp.setPreferredSize(dialog);
            temp.setMinimumSize(dialog);
            temp.setMaximumSize(dialog);

            // OBJECT
            JLabel labId = new JLabel("ID: ");
            labId.setFont(new Font("Arial", Font.PLAIN, 20));
            JLabel labCri = new JLabel("Criteria:");
            labCri.setFont(new Font("Arial", Font.PLAIN, 20));
            JLabel labVal = new JLabel("New value:");
            labVal.setFont(new Font("Arial", Font.PLAIN, 20));
            JTextField textid = new JTextField(40);
            JTextField textVal = new JTextField(40);
            String options[] = { "ID", "Name", "Address", "Note", "Mark", "Avatar" };
            JButton btnOk = new JButton("Update");
            btnOk.setFont(new Font("Arial", Font.PLAIN, 15));
            JButton btnCancel = new JButton("Cancel");
            btnCancel.setFont(new Font("Arial", Font.PLAIN, 15));
            JComboBox combo = new JComboBox<>(options);

            JButton choose = new JButton("Your choose");
            choose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String selectAction = (String) combo.getSelectedItem();
                }
            });

            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints constraint = new GridBagConstraints();
            constraint.insets = new Insets(0, 20, 0, 0);
            temp.setLayout(layout);
            temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            temp.setBackground(new Color(32, 178, 170));
            constraint.gridx = 0;
            constraint.gridy = 0;
            constraint.fill = GridBagConstraints.BOTH;
            constraint.insets = new Insets(5, 0, 0, 5);
            temp.add(labId, constraint);
            constraint.gridy = 1;
            temp.add(labCri, constraint);
            constraint.gridy = 2;
            temp.add(labVal, constraint);
            constraint.gridy = 3;
            temp.add(btnCancel, constraint);
            constraint.fill = GridBagConstraints.BOTH;
            constraint.gridx = 1;
            constraint.gridy = 0;
            temp.add(textid, constraint);
            constraint.gridy = 1;
            temp.add(combo, constraint);
            constraint.gridy = 2;
            temp.add(textVal, constraint);
            constraint.gridy = 3;
            temp.add(btnOk, constraint);

            btnOk.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    String id = textid.getText();
                    String value = textVal.getText();
                    String criteria = combo.getSelectedItem().toString();
                    String result = UpdateStudent(id, criteria, value);
                    textVal.setText("");
                    textid.setText("");
                    combo.setSelectedItem(1);
                    JOptionPane.showMessageDialog(UpdateStudentScreen.this,
                            result);

                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }
            });
            btnCancel.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    textVal.setText("");
                    textid.setText("");
                    combo.setSelectedItem(1);
                    JOptionPane.showMessageDialog(UpdateStudentScreen.this,
                            "Cancel Update Action!!!");

                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }
            });
            add(temp);
        }

    }

    // done
    class ImportStudentScreen extends JPanel {
        public ImportStudentScreen() {

        }

        public void updateInImport(String pathfile) {
            String result = ReadFromFile(pathfile);
            JOptionPane.showMessageDialog(ImportStudentScreen.this,
                    result);

        }
    }

    // done
    class ExportStudentScreen extends JPanel {
        public ExportStudentScreen() {
        }

        public void updateInExport() {
            String dir = System.getProperty("user.dir");
            // tạo file export.csv tại thư mục hiện hành
            try {
                FileWriter file = new FileWriter("export.csv");
                WriteToFile(dir + "export.csv");
                file.close();
            } catch (Exception e) {

            }
        }
    }

    class DeleteStudentScreen extends JPanel {
        JLabel labChoose;
        JButton btnOK, btnCancel, choose;
        DefaultComboBoxModel<String> options;
        // String options[];
        JComboBox combo;

        public DeleteStudentScreen() {
            Dimension dimension = new Dimension(350, 200);
            JPanel temp = new JPanel();
            temp.setPreferredSize(dimension);
            temp.setMinimumSize(dimension);
            temp.setMaximumSize(dimension);
            labChoose = new JLabel("Choose ID: ");
            labChoose.setFont(new Font("Arial", Font.PLAIN, 20));
            btnOK = new JButton("CONFIRM");
            btnOK.setFont(new Font("Arial", Font.PLAIN, 15));
            btnCancel = new JButton("CANCEL");
            btnCancel.setFont(new Font("Arial", Font.PLAIN, 15));
            options = new DefaultComboBoxModel<>();
            for (int i = 0; i < list.size(); ++i) {
                options.addElement(list.get(i).getId());
            }
            combo = new JComboBox<>(options);
            choose = new JButton("Your choose");

            choose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String selectAction = (String) combo.getSelectedItem();
                }
            });

            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints constraint = new GridBagConstraints();
            constraint.insets = new Insets(0, 20, 0, 0);
            temp.setLayout(layout);
            temp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            temp.setBorder(BorderFactory.createLineBorder(Color.black));
            temp.setBackground(new Color(32, 178, 170));
            constraint.gridx = 0;
            constraint.gridy = 0;
            constraint.fill = GridBagConstraints.BOTH;
            // constraint.insets = new Insets(20, 0, 0, 0);
            temp.add(labChoose, constraint);
            constraint.insets = new Insets(10, 0, 0, 10);
            constraint.gridy = 1;
            temp.add(btnCancel, constraint);
            constraint.fill = GridBagConstraints.BOTH;
            constraint.gridx = 1;
            constraint.gridy = 0;
            temp.add(combo, constraint);
            constraint.gridy = 1;
            temp.add(btnOK, constraint);

            // xử lý sự kiện thao tác xóa/hủy
            btnOK.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    String id = combo.getSelectedItem().toString();
                    DeleteStudent(id);
                    combo.removeItem(id);
                    combo.setSelectedItem(null);
                    JOptionPane.showMessageDialog(DeleteStudentScreen.this,
                            "Delete success!!!");

                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }
            });
            btnCancel.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    combo.setSelectedItem(null);
                    JOptionPane.showMessageDialog(DeleteStudentScreen.this,
                            "Cancel Delete Action!!!");

                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }
            });
            add(temp);
            validate();
            repaint();
        }

        public void updateInDelete() {
            options.removeAllElements();
            options = new DefaultComboBoxModel<>();
            for (int i = 0; i < list.size(); ++i) {
                options.addElement(list.get(i).getId());
            }
            combo.setModel(options);
            validate();
            repaint();

        }
    }

    public static void main(String[] argc) {
        ListStudent l = new ListStudent();
        l.setVisible(true);
    }
};
