import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Marwen on 12/8/16.
 */
public class Gui extends JFrame {

    private JLabel picLabel = new JLabel();

    Automate a1;
    JLabel reste;
    JLabel etat;
    JLabel finish;
    public Gui() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 400);
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.setBackground(Color.WHITE);
        jp.add(picLabel, BorderLayout.WEST);

        JPanel controles = new JPanel();
        controles.setLayout(new BoxLayout(controles, BoxLayout.Y_AXIS));

        JTextField tf = new JTextField();
        tf.setMaximumSize(new Dimension(200, 30));
        controles.add(tf);

        JButton bt = new JButton("Demarrer");
        JButton bt2 = new JButton("Suivant");

        bt.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //a1 = new Automate1(tf.getText());
                a1 = new Automate1(tf.getText());
                drawAutomate(a1.generateAutomateImg());
                reste.setText("RESTE :" + a1.getReste());
                etat.setText("Etat : " + a1.getEtape());
                finish.setText("");

            }
        });

        bt2.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(a1.pasFin()){
                    a1.suivant();
                    drawAutomate(a1.generateAutomateImg());
                    reste.setText("RESTE :" + a1.getReste());
                    etat.setText("Etat : " + a1.getEtape());
                }else{
                    if(a1.getReste().charAt(0) != 'a' && a1.getReste().charAt(0) != 'b'){
                        finish.setText("Alphabet " + a1.getReste().charAt(0) + " non reconnu, Mot non accpetee");
                    }else{
                        if(a1.accept())
                            finish.setText("Mot acceptee");
                        else

                            finish.setText("Mot non acceptee");
                    }

                }
            }
        });
        controles.add(bt);
        controles.add(bt2);
        etat  = new JLabel("Etat : 0");
        etat.setForeground(Color.RED);

        reste  = new JLabel("RESTE :");
        reste.setForeground(Color.RED);

        finish  = new JLabel("");
        finish.setForeground(Color.RED);

        controles.add(etat);
        controles.add(reste);
        controles.add(finish);


        jp.add(controles, BorderLayout.EAST);

        this.setContentPane(jp);
        this.setVisible(true);

        bt.setText("Demarrer");
        bt2.setText("Suivant");

    }

    public void drawAutomate(byte[] image) {
        try {
            BufferedImage bufferedImage = null;
            InputStream inputStream = new ByteArrayInputStream(image);
            bufferedImage = ImageIO.read(inputStream);
            picLabel.setIcon(new ImageIcon(bufferedImage));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Gui();
    }
}
