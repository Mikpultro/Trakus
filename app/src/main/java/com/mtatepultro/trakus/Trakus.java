package com.mtatepultro.trakus;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;



public class Trakus extends ActionBarActivity {

    // Keep all Images in array

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
                        Button b = new Button(Trakus.this);
                        b.setText(String.valueOf(x));
                        b.setPadding(0, 0, 0, 0);

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
