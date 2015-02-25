package com.innovativemobileapps.ngujjari.squareapp;
import android.util.Log;

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
public class MatchSequence {

    private static final String TAG = "MatchSequence";
    
    int totalNodes = 9;
    static Integer nodes[] = {1,2,3,4,5,6,7,8,9};
    int winNodes[][] = {{2,3,4},{4,5,6},{6,7,8},{8,9,2},{2,1,6},{8,1,4},{7,1,3},{9,1,5}};
    static Integer adjNodes[][] = {{2,3,4,5,6,7,8,9},{9,1,3},{2,1,4},{3,1,5},{4,1,6},{5,1,7},{6,1,8},{7,1,9},{8,1,2}};
    int pn = 1;
    int npn[] = {2,3,4,5,6,7,8,9};
    Set<Integer> aList = new HashSet<Integer>();
    Set<Integer> bList = new HashSet<Integer>();
    List<Integer> tList = new ArrayList<Integer>();
    static HashMap<Integer, Integer[]> validMovesMap = new HashMap<Integer, Integer[]>();
    HashMap<String, ActionTakenBean> actionTakenBeanHashMap = new HashMap<String, ActionTakenBean>();

    String player = "";
    String previousPlayer = "";
    boolean isWon = false;
    boolean flipPlayer = true;
    boolean actionSuccess = false;
    boolean dragStatus = false;

    public static final String MSG_1001 = "Entered value is already exist";

    public static final String MSG_1002 = "Enter drag drop values correctly ....";
    public static final String MSG_1003 = "Select TO Node";

    List<String> msgList = new ArrayList<String>();

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
                //  Log.v(TAG,p1);
                tempList.add(new Integer(p1));
            }
            if(checkList.containsAll(tempList))
            {
                isWon = true;
               // Log.v(TAG, "onClick method Begin   Button ID : " + v.getId());
                 Log.v(TAG,player + " WON the GAME !!!!!!!!!!!!!! number are : " + tempList.toString());
            }

        }

        return isWon;

    }

    private int takeInput(String player)
    {
        Scanner in = new Scanner(System.in);
         Log.v(TAG,player+" : Enter an integer in 1,2,3,4,5,6,7,8,9" );
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
        // Log.v(TAG,"vlu == "+vlu);
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
         Log.v(TAG,player+" : move pawn from "+abList.toString() +" to " + setA.toString() );
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
                     Log.v(TAG,"Valid input");

                    if(!isValidMove(fromNd, toNd))
                    {

                         Log.v(TAG,"Invalid Move !! valid moves are .. "+abList.toArray()[0] +" -> " + remainedMoves((Integer)abList.toArray()[0],tList)
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
                     Log.v(TAG,player+" : InValid input try again !!");
                    //takeInput(player, abList, tList);
                    return 1;
                }

            }
            catch(NumberFormatException e)
            {
                 Log.v(TAG,player+" : Invalid input enter again !!" );
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
        Log.v(TAG," validateInput begin input = " + a);
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
                 Log.v(TAG,"Entered value is already exist, Please choose from " + setA.toString());
                msgList.add(MSG_1001);
                return returnVal;
            }
            returnVal = true;
            Log.v(TAG,"Accepted !! "  + returnVal);
            return returnVal;
        }

         Log.v(TAG,"Enter valid integer from 1,2,3,4,5,6,7,8,9" );
        return returnVal;
    }

    // Take the input until user enter exit or e
    /*
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
                 Log.v(TAG,"You entered number "+a);

                if(player.equals("Player1"))
                {
                    aList.add(new Integer(a));
                    //  Log.v(TAG,"Player1 array = "+aList.toArray().toString());
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
                 Log.v(TAG,"Lets play the game !!! !!!!");
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
         Log.v(TAG,"You exited the program ");



    }*/

    public boolean validateInputA(int a)
    {

        if(tList.size() < 6) {
            // a = this.takeInput(player);
            if (!validateInput(a)) {
                actionSuccess = false;
                return actionSuccess;
            }
            actionSuccess = true;
             Log.v(TAG,"You entered number " + a);
          //  return actionSuccess;
        }else
        {

        }
        return actionSuccess;
    }

    private void takeInput(int a, String player)
    {
        ActionTakenBean playerAction = actionTakenBeanHashMap.get(player);

        if(playerAction != null)
        {
           String playerIn = playerAction.getPlayer();
           Integer fromNd = playerAction.getFromNd();
            Integer toNd = playerAction.getToNd();
            if(fromNd != null && !fromNd.equals(new Integer(-1)))
            {
                playerAction.setToNd(a);
            }
            else {
                playerAction.setFromNd(a);
            }
        }
        else
        {
            actionTakenBeanHashMap.put(player, new ActionTakenBean(player, a, -1));
        }

    }
    public int validateInput(int a, String player, Set<Integer> abList, List<Integer> tList)
    {

        Set<Integer> setA = new HashSet(Arrays.asList(nodes));
        Set<Integer> setB = new HashSet(tList);
        setA.removeAll(setB);
         Log.v(TAG,player+" : move pawn from "+abList.toString() +" to " + setA.toString() );



         //   StringTokenizer tokens = new StringTokenizer(s, ",");

            try{

                takeInput(a, player);
                ActionTakenBean playerAction = actionTakenBeanHashMap.get(player);
                int fromNd = playerAction.getFromNd();
                int toNd = playerAction.getToNd();

               // int fromNd = Integer.parseInt(tokens.nextToken());
                // int toNd = Integer.parseInt(tokens.nextToken());


                if(toNd < 0)
                {
                    Log.v(TAG,"SELECT TO Node ........................");
                    dragStatus = true;
                    msgList.clear();
                    msgList.add(MatchSequence.MSG_1003);
                    return 1;
                }
                else if(abList.contains(new Integer(fromNd)) && setA.contains(new Integer(toNd)))
                {
                     Log.v(TAG,"Valid input");

                    if(!isValidMove(fromNd, toNd))
                    {

                         Log.v(TAG,"Invalid Move !! valid moves are .. "+abList.toArray()[0] +" -> " + remainedMoves((Integer)abList.toArray()[0],tList)
                                + " , "+abList.toArray()[1] +" -> "+remainedMoves((Integer)abList.toArray()[1],tList)
                                + " , "+abList.toArray()[2] +" -> "+remainedMoves((Integer)abList.toArray()[2],tList));
                        //takeInput(player, abList, tList);
                        msgList.clear();
                        msgList.add(MatchSequence.MSG_1002);
                        return 1;
                    }
                    abList.remove(new Integer(fromNd));
                    abList.add(new Integer(toNd));
                    msgList.clear();
                    dragStatus = false;
                }
                else
                {
                     Log.v(TAG,player+" : InValid input try again !!");
                    //takeInput(player, abList, tList);
                    msgList.clear();
                    msgList.add(MatchSequence.MSG_1002);
                    return 1;
                }

            }
            catch(NumberFormatException e)
            {
                 Log.v(TAG,player+" : Invalid input enter again !!" );
                //takeInput(player, abList, tList);
                msgList.clear();
                msgList.add(MatchSequence.MSG_1002);
                return 1;
            }

            // return Integer.parseInt(s);

        return 0;
    }

    public boolean execute(int a)
    {

        //int a = 1;
        actionSuccess = false;
        if(tList.size() < 6)
        {
           // a = this.takeInput(player);
            if(!validateInput(a))
            {
                actionSuccess = false;
                return actionSuccess;
            }
             Log.v(TAG, player +" You entered number "+a);

            if(player.equals("Player1"))
            {
                aList.add(new Integer(a));
                //  Log.v(TAG,"Player1 array = "+aList.toArray().toString());
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
             Log.v(TAG,"Lets play the game !!! !!!!");

            Set<Integer> abList =  player.equals("Player2") ? bList : aList;
            int validateInputReturn = validateInput(a, player, abList, tList );
            if(validateInputReturn != 0)
            {
                actionSuccess = false;
                Log.v(TAG," Msg Size:  " + msgList.size()  );
               // msgList.add(MSG_1002);
                return actionSuccess;
            }

            isWon = player.equals("Player2") ? isWon("Player2",bList) : isWon("Player1",aList);
            tList.clear();
            actionTakenBeanHashMap.clear();
            tList.addAll(aList);
            tList.addAll(bList);

        }
        actionSuccess = true;
       // player = player.equals("Player2") ? "Player1" : "Player2";
        return actionSuccess;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        MatchSequence ms = new MatchSequence();
        //ms.runAlg();
    }
}
