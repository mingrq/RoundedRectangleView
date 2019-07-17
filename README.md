# 最新版本
 2.0.0

#

### 使用
#
```
在 build.gradle 中添加
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	        implementation 'com.github.mingrq:RoundedRectangleView:Tag'
	}
```
## 方法

#
### float getRadius()
####  获取圆角角度


#
### void setRadius(float radius)
#### 设置圆角大小 ``单位 px``
如要使用dp单位值 可使用 float dp2px(float dpValues) 方法转换

#
###  float dp2px(float dpValues)
#### dp转px
