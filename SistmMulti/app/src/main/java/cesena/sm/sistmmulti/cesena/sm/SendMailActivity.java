package cesena.sm.sistmmulti.cesena.sm;

import java.net.URL;
import java.net.URLConnection;
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
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cesena.sm.sistmmulti.R;

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



       /* Intent addAccountIntent = new Intent(Settings.ACTION_ADD_ACCOUNT);
        addAccountIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        addAccountIntent.putExtra(Settings.EXTRA_AUTHORITIES, new String[]{"com.app.SistMulti"});
        startActivity(addAccountIntent);*/

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

                                if(isOnline()){


                                        int si = 0;
                                        try {
                                            Log.i("SendMailActivity", "Send Button Clicked.");
                                            String fromEmail =uno.getText().toString();
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

                                        }

                                }else{
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
                alert.setMessage("se si procede in questo modo verrà cancellato il db. Continuare?");
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
    public boolean checkMailTrue(String email){
        boolean vediamo=false;
        for(int i=0;i<email.length();i++){
            String l=".";
            if(Character.toString(email.charAt(i)).compareTo(l)==0){
                vediamo=true;
                //Toast.makeText(SendMailActivity.this, "presente il punto ", Toast.LENGTH_LONG).show();
            }
        }
        return vediamo;
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
    public boolean isConnectedToServer() {
        try{
            URL myUrl = new URL("pod51002.outlook.com");
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(3000);
            connection.connect();
            return true;
        } catch (Exception e) {
            // Handle your exceptions
            return false;
        }
    }
}