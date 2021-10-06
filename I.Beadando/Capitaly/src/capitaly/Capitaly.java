/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Greg
 */
public class Capitaly {

    /**
     * the first one is for normal usage
     * the second one is for testing
     */
    //private final ArrayList<Player> players;
    public final ArrayList<Player> players;
    /**
     * the first one is for normal usage
     * the second one is for testing
     */
    //private final ArrayList<BoardPieces> board;
    public final ArrayList<BoardPieces> board; // csak a teszteleshez
    private final ArrayList<Integer> cubeThrows;
    int x=0;
    public int deathcounter = 0;
    private String secondLost;

    public Capitaly() {
        players = new ArrayList<>();
        board = new ArrayList<>();
        cubeThrows = new ArrayList<>();
    }

    /**\
     * read the board, the players and the cube throws from file
     * you can use the cubeThrow method to crate random sollutions every time
     * @param filename, the name of the file you want to read
     * @throws FileNotFoundException
     * @throws InvalidInputException 
     */
    public void read(String filename) throws FileNotFoundException, InvalidInputException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int boardLength = sc.nextInt();
        int i = 0;
        int price;
        while (i < boardLength) {
            BoardPieces boardpiece;
            int tmp = 0;
            switch (sc.next()) {
                case "L":
                    price = Integer.parseInt(sc.next());
                    boardpiece = new Luck(price);
                    i++;
                    break;
                case "S":
                    price = Integer.parseInt(sc.next());
                    boardpiece = new Service(price);
                    i++;
                    break;
                case "P":
                    boardpiece = new Property();
                    i++;
                    break;
                default:
                    throw new InvalidInputException();
            }
            board.add(boardpiece);
        }
        int playerNumber = sc.nextInt();
        i = 0;
        while (i < playerNumber) {
            Player player;
            switch (sc.next()) {
                case "G":
                    player = new Greedy(sc.next());
                    i++;
                    break;
                case "C":
                    player = new Carefull(sc.next());
                    i++;
                    break;
                case "T":
                    player = new Tactical(sc.next());
                    i++;
                    break;
                default:
                    throw new InvalidInputException();
            }
            players.add(player);
        }
        int numberOfCubeThrows = sc.nextInt();
        i = 0;
        while (i < numberOfCubeThrows) {
            cubeThrows.add(sc.nextInt());
            i++;
        }
    }
    /**
     * you can use this to check what boardpieces and players are in the database
     */
    public void report() {
        System.out.println("Boardpieces in database: ");
        for (BoardPieces b : board) {
            System.out.println(b);
        }
        System.out.println("Players in database: ");
        for (Player b : players) {
            System.out.println(b);
        }
    }
    /**
     * this is for regular usage, it visualises everything what happens on console
     * the first for is playes for every player
     * the first 3 if selects the boardpiece type and gives/removes money
     * Property is different. there you have to check if its owned or not. if it is you pay.
     * if its not the player desides to buy it or leave it
     * in the end you check if anyone lost after the cubethrow.
     */
    public void round() {
        for (int i = 0; i < players.size(); i++) {
            int number = cubeThrows.get(x);
            x++;
            System.out.println("Az " + i + ". jatekos dobott. Szám: " + number);
            players.get(i).location = (number + players.get(i).location) % board.size();
            if (board.get(players.get(i).location).type == 'L') {
                System.out.println("Az " + i + ". jatekos Szerencse mezore lepett.");
                System.out.println("Az " + i + ". jatekos penze elotte:" + players.get(i).money);
                players.get(i).money += board.get(players.get(i).location).price;
                System.out.println("Az " + i + ". jatekos penze utana:" + players.get(i).money);
            }
            if (board.get(players.get(i).location).type == 'S') {
                System.out.println("Az " + i + ". jatekos Szolgaltatas mezore lepett.");
                System.out.println("Az " + i + ". jatekos penze elotte:" + players.get(i).money);
                players.get(i).money -= board.get(players.get(i).location).price;
                System.out.println("Az " + i + ". jatekos penze utana:" + players.get(i).money);
            }
            if (board.get(players.get(i).location) instanceof Property) {
                System.out.println("Az " + i + ". jatekos Ingatlan mezore lepett.");
                Property asd = new Property();
                asd = (Property) board.get(players.get(i).location);
                if (!asd.getOwned()) {
                    System.out.println("A mezonek nem volt tulajdonosa");
                    //itt eldonti a joember megveszi e vagy nem.
                    if (players.get(i).strategy == 'G') {
                        if (players.get(i).money > 1000) {
                            System.out.println("Az " + i + ". jatekos megvasarolta a teruletet.");
                            asd.setOwned(Boolean.TRUE);
                            asd.setOwnedBy(players.get(i).name);
                            players.get(i).money -= 1000;
                        }
                    }
                    if (players.get(i).strategy == 'C') {
                        if ((players.get(i).money / 2) > 1000) {
                            asd.setOwned(Boolean.TRUE);
                            asd.setOwnedBy(players.get(i).name);
                            players.get(i).money -= 1000;
                            System.out.println("Az " + i + ". jatekos megvasarolta a teruletet.");
                        }
                    }
                    if (players.get(i).strategy == 'T') {
                        // itt az a baj hogy nem tudom kiszedni az adattagot ugyan ugy mint a asd eseten
                        Tactical t = (Tactical) players.get(i);
                        if (!t.getBought()) {
                            if (players.get(i).money > 1000) {
                                asd.setOwned(Boolean.TRUE);
                                asd.setOwnedBy(players.get(i).name);
                                players.get(i).money -= 1000;
                                t.setBought(true);
                                players.set(i, t);
                                System.out.println("Az " + i + ". jatekos megvasarolta a teruletet.");
                            } else {
                                t.setBought(false);
                            }
                        }
                    }
                    board.set(players.get(i).location, asd);
                } else {
                    //ha van tulajdonosa
                    if (asd.getOwnedBy() != players.get(i).name) {
                        System.out.println("A mezonek nem az" + i + ". jatekos a tulajdonosa");
                        //ha nem az ove a telek, de van tulajdonosa
                        if (asd.getHoused() == false) {
                            System.out.println("A jatekos 500 at veszitett. Jelenlegi penze:" + players.get(i).money);
                            players.get(i).money -= 500;
                        } else {
                            System.out.println("A jatekos 2000 et veszitett. Jelenlegi penze:" + players.get(i).money);
                            players.get(i).money -= 2000;
                        }
                    } else {
                        System.out.println("A mezonek az" + i + ". jatekos a tulajdonosa");
                        //itt eldonti a paraszt akar e ra hazat venni
                        if (players.get(i).strategy == 'G') {
                            if (players.get(i).money > 5000) {
                                System.out.println("Az " + i + ". jatekos Hazat vasarol.");
                                System.out.println("Az " + i + ". jatekos penze elotte:" + players.get(i).money);
                                asd.setOwned(Boolean.TRUE);
                                asd.setOwnedBy(players.get(i).name);
                                players.get(i).money -= 5000;
                                System.out.println("Az " + i + ". jatekos penze utana:" + players.get(i).money);
                            }
                        }
                        if (players.get(i).strategy == 'C') {
                            if ((players.get(i).money / 2) > 5000) {
                                System.out.println("Az " + i + ". jatekos Hazat vasarol.");
                                System.out.println("Az " + i + ". jatekos penze elotte:" + players.get(i).money);
                                asd.setOwned(Boolean.TRUE);
                                asd.setOwnedBy(players.get(i).name);
                                players.get(i).money -= 5000;
                                System.out.println("Az " + i + ". jatekos penze utana:" + players.get(i).money);
                            }
                        }
                        if (players.get(i).strategy == 'T') {
                            // itt az a baj hogy nem tudom kiszedni az adattagot ugyan ugy mint a asd eseten
                            Tactical t = (Tactical) players.get(i);
                            if (!t.getBought()) {
                                if (players.get(i).money > 5000) {
                                    System.out.println("Az " + i + ". jatekos Hazat vasarol.");
                                    System.out.println("Az " + i + ". jatekos penze elotte:" + players.get(i).money);
                                    asd.setOwned(Boolean.TRUE);
                                    asd.setOwnedBy(players.get(i).name);
                                    players.get(i).money -= 5000;
                                    t.setBought(true);
                                    players.set(i, t);
                                    System.out.println("Az " + i + ". jatekos penze utana:" + players.get(i).money);
                                } else {
                                    t.setBought(false);
                                }
                            }
                        }
                    }
                    board.set(players.get(i).location, asd);
                }
            }
            checkIfDied(players.get(i));
        }
    }
    /**
     * this is the same as the previous one. read it to check
     * The only difference is there is dataList array for testing
     * @return dataList array, only for testing
     */
    public ArrayList<Integer> roundTest() {
        ArrayList<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            int number = cubeThrows.get(x);
            x++;
            dataList.add(number);
            players.get(i).location = (number + players.get(i).location) % board.size();
            if (board.get(players.get(i).location).type == 'L') {
                players.get(i).money += board.get(players.get(i).location).price;
                dataList.add(players.get(i).money);
            }
            if (board.get(players.get(i).location).type == 'S') {
                players.get(i).money -= board.get(players.get(i).location).price;
                dataList.add(players.get(i).money);
            }
            if (board.get(players.get(i).location) instanceof Property) {
                Property asd = new Property();
                asd = (Property) board.get(players.get(i).location);
                if (!asd.getOwned()) {
                    if (players.get(i).strategy == 'G') {
                        if (players.get(i).money > 1000) {
                            asd.setOwned(Boolean.TRUE);
                            asd.setOwnedBy(players.get(i).name);
                            players.get(i).money -=1000;
                            dataList.add(players.get(i).money);
                        }
                    }
                    if (players.get(i).strategy == 'C') {
                        if ((players.get(i).money / 2) > 1000) {
                            asd.setOwned(Boolean.TRUE);
                            asd.setOwnedBy(players.get(i).name);
                            players.get(i).money -=1000;
                            dataList.add(players.get(i).money);
                        }
                    }
                    if (players.get(i).strategy == 'T') {
                        // itt az a baj hogy nem tudom kiszedni az adattagot ugyan ugy mint a asd eseten
                        Tactical t = (Tactical) players.get(i);
                        if (!t.getBought()) {
                            if (players.get(i).money > 1000) {
                                asd.setOwned(Boolean.TRUE);
                                asd.setOwnedBy(players.get(i).name);
                                players.get(i).money -= 1000;
                                t.setBought(true);
                                players.set(i, t);
                                dataList.add(players.get(i).money);
                            } else {
                                dataList.add(players.get(i).money);
                                t.setBought(false);
                            }
                        }
                    }
                    board.set(players.get(i).location, asd);
                } else {
                    //ha van tulajdonosa
                    if (asd.getOwnedBy() != players.get(i).name) {
                        if (asd.getHoused() == false) {
                            players.get(i).money -= 500;
                            dataList.add(players.get(i).money);
                        } else {
                            players.get(i).money -= 2000;
                            dataList.add(players.get(i).money);
                        }
                    } else {
                        if (players.get(i).strategy == 'G') {
                            if (players.get(i).money > 5000) {
                                asd.setOwned(Boolean.TRUE);
                                asd.setOwnedBy(players.get(i).name);
                                players.get(i).money -= 5000;
                                dataList.add(players.get(i).money);
                            }
                        }
                        if (players.get(i).strategy == 'C') {
                            if ((players.get(i).money / 2) > 5000) {
                                asd.setOwned(Boolean.TRUE);
                                asd.setOwnedBy(players.get(i).name);
                                players.get(i).money -= 5000;
                                dataList.add(players.get(i).money);
                            }
                        }
                        if (players.get(i).strategy == 'T') {
                            // itt az a baj hogy nem tudom kiszedni az adattagot ugyan ugy mint a asd eseten
                            Tactical t = (Tactical) players.get(i);
                            if (!t.getBought()) {
                                if (players.get(i).money > 5000) {
                                    asd.setOwned(Boolean.TRUE);
                                    asd.setOwnedBy(players.get(i).name);
                                    players.get(i).money -= 5000;
                                    t.setBought(true);
                                    players.set(i, t);
                                    dataList.add(players.get(i).money);
                                } else {
                                    t.setBought(false);
                                }
                            }
                        }
                    }
                    board.set(players.get(i).location, asd);
                }
            }
            checkIfDied(players.get(i));
        }
        return dataList;
    }
    /**
     * checks if a player died or not
     * @param p a player from players array
     */
    public void checkIfDied(Player p) {
        if (p.money < 0) {
            die(p);
        }
    }
    /**
     * this metod delets a player if he lost, unlocks his propertys and delets his houses
     * @param p a player from players array
     */
    public void die(Player p) {
        System.out.println(p.name + " nevu jatekos meghalt.");
        secondLost = p.name;
        deathcounter++;
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i) instanceof Property) {
                Property prop = (Property) board.get(i);
                if (prop.getOwnedBy() == p.name) {
                    prop.setOwned(false);
                    prop.setOwnedBy("");
                    board.set(i, prop);
                }
            }
        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).name == p.name) {
                players.remove(i);
            }
        }
    }

    /**
     * if its the end of game true, else false  
     * @return if its end of te game or not
     */
    public boolean endOfGame() {
        if (deathcounter == 2) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * creates a random number between 1 and 6, simulates a cube
     * @return randum number between 1 and 6
     */
    public int cubeThrow() {
        int min = 1;
        int max = 6;
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return random_int;
    }
    /**
     * its for regular usage, with more information.
     * its the cycle whitch runs the game
     */
    public void simulation() {
        int roundcounter = 1;
        while (!endOfGame()) {
            round();
            System.out.println(roundcounter + " KOR VEGE");
            roundcounter++;
        }
        System.out.println("JATEK VEGE");
    }
    /**
     * it creates a matrix with integers, its used only for testing.    
     * @return ArrayList<ArrayList<Integer>> for testing only
     */
    public ArrayList<ArrayList<Integer>> simulationTest() { // ez a test fuggveny
        int roundcounter = 1;
        ArrayList<ArrayList<Integer>> testData = new ArrayList<>();
        while (!endOfGame()) {
            testData.add(roundTest());
            roundcounter++;
        }
        return testData;
    }
}
