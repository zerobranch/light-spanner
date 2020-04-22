
# Light Spanner
[![](https://jitpack.io/v/zerobranch/light-spanner.svg)](https://jitpack.io/#zerobranch/light-spanner)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/zerobranch/android-remote-debugger/blob/master/LICENSE)

**Light Spanner** - это проект для платформы Android, который предоставляет простой способ работы с `Android Spannable`.

### Выберите язык
[English](https://github.com/zerobranch/light-spanner/blob/master/README.md)

[Русский](https://github.com/zerobranch/light-spanner/blob/master/RUSSIAN_README.md)

## Особенности
* простота
* применение Span или комбинации Span к части текста или ко всему тексту сразу
* использование совственных span
* поддержка установки размеров для span сразу в dp, sp или px
* два способа применения span:
	* установление span явно указав часть текста 
	* указав позицию для каждого span в тексте
* возможность применять к тексту Small Caps стиль

## Скачать
Скачайте [APK](https://github.com/zerobranch/light-spanner/releases/download/1.0.0/light_spanner_example.apk) примера и попробуйте!

## Скриншот
<img src="/screenshots/example.png" alt="example.png" title="example.png" width="300" height="560" /> 

## Интеграция
Добавьте в корневой build.gradle следующий репозиторий:
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Добавьте в build.gradle вашего модуля следующую зависимость:
```groovy
dependencies {
    implementation 'com.github.zerobranch:light-spanner:1.0.0'
}
```

## Как использовать ?
##### Пример использования

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

##### Пример кликабельного Span

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

#### Вы можете применить Span используя один из следующих способов:

```kotlin
Spanner()
    .appendln("italic text", Spans.italic())
    .installTo(your_text_view)
```

#### или

```kotlin
val spannable: SpannableStringBuilder = Spanner()
    .append("bold text", Spans.bold())
    .get()

your_text_view.text = spannable
```

## Описание

| Метод | Описание |
|---|---|
| append(text, span1, span2, ... ) | Добавить текст и применить к нему указанные Span |
| appendln(text, span1, span2, ... ) | Добавить текст, применить к нему указанные Span и добавить перевод на следующую строку |
| newLine() | добавить перевод на следующую строку |
| all(span1, span2, ... ) | применить ко всему тексту указанные Span |
| set(text, spanParams1, spanParams2,... ) | применить Span явно указав позицию |
| toSmallCaps(textView) | применить ко всему тексту стиль Small Caps |
| installTo(textView) | применить все указанные Span к textView |
| get() | получить объект SpannableStringBuilder со всеми примененными Span |

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

## Дополнительно

При необходимости можно создать свои Span и таким же образом передать их в метод `append`

В некоторых методах на вход принимается параметр `DimensionType`, который позволяет указывать размеры в трех величинах: PX, DP, SP. По умолчанию `DimensionType` равен `DimensionType.PX`

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
