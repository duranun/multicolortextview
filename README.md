[![](https://jitpack.io/v/duranun/multicolortextview.svg)](https://jitpack.io/#duranun/multicolortextview)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
# Installation


Step 1. Add the JitPack repository to your build file

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```

Step 2. Add the dependency

```
dependencies {
	        implementation 'com.github.duranun:multicolortextview:0.1.2'
	}
```
## Usage

#### With Widget
```XML
<com.duranun.multicolortextview.MultiColorTextView
            android:id="@+id/coloredTextView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@string/resend_desc_text"
            app:colorIntArray="@array/colorList"
            app:coloredTextIsBold="true"
            />
```


## Attributes
| Attribute | Use |
| ----------| --- |
| app:colorIntArray | sets the colors of each <color> tag |
| app:coloredTextIsBold | sets the bold of colored text |
| app:startTag | sets the custom oepn tag.  For example &lt;color&gt;. To display a less than sign (<) you must write: "&lt;" and to display a greater than sign (>) you must write : "&gt;" |
| app:endTag | sets the custom end tag. For example &lt;/color&gt;  |
