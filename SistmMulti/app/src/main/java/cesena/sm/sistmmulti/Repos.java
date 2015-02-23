package cesena.sm.sistmmulti;

/**
 * Created by Dario5 on 22/02/2015.
 */

import android.graphics.Bitmap;




public class Repos {
    int _id;
    String _testo;
    String _level;
    String _testo2;

    public Repos() {

    }

    public Repos(int id, String productname, String level,String categoria) {
        this._id = id;
        this._testo= productname;
        this._level= level;
        this._testo2=categoria;
    }

    public Repos(String productname, String level,String categoria) {
        this._testo = productname;
        this._level = level;
        this._testo2=categoria;
    }
    public Repos(int id,String productname) {
        this._testo = productname;
        this._id = id;
        this._testo=productname;
    }

    public void setCOD2(int id) {
        this._id = id;
    }

    public int getCOD2() {
        return this._id;
    }

    public void setTesto2(String productname) {
        this._testo = productname;
    }

    public String getTesto2() {
        return this._testo;
    }

    public void setLevel2(String level) {
        this._level = level;
    }

    public String getLevel2() {
        return this._level;
    }
    public void setCategory2(String categoria) {
        this._testo2 = categoria;
    }

    public String getCategory2() {
        return this._testo2;
    }

    @Override
    public String toString() {
        return /*this._id + ". " + */" Barriera: ("+this._testo  +") : "+ "Descrizione :  ("  + this._level +  ") "+"Gps: ("+this._testo2+").<br>"+"" +
                "";
    }
}
