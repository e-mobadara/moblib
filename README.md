# 2d-game-lib
library for 2D android games

## Requirements 
- minSdkVersion 17
- targetSdkVersion 28

## Usage

### Step 1: Add the JitPack repository to your build file

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

### Step 2: Add the dependency
```
dependencies {
		implementation 'com.github.e-mobadara:2d-game-lib:v1.1'
	}
```

### Step 3: Add the Needed Permissions in your app manifest file
To avoid the priorities that the Android System uses when merging the permissions in the library and the permissions of your app  see this [document](https://developer.android.com/studio/build/manifest-merge.html "Document") we decided to let you add them in the manifest of your app to avoid any unwanted behaviors. 
```
<manifest>
	<uses-permission android:name="android.permission.WAKE_LOCK" />
    	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
   	<uses-permission android:name="android.permission.VIBRATE" />
</manifest>
```

### Documentation 
The documentation is being written now. 

### Reporting issues:
This is a beta release, So if you did encounter a bug please open an issue in the issues Session and provide the way to reproduce the bug or a link to your project. 
