# Light Spanner
[![](https://jitpack.io/v/zerobranch/light-spanner.svg)](https://jitpack.io/#zerobranch/light-spanner)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/zerobranch/android-remote-debugger/blob/master/LICENSE)

**Light Spanner** is a project for the Android platform that provides an simple way to work with `Android Spannable`.
 
### Choose language
[English](https://github.com/zerobranch/light-spanner/blob/master/README.md)

[Русский](https://github.com/zerobranch/light-spanner/blob/master/RUSSIAN_README.md)

## Features
* simplicity
* applying a span or span combination to a piece of text or to all text at once
* using custom spans
* sizing support for span in dp, sp or px
* two ways to applying span:
	* setting span by explicitly specifying part of the text
	* specifying a position for each span in the text
* applying Small Caps style to text

## Download
Download the sample [APK](https://github.com/zerobranch/light-spanner/releases/download/1.0.0/light_spanner_example.apk) and try it out!

## Screenshot
<img src="/screenshots/example.png" alt="example.png" title="example.png" width="300" height="560" /> 

## Integration
Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add the following dependency to your module's build.gradle:
```groovy
dependencies {
    implementation 'com.github.zerobranch:light-spanner:1.0.0'
}
```

## How to use ?
##### Example of use

```kotlin
Spanner()
    .appendln("Simple text")
    .newLine()
    .appendln("Google", Spans.url("https://google.com"))
    .appendln("alignNormal", Spans.alignNormal(), Spans.bold(), Spans.relativeSize(1.5f))
    .appendln("alignOpposite", Spans.alignOpposite(), Spans.foregroundColor(context, R.color.your_color))
    .appendln("alignCenter", Spans.alignCenter())
    .appendln("leadingMargin", Spans.leadingMargin(40F, 20F, DimensionType.DP))
    .appendln("linesLeadingMargin", Spans.linesLeadingMargin(30F, 3, DimensionType.DP))
    .appendln("lineHeight", Spans.lineHeight(27F, DimensionType.SP))
    .appendln("tabStop", Spans.tabStop(150F, DimensionType.DP))
    .appendln("bullet", Spans.bullet(context, 24f, R.color.your_color, DimensionType.DP))
    .appendln("subscript", Spans.subscript(), Spans.textSize(12F, DimensionType.SP))
    .appendln("superscript", Spans.superscript(), Spans.textSize(12F, DimensionType.SP))
    .appendln("textAppearance", Spans.textAppearance(context, android.R.style.TextAppearance_Medium))
    .appendln("textSize", Spans.textSize(13F, DimensionType.SP))
    .appendln("lineBackground", Spans.lineBackground(Color.GREEN))
    .appendln("relativeSize", Spans.relativeSize(1.5f))
    .appendln("foregroundColor", Spans.foregroundColor(Color.BLUE))
    .append("backgroundColor", Spans.backgroundColor(Color.LTGRAY))
    .append("drawableMargin", Spans.drawableMargin(context, R.drawable.your_drawable, 16F, DimensionType.DP))
    .append("iconMargin", Spans.iconMargin(Bitmap, 6F, DimensionType.DP))
    .append("image", Spans.image(context, R.drawable.your_drawable, DynamicDrawableSpan.ALIGN_BASELINE))
    .append("scaleX", Spans.scaleX(2F))
    .append("underline", Spans.underline())
    .append("normal", Spans.normal())
    .append("bold", Spans.bold())
    .append("italic", Spans.italic())
    .append("boldItalic", Spans.boldItalic())
    .append("blurMaskFilter", Spans.blurMaskFilter(2F, BlurMaskFilter.Blur.NORMAL, DimensionType.DP))
    .append("quote", Spans.quote(Color.BLUE))
    .append("strikethrough", Spans.strikethrough())
    .append("typeface", Spans.typeface("monospace"))
    .append("typefaceStandard", Spans.typeface(Typeface.DEFAULT_BOLD))
    .append("typeface", Spans.font(context, R.font.your_font))
    .set(
        "Example with a custom positions",
        SpanParams(0, 31, Spans.bold(), Spans.boldItalic()),
        SpanParams(37, 41, Spans.boldItalic(), Spans.underline(), Spans.foregroundColor(context, R.color.your_color))
    )
    .all(Spans.bold(), Spans.italic(), Spans.strikethrough())
    .toSmallCaps(your_text_view)
    .installTo(your_text_view)
```

##### Clickable span example

```kotlin
Spanner()
    .append(
        "Clickable text",
        Spans.clickable(
            your_text_view,
            { Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show() },
            { isPressed, textPaint ->
                if (isPressed) {
                    textPaint.color = Color.RED
                    textPaint.isUnderlineText = true
                } else {
                    textPaint.isUnderlineText = false
                }
            }
        )
    )
    .installTo(your_text_view)
```

#### You can apply Span using one of the following methods:

```kotlin
Spanner()
    .appendln("italic text", Spans.italic())
    .installTo(your_text_view)
```

#### or

```kotlin
val spannable: SpannableStringBuilder = Spanner()
    .append("bold text", Spans.bold())
    .get()

your_text_view.text = spannable
```

## Description

| Method | Description |
|---|---|
| append(text, span1, span2, ... ) | Append text and apply specified Span to it |
| appendln(text, span1, span2, ... ) | Append text, apply specified Span to it and append a line separator |
| newLine() | Append a line separator |
| all(span1, span2, ... ) | Apply specified spans to all text |
| set(text, spanParams1, spanParams2,... ) | Apply Spans by explicitly specifying a position |
| toSmallCaps(textView) | Apply Small Caps style to all text |
| installTo(textView) | Apply all specified Spans to textView |
| get() | Get a SpannableStringBuilder object with all Spans applied |

#### Spans

| | | | |
|---|---|---|---|
| clickable | tabStop | textAppearance | scaleX |
| url | bullet | lineBackground | strikethrough |
| alignNormal | subscript | textSize | underline |
| alignOpposite | superscript | relativeSize | normal |
| alignCenter | suggestion | foregroundColor | bold |
| align | blurMaskFilter | backgroundColor | italic |
| leadingMargin | quote | drawableMargin | boldItalic |
| linesLeadingMargin | typeface | iconMargin | font |
| lineHeight | image |  | |

## Additionally

If necessary, you can create your Span and send it to the `append()` method in the same way.

In some methods, the parameter DimensionType is accepted as an input, which allows you to specify sizes in three quantities: PX, DP, SP.

In some methods, `DimensionType` is sent to the input, which allows you to specify sizes in three quantities: PX, DP, SP. By default, `DimensionType` is equal to `DimensionType.PX`

## License

```
Copyright 2020 Arman Sargsyan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
