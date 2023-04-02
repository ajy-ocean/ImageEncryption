//Use font image to show demo of image encryption process

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageOperation {

    public static void operate(int key) {
        JFileChooser jfilechooser = new JFileChooser();
        jfilechooser.showOpenDialog(null);
        File file = jfilechooser.getSelectedFile();

        // Reading data from file using FileInputStream
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            int i = 0;
            for (byte byteData : data) {
                System.out.println(byteData);
                data[i] = (byte) (byteData ^ key);
                i++;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
            fileInputStream.close();
            fileOutputStream.close();
            JOptionPane.showMessageDialog(null, "Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Project is running");

        JFrame jframe = new JFrame();
        jframe.setTitle("Image Operation");
        jframe.setSize(300, 200);
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Font
        Font font = new Font("Roboto", Font.BOLD, 20);

        // Creating textField
        JTextField jtextfield = new JTextField(10);
        jtextfield.setFont(font);

        // Creating button
        JButton jbutton = new JButton();
        jbutton.setText("Open Image");
        jbutton.setFont(font);
        jbutton.addActionListener(e -> {
            System.out.println("Button Clicked");
            String text = jtextfield.getText();
            int temp = Integer.parseInt(text);
            operate(temp);
        });

        jframe.setLayout(new FlowLayout());
        jframe.add(jbutton);
        jframe.add(jtextfield);
        jframe.setVisible(true);

    }
}