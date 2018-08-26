package com.software.pyc.aguassync.provider;


import android.content.Context;
import android.database.Cursor;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.pyc.aguassync.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorDeMedida extends RecyclerView.Adapter<AdaptadorDeMedida.ExpenseViewHolder> {


        private Cursor cursor;
        private Context context;
        public List<Medida> lsMedida;




        public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
            // Campos respectivos de un item

            public TextView ruta;
            public TextView nombre;
            public TextView orden;
            public TextView codigo;
            public TextView medidor;
            public TextView partida;
            public TextView estAnt;
            public TextView estAct;
            public ImageView estado;

            public ExpenseViewHolder(View v) {
                super(v);


                // Referencias UI.
                 ruta    = itemView.findViewById(R.id.itemRuta);
                 nombre  = itemView.findViewById(R.id.itemNombre);
                 orden   = itemView.findViewById(R.id.itemOrden);
                 codigo  = itemView.findViewById(R.id.itemCodigo);
                 medidor = itemView.findViewById(R.id.itemMedidor);
                 partida = itemView.findViewById(R.id.itemPartida);
                 estAnt  = itemView.findViewById(R.id.itemAnt);
                 estAct  = itemView.findViewById(R.id.itemAct);
                 estado = itemView.findViewById(R.id.imgEstado);

            }
        }

        public AdaptadorDeMedida(Context context) {
            this.context= context;

        }

        @Override
        public int getItemCount() {
            if (cursor!=null)
                return cursor.getCount();
            return 0;
        }

        @Override
        public ExpenseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_lista, viewGroup, false);
            return new ExpenseViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ExpenseViewHolder viewHolder, int i) {

            cursor.moveToPosition(i);

            String ruta;
            String orden;
            String codigo;
            String nombre;
            String medidor;
            String partida;
            String estAnt;
            String estAct;
            String estado;

            ruta = cursor.getString(1);
            orden = cursor.getString(2);
            codigo = cursor.getString(3);
            nombre = cursor.getString(4);
            medidor = cursor.getString(5);
            partida = cursor.getString(6);
            estAnt = cursor.getString(7);
            estAct = cursor.getString(8);
            estado = cursor.getString(13);

            viewHolder.ruta.setText(ruta);
            viewHolder.orden.setText(orden);
            viewHolder.codigo.setText(codigo);
            viewHolder.nombre.setText(nombre);
            viewHolder.medidor.setText(medidor);
            viewHolder.partida.setText(partida);
            viewHolder.estAnt.setText(estAnt);
            viewHolder.estAct.setText(estAct);

           // viewHolder.estado.setImageDrawable();

            getMedida(cursor);

        }

        public void getMedida(Cursor c){
            List<Medida> listaMedida = new ArrayList<>();

            // Si el cursor contiene datos los añadimos al List
            if (c.moveToFirst()) {
                do {

                    listaMedida.add(new Medida(c.getString(0),c.getString(1),c.getString(2),
                            c.getString(3),c.getString(4),c.getString(5),
                            c.getString(6),c.getString(7),c.getString(8),
                            c.getString(9),c.getString(10),c.getString(11)));
                } while (c.moveToNext());
            }

            setLsMedida(listaMedida);
        }

        public void swapCursor(Cursor newCursor) {
            cursor = newCursor;
            notifyDataSetChanged();
        }

        public Cursor getCursor() {
            return cursor;
        }

    public List<Medida> getLsMedida() {
        return lsMedida;
    }

    public void setLsMedida(List<Medida> lsMedida) {
        this.lsMedida = lsMedida;
    }
}
