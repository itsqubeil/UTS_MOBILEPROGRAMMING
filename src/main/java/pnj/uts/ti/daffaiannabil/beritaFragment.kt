package pnj.uts.ti.daffaiannabil

import NewsAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [beritaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class beritaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sample news data
        val newsList = listOf(
            NewsItem("Bye Bye, AI: How to turn off Google's annoying AI overviews and just get search results", R.drawable.news1, "Tired of Google's AI overviews and their bad, plagiarized advice? Here's how to avoid them in search."),
            NewsItem("Huawei brings sanctions-busting Kirin 9000C CPU to desktop PCs to replace banned Intel Alder Lake chips", R.drawable.news2, "Huawei has announced a brand new pre-built OEM machine featuring China silicon. The latest change results from U.S. regulations preventing Huawei from using Intel hardware."),
            NewsItem("Linux distros ban 'tainted' AI-generated code", R.drawable.news3, "Two major Linux distributions ban the contribution of AI-generated code to their open-source projects."),
            NewsItem("Idle Windows XP and 2000 machines get infected with viruses within minutes of being exposed online", R.drawable.news4, "A YouTuber installs a Windows XP VM without using any basic security measures to see how many viruses the OS will get. Within minutes, the OS has several viruses infecting the VM."),
            NewsItem("This giant Game Boy XL uses a Raspberry Pi 5 and has working buttons", R.drawable.news5, "Arnov Sharma is using a Raspberry Pi 5 to power this extra-large Game Boy, which features a custom 3D-printed shell and working buttons."),
            NewsItem("Jailbroken coin-operated washing machines unlock unlimited free cycles and millions in funds", R.drawable.news6, "It can also be exploited to turn washing machines into a fire hazard as it bypasses safety restrictions."),
            NewsItem("Enthusiast gets Windows XP running on an i486", R.drawable.news7, "Modded Windows Xp SP3 ISO (German) available on Archive.org."),
            NewsItem("M4 iPad Pro teardown shows the M4 processor and Apple Logo heat spreader in the flesh", R.drawable.news8, "Phone Repair Guru opens and disassembles a 13-inch iPad Pro M4, and we are impressed."),
            NewsItem("Baidu's breakthrough can meld GPUs from different brands into one AI training cluster", R.drawable.news9, "Baidu developed a system that will let it use GPUs from different brands and use it as a unified computing cluster for training LLMs."),
            NewsItem("Asus vows to improve clarity surrounding warranty claims and astronomical hardware repair costs", R.drawable.news10, "The company is taking steps to make its warranty communication clearer for customers. But will that be enough to give Asus clients the service they deserve?"),

            // Add more news items as needed
        )

        // Initialize the ListView
        val listViewBerita = view.findViewById<ListView>(R.id.listViewBerita)

        // Create and set the custom adapter
        val adapter = NewsAdapter(requireContext(), newsList)
        listViewBerita.adapter = adapter
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment chatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            beritaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}