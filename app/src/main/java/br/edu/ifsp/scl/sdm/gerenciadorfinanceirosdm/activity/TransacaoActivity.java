package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.BigDecimal;

import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter.TransacaoAdapter;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter.listener.TransacaoClickListener;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.NaturezaTransacao;
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
        TransacaoAdapter adapter = new TransacaoAdapter(conta.getTransacoes(), this);
        transacoesRecyclerView.setAdapter(adapter);
        if (conta.getTransacoes().size() > 0) {
            emptyListTransacaoTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("conta", conta);
        setResult(0, i);
        super.onBackPressed();
    }

    private void showAddTransacaoPopup() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.popup_add_transacao, null);

        EditText nomeTransacaoEditText = dialogView.findViewById(R.id.nomeTransacaoEditText);
        EditText tipoTransacaoEditText = dialogView.findViewById(R.id.tipoTransacaoEditText);
        EditText valorTransacaoEditText = dialogView.findViewById(R.id.valorTransacaoEditText);
        RadioGroup naturezaTransacaoRadioGroup = dialogView.findViewById(R.id.naturezaTransacaoRadioGroup);

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle(getString(R.string.adicionar_transacao));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(dialogView, 10, 20, 10, 5);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.btnOK), (dialog1, which) -> {
            Transacao tr = new Transacao();
            tr.setNome(nomeTransacaoEditText.getText().toString());
            tr.setTipo(tipoTransacaoEditText.getText().toString());
            BigDecimal valorTransacao = new BigDecimal(valorTransacaoEditText.getText().toString());
            tr.setValor(valorTransacao);
            if (naturezaTransacaoRadioGroup.getCheckedRadioButtonId() == R.id.creditoRadioButton) {
                tr.setNaturezaTransacao(NaturezaTransacao.CREDITO);
                conta.creditar(valorTransacao);
            } else if (naturezaTransacaoRadioGroup.getCheckedRadioButtonId() == R.id.debitoRadioButton) {
                tr.setNaturezaTransacao(NaturezaTransacao.DEBITO);
                conta.debitar(valorTransacao);
                tr.setValor(tr.getValor().multiply(new BigDecimal(-1)));
            } else {
                tr.setNaturezaTransacao(NaturezaTransacao.CREDITO);
            }
            if (emptyListTransacaoTextView.getVisibility() == View.VISIBLE) {
                emptyListTransacaoTextView.setVisibility(View.INVISIBLE);
            }
            conta.getTransacoes().add(tr);
            transacoesRecyclerView.getAdapter().notifyDataSetChanged();
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.btnCancelar), (dialog2, which) -> dialog2.dismiss());
        dialog.show();
    }

    @Override
    public void onTransacaoClick(Transacao transacao) {
        showListDialog(R.array.gerenciar_transacao, (dialog, which) -> {
            switch (transacao.getNaturezaTransacao()) {
                case CREDITO:
                    conta.debitar(transacao.getValor());
                case DEBITO:
                    conta.creditar(transacao.getValor().multiply(new BigDecimal(-1)));
            }
            conta.getTransacoes().remove(transacao);
            transacoesRecyclerView.getAdapter().notifyDataSetChanged();
            if(transacoesRecyclerView.getAdapter().getItemCount() == 0) {
                emptyListTransacaoTextView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showListDialog(Integer listResId, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(listResId, listener);
        builder.show();
    }

}