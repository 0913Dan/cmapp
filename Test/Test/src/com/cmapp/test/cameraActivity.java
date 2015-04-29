package com.cmapp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class cameraActivity extends Activity {
	private static final int IMAGE_CAMERA = 100;
	private static final int IMAGE_FILE = 200;
	private Uri mPictureUri; 
	private File image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		
		Button btCamera=(Button) findViewById(R.id.btCamera);
		Button btGallery=(Button) findViewById(R.id.btGallery);
		btCamera.setOnClickListener(btListener);
		btGallery.setOnClickListener(btListener);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	private Button.OnClickListener btListener =new Button.OnClickListener (){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.btCamera){
				launchChooser("Camera");
			}
			if(v.getId()==R.id.btGallery){
				launchChooser("Gallery");
			}
			
		}
		
		
	};
	  
	private void launchChooser(String type) {  
	   
	    if(type.equals("Camera")){
	    	Intent i2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE );  
	    	String text = (String) DateFormat.format("yyyyMMddkkmmss", Calendar.getInstance()); 
	    	image=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),text+".jpg");
	    	i2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));  
	    	mPictureUri=Uri.fromFile(image);
	    	startActivityForResult(i2, IMAGE_CAMERA);  
	    }
	    if(type.equals("Gallery")){
	    	Intent intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			intent.setType("image/*");
			startActivityForResult(
					Intent.createChooser(intent, "Select File"),
					IMAGE_FILE);
	    }
	}  
	  
	@Override  
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
		   TextView imgroot= (TextView)findViewById(R.id.cameraRoot);
		   boolean needrotate=false;
	    if (requestCode == IMAGE_CAMERA  && resultCode == RESULT_OK) {  
	  
	    	ImageView iv = (ImageView) findViewById(R.id.imgCamera); 
	    	//Bundle bundle =data.getExtras();
//	    	Bitmap bmp=(Bitmap)bundle.get("data");
	    	BitmapFactory.Options option=new BitmapFactory.Options();
	    	option.inJustDecodeBounds =true;
	    	BitmapFactory.decodeFile(mPictureUri.getPath(),option);
	    	
	    	int iw=option.outWidth;
	    	int ih=option.outHeight;
	    	int vw=iv.getWidth();
	    	int vh=iv.getHeight();
	    	int scale=0;
	    	if(iw<ih){
	    		needrotate=false;
	    		scale=Math.min(iw/vw, ih/vh);
	    		
	    	}else{
	    		needrotate=true;
	    		scale=Math.min(ih/vw, iw/vh);
	    	}
	    	option.inSampleSize=scale;
	    	option.inPurgeable=true;
	    	option.inJustDecodeBounds=false;
	    	Bitmap bmp=BitmapFactory.decodeFile(mPictureUri.getPath(),option);
	    	if(needrotate){
	    		Matrix  matrix =new Matrix();
	    		matrix.postRotate(90);
	    		bmp=Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
	    		
	    	}
	    	
	    	
	    	iv.setImageBitmap(bmp);
	    }  
	    
	    
	    if (requestCode == IMAGE_FILE && resultCode == RESULT_OK) {  
	  	  
	    	
	    	  ImageView iv = (ImageView) findViewById(R.id.imgCamera); 
	    	  
	    	  Uri selectedImageUri = data.getData();
	  		String[] projection = { MediaColumns.DATA };
	  		Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
	  				null);
	  		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
	  		cursor.moveToFirst();

	  		String selectedImagePath = cursor.getString(column_index);

	  		Bitmap bm;
	  		BitmapFactory.Options options = new BitmapFactory.Options();
	  		options.inJustDecodeBounds = true;
	  		BitmapFactory.decodeFile(selectedImagePath, options);
	  		final int REQUIRED_SIZE = 200;
	  		int scale = 1;
	  		while (options.outWidth / scale / 2 >= REQUIRED_SIZE
	  				&& options.outHeight / scale / 2 >= REQUIRED_SIZE)
	  			scale *= 2;
	  		options.inSampleSize = scale;
	  		options.inJustDecodeBounds = false;
	  		bm = BitmapFactory.decodeFile(selectedImagePath, options);

			int rotation =neededRotation(selectedImagePath);
			iv.setRotation(rotation);
			iv.setImageBitmap(bm);
	    	
	      
	  
	       
	        
	       /* Uri result = (data == null) ? mPictureUri : data.getData();  
	        File imageFile = new File(result.toString());
	        int rotation = neededRotation(result);
	        iv.setRotation(rotation);
	        iv.setImageURI(result);  
	        */
	     
	        imgroot.setText(selectedImagePath);
	  
	        mPictureUri = null;
	    }  
	    return ;
	} 
	public static int neededRotation(String ff)
    {
    try
        {
        ExifInterface exif = new ExifInterface(ff);
        int orientation = exif.getAttributeInt(
           ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
            { return 270; }
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
            { return 180; }
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
            { return 90; }
        return 0;

        } catch (FileNotFoundException e)
        {
        e.printStackTrace();
        } catch (IOException e)
        {
        e.printStackTrace();
        }
    return 0;
    }
	public static int neededRotation(Uri ff )
    {
    try
        {
        ExifInterface exif = new ExifInterface(ff.getPath());
        int orientation = exif.getAttributeInt(
           ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        if (orientation == ExifInterface.ORIENTATION_ROTATE_270)
            { return 270; }
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180)
            { return 180; }
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90)
            { return 90; }
        return 0;

        } catch (FileNotFoundException e)
        {
        e.printStackTrace();
        } catch (IOException e)
        {
        e.printStackTrace();
        }
    return 0;
    }
	
	
}
