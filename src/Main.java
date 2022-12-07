import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.ref.Cleaner;
import java.sql.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main extends JFrame {

    public void paint(Graphics g){
        super.paint(g);
    }

    public static void main(String[] args) {
        new Main();
    }

    static Map<Integer, JTextField> listArray = new HashMap<>();
    static int[] listNumbers = new int[13];

    static JTextField scoreText = new JTextField("score: 0");
    static JTextField textNumber;

    static int nextNumber = 0;

    public static <X, A> X getKeyHashMap(Map<X, A> map, A value) {
        for (Map.Entry<X, A> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Main(){
        super("Задание 9");
        MouseAdapter mouseAdapter = new MouseAdapter();

        int x = 0;
        int y = 0;
        nextNumber = (int)(Math.random() * 25);
        textNumber = new JTextField("number: " + nextNumber);

        for (int i = 1; i < 26; i++){
            if((i >= 5 + (5 * y)) && (i % 5 != 0)) {x = 0; y++;}
            listArray.put(i-1, new JTextField("0"));
            listArray.get(i-1).setBounds
                    (43 + (50 * x),
                            21 + (50 * y),
                            50,50);
            add(listArray.get(i-1));
            listArray.get(i-1).setHorizontalAlignment(JTextField.CENTER);
            listArray.get(i-1).setBackground(null);
            listArray.get(i-1).addMouseListener(mouseAdapter);
            x++;
        }

        scoreText.setBounds(350,350,100,25);
        add(scoreText);

        textNumber.setBounds(200,400,100,25);
        add(textNumber);

        for(int i = 0; i < 13; i++){
            listNumbers[i] = 4;
        }

        setLayout(null);
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    static class MouseAdapter implements MouseListener {
        int emptyBox = 13;
        int score = 0;

        public int getNumber(){
            while(true) {
                int key = (int) (Math.random() * 12 + 1);
                if (listNumbers[key] != 0) {
                    listNumbers[key] = listNumbers[key] - 1;
                    return key;
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            JTextField playerTextField = (JTextField) mouseEvent.getSource();
            playerTextField.setText(String.valueOf(nextNumber));
            playerTextField.setEnabled(false);
            emptyBox = emptyBox - 1;
            nextNumber = getNumber();
            textNumber.setText("number: " + String.valueOf(nextNumber));

            if (emptyBox != 1) {
                while (true) {
                    int key = (int) (Math.random() * 25);
                    if (listArray.get(key).getText().equals("0")) {
                        listArray.get(key).setText(String.valueOf(getNumber()));
                        listArray.get(key).setEnabled(false);
                        break;
                    }
                }
            }

            if(emptyBox == 0){
                for(int i = 0; i < 5; i++){
                    int sc = 0;
                    int selectNumber = 0;
                    selectNumber = Integer.parseInt(listArray.get(i * 5).getText());
                    for(int j = 0; j <5; j++){
                        if(selectNumber == Integer.parseInt(listArray.get(j + (i*5)).getText())){
                            sc = sc + 10;
                        }
                    }
                    score = score + sc;
                    scoreText.setText("score: " + score);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }
}
