package com.noriaki.cadastrarusuario;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;


public class FormAlterarActivity extends ActionBarActivity {

    private TextView tvMensagem;
    private EditText etUsuario;
    private EditText etEmail;
    private EditText etCpf;
    private EditText etSenha;
    private Button btAlterar;
    private PessoaBean pessoa;
    private Map<String, PessoaBean> mapPessoa;

    private void inicializarComponentes() {
        tvMensagem = (TextView) findViewById(R.id.tvMensagem);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etCpf = (EditText) findViewById(R.id.etCpf);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btAlterar = (Button) findViewById(R.id.btAlterar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_alterar);
        inicializarComponentes();

        String cpf = getIntent().getStringExtra("cpf");
        mapPessoa = PessoaControlBase.getListPessoa(getApplicationContext());
        if(mapPessoa.containsKey(cpf)) {
            pessoa = mapPessoa.get(cpf);
            etUsuario.setText(pessoa.getUsuario());
            etEmail.setText(pessoa.getEmail());
            etSenha.setText(pessoa.getSenha());
        } else {
            tvMensagem.setText("CPF não foi encontrado!");
            btAlterar.setEnabled(false);
        }

        etCpf.setText(cpf);

        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsuario != null && etSenha != null && etCpf != null && etEmail != null) {
                    if (etUsuario.getText().length() > 2 && etSenha.getText().length() >= 4 &&
                            etCpf.getText().length() >= 11 && etEmail.getText().length() >= 9) {
                        pessoa = new PessoaBean(etUsuario.getText().toString(), etEmail.getText().toString(),
                                etCpf.getText().toString(), etSenha.getText().toString());

                        PessoaControlBase.alterar(getApplicationContext(), pessoa);
                        Toast.makeText(getApplicationContext(), "Alterado com sucesso!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form_alterar, menu);
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
