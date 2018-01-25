# FillWidthTextView

经常会有一些填写表单页面之类的需求，比如像这样：

![难看的TextView](https://github.com/LeonXtp/FillWidthTextView/blob/master/art/ugly-textview.png)

很难将它们的宽度和间隔调整到一致。

本控件就是为了解决这个问题，效果如下：

![漂亮的TextView](https://github.com/LeonXtp/FillWidthTextView/blob/master/art/fill-width-textview.png
)

使用很简单，只需要给它指定一个宽度就好了：

``` xml
    <com.leonxtp.library.FillWidthTextView
        android:id="@+id/tvName"
        android:layout_width="100dp"
        android:layout_height="wrap_content"
        android:background="#afa6a6"
        android:text="姓名：" />
```

Enjoy~


### License
-------

MIT
