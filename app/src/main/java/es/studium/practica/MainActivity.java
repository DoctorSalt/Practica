package es.studium.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnContextClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnContextClickListener {
    boolean errorNombre=false;
    boolean errorApellido=false;
    boolean errorEdad=false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter adaptadorTipoFichero = ArrayAdapter.createFromResource(this, R.array.valores, R.layout.support_simple_spinner_dropdown_item);
        final Spinner spn = findViewById(R.id.spinner);
        spn.setAdapter(adaptadorTipoFichero);
        Button btnreset = findViewById(R.id.btnreset);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText textoNombre = findViewById(R.id.innombre);
                final EditText textoApellido = findViewById(R.id.inapellido);
                final EditText textoEdad = findViewById(R.id.inedad);
                textoNombre.setText("");
                textoApellido.setText("");
                textoEdad.setText("");
                final RadioButton hombreRadio = findViewById(R.id.rhombre);
                final RadioButton mujerRadio = findViewById(R.id.rmujer);
                hombreRadio.setChecked(true);
                mujerRadio.setChecked(false);
                spn.setSelection(0);
                Switch simpleSwitch = (Switch) findViewById(R.id.swhijos);
                simpleSwitch.setChecked(false);
            }
        });


        Button btngenerar = (Button)findViewById(R.id.btngenerar);
        btngenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String nombre="";
                errorNombre=false;
                errorApellido=false;
                errorEdad=false;
                String apellido="";
                String genero="";
                int edad=0;
                String mayorOmenor="";
                String hijos="";

                final EditText textoNombre = findViewById(R.id.innombre);
                final EditText textoApellido = findViewById(R.id.inapellido);
                final EditText textoEdad = findViewById(R.id.inedad);
                if(textoNombre.getText().toString().isEmpty()){
                  errorNombre=true;
                }else
                {
                    nombre = textoNombre.getText().toString();
                }
                if(textoApellido.getText().toString().isEmpty()){
                   errorApellido=true;
                }else {
                    apellido = textoApellido.getText().toString();
                }
                try {
                    edad = Integer.parseInt(textoEdad.getText().toString());
                    mayorOmenor = "";
                    if(edad>=18){
                        mayorOmenor="Mayor de Edad";
                    }else{
                        mayorOmenor="Menor de Edad";
                    }
                }catch (NumberFormatException nfe){
                    errorEdad=true;
                }
                final RadioButton hombreRadio = findViewById(R.id.rhombre);
                if(hombreRadio.isChecked()){
                    genero = "hombre";
                }
                else{
                    genero = "mujer";
                }
                String estadoCivil= spn.getSelectedItem().toString();
                Switch simpleSwitch = (Switch) findViewById(R.id.swhijos);
                if(simpleSwitch.isChecked()){
                    hijos="con hijos";
                }else{
                    hijos="sin hijos";
                }
                final EditText resultadoFinal = findViewById(R.id.lbresult);

                if(errorNombre==true){
                    resultadoFinal.setTextColor(getColor(R.color.colorMal));
                    resultadoFinal.setText("Error Nombre");
                    if(errorApellido==true){
                        resultadoFinal.setTextColor(getColor(R.color.colorMal));
                      resultadoFinal.setText("Error nombre y error apellido");
                       if(errorEdad==true){
                           resultadoFinal.setTextColor(getColor(R.color.colorMal));
                         resultadoFinal.setText("Error nombre, error apellido y error edad");
                        }
                    }
                }else{
                    if(errorApellido==true){
                        resultadoFinal.setText("Error apellido");
                        resultadoFinal.setTextColor(getColor(R.color.colorMal));
                        if(errorEdad==true){
                            resultadoFinal.setText("Error apellido y edad");
                        }
                    }else{
                        if(errorEdad==true){
                            resultadoFinal.setTextColor(getColor(R.color.colorMal));
                            resultadoFinal.setText("Error edad");
                        }
                        else{
                            resultadoFinal.setTextColor(getColor(R.color.colorPrimary));
                            resultadoFinal.setText(apellido + " , " + nombre + ".  "+genero+" ," + mayorOmenor + ", " + estadoCivil + " , " + hijos + ".");
                        }
                    }
                }
            }
            });
        }

    @Override
    public boolean onContextClick(View v) {
        return false;
    }

}
