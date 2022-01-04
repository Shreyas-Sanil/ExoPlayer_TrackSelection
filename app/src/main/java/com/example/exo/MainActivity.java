package com.example.exo;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    int REQUEST_TAKE_GALLERY_VIDEO = 1;
    private static final String salt = "t784";
    private static final String cryptPassword = "12345678";
    private static final String fileToBeCrypted = "c:\\Temp\\sampleFile.conf";
    private static final String fileToBeDecrypted = "c:\\Temp\\sampleFile.conf.crypt";
    private static final String fileDecryptedOutput = "c:\\Temp\\sampleFile.conf.decrypted";
    PermissionListener permissionlistener;
    private static final int SELECT_VIDEO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button encryptVideo = findViewById(R.id.encryptVideo);

        permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_VIDEO);
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };


        encryptVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    TedPermission.with(MainActivity.this)
                            .setPermissionListener(permissionlistener)
                            .setRationaleTitle("datas")
                            .setRationaleMessage("agsss")
                            .setDeniedTitle("Permission denied")
                            .setDeniedMessage(
                                    "If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                            .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .check();

                }else{

                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, SELECT_VIDEO);

                }
            }
        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_VIDEO) {
                String selectedVideoPath = getPath(data.getData());
                if (selectedVideoPath == null) {
                   Log.d("datadddd","dnull");
                    finish();
                } else {
                    Log.d("datfffffa","file:///"+selectedVideoPath);
                    try {
                        encryptfile("file:///"+selectedVideoPath,cryptPassword);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if(cursor!=null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        else return null;
    }


        public static void encryptfile (String path, String password) throws
        IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
            FileInputStream fis = new FileInputStream(path);
            FileOutputStream fos = new FileOutputStream(path.concat(".crypt"));
            byte[] key = (salt + password).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec sks = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            int b;
            byte[] d = new byte[8];
            while ((b = fis.read(d)) != -1) {
                cos.write(d, 0, b);
                Log.d("Encrypting", "Encrypting");
            }
            Log.d("Encrypting", "Done");
            cos.flush();
            cos.close();
            fis.close();
        }
    }