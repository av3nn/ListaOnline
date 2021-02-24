package com.avennapps.listaonline;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private List<String> linhas = new ArrayList<String>();
    //LocalDateTime

    public void addItem(View v){
        ListView listView = findViewById(R.id.listView);
        EditText edtTexto = findViewById(R.id.edtTexto);
        final boolean add = linhas.add(String.valueOf(edtTexto.getText()));
        if (add) {
            ArrayAdapter adap = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, linhas);
            listView.setAdapter(adap);
        }

        escondeLayout(v);
    }

    public void mostraLayout(View v){
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        FloatingActionButton floatBtnAdd = findViewById(R.id.floatBtnAdd);
        FloatingActionButton floatBtnRfs = findViewById(R.id.floatBtnRfs);
        EditText edtTexto = findViewById(R.id.edtTexto);

        edtTexto.setText("");
        floatBtnAdd.setVisibility(View.INVISIBLE);
        floatBtnRfs.setVisibility(View.INVISIBLE);
        frameLayout.setVisibility(View.VISIBLE);

    }

    public void escondeLayout(View v){
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        FloatingActionButton floatBtnAdd = findViewById(R.id.floatBtnAdd);
        FloatingActionButton floatBtnRfs = findViewById(R.id.floatBtnRfs);

        floatBtnAdd.setVisibility(View.VISIBLE);
        floatBtnRfs.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.INVISIBLE);
    }

    public void refreshList(View v){

    }

    public void atualiza(View v) throws ExecutionException, InterruptedException, JSONException {
        Rest rest = new Rest();
        rest.execute();
        JSONArray itemsRet = rest.get();

        ListView lv = findViewById(R.id.listView);
        linhas.clear();

        for (int i = 0; i < itemsRet.length(); i++){
            JSONObject obj = itemsRet.getJSONObject(i) ;
            linhas.add(obj.get("qtd").toString() + "x " + obj.get("desc").toString());
        }

        ArrayAdapter adap = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, linhas);
        lv.setAdapter(adap);

        // Comentarios

    }

}