/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import amoba.AmobaGUI;
import static java.lang.Integer.max;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Greg
 */
public class AmobaModel {

    private int boradSize;
    public Boolean turn = false;
    public Boolean thegameended = false;
    public Boolean chill;
    public String winner;
    String[][] gameBoardMemory;

    public Boolean needsDelete = false;
    public int needDeletex;
    public int needDeletey;
    
    public Boolean needsDelete2 = false;
    public int needDeletex2;
    public int needDeletey2;

    public AmobaModel() {
    }
    
    public void reset() {
    boradSize=0;
    turn = false;
    thegameended = false;
    chill=false;
    winner="";
    needsDelete = false;
    needsDelete2 = false;
    }

    public void dontetlen() {
        chill = false;
        for (int i = 0; i < boradSize; i++) {
            for (int j = 0; j < boradSize; j++) {
                if (gameBoardMemory[i][j] == "I") {
                    chill = true;
                }
            }
        }
        if (chill == false) {
            thegameended = true;
            winner = "Tie.";
        }
    }

    public void checkStreakOnPlayerMove(int x, int y, String shape) {
        if (HorizontalStreakCounter(x, y, shape) >= 5 || VerticalStreakCounter(x, y, shape) >= 5 || DiagonalStreakCounter1(x, y, shape) >= 5 || DiagonalStreakCounter2(x, y, shape) >= 5) {
            if (turn) {
                thegameended = true;
                winner = "Player2 won.";
                System.out.println("Player2 WON");
            } else {
                thegameended = true;
                winner = "Player1 won.";
                System.out.println("Player1 WON");
            }
        }
        if (HorizontalStreakCounter(x, y, shape) == 3 || VerticalStreakCounter(x, y, shape) == 3 || DiagonalStreakCounter1(x, y, shape) == 3 || DiagonalStreakCounter2(x, y, shape) == 3) {
            deleteShape(shape);
        }
        if (HorizontalStreakCounter(x, y, shape) == 4 || VerticalStreakCounter(x, y, shape) == 4 || DiagonalStreakCounter1(x, y, shape) == 4 || DiagonalStreakCounter2(x, y, shape) == 4) {
            deleteShape(shape);
            delete2Shape(shape);
        }
    }

    public int deleteShape(String shape) {
        int shapecounter = 0;
        for (int i = 0; i < boradSize; i++) {
            for (int j = 0; j < boradSize; j++) {
                if (gameBoardMemory[i][j] == shape) {
                    shapecounter++;
                }
            }
        }
        int randomShape = ThreadLocalRandom.current().nextInt(0, shapecounter);
        shapecounter = 0;
        for (int i = 0; i < boradSize; i++) {
            for (int j = 0; j < boradSize; j++) {
                if (gameBoardMemory[i][j] == shape) {
                    if (shapecounter == randomShape) {
                        gameBoardMemory[i][j] = "I";
                        needDeletex = i;
                        needDeletey = j;
                        System.out.println("randoShape:" + randomShape + " shapecounter:" + shapecounter + " i:" + needDeletex + " j:" + needDeletey);
                        needsDelete = true;
                    }
                    shapecounter++;
                }
            }
        }
        return 0;
    }
        public int delete2Shape(String shape) {
        //delete 1 shape
        int shapecounter = 0;
        for (int i = 0; i < boradSize; i++) {
            for (int j = 0; j < boradSize; j++) {
                if (gameBoardMemory[i][j] == shape) {
                    shapecounter++;
                }
            }
        }
        int randomShape = ThreadLocalRandom.current().nextInt(0, shapecounter);
        shapecounter = 0;
        for (int i = 0; i < boradSize; i++) {
            for (int j = 0; j < boradSize; j++) {
                if (gameBoardMemory[i][j] == shape) {
                    if (shapecounter == randomShape) {
                        gameBoardMemory[i][j] = "I";
                        needDeletex = i;
                        needDeletey = j;
                        needsDelete = true;
                    }
                    shapecounter++;
                }
            }
        }
        shapecounter = 0;
        for (int i = 0; i < boradSize; i++) {
            for (int j = 0; j < boradSize; j++) {
                if (gameBoardMemory[i][j] == shape) {
                    shapecounter++;
                }
            }
        }
        randomShape = ThreadLocalRandom.current().nextInt(0, shapecounter);
        shapecounter = 0;
        for (int i = 0; i < boradSize; i++) {
            for (int j = 0; j < boradSize; j++) {
                if (gameBoardMemory[i][j] == shape) {
                    if (shapecounter == randomShape) {
                        gameBoardMemory[i][j] = "I";
                        needDeletex2 = i;
                        needDeletey2 = j;
                        needsDelete2 = true;
                    }
                    shapecounter++;
                }
            }
        }
        return 0;
    }

    public int HorizontalStreakCounter(int x, int y, String shape) {
        int originalx = x;
        int originaly = y;
        int horizontalStreakCounter = 0;
        if (x < boradSize - 1) {
            while (gameBoardMemory[x + 1][y] == shape) {
                horizontalStreakCounter++;
                if (x == boradSize - 2) {
                    break;
                }
                x++;
            }
        }
        x = originalx;
        y = originaly;
        if (x != 0) {
            while (gameBoardMemory[x - 1][y] == shape) {
                horizontalStreakCounter++;
                if (x == 1) {
                    break;
                }
                x--;
            }
        }
        horizontalStreakCounter++;
        return horizontalStreakCounter++;
    }

    public int VerticalStreakCounter(int x, int y, String shape) {
        int originalx = x;
        int originaly = y;
        int verticalStreakCounter = 0;
        if (y < boradSize - 1) {
            while (gameBoardMemory[x][y + 1] == shape) {
                verticalStreakCounter++;
                if (y == boradSize - 2) {
                    break;
                }
                y++;
            }
        }
        x = originalx;
        y = originaly;
        if (y != 0) {
            while (gameBoardMemory[x][y - 1] == shape) {
                verticalStreakCounter++;
                if (y == 1) {
                    break;
                }
                y--;
            }
        }
        verticalStreakCounter++;
        return verticalStreakCounter++;
    }

    public int DiagonalStreakCounter1(int x, int y, String shape) {
        int originalx = x;
        int originaly = y;
        int diagonalStreakCounter1 = 0;
        if (y < boradSize - 1 && x < boradSize - 1) {
            while (gameBoardMemory[x + 1][y + 1] == shape) {
                diagonalStreakCounter1++;
                if (y == boradSize - 2 || x == boradSize - 2) {
                    break;
                }
                x++;
                y++;
            }
        }
        x = originalx;
        y = originaly;
        if (y != 0 && x != 0) {
            while (gameBoardMemory[x - 1][y - 1] == shape) {
                diagonalStreakCounter1++;
                if (y == 1 || x == 1) {
                    break;
                }
                x--;
                y--;
            }
        }
        diagonalStreakCounter1++;
        return diagonalStreakCounter1++;
    }

    public int DiagonalStreakCounter2(int x, int y, String shape) {
        int originalx = x;
        int originaly = y;
        int diagonalStreakCounter2 = 0;
        if (x < boradSize - 1 && y != 0) {
            while (gameBoardMemory[x + 1][y - 1] == shape) {
                diagonalStreakCounter2++;
                if (x == boradSize - 2 || y == 1) {
                    break;
                }
                x++;
                y--;
            }
        }
        x = originalx;
        y = originaly;
        if (x != 0 && y < boradSize - 1) {
            while (gameBoardMemory[x - 1][y + 1] == shape) {
                diagonalStreakCounter2++;
                if (x == 1 || y == boradSize - 2) {
                    break;
                }
                x--;
                y++;
            }
        }
        diagonalStreakCounter2++;
        return diagonalStreakCounter2++;
    }

    public int getBoradSize() {
        return boradSize;
    }

    public void setBoradSize(int boradSize) {
        this.boradSize = boradSize;
    }

    public Boolean getTurn() {
        return turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

    public String[][] getGameBoardMemory() {
        return gameBoardMemory;
    }

    public void setGameBoardMemory(String[][] gameBoardMemory) {
        this.gameBoardMemory = gameBoardMemory;
    }

    public void setGameBoardMemory2(int x, int y, String shape) {
        gameBoardMemory[x][y] = shape;
    }

}
