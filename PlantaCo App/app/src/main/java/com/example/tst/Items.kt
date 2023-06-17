package com.example.tst

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.list_item.view.*

class Items : AppCompatActivity() {
    var arr = ArrayList<Zar3a>()
    var arrlist = ArrayList<Zar3a>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        //hardcoding the data
        val info1 = "Temperature: 25 – 35 C\n" +
                "Humidity: 70%\n" +
                "How to Grow Mint\n" +
                "There is not really much that mint needs besides moisture and rich soil. It is pretty hard to kill a mint plant. The only maintenance required will be to make sure that you keep the mint in check and watch for overgrowth. It will take over your garden if you let it. Harvest or shear the plants to keep them lush with leaves.\n" +
                "Notes\n" +
                "\t•\tWater mint plants as needed throughout the season. Mint plants enjoy soil that is thoroughly moist. So depending on your climate and the plants' location, watering may need to be conducted daily.\n" +
                "\t•\tHarvesting of the mint leaves can and should be done often during the season. Harvesting equally serves as pruning and will promote lush and dense growth. To harvest mint, use your fingers to pinch off new growth from the tops of the plants. Pinch growth above the node that is about 1/3 of the way down on the mint branch.\n"

        val info2 = "Temperature : 20 – 30C \n" +
                "Humidity: 50 – 70%\n" +
                "Notes:\n" +
                "Water: Rosemary is much more likely to be killed by overwatering than under-watering. Use terracotta pots to increase evaporation in the root zone and let the soil dry between waterings.\n" +
                "Rosemary is generally a very easy plant to grow, yielding large quantities of piney scented leaves that are both medicinal and useful in cooking. The most common problem to beset rosemary is powdery mildew, which typically affects plants that are too wet or have insufficient light and/or circulation. Powdery mildew looks exactly like it sounds: white powder on the leaves. Because rosemary is an edible plant, you have to be careful what you use to treat powdery mildew with. When you first see signs of it, remove all affected parts of the plant (carefully, so as not to spread the mildew) and seal up the infected branches in airtight bags, then dispose of them. A variety of remedies can be used to treat the remaining plant, including neem oil and baking soda. Always test your remedy on a few leaves first before treating the whole plant.\n"
        val info3 = "•\tTemperature: 15 to 32º C\n" +
                "\t•\tHumidity: 70% to 80% \n" +
                "Important notes: \n" +
                "\t•\tPlacing the pot in an aquarium or a shallow tray of pebbles in water will help achieve a high level of humidity.\n" +
                "\t•\tAvoid direct sun, which may burn the plant, but keep it in a bright location to encourage blooming. Place the plant between 1.5 and 2.4 meter from a window for some gentle sunlight.\n" +
                "\t•\tDo not keep plants directly in front of heaters and heating vents, which may burn them.\n" +
                "\t•\tIf the leaves turn yellow (but not brown and withered), this may be a sign of overwatering. Let the soil dry out before watering again if this occurs.\n" +
                "\t•\tIf your plant is vine-like and failing to support itself, use a stake or other wooden object for the plant to climb up. You do not need to move epiphytic Anthurium out of the soil; it will not cause them harm.\n"


        // creating a variable for each zar3a
        val mint = Zar3a("Mint", 27, 60, info1, R.drawable.mint)
        val rosemary = Zar3a("Rosemary", 25, 60, info2, R.drawable.rosemary)
        val anthurium = Zar3a("Anthurium", 22, 50, info3, R.drawable.rosemary)
        // adding el zar3 to the main array
        arr.add(mint)
        arr.add(rosemary)
        arr.add(anthurium)

        // recieving the data from the previous window
        val temp = intent.getIntExtra("tt", 0)
        val hum = intent.getIntExtra("hh", 0)
        // filtering data
        for (i in 0 until arr.size) {
            if ((temp < (arr[i].t!! + 5)) and (temp > (arr[i].t!! - 5)) and (hum < (arr[i].h!! + 30)) and (hum > (arr[i].h!! - 30))) {
                arrlist.add(arr[i])
            }

        }

        // adapting the plant array with the list
        val adap = PlantAdapter(this, arrlist)
        listv.adapter = adap
        // seeting the on click listener to pass data to the next window ( the window that shows data)
        listv.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Plant::class.java)
            intent.putExtra("name", arrlist[position].name)
            intent.putExtra("info", arrlist[position].info)
            intent.putExtra("image", arrlist[position].photo)
            startActivity(intent)

        }
    }

    inner class  PlantAdapter: BaseAdapter {
        var context: Activity? = null
        var plantsLocalArray = ArrayList<Zar3a>()

        constructor(context:Activity, arr:ArrayList<Zar3a>){
            this.context = context
            plantsLocalArray = ArrayList<Zar3a>(arr)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val plantoo = plantsLocalArray[position]
            var inf = context!!.layoutInflater
            val plantview = inf.inflate(R.layout.list_item,null,true)
            plantview.tvname.text = plantoo.name
            plantview.img.setImageResource(plantoo.photo!!)

             return  plantview


        }

        override fun getItem(position: Int): Any {
            return arrlist[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int { // knowing how much plants should be displayed
            return arrlist.size
        }



    }
}
