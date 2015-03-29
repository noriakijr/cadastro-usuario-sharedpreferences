package com.noriaki.cadastrarusuario;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeActivity extends ActionBarActivity implements View.OnClickListener{

    private TextView tvBemVindo;
    private Button btCadastrar;
    private Button btAlterarExcluir;
    private Button btListar;
    private Button btLogout;

    private void inicializarComponentes() {
        tvBemVindo = (TextView) findViewById(R.id.tvBemVindo);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btAlterarExcluir = (Button) findViewById(R.id.btAlterarExcluir);
        btListar = (Button) findViewById(R.id.btListar);
        btLogout = (Button) findViewById(R.id.btLogout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        inicializarComponentes();

        PessoaBean pessoa = (PessoaBean) getIntent().getSerializableExtra("pessoa");

        tvBemVindo.setText("Bem vindo(a) " + pessoa.getUsuario());

        btCadastrar.setOnClickListener(this);
        btAlterarExcluir.setOnClickListener(this);
        btListar.setOnClickListener(this);
        btLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btCadastrar:
                startActivity(new Intent(HomeActivity.this, FormUsuarioActivity.class));
                break;
            case R.id.btAlterarExcluir:
                startActivity(new Intent(HomeActivity.this, AlterarExcluirActivity.class));
                break;
            case R.id.btListar:
                startActivity(new Intent(HomeActivity.this, ListarPessoasActivity.class));
                break;
            case R.id.btLogout:
                PessoaControlBase.saveLogin(getApplicationContext(), null);
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                break;
        }

    }
}
