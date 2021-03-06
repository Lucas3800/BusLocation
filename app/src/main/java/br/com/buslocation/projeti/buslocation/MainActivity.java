package br.com.buslocation.projeti.buslocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        //Toolbar
        mToolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Bus Location");
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            sendToStart();
        }
    }

    private void sendToStart() {
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.main_menu_config) {
            Toast.makeText(getApplicationContext(), "Configura ainda não :(", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.main_menu_sair) {
            FirebaseAuth.getInstance().signOut();
            sendToStart();
        }
        return true;
    }
}
