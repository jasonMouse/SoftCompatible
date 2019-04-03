# SoftCompatible
主要解决软键盘将布局顶出页面的问题

输入框在比较靠近屏幕底部的时候，软键盘会将页面顶出屏幕，解决问题的核心思想就是在屏幕底部添加一个View，当键盘展开的时候将整个布局撑起，键盘收起的时候将View的高度设置为0，具体效果如下：
![图片](https://github.com/jasonMouse/SoftCompatible/blob/master/app/src/main/assets/SoftCompatible.gif)
