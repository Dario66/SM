package cesena.sm.sistmmulti;

/**
 * Created by Dario5 on 22/02/2015.
 */

public class Student {
    int _id;
    String _testo;
    int _level;
    String _testo2;

    public Student() {

    }

    public Student(int id, String productname, int level,String categoria) {
        this._id = id;
        this._testo= productname;
        this._level= level;
        this._testo2=categoria;
    }

    public Student(String productname, int level,String categoria) {
        this._testo = productname;
        this._level = level;
        this._testo2=categoria;
    }
    public Student(int id,String productname) {
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

    public void setLevel2(int level) {
        this._level = level;
    }

    public int getLevel2() {
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
        return /*this._id + ". " + */"Nome: "+this._testo  +" : "+ "Punteggio : [ "  + this._level +  " ]";
    }
}
