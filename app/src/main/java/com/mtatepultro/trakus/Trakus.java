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



public class Trakus extends ActionBarActivity {

    // Keep all Images in array
    ImageButton iTile_Player1;
    ImageButton iTile_Player2;
    ImageButton lTile_Player1;
    ImageButton lTile_Player2;
    ImageButton tTile_Player1;
    ImageButton tTile_Player2;

    private GridLayout oGameBoard;
    private LinearLayout oGameBoardShell;

    private static Tile[] InitializeTiles(){
        Tile tiles[] = new Tile[36];
        for( int i = 0; i < 36; i++)
        {
            tiles[i] = new Tile(i, Tile.TileType.blank_Tile, Tile.Owner.open);
        }
        tiles[0].setTileType(Tile.TileType.l_Tile);
        tiles[35].setTileType(Tile.TileType.l_Tile);
        return tiles;
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
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DROP:{
                        System.out.println(event.getX() + " " + event.getY());

                        return(true);
                    }
                    case DragEvent.ACTION_DRAG_ENDED:{

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
                return false;
            }
        });

        iTile_Player2.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent arg1) {
                 ClipData data = ClipData.newPlainText("", "");
                 View.DragShadowBuilder shadow_iTile_Player2 = new View.DragShadowBuilder(iTile_Player2);
                 v.startDrag(data, shadow_iTile_Player2, null, 0);
                 return false;
             }
        });

        lTile_Player1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow_lTile_Player1 = new View.DragShadowBuilder(lTile_Player1);
                v.startDrag(data, shadow_lTile_Player1, null, 0);
                return false;
            }
        });

        lTile_Player2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow_lTile_Player2 = new View.DragShadowBuilder(lTile_Player2);
                v.startDrag(data, shadow_lTile_Player2, null, 0);
                return false;
            }
        });

        tTile_Player1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow_tTile_Player1 = new View.DragShadowBuilder(tTile_Player1);
                v.startDrag(data, shadow_tTile_Player1, null, 0);
                return false;
            }
        });

        tTile_Player2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent arg1) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow_tTile_Player2 = new View.DragShadowBuilder(tTile_Player2);
                v.startDrag(data, shadow_tTile_Player2, null, 0);
                return false;
            }
        });



        this.oGameBoard = (GridLayout) this.findViewById(R.id.gridGameBoard);
        this.oGameBoard.getViewTreeObserver().addOnGlobalLayoutListener(this.SquareIfy());

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
                {
                    try
                    {
                        ImageButton b = new ImageButton(Trakus.this);
                        //b.setText(String.valueOf(x));
                        b.setPadding(0, 0, 0, 0);
                        b.setImageResource(R.drawable.tile_blank);


                        GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
                        lp.width = smallestSizeInt;
                        lp.height = smallestSizeInt;
                        lp.leftMargin=0;
                        lp.rightMargin=0;
                        lp.topMargin=0;
                        lp.bottomMargin=0;
                        b.setLayoutParams(lp);

                        Trakus.this.oGameBoard.addView(b);
                        Trakus.this.oGameBoard.getLayoutParams().width=smallestSizeInt * cols;
                        Trakus.this.oGameBoard.getLayoutParams().height=smallestSizeInt * rows;
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
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
