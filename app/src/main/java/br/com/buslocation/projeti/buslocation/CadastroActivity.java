package br.com.buslocation.projeti.buslocation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private TextInputLayout tNome;
    private TextInputLayout tNumero;
    private TextInputLayout tEmail;
    private TextInputLayout tSenha;
    private TextInputLayout tCSenha;
    private TextView txtAlert;
    private Button bEntrar;
    private Button bFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mAuth = FirebaseAuth.getInstance();

        tNome = findViewById(R.id.reg_nome);
        tNumero = findViewById(R.id.reg_numero);
        tEmail = findViewById(R.id.reg_email);
        tSenha = findViewById(R.id.reg_senha);
        tCSenha = findViewById(R.id.reg_csenha);
        txtAlert = findViewById(R.id.reg_alert);
        bEntrar = findViewById(R.id.reg_entrar);
        bFacebook = findViewById(R.id.reg_facebook);

        bEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = tNome.getEditText().getText().toString();
                String numero = tNumero.getEditText().getText().toString();
                String email = tEmail.getEditText().getText().toString();
                String senha = tSenha.getEditText().getText().toString();
                String csenha = tCSenha.getEditText().getText().toString();

                if (senha.equals(csenha)) {
                    novoUsuario(nome, numero, email, senha);
                } else {
                    txtAlert.setText("As senhas não condizem! Por favor, reformule as senhas.");
                }
            }
        });
    }

    private void novoUsuario(String nome, String numero, String email, String senha) {
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(CadastroActivity.this, MainActivity.class));
                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();
                        } else {
                            txtAlert.setText("Email ja cadastrado! Por favor, entre com outro endereço de email para realizar um novo cadastro");
                        }
                    }
                });
    }
}