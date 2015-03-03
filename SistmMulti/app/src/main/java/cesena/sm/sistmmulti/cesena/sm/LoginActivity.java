package cesena.sm.sistmmulti.cesena.sm;

import javax.mail.MessagingException;

import javax.mail.AuthenticationFailedException;


//import com.sun.mail.util.logging.MailHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cesena.sm.sistmmulti.R;

public class LoginActivity extends Activity {
    private static SqLiteConn db;
    private MyLocationListener gps;
    EditText nome;
    EditText cognome;
    EditText matricola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        {
            db = new SqLiteConn(this);

        }
        if(db.ExistStuds()==true){
            //setContentView(R.layout.activity_report);
        	Intent intent = new Intent(this, ReportActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
          //  Toast.makeText(this, "già pieno", Toast.LENGTH_LONG).show();
        }else{
            setContentView(R.layout.prova);

        }
        //inizio on create

        nome=(EditText)findViewById(R.id.btn_poids2);
        matricola=(EditText)findViewById(R.id.btn_poids);
        cognome=(EditText)findViewById(R.id.btn_poids3);
    }
    public void agg(View v) throws AuthenticationFailedException, MessagingException{
    String s="";
        if(nome.getText().toString().compareTo(s)==0||cognome.getText().toString().compareTo(s)==0||matricola.getText().toString().compareTo(s)==0) {
            Toast.makeText(this, "compilare tutti i campi previsti..", Toast.LENGTH_LONG).show();
        }
        else{
            Student stud = new Student(1, nome.getText().toString() + " " + cognome.getText().toString(), 0, matricola.getText().toString());
            db.addStud(stud);
           // Toast.makeText(this, "" + db.getStud(1).toString() + "", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ReportActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }       // alertMy("ciaociao","bobobobo");

       // checkMailTrue("da.asdas");
    }
    public void alertMy(String sTitle, String sMessage){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this); // activity
        builder.setTitle(sTitle)
                .setMessage(sMessage)
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("NO",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert = builder.create();
        alert.show();

    }
    public boolean checkMailTrue(String email){
        boolean vediamo=false;
        for(int i=0;i<email.length();i++){
            String l=".";
            if(Character.toString(email.charAt(i)).compareTo(l)==0){
                vediamo=true;
               // Toast.makeText(LoginActivity.this, "presente il punto ", Toast.LENGTH_LONG).show();
            }
        }
        return vediamo;
    }
}





