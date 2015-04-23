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
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    TextView iCounter_Player1;
    TextView lCounter_Player1;
    TextView tCounter_Player1;
    TextView iCounter_Player2;
    TextView lCounter_Player2;
    TextView tCounter_Player2;

    Integer iTile_Player1_rotation = R.drawable.tile_i_blue_0;
    Integer lTile_Player1_rotation = R.drawable.tile_l_blue_0;
    Integer tTile_Player1_rotation = R.drawable.tile_t_blue_0;
    Integer iTile_Player2_rotation = R.drawable.tile_i_red_180;
    Integer lTile_Player2_rotation = R.drawable.tile_l_red_180;
    Integer tTile_Player2_rotation = R.drawable.tile_t_red_180;

    private static Integer draggingButton;
    private static Integer numTiles = 36;

    private boolean successfulDrop = false;

    private GridLayout oGameBoard;
    private LinearLayout oGameBoardShell;

    Player playerOne = new Player(5, 9, 3);
    Player playerTwo = new Player(5, 9, 3);;
    public String numITilesP1;


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
        else if(button == lTile_Player1)
        {
            switch(rotation){
                case R.drawable.tile_l_blue_0: newRotation = R.drawable.tile_l_blue_90;
                    lTile_Player1_rotation = R.drawable.tile_l_blue_90;
                    break;
                case R.drawable.tile_l_blue_90: newRotation = R.drawable.tile_l_blue_180;
                    lTile_Player1_rotation = R.drawable.tile_l_blue_180;
                    break;
                case R.drawable.tile_l_blue_180: newRotation = R.drawable.tile_l_blue_270;
                    lTile_Player1_rotation = R.drawable.tile_l_blue_270;
                    break;
                case R.drawable.tile_l_blue_270: newRotation = R.drawable.tile_l_blue_0;
                    lTile_Player1_rotation = R.drawable.tile_l_blue_0;
                    break;
            }
        }
        else if(button == lTile_Player2)
        {
            switch(rotation){
                case R.drawable.tile_l_red_0: newRotation = R.drawable.tile_l_red_90;
                    lTile_Player2_rotation = R.drawable.tile_l_red_90;
                    break;
                case R.drawable.tile_l_red_90: newRotation = R.drawable.tile_l_red_180;
                    lTile_Player2_rotation = R.drawable.tile_l_red_180;
                    break;
                case R.drawable.tile_l_red_180: newRotation = R.drawable.tile_l_red_270;
                    lTile_Player2_rotation = R.drawable.tile_l_red_270;
                    break;
                case R.drawable.tile_l_red_270: newRotation = R.drawable.tile_l_red_0;
                    lTile_Player2_rotation = R.drawable.tile_l_red_0;
                    break;
            }
        }
        else if(button == tTile_Player1)
        {
            switch(rotation){
                case R.drawable.tile_t_blue_0: newRotation = R.drawable.tile_t_blue_90;
                    tTile_Player1_rotation = R.drawable.tile_t_blue_90;
                    break;
                case R.drawable.tile_t_blue_90: newRotation = R.drawable.tile_t_blue_180;
                    tTile_Player1_rotation = R.drawable.tile_t_blue_180;
                    break;
                case R.drawable.tile_t_blue_180: newRotation = R.drawable.tile_t_blue_270;
                    tTile_Player1_rotation = R.drawable.tile_t_blue_270;
                    break;
                case R.drawable.tile_t_blue_270: newRotation = R.drawable.tile_t_blue_0;
                    tTile_Player1_rotation = R.drawable.tile_t_blue_0;
                    break;
            }
        }
        else if(button == tTile_Player2)
        {
            switch(rotation){
                case R.drawable.tile_t_red_0: newRotation = R.drawable.tile_t_red_90;
                    tTile_Player2_rotation = R.drawable.tile_t_red_90;
                    break;
                case R.drawable.tile_t_red_90: newRotation = R.drawable.tile_t_red_180;
                    tTile_Player2_rotation = R.drawable.tile_t_red_180;
                    break;
                case R.drawable.tile_t_red_180: newRotation = R.drawable.tile_t_red_270;
                    tTile_Player2_rotation = R.drawable.tile_t_red_270;
                    break;
                case R.drawable.tile_t_red_270: newRotation = R.drawable.tile_t_red_0;
                    tTile_Player2_rotation = R.drawable.tile_t_red_0;
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

        iCounter_Player1 = (TextView)findViewById(R.id.count_Player1_I);
        lCounter_Player1 = (TextView)findViewById(R.id.count_Player1_L);
        tCounter_Player1 = (TextView)findViewById(R.id.count_Player1_T);
        iCounter_Player2 = (TextView)findViewById(R.id.count_Player2_I);
        lCounter_Player2 = (TextView)findViewById(R.id.count_Player2_L);
        tCounter_Player2 = (TextView)findViewById(R.id.count_Player2_T);

        iCounter_Player1.setText(Integer.toString(playerOne.getI_TilesLeft()));
        lCounter_Player1.setText(Integer.toString(playerOne.getL_TilesLeft()));
        tCounter_Player1.setText(Integer.toString(playerOne.getT_TilesLeft()));
        iCounter_Player2.setText(Integer.toString(playerTwo.getI_TilesLeft()));
        lCounter_Player2.setText(Integer.toString(playerTwo.getL_TilesLeft()));
        tCounter_Player2.setText(Integer.toString(playerTwo.getT_TilesLeft()));

        RotateAnimation rotate = (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotate);

        iCounter_Player1.setAnimation(rotate);
        lCounter_Player1.setAnimation(rotate);
        tCounter_Player1.setAnimation(rotate);
        iCounter_Player2.setAnimation(rotate);
        lCounter_Player2.setAnimation(rotate);
        tCounter_Player2.setAnimation(rotate);



        oGameBoard.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
//                        System.out.println("STARTED");
                        successfulDrop = false;
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
//                        System.out.println("EXITED");
                        successfulDrop = false;
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
//                        System.out.println("ENTERED");
                        successfulDrop = true;
                        break;
                    case DragEvent.ACTION_DROP: {
                        placeTile(event.getX(), event.getY(), draggingButton);
//                        System.out.println("X="+event.getX() + " Y=" + event.getY());
                        successfulDrop = true;
//                        System.out.println("DROP");
                        return (true);
                    }
                    case DragEvent.ACTION_DRAG_ENDED: {
//                        System.out.println("ENDED");
                        if (!successfulDrop) {
                            rotate(draggingButton);
                        }
                        return (true);
                    }
                    default:
                        break;
                }
                return true;
            }
        });

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

        if(currentTile.isEmpty()){
            currentTile.isUsed();
            currentButton.setImageResource(tileToUse);
            setPlayableTiles(pos);
            buttonList.set(pos, currentButton);
            tileList.set(pos, currentTile);
        }
        else{
            System.out.println("TILE OCCUPIED");
        }

    }

    public void setPlayableTiles(int pos){
        Tile currentTile;
        Integer rotation;
        currentTile = tileList.get(pos);
        switch(draggingButton){

            case R.id.button_Player1_I:
                rotation = iTile_Player1_rotation;
                switch(rotation){
                    case R.drawable.tile_i_blue_0:
                    case R.drawable.tile_i_blue_180:
                        currentTile.setSideNorthPlayable(true);
                        currentTile.setSideSouthPlayable(true);
                        System.out.println((pos-6)+ " " +(pos+6));
                        break;
                    case R.drawable.tile_i_blue_90:
                    case R.drawable.tile_i_blue_270:
                        currentTile.setSideEastPlayable(true);
                        currentTile.setSideWestPlayable(true);
                        break;
                }
                break;

            case R.id.button_Player2_I:
                rotation = iTile_Player2_rotation;
                switch(rotation){
                    case R.drawable.tile_i_red_0:
                    case R.drawable.tile_i_red_180:
                        currentTile.setSideNorthPlayable(true);
                        currentTile.setSideSouthPlayable(true);
                        break;
                    case R.drawable.tile_i_red_90:
                    case R.drawable.tile_i_red_270:
                        currentTile.setSideEastPlayable(true);
                        currentTile.setSideWestPlayable(true);
                        break;
                }
                break;

            case R.id.button_Player1_L:
                rotation = lTile_Player1_rotation;

                break;

            case R.id.button_Player2_L:
                rotation = lTile_Player2_rotation;

                break;

            case R.id.button_Player1_T:
                rotation = tTile_Player1_rotation;

                break;

            case R.id.button_Player2_T:
                rotation = tTile_Player2_rotation;

                break;
        }

    }

    public int tileToUse(int view) {
        int returnTile = 0;
        switch (view) {
            case R.id.button_Player1_I:
                returnTile = iTile_Player1_rotation;
                if (playerOne.getI_TilesLeft() > 0 ) {
                    playerOne.playI_Tile();
                    iCounter_Player1.setText(Integer.toString(playerOne.getI_TilesLeft()));
                }
                break;
            case R.id.button_Player1_L:
                returnTile = lTile_Player1_rotation;
                if (playerOne.getL_TilesLeft() > 0) {
                    playerOne.playL_Tile();
                    lCounter_Player1.setText(Integer.toString(playerOne.getL_TilesLeft()));
                }
                break;
            case R.id.button_Player1_T:
                returnTile = tTile_Player1_rotation;
                if (playerOne.getT_TilesLeft() > 0) {
                    playerOne.playT_Tile();
                    tCounter_Player1.setText(Integer.toString(playerOne.getT_TilesLeft()));
                }
                break;
            case R.id.button_Player2_I:
                returnTile = iTile_Player2_rotation;
                if (playerTwo.getI_TilesLeft() > 0) {
                    playerTwo.playI_Tile();
                    iCounter_Player2.setText(Integer.toString(playerTwo.getI_TilesLeft()));
                }
                break;
            case R.id.button_Player2_L:
                returnTile = lTile_Player2_rotation;
                if (playerTwo.getL_TilesLeft() > 0) {
                    playerTwo.playL_Tile();
                    lCounter_Player2.setText(Integer.toString(playerTwo.getL_TilesLeft()));
                }
                break;
            case R.id.button_Player2_T:
                returnTile = tTile_Player2_rotation;
                if (playerTwo.getT_TilesLeft() > 0) {
                    playerTwo.playT_Tile();
                    tCounter_Player2.setText(Integer.toString(playerTwo.getT_TilesLeft()));
                }
                break;
            default:
                returnTile = R.drawable.tile;
                break;
        }
        return returnTile;
    }

    public void placeTile( float xPos, float yPos, Integer view) {
        int width = Trakus.this.oGameBoard.getMeasuredWidth();
        int height = Trakus.this.oGameBoard.getMeasuredHeight();
        int cols = Trakus.this.oGameBoard.getColumnCount();
        int rows = Trakus.this.oGameBoard.getRowCount();

        double sizeX = (width / cols);
        double sizeY = (height / rows);
        int tileUsed = 0;
        if (xPos > 0 && xPos < sizeX) {
            if (yPos > 0 && yPos < sizeY) {
                System.out.println("Tile 0");
                if (tileList.get(0).isEmpty()){
                    tileConfig(0, tileToUse(view));
                }

            } else if (yPos > sizeY && yPos < (sizeY * 2)) {
                System.out.println("Tile 6");
                if (tileList.get(6).isEmpty()){
                    tileConfig(6, tileToUse(view));
                }
            } else if (yPos > (sizeY * 2) && yPos < (sizeY * 3)) {
                System.out.println("Tile 12");
                if (tileList.get(12).isEmpty()){
                    tileConfig(12, tileToUse(view));
                }
            } else if (yPos > (sizeY * 3) && yPos < (sizeY * 4)) {
                System.out.println("Tile 18");
                if (tileList.get(18).isEmpty()){
                    tileConfig(18, tileToUse(view));
                }
            } else if (yPos > (sizeY * 4) && yPos < (sizeY * 5)) {
                System.out.println("Tile 24");
                if (tileList.get(24).isEmpty()){
                    tileConfig(24, tileToUse(view));
                }
            } else if (yPos > (sizeY * 5) && yPos < (sizeY * 6)) {
                System.out.println("Tile 30");
                if (tileList.get(30).isEmpty()){
                    tileConfig(30, tileToUse(view));
                }
            }
        } else if (xPos > sizeX && xPos < (sizeX * 2)) {
            if (yPos > 0 && yPos < sizeY) {
                System.out.println("Tile 1");
                if (tileList.get(1).isEmpty()){
                    tileConfig(1, tileToUse(view));
                }
            } else if (yPos > sizeY && yPos < (sizeY * 2)) {
                System.out.println("Tile 7");
                if (tileList.get(7).isEmpty()){
                    tileConfig(7, tileToUse(view));
                }
            } else if (yPos > (sizeY * 2) && yPos < (sizeY * 3)) {
                System.out.println("Tile 13");
                if (tileList.get(13).isEmpty()){
                    tileConfig(13, tileToUse(view));
                }
            } else if (yPos > (sizeY * 3) && yPos < (sizeY * 4)) {
                System.out.println("Tile 19");
                if (tileList.get(19).isEmpty()){
                    tileConfig(19, tileToUse(view));
                }
            } else if (yPos > (sizeY * 4) && yPos < (sizeY * 5)) {
                System.out.println("Tile 25");
                if (tileList.get(25).isEmpty()){
                    tileConfig(25, tileToUse(view));
                }
            } else if (yPos > (sizeY * 5) && yPos < (sizeY * 6)) {
                System.out.println("Tile 31");
                if (tileList.get(31).isEmpty()){
                    tileConfig(31, tileToUse(view));
                }
            }
        } else if (xPos > (sizeX * 2) && xPos < (sizeX * 3)) {
            if (yPos > 0 && yPos < sizeY) {
                System.out.println("Tile 2");
                if (tileList.get(2).isEmpty()){
                    tileConfig(2, tileToUse(view));
                }
            } else if (yPos > sizeY && yPos < (sizeY * 2)) {
                System.out.println("Tile 8");
                if (tileList.get(8).isEmpty()){
                    tileConfig(8, tileToUse(view));
                }
            } else if (yPos > (sizeY * 2) && yPos < (sizeY * 3)) {
                System.out.println("Tile 14");
                if (tileList.get(14).isEmpty()){
                    tileConfig(14, tileToUse(view));
                }
            } else if (yPos > (sizeY * 3) && yPos < (sizeY * 4)) {
                System.out.println("Tile 20");
                if (tileList.get(20).isEmpty()){
                    tileConfig(20, tileToUse(view));
                }
            } else if (yPos > (sizeY * 4) && yPos < (sizeY * 5)) {
                System.out.println("Tile 26");
                if (tileList.get(26).isEmpty()){
                    tileConfig(26, tileToUse(view));
                }
            } else if (yPos > (sizeY * 5) && yPos < (sizeY * 6)) {
                System.out.println("Tile 32");
                if (tileList.get(32).isEmpty()){
                    tileConfig(32, tileToUse(view));
                }
            }
        } else if (xPos > (sizeX * 3) && xPos < (sizeX * 4)) {
            if (yPos > 0 && yPos < sizeY) {
                System.out.println("Tile 3");
                if (tileList.get(3).isEmpty()){
                    tileConfig(3, tileToUse(view));
                }
            } else if (yPos > sizeY && yPos < (sizeY * 2)) {
                System.out.println("Tile 9");
                if (tileList.get(9).isEmpty()){
                    tileConfig(9, tileToUse(view));
                }
            } else if (yPos > (sizeY * 2) && yPos < (sizeY * 3)) {
                System.out.println("Tile 15");
                if (tileList.get(15).isEmpty()){
                    tileConfig(15, tileToUse(view));
                }
            } else if (yPos > (sizeY * 3) && yPos < (sizeY * 4)) {
                System.out.println("Tile 21");
                if (tileList.get(21).isEmpty()){
                    tileConfig(21, tileToUse(view));
                }
            } else if (yPos > (sizeY * 4) && yPos < (sizeY * 5)) {
                System.out.println("Tile 27");
                if (tileList.get(27).isEmpty()){
                    tileConfig(27, tileToUse(view));
                }
            } else if (yPos > (sizeY * 5) && yPos < (sizeY * 6)) {
                System.out.println("Tile 33");
                if (tileList.get(33).isEmpty()){
                    tileConfig(33, tileToUse(view));
                }
            }
        } else if (xPos > (sizeX * 4) && xPos < (sizeX * 5)) {
            if (yPos > 0 && yPos < sizeY) {
                System.out.println("Tile 4");
                if (tileList.get(4).isEmpty()){
                    tileConfig(4, tileToUse(view));
                }
            } else if (yPos > sizeY && yPos < (sizeY * 2)) {
                System.out.println("Tile 10");
                if (tileList.get(10).isEmpty()){
                    tileConfig(10, tileToUse(view));
                }
            } else if (yPos > (sizeY * 2) && yPos < (sizeY * 3)) {
                System.out.println("Tile 16");
                if (tileList.get(16).isEmpty()){
                    tileConfig(16, tileToUse(view));
                }
            } else if (yPos > (sizeY * 3) && yPos < (sizeY * 4)) {
                System.out.println("Tile 22");
                if (tileList.get(22).isEmpty()){
                    tileConfig(22, tileToUse(view));
                }
            } else if (yPos > (sizeY * 4) && yPos < (sizeY * 5)) {
                System.out.println("Tile 28");
                if (tileList.get(28).isEmpty()){
                    tileConfig(28, tileToUse(view));
                }
            } else if (yPos > (sizeY * 5) && yPos < (sizeY * 6)) {
                System.out.println("Tile 34");
                if (tileList.get(34).isEmpty()){
                    tileConfig(34, tileToUse(view));
                }
            }
        } else if (xPos > (sizeX * 5) && xPos < (sizeX * 6)) {
            if (yPos > 0 && yPos < sizeY) {
                System.out.println("Tile 5");
                if (tileList.get(5).isEmpty()){
                    tileConfig(5, tileToUse(view));
                }
            } else if (yPos > sizeY && yPos < (sizeY * 2)) {
                System.out.println("Tile 11");
                if (tileList.get(11).isEmpty()){
                    tileConfig(11, tileToUse(view));
                }
            } else if (yPos > (sizeY * 2) && yPos < (sizeY * 3)) {
                System.out.println("Tile 17");
                if (tileList.get(17).isEmpty()){
                    tileConfig(17, tileToUse(view));
                }
            } else if (yPos > (sizeY * 3) && yPos < (sizeY * 4)) {
                System.out.println("Tile 23");
                if (tileList.get(23).isEmpty()){
                    tileConfig(23, tileToUse(view));
                }
            } else if (yPos > (sizeY * 4) && yPos < (sizeY * 5)) {
                System.out.println("Tile 29");
                if (tileList.get(29).isEmpty()){
                    tileConfig(29, tileToUse(view));
                }
            } else if (yPos > (sizeY * 5) && yPos < (sizeY * 6)) {
                System.out.println("Tile 35");
                if (tileList.get(35).isEmpty()){
                    tileConfig(35, tileToUse(view));
                }
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
                draggingButton = R.id.button_Player1_L;
                placeTile(112, 112, R.id.button_Player1_L);
                draggingButton = R.id.button_Player2_L;
                placeTile(1600, 1200, R.id.button_Player2_L);


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
