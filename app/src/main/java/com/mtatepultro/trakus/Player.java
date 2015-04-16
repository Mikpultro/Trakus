package com.mtatepultro.trakus;

/**
 * Created by Mikpultro1 on 3/2/15.
 */
public class Player {

    // ---- Variables ----
    private int mI_TilesLeft; // 5
    private int mL_TilesLeft; // 8
    private int mT_TilesLeft; // 3

    private Boolean mPowerPlayActive = true;
    private Boolean mReDoActive = true;
    private Boolean mScorchedEarthActive = true;

    // ---- Constructor ----
    public Player(int i, int l, int t){
        this.setI_TilesLeft(i);
        this.setL_TilesLeft(l);
        this.setT_TilesLeft(t);
    }


    // ---- Methods ----
    public void playI_Tile(){
        int newCount = getI_TilesLeft() - 1;
        setI_TilesLeft(newCount);
        System.out.println("New I Tile Count: "+Integer.toString(this.getI_TilesLeft()));
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

    public int getI_TilesLeft() {
        return mI_TilesLeft;
    }

    public void setI_TilesLeft(int i_TilesLeft) {
        mI_TilesLeft = i_TilesLeft;
    }

    public int getL_TilesLeft() {
        return mL_TilesLeft;
    }

    public void setL_TilesLeft(int l_TilesLeft) {
        mL_TilesLeft = l_TilesLeft;
    }

    public int getT_TilesLeft() {
        return mT_TilesLeft;
    }

    public void setT_TilesLeft(int t_TilesLeft) {
        mT_TilesLeft = t_TilesLeft;
    }

    public Boolean getPowerPlayActive() {
        return mPowerPlayActive;
    }

    public void setPowerPlayActive(Boolean powerPlayActive) {
        mPowerPlayActive = powerPlayActive;
    }

    public Boolean getReDoActive() {
        return mReDoActive;
    }

    public void setReDoActive(Boolean reDoActive) {
        mReDoActive = reDoActive;
    }

    public Boolean getScorchedEarthActive() {
        return mScorchedEarthActive;
    }

    public void setScorchedEarthActive(Boolean scorchedEarthActive) {
        mScorchedEarthActive = scorchedEarthActive;
    }

}