package com.innovativemobileapps.ngujjari.squareapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "MainActivity";

    Animation animation;
    private Button btnClick;
   // private ImageButton btnClickPly1;
  //  private ImageButton btnClickPly2;
    private Button btnClickReset;
    private ImageButton btnClick1;
    private ImageButton btnClick2;
    private ImageButton btnClick3;
    private ImageButton btnClick4;
    private ImageButton btnClick5;
    private ImageButton btnClick6;
    private ImageButton btnClick7;
    private ImageButton btnClick8;
    private ImageButton btnClick9;
    private TextView toastText;
    private Toast toast;
    private View layout;
    private boolean highLevel = true;

    private XmlResourceParser xmlParser;
    LinearLayout L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.responsive);
/*
        btnClickPly1 = (ImageButton) findViewById(R.id.player1Btn) ;
        btnClickPly1.setOnClickListener(this);

        btnClickPly2 = (ImageButton) findViewById(R.id.player2Btn) ;
        btnClickPly2.setOnClickListener(this);
*/
       // L=(LinearLayout)findViewById(R.id.ll2);

       // L.setLayoutParams(new LinearLayout.LayoutParams(1080, 400));


        btnClick1 = (ImageButton) findViewById(R.id.button1) ;
        //btnClick1.setOnClickListener(this);
        btnClick1.setOnTouchListener(new MyTouchListener());
        btnClick1.setOnDragListener(new MyDragListener());

        btnClick2 = (ImageButton) findViewById(R.id.button2) ;
       // btnClick2.setOnClickListener(this);
        btnClick2.setOnTouchListener(new MyTouchListener());
        btnClick2.setOnDragListener(new MyDragListener());

        btnClick3 = (ImageButton) findViewById(R.id.button3) ;
       //// btnClick3.setOnClickListener(this);
        btnClick3.setOnTouchListener(new MyTouchListener());
        btnClick3.setOnDragListener(new MyDragListener());

        btnClick4 = (ImageButton) findViewById(R.id.button4) ;
       // btnClick4.setOnClickListener(this);
        btnClick4.setOnTouchListener(new MyTouchListener());
        btnClick4.setOnDragListener(new MyDragListener());

        btnClick5 = (ImageButton) findViewById(R.id.button5) ;
       // btnClick5.setOnClickListener(this);
        btnClick5.setOnTouchListener(new MyTouchListener());
        btnClick5.setOnDragListener(new MyDragListener());

        btnClick6 = (ImageButton) findViewById(R.id.button6) ;
       // btnClick6.setOnClickListener(this);
        btnClick6.setOnTouchListener(new MyTouchListener());
        btnClick6.setOnDragListener(new MyDragListener());

        btnClick7 = (ImageButton) findViewById(R.id.button7) ;
        //btnClick7.setOnClickListener(this);
        btnClick7.setOnTouchListener(new MyTouchListener());
        btnClick7.setOnDragListener(new MyDragListener());

        btnClick8 = (ImageButton) findViewById(R.id.button8) ;
       // btnClick8.setOnClickListener(this);
        btnClick8.setOnTouchListener(new MyTouchListener());
        btnClick8.setOnDragListener(new MyDragListener());

        btnClick9 = (ImageButton) findViewById(R.id.button9) ;
        //btnClick9.setOnClickListener(this);
        btnClick9.setOnTouchListener(new MyTouchListener());
        btnClick9.setOnDragListener(new MyDragListener());

        btnClickReset = (Button) findViewById(R.id.buttonReset) ;
        btnClickReset.setOnClickListener(this);


        animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(600); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in

        // Messages
        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.layout2,
                (ViewGroup) findViewById(R.id.mylinearlayout));
        toastText = (TextView) layout.findViewById(R.id.toasttext);


        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
       // Resources res = this.getResources();
       // xmlParser  = res.getXml(R.xml.gameresults);

        Log.v(TAG, "onCreate method end :");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isPlayerFlipped(String buttonVlu)
    {
        if(buttonVlu.startsWith("Player"))
        {
            return true;
        }
        return false;
    }

    private void setMessage(Toast toast, View layout, TextView text, String msg)
    {

        text.setText(msg);
        toast.setView(layout);
        //toast.show();
    }
    public void onClick(View v)
    {

        Log.v(TAG, " button id : "+v.getId());
        String buttonVlu = this.getButtonId(v.getId());
        Log.v(TAG, " buttonVlu : "+buttonVlu);
        // Messages
        setMessage(toast, layout,toastText, "");

       // MatchSequence ms = null;
        MatchApplication app = (MatchApplication) getApplication();
        MatchSequence ms = (app.getMs() != null ) ? app.getMs() : new MatchSequence();

        if(buttonVlu.equals("Reset")){
            Log.v(TAG, " Reset Clicked !!! ");
            ms = new MatchSequence();
            app.setMs(ms);
            this.setColor(new HashSet<Integer>(), new HashSet<Integer>());
            stopSuccessAnimation();
          //  Intent intent = getIntent();
          //  finish();
          //  startActivity(intent);
            return;
        }
        app.setMs(ms);
        ms.player = "Player1";
        Log.v(TAG, ms.flipPlayer+ " onClick method Begin   Button ID : "+v.getId());
/*
        if(ms.flipPlayer) {
            if(isPlayerFlipped(buttonVlu) ){
                ms.player = buttonVlu;
            }
            if(!ms.dragStatus && ms.previousPlayer != null && !ms.previousPlayer.equals("")
                    && ms.player.equals(ms.previousPlayer)){
                setMessage(toast, layout,toastText, "Invalid player. Please choose other player.");
                // Do not execute .. wrong player.... flip the player
                Log.v(TAG, "Do not execute 1.. wrong player.... flip the player  "+v.getId());
                return;
            }
        }
        Log.v(TAG, " Begin  : "+ms.flipPlayer+ "=  curr: "+ms.player +"  prev  = "+ms.previousPlayer +"  buttonVlu = "+buttonVlu);

        if(ms.flipPlayer == true || ms.previousPlayer == null || ms.previousPlayer.equals("")){
            this.setPlayerBtnBackground(ms.player);
        }
*/
        Log.v(TAG, " END  : "+ms.flipPlayer+ "=  curr: "+ms.player +"  prev  = "+ms.previousPlayer +"  buttonVlu = "+buttonVlu);

        return;

    }

    private void setPlayerBtnBackground(String player)
    {
        /*
        Resources res = getResources();

        if(player.equals("Player1")) {
            Drawable shapePly1 = res. getDrawable(R.drawable.buttonshapeselected);
            btnClickPly1.setImageResource(R.drawable.crickettoon);
            btnClickPly1.setBackground(shapePly1);

            Drawable shapePly2 = res. getDrawable(R.drawable.buttonshape);
            btnClickPly2.setImageResource(R.drawable.soccergirl);
            btnClickPly2.setBackground(shapePly2);
        }
        else if(player.equals("Player2")){
            Drawable shapePly1 = res. getDrawable(R.drawable.buttonshape);
            btnClickPly1.setImageResource(R.drawable.crickettoon);
            btnClickPly1.setBackground(shapePly1);

            Drawable shapePly2 = res. getDrawable(R.drawable.buttonshapeselected);
            btnClickPly2.setImageResource(R.drawable.soccergirl);
            btnClickPly2.setBackground(shapePly2);
        }
        else{
            setMessage(toast, layout, toastText, "Please select player.");
        }
        */
    }

    // This defines your touch listener
    private final class MyTouchListener implements View.OnTouchListener {

        public boolean onTouch(View view, MotionEvent motionEvent) {
            Log.v(TAG, "MyTouchListener onTouch Begin : "+view.getId());
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                Log.v(TAG, "MyTouchListener onTouch end  true : "+view.getId());
                return true;
            } else {
                Log.v(TAG, "MyTouchListener onTouch end False : "+view.getId());
                Log.v(TAG, " button id : "+view.getId());

                String buttonVlu = getButtonId(view.getId());
                Log.v(TAG, " buttonVlu : "+buttonVlu);

                // Messages
                setMessage(toast, layout,toastText, "");

                // MatchSequence ms = null;
                MatchApplication app = (MatchApplication) getApplication();

                MatchSequence ms = (app.getMs() != null ) ? app.getMs() : new MatchSequence();

                if(buttonVlu.equals("Reset")){
                    Log.v(TAG, " Reset Clicked !!! ");
                    ms = new MatchSequence();
                    app.setMs(ms);
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                     return false;
                }
                app.setMs(ms);
                Log.v(TAG, ms.flipPlayer + " onTouch method Begin   Button ID : " + view.getId());
                ms.player = "Player1";
                if(ms.flipPlayer) {
                    if(isPlayerFlipped(buttonVlu) ){
                        ms.player = buttonVlu;
                    }
/*
                    if(!ms.dragStatus && ms.previousPlayer != null && !ms.previousPlayer.equals("")
                            && ms.player.equals(ms.previousPlayer)){
                        setMessage(toast, layout,toastText, "Invalid player. Please choose other player.");
                        // Do not execute .. wrong player.... flip the player
                        Log.v(TAG, "onTouch: Do not execute 1.. wrong player.... flip the player  "+view.getId());
                        return false;
                    } */
                }

                Log.v(TAG, " onTouch Begin  : " + ms.flipPlayer + "=  curr: " + ms.player + "  prev  = " + ms.previousPlayer + "  buttonVlu = " + buttonVlu);
                if(!buttonVlu.startsWith("Player") && !ms.player.equals(""))
                {
                    boolean executeStep = ms.execute(Integer.parseInt(buttonVlu));
                    Log.v(TAG, " onTouch executeStep  : "+executeStep);
                    if (executeStep) {
                    } else {
                        ms.flipPlayer = false;
                    }
                        ms.previousPlayer = ms.player;
                        ms.flipPlayer = true;

                    if(!(ms.msgList != null && ms.msgList.size() > 0)) {
                        boolean isWonGame = ms.isWon;
                        if(isWonGame == false){
                            ms.player = "Player2";

                            executeStep = true;
                            while(executeStep)
                            {

                                if(ms.tList.size() < 6)
                                {

                                        int toNd = ms.predictUserinput("singleNd", ms.player);
                                        if (toNd == -1) {
                                            toNd = ms.getRandomNum();
                                        }

                                        Log.v(TAG, "runAlg toNd before execute == " + toNd + " " + " from palyer = " + ms.player);
                                    executeStep = ms.execute(toNd);
                                }
                                else
                                {
                                    Log.v(TAG, "MyTouchListener: runAlg Lets play the game !!! !!!!  player = " + ms.player);
                                    ActionTakenBean userInput =  ms.predictUserinput(ms.player);
                                    int fromNd = (userInput.getFromNd() != null) ? userInput.getFromNd().intValue() : -1;
                                    int toNd = (userInput.getToNd() != null) ? userInput.getToNd().intValue() : -1;
                                    if(fromNd > 0 && toNd > 0) {
                                        executeStep = ms.execute(fromNd, toNd);
                                        //break;
                                    }
                                }

                                executeStep = (executeStep == true)  ? false : true;
                            }

                        }
                    }

                    setMessages(ms);
                }
                else if(ms.flipPlayer == true || ms.previousPlayer == null || ms.previousPlayer.equals("")) {
                    setPlayerBtnBackground(ms.player);
                }
                Log.v(TAG, " END  : " + ms.flipPlayer + "=  curr: " + ms.player + "  prev  = " + ms.previousPlayer + "  buttonVlu = " + buttonVlu);

                return false;
            }

        }
    }

    private class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {

            int action = event.getAction();
           // Log.v(TAG, "MyDragListener onTouch begin : "+v.getId() +"  action : "+action);
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                   // v.setBackgroundDrawable(greydraggable);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                   // v.setBackgroundDrawable(buttonplayer1);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup


                   View view = (View) event.getLocalState();

                    Log.v(TAG, "MyDragListener onTouch after ACTION_DROP : source : "+view.getId() +"  target : "+v.getId());

                    String fromButton = getButtonId(view.getId());
                    String toButton = getButtonId(v.getId());
                    Log.v(TAG, "MyTouchListener onTouch end False : "+view.getId() + " fromButton : "+fromButton +",   toButton = "+toButton);

                    // Init Toast
                    setMessage(toast, layout, toastText, "");

                    MatchApplication app = (MatchApplication) getApplication();
                    MatchSequence ms = (app.getMs() != null ) ? app.getMs() : new MatchSequence();

                    app.setMs(ms);

                    Log.v(TAG, "onDrag  Begin  : "+ms.flipPlayer+ "=  curr: "+ms.player +"  prev  = "+ms.previousPlayer +"  fromButton = "+Integer.parseInt(fromButton) +" toBtn = "+Integer.parseInt(toButton)+"  ,ms.dragStatus = "+ms.dragStatus);
                    if(ms.player.equals("Player1") && !fromButton.equals(toButton)) {
                        boolean executeStep = ms.execute(Integer.parseInt(fromButton), Integer.parseInt(toButton));
                        Log.v(TAG, "onDrag  executeStep  : " + executeStep);
                        if (executeStep) {
                            ms.previousPlayer = ms.player;
                            // ms.player = buttonVlu;
                            ms.flipPlayer = true;

                            if (!(ms.msgList != null && ms.msgList.size() > 0)) {
                                boolean isWonGame = ms.isWon;
                                if (isWonGame == false) {
                                    ms.player = "Player2";

                                    executeStep = true;
                                    while (executeStep) {

                                        if (ms.tList.size() < 6) {

                                            //int toNd = ms.predictUserinput("singleNd", ms.player);
                                            ActionTakenBean userInput = ms.predictUserinput(ms.player);
                                            int toNd = userInput.getToNd();
                                            if (toNd == -1) {
                                                toNd = ms.getRandomNum();
                                            }

                                            Log.v(TAG, "runAlg toNd before execute == " + toNd + " " + " from palyer = " + ms.player);
                                            executeStep = ms.execute(toNd);
                                        } else {
                                            ActionTakenBean userInput = ms.predictUserinput(ms.player);
                                            Log.v(TAG, "MyDragListener :runAlg Lets play the game !!! !!!!  player = " + ms.player +" from = "+userInput.getFromNd() +" toNd = "+userInput.getToNd());

                                            int fromNd = (userInput.getFromNd() != null) ? userInput.getFromNd().intValue() : -1;
                                            int toNd = (userInput.getToNd() != null) ? userInput.getToNd().intValue() : -1;
                                            if(fromNd > 0 && toNd > 0) {
                                                executeStep = ms.execute(fromNd, toNd);
                                                //break;
                                            }else{
                                                Log.v(TAG, "ELSE MyDragListener :runAlg Lets play the game !!! !!!!  player = " + ms.player +" from = "+fromNd +" toNd = "+toNd);

                                            }

                                        }

                                        executeStep = (executeStep == true) ? false : true;
                                    }


                                }
                            }


                        } else {
                            ms.flipPlayer = false;

                        }

                        setMessages(ms);
                    }
                    Log.v(TAG, " END  : "+ms.flipPlayer+ "=  curr: "+ms.player +"  prev  = "+ms.previousPlayer +"  buttonVlu = "+Integer.parseInt(toButton));
                    view.setVisibility(View.VISIBLE);
                   // view.dispatchTouchEvent(event);

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundDrawable(buttonplayer1);
                   // Log.v(TAG, "MyDragListener onDrag ACTION_DRAG_ENDED : "+v.getId() +"  action : "+action);
                default:
                    break;
            }
            Log.v(TAG, "MyDragListener onTouch end : " + v.getId() + "  action : " + action);
            return true;
        }
    }

    private void setMessages( MatchSequence ms)
    {
        List<String> msgList = ms.msgList;
        Set<Integer> aList = ms.aList;
        Set<Integer> bList = ms.bList;

        if(!(msgList != null && msgList.size() > 0)) {
            boolean isWonGame = ms.isWon;


            if (isWonGame) {
                setMessage(toast, layout, toastText, "Congratulations !! "+ms.player + " won the game.");
                startSuccessAnimation(ms.player, aList, bList);

                Log.v(TAG, ms.player + " WON THE GAME !!!!!!! ");
            }else{
                for(String msg : ms.msgList) {
                    setMessage(toast, layout, toastText, msg);
                }

            }


            setColor(aList, bList);
        }
        else
        {
            for(String msg : msgList) {
                Log.v(TAG, ms.player + " Message : " + msg);
                if(msg.equals(MatchSequence.MSG_1002)){
                    setColor(aList, bList);
                    setMessage(toast, layout, toastText, "Invalid move !!!!");
                }
            }
        }

    }
    private void startSuccessAnimation(String player, Set<Integer> aList,  Set<Integer> bList)
    {

        Set<Integer> abList = (player.equals("Player1")) ? aList : bList;
        for (Integer node : abList)
        {
            Log.v(TAG, " Success Nodes  " + node +" =  "+buttonMapRev.get(node+""));
            ImageButton btnClick = (ImageButton) findViewById(buttonMapRev.get(node+""));
            //Resources res = getResources();
            //Drawable shape = res. getDrawable(R.drawable.buttonplayer1);
            // btnClick.setBackground();;
            btnClick.startAnimation(animation);
           // btnClick.setBackground(shape);
        }
    }

    private void stopSuccessAnimation()
    {

        Set<Integer> abList = new HashSet(Arrays.asList(MatchSequence.nodes));

        for (Integer node : abList)
        {
            Log.v(TAG, " Success Nodes  " + node +" =  "+buttonMapRev.get(node+""));
            ImageButton btnClick = (ImageButton) findViewById(buttonMapRev.get(node+""));
            //Resources res = getResources();
            //Drawable shape = res. getDrawable(R.drawable.buttonplayer1);
            // btnClick.setBackground();;
            btnClick.clearAnimation();
            // btnClick.setBackground(shape);
        }
    }


    private void setColor( Set<Integer> aList,  Set<Integer> bList)
    {

        // Flowers, X's and Os ,
        Set<Integer> setA = new HashSet(Arrays.asList(MatchSequence.nodes));
        for (Integer node : aList)
        {
            Log.v(TAG, " A SetColor Node  " + node +" =  "+buttonMapRev.get(node+""));
            ImageButton btnClick = (ImageButton) findViewById(buttonMapRev.get(node+""));
            Resources res = getResources();
            btnClick.setImageResource(R.drawable.xshape);
            // b1.setText(adapt_objmenu.city_name_array[i]);
            //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

              //  lp.addRule(RelativeLayout.RIGHT_OF, btnClick.getId() - 1);

            //btnClick.setLayoutParams(lp);
            Drawable shape = res. getDrawable(R.drawable.buttonplayer1);
           // btnClick.setBackground();;
            btnClick.setBackground(shape);
        }
        for (Integer node : bList)
        {
            Log.v(TAG, " B SetColor Node  " + node +" = "+buttonMapRev.get(node+""));
            /*Button btnClick = (Button) findViewById(buttonMapRev.get(node+""));
            btnClick.setBackgroundColor(Color.CYAN);*/
            ImageButton btnClick = (ImageButton) findViewById(buttonMapRev.get(node+""));
            Resources res = getResources();
            btnClick.setImageResource(R.drawable.circle);

            // b1.setText(adapt_objmenu.city_name_array[i]);
           // RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            //lp.addRule(RelativeLayout.RIGHT_OF, btnClick.getId() - 1);

            //btnClick.setLayoutParams(lp);
            //Drawable shape = res. getDrawable(R.drawable.buttonplayer2);
            // btnClick.setBackground();;

            //btnClick.setBackground(shape);

        }


        setA.removeAll(aList);
        setA.removeAll(bList);
        for (Integer node : setA)
        {
            Log.v(TAG, " Default SetColor Node  " + node +" = "+buttonMapRev.get(node+""));
            /*Button btnClick = (Button) findViewById(buttonMapRev.get(node+""));
            btnClick.setBackgroundColor(Color.LTGRAY);*/
            ImageButton btnClick = (ImageButton) findViewById(buttonMapRev.get(node+""));
            Resources res = getResources();
            Drawable shape = res. getDrawable(R.drawable.grey);
            btnClick.setBackground(shape);
            btnClick.setImageResource(R.drawable.grey);
        }


    }

    private String getButtonId(int buttonId)
    {

        return buttonMap.get(buttonId);
    }

    static Map<Integer, String> buttonMap = new HashMap<Integer, String>();
    static Map<String, Integer> buttonMapRev = new HashMap<String, Integer>();

    static {
        buttonMap.put(R.id.player1Btn, "Player1");
        buttonMap.put(R.id.player2Btn, "Player2");
        buttonMap.put(R.id.buttonReset, "Reset");
        buttonMap.put(R.id.button1, "1");
        buttonMap.put(R.id.button2, "2");
        buttonMap.put(R.id.button3, "3");
        buttonMap.put(R.id.button4, "4");
        buttonMap.put(R.id.button5, "5");
        buttonMap.put(R.id.button6, "6");
        buttonMap.put(R.id.button7, "7");
        buttonMap.put(R.id.button8, "8");
        buttonMap.put(R.id.button9, "9");

        buttonMapRev.put("Player1", R.id.player1Btn);
        buttonMapRev.put("Player2", R.id.player2Btn);
        buttonMapRev.put("1", R.id.button1);
        buttonMapRev.put("2", R.id.button2);
        buttonMapRev.put("3", R.id.button3);
        buttonMapRev.put("4", R.id.button4);
        buttonMapRev.put("5", R.id.button5);
        buttonMapRev.put("6", R.id.button6);
        buttonMapRev.put("7", R.id.button7);
        buttonMapRev.put("8", R.id.button8);
        buttonMapRev.put("9", R.id.button9);


    }


    public void writeToFile(String vlu)
    {

        String filename = "file.txt";

        try {
            FileOutputStream fos;

            fos = openFileOutput(filename, Context.MODE_APPEND);


            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

            serializer.startTag(null, "root");

            for (int j = 0; j < 3; j++) {

                serializer.startTag(null, "record");

                serializer.text(vlu);

                serializer.endTag(null, "record");
            }
            serializer.endDocument();

            serializer.flush();

            fos.close();
        }
        catch (EOFException eof){
            Log.v(TAG, "Write file EOF ex .. " + eof);
        }
        catch (Exception e){
            Log.v(TAG, "Write file  ex .. " + e);
        }
    }



}
