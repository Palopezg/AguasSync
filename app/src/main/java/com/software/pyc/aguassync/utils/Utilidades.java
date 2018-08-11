package com.software.pyc.aguassync.utils;


import android.database.Cursor;
import android.os.Build;
import android.util.Log;

import com.software.pyc.aguassync.provider.ContractMedida;

import org.json.JSONException;
import org.json.JSONObject;

public class Utilidades {
    // Indices para las columnas indicadas en la proyección
    public static final int COLUMNA_ID = 0;
    public static final int COLUMNA_ID_REMOTA = 0;
    public static final int COLUMNA_RUTA = 1;
    public static final int COLUMNA_ORDEN = 2;
    public static final int COLUMNA_CODIGO = 3;
    public static final int COLUMNA_NOMBRE = 4;
    public static final int COLUMNA_MEDIDOR = 5;
    public static final int COLUMNA_PARTIDA = 6;
    public static final int COLUMNA_ESTADO_ANT = 7;
    public static final int COLUMNA_ESTADO_ACT = 8;
    public static final int COLUMNA_FECHA_ACT= 9;
    public static final int COLUMNA_USUARIO = 10;


    /**
     * Determina si la aplicación corre en versiones superiores o iguales
     * a Android LOLLIPOP
     *
     * @return booleano de confirmación
     */
    public static boolean materialDesign() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Copia los datos de un gasto almacenados en un cursor hacia un
     * JSONObject
     *
     * @param c cursor
     * @return objeto jason
     */
    public static JSONObject deCursorAJSONObject(Cursor c) {
        JSONObject jObject = new JSONObject();
        String ruta;
        String orden;
        String codigo;
        String nombre;
        String medidor;
        String partida;
        String estado_ant;
        String estado_act;
        String fecha_act;
        String usuario;

        ruta = c.getString(COLUMNA_RUTA);
        orden = c.getString(COLUMNA_ORDEN);
        codigo = c.getString(COLUMNA_CODIGO);
        nombre = c.getString(COLUMNA_NOMBRE);
        medidor = c.getString(COLUMNA_MEDIDOR);
        partida = c.getString(COLUMNA_PARTIDA);
        estado_ant = c.getString(COLUMNA_ESTADO_ANT);
        estado_act = c.getString(COLUMNA_ESTADO_ACT);
        fecha_act = c.getString(COLUMNA_FECHA_ACT);
        usuario = c.getString(COLUMNA_USUARIO);

        try {
            jObject.put(ContractMedida.Columnas.RUTA, ruta);
            jObject.put(ContractMedida.Columnas.ORDEN, orden);
            jObject.put(ContractMedida.Columnas.CODIGO, codigo);
            jObject.put(ContractMedida.Columnas.NOMBRE, nombre);
            jObject.put(ContractMedida.Columnas.MEDIDOR, medidor);
            jObject.put(ContractMedida.Columnas.PARTIDA, partida);
            jObject.put(ContractMedida.Columnas.ESTADO_ACT, estado_act);
            jObject.put(ContractMedida.Columnas.ESTADO_ANT, estado_ant);
            jObject.put(ContractMedida.Columnas.FECHA_ACT, fecha_act);
            jObject.put(ContractMedida.Columnas.USUARIO, usuario);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("Cursor a JSONObject", String.valueOf(jObject));

        return jObject;
    }
}

