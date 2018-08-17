package lifecycle.icesi.edu.co.listas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btn_agregar;
    private EditText et_nombre;
    private EditText et_telefono;
    private ListView lista_estudiantes;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CALL_PHONE}, 11);

        btn_agregar = findViewById(R.id.btn_agregar);
        et_telefono = findViewById(R.id.et_telefono);
        et_nombre = findViewById(R.id.et_nombre);
        lista_estudiantes = findViewById(R.id.lista_estudiantes);
        adaptador = new Adaptador(this);
        lista_estudiantes.setAdapter(adaptador);

        adaptador.agregarEstudiante(new Estudiante("Cristian", "A00056439"));
        adaptador.agregarEstudiante(new Estudiante("Daniel", "A9012378"));

        lista_estudiantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Estudiante es = (Estudiante) adaptador.getItem(position);
                Toast.makeText(MainActivity.this, es.getNombre(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:3148221300"));
                startActivity(i);
            }
        });

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = et_nombre.getText().toString();
                String telefono = et_telefono.getText().toString();
                Estudiante new_estudiante = new Estudiante(nombre,telefono);
                adaptador.agregarEstudiante(new_estudiante);
                et_nombre.setText("");
                et_telefono.setText("");
            }
        });

    }

    //adapter: permite tomar los datos y mostrarlos de forma grafica




}
