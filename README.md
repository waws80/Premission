# Premission

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
