package in.bitcode.customdialogs2;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TextEditorDialog extends Dialog {

    private EditText edtText;
    private RadioButton radioUppercase, radioLowercase, radioInitCap;
    private CheckBox chkReverse;
    private Button btnSet;

    private String text;

    public interface OnTextEditedListener {
        void onTextEdited(String text);
    }

    private OnTextEditedListener onTextEditedListener = null;

    public void setOnTextEditedListener(OnTextEditedListener onTextEditedListener) {
        this.onTextEditedListener = onTextEditedListener;
    }

    public TextEditorDialog(Context context, String text) {
        super(context);

        setContentView(R.layout.text_editor_dialog);
        initViews();

        setupListeners();

        this.text = text;
        edtText.setText(text);
    }

    public TextEditorDialog(Context context) {
        super(context);
        setContentView(R.layout.text_editor_dialog);
        initViews();
    }

    public void setText(String text) {
        this.text = text;
        edtText.setText(text);
    }

    private void setupListeners() {
        //1
        /*radioUppercase.setOnCheckedChangeListener(
                new RadioUppercaseOnCheckedChangeListener()
        )*/;
        RadioOptionsOnCheckedChangeListener radioOptionsOnCheckedChangeListener =
                new RadioOptionsOnCheckedChangeListener();

        radioUppercase.setOnCheckedChangeListener(radioOptionsOnCheckedChangeListener);
        radioLowercase.setOnCheckedChangeListener(radioOptionsOnCheckedChangeListener);
        radioInitCap.setOnCheckedChangeListener(radioOptionsOnCheckedChangeListener);

        chkReverse.setOnCheckedChangeListener(
                new ChkReverseCheckedChangeListener()
        );

        btnSet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onTextEditedListener != null) {
                            onTextEditedListener.onTextEdited(text);
                        }
                        dismiss();

                    }
                }
        );
    }

    private class ChkReverseCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                char [] chars = text.toCharArray();
                for(int i = 0, j = chars.length-1; i < j; i++, j--) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
                text = new String(chars, 0, chars.length);
                edtText.setText(text);


        }
    }

    private class RadioOptionsOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

            text = edtText.getText().toString();

            switch (compoundButton.getId()) {
                case R.id.radioUppercase:
                    if(isChecked) {
                        text = text.toUpperCase();
                    }
                    break;
                case R.id.radioLowercase:
                    if(isChecked) {
                        text = text.toLowerCase();
                    }
                    break;
                case R.id.radioInitCap:
                    if(isChecked) {
                        text = text.substring(0, 1).toUpperCase() +  text.substring(1, text.length()).toLowerCase();
                    }
                    break;
            }
            edtText.setText(text);

        }
    }

    /*private class RadioUppercaseOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if(isChecked) {
                text = text.toUpperCase();
                edtText.setText(text);
            }

        }
    }*/


    private void initViews() {
        edtText = findViewById(R.id.edtText);

        radioUppercase = findViewById(R.id.radioUppercase);
        radioLowercase = findViewById(R.id.radioLowercase);
        radioInitCap = findViewById(R.id.radioInitCap);

        chkReverse = findViewById(R.id.chkReverse);

        btnSet = findViewById(R.id.btnSet);
    }
}
