# Premission

[![](https://jitpack.io/v/waws80/Premission.svg)](https://jitpack.io/#waws80/Premission)

### gradle
```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```java
dependencies {
	        compile 'com.github.waws80:Premission:V0.1'
	}
```

### 使用：

```java
Permission.init(true).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .build(this)
                    .execute { code ->
                        if (code == Permission.OK){
                            toast("获取权限成功！")
                        }else{
                            toast("获取权限失败！")
                        }
                    }
                    
```
