package cesena.sm.sistmmulti;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendMailActivity extends Activity {
    private static SqLiteConn db;
    private MyLocationListener gps;
    EditText uno;
    EditText due;
    Button canc;
    List<Repos>listRep;
    Student k;
    String lk;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        {
            db = new SqLiteConn(this);

        }
        uno=(EditText)findViewById(R.id.btn_uno);
        due=(EditText)findViewById(R.id.btn_due);
        canc=(Button)findViewById(R.id.btn_canc);
       listRep=db.getAllRepos();
        k=db.getStud(1);
        lk = k._testo+" "+k._testo2;
        StrictMode.ThreadPolicy strictModeThreadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(strictModeThreadPolicy);
        final Button send = (Button) this.findViewById(R.id.btn_coco);
        canc=(Button)findViewById(R.id.btn_canc);


        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(SendMailActivity.this);
                alert.setMessage("se si procede in questo modo verrà inviata una mail contentente i report svolti. Continuare?");
                alert.setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                if(isOnline()) {
                                    int si = 0;
                                    try {
                                        Log.i("SendMailActivity", "Send Button Clicked.");
                                        String fromEmail = uno.getText().toString();
                                        String fromPassword = due.getText().toString();//"1928dgieee";//
                                        String toEmails = "catia.prandi2@unibo.it";
                                        List toEmailList = Arrays.asList(toEmails
                                                .split("\\s*,\\s*"));
                                        Log.i("SendMailActivity", "To List: " + toEmailList);
                                        String emailSubject = "Corso di Sistemi Multimediali, consegna dello studente: " + lk;
                                        String emailBody = listRep.toString();//"asfafdsgsdfsdfsdf";
                                        new SendTaskMail(SendMailActivity.this).execute(fromEmail,
                                                fromPassword, toEmailList, emailSubject, emailBody);
                                        si = 1;

                                    } catch (Exception a) {

                                        // Toast.makeText(getApplicationContext(),
                                        //      "errore", Toast.LENGTH_SHORT).show();

                                    }
                                }
                                else{
                                    Toast.makeText(SendMailActivity.this, "abilitare la connessione internet..", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                alert.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                String str = "no";
                                Toast.makeText(getApplicationContext(),
                                        str, Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();
            }
        });

        canc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {


                AlertDialog.Builder alert = new AlertDialog.Builder(SendMailActivity.this);
                alert.setMessage("se si procede in questo modo verrà inviata una mail contentente i report svolti. Continuare?");
                alert.setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                    cancella();
                                Toast.makeText(getApplicationContext(),
                                        "db cancellato con successo..", Toast.LENGTH_SHORT).show();

                            }
                        });
                alert.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {


                            }
                        });
                alert.show();
            }
        });



}


    public void cancella(){
        List<Repos> k=db.getAllRepos();
        for(Repos l:k){
            db.deleteRepos(l);
        }
        List<Student>p=db.getAllStuds();
        for(Student ll:p){
            db.deleteStuds(ll);
        }
        Intent intent = new Intent(SendMailActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}