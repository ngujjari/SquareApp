package com.innovativemobileapps.ngujjari.squareapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button btnClickPly1;
    private Button btnClickPly2;
    private Button btnClickReset;
    private Button btnClick1;
    private Button btnClick2;
    private Button btnClick3;
    private Button btnClick4;
    private Button btnClick5;
    private Button btnClick6;
    private Button btnClick7;
    private Button btnClick8;
    private Button btnClick9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout2);

        btnClickPly1 = (Button) findViewById(R.id.player1Btn) ;
        btnClickPly1.setOnClickListener(this);

        btnClickPly2 = (Button) findViewById(R.id.player2Btn) ;
        btnClickPly2.setOnClickListener(this);


        btnClick1 = (Button) findViewById(R.id.button1) ;
        btnClick1.setOnClickListener(this);

        btnClick2 = (Button) findViewById(R.id.button2) ;
        btnClick2.setOnClickListener(this);

        btnClick3 = (Button) findViewById(R.id.button3) ;
        btnClick3.setOnClickListener(this);

        btnClick4 = (Button) findViewById(R.id.button4) ;
        btnClick4.setOnClickListener(this);

        btnClick5 = (Button) findViewById(R.id.button5) ;
        btnClick5.setOnClickListener(this);

        btnClick6 = (Button) findViewById(R.id.button6) ;
        btnClick6.setOnClickListener(this);

        btnClick7 = (Button) findViewById(R.id.button7) ;
        btnClick7.setOnClickListener(this);

        btnClick8 = (Button) findViewById(R.id.button8) ;
        btnClick8.setOnClickListener(this);

        btnClick9 = (Button) findViewById(R.id.button9) ;
        btnClick9.setOnClickListener(this);

        btnClickReset = (Button) findViewById(R.id.buttonReset) ;
        btnClickReset.setOnClickListener(this);

        animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in

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
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout2,
                (ViewGroup) findViewById(R.id.mylinearlayout));
        TextView text = (TextView) layout.findViewById(R.id.toasttext);


        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);


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
            return;
        }
        app.setMs(ms);
        Log.v(TAG, ms.flipPlayer+ " onClick method Begin   Button ID : "+v.getId());

        if(ms.flipPlayer) {
            if(isPlayerFlipped(buttonVlu) ){
                ms.player = buttonVlu;
            }



            if(!ms.dragStatus && ms.previousPlayer != null && !ms.previousPlayer.equals("")
                    && ms.player.equals(ms.previousPlayer)){
                setMessage(toast, layout,text, "Invalid player. Please choose other player.");
                // Do not execute .. wrong player.... flip the player
                Log.v(TAG, "Do not execute 1.. wrong player.... flip the player  "+v.getId());
                return;
            }
        }
        Log.v(TAG, " Begin  : "+ms.flipPlayer+ "=  curr: "+ms.player +"  prev  = "+ms.previousPlayer +"  buttonVlu = "+buttonVlu);
    if(!buttonVlu.startsWith("Player") && !ms.player.equals("")) {
        boolean executeStep = ms.execute(Integer.parseInt(buttonVlu));
        Log.v(TAG, " executeStep  : "+executeStep);
        if (executeStep) {
            ms.previousPlayer = ms.player;
           // ms.player = buttonVlu;
            ms.flipPlayer = true;
        } else {
            ms.flipPlayer = false;

        }
        List<String> msgList = ms.msgList;
        if(!(msgList != null && msgList.size() > 0)) {
            boolean isWonGame = ms.isWon;
            Set<Integer> aList = ms.aList;
            Set<Integer> bList = ms.bList;

            if (isWonGame) {
                setMessage(toast, layout, text, "Congratulations !! "+ms.player + " won the game.");
                startSuccessAnimation(ms.player, aList, bList);

                Log.v(TAG, ms.player + " WON THE GAME !!!!!!! " + v.getId());
            }else{
                for(String msg : ms.msgList) {
                    setMessage(toast, layout, text, msg);
                }

            }


            setColor(aList, bList);
        }
        else
        {
            for(String msg : msgList) {
                Log.v(TAG, ms.player + " Message : " + msg);
                if(msg.equals(MatchSequence.MSG_1003)){
                    Resources res = getResources();
                    Drawable shapePly1 = res. getDrawable(R.drawable.greydraggable);
                    Button btnClick = (Button) findViewById(buttonMapRev.get(buttonVlu+""));
                    btnClick.setBackground(shapePly1);
                   /* Button btnClick = (Button) findViewById(buttonMapRev.get(buttonVlu+""));
                    btnClick.setBackgroundColor(Color.rgb(120,244,200));*/
                }
            }
        }
    }
        else if(ms.flipPlayer == true || ms.previousPlayer == null || ms.previousPlayer.equals("")){

        Resources res = getResources();

            if(ms.player.equals("Player1")) {
                Drawable shapePly1 = res. getDrawable(R.drawable.buttonshapeselected);
                btnClickPly1.setBackground(shapePly1);
              //  btnClickPly1.setBackgroundResource(R.drawable.new_todo_image);

                Drawable shapePly2 = res. getDrawable(R.drawable.buttonshape);
                btnClickPly2.setBackground(shapePly2);

              //  btnClickPly1.setBackgroundColor(Color.RED);
              //  btnClickPly2.setBackgroundColor(Color.GREEN);
            }
            else if(ms.player.equals("Player2")){
                Drawable shapePly1 = res. getDrawable(R.drawable.buttonshape);
                btnClickPly1.setBackground(shapePly1);

                Drawable shapePly2 = res. getDrawable(R.drawable.buttonshapeselected);
                btnClickPly2.setBackground(shapePly2);
               // btnClickPly1.setBackgroundColor(Color.GREEN);
               // btnClickPly2.setBackgroundColor(Color.RED);
            }
            else{
                setMessage(toast, layout, text, "Please select player.");
            }

        }

       // for()
       /* switch(v.getId()){
            case R.id.player1Btn:
               // SaveSettings();
                Log.v(TAG, "onClick : "+ v.getId());
                btnClickPly1.setBackgroundColor(Color.RED);
                btnClickPly2.setBackgroundColor(Color.GREEN);
                break;
            case R.id.player2Btn:
                //CloseDialog();
                Log.v(TAG, "onClick : "+ v.getId());
                btnClickPly1.setBackgroundColor(Color.GREEN);
                btnClickPly2.setBackgroundColor(Color.RED);
                break;
            case R.id.button1:
                //CloseDialog();
                Color.alpha(btnClick1.getDrawingCacheBackgroundColor());
                btnClick1.setBackgroundColor(Color.CYAN);
                break;

            default:
                btnClick = (Button) findViewById(v.getId());
                btnClick.setBackgroundColor(Color.YELLOW);
        }

        if(v == btnClickPly1)
        {
            Log.v(TAG, "onClick : "+ v.getId());
           // btnClickPly1.setBackgroundColor(Color.RED);
        }*/
        Log.v(TAG, " END  : "+ms.flipPlayer+ "=  curr: "+ms.player +"  prev  = "+ms.previousPlayer +"  buttonVlu = "+buttonVlu);

        return;

    }

    private void startSuccessAnimation(String player, Set<Integer> aList,  Set<Integer> bList)
    {

        Set<Integer> abList = (player.equals("Player1")) ? aList : bList;
        for (Integer node : abList)
        {
            Log.v(TAG, " Success Nodes  " + node +" =  "+buttonMapRev.get(node+""));
            Button btnClick = (Button) findViewById(buttonMapRev.get(node+""));
            //Resources res = getResources();
            //Drawable shape = res. getDrawable(R.drawable.buttonplayer1);
            // btnClick.setBackground();;
            btnClick.startAnimation(animation);
           // btnClick.setBackground(shape);
        }
    }

    private void setColor( Set<Integer> aList,  Set<Integer> bList)
    {

        Set<Integer> setA = new HashSet(Arrays.asList(MatchSequence.nodes));
        for (Integer node : aList)
        {
            Log.v(TAG, " A SetColor Node  " + node +" =  "+buttonMapRev.get(node+""));
            Button btnClick = (Button) findViewById(buttonMapRev.get(node+""));
            Resources res = getResources();
            Drawable shape = res. getDrawable(R.drawable.buttonplayer1);
           // btnClick.setBackground();;
            btnClick.setBackground(shape);
        }
        for (Integer node : bList)
        {
            Log.v(TAG, " B SetColor Node  " + node +" = "+buttonMapRev.get(node+""));
            /*Button btnClick = (Button) findViewById(buttonMapRev.get(node+""));
            btnClick.setBackgroundColor(Color.CYAN);*/
            Button btnClick = (Button) findViewById(buttonMapRev.get(node+""));
            Resources res = getResources();
            Drawable shape = res. getDrawable(R.drawable.buttonplayer2);
            // btnClick.setBackground();;
            btnClick.setBackground(shape);
        }


        setA.removeAll(aList);
        setA.removeAll(bList);
        for (Integer node : setA)
        {
            Log.v(TAG, " Default SetColor Node  " + node +" = "+buttonMapRev.get(node+""));
            /*Button btnClick = (Button) findViewById(buttonMapRev.get(node+""));
            btnClick.setBackgroundColor(Color.LTGRAY);*/
            Button btnClick = (Button) findViewById(buttonMapRev.get(node+""));
            Resources res = getResources();
            Drawable shape = res. getDrawable(R.drawable.grey);
            btnClick.setBackground(shape);
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
}
