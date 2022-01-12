package com.example.myapplication;

import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddProductActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    EditText editTextname, editTextTextdescription,editTextTextprice;
    Button addBtn;
    TextView component;
    EditText textViewcomponent1,textViewcomponent2,textViewcomponent3,textViewcomponent4,textViewcomponent5;

    Map<String, Object> addproductmap;

    ImageView product_image;

    List<String> components;

    public static AlertDialog desDialog = null;

    public static final int CAMERA_REQUEST = 1888;
    public static final int GALLERY_REQUEST = 100;

    String product_img="";
    String filePath = "";
    File file2;


    StorageReference storageRef;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //Definitions
        editTextname=findViewById(R.id.editTextnames);
        editTextTextdescription=findViewById(R.id.editTextTextdescription);
        editTextTextprice=findViewById(R.id.editTextTextprice);
        addBtn = findViewById(R.id.button);

        component=findViewById(R.id.component);
        product_image=findViewById(R.id.product_image);

        textViewcomponent1=findViewById(R.id.textViewcomponent1);
        textViewcomponent2=findViewById(R.id.textViewcomponent2);
        textViewcomponent3=findViewById(R.id.textViewcomponent3);
        textViewcomponent4=findViewById(R.id.textViewcomponent4);
        textViewcomponent5=findViewById(R.id.textViewcomponent5);

        addproductmap=new HashMap<>();

        components=new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();

        storageRef = storage.getReference();

        product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showGallery(AddProductActivity.this);

            }
        });


        component.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewcomponent1.setVisibility(View.VISIBLE);

                if (!textViewcomponent1.getText().toString().isEmpty()){
                    textViewcomponent2.setVisibility(View.VISIBLE);
                }
                if (!textViewcomponent2.getText().toString().isEmpty()){
                    textViewcomponent3.setVisibility(View.VISIBLE);
                }
                if (!textViewcomponent3.getText().toString().isEmpty()){
                    textViewcomponent4.setVisibility(View.VISIBLE);
                }
                if (!textViewcomponent4.getText().toString().isEmpty()){
                    textViewcomponent5.setVisibility(View.VISIBLE);
                }

            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Save input info in Strings
                String name = editTextname.getText().toString();
                String desc = editTextTextdescription.getText().toString();
                String price = editTextTextprice.getText().toString();

                components.add(textViewcomponent1.getText().toString());
                components.add(textViewcomponent2.getText().toString());
                components.add(textViewcomponent3.getText().toString());
                components.add(textViewcomponent4.getText().toString());
                components.add(textViewcomponent5.getText().toString());

                //Check if Fields are empty
                if (name.isEmpty()){
                    editTextname.setError("Required");
                }else if (desc.isEmpty()){
                    editTextTextdescription.setError("Required");
                }else if (price.isEmpty()){
                    editTextTextprice.setError("Required");
                }else {
                    addProduct(name,desc,price,components,filePath.toString());
                }
            }
        });

    }

    private void addProduct(String name,String description,String price,List compon,String productimg){

        //Add Values in the hash map to add in the Firebase Database

        addproductmap.put("name",name);
        addproductmap.put("description",description);
        addproductmap.put("price",price);
        addproductmap.put("Components",compon);
        addproductmap.put("imageproduct","gs://digital-method-235716.appspot.com"+productimg);

        // Add a new Product in the database
        db.collection("Product").add(addproductmap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                //Show Success Message
                Toast.makeText(getApplicationContext(),"Product added successfully",Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //Show Failure Message
                        Toast.makeText(getApplicationContext(),"Product not added successfully",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void savepic(String path,String name){

        StorageReference mountainsRef = storageRef.child(name);

// Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child(path);

// While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());

    }


    public static void showGallery(final Activity activitys) {
        LayoutInflater inflater = (LayoutInflater) activitys.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AlertDialog.Builder builder = new AlertDialog.Builder(activitys);
        View dialogView = inflater.inflate(R.layout.gallery, null);
        builder.setView(dialogView);
   dialogView.findViewById(R.id.Camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                captureImage(activitys);
                desDialog.cancel();
            }
        });


        dialogView.findViewById(R.id.gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, EXTERNAL_CONTENT_URI);
                activitys.startActivityForResult(i, GALLERY_REQUEST);
                desDialog.cancel();
            }
        });

        desDialog = builder.create();
        desDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        desDialog.show();
    }

    public static void captureImage(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            ContentValues values = new ContentValues(1);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
            activity.startActivityForResult(intent, CAMERA_REQUEST);

        } else {
            Toast.makeText(activity, "error_no_camera", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == 100) {
            final Uri extras = Uri.parse(data.getData().buildUpon().toString());
            if (extras != null) {
                product_img = data.getData().buildUpon().toString();
            }
        }

        try {
            filePath = getPath(AddProductActivity.this, data.getData());
        }catch (Exception e){
            e.printStackTrace();
        }

        file2 = new File(filePath);

        if(file2.exists()){


            Bitmap myBitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());

            product_image.setImageBitmap(myBitmap);

            savepic(filePath,editTextname.getText().toString());



        }


        super.onActivityResult(requestCode, resultCode, data);

    }

    public static String getPath(Activity activity, Uri selectedImaeUri) {
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = activity.managedQuery(selectedImaeUri, projection, null, null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            return cursor.getString(columnIndex);
        }

        return selectedImaeUri.getPath();
    }




}