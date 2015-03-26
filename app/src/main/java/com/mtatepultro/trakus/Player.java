package com.mtatepultro.trakus;

/**
 * Created by Mikpultro1 on 3/2/15.
 */
public class Player {

    // ---- Variables ----
    private int mI_TilesLeft = 5;
    private int mL_TilesLeft = 8;
    private int mT_TilesLeft = 3;

    private Boolean mPowerPlayActive = true;
    private Boolean mReDoActive = true;
    private Boolean mScorchedEarthActive = true;


    // ---- Methods ----
    public void playI_Tile(){

    }

    public void playL_Tile(){

    }

    public void playT_Tile(){

    }

    public void placeTile(){

    }

    public void rotateTile(){

    }

    public void lockTile(){

    }

    public void claimTile(int tileID){

    }


    public void playPowerPlay() {
        if(this.mPowerPlayActive){
            this.mPowerPlayActive = false;
            // TODO: Play Power Play
        }
        else{
            // You have already used your PowerPlay!
        }
    }

    public void playReDo() {
        if(this.mReDoActive){
            this.mReDoActive = false;
            // TODO: Play ReDo
        }
        else{
            // You have already used your ReDo!
        }
    }

    public void playScorchedEarth() {
        if(this.mScorchedEarthActive){
            this.mScorchedEarthActive = false;
            // TODO: Play ScorchedEarth
        }
        else{
            // You have already used your ScorchedEarth!
        }
    }

}