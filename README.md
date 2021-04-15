# FilePicker
FilePicker is an Android Library that lets you choose any kind of file easyly. It handles the storage permission itself.

# Minimum Sdk Version
    Api Level 19 or Above

# Installation

Add the following code snippet in your project level gradle file

    repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    
Add the dependency in your app gradle

    dependencies {
           ...
          implementation 'com.github.ajayasija23:FilePicker:1.0.0'
      }
      
# Usage

### Start FilePickerActivity and pass the MimeType of the target file

      Intent intent= new Intent(MainActivity.this,FilePickerActivity.class);
      intent.putExtra("type","image/*");
      startActivityForResult(intent,101);
      
### Override the onActivityResult
  
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file= FileUtils.getFile(this,data.getData());
        Log.d("fileUri",file.toString());
    }

