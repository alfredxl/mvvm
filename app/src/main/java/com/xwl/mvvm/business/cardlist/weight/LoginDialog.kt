package com.xwl.mvvm.business.cardlist.weight

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.xwl.mvvm.R

/**
 * @ProjectName: mvvm
 * @Package: com.xwl.mvvm.business.cardlist.weight
 * @ClassName: LoginDialog
 * @Description: java类作用描述
 * @Author: 谢文良
 * @CreateDate: 2021/2/1 9:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/2/1 9:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class LoginDialog(context: Context, val listener: LoginDialogListener) : Dialog(context), View.OnClickListener {
    private lateinit var etName: AppCompatEditText
    private lateinit var etPassword: AppCompatEditText
    private lateinit var etCode: AppCompatEditText
    private lateinit var ivCode: AppCompatImageView
    private lateinit var btLogin: AppCompatButton

    init {
        init()
    }

    private fun init() {
        setContentView(R.layout.login_dialog)
        etName = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)
        etCode = findViewById(R.id.etCode)
        ivCode = findViewById(R.id.ivCode)
        btLogin = findViewById(R.id.btLogin)
        ivCode.setOnClickListener(this)
        btLogin.setOnClickListener(this)
        etName.setText("xiewenliang")
        etPassword.setText("123456")
        Glide.with(ivCode).load(listener.getCodeUrl()).into(ivCode)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivCode -> Glide.with(v).load(listener.getCodeUrl()).into(v as ImageView)
            R.id.btLogin -> {
                dismiss()
                listener.login(etName.text.toString(), etPassword.text.toString(), etCode.text.toString())
            }
        }
    }

    interface LoginDialogListener {
        fun getCodeUrl(): String
        fun login(tvUserName: String, tvPassword: String, tvCode: String)
    }
}