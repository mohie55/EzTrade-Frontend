package ma4174h.gre.ac.uk.eztrade;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import java.util.concurrent.CopyOnWriteArrayList;

public class LoadingDialog {

    private Activity activity;
    private Context context;
    private AlertDialog dialog;

    public LoadingDialog(Context context,Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void startProgressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }
}
