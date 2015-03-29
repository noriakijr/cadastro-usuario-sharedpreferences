package com.noriaki.cadastrarusuario;

import android.content.Context;

import com.noriaki.cadastrarusuario.Util.DSSharePreferenceUtility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Noriaki on 01/02/2015.
 */
public class PessoaControlBase {

    public static HashMap<String, PessoaBean> getListPessoa(Context context) {
        HashMap<String, PessoaBean> map = (HashMap<String, PessoaBean>) DSSharePreferenceUtility.readObjectFromFile(context, "lista.pessoa");

        if (map == null) {
            map = new HashMap<String, PessoaBean>();
        }

        return map;
    }

    public static void salvar(Map<String, PessoaBean> lista, Context context) {
        DSSharePreferenceUtility.writeObjectToFile(context, lista, "lista.pessoa");
    }

    public static void remover(Context context, PessoaBean pessoa) {
        Map<String, PessoaBean> lista = getListPessoa(context);
        if(lista.containsKey(pessoa.getCpf())) {
            DSSharePreferenceUtility.removeSharedPreferences(context, "lista.pessoa");
            lista.remove(pessoa.getCpf());
            salvar(lista, context);
        }

    }

    public static void alterar(Context context, PessoaBean pessoa) {
        Map<String, PessoaBean> lista = getListPessoa(context);
        if(lista.containsKey(pessoa.getCpf())) {
            DSSharePreferenceUtility.removeSharedPreferences(context, "lista.pessoa");
            lista.remove(pessoa.getCpf());
            lista.put(pessoa.getCpf(), pessoa);
            salvar(lista, context);
        }
    }

    public static PessoaBean getLogado(Context context) {
        PessoaBean p = (PessoaBean) DSSharePreferenceUtility.readObjectFromFile(context, "logado");

        return p;
    }

    public static void saveLogin(Context context, PessoaBean p) {
        DSSharePreferenceUtility.writeObjectToFile(context, p, "logado");
    }
}
