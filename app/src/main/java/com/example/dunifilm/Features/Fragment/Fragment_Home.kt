package com.example.dunifilm.Features.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dunifilm.Features.Fragment.Adapter.Recycler_Adpter
import com.example.dunifilm.Features.Info_Movie_Activity
import com.example.dunifilm.Features.Movie_Activty
import com.example.dunifilm.Features.Search_Activity
import com.example.dunifilm.Modle.API_Manager
import com.example.dunifilm.Modle.Genres
import com.example.dunifilm.Modle.Movie
import com.example.dunifilm.Modle.keyGenres
import com.example.dunifilm.Modle.keyGenresName
import com.example.dunifilm.Modle.keySearch
import com.example.dunifilm.Modle.keySendMovieID
import com.example.dunifilm.R
import com.example.dunifilm.databinding.HomeFragmentBinding
import com.google.android.material.chip.Chip

class Fragment_Home : Fragment(), Recycler_Adpter.sendData {
    lateinit var loadingDialog: AlertDialog
    val api_manager = API_Manager()
    lateinit var binding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        createDialg()
        initUi()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.edtSearch.setOnFocusChangeListener { view, b ->
            if (b) {
                val intent = Intent(requireContext(), Search_Activity::class.java)
                startActivity(intent)
            }
        }


        binding.edtSearch.setOnClickListener {
            val intent = Intent(requireContext(), Search_Activity::class.java)
            startActivity(intent)

        }


        binding.chipAll.setOnClickListener {
            setonClickviewAll("All categorys", 22)
        }

        binding.vAllCrime.setOnClickListener {
            setonClickviewAll("Crime", 1)
        }

        binding.vAllAction.setOnClickListener {
            setonClickviewAll("Action", 3)
        }

        binding.vAllSport.setOnClickListener {
            setonClickviewAll("Sport", 21)
        }

        binding.vAllHorror.setOnClickListener {
            setonClickviewAll("Horror", 17)
        }

        binding.vAllBiography.setOnClickListener {
            setonClickviewAll("Biography", 4)
        }

    }

    private fun setonClickviewAll(name: String, position: Int) {
        val intent = Intent(requireContext(), Movie_Activty::class.java)
        intent.putExtra(keyGenres, position)
        intent.putExtra(keyGenresName, name)
        startActivity(intent)

    }


    private fun initUi() {

        api_manager.getGenres(object : API_Manager.apiCallBack<List<Genres>> {
            override fun onSuccess(data: List<Genres>) {
                createChips(data)
            }

            override fun onError(errorMessage: String) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }

        })


        dateCrime()

        dateSport()

        dateAction()

        dateBiography()

        dateHistory()

        loadingDialog.dismiss()


    }


    private fun dateSport() {
        api_manager.getMovies(21, 1, object : API_Manager.apiCallBack<Movie> {
            override fun onSuccess(data: Movie) {
                // 'data.data' حاوی لیست فیلم‌ها است
                binding.recyclerSport.adapter = Recycler_Adpter(data.data, this@Fragment_Home)
                binding.recyclerSport.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

            }

            override fun onError(errorMessage: String) {
                Toast.makeText(
                    requireContext(),
                    "خطا در دریافت فیلم‌ها: $errorMessage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun dateCrime() {
        // شناسه ژانر (genreId) را برای دریافت فیلم‌های مورد نظر مشخص کنید
        // در اینجا به عنوان مثال از ID=1 استفاده شده است
        api_manager.getMovies(1, 1, object : API_Manager.apiCallBack<Movie> {
            override fun onSuccess(data: Movie) {
                // 'data.data' حاوی لیست فیلم‌ها است
                binding.recyclerCrime.adapter = Recycler_Adpter(data.data, this@Fragment_Home)
                binding.recyclerCrime.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

            }

            override fun onError(errorMessage: String) {
                Toast.makeText(
                    requireContext(),
                    "خطا در دریافت فیلم‌ها: $errorMessage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun createChips(data: List<Genres>) {
        data.forEach {
            val chip = LayoutInflater.from(requireContext())
                .inflate(R.layout.chip_category, binding.chipGroupCategories, false) as Chip
            chip.id = it.id
            chip.text = it.name
            binding.chipGroupCategories.addView(chip)

            chip.setOnClickListener {
                when (chip.id) {
                    1 -> {
                        setonClickviewAll("Crime", 1)
                    }

                    2 -> {
                        setonClickviewAll("Drama", 2)
                    }

                    3 -> {
                        setonClickviewAll("Action", 3)
                    }

                    4 -> {
                        setonClickviewAll("Biography", 4)
                    }

                    5 -> {
                        setonClickviewAll("History", 5)
                    }

                    6 -> {
                        setonClickviewAll("Adventure", 6)
                    }

                    7 -> {
                        setonClickviewAll("Fantasy", 7)
                    }

                    8 -> {
                        setonClickviewAll("Western", 8)
                    }

                    9 -> {
                        setonClickviewAll("Comedy", 9)
                    }

                    10 -> {
                        setonClickviewAll("Sci-Fi", 10)
                    }

                    11 -> {
                        setonClickviewAll("Mystery", 11)
                    }

                    12 -> {
                        setonClickviewAll("Thriller", 12)
                    }

                    13 -> {
                        setonClickviewAll("Family", 13)
                    }

                    14 -> {
                        setonClickviewAll("War", 14)
                    }

                    15 -> {
                        setonClickviewAll("Animation", 15)
                    }

                    16 -> {
                        setonClickviewAll("Romance", 16)
                    }

                    17 -> {
                        setonClickviewAll("Horror", 17)
                    }

                    18 -> {
                        setonClickviewAll("Music", 18)
                    }

                    19 -> {
                        setonClickviewAll("Film-Noir", 19)
                    }

                    20 -> {
                        setonClickviewAll("Musical", 20)
                    }

                    21 -> {
                        setonClickviewAll("Sport", 21)
                    }
                }
            }
        }
    }

    private fun dateAction() {
        api_manager.getMovies(3, 1, object : API_Manager.apiCallBack<Movie> {
            override fun onSuccess(data: Movie) {
                // 'data.data' حاوی لیست فیلم‌ها است
                binding.recyclerAction.adapter = Recycler_Adpter(data.data, this@Fragment_Home)
                binding.recyclerAction.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

            }

            override fun onError(errorMessage: String) {
                Toast.makeText(
                    requireContext(),
                    "خطا در دریافت فیلم‌ها: $errorMessage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun dateBiography() {

        api_manager.getMovies(4, 1, object : API_Manager.apiCallBack<Movie> {
            override fun onSuccess(data: Movie) {
                // 'data.data' حاوی لیست فیلم‌ها است
                binding.recyclerBiography.adapter = Recycler_Adpter(data.data, this@Fragment_Home)
                binding.recyclerBiography.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

            }

            override fun onError(errorMessage: String) {
                Toast.makeText(
                    requireContext(),
                    "خطا در دریافت فیلم‌ها: $errorMessage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun dateHistory() {
        api_manager.getMovies(17, 1, object : API_Manager.apiCallBack<Movie> {
            override fun onSuccess(data: Movie) {
                // 'data.data' حاوی لیست فیلم‌ها است
                binding.recyclerHorror.adapter = Recycler_Adpter(data.data, this@Fragment_Home)
                binding.recyclerHorror.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

            }

            override fun onError(errorMessage: String) {
                Toast.makeText(
                    requireContext(),
                    "خطا در دریافت فیلم‌ها: $errorMessage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun clickOnMovie(film_id: Int) {
        val intent = Intent(requireContext(), Info_Movie_Activity::class.java)
        intent.putExtra(keySendMovieID, film_id)
        startActivity(intent)
    }

    private fun createDialg() {
        val dialogView = layoutInflater.inflate(R.layout.loadinf_dialog, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        loadingDialog = builder.create()
        loadingDialog.show()

        loadingDialog.window?.setBackgroundDrawableResource(R.color.shafaf)
        val scale = resources.displayMetrics.density
        val sizeXY = (250 * scale).toInt()

        loadingDialog.window?.setLayout(sizeXY, sizeXY)

        loadingDialog.setCancelable(true)
    }

}

