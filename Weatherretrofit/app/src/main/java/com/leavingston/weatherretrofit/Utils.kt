package com.leavingston.weatherretrofit

object Utils {
    val celsiusYinva = arrayOf(-1,-2,-3,-4,-5,-6,-7,-8,-9,-10,-11,-12,-13,-14,-15,-16,-17,-18,-19,-20)
    val celsiusArray = arrayOf(1,2,3,4,5,6,7,8,9,10)
    val celsiusArray1 = arrayOf(11,12,13,14,15)
    val celsiusArray2 = arrayOf(16,17,18,19,20)
    val celsiusArray3 = arrayOf(21,22,23,24,25)
    val celsiusArray4 = arrayOf(26,27,28,29,30)
    val celsiusArray5 = arrayOf(31,32,33,34,35,37,38,39,40,41,42,43,44,45,46)

    fun changeCelsius(int : Int?) : String {
        if(celsiusArray.contains(int)){
            return "სიცივეა და ჩაიცვი თბილად\nტემპერატურა : $int°C  "
        }
        if (celsiusArray1.contains(int)){
            return "საკაიფოდ გრილა და მოიცვი რამე \nტემპერატურა : $int°C  "
        }
        if (celsiusArray2.contains(int)){
            return "მცივანებმა ჩაიცვით , დანარჩენებმა იკაიფეთ \nტემპერატურა : $int°C "
        }
        if(celsiusArray3.contains(int)){
            return "უკვე საკაიფოდ ცხელა ბრატან \nტემპერატურა : $int°C "
        }
        if(celsiusArray4.contains(int)){
            return "ბევრი ლუდია საჭირო \nტემპერატურა : $int°C "
        }
        if(celsiusArray5.contains(int)){
            return "ადუღებს ყველაფერს \nტემპერატურა : $int°C "
        }
        if (celsiusYinva.contains(int)) {
            return "ყინვაა , თბილაც ჩაიცვი \nტემპერატურა : $int°C "
        }
        if (int == 0){
            return "არც იქით და არც აქეთ \n0 გრადუსია ბრატ"
        }


        return "$int°C"
    }

    fun changeWeather(string : String) : String{

        if (string == "clear sky"){

//            return "ცა მოწმენდილია რავარც პოლები"
            return "ესეთ მოწმენდილ ცასა \n ვერსადა ნახავ სხვასა"
        }
        if(string == "few clouds"){
            return "კედელს ატაკე დუბელი \n აქა იქ არის ღრუბელი"
        }
        if(string == "overcast clouds"){
            return "მოღრუბლულია ცაო \n ან რამ გაგაოცაო ?! "
        }
        if(string == "broken clouds"){
            return "როგორც გაუწყე წუხელი \n მზეს დაგვიფარავს ღრუბელი "
        }
        if (string == "scattered clouds"){
            return "როგორც გაუწყე წუხელი \n მზეს დაგვიფარავს ღრუბელი "
        }
        if (string == ""){
            return "წვიმს და სველდება მიდამო \n არ გაგვაფრთხილა გნიდამო"
        }
        if (string == ""){
            return "თავსხმა წვიმაა ჯიმა \n ბედმა არ გაგიღიმა"
        }
        if (string == "light rain"){
            return "მოდის ლაითი წვიმა \n შესცა ბაყაყსა ვირმა "
//            შესცა ბაყაყსა ვირმა
        }




        return ""
    }







    fun translateFromEnToGeo(string : String){



        string.forEach {




        }

    }

    fun change(i : Char) : String{

        if (i.toLowerCase().equals("A") || i.toLowerCase().equals("a")){
            return "ა"
        }
        if (i.toLowerCase().equals("B") || i.toLowerCase().equals("b")){
            return "ბ"
        }
        if (i.toLowerCase().equals("C") || i.toLowerCase().equals("c")){
            return "ც"
        }
        if (i.toLowerCase().equals("D") || i.toLowerCase().equals("d")){
            return "დ"
        }
        if (i.toLowerCase().equals("E") || i.toLowerCase().equals("e")){
            return "ე"
        }
        if (i.toLowerCase().equals("F") || i.toLowerCase().equals("f")){
            return "ფ"
        }
        if (i.toLowerCase().equals("G") || i.toLowerCase().equals("g")){
            return "გ"
        }
        if (i.toLowerCase().equals("H") || i.toLowerCase().equals("h")){
            return "ჰ"
        }
        if (i.toLowerCase().equals("I") || i.toLowerCase().equals("i")){
            return "ა"
        }
        if (i.toLowerCase().equals("J") || i.toLowerCase().equals("j")){
            return "ა"
        }
        if (i.toLowerCase().equals("K") || i.toLowerCase().equals("k")){
            return "ა"
        }
        if (i.toLowerCase().equals("L") || i.toLowerCase().equals("l")){
            return "ა"
        }
        if (i.toLowerCase().equals("M") || i.toLowerCase().equals("m")){
            return "ა"
        }
        if (i.toLowerCase().equals("N") || i.toLowerCase().equals("n")){
            return "ა"
        }
        if (i.toLowerCase().equals("O") || i.toLowerCase().equals("o")){
            return "ა"
        }





        return ""
    }



}