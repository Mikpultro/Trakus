package com.mtatepultro.trakus;

/**
 * Created by Mikpultro1 on 3/2/15.
 */
public class Tile {

    // ---- Variables ----
    public Integer mTileID;
    public boolean mSideNorthPlayable = false;
    public boolean mSideSouthPlayable = false;
    public boolean mSideEastPlayable = false;
    public boolean mSideWestPlayable = false;
    public boolean mIsTileEmpty = true;
    public enum TileType{
        i_Tile,
        l_Tile,
        t_Tile,
        blank_Tile
    }
    public enum Owner{
        player_1,
        player_2,
        open
    }


    TileType mTileType;
    Owner mOwner;

    public Tile(Integer tileID, TileType tt, Owner owner){
        this.setTileID(tileID);
        if(tt == TileType.i_Tile){
            this.setTileType(tt);

        }
        else if(tt == TileType.l_Tile){
            this.setTileType(tt);
        }
        else if(tt == TileType.t_Tile){
            this.setTileType(tt);
        }
        else if(tt == TileType.blank_Tile){
            this.setTileType(tt);
        }

    }

    // GETTERS
    public Integer getTileID() {
        return mTileID;
    }

    public boolean isSideNorthPlayable() {

        return mSideNorthPlayable;
    }

    public boolean isSideSouthPlayable() {
        return mSideSouthPlayable;
    }

    public boolean isSideEastPlayable() {
        return mSideEastPlayable;
    }

    public boolean isSideWestPlayable() {
        return mSideWestPlayable;
    }

    public boolean isTileEmpty() {
        return mIsTileEmpty;
    }

    public TileType getTileType() {
        return mTileType;
    }

    public Owner getOwner(){ return mOwner; }

    // SETTERS

    public void setTileID(Integer tileID) {
        mTileID = tileID;
    }

    public void setSideNorthPlayable(boolean sideNorthPlayable) {
        mSideNorthPlayable = sideNorthPlayable;
    }

    public void setSideSouthPlayable(boolean sideSouthPlayable) {
        mSideSouthPlayable = sideSouthPlayable;
    }

    public void setSideEastPlayable(boolean sideEastPlayable) {
        mSideEastPlayable = sideEastPlayable;
    }

    public void setSideWestPlayable(boolean sideWestPlayable) {
        mSideWestPlayable = sideWestPlayable;
    }

    public void setTileEmpty(boolean isTileEmpty) {
        mIsTileEmpty = isTileEmpty;
    }

    public void isUsed() {
        mIsTileEmpty = false;
    }

    public void setTileType(TileType tileType) {
        this.mTileType = tileType;
    }

    public void setOwner(Owner owner) { this.mOwner = owner; }

    // ---- Methods ----
//    public boolean SidePlayable() {
//        boolean playable = false;
//        if(this.SideOpen() && this.SideOnBoard()){
//            playable = true;
//        }
//        return playable;
//    }

    public void config(int tile){

    }

    public boolean SideOpen(){
        // TODO: SideOpen();

        return true;
    }

    public boolean SideOnBoard(){
        // TODO: SideOnBoard();

        return true;
    }
}

