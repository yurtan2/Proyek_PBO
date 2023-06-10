package com.mycompany.pacman_escape;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Model extends JPanel implements ActionListener{

    private Dimension d;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private boolean inGame = false;
    private boolean dying = false;
    String imagePathGhost = "src/images/ghost.png";
    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 20;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int MAX_GHOSTS = 12;
    private final int PACMAN_SPEED = 6;

    private int N_GHOSTS = 6;
    private int lives, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image heart, ghost;
    private Image up, down, left, right;

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;
    
    //bagian pollymorph nya disini
    Map map1=new Map1();
    Map map2=new Map2();
    int[] position = map1.getfinishpos1();
    int finish1x=position[0];
    int finish1y=position[1];
    int finish2x=position[2];
    int finish2y=position[3];
    private final short levelData[] = map1.getMapMaze();
    //sampai sini bagian pollymorph nya 
    
    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;

    private int currentSpeed = 3;
    private short[] screenData;
    private Timer timer;
    String fileName = "src/highscore.txt";

    public Model() {

        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
    }
    
    private void loadData(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null)                 // read the score file line by line
            {
                try {
                    
                } catch (NumberFormatException e1) {
                    
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
    }

    private void saveData() {
        String skor = Integer.toString(score);
        try {
            boolean fileIsEmpty = isFileEmpty(fileName);
            FileWriter fileWriter = new FileWriter(fileName, true); // Mode penulisan APPEND

            if (!fileIsEmpty) {
                // Jika file tidak kosong, tambahkan baris kosong sebelum menulis data baru
                fileWriter.write("\n");
            }

            fileWriter.write(skor); // Tidak perlu tambahkan karakter baru setelah setiap skor
            fileWriter.close();

            System.out.println("Data tersimpan!");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan karena: " + e.getMessage());
        }
    }

    private boolean isFileEmpty(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String firstLine = reader.readLine();
            reader.close();

            return (firstLine == null);
        } catch (IOException e) {
        System.out.println("Terjadi kesalahan karena: " + e.getMessage());
        }
        return true; // Anggap file kosong jika terjadi kesalahan saat membaca file
    }
    private void loadImages() {
        down = new ImageIcon("src/images/down.gif").getImage();
        up = new ImageIcon("src/images/up.gif").getImage();
        left = new ImageIcon("src/images/left.gif").getImage();
        right = new ImageIcon("src/images/right.gif").getImage();
        heart = new ImageIcon("src/images/heart.png").getImage();


    }
       private void initVariables() {

        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension(1280, 720);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];
        
        timer = new Timer(40, this);
        timer.start();
    }

    private void playGame(Graphics2D g2d) {

        if (dying) {
            
            death();

        } else {

            movePacman();
            drawPacman(g2d);
            moveGhosts(g2d);
            checkMaze();
        }
    }

private void showIntroScreen(Graphics2D g2d) {
    String start = "Press SPACE to start";
    g2d.setColor(Color.yellow);

    // Mengukur lebar dan tinggi teks
    FontMetrics fm = g2d.getFontMetrics();
    int textWidth = fm.stringWidth(start);
    int textHeight = fm.getHeight();

    // Menghitung posisi kotak berdasarkan teks
    int boxX = (SCREEN_SIZE - textWidth) / 2 - 15;  //padding
    int boxY = 150 - textHeight + fm.getAscent() - 25;  //padding
    int boxWidth = textWidth + 30;  // padding
    int boxHeight = textHeight + 30;  // padding

    // Menggambar kotak dengan latar belakang kuning
    g2d.setColor(Color.yellow);
    g2d.fillRect(boxX, boxY, boxWidth, boxHeight);

    // Menggambar kotak tepi hitam
    g2d.setColor(Color.black);
    g2d.drawRect(boxX, boxY, boxWidth, boxHeight);

    // Menggambar teks di dalam kotak
    g2d.setColor(Color.black);
    g2d.drawString(start, (SCREEN_SIZE - textWidth) / 2, 150);
}

    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    private void checkMaze() {

        int i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i]) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {

            score += 50;

            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initLevel();
        }
    }

    private void death() {

    	lives--;

        if (lives == 0) {
            saveData();
            inGame = false;
        }

        continueLevel();
    }

    private void moveGhosts(Graphics2D g2d) {
        
        int pos;
        int count;

        for (int i = 0; i < N_GHOSTS; i++) {
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;

                // List untuk menyimpan kemungkinan arah yang dapat diambil oleh hantu
                var possibleDirections = new ArrayList<Integer>();


                // Cek apakah hantu bisa bergerak ke arah kiri
                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
                    possibleDirections.add(-1);
                }

                // Cek apakah hantu bisa bergerak ke arah atas
                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
                    possibleDirections.add(-2);
                }

                // Cek apakah hantu bisa bergerak ke arah kanan
                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
                    possibleDirections.add(1);
                }

                // Cek apakah hantu bisa bergerak ke arah bawah
                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
                    possibleDirections.add(2);
                }

                // Jika tidak ada kemungkinan arah yang dapat diambil, maka hantu harus mencari jalan lain atau berbalik arah
                if (possibleDirections.isEmpty()) {
                    // Berbalik arah
                    ghost_dx[i] *= -1;
                    ghost_dy[i] *= -1;
                }


                // Jika terdapat kemungkinan arah yang dapat diambil
                else if (!possibleDirections.isEmpty()) {
                    int pacmanTileX = pacman_x / BLOCK_SIZE;
                    int pacmanTileY = pacman_y / BLOCK_SIZE;

                    // Menghitung jarak hantu dengan posisi Pacman
                    int distanceX = Math.abs(pacmanTileX - ghost_x[i] / BLOCK_SIZE);
                    int distanceY = Math.abs(pacmanTileY - ghost_y[i] / BLOCK_SIZE);

                    // Menghitung titiktemu untuk setiap arah yang mungkin
                    int[] Titiktemu = new int[possibleDirections.size()];
                    for (int j = 0; j < possibleDirections.size(); j++) {
                        int direction = possibleDirections.get(j);

                        int nextTileX = (ghost_x[i] + (direction % 2) * BLOCK_SIZE) / BLOCK_SIZE;
                        int nextTileY = (ghost_y[i] + (direction / 2) * BLOCK_SIZE) / BLOCK_SIZE;

                        int distanceToPacman = Math.abs(nextTileX - pacmanTileX) + Math.abs(nextTileY - pacmanTileY);
                        Titiktemu[j] = distanceToPacman;
                    }

                    // Mengambil arah 
                    int minIndex = 0;
                    for (int j = 1; j < Titiktemu.length; j++) {
                        if (Titiktemu[j] < Titiktemu[minIndex]) {
                            minIndex = j;
                        }
                    }

                    int selectedDirection = possibleDirections.get(minIndex);
                    ghost_dx[i] = selectedDirection % 2; // dx = 0 untuk vertikal, dx = 1 untuk horizontal
                    ghost_dy[i] = selectedDirection / 2; // dy = 0 untuk horizontal, dy = 1 untuk vertikal
                }

            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            drawGhost(g2d, imagePathGhost, ghost_x[i] + 1, ghost_y[i] + 1);

            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
                && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
                && inGame) {

                dying = true;
            }
        }
    }

    private void drawGhost(Graphics2D g2d, String imagePath, int x, int y) {
    try {
        Image ghost = ImageIO.read(new File(imagePath));
        g2d.drawImage(ghost, x, y, this);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private void movePacman() {

        int pos;
        short ch;
        
        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];

            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                score++;
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }

            // Check for standstill
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
        if(pacman_x==finish1x&&pacman_y==finish1y||pacman_x==finish2x&&pacman_y==finish2y){
            inGame=false;
        }
    }
    private void drawPacman(Graphics2D g2d) {

        if (req_dx == -1) {
        	g2d.drawImage(left, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dx == 1) {
        	g2d.drawImage(right, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dy == -1) {
        	g2d.drawImage(up, pacman_x + 1, pacman_y + 1, this);
        } else {
        	g2d.drawImage(down, pacman_x + 1, pacman_y + 1, this);
        }
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(5));
                if ((levelData[i] == 0)) { 
                	g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                 }

                if ((screenData[i] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) { 
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) { 
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) { 
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
               }

                i++;
            }
        }
    }
    private void initGame() {

    	lives = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 6;
        currentSpeed = 3;
    }

    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }

        continueLevel();
    }

private void continueLevel() {
    int dx = 1;
    int random;

    for (int i = 0; i < N_GHOSTS; i++) {
        ghost_y[i] = 4 * BLOCK_SIZE; // start position
        ghost_x[i] = 4 * BLOCK_SIZE;
        ghost_dy[i] = 0;
        ghost_dx[i] = dx;
        dx = -dx;

        int randomX;
        int randomY;

        boolean validPosition;

        do {
            validPosition = true;
            randomX = (int) (Math.random() * (N_BLOCKS - 2) + 1) * BLOCK_SIZE;
            randomY = (int) (Math.random() * (N_BLOCKS - 2) + 1) * BLOCK_SIZE;
            if ((randomX % BLOCK_SIZE != 0) || (randomY % BLOCK_SIZE != 0)) {
                validPosition = false;
            }

            int pos = randomX / BLOCK_SIZE + N_BLOCKS * (randomY / BLOCK_SIZE);

            if ((screenData[pos] & 1) != 0) {
                validPosition = false;
            }

            for (int j = 0; j < i; j++) {
                if (ghost_x[j] == randomX && ghost_y[j] == randomY) {
                    validPosition = false;
                    break;
                }
            }
    
            // Memeriksa jarak antara posisi respawn pacman dan posisi respawn hantu
            int pacmanRespawnX = 0 * BLOCK_SIZE;
            int pacmanRespawnY = 0 * BLOCK_SIZE;
            int distance = Math.abs(randomX - pacmanRespawnX) + Math.abs(randomY - pacmanRespawnY);
    
            if (distance < 4 * BLOCK_SIZE) {
                validPosition = false;
            }
    
            // Cek apakah posisi spawn hantu berada di dalam tembok
            int spawnPos = randomX / BLOCK_SIZE + N_BLOCKS * (randomY / BLOCK_SIZE);
            if (screenData[spawnPos] == 0) {
                validPosition = false;
            }

        } while (!validPosition);


        ghost_x[i] = randomX;
        ghost_y[i] = randomY;

        random = (int) (Math.random() * (currentSpeed + 1));

        if (random > currentSpeed) {
            random = currentSpeed;
        }

        ghostSpeed[i] = validSpeeds[random];
    }

    pacman_x = 0 * BLOCK_SIZE;  // start position
    pacman_y = 0 * BLOCK_SIZE;
    pacmand_x = 0;  // reset direction move
    pacmand_y = 0;
    req_dx = 0;     // reset direction controls
    req_dy = 0;
    dying = false;
}


 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

    // Mengatur translasi agar maze berada di tengah-tengah layar
        int offsetX = (d.width - SCREEN_SIZE) / 2;
        int offsetY = (d.height - SCREEN_SIZE) / 2;
        g2d.translate(offsetX, offsetY);

    // Gambar persegi hitam di sisi kiri dan kanan layar
        g2d.setColor(Color.BLACK);
        g2d.fillRect(-BLOCK_SIZE, 0, BLOCK_SIZE, d.height);
        g2d.fillRect(d.width - BLOCK_SIZE/2, 0, BLOCK_SIZE, d.height);

        drawMaze(g2d);
        drawScore(g2d);

        if (inGame) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }



    //controls
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                    req_dx = 0;
                    req_dy = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                } 
            } else {
                if (key == KeyEvent.VK_SPACE) {
                    inGame = true;
                    initGame();
                }
            }
        }
}

	
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
	
}
