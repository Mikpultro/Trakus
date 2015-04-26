package com.mtatepultro.trakus;

import java.util.List;

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

    public boolean isEmpty() {
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
    public boolean isPlayable( List<Tile> tileList, int pos) {
        boolean playable = false;
        Tile northTile = SidePlayable((pos-6), tileList);
        Tile southTile = SidePlayable((pos+6), tileList);
        Tile eastTile = SidePlayable((pos+1), tileList);
        Tile westTile = SidePlayable((pos-1), tileList);

        if( northTile.isSideSouthPlayable() ||
            southTile.isSideNorthPlayable() ||
            eastTile.isSideWestPlayable() ||
            westTile.isSideEastPlayable() ){
            playable = true;


        }
        return playable;
    }


    public Tile SidePlayable(int pos, List<Tile> tileList) {
        Tile tileToReturn = tileList.get(0);
        if(this.SideOnBoard(pos)){
            if(this.SideOpen(tileList.get(pos))){
                tileToReturn = tileList.get(pos);
            }
        }
        return tileToReturn;
    }

    public boolean SideOpen(Tile tile){
        boolean empty = false;
        if(tile.isEmpty())
        {
            empty = true;
        }
        return empty;
    }

    public boolean SideOnBoard(int pos){
        boolean onBoard = false;
        if(pos >= 0 && pos <= 35)
        {
            onBoard = true;
        }
        return onBoard;
    }
}

