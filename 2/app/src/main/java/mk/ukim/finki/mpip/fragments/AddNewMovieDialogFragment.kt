package mk.ukim.finki.mpip.fragments

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import mk.ukim.finki.mpip.R

class AddNewMovieDialogFragment : DialogFragment() {
    private lateinit var listener: AddMovieDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_add_movie, null);
            val editDialogMovieTitle: EditText = view.findViewById(R.id.movieTitleLabel)
            val editDialogMovieDescription: EditText = view.findViewById(R.id.movieDescriptionLabel)
            val editDialogMovieDirector: EditText = view.findViewById(R.id.movieDirectorLabel)
            val editDialogMovieActors: EditText = view.findViewById(R.id.movieActorsLabel)

            builder.setView(view)
                .setPositiveButton(R.string.ok) { _, _ ->
                    listener.onDialogPositiveClick(
                        editDialogMovieTitle.text.toString(),
                        editDialogMovieDescription.text.toString(),
                        editDialogMovieDirector.text.toString(),
                        editDialogMovieActors.text.toString().split(",").toMutableList()
                            .toCollection(ArrayList())
                    )
                }
                .setNegativeButton(
                    R.string.cancel,
                    DialogInterface.OnClickListener { _, _ ->
                        listener.onDialogNegativeClick(this)
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as AddMovieDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement AddMovieDialogListener.")
        }
    }

    interface AddMovieDialogListener {
        fun onDialogPositiveClick(
            title: String,
            description: String,
            director: String,
            actors: ArrayList<String>
        )

        fun onDialogNegativeClick(dialog: DialogFragment)
    }
}