# Preview

![](https://github.com/ajayasija23/FilePicker/blob/master/preview/preview3.jpeg) ![](https://github.com/ajayasija23/FilePicker/blob/master/preview/preview2.jpeg)
![](https://github.com/ajayasija23/FilePicker/blob/master/preview/preview1.jpeg)


# FilePicker
FilePicker is an Android Library that lets you choose any kind of file easyly. It handles the storage permission itself.

# Minimum Sdk Version
    Api Level 19 or Above
# Max Sdk Version
    Api Level 30

# Installation

Add the following code snippet in your project level gradle file

    repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    
Add the dependency in your app gradle

    dependencies {
           ...
          implementation 'com.github.ajayasija23:FilePicker:1.0.0' //choose latest version
      }
      
# Usage

### Start FilePickerActivity and pass the MimeType of the target file

    Intent intent=new Intent(this, FilePickerActivity.class);
    intent.putExtra("multiple",true);
    intent.putExtra("type","*/*");
    startActivityForResult(intent,REQUEST_CODE);
      
### Override the onActivityResult
  
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE&&resultCode==RESULT_OK&&data!=null){
            if (data.getData()!=null){
                File file=FileUtils.getFile(this,data.getData());
                selectedFiles.add(file.getName());
                adapter.notifyDataSetChanged();
            }
            if (data.getClipData()!=null){
                for(int i = 0; i < data.getClipData().getItemCount(); i++) {
                    Uri uri = data.getClipData().getItemAt(i).getUri();
                    File file=FileUtils.getFile(this,uri);
                    selectedFiles.add(file.getName());
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

