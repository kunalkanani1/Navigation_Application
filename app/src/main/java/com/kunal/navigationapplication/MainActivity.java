package com.kunal.navigationapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigation;
    ActionBarDrawerToggle toggle;
    TextView textView;
    TextView text1;
    LinearLayout linearLayout;

//    Button btn, btn2;
//    ImageView img;
//    Uri selectedImageUri;

    private static final float END_SCALE = 0.7f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        text1 = findViewById(R.id.text);
        linearLayout = findViewById(R.id.linear);

//        btn = findViewById(R.id.btn);
//        btn2 = findViewById(R.id.btn2);
//        img = findViewById(R.id.img);
//
//        btn.setOnClickListener(view -> {
//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.setType("image/*");
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
//        });
//
//        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
//        StorageReference storageReference = firebaseStorage.getReference();
//
//
//        btn2.setOnClickListener(view -> {
//            if (selectedImageUri!=null) {
//                String temp = String.valueOf(selectedImageUri);
//                StorageReference storageReference1 = storageReference.child(temp);
//                storageReference1.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
        toggle.syncState();

        toolbar.setNavigationIcon(new DrawerArrowDrawable(this));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(navigation)) {
                    drawer.closeDrawer(navigation);
                } else {
                    drawer.openDrawer(navigation);
                }
            }
        });

        View myview = navigation.getHeaderView(0);
        textView = myview.findViewById(R.id.city);
        textView.setText("Profile Name");

        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                text1.setVisibility(slideOffset > 0 ? View.VISIBLE : View.GONE);
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                linearLayout.setScaleX(offsetScale);
                linearLayout.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = linearLayout.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                linearLayout.setTranslationX(xTranslation);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
//                text1.setVisibility(View.GONE);
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.l, new first()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.l2, new second()).commit();
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment f = null;
                if (item.getItemId() == R.id.name) {
                    f = new first();
                }
                if (item.getItemId() == R.id.info) {
                    f = new second();
                }
                if (item.getItemId() == R.id.contact) {

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.l, f).commit();
                drawer.closeDrawers();

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            if (resultCode == RESULT_OK) {
//                if (requestCode == 1) {
//                    selectedImageUri = data.getData();
//                    // Get the path from the Uri
//                    final String path = getPathFromURI(selectedImageUri);
//                    if (path != null) {
//                        File f = new File(path);
//                        selectedImageUri = Uri.fromFile(f);
//                    }
//                    // Set the image in ImageView
////                    ImageView((ImageView) findViewById(R.id.img)).setImageURI(selectedImageUri);
//                    img.setImageURI(selectedImageUri);
//                }
//            }
//        } catch (Exception e) {
//            Log.e("FileSelectorActivity", "File select error", e);
//        }
//    }
//
//    public String getPathFromURI(Uri contentUri) {
//        String res = null;
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
//        if (cursor.moveToFirst()) {
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            res = cursor.getString(column_index);
//        }
//        cursor.close();
//        return res;
//    }

}