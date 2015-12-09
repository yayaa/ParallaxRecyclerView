# ParallaxRecyclerView
This library will provide you to have Parallax effect on every item of your RecyclerView. 
Sample shows with static images but it can be used with async loading images as well. 
Tested with [Universal-Image-Loader.][1]

![](http://yayandroid.com/data/github_library/parallax_recyclerview/parallax_recyclerview.gif)

Compatibility
-------------
This library works on 2.2+ probably earlier as well, but i didn't even bother to test because i believe at some point we have to stop supporting every version ;)

Usage
-----
Get your ParallaxRecyclerView instance, and set its adapter as shown in sample. You need to provide a ParallaxImageView, which will handle parallax effect, on your recyclerView's item. 

In your adapter create a ViewHolder which extends ParallaxViewHolder. This will make you implement below method that you need to return your ParallaxImageView's id on item.

```java 
@Override
public int getParallaxImageId() {
    return R.id.backgroundImage;
}
``` 

And this method needs to be called onBindViewHolder method to notify ParallaxImageView that will be displayed once again, so it will re-center itselfs.

```java 
viewHolder.getBackgroundImage().reuse();
```

If you wish to change Parallax effects ratio, you can simple call `setParallaxRatio` on code, or you can set it by xml with `parallax_ratio` attribute.

## Download
Add library dependency to your `build.gradle` file:

[![Maven Central](https://img.shields.io/maven-central/v/com.yayandroid/ParallaxRecyclerView.svg)](http://search.maven.org/#search%7Cga%7C1%7CParallaxRecyclerView)
```groovy
dependencies {    
     compile 'com.yayandroid:ParallaxRecyclerView:1.0'
}
```

References
----------

This library has been built by our designer's insistence. She's seen [JBParallaxCell library on IOS][2] and want it to have in Android as well. Researches lead me to [this repository][3] but it wasn't quite affective as it is on IOS, so here ParallaxRecyclerView.

## License

```
The MIT License (MIT)

Copyright (c) 2015 yayandroid

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```

[1]: https://github.com/nostra13/Android-Universal-Image-Loader
[2]: https://github.com/jberlana/JBParallaxCell
[3]: https://github.com/bopbi/Android-Parallax-ListView-Item
