package com.software.pyc.aguassync.provider;


/**
 * Created by pablo on 2/8/2018.
 */

public class ConsultaMedida {
    String where;
    String orderBy;
    String orderByMetodo="desc";
    String ruta;


    public void addWhereAnd(String w){
        this.where = this.ruta+" and "+w;
    }

    public void addOrderBy(String o){
        this.orderBy = o;
    }

    public ConsultaMedida() {
        this.where = null;
        this.orderBy = null;
    }

    public ConsultaMedida(String where) {
        this.where = where;
    }

    public ConsultaMedida(String where, String orderBy) {
        this.where = where;
        setOrderBy(orderBy);
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
        this.where = ruta;
    }

    public String getOrderByMetodo() {
        return orderByMetodo;
    }

    public void setOrderByMetodo(String orderByMetodo) {
        this.orderByMetodo = orderByMetodo;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy+" "+this.getOrderByMetodo();
    }
}
