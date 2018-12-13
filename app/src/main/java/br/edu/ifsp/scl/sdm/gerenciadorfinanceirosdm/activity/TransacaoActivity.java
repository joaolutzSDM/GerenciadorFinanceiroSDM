package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter.TransacaoAdapter;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter.listener.TransacaoClickListener;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Transacao;

public class TransacaoActivity extends AppCompatActivity implements TransacaoClickListener {

    private Conta conta;

    private TextView emptyListTransacaoTextView;
    private RecyclerView transacoesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacao);
        Toolbar toolbar = findViewById(R.id.toolbarTransacao);

        emptyListTransacaoTextView = findViewById(R.id.emptyListTransacaoTextView);
        transacoesRecyclerView = findViewById(R.id.transacoesRecyclerView);
        transacoesRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        conta = (Conta) getIntent().getExtras().getSerializable("conta");
        toolbar.setTitle(conta.getNome());

        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fabAddTransacao);
        fab.setOnClickListener(view -> showAddTransacaoPopup());
        carregarTransacoes();
    }

    private void carregarTransacoes() {
        if(conta.getTransacoes().size() > 0) {
            emptyListTransacaoTextView.setVisibility(View.INVISIBLE);
            TransacaoAdapter adapter = new TransacaoAdapter(conta.getTransacoes(),this);
            transacoesRecyclerView.setAdapter(adapter);
        }
    }

    private void showAddTransacaoPopup() {

    }

    @Override
    public void onTransacaoClick(Transacao transacao) {

    }

}
