package br.com.jonathan.challenge.ui.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import br.com.jonathan.challenge.R;

public class CustomDialog {

    public static final String TITLE_DEFAULT_PROBLEM = "Ops! algo deu errado :(";
    public static final String TITLE_SESSION_CLOSED = "Sessão Encerrada";
    public static final String TITLE_PROGRESS_AUTHENTICATION = "Autenticação do Usuário";
    public static final String TITLE_AUTHENTICATION_MESSAGE = "Autenticando...";

    /**
     * Show a progress Dialog
     *
     * @param context
     * @param displayMessage
     * @param displayTitle
     * @return
     */
    public static AlertDialog showProgressDialog(Context context, String displayTitle, String displayMessage) {

//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View popUpView = layoutInflater.inflate(R.layout.dialog_progress_dialog, null);
//        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setView(popUpView);
//        builder.setCancelable(false);
//
//        TextView textViewTitle = popUpView.findViewById(R.id.text_view_title);
//        TextView textViewDescription = popUpView.findViewById(R.id.text_view_description);
//
//        // Show the title if displayTitle is not empty
//        if(!TextUtils.isEmpty(displayTitle)) {
//            ConstraintLayout constraintLayout = popUpView.findViewById(R.id.constraint_layout_title);
//            constraintLayout.setVisibility(View.VISIBLE);
//            textViewTitle.setText(displayTitle);
//        }
//
//        textViewDescription.setText(displayMessage);
//        textViewDescription.setMovementMethod(new ScrollingMovementMethod());
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        return alertDialog;
        return null;
    }
}
