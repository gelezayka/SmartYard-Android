package ru.madbrains.smartyard.ui.main.settings.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import ru.madbrains.smartyard.databinding.DialogServiceBinding
import ru.madbrains.smartyard.ui.main.settings.SettingsViewModel

/**
 * @author Nail Shakurov
 * Created on 2020-02-20.
 */
class DialogServiceFragment : DialogFragment() {
    private var _binding: DialogServiceBinding? = null
    private val binding get() = _binding!!

    interface OnDialogServiceListener {
        fun onDismiss()
        fun onDone()
    }

    private lateinit var mData: SettingsViewModel.TypeDialog
    private var service: String = ""
    private var onDialogServiceListener: OnDialogServiceListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    fun setListener(listener: OnDialogServiceListener) {
        onDialogServiceListener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = "Услуга \"$service\" ${getString(mData.title)}"
        binding.tvCaption.setText(mData.caption)
        binding.btnDone.setText(mData.button)
        binding.ivDismiss.setOnClickListener {
            onDialogServiceListener?.onDismiss()
        }
        binding.btnDone.setOnClickListener {
            onDialogServiceListener?.onDone()
        }
    }

    override fun onStart() {
        super.onStart()
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 30)
        dialog?.window?.setBackgroundDrawable(inset)
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    fun setData(data: SettingsViewModel.TypeDialog, service: String) {
        this.mData = data
        this.service = service
    }
}
