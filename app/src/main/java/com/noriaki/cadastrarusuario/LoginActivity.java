package com.noriaki.cadastrarusuario;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText etCpf;
    private EditText etSenha;
    private Button btLogar;
    private Button btCadastrar;
    private CheckBox cbLogado;
//    public static HashMap<String, PessoaBean> listaPessoas = new HashMap<>();

    private void inicializarComponentes() {
        etCpf = (EditText) findViewById(R.id.etCpf);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btLogar = (Button) findViewById(R.id.btLogar);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        cbLogado = (CheckBox) findViewById(R.id.cbLogado);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarComponentes();
//        PessoaBean p1 = new PessoaBean("Jose", "jose@jose.com", "11122233311", "12345");
//        PessoaBean p2 = new PessoaBean("Maria", "maria@maria.com", "11122233322", "12345");
//        PessoaBean p3 = new PessoaBean("Carlos", "carlos@carlos.com", "11122233344", "12345");
//        listaPessoas.put(p1.getCpf(), p1);
//        listaPessoas.put(p2.getCpf(), p2);
//        listaPessoas.put(p3.getCpf(), p3);

        PessoaBean p = PessoaControlBase.getLogado(getApplicationContext());

        if(p != null) {
            startHome(p);
        }

        btCadastrar.setOnClickListener(this);
        btLogar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLogar:
                if(etCpf != null && etSenha != null &&
                        etCpf.getText().length() >= 11 && etSenha.getText().length() >= 4) {
                    PessoaBean p = PessoaControlBase.getListPessoa(getApplicationContext()).get(etCpf.getText().toString());
                    if (p != null && etSenha.getText().toString().equals(p.getSenha())) {
                        if(cbLogado.isChecked()) {
                            PessoaControlBase.saveLogin(getApplicationContext(), p);
                        }
                        startHome(p);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tente novamente...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btCadastrar:
                startActivity(new Intent(LoginActivity.this, FormUsuarioActivity.class));
                break;
        }
    }

    public void startHome(PessoaBean p) {
        Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("pessoa", p);
        startActivity(intent);
        finish();
    }
}
