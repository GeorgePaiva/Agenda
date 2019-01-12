package br.com.georgepaiva.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.georgepaiva.agenda.modelo.Aluno;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoEmail;
    private final RatingBar campoNota;
    private final ImageView campoFoto;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = activity.findViewById(R.id.formulario_nome);
        campoEndereco = activity.findViewById(R.id.formulario_endereco);
        campoTelefone = activity.findViewById(R.id.formulario_telefone);
        campoEmail = activity.findViewById(R.id.formulario_email);
        campoNota = activity.findViewById(R.id.formulario_nota);
        campoFoto = activity.findViewById ( R.id.formulario_foto );
        aluno = new Aluno();
    }

    public Aluno pegarAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        aluno.setCaminhoFoto ( (String) campoFoto.getTag () );
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText (aluno.getNome ());
        campoEndereco.setText (aluno.getEndereco());
        campoTelefone.setText (aluno.getTelefone());
        campoEmail.setText (aluno.getEmail());
        campoNota.setProgress (aluno.getNota().intValue ());
        carregaImagem ( aluno.getCaminhoFoto () );
        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto!= null) {
            Bitmap bitmap = BitmapFactory.decodeFile ( caminhoFoto );
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap ( bitmap, 300, 300, true );
            campoFoto.setImageBitmap ( bitmapReduzido );
            campoFoto.setScaleType ( ImageView.ScaleType.FIT_XY );
            campoFoto.setTag ( caminhoFoto );
        }
    }
}
