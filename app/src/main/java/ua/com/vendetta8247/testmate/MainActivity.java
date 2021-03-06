package ua.com.vendetta8247.testmate;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Color;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.text.InputType;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.ipaulpro.afilechooser.utils.FileUtils;

        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private RichEditor mEditor;
    private TextView mPreview;
    String htmlOutput="";
    String m_text = "";
    final int REQUEST_CHOOSER = 1228;
    String imageFilePath = "";


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor.setEditorHeight(600);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(R.color.blackFont);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        //    mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("Insert text here...");

        if(getIntent().getStringExtra("content") !=null)
            mEditor.setHtml(getIntent().getStringExtra("content"));

       // mPreview = (TextView) findViewById(R.id.preview);
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override public void onTextChange(String text) {
          //      mPreview.setText(text);
                htmlOutput = new String(text);
            }
        });

        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.undo();
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.redo();
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setItalic();
            }
        });

        findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSubscript();
            }
        });

        findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setSuperscript();
            }
        });

        findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });

        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setUnderline();
            }
        });

        findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });

        findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });

        findViewById(R.id.action_heading3).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(3);
            }
        });

        findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });

        findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });

        findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });

        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            boolean isChanged;

            @Override public void onClick(View v) {
                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            boolean isChanged;

            @Override public void onClick(View v) {
                mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                isChanged = !isChanged;
            }
        });


        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });

        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });

        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });

        findViewById(R.id.action_insert_image).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                File sdCard = Environment.getExternalStorageDirectory();
                File dest = new File(sdCard.getAbsolutePath() + "/TestMate/Editing/");


                Intent getContentIntent = FileUtils.createGetContentIntent();

                Intent intent = Intent.createChooser(getContentIntent, "Select a file");
                startActivityForResult(intent, REQUEST_CHOOSER);
                System.out.println("IMAGE FILE PATH" + imageFilePath);
            }
        });

        findViewById(R.id.action_insert_link).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Add a Link");

                final String[] m_string = new String[2];
                final LinearLayout ll = new LinearLayout(v.getContext());
                ll.setOrientation(LinearLayout.VERTICAL);
                final EditText input = new EditText(v.getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setTextColor(0xff000000);
                final EditText input2 = new EditText(v.getContext());
                input2.setInputType(InputType.TYPE_CLASS_TEXT);
                input2.setTextColor(0xff000000);
                TextView text1 = new TextView(v.getContext());
                text1.setText("Link address");
                text1.setTextColor(0xff000000);
                TextView text2 = new TextView(v.getContext());
                text2.setText("Link name");
                text2.setTextColor(0xff000000);
                ll.addView(text1);
                ll.addView(input);
                ll.addView(text2);
                ll.addView(input2);
                builder.setView(ll);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_string[0] = input.getText().toString();
                        m_string[1] = input2.getText().toString();
                        if(!m_string[0].contains("http://")) m_string[0] = "http://" + m_string[0];
                        mEditor.insertLink(m_string[0], m_string[1]);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();


//                AlertDialog.Builder builder2 = new AlertDialog.Builder(v.getContext());
//                builder2 = new AlertDialog.Builder(v.getContext());
//                builder2.setTitle("Link name");
//
//                final EditText input2 = new EditText(v.getContext());
//                input2.setInputType(InputType.TYPE_CLASS_TEXT);
//                builder2.setView(input2);
//
//                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        m_string[1] = input2.getText().toString();
//
//                    }
//                });
//                builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//                builder2.show();


            }
        });
        findViewById(R.id.action_save).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(final View v) {
                System.out.println(m_text.length());

                if (m_text.length() == 0) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("File name");


                    final EditText input = new EditText(v.getContext());
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            m_text = input.getText().toString();

                            File sdCard = v.getContext().getFilesDir();
                            File dir = new File(sdCard.getAbsolutePath() + "/TestMate/Editing/");
                            if (!dir.exists())
                                dir.mkdir() ;
                            File f = new File(dir + m_text);
                            System.out.println(dir + m_text);
                            try {
                                if (htmlOutput.length() != 0) {
                                    FileWriter fw = new FileWriter(f);
                                    fw.write(htmlOutput);
                                    fw.flush();
                                    fw.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();


                }

                else
                {
                    File sdCard = v.getContext().getFilesDir();
                    File dir = new File(sdCard.getAbsolutePath() + "/TestMate/Editing");

                    if (!dir.exists())
                        dir.mkdirs();
                    File f = new File(sdCard.getAbsolutePath() + "/TestMate/" + m_text);
                    System.out.println(sdCard.getAbsolutePath() + "/TestMate/" + m_text);
                    try {
                        if (htmlOutput.length() != 0) {
                            FileWriter fw = new FileWriter(f);
                            fw.write(htmlOutput);
                            fw.flush();
                            fw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {

                    final Uri uri = data.getData();
                    imageFilePath = FileUtils.getPath(this, uri);
                    InputStream inStream = null;
                    OutputStream outStream = null;
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/TestMate/Editing/";

                    try{

                        File afile =new File(imageFilePath);
                        File dir = new File(path);
                        if(!dir.exists())
                            dir.mkdir();
                        File bfile =new File(path + "img.png");

                        inStream = new FileInputStream(afile);
                        outStream = new FileOutputStream(bfile);

                        byte[] buffer = new byte[1024];

                        int length;
                        while ((length = inStream.read(buffer)) > 0){

                            outStream.write(buffer, 0, length);

                        }

                        inStream.close();
                        outStream.close();

                        System.out.println("File is copied successfully!");

                    }catch(IOException e){
                        e.printStackTrace();
                    }

                    System.out.println("IMAGE FILE PATH" + imageFilePath);
                    mEditor.insertImage(Environment.getExternalStorageDirectory().getAbsolutePath() + "/TestMate/Editing/img.png", "");
                }
                break;
        }
    }
}
