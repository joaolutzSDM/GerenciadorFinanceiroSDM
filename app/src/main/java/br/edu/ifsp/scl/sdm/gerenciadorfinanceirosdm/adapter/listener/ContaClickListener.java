package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter.listener;

import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta;

public interface ContaClickListener {

    void onContaClick(Conta conta);

    boolean onContaLongClick(Conta conta);

}
