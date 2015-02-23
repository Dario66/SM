package cesena.sm.sistmmulti;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ReportActivity extends Activity {

    private static SqLiteConn db;
    private MyLocationListener gps;
    EditText d;
    EditText d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        {
            db = new SqLiteConn(this);

        }
        d=(EditText)findViewById(R.id.editbarriera);
        d2=(EditText)findViewById(R.id.editdesc);
    }



    double a;
    double b;
    public void makeRepo(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this); // activity
        builder.setTitle("Nuovo Report")
                .setMessage("sicuro di procedere alla creazione di un nuovo report? assicurarsi di avere la connessione a internet e il gps abilitato nel telefono..per continuare premere Ok.")
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        try{
                            gps=new MyLocationListener(ReportActivity.this);
                            //if(gps.isNetworkEnabled){
                            Location j;

                            j=gps.getLocation();
                            a=j.getLatitude();
                            b=j.getLongitude();


                            Repos g=new Repos(d.getText().toString(),d2.getText().toString(),Double.toString(a)+" "+Double.toString(b));
                            db.addRepo(g);
                            //Toast.makeText(this, "presi.."+a+"_"+b+"", Toast.LENGTH_SHORT).show();
                            Toast.makeText(ReportActivity.this, "report creato con successo!! ", Toast.LENGTH_LONG).show();
                            //}

                        }catch(Exception s){
                            //Toast.makeText(this, ""+s.toString()+"", Toast.LENGTH_SHORT).show();
                            Toast.makeText(ReportActivity.this, "abilitare il Gps ..", Toast.LENGTH_SHORT).show();
                        }

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
    public void esci(View v){
        List<Repos> s=db.getAllRepos();
        for(Repos h:s){
            db.deleteRepos(h);
        }
        List<Student> st=db.getAllStuds();
        for(Student j:st){
            db.deleteStuds(j);
        }
       // db.deleteAllRepos();
        Toast.makeText(this, "account eliminato con successo..", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
   



    public void consegna(View v){
        Intent intent = new Intent(this, SendMailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
