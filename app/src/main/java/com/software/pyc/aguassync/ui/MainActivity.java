package com.software.pyc.aguassync.ui;

import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.software.pyc.aguassync.R;
import com.software.pyc.aguassync.provider.ConsultaMedida;
import com.software.pyc.aguassync.provider.ContractMedida;
import com.software.pyc.aguassync.provider.Medida;
import com.software.pyc.aguassync.provider.MedidaAdapter;
import com.software.pyc.aguassync.provider.MedidaDBHelper;
import com.software.pyc.aguassync.sync.SyncAdapter;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.software.pyc.aguassync.ui.CargaMedida.OnSimpleDialogListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnSimpleDialogListener {




    ListView listViewMedidas;
    ArrayAdapter<String> arrayAdapter;
    Toolbar toolbarVar;

    static MedidaAdapter medidaAdapter;
    static List<Medida> listMedida;
    static Cursor c;
    static String rutaSeleccionada;
    static String opcion="general";
    //static String opcionBusqueda="Nombre";
    static String opcionBusqueda;
    static String opcionBusquedaAux;
    Toolbar toolbar;



    ConsultaMedida consulta = new ConsultaMedida();

    MedidaDBHelper medidaOpenHelper = new MedidaDBHelper(this,ContractMedida.DATABASE_NAME,null,1);
    static int posicionList =5;



    public void actualizarDatos(){


        c = medidaOpenHelper.getAllMedidas(consulta.getOrderBy(),consulta.getWhere());
        listMedida = medidaOpenHelper.getListaMedidas(c);


        medidaAdapter = new MedidaAdapter(getApplicationContext(), listMedida);

        listViewMedidas.setAdapter(medidaAdapter);

        medidaAdapter.notifyDataSetChanged();

        listViewMedidas.setSelection(posicionList);

    }

    @Override
    public void onPossitiveButtonClick() {
        //medidaAdapter = new MedidaAdapter(getApplicationContext(), medidaOpenHelper.getListaMedidas(medidaOpenHelper.getAllMedidas(orderBy)));
        Toast.makeText(getApplicationContext(), "OnClickListener", Toast.LENGTH_SHORT).show();
        //medidaAdapter.notifyDataSetChanged();
        //listMedida = medidaOpenHelper.getListaMedidas(medidaOpenHelper.getAllMedidas());
        actualizarDatos();
    }




        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listViewMedidas = (ListView)findViewById(R.id.lvMedidas);

        final Button logOut = (Button)findViewById(R.id.btnLogOut);
        TextView user = (TextView)findViewById(R.id.tvUser);

        //Borrar la base para probar
        medidaOpenHelper.onDelete();


//Ordenamientos
        TextView orderByOrden = (TextView)findViewById(R.id.lvOrden);
        TextView orderByCodigo = (TextView)findViewById(R.id.lvCodigo);
        TextView orderByNombre = (TextView)findViewById(R.id.lvNombre);
        TextView orderByMedidor = (TextView)findViewById(R.id.lvMedidor);
        TextView orderByPartida = (TextView)findViewById(R.id.lvPartida);
        TextView orderByAnt = (TextView)findViewById(R.id.lvAnt);
        TextView orderByAct = (TextView)findViewById(R.id.lvAct);

        orderByOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consulta.getOrderByMetodo().equalsIgnoreCase("desc")){
                    consulta.setOrderByMetodo("asc");
                }else{
                    consulta.setOrderByMetodo("desc");
                };

                consulta.setOrderBy(ContractMedida.Columnas.ORDEN);
                posicionList=0;
                actualizarDatos();
            }
        });
        orderByCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consulta.getOrderByMetodo().equalsIgnoreCase("desc")){
                    consulta.setOrderByMetodo("asc");
                }else{
                    consulta.setOrderByMetodo("desc");
                };

                consulta.setOrderBy(ContractMedida.Columnas.CODIGO);
                posicionList=0;
                actualizarDatos();
            }
        });
        orderByNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consulta.getOrderByMetodo().equalsIgnoreCase("desc")){
                    consulta.setOrderByMetodo("asc");
                }else{
                    consulta.setOrderByMetodo("desc");
                };

                consulta.setOrderBy(ContractMedida.Columnas.NOMBRE);
                posicionList=0;
                actualizarDatos();
            }
        });
        orderByMedidor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consulta.getOrderByMetodo().equalsIgnoreCase("desc")){
                    consulta.setOrderByMetodo("asc");
                }else{
                    consulta.setOrderByMetodo("desc");
                };

                consulta.setOrderBy(ContractMedida.Columnas.MEDIDOR);
                posicionList=0;
                actualizarDatos();
            }
        });
        orderByPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consulta.getOrderByMetodo().equalsIgnoreCase("desc")){
                    consulta.setOrderByMetodo("asc");
                }else{
                    consulta.setOrderByMetodo("desc");
                };

                consulta.setOrderBy(ContractMedida.Columnas.PARTIDA);
                posicionList=0;
                actualizarDatos();
            }
        });
        orderByAnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consulta.getOrderByMetodo().equalsIgnoreCase("desc")){
                    consulta.setOrderByMetodo("asc");
                }else{
                    consulta.setOrderByMetodo("desc");
                };

                consulta.setOrderBy(ContractMedida.Columnas.ESTADO_ANT);
                posicionList=0;
                actualizarDatos();
            }
        });
        orderByAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consulta.getOrderByMetodo().equalsIgnoreCase("desc")){
                    consulta.setOrderByMetodo("asc");
                }else{
                    consulta.setOrderByMetodo("desc");
                };

                consulta.setOrderBy(ContractMedida.Columnas.ESTADO_ACT);
                posicionList=0;
                actualizarDatos();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logOut();
            }
        });

        actualizarDatos();

        //Implementacion del spinner ruta
        Spinner spinner = (Spinner) findViewById(R.id.spRuta);
        String[] ruta_spinner = {"1","2","3","4"};

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ruta_spinner));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)

            {


                rutaSeleccionada = ContractMedida.Columnas.RUTA+"="+String.valueOf(adapterView.getItemAtPosition(pos));
                //opcion="ruta";

                consulta.setRuta(rutaSeleccionada);
                actualizarDatos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });


        //Implementacion del spinner busqueda
        Button btnBusqueda = (Button)findViewById(R.id.btnBusqueda);
        Spinner spinnerBusqueda = (Spinner) findViewById(R.id.spBusqueda);
        final EditText etbusqueda = (EditText)findViewById(R.id.etBusqueda2);

        String[] busqueda_spinner = {"Nombre","Partida","Medidor"};

        spinnerBusqueda.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, busqueda_spinner));

        spinnerBusqueda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)

            {

                String busquedaSeleccionada = String.valueOf(adapterView.getItemAtPosition(pos));

                switch (busquedaSeleccionada){
                    case "Nombre":
                        busquedaSeleccionada = ContractMedida.Columnas.NOMBRE;
                        break;
                    case "Partida":
                        busquedaSeleccionada = ContractMedida.Columnas.PARTIDA;
                        break;
                    case "Medidor":
                        busquedaSeleccionada = ContractMedida.Columnas.MEDIDOR;
                        break;
                }

                opcionBusquedaAux=busquedaSeleccionada;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });


        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busquedaValor = etbusqueda.getText().toString();

                opcionBusqueda=opcionBusquedaAux+" like '%"+busquedaValor+"%'";
                consulta.addWhereAnd(opcionBusqueda);
                actualizarDatos();

            }
        });



        // Eventos
          listViewMedidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                posicionList=listViewMedidas.getFirstVisiblePosition();

                Medida currentMedida = medidaAdapter.getItem(position);
                //Toast.makeText(getApplicationContext(),"headerCount: \n" + currentMedida.getNombre(),Toast.LENGTH_SHORT).show();

                CargaMedida d = new CargaMedida();
                d.Carga(currentMedida);
                d.show(getFragmentManager(),"");


            }
        });
    }

/*    private void logOut(){
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.logoutUser();
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sync) {
            SyncAdapter.sincronizarAhora(this, false);
            actualizarDatos();
            return true;
        }
        if (id == R.id.action_sync_local) {
            Toast.makeText(getApplicationContext(),"Subir cambios al servidor",Toast.LENGTH_SHORT).show();
            SyncAdapter.sincronizarAhora(this, true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/*    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        emptyView.setText("Cargando datos...");
        // Consultar todos los registros
        return new CursorLoader(
                this,
                ContractParaGastos.CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        emptyView.setText("");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }*/
}
