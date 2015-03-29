package com.noriaki.cadastrarusuario;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class FormUsuarioActivity extends ActionBarActivity {

    private EditText etUsuario;
    private EditText etEmail;
    private EditText etCpf;
    private EditText etSenha;
    private Button btSalvar;
    private PessoaBean pessoa;

    private void inicializarComponentes() {
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etCpf = (EditText) findViewById(R.id.etCpf);
        etSenha = (EditText) findViewById(R.id.etSenha);
        btSalvar = (Button) findViewById(R.id.btSalvar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_usuario);

        inicializarComponentes();

        pessoa = (PessoaBean) getIntent().getSerializableExtra("pessoa");
        boolean semEdicao = getIntent().getBooleanExtra("semEdicao", true);
        if(semEdicao) {
            setTitle("Ver perfil");
            etCpf.setText(pessoa.getCpf());
            editTextSemEdicao(etCpf);
            etUsuario.setText(pessoa.getUsuario());
            editTextSemEdicao(etUsuario);
            etEmail.setText(pessoa.getEmail());
            editTextSemEdicao(etEmail);
            etSenha.setText(pessoa.getSenha());
            editTextSemEdicao(etSenha);

            btSalvar.setVisibility(View.INVISIBLE);
            // View.INVISIBLE - deixa invisivel, deixa um espaço em branco
            // View.GONE - sumir da tela, se preciso, junta os componentes da tela sem deixar espaço em branco
        } else if(!semEdicao) {
            etCpf.setText(pessoa.getCpf());
            etUsuario.setText(pessoa.getUsuario());
            etEmail.setText(pessoa.getEmail());
            etSenha.setText(pessoa.getSenha());
            btSalvar.setText("Alterar");

            etCpf.setSelected(false);
            etCpf.setEnabled(false);
        }

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsuario != null && etSenha != null && etCpf != null && etEmail != null) {
                    if (etUsuario.getText().length() > 2 && etSenha.getText().length() >= 4 &&
                            etCpf.getText().length() >= 11 && etEmail.getText().length() >= 9) {
                        pessoa = new PessoaBean(etUsuario.getText().toString(), etEmail.getText().toString(),
                                etCpf.getText().toString(), etSenha.getText().toString());
                        Map<String, PessoaBean> map = new HashMap<String, PessoaBean>();
                        map = PessoaControlBase.getListPessoa(getApplicationContext());
                        if(map.containsKey(pessoa.getCpf())) {
                            map.remove(pessoa.getCpf());
                        }
                        map.put(pessoa.getCpf(), pessoa);
                        PessoaControlBase.salvar(map, getApplicationContext());

                        Toast.makeText(getApplicationContext(), "Operação realizada com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Preencha novamente...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void editTextSemEdicao(EditText et) {
        et.setEnabled(false);
        et.setSelected(false);
        et.setKeyListener(null);
    }
}
