package com.noriaki.cadastrarusuario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.noriaki.cadastrarusuario.PessoaBean;
import com.noriaki.cadastrarusuario.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Noriaki on 01/02/2015.
 */
public class PessoaAdapter extends BaseAdapter {

    private List<PessoaBean> list = new ArrayList<>();
    private Context context;

    public PessoaAdapter(HashMap<String, PessoaBean> map, Context context) {
        this.list.addAll(map.values());
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void deleteItem(int position) {list.remove(position); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.adapter_lista_pessoa, null);

        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        TextView tvCpf = (TextView) view.findViewById(R.id.tvCpf);

        tvNome.setText(list.get(position).getUsuario());
        tvEmail.setText(list.get(position).getEmail());
        tvCpf.setText(list.get(position).getCpf());

        if(position % 2 == 0) {
            view.setBackgroundColor(R.color.abc_secondary_text_material_light);
        }

        return view;
    }
}
