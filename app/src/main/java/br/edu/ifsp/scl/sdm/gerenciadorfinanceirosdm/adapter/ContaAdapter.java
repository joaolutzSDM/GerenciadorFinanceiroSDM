package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter.listener.ContaClickListener;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta;

public class ContaAdapter extends RecyclerView.Adapter<ContaAdapter.ContaViewHolder> {

    private List<Conta> contas;
    private ContaClickListener listener;

    public ContaAdapter(List<Conta> contas, ContaClickListener listener) {
        this.contas = contas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conta_item, parent, false);
        return new ContaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContaViewHolder holder, int i) {
        Conta conta  = contas.get(i);
        holder.nomeContaTextView.setText(conta.getNome());
        holder.saldoContaTextView.setText(conta.getSaldoFormatado());
        holder.statusContaImageView.setImageResource(conta.isSaldoPositivo() ? R.drawable.ic_saldo_positivo : R.drawable.ic_saldo_negativo);
    }

    @Override
    public int getItemCount() {
        return contas.size();
    }

    class ContaViewHolder extends RecyclerView.ViewHolder {

        final TextView nomeContaTextView;
        final TextView saldoContaTextView;
        final ImageView statusContaImageView;

        ContaViewHolder(View view) {
            super(view);
            nomeContaTextView = view.findViewById(R.id.nomeContaTextView);
            saldoContaTextView = view.findViewById(R.id.saldoContaTextView);
            statusContaImageView = view.findViewById(R.id.statusContaImageView);
            view.setOnClickListener((View v) -> listener.onContaClick(contas.get(getAdapterPosition())));
            view.setOnLongClickListener((View v) -> listener.onContaLongClick(contas.get(getAdapterPosition())));
        }
    }

}
