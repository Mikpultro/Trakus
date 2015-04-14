package com.mtatepultro.trakus;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import static com.mtatepultro.trakus.R.drawable.tile_blank;


public class Trakus extends ActionBarActivity {

    // Keep all Images in array
    ImageButton iTile_Player1;
    ImageButton iTile_Player2;
    ImageButton lTile_Player1;
    ImageButton lTile_Player2;
    ImageButton tTile_Player1;
    ImageButton tTile_Player2;

    Integer iTile_Player1_rotation = R.drawable.tile_i_blue_0;
    Integer lTile_Player1_rotation = R.drawable.tile_l_blue_0;
    Integer tTile_Player1_rotation = R.drawable.tile_t_blue_0;
    Integer iTile_Player2_rotation = R.drawable.tile_i_red_0;
    Integer lTile_Player2_rotation = R.drawable.tile_l_red_0;
    Integer tTile_Player2_rotation = R.drawable.tile_t_red_0;

    private static Integer draggingButton;
    private static Integer numTiles = 36;

    private boolean successfulDrop = false;

    private GridLayout oGameBoard;
    private LinearLayout oGameBoardShell;

    private static List<ImageButton> buttonList = new ArrayList<ImageButton>(36);
    private static List<Tile> tileList;

    private static List<Tile> initializeTiles(){

        ArrayList<Tile> tiles = new ArrayList<>(numTiles);

        for (int i = 0; i < numTiles; i++){
            Tile currentTile = new Tile(i, Tile.TileType.blank_Tile, Tile.Owner.open);
            tiles.add(i, currentTile);
        }

        tiles.get(0).setTileType(Tile.TileType.l_Tile);
        tiles.get(35).setTileType(Tile.TileType.l_Tile);

        return tiles;
    }

    public Trakus(){
        tileList = initializeTiles();
    }

    public void rotate(Integer rotatingBtn){
        System.out.println("ROTATE");
        switch (rotatingBtn){
            case R.id.button_Player1_I:
                rotateTile(iTile_Player1, iTile_Player1_rotation);
                break;
            case R.id.button_Player1_L:
                rotateTile(lTile_Player1, lTile_Player1_rotation);
                break;
            case R.id.button_Player1_T:
                rotateTile(tTile_Player1, tTile_Player1_rotation);
                break;
            case R.id.button_Player2_I:
                rotateTile(iTile_Player2, iTile_Player2_rotation);
                break;
            case R.id.button_Player2_L:
                rotateTile(lTile_Player2, lTile_Player2_rotation);
                break;
            case R.id.button_Player2_T:
                rotateTile(tTile_Player2, tTile_Player2_rotation);
                break;
            default:
                break;
        }
    }

    private void rotateTile(ImageButton button, int rotation){
        int newRotation = R.drawable.tile_blank;
        if(button == iTile_Player1)
        {
            switch(rotation){
                case R.drawable.tile_i_blue_0: newRotation = R.drawable.tile_i_blue_90;
                    iTile_Player1_rotation = R.drawable.tile_i_blue_90;
                    break;
                case R.drawable.tile_i_blue_90: newRotation = R.drawable.tile_i_blue_180;
                    iTile_Player1_rotation = R.drawable.tile_i_blue_180;
                    break;
                case R.drawable.tile_i_blue_180: newRotation = R.drawable.tile_i_blue_270;
                    iTile_Player1_rotation = R.drawable.tile_i_blue_270;
                    break;
                case R.drawable.tile_i_blue_270: newRotation = R.drawable.tile_i_blue_0;
                    iTile_Player1_rotation = R.drawable.tile_i_blue_0;
                    break;
            }
        }
        else if(button == iTile_Player2)
        {
            switch(rotation){
                case R.drawable.tile_i_red_0: newRotation = R.drawable.tile_i_red_90;
                    iTile_Player2_rotation = R.drawable.tile_i_red_90;
                    break;
                case R.drawable.tile_i_red_90: newRotation = R.drawable.tile_i_red_180;
                    iTile_Player2_rotation = R.drawable.tile_i_red_180;
                    break;
                case R.drawable.tile_i_red_180: newRotation = R.drawable.tile_i_red_270;
                    iTile_Player2_rotation = R.drawable.tile_i_red_270;
                    break;
                case R.drawable.tile_i_red_270: newRotation = R.drawable.tile_i_red_0;
                    iTile_Player2_rotation = R.drawable.tile_i_red_0;
                    break;
            }
        }
        button.setImageResource(newRotation);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trakus);
        iTile_Player1 = (ImageButton)findViewById(R.id.button_Player1_I);
        lTile_Player1 = (ImageButton)findViewById(R.id.button_Player1_L);
        tTile_Player1 = (ImageButton)findViewById(R.id.button_Player1_T);
        iTile_Player2 = (ImageButton)findViewById(R.id.button_Player2_I);
        lTile_Player2 = (ImageButton)findViewById(R.id.button_Player2_L);
        tTile_Player2 = (ImageButton)findViewById(R.id.button_Player2_T);
        oGameBoard = (GridLayout)findViewById(R.id.gridGameBoard);


        oGameBoard.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                final int action = event.getAction();
                switch(action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        System.out.println("STARTED");
                        successfulDrop = false;
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        System.out.println("EXITED");
                        successfulDrop = false;
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        System.out.println("ENTERED");
                        successfulDrop = true;
                        break;
                    case DragEvent.ACTION_DROP:{
                        placeTile(event.getX(), event.getY(), draggingButton);
                        successfulDrop = true;
                        System.out.println("DROP");
                        return(true);
                    }
                    case DragEvent.ACTION_DRAG_ENDED:{
                        System.out.println("ENDED");
                        if(!successfulDrop){
                            rotate(draggingButton);
                        }
                        return(true);
                    }
                    default:
                        break;
                }
                return true;
            }});

        iTile_Player1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow_iTile_Player1 = new View.DragShadowBuilder(iTile_Player1);
                v.startDrag(data, shadow_iTile_Player1, null, 0);
                draggingButton = v.getId();
                return false;
            }
        });

        iTile_Player2.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent arg1) {
                 ClipData data = ClipData.newPlainText("", "");
                 View.DragShadowBuilder shadow_iTile_Player2 = new View.DragShadowBuilder(iTile_Player2);
                 v.startDrag(data, shadow_iTile_Player2, null, 0);
                 draggingButton = v.getId();
                 return false;
             }
        });

        lTile_Player1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow_lTile_Player1 = new View.DragShadowBuilder(lTile_Player1);
                v.startDrag(data, shadow_lTile_Player1, null, 0);
                draggingButton = v.getId();
                return false;
            }
        });

        lTile_Player2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow_lTile_Player2 = new View.DragShadowBuilder(lTile_Player2);
                v.startDrag(data, shadow_lTile_Player2, null, 0);
                draggingButton = v.getId();
                return false;
            }
        });

        tTile_Player1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow_tTile_Player1 = new View.DragShadowBuilder(tTile_Player1);
                v.startDrag(data, shadow_tTile_Player1, null, 0);
                draggingButton = v.getId();
                return false;
            }
        });

        tTile_Player2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow_tTile_Player2 = new View.DragShadowBuilder(tTile_Player2);
                v.startDrag(data, shadow_tTile_Player2, null, 0);
                draggingButton = v.getId();
                return false;
            }
        });



        this.oGameBoard = (GridLayout) this.findViewById(R.id.gridGameBoard);
        this.oGameBoard.getViewTreeObserver().addOnGlobalLayoutListener(this.SquareIfy());

    }


    private void tileConfig(int pos, int tileToUse){

        ImageButton currentButton;
        Tile currentTile;


        currentTile = tileList.get(pos);
        currentButton = buttonList.get(pos);
        currentButton.setImageResource(tileToUse);
        System.out.println(tileToUse);
        buttonList.set(pos, currentButton);
        tileList.set(pos, currentTile);
    }

    public void placeTile( float xPos, float yPos, Integer view){
        int width = Trakus.this.oGameBoard.getMeasuredWidth();
        int height = Trakus.this.oGameBoard.getMeasuredHeight();
        int cols = Trakus.this.oGameBoard.getColumnCount();
        int rows = Trakus.this.oGameBoard.getRowCount();

        double sizeX = (width/cols);
        double sizeY = (height/rows);
        int tileToUse;

        switch (view){
            case R.id.button_Player1_I: tileToUse = iTile_Player1_rotation;
                break;
            case R.id.button_Player1_L: tileToUse = lTile_Player1_rotation;
                break;
            case R.id.button_Player1_T: tileToUse = tTile_Player1_rotation;
                break;
            case R.id.button_Player2_I: tileToUse = iTile_Player2_rotation;
                break;
            case R.id.button_Player2_L: tileToUse = lTile_Player2_rotation;
                break;
            case R.id.button_Player2_T: tileToUse = tTile_Player2_rotation;
                break;
            default: tileToUse = R.drawable.tile;
                break;
        }

        if(xPos > 0 && xPos < sizeX){
            if(yPos > 0 && yPos < sizeY){
                System.out.println("Tile 0");
//                currentTile = tileList.get(0);
//                currentTile.setOwner(Tile.Owner.player_1);
//                currentButton = buttonList.get(0);
//                currentButton.setImageResource(tileToUse);
//                System.out.println(tileToUse);
//                buttonList.set(0, currentButton);
//                tileList.set(0, currentTile);
                tileConfig(0, tileToUse);
            }
            else if(yPos > sizeY && yPos < (sizeY*2)){
                System.out.println("Tile 6");
                tileConfig(6, tileToUse);
            }
            else if(yPos > (sizeY*2) && yPos < (sizeY*3)){
                System.out.println("Tile 12");
                tileConfig(12, tileToUse);
            }
            else if(yPos > (sizeY*3) && yPos < (sizeY*4)){
                System.out.println("Tile 18");
                tileConfig(18, tileToUse);
            }
            else if(yPos > (sizeY*4) && yPos < (sizeY*5)){
                System.out.println("Tile 24");
                tileConfig(24, tileToUse);
            }
            else if(yPos > (sizeY*5) && yPos < (sizeY*6)){
                System.out.println("Tile 30");
                tileConfig(30, tileToUse);
            }
        }
        else if(xPos > sizeX && xPos < (sizeX*2)){
            if(yPos > 0 && yPos < sizeY){
                System.out.println("Tile 1");
                tileConfig(1, tileToUse);
            }
            else if(yPos > sizeY && yPos < (sizeY*2)){
                System.out.println("Tile 7");
                tileConfig(7, tileToUse);
            }
            else if(yPos > (sizeY*2) && yPos < (sizeY*3)){
                System.out.println("Tile 13");
                tileConfig(13, tileToUse);
            }
            else if(yPos > (sizeY*3) && yPos < (sizeY*4)){
                System.out.println("Tile 19");
                tileConfig(19, tileToUse);
            }
            else if(yPos > (sizeY*4) && yPos < (sizeY*5)){
                System.out.println("Tile 25");
                tileConfig(25, tileToUse);
            }
            else if(yPos > (sizeY*5) && yPos < (sizeY*6)){
                System.out.println("Tile 31");
                tileConfig(31, tileToUse);
            }
        }
        else if(xPos > (sizeX*2) && xPos < (sizeX*3)){
            if(yPos > 0 && yPos < sizeY){
                System.out.println("Tile 2");
                tileConfig(2, tileToUse);
            }
            else if(yPos > sizeY && yPos < (sizeY*2)){
                System.out.println("Tile 8");
                tileConfig(8, tileToUse);
            }
            else if(yPos > (sizeY*2) && yPos < (sizeY*3)){
                System.out.println("Tile 14");
                tileConfig(14, tileToUse);
            }
            else if(yPos > (sizeY*3) && yPos < (sizeY*4)){
                System.out.println("Tile 20");
                tileConfig(20, tileToUse);
            }
            else if(yPos > (sizeY*4) && yPos < (sizeY*5)){
                System.out.println("Tile 26");
                tileConfig(26, tileToUse);
            }
            else if(yPos > (sizeY*5) && yPos < (sizeY*6)){
                System.out.println("Tile 32");
                tileConfig(32, tileToUse);
            }
        }
        else if(xPos > (sizeX*3) && xPos < (sizeX*4)){
            if(yPos > 0 && yPos < sizeY){
                System.out.println("Tile 3");
                tileConfig(3, tileToUse);
            }
            else if(yPos > sizeY && yPos < (sizeY*2)){
                System.out.println("Tile 9");
                tileConfig(9, tileToUse);
            }
            else if(yPos > (sizeY*2) && yPos < (sizeY*3)){
                System.out.println("Tile 15");
                tileConfig(15, tileToUse);
            }
            else if(yPos > (sizeY*3) && yPos < (sizeY*4)){
                System.out.println("Tile 21");
                tileConfig(21, tileToUse);
            }
            else if(yPos > (sizeY*4) && yPos < (sizeY*5)){
                System.out.println("Tile 27");
                tileConfig(27, tileToUse);
            }
            else if(yPos > (sizeY*5) && yPos < (sizeY*6)){
                System.out.println("Tile 33");
                tileConfig(33, tileToUse);
            }
        }
        else if(xPos > (sizeX*4) && xPos < (sizeX*5)){
            if(yPos > 0 && yPos < sizeY){
                System.out.println("Tile 4");
                tileConfig(4, tileToUse);
            }
            else if(yPos > sizeY && yPos < (sizeY*2)){
                System.out.println("Tile 10");
                tileConfig(10, tileToUse);
            }
            else if(yPos > (sizeY*2) && yPos < (sizeY*3)){
                System.out.println("Tile 16");
                tileConfig(16, tileToUse);
            }
            else if(yPos > (sizeY*3) && yPos < (sizeY*4)){
                System.out.println("Tile 22");
                tileConfig(22, tileToUse);
            }
            else if(yPos > (sizeY*4) && yPos < (sizeY*5)){
                System.out.println("Tile 28");
                tileConfig(28, tileToUse);
            }
            else if(yPos > (sizeY*5) && yPos < (sizeY*6)){
                System.out.println("Tile 34");
                tileConfig(34, tileToUse);
            }
        }
        else if(xPos > (sizeX*5) && xPos < (sizeX*6)){
            if(yPos > 0 && yPos < sizeY){
                System.out.println("Tile 5");
                tileConfig(5, tileToUse);
            }
            else if(yPos > sizeY && yPos < (sizeY*2)){
                System.out.println("Tile 11");
                tileConfig(11, tileToUse);
            }
            else if(yPos > (sizeY*2) && yPos < (sizeY*3)){
                System.out.println("Tile 17");
                tileConfig(17, tileToUse);
            }
            else if(yPos > (sizeY*3) && yPos < (sizeY*4)){
                System.out.println("Tile 23");
                tileConfig(23, tileToUse);
            }
            else if(yPos > (sizeY*4) && yPos < (sizeY*5)){
                System.out.println("Tile 29");
                tileConfig(29, tileToUse);
            }
            else if(yPos > (sizeY*5) && yPos < (sizeY*6)){
                System.out.println("Tile 35");
                tileConfig(35, tileToUse);
            }
        }
    }

    ViewTreeObserver.OnGlobalLayoutListener SquareIfy()
    {
        return new ViewTreeObserver.OnGlobalLayoutListener()
        {

            @Override
            public void onGlobalLayout()
            {
                int width = Trakus.this.oGameBoard.getMeasuredWidth();
                int height = Trakus.this.oGameBoard.getMeasuredHeight();
                int cols = Trakus.this.oGameBoard.getColumnCount();
                int rows = Trakus.this.oGameBoard.getRowCount();
                int tileCount = cols*rows;

                double sizeA = (width/cols);
                double sizeB = (height/rows);

                double smallestSize = (sizeA<sizeB?sizeA:sizeB);
                int smallestSizeInt = (int) Math.floor(smallestSize);



                for(int x=0;x<=tileCount-1;x++)
                    try {
                        ImageButton b = new ImageButton(Trakus.this);
                        buttonList.add(x, b);
                        b.setPadding(0, 0, 0, 0);
                        b.setImageResource(tile_blank);

                        GridLayout.LayoutParams lp = new GridLayout.LayoutParams();

                        lp.width = smallestSizeInt;
                        lp.height = smallestSizeInt;
                        lp.leftMargin = 0;
                        lp.rightMargin = 0;
                        lp.topMargin = 0;
                        lp.bottomMargin = 0;
                        b.setLayoutParams(lp);

                        Trakus.this.oGameBoard.addView(b);
                        Trakus.this.oGameBoard.getLayoutParams().width = smallestSizeInt * cols;
                        Trakus.this.oGameBoard.getLayoutParams().height = smallestSizeInt * rows;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }


                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
                {
                    Trakus.this.oGameBoard.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                else
                {
                    Trakus.this.oGameBoard.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }



            }
        };
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trakus, menu);
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


}
