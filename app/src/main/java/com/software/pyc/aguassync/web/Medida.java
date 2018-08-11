package com.software.pyc.aguassync.web;

import com.software.pyc.aguassync.utils.Constantes;

/**
 * Esta clase representa una medida individual de cada registro de la base de datos
 * se usa para mapear la consulta de webService, los registros se deben llamar con
 * en la consulta
 */

public class Medida {


    public String idMedida;
    public String ruta;
    public String orden;
    public String codigo;
    public String nombre;
    public String medidor;
    public String partida;
    public String estadoAnterior;
    public String estadoActual;
    public String fechaActualizacion;
    public String usuario;


    public Medida(String idMedida, String ruta, String orden, String codigo, String nombre, String medidor, String partida, String estadoAnterior, String estadoActual, String fechaActualizacion, String usuario) {
        this.idMedida = idMedida;
        this.ruta = ruta;
        this.orden = orden;
        this.codigo = codigo;
        this.nombre = nombre;
        this.medidor = medidor;
        this.partida = partida;
        this.estadoAnterior = estadoAnterior;
        this.estadoActual = estadoActual;
        this.fechaActualizacion = fechaActualizacion;
        this.usuario = usuario;
    }
}
