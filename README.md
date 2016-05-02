# ImageLoader

my fisrt ImageLoader Library.

cache and save images.

of course i will update.


##How to use

```java
ImageLoader imageLoader = new ImageLoader();
// use memory cache.
imageLoader.setImageCache(new MemoryCache());
//use disk cache.
imageLoader.setImageCache(new DiskCache());
//use double cache.
imageLoader.setImageCache(new DoubleCache());
//design by yourself.

```

