[![](https://jitpack.io/v/duranun/multicolortextview.svg)](https://jitpack.io/#duranun/multicolortextview)
[![Build Status](https://travis-ci.org/duraun/multicolortextview.svg?branch=master)](https://travis-ci.org/duranun/mulitcolortextview)
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
            android:text="@string/coloredText"
            app:colorIntArray="@array/colorList"
            app:coloredTextIsBold="true"
            />
```

#### You must write the word you want to color between ```<color> </color>``` tags.

```XML
<string name="coloredText"><![CDATA[<color>Hello</color> <color>Worold</color>]]></string>
```

#### To color the words, you must define the colors in the array according to the label order.

```XML
<integer-array name="colorList" >
        <item>@color/red</item>
        <item>@color/color_green</item>
    </integer-array>
```
and set the widget in XML  app:colorIntArray="@array/colorList" or you can set programmatically

```KOTLIN
        coloredTextView.setColorsListArray(R.array.colorList)
```
<img src="https://raw.githubusercontent.com/duranun/multicolortextview/master/sample1.png" width="270" height="480" /> &nbsp;&nbsp;


## Attributes
| Attribute | Use |
| ----------| --- |
| app:colorIntArray | sets the colors of each <color> tag |
| app:coloredTextIsBold | sets the bold of colored text |
| app:startTag | sets the custom open tag.  For example &lt;color&gt;. To display a less than sign (<) you must write: ```&lt;``` and to display a greater than sign (>) you must write : ```&gt;``` |
| app:endTag | sets the custom close tag. For example &lt;/color&gt;.To display a less than sign (<) you must write: ```&lt;``` and to display a greater than sign (>) you must write : ```&gt;```  |
