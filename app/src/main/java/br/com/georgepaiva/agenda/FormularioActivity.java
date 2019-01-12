package br.com.georgepaiva.agenda;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import br.com.georgepaiva.agenda.dao.AlunoDAO;
import br.com.georgepaiva.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);
        Intent intent = getIntent ();
        Aluno aluno = (Aluno) intent.getSerializableExtra ( "aluno");
        if (aluno!= null) {
            helper.preencheFormulario(aluno);
        }

        final View botaoFoto = findViewById ( R.id.formulario_botao_foto );
        botaoFoto.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent ( MediaStore.ACTION_IMAGE_CAPTURE );
                String caminhoFoto = getExternalFilesDir ( null ) + "/" + System.currentTimeMillis () + ".jpg";
                File arquivoFoto = new File ( caminhoFoto );
                intentCamera.putExtra ( MediaStore.EXTRA_OUTPUT, Uri.fromFile ( arquivoFoto ) );
                startActivity ( intentCamera );
            }
        } );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegarAluno();
                AlunoDAO dao = new AlunoDAO(this);
                if(aluno.getId ()!=null){
                    dao.altera(aluno);
                }else{
                    dao.insere(aluno);
                }
                dao.close();
                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome()+ " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
