package com.example.unitconverter;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner category_spinner , unit_spinner;
    TextView res;
    EditText edit_message;
    String[] categories={"Length", "Temperature","Currency"};
    String[] units={"Cm","M","Km"};
    ArrayAdapter aa2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        category_spinner=findViewById(R.id.category_spinner);
        unit_spinner=findViewById(R.id.unit_spinner);
        res=findViewById(R.id.res);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(aa);

        category_spinner.setOnItemSelectedListener(this);

        aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,units);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_spinner.setAdapter(aa2);

        edit_message=findViewById(R.id.edit_message);
        edit_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateResult();
            }
        });
    }
    public void updateResult(){
        if(edit_message.getText().toString().length()>0){
            if(category_spinner.getSelectedItemPosition()==0){
                if(unit_spinner.getSelectedItemPosition()==0){
                    Double res_m=Double.parseDouble(edit_message.getText().toString())*0.01;
                    Double res_km=Double.parseDouble(edit_message.getText().toString())*0.00001;
                    res.setText(res_m+" m \n"+res_km+" km");
                }
                if(unit_spinner.getSelectedItemPosition()==1){
                    Double res_cm=Double.parseDouble(edit_message.getText().toString())*100;
                    Double res_km=Double.parseDouble(edit_message.getText().toString())*0.001;
                    res.setText(res_cm+" cm \n"+res_km+" km");
                }
                if(unit_spinner.getSelectedItemPosition()==2){
                    Double res_cm=Double.parseDouble(edit_message.getText().toString())*100000F;
                    Double res_m=Double.parseDouble(edit_message.getText().toString())*1000F;
                    res.setText(res_cm+" cm \n"+res_m+" m");
                }
            }else if(category_spinner.getSelectedItemPosition()==1){
                if(unit_spinner.getSelectedItemPosition()==0){
                    Double res_k=Double.parseDouble(edit_message.getText().toString())-273.15F;
                    Double res_F=Double.parseDouble(edit_message.getText().toString());
                    res_F=1.8*(res_F-273)+32;
                    res.setText(res_k+" c \n"+res_F+" F");
                }
                if(unit_spinner.getSelectedItemPosition()==1){
                    Double res_k=Double.parseDouble(edit_message.getText().toString())+273.15;
                    Double res_F=(Double.parseDouble(edit_message.getText().toString())*1.8)+32;
                    res.setText(res_k+" K \n"+res_F+" F");
                }
                if(unit_spinner.getSelectedItemPosition()==2){
                    Double res_k=(Double.parseDouble(edit_message.getText().toString())+459.67)*5/9;
                    Double res_c=(Double.parseDouble(edit_message.getText().toString())-32)/1.8;
                    res.setText(res_k+" K \n"+res_c+" c");
                }
            }else{
                if(unit_spinner.getSelectedItemPosition()==0){
                    Double res_d=Double.parseDouble(edit_message.getText().toString())*0.013;
                    Double res_e=Double.parseDouble(edit_message.getText().toString())*0.012;
                    res.setText(res_e+" €(Euro) \n"+res_d+" $ (USD)");
                }
                if(unit_spinner.getSelectedItemPosition()==1){
                    Double res_r=Double.parseDouble(edit_message.getText().toString())*77.98;
                    Double res_e=Double.parseDouble(edit_message.getText().toString())*0.95;
                    res.setText(res_r+" INR \n"+res_e+" €(Euro) ");
                }
                if(unit_spinner.getSelectedItemPosition()==2) {
                    Double res_r = Math.round(Double.parseDouble(edit_message.getText().toString())*82.26)/10000000.0;
                    Double res_d = Math.round(Double.parseDouble(edit_message.getText().toString())*1.05)/10000000.0;
                    res.setText(res_r + " INR \n" + res_d + " $ (USD)");
                }
            }
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(category_spinner.getSelectedItemPosition()==0){
            units[0]="Cm";
            units[1]="M";
            units[2]="Km";
        }else if(category_spinner.getSelectedItemPosition()==1){
            units[0]="K";
            units[1]="C";
            units[2]="F";
        }else{
            units[0]="Rupee";
            units[1]="Dollar";
            units[2]="Euro";
        }
        aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,units);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_spinner.setAdapter(aa2);
        updateResult();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}