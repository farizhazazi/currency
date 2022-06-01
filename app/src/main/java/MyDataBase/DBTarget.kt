package MyDataBase

import android.provider.BaseColumns

object DBTarget {
    class targetTable : BaseColumns{
        companion object{
            val tabletarget = "target"
            val kolomid : String = "id"
            val kolomnama : String = "nama"
            val kolomharga : String = "harga"
            val kolomjangka : String = "waktu"

        }
    }
}