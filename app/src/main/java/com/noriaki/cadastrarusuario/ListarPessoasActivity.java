package com.noriaki.cadastrarusuario;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;


public class ListarPessoasActivity extends Activity {

    private ListView lvPessoas;
    private int myPosition = 0;
    private PessoaAdapter pessoaAdapter;
    private HashMap<String, PessoaBean> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pessoas);

        lvPessoas = (ListView) findViewById(R.id.lvPessoas);

        lista = PessoaControlBase.getListPessoa(getApplicationContext());
        pessoaAdapter = new PessoaAdapter(PessoaControlBase.getListPessoa(getApplicationContext()), getApplicationContext());

        lvPessoas.setAdapter(pessoaAdapter);

        lvPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Use the Builder class for convenient dialog construction
                AlertDialog.Builder builder = new AlertDialog.Builder(ListarPessoasActivity.this);
                String listaOpcoes[] = {"Visualizar", "Modificar", "Deletar", "Cancelar"};
                myPosition = position;

                builder.setTitle("Escolha uma opção").setItems(listaOpcoes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                visualizarItem(myPosition, true);
                                break;
                            case 1:
                                visualizarItem(myPosition, false);
                                break;
                            case 2:
                                deletarItem(myPosition);
                                break;
                            default:
                        }
                    }

                    public void visualizarItem(int position, boolean semEdicao) {
                        Intent intent = new Intent(ListarPessoasActivity.this, FormUsuarioActivity.class);
                        intent.putExtra("pessoa", (PessoaBean) pessoaAdapter.getItem(position));
                        intent.putExtra("semEdicao", semEdicao);
                        startActivity(intent);
                        finish();
                    }

                    public void deletarItem(int position) {
                        String cpf = ((PessoaBean) pessoaAdapter.getItem(position)).getCpf();
                        lista.remove(cpf);
                        pessoaAdapter.deleteItem(position);
                        pessoaAdapter.notifyDataSetChanged();
                        PessoaControlBase.salvar(lista, getApplicationContext());
                    }
                });

                builder.create().show();
            }
        });
    }
}
