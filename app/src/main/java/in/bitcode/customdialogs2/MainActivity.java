package in.bitcode.customdialogs2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtText;
    private Button btnEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
    }

    private void initViews() {
        edtText = findViewById(R.id.edtText);
        btnEditText = findViewById(R.id.btnEditText);
    }

    private void setupListeners() {
        btnEditText.setOnClickListener(new BtnEditTextClickListener());


    }

    private class BtnEditTextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //TextEditorDialog textEditorDialog = new TextEditorDialog(MainActivity.this);
            TextEditorDialog textEditorDialog = new TextEditorDialog(
                    MainActivity.this,
                    edtText.getText().toString()
            );
            //textEditorDialog.setText(edtText.getText().toString());
            //textEditorDialog.setOnTextEditedListener(new MyOnTextEditedListener());
            textEditorDialog.setOnTextEditedListener(
                    new TextEditorDialog.OnTextEditedListener() {
                        @Override
                        public void onTextEdited(String text) {
                            edtText.setText(text);
                        }
                    }
            );
            textEditorDialog.show();
        }
    }

    private class MyOnTextEditedListener implements TextEditorDialog.OnTextEditedListener {
        @Override
        public void onTextEdited(String text) {
            edtText.setText(text);
        }
    }
}




