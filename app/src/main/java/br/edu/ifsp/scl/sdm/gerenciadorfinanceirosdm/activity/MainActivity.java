package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.R;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter.ContaAdapter;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.adapter.listener.ContaClickListener;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Conta;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.NaturezaTransacao;
import br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model.Transacao;

public class MainActivity extends AppCompatActivity implements ContaClickListener {

    private static List<Conta> contas;
    private TextView emptyListTextView;
    private RecyclerView contasRecyclerView;
    private TextView saldoTotalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApp();
    }

    private void initApp() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contas = new ArrayList<>();
        emptyListTextView = findViewById(R.id.emptyListTextView);
        contasRecyclerView = findViewById(R.id.contasRecyclerView);
        saldoTotalTextView = findViewById(R.id.saldoTotalTextView);

        ContaAdapter adapter = new ContaAdapter(contas,this);
        contasRecyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAddConta);
        fab.setOnClickListener(view -> showAddContaPopup());
        atualizarSaldoTotal();
    }

    private void showAddContaPopup() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.popup_add_conta, null);
        EditText nomeContaEditText = dialogView.findViewById(R.id.nomeContaEditText);
        EditText saldoInicialEditText = dialogView.findViewById(R.id.saldoInicialEditText);
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle(getString(R.string.adicionar_conta));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(dialogView, 10,20,10,5);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.btnOK), (dialog1, which) -> {
            Conta conta = new Conta();
            conta.setNome(nomeContaEditText.getText().toString());
            conta.setSaldo(new BigDecimal(saldoInicialEditText.getText().toString()));
            contas.add(conta);
            if(emptyListTextView.getVisibility() == View.VISIBLE) {
                emptyListTextView.setVisibility(View.INVISIBLE);
            }
            contasRecyclerView.getAdapter().notifyDataSetChanged();
            atualizarSaldoTotal();
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.btnCancelar), (dialog2, which) -> dialog2.dismiss());
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContaClick(Conta conta) {
        Intent i = new Intent(this, TransacaoActivity.class);
        i.putExtra("conta", conta);
        startActivityForResult(i, 0);
    }

    @Override
    public boolean onContaLongClick(Conta conta) {
        showListDialog(R.array.gerenciar_conta, (dialog, which) -> {
            contas.remove(conta);
            atualizarSaldoTotal();
        });
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            Conta conta = (Conta) data.getSerializableExtra("conta");
            //atualiza a conta
            contas.set(contas.indexOf(conta), conta);
            contasRecyclerView.getAdapter().notifyDataSetChanged();
            atualizarSaldoTotal();
        }
    }

    private void atualizarSaldoTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for(Conta c : contas) {
            total = total.add(c.getSaldo());
        }
        saldoTotalTextView.setText(Conta.getCurrencyBigDecimal(total));
    }

    private void showListDialog(Integer listResId, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(listResId, listener);
        builder.show();
    }

}
