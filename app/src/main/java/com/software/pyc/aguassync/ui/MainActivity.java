package com.software.pyc.aguassync.ui;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.database.Cursor;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import com.software.pyc.aguassync.R;
import com.software.pyc.aguassync.provider.AdaptadorDeMedida;
import com.software.pyc.aguassync.provider.ConsultaMedida;
import com.software.pyc.aguassync.provider.ContractMedida;
import com.software.pyc.aguassync.provider.Medida;
import com.software.pyc.aguassync.provider.MedidaAdapter;
import com.software.pyc.aguassync.provider.MedidaDBHelper;
import com.software.pyc.aguassync.provider.RecyclerItemClickListener;
import com.software.pyc.aguassync.provider.RecyclerViewClickListener;
import com.software.pyc.aguassync.provider.RecyclerViewTouchListener;
import com.software.pyc.aguassync.sync.SyncAdapter;
import com.software.pyc.aguassync.ui.CargaMedida.OnSimpleDialogListener;


public class MainActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>, OnSimpleDialogListener {



    static MedidaAdapter medidaAdapter;
    static Cursor c;
    static String rutaSeleccionada;
    static String opcionBusqueda;
    static String opcionBusquedaAux;
    Toolbar toolbar;

    static Boolean firstTime = false;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdaptadorDeMedida adapter;
    ProgressDialog progressDoalog;


    ConsultaMedida consulta = ConsultaMedida.getInstance();

    MedidaDBHelper medidaOpenHelper = new MedidaDBHelper(this,ContractMedida.DATABASE_NAME,null,1);
    static int posicionList =1;



    public void actualizarDatos(){

        c = medidaOpenHelper.getAllMedidas(consulta.getOrderBy(),consulta.getWhere(),consulta.getLimite());
        consulta.setFin(medidaOpenHelper.getCantAllMedidas(consulta.getOrderBy(),consulta.getWhere(),consulta.getLimite()));
        TextView usuario = findViewById(R.id.tvUser);
        usuario.setText("Pagina "+String.valueOf(consulta.getPagActual())+" de "+String.valueOf(consulta.getPaginas()));

        //consulta.setFin(c.getCount());

        adapter = new AdaptadorDeMedida(this);
        adapter.swapCursor(c);
        recyclerView.setAdapter(adapter);
        recyclerView.getLayoutManager().scrollToPosition(posicionList);


    }

    @Override
    public void onPossitiveButtonClick() {

        actualizarDatos();
    }




        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.reciclador);
        //recyclerView.setHasFixedSize(true);

        recyclerView.setItemViewCacheSize(10);
        recyclerView.setDrawingCacheEnabled(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdaptadorDeMedida(this);

        recyclerView.setAdapter(adapter);
        getSupportLoaderManager().initLoader(0, null, this);






        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {

                if (!recyclerView.canScrollVertically(-1)) {
                    //onScrolledToTop();
                    Toast.makeText(getApplicationContext(),"FIRST Item Wow",Toast.LENGTH_SHORT).show();
                    if (consulta.previusPg()) {
                        posicionList = consulta.getCanMostrar()-layoutManager.getChildCount();
                        actualizarDatos();
                    }
                } else if (!recyclerView.canScrollVertically(1)) {
                    //onScrolledToBottom();
                    Toast.makeText(getApplicationContext(),"OFFSET: "+ String.valueOf(consulta.getOffset())+" FIN: "+ String.valueOf(consulta.getFin()),Toast.LENGTH_SHORT).show();
                    if (consulta.nextPg()) {
                        posicionList = 1;
                        actualizarDatos();
                        //Do pagination.. i.e. fetch new data
                    }
                }

 /*               boolean loading = true;
                if(dy > 0) //check for scroll down
                {


                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                           // Toast.makeText(getApplicationContext(),"Last Item Wow",Toast.LENGTH_SHORT).show();
                            if (consulta.nextPg()) {
                                actualizarDatos();
                                //Do pagination.. i.e. fetch new data
                            }
                        }

                    }
                }
                if(dy < 0) //check for scroll up
                {


                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( pastVisiblesItems <= 1)
                        {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            Toast.makeText(getApplicationContext(),"FIRST Item Wow",Toast.LENGTH_SHORT).show();

*//*                                if (consulta.previusPg()) {
                                    actualizarDatos();
                                }*//*
                                //Do pagination.. i.e. fetch new dato

                        }

                    }
                }*/
            }
        });



      /*      recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), recyclerView, new RecyclerViewClickListener() {
                @Override
                public void onClick(View view, int position) {

                    Medida currentMedida = medidaAdapter.getItem(position);

                    CargaMedida d = new CargaMedida();
                    d.Carga(currentMedida);
                    d.show(getFragmentManager(),"");
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));
*/

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {

                            List<Medida> listaMedida = adapter.getLsMedida();

                            Medida currentMedida = listaMedida.get(position);

                            CargaMedida d = new CargaMedida();
                            d.Carga(currentMedida);
                            d.show(getFragmentManager(),"");
                            //recyclerView.getLayoutManager().;


                            posicionList = layoutManager.findFirstVisibleItemPosition();


                            //Toast.makeText(getApplicationContext(),"OnClick: " + recyclerView.getLayoutManager().,Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"OnClick: " + view.getLayoutParams().toString(),Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),"OnClick: " + listaMedida.get(position).getNombre(),Toast.LENGTH_SHORT).show();
                        }

                        @Override public void onLongItemClick(View view, int position) {
                            // do whatever
                        }
                    })
            );


        final Button logOut = findViewById(R.id.btnLogOut);

        //Borrar la base para probar
       // medidaOpenHelper.onDelete();



//Ordenamientos
        TextView orderByOrden = findViewById(R.id.lvOrden);
        TextView orderByCodigo = findViewById(R.id.lvCodigo);
        TextView orderByNombre = findViewById(R.id.lvNombre);
        TextView orderByMedidor = findViewById(R.id.lvMedidor);
        TextView orderByPartida = findViewById(R.id.lvPartida);
        TextView orderByAnt = findViewById(R.id.lvAnt);
        TextView orderByAct = findViewById(R.id.lvAct);

        orderByOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (consulta.getOrderByMetodo().equalsIgnoreCase("desc")){
                    consulta.setOrderByMetodo("asc");
                }else{
                    consulta.setOrderByMetodo("desc");
                }

                consulta.setOrderBy(ContractMedida.Columnas.ORDEN);
                posicionList=1;
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
                }

                consulta.setOrderBy(ContractMedida.Columnas.CODIGO);
                posicionList=1;
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
                }

                consulta.setOrderBy(ContractMedida.Columnas.NOMBRE);
                posicionList=1;
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
                }

                consulta.setOrderBy(ContractMedida.Columnas.MEDIDOR);
                posicionList=1;
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
                }

                consulta.setOrderBy(ContractMedida.Columnas.PARTIDA);
                posicionList=1;
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
                }

                consulta.setOrderBy(ContractMedida.Columnas.ESTADO_ANT);
                posicionList=1;
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
                }

                consulta.setOrderBy(ContractMedida.Columnas.ESTADO_ACT);
                posicionList=1;
                actualizarDatos();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logOut();
            }
        });

       // actualizarDatos();

        //Implementacion del spinner ruta
        Spinner spinner = findViewById(R.id.spRuta);
        String[] ruta_spinner = {"1","2","3","4"};

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ruta_spinner));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)

            {
                rutaSeleccionada = ContractMedida.Columnas.RUTA+"="+String.valueOf(adapterView.getItemAtPosition(pos));

                consulta.setRuta(rutaSeleccionada);
                actualizarDatos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {    }
        });


        //Implementacion del spinner busqueda
        Button btnBusqueda = findViewById(R.id.btnBusqueda);
        Spinner spinnerBusqueda =  findViewById(R.id.spBusqueda);
        final EditText etbusqueda = findViewById(R.id.etBusqueda2);

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
                consulta.setOffset(1);

                actualizarDatos();

            }
        });



        // Eventos
/*          listViewMedidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // posicionList=listViewMedidas.getFirstVisiblePosition();

                Medida currentMedida = medidaAdapter.getItem(position);
                //Toast.makeText(getApplicationContext(),"headerCount: \n" + currentMedida.getNombre(),Toast.LENGTH_SHORT).show();

                CargaMedida d = new CargaMedida();
                d.Carga(currentMedida);
                d.show(getFragmentManager(),"");


            }
        });*/

recyclerView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildLayoutPosition(v);
        Medida currentMedida = medidaAdapter.getItem(position);

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


            firstTime = true;

            progressDoalog = new ProgressDialog(MainActivity.this);
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Descargando tabla....");
            progressDoalog.setTitle("Recuperando datos del servidor");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.setCancelable(false);
            progressDoalog.show();



            return true;
        }
        if (id == R.id.action_sync_local) {
            //Toast.makeText(getApplicationContext(),"Subir cambios al servidor",Toast.LENGTH_SHORT).show();
            SyncAdapter.sincronizarAhora(this, true);


            firstTime = true;

            progressDoalog = new ProgressDialog(MainActivity.this);
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Subiendo modificaciones....");
            progressDoalog.setTitle("Actualizando el Servidor");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.setCancelable(false);
            progressDoalog.show();


            /*            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
*//*                        while (progressDoalog.getProgress() <= progressDoalog
                                .getMax()) {
                            Thread.sleep(200);
                            handle.sendMessage(handle.obtainMessage());
                            if (progressDoalog.getProgress() == progressDoalog
                                    .getMax()) {
                                progressDoalog.dismiss();
                            }
                        }*//*
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();*/



            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/*    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDoalog.incrementProgressBy(1);
        }
    };*/

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        // Consultar todos los registros
        return new CursorLoader(
                this,
                ContractMedida.CONTENT_URI,
                null, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        actualizarDatos();

if (firstTime) {
   // progressDoalog.setProgress(progressDoalog.getMax());
    progressDoalog.dismiss();
}else {
    consulta.setFin(data.getCount());
}

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
        actualizarDatos();
    }


}
