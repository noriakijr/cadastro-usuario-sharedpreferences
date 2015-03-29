package com.noriaki.cadastrarusuario;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AlterarExcluirActivity extends ActionBarActivity {

    private EditText etCpf;
    private Button btPreAlterar;
    private Button btExcluir;
    private PessoaBean pessoa;

    private void inicializarComponentes() {
        etCpf = (EditText) findViewById(R.id.etCpf);
        btPreAlterar = (Button) findViewById(R.id.btPreAlterar);
        btExcluir = (Button) findViewById(R.id.btExcluir);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_excluir);
        inicializarComponentes();

        btPreAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlterarExcluirActivity.this, FormAlterarActivity.class);
                intent.putExtra("cpf", etCpf.getText().toString());
                startActivity(intent);
            }
        });

        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etCpf != null) {
                    pessoa = new PessoaBean(null, null, etCpf.getText().toString(), null);
                    PessoaControlBase.remover(getApplicationContext(), pessoa);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alterar_excluir, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
