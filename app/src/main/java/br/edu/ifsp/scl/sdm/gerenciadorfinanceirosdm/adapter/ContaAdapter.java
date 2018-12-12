package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta;

public class ContaAdapter extends RecyclerView.Adapter<ContaAdapter.ContaViewHolder> {

    private List<Conta> contas;
    private ItemClickListener clickListener;

    public ContaAdapter(List<Conta> contas, ItemClickListener clickListener) {
        this.contas = contas;
        this.clickListener = clickListener;
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

    public  class ContaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        final TextView nomeContaTextView;
        final TextView saldoContaTextView;
        final ImageView statusContaImageView;

        ContaViewHolder(View view) {
            super(view);
            nomeContaTextView = view.findViewById(R.id.nomeContaTextView);
            saldoContaTextView = view.findViewById(R.id.saldoContaTextView);
            statusContaImageView = view.findViewById(R.id.statusContaImageView);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onContaClick(contas.get(getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            return clickListener.onContaLongClick(contas.get(getAdapterPosition()));
        }
    }

    public interface ItemClickListener {
        void onContaClick(Conta conta);
        boolean onContaLongClick(Conta conta);
    }

}
