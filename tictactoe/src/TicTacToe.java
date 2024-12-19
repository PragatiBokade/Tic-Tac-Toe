
import java.awt.*;
import java.awt.event.*;
import java.util.prefs.BackingStoreException;

import javax.swing.*;

public class TicTacToe {
    int boardWidth=600;
    int boardHeight=650;

    JFrame frame= new JFrame("Tic-Tac-Toe");
    JLabel textlabel= new JLabel();
    JPanel textpanel= new JPanel();
    JPanel boardPanel= new JPanel();
    JButton[][] board= new JButton[3][3];
    String playerx="X";
    String player0="0";
    String currentplayer=playerx;

    boolean gameOver= false;
    int turn=0;

TicTacToe() { 
    frame.setVisible(true); //decide a frame should be visible or not
    frame.setSize(boardHeight,boardWidth); //set the size of frame
    frame.setLocationRelativeTo(null); //take frame into center
    frame.setResizable(false); //can not resize
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close the frame
    frame.setLayout(new BorderLayout()); //Arrange your stuff using the BorderLayout style(north,east,west,center,south)

    textlabel.setBackground(Color.black);
    textlabel.setForeground(Color.white);
    textlabel.setFont(new Font("Arial",Font.BOLD,50));
    textlabel.setHorizontalAlignment(JLabel.CENTER);
    textlabel.setText("TicTacToe");
    textlabel.setOpaque(true);//background color will be drawn, and the label will remain transparent otherwise it will be transparent

    textpanel.setLayout(new BorderLayout());
    textpanel.add(textlabel);
    frame.add(textpanel, BorderLayout.NORTH);

    boardPanel.setLayout(new GridLayout(3,3));
    boardPanel.setBackground(Color.DARK_GRAY);
    frame.add(boardPanel);

    for(int r=0;r<3;r++){
        for(int c=0;c<3;c++){
            JButton tile= new JButton();
            board[r][c]=tile;
            boardPanel.add(tile);

            tile.setBackground(Color.black);
            tile.setForeground(Color.white);
            tile.setFont(new Font("Arial",Font.BOLD,120));
            tile.setFocusable(false);//respond to mouse clicks but cannot be focused or activated using the keyboard
            //tile.setText(currentplayer);

            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(gameOver) return;
                    JButton tile = (JButton) e.getSource();
                    if(tile.getText()==""){
                        tile.setText(currentplayer);
                        turn++;
                         checkWinner();
                         if(!gameOver){
                            currentplayer = currentplayer == playerx? player0:playerx;
                            textlabel.setText(currentplayer + "'s turn.");
                         }
                    }
                }
            });
        }
    }
}
void checkWinner(){
    //horizontal
    for(int r=0;r<3;r++){
        if(board[r][0].getText()=="") continue;

        if(board[r][0].getText()==board[r][1].getText() &&
           board[r][1].getText()==board[r][2].getText() ){
            for(int i=0;i<3;i++){
                setWinner(board[r][i]);
            }
            gameOver=true;
            return;
           }
    }
//vertical
for(int c=0;c<3;c++){
    if(board[0][c].getText()=="") continue;

    if(board[0][c].getText()==board[1][c].getText() &&
       board[1][c].getText()==board[2][c].getText()){
        for(int i=0;i<3;i++){
            setWinner(board[i][c]);
        }
        gameOver=true;
        return;
       }
}
   //digonally
   if(board[0][0].getText()==board[1][1].getText() &&
   board[1][1].getText()==board[2][2].getText() && 
   board[0][0].getText()!=""){
    for(int i=0;i<3;i++){
        setWinner(board[i][i]);
    }
   }

   //anti diagonnal
   if(board[0][2].getText()==board[1][1].getText() &&
   board[1][1].getText()==board[2][0].getText() && 
   board[0][2].getText()!=""){
        setWinner(board[0][2]);
        setWinner(board[1][1]);
        setWinner(board[2][0]);
        gameOver=true;
        return;
    }
    if(turn==9){
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                setTie(board[r][c]);
            }
        }
        gameOver=true;
    }
   }
void setWinner(JButton tile){
    tile.setBackground(Color.green);
    tile.setForeground(Color.black);
    textlabel.setText(currentplayer + " is a winner");
}
 void setTie(JButton tile){
    tile.setBackground(Color.red);
    tile.setForeground(Color.black);
    textlabel.setText("Tie!");
 }
}