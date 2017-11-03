package top.waws.premissionapp

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import top.waws.premission.Permission

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener{
            Permission.init(true).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .build(this)
                    .execute { code ->
                        if (code == Permission.OK){
                            toast("获取权限成功！")
                        }else{
                            toast("获取权限失败！")
                        }
                    }
        }

    }

    fun toast(msg: String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

}
