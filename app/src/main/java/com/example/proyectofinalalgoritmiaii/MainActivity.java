package com.example.proyectofinalalgoritmiaii;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView palabraObjetivo;
    Button[] botonesCirculo;
    ArrayList<String> palabrasEncontradas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int[] idBotonesCirculo = new int[] {
                R.id.button5, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12
        };

        botonesCirculo = new Button[] {
                findViewById(idBotonesCirculo[0]),
                findViewById(idBotonesCirculo[1]),
                findViewById(idBotonesCirculo[2]),
                findViewById(idBotonesCirculo[3]),
                findViewById(idBotonesCirculo[4]),
                findViewById(idBotonesCirculo[5]),
                findViewById(idBotonesCirculo[6])
        };

        Button botonClear = findViewById(R.id.button3);
        ImageButton botonRandom = findViewById(R.id.imageButton);
        ImageButton botonBonus = findViewById(R.id.imageButton5);
        palabraObjetivo=findViewById(R.id.textView3);


        // Establecer el OnClickListener para el botón
        botonClear.setOnClickListener(v -> palabraObjetivo.setText(""));

        botonRandom.setOnClickListener(v -> shuffleButtons(botonesCirculo));

        botonBonus.setOnClickListener(v -> {
            // Crear el AlertDialog.Builder
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            // Configurar título y mensaje
            // Nota: Aquí debes reemplazar las cadenas con las variables adecuadas que contienen las palabras encontradas y totales
            builder.setTitle("Palabras Encontradas y Posibles");
            String mensaje = "Palabras encontradas: " + String.join(", ", palabrasEncontradas) + "\n\nTotal de todas las palabras que se pueden construir: "+palabrasEncontradas.size();
            builder.setMessage(mensaje);

            // Botón OK para cerrar el diálogo
            builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

            // Crear y mostrar el AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();

        });

        for (int i=0;i<botonesCirculo.length;i++) {
            botonesCirculo[i].setOnClickListener(v -> setLletra(v));
        }
    }
    @SuppressLint("SetTextI18n")
    public void setLletra(View view) {
        Button btn = (Button) view;
        String lletra = btn.getText().toString();
        palabraObjetivo.setText(palabraObjetivo.getText().toString() + lletra);
        btn.setEnabled(false); // Desactivar el botón después de usarlo
        btn.setTextColor(Color.GRAY); // Cambiar el color para indicar que el botón está desactivado
    }
    private void shuffleButtons(Button[] buttons) {
        Random rnd = new Random();
        for (int i = buttons.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Intercambio de textos
            CharSequence temp = buttons[i].getText();
            buttons[i].setText(buttons[index].getText());
            buttons[index].setText(temp);
        }
    }

    public static boolean esSolución(String palabra1, String palabra2){
          
        HashMap mapa = new HashMap <Character,Integer> ();            
        for(int i=0; i<palabra1.length(); i++){
            if(mapa.get(palabra1.charAt(i))==null){
                mapa.put(palabra1.charAt(i), 1);
            }
            else{
               mapa.put(palabra1.charAt(i), ((int) mapa.get(palabra1.charAt(i)))+1);
            }
            
        }
        for(int i=0;i<palabra2.length(); i++){
            mapa.put( palabra2.charAt(i), ((int) mapa.get(palabra2.charAt(i)))-1);   
        }
        for(int i=0; i<palabra1.length(); i++){
            if(((int) mapa.get(palabra1.charAt(i)))<0){
                return false;
            }
        }
        return true;
        }
}
}
