package com.dev.iccaka;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.iccaka.intervaltimer.R;

import java.util.Calendar;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    //constante
    static int puntaje;
    //atributos
    private EditText et_fecha , et_enfoque, et_numejer, et_nomejer ,et_series, et_rep;
    static int num ;
    public static int i ;
    int dia, mes , ano ;
    Button b_fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        // datos de entrenamiento
        et_fecha = (EditText)findViewById(R.id.txt_fecha);
        et_enfoque = (EditText)findViewById(R.id.txt_enfoque);
        et_numejer = (EditText)findViewById(R.id.txt_numejer);
        b_fecha = (Button)findViewById(R.id.Fecha);
        b_fecha.setOnClickListener(this);

        //datos de ejercicios realizados

    }
    //metodo para crear la base de datos
    public void registrar (View view){
       adminBD admin = new adminBD(this,"entrenamiento",null, 1);
       //
        SQLiteDatabase entrenamiento = admin.getWritableDatabase();
        //variables para los datos
        String fecha = et_fecha.getText().toString();
        String enfoque = et_enfoque.getText().toString();
        String numejer = et_numejer.getText().toString();
        // condicional para evitar campos vacios
        if(!fecha.isEmpty() && !enfoque.isEmpty() && !numejer.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("fecha" , fecha);
            registro.put("enfoque" , enfoque);
            registro.put("numejer" , numejer);
            entrenamiento.insert("sesion", null, registro);
            //cierre de la base de datos
            entrenamiento.close();
            et_fecha.setText("");
            et_enfoque.setText("");
            et_numejer.setText("");

            Toast.makeText(this, "Completado!",Toast.LENGTH_SHORT).show();

            setContentView(R.layout.activity_registro2);
            num = Integer.parseInt(numejer);

        }else{
            //mensaje para cuando los campos no son rellenados
            Toast.makeText(this, "Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }
    }

    public void agregar (View view){
        adminBD admin = new adminBD(this, "entrenamiento", null, 1);
        SQLiteDatabase entrenamiento = admin.getWritableDatabase();

        et_nomejer = (EditText)findViewById(R.id.txt_nomejer);
        et_series = (EditText)findViewById(R.id.txt_series);
        et_rep = (EditText)findViewById(R.id.txt_rep);

        //variables de datos
        String nomejer = et_nomejer.getText().toString();
        String series = et_series.getText().toString();
        String rep = et_rep.getText().toString();

        // for (int i = 0; i < num; i++) {
        if (!nomejer.isEmpty() && !series.isEmpty() && !rep.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("nomejer", nomejer);
            registro.put("series", series);
            registro.put("rep", rep);
            entrenamiento.insert("ejercicios", null, registro);
            //cierre de la base de datos
            entrenamiento.close();
            et_nomejer.setText("");
            et_series.setText("");
            et_rep.setText("");
            Toast.makeText(this, "Completado!", Toast.LENGTH_SHORT).show();
            i ++;
            if(num == i){
                Toast.makeText(this, "ya pusiste todos los ejercicios",Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_progreso);
            }else{
                registro_2();
                Toast.makeText(this, "completo " + i + " de " + num + " ejercicios",Toast.LENGTH_SHORT).show();

            }

        } else {
            //mensaje para cuando los campos no son rellenados
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

       /* i ++;
        if(num == i){
            Toast.makeText(this, "ya pusiste todos los ejercicios",Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_progreso);
        }else{
            registro_2();
            Toast.makeText(this, "completo " + i + " de " + num + " ejercicios",Toast.LENGTH_SHORT).show();
        }
        */
    }
    //boton de ejercicios
    public void registro_2() {
        adminBD admin = new adminBD(this, "entrenamiento", null, 1);
        SQLiteDatabase ejercicios = admin.getWritableDatabase();

        //variables de datos
        String nomejer = et_nomejer.getText().toString();
        String series = et_series.getText().toString();
        String rep = et_rep.getText().toString();

        //

        // for (int i = 0; i < num; i++) {
        if (!nomejer.isEmpty() && !series.isEmpty() && !rep.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("nomejer", nomejer);
            registro.put("series", series);
            registro.put("rep", rep);
            ejercicios.insert("ejercicios", null, registro);
            //cierre de la base de datos
            ejercicios.close();
            et_nomejer.setText("");
            et_series.setText("");
            et_rep.setText("");
            Toast.makeText(this, "Completado!", Toast.LENGTH_SHORT).show();

            Toast.makeText(this, "Sumaste 10 puntos!", Toast.LENGTH_SHORT).show();

        } else {
            //mensaje para cuando los campos no son rellenados
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view){
        if (view == b_fecha){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year , int monthOfYear, int dayOfMonth) {

                    et_fecha.setText(dayOfMonth + "/" +(monthOfYear + 1) + "/" + year );

                }
            }
            ,dia,mes,ano);
            datePickerDialog.show();
        }
    }
    public void volver (View view){
        Intent v = new Intent (this, ProgresoActivity.class);
        startActivity(v);
    }
}