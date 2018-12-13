package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter.listener.TransacaoClickListener;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.NaturezaTransacao;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Transacao;

public class TransacaoAdapter extends RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder> {

    private List<Transacao> transacoes;
    private TransacaoClickListener listener;

    public TransacaoAdapter(List<Transacao> transacoes, TransacaoClickListener listener) {
        this.transacoes = transacoes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TransacaoAdapter.TransacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transacao_item, parent, false);
        return new TransacaoAdapter.TransacaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransacaoViewHolder holder, int i) {
        Transacao tr  = transacoes.get(i);
        holder.nomeTransacaoTextView.setText(tr.getNome());
        holder.tipoTransacaoTextView.setText(tr.getTipo());
        holder.valorTransacaoTextView.setText(Conta.getCurrencyBigDecimal(tr.getValor()));
        if (tr.getNaturezaTransacao() == NaturezaTransacao.DEBITO) {
            holder.valorTransacaoTextView.setTextColor(Color.parseColor("#e10000"));
        }
    }

    @Override
    public int getItemCount() {
        return transacoes.size();
    }

    class TransacaoViewHolder extends RecyclerView.ViewHolder {

        final TextView nomeTransacaoTextView;
        final TextView tipoTransacaoTextView;
        final TextView valorTransacaoTextView;

        TransacaoViewHolder(View view) {
            super(view);
            nomeTransacaoTextView = view.findViewById(R.id.nomeTransacaoTextView);
            tipoTransacaoTextView = view.findViewById(R.id.tipoTransacaoTextView);
            valorTransacaoTextView = view.findViewById(R.id.valorTransacaoTextView);
            view.setOnClickListener((View v) -> listener.onTransacaoClick(transacoes.get(getAdapterPosition())));
        }
    }

}
