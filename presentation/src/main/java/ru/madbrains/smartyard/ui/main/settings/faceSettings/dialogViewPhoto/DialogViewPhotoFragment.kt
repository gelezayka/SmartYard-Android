package ru.madbrains.smartyard.ui.main.settings.faceSettings.dialogViewPhoto

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
import com.bumptech.glide.Glide
import ru.madbrains.smartyard.databinding.DialogViewPhotoBinding

class DialogViewPhotoFragment(
    private val photoUrl: String
) : DialogFragment() {
    private var _binding: DialogViewPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogViewPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.ivViewFacePhoto)
            .load(photoUrl)
            .into(binding.ivViewFacePhoto)
        binding.ivViewFacePhoto.clipToOutline = true

        binding.ivViewFacePhotoClose.setOnClickListener {
            this.dismiss()
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
}
