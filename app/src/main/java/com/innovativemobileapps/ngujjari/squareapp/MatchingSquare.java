package com.innovativemobileapps.ngujjari.squareapp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by ngujjari on 2/16/15.
 */
public class MatchingSquare {

    int totalNodes = 9;
    Integer nodes[] = {1,2,3,4,5,6,7,8,9};
    int winNodes[][] = {{2,3,4},{4,5,6},{6,7,8},{8,9,2},{2,1,6},{8,1,4},{7,1,3},{9,1,5}};
    static Integer adjNodes[][] = {{2,3,4,5,6,7,8,9},{9,1,3},{2,1,4},{3,1,5},{4,1,6},{5,1,7},{6,1,8},{7,1,9},{8,1,2}};
    int pn = 1;
    int npn[] = {2,3,4,5,6,7,8,9};
    Set<Integer> aList = new HashSet<Integer>();
    Set<Integer> bList = new HashSet<Integer>();
    List<Integer> tList = new ArrayList<Integer>();
    static HashMap<Integer, Integer[]> validMovesMap = new HashMap<Integer, Integer[]>();

    static
    {
        for(int i = 1; i <=9 ; i++)
        {
            validMovesMap.put(i, adjNodes[i-1]);
        }
    }
    private boolean isWon(String player, Set<Integer> checkList)
    {
        boolean isWon = false;
        for (int ma = 0; ma < winNodes.length; ma++) {
            List<Integer> tempList = new ArrayList<Integer>();
            for (int mb = 0; mb < winNodes[ma].length; mb++) {
                int p1 = winNodes[ma][mb];
                // System.out.println(p1);
                tempList.add(new Integer(p1));
            }
            if(checkList.containsAll(tempList))
            {
                isWon = true;
                System.out.println(player + " WON the GAME !!!!!!!!!!!!!! number are : " + tempList.toString());
            }

        }

        return isWon;

    }

    private int takeInput(String player)
    {
        Scanner in = new Scanner(System.in);
        System.out.println(player+" : Enter an integer in 1,2,3,4,5,6,7,8,9" );
        String s = in.nextLine();
        if(s.equals("e") || s.equals("exit"))
        {
            return -1;
        }
        else{
            return Integer.parseInt(s);
        }
        // int a = in.nextInt();

        // return a;
    }

    private boolean isValidMove(int fromNd, int toNd)
    {

        Integer[] vlu = validMovesMap.get(fromNd);
        //System.out.println("vlu == "+vlu);
        for(int k = 0; k < vlu.length; k++)
        {
            if(toNd==vlu[k])
            {
                return true;
            }
        }



        return false;
    }

    private String remainedMoves(Integer moveNd, List<Integer> tList)
    {
        Integer[] validNds = validMovesMap.get(moveNd);
        List<Integer> validList = new ArrayList<Integer>((List<Integer>)Arrays.asList(validNds));
        validList.removeAll(tList);

        return validList.toString();
    }
    private int takeInput(String player, Set<Integer> abList, List<Integer> tList)
    {
        Scanner in = new Scanner(System.in);
        Set<Integer> setA = new HashSet(Arrays.asList(nodes));
        Set<Integer> setB = new HashSet(tList);
        setA.removeAll(setB);
        System.out.println(player+" : move pawn from "+abList.toString() +" to " + setA.toString() );
        String s = in.nextLine();
        if(s.equals("e") || s.equals("exit"))
        {
            return -1;
        }
        else{
            StringTokenizer tokens = new StringTokenizer(s, ",");

            try{
                int fromNd = Integer.parseInt(tokens.nextToken());
                int toNd = Integer.parseInt(tokens.nextToken());



                if(abList.contains(new Integer(fromNd)) && setA.contains(new Integer(toNd)))
                {
                    System.out.println("Valid input");

                    if(!isValidMove(fromNd, toNd))
                    {

                        System.out.println("Invalid Move !! valid moves are .. "+abList.toArray()[0] +" -> " + remainedMoves((Integer)abList.toArray()[0],tList)
                                + " , "+abList.toArray()[1] +" -> "+remainedMoves((Integer)abList.toArray()[1],tList)
                                + " , "+abList.toArray()[2] +" -> "+remainedMoves((Integer)abList.toArray()[2],tList));
                        //takeInput(player, abList, tList);
                        return 1;
                    }
                    abList.remove(new Integer(fromNd));
                    abList.add(new Integer(toNd));
                }
                else
                {
                    System.out.println(player+" : InValid input try again !!");
                    //takeInput(player, abList, tList);
                    return 1;
                }

            }
            catch(NumberFormatException e)
            {
                System.out.println(player+" : Invalid input enter again !!" );
                //takeInput(player, abList, tList);
                return 1;
            }

            // return Integer.parseInt(s);
        }
        return 0;
    }

    private boolean validateInput(int a)
    {
        List<Integer> allNodes = Arrays.asList(nodes);
        boolean returnVal = false;
        if(allNodes.contains(new Integer(a)))
        {
            if(tList.contains(new Integer(a)) && tList.size() < 6)
            {
                returnVal = false;
                // Integer tListArray[] = (Integer[])tList.toArray();
                Set<Integer> setA = new HashSet(Arrays.asList(nodes));
                Set<Integer> setB = new HashSet(tList);
                setA.removeAll(setB);
                // List<Integer> tempArray = ((List)allNodes).clone();
                System.out.println("Entered value is already exist, Please choose from " + setA.toString());
                return returnVal;
            }
            returnVal = true;
            //  System.out.println("Accepted !!" );
            return returnVal;
        }

        System.out.println("Enter valid integer from 1,2,3,4,5,6,7,8,9" );
        return returnVal;
    }

    // Take the input until user enter exit or e
    public void runAlg()
    {
        int a = 1;
        String player = "Player1";
        boolean isWon = false;
        // a = this.takeInput();
        while( a != -1 && !isWon)
        {

            if(tList.size() < 6)
            {
                a = this.takeInput(player);
                if(!validateInput(a))
                {
                    continue;
                }
                System.out.println("You entered number "+a);

                if(player.equals("Player1"))
                {
                    aList.add(new Integer(a));
                    // System.out.println("Player1 array = "+aList.toArray().toString());
                    isWon = isWon("Player1",aList);
                }
                else
                {
                    bList.add(new Integer(a));
                    isWon = isWon("Player2",bList);
                }

                tList.clear();
                tList.addAll(aList);
                tList.addAll(bList);



            }
            else
            {
                System.out.println("Lets play the game !!! !!!!");
                Set<Integer> abList =  player.equals("Player2") ? bList : aList;
                a = 1;
                while(a == 1){
                    a = this.takeInput(player, abList, tList);
                }
                isWon = player.equals("Player2") ? isWon("Player2",bList) : isWon("Player1",aList);
                tList.clear();
                tList.addAll(aList);
                tList.addAll(bList);

            }
            player = player.equals("Player2") ? "Player1" : "Player2";
        }
        System.out.println("You exited the program ");



    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        MatchingSquare ms = new MatchingSquare();
        ms.runAlg();
    }
}
